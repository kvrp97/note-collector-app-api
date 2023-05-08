package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestUserLogInDTO;
import lk.acpt.notecollectorappapi.entity.User;
import lk.acpt.notecollectorappapi.exception.DuplicateException;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.exception.UnauthorizedException;
import lk.acpt.notecollectorappapi.repo.UserRepo;
import lk.acpt.notecollectorappapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User saveUser(RequestSaveUserDTO saveUserDTO){
        if (userRepo.existsByEmailAddress(saveUserDTO.getEmailAddress())) {
            throw new DuplicateException("Email address already exists");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(saveUserDTO.getPassword());
        saveUserDTO.setPassword(encodedPassword);
        User user = modelMapper.map(saveUserDTO,User.class);
        userRepo.save(user);
        return user;
    }

    @Override
    public User authenticateUser(RequestUserLogInDTO logInDTO){
        Optional<User> optionalUser = userRepo.findByEmailAddress(logInDTO.getEmailAddress());
        if (optionalUser.isPresent()){
            User dbUser = optionalUser.get();
            if (new BCryptPasswordEncoder().matches(logInDTO.getPassword(), dbUser.getPassword())) {
                return dbUser;
            } else {
                throw new UnauthorizedException("Unauthorized user");
            }
        }
        throw new NotFoundException("No user is found for the given email :"+ logInDTO.getEmailAddress());
    }

}
