package com.chat.chatroom.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chat.chatroom.repo.UserRepo;

public class SecurityUserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not present");
        }
        return new SecurityUser() {
            {
                setId(user.getUserId());
                setAuthorities(List.of(() -> "read"));
                setPassword(user.getPassword());
                setUsername(user.getUserName());
                setAccountNonExpired(true);
                setAccountNonLocked(true);
                setCredentialsNonExpired(true);
                setEnabled(true);
            }
        };

    }

}