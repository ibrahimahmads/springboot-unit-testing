package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.request.RegisterRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.entity.Role;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private final MemberService memberService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;

    @Autowired
    public AuthServiceImpl(MemberService memberService, CustomerService customerService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, RoleService roleService) {
        this.memberService = memberService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
    }

    @Override
    public void register(RegisterRequest registerRequest) {

        if (memberService.findMemberByUsername(registerRequest.getUsername())){
            throw new IllegalStateException("Username already exists");
        }

        Role userRole = roleService.findRoleByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        Member member = new Member();
        member.setUsername(registerRequest.getUsername());
        member.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        member.setRoles(Set.of(userRole));
        memberService.saveMember(member);

        Customer customer = new Customer();
        customer.setFullName(registerRequest.getFullName());
        customer.setEmail(registerRequest.getEmail());
        customer.setAddress(registerRequest.getAddress());
        customer.setGender(registerRequest.getGender());
        customer.setMember(member);

        customerService.saveCustomer(customer);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()
                )
        );
        String token = jwtUtil.generateToken(authentication);
        return new LoginResponse(token);
    }
}
