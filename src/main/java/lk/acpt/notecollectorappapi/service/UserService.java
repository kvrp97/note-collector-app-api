package lk.acpt.notecollectorappapi.service;

import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestUserLogInDTO;
import lk.acpt.notecollectorappapi.entity.User;

public interface UserService {
    User saveUser(RequestSaveUserDTO saveUserDTO);

    User authenticateUser(RequestUserLogInDTO logInDTO);
}
