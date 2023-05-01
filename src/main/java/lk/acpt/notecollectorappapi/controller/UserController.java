package lk.acpt.notecollectorappapi.controller;

import lk.acpt.notecollectorappapi.dto.request.RequestAuthenticateUserDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;
import lk.acpt.notecollectorappapi.service.UserService;
import lk.acpt.notecollectorappapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveUser(@RequestBody RequestSaveUserDTO requestSaveUserDTO){
        boolean isSaved = userService.addUser(requestSaveUserDTO);
        return new ResponseEntity<>(
                new StandardResponse(201,"Success", isSaved),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/login")
    public ResponseEntity<StandardResponse> authenticateUser(@RequestBody RequestAuthenticateUserDTO requestAuthenticateUserDTO){
        boolean authenticated = userService.authenticateUser(requestAuthenticateUserDTO);
        return new ResponseEntity<>(
                new StandardResponse(200,"Success", authenticated),
                HttpStatus.OK
        );
    }
}
