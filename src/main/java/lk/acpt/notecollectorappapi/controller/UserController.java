package lk.acpt.notecollectorappapi.controller;

import lk.acpt.notecollectorappapi.dto.request.RequestUserLogInDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestSaveUserDTO;
import lk.acpt.notecollectorappapi.entity.User;
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

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveUser(@RequestBody RequestSaveUserDTO saveUserDTO){
        User savedUser = userService.saveUser(saveUserDTO);
        return new ResponseEntity<>(
                new StandardResponse(201,"User saved successfully", savedUser.getFirstName()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> authenticateUser(@RequestBody RequestUserLogInDTO logInDTO) {
        User authUser = userService.authenticateUser(logInDTO);
        return ResponseEntity.ok(new StandardResponse(200, "Authentication Successful", authUser.getFirstName()));
    }
}
