package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestAuthenticateUserDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;
import lk.acpt.notecollectorappapi.entity.User;
import lk.acpt.notecollectorappapi.exception.DuplicateException;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.exception.UnauthorizedException;
import lk.acpt.notecollectorappapi.repo.UserRepo;
import lk.acpt.notecollectorappapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean addUser(RequestSaveUserDTO requestSaveUserDTO) {
        if (!userRepo.existsByEmailAddress(requestSaveUserDTO.getEmailAddress())) {
            User user = modelMapper.map(requestSaveUserDTO, User.class);
            userRepo.save(user);
            return true;
        }
        throw new DuplicateException("Email already in use");
    }

    @Override
    public boolean authenticateUser(RequestAuthenticateUserDTO requestAuthenticateUserDTO) {
        if (userRepo.existsByEmailAddress(requestAuthenticateUserDTO.getEmailAddress())) {
            User user = userRepo.getUserByEmailAddress(requestAuthenticateUserDTO.getEmailAddress());
            if (user.getPassword().equals(requestAuthenticateUserDTO.getPassword())) {
                return true;
            } else {
                throw new UnauthorizedException("Invalid login");
            }
        }
        throw new NotFoundException("No user found");
    }
}
