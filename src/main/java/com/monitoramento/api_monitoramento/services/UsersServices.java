package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.entity.users.UserDto;
import com.monitoramento.api_monitoramento.entity.users.UserType;
import com.monitoramento.api_monitoramento.exceptions.UserAlreadyExists;
import com.monitoramento.api_monitoramento.repository.users.UserRepository;

import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServices implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean userExists(String username,String email){
        return this.userRepository.findByUsernameOrEmail(username,email) != null;
    }


    @Override
    public @NullMarked User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not Found: " + username);
        }
        return user;
    }

    public Boolean createUser(UserDto userDto){
        if (!userExists(userDto.username(), userDto.email())){
            String password = this.encode(userDto.password());
            User user = new User(userDto.username(), userDto.email(),password, UserType.Enum.USER.get(),userDto.registration());
            userRepository.save(user);
            return Boolean.TRUE;
        }
        else{
            throw new UserAlreadyExists(userDto.username());
        }
    }

    public String encode(String password){
        return passwordEncoder.encode(password);
    }

}
