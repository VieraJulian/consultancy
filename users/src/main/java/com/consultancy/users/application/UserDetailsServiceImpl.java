package com.consultancy.users.application;

import com.consultancy.users.application.dto.AuthLoginDTO;
import com.consultancy.users.application.dto.AuthResponseDTO;
import com.consultancy.users.application.dto.SignUpDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.utils.JwtUtils;
import com.consultancy.users.domain.Role;
import com.consultancy.users.domain.UserEntity;
import com.consultancy.users.infrastructure.outputPort.IRoleMethods;
import com.consultancy.users.infrastructure.outputPort.IUserMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserMethods userMethods;

    private final IRoleMethods roleMethods;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserDetailsServiceImpl(IUserMethods userMethods, IRoleMethods roleMethods, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userMethods = userMethods;
        this.roleMethods = roleMethods;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userMethods.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getRole())));

        user.getRole().getPermissions()
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isCredentialNotExpired(),
                user.isAccountNotLocked(),
                user.isCredentialNotExpired(),
                authorityList
        );
    }

    public AuthResponseDTO login(AuthLoginDTO loginDTO) {

        String email = loginDTO.email();
        String password = loginDTO.password();

        Authentication authentication = this.authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponseDTO(email, "Login ok", accessToken, true);
    }

    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String register(SignUpDTO signUpDTO) throws RoleNotFoundException {

        String username = signUpDTO.name();
        String email = signUpDTO.email();

        userMethods.findByUsername(username);

        userMethods.findByEmail(email);

        String password = passwordEncoder.encode(signUpDTO.password());

        Role role;

        if (email.contains("@admin.com")) {
            role = roleMethods.findById(1L);
        } else {
            role = roleMethods.findById(2L);
        }

        if (role == null) {
            return null;
        }

        UserEntity user = new UserEntity();

        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialNotExpired(true);
        user.setRole(role);

        userMethods.save(user);

        return "User registration successfully.";

    }

}
