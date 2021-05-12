package com.ust.foodieapp.service;

import com.ust.foodieapp.exception.UserAlreadyExistsException;
import com.ust.foodieapp.exception.UserNotFoundException;
import com.ust.foodieapp.model.User;
import com.ust.foodieapp.model.UserDto;
import com.ust.foodieapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
   import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUserDetailsService.class);

    // load user by user name

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findByUsername(username);
            if(user==null){
                logger.info("user not found");
                throw new UsernameNotFoundException("User not found" + username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

    //register userr details to db throw exception if user already exists

    public User saveUser(User user) throws UserAlreadyExistsException {
            if(repository.existsByusername(user.getUsername())){
                logger.error("User account already exists ");
                throw new UserAlreadyExistsException();
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info("User saved");
            return repository.save(user);
    }

    //delete user details by passing user Id

    public User deleteUser(int id)throws UserNotFoundException{
        User user=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()){
            logger.error("Customer unknown");
           throw new UserNotFoundException();
        }
        user=repository.findById(id).get();
        repository.deleteById(id);
        logger.info("Customer information deleted");
        return user;
    }

    //get user details by using id

    public User getUserById(int id) throws UserNotFoundException {
        User user=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()){
            logger.error("user not found");
           throw new UserNotFoundException();
        }
        logger.info("User details fetched");
        user=repository.findById(id).get();
        return user;
    }

    //update password by using user id

    public User updateUser(User user,int id) throws UserNotFoundException{
        User userUp=null;
        Optional optional=repository.findById(id);
        if (!optional.isPresent()){
            logger.error("user not found");

              throw new UserNotFoundException();
        }
        logger.info("password updated");
        userUp=repository.findById(id).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userUp= repository.save(user);
        return userUp;
    }
}
