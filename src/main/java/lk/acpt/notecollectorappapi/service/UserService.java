package lk.acpt.notecollectorappapi.service;

import lk.acpt.notecollectorappapi.dto.request.RequestAuthenticateUserDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;

public interface UserService {

    boolean addUser(RequestSaveUserDTO requestSaveUserDTO);

    boolean authenticateUser(RequestAuthenticateUserDTO requestAuthenticateUserDTO);
}
