package com.consultancy.users.infrastructure.inputAdapter;

import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.infrastructure.inputPort.IUserInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserInputPort userInputPort;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
        try {
            UserResponseDTO userResponseDTO = userInputPort.findUserById(id);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting user by id {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        try {
            UserResponseDTO userResponseDTO = userInputPort.createUser(userRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating user {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){
        try {
            UserResponseDTO userResponseDTO = userInputPort.updateUser(id, userRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error editing user {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> findUserByEmail(@RequestParam String email){
        try {
            UserResponseDTO userResponseDTO = userInputPort.findUserByEmail(email);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting user by email {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        try {
            String msj = userInputPort.deleteUserById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting user {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
