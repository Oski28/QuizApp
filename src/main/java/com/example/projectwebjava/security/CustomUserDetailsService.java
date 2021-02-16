package com.example.projectwebjava.security;

import com.example.projectwebjava.model.User;
import com.example.projectwebjava.model.UserRole;
import com.example.projectwebjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        }
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        convertAuthorities(user.getRoles())
                );
        return userDetails;
    }

    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoleSet) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : userRoleSet) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return authorities;
    }
}
