package com.capstone.project.busbooking;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByEmailId(emailId);
        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return new HashSet<GrantedAuthority>();
//    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <UserEntity> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getFirstName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
