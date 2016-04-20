package com.craftexercise.interfac.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Here we can implement user lookup from external/db sources
        if (StringUtils.isEmpty(username)) {
            logger.info("Username " + username + " not found");
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        //TODO change "password" to complex string
        return new User(username, "password", getGrantedAuthorities(username));
    }
    
    
        
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if (username.equalsIgnoreCase("admin")) {
            //adding permissions into Spring authorities, this will act as ROLE for authorization.
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if(username.equalsIgnoreCase("user")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        logger.info("Permission for " + username + " is - " + authorities.toString());
        return authorities;
    }
}