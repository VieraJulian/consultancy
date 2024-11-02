package com.consultancy.users.infrastructure.inputAdapter;

import com.consultancy.users.application.dto.RoleDTO;
import com.consultancy.users.application.dto.RoleRequestDTO;
import com.consultancy.users.infrastructure.inputPort.IRoleInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private final IRoleInputPort roleInputPort;

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    public RoleController(IRoleInputPort roleInputPort){
        this.roleInputPort = roleInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        try {
            RoleDTO roleDTO = roleInputPort.createRole(roleRequestDTO);
            return new ResponseEntity<>(roleDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating role {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleRequestDTO) {
        try {
            RoleDTO roleDTO = roleInputPort.updateRole(id, roleRequestDTO);
            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error editing role {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        try {
            List<RoleDTO> roleDTOList = roleInputPort.findAllRoles();
            return new ResponseEntity<>(roleDTOList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all roles {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable Long id) {
        try {
            RoleDTO roleDTO = roleInputPort.findRoleById(id);
            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting role {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable Long id) {
        try {
            String msj = roleInputPort.deleteRoleById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting role {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
