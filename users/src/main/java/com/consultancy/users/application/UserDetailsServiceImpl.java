package com.consultancy.users.application;

import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.domain.UserEntity;
import com.consultancy.users.infrastructure.outputPort.IUserMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserMethods userMethods;

    @Autowired
    public UserDetailsServiceImpl(IUserMethods userMethods) {
        this.userMethods = userMethods;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user;

        try {
            user = userMethods.findByEmail(email);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found with email: " + email, e);
        }

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

}
