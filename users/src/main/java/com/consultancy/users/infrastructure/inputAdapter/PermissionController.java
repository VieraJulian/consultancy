package com.consultancy.users.infrastructure.inputAdapter;

import com.consultancy.users.application.dto.PermissionDTO;
import com.consultancy.users.application.dto.PermissionNameDTO;
import com.consultancy.users.infrastructure.inputPort.IPermissionInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final IPermissionInputPort permissionInputPort;

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    public PermissionController(IPermissionInputPort permissionInputPort) {
        this.permissionInputPort = permissionInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionNameDTO permissionNameDTO) {
        try {
            PermissionDTO permissionDTO = permissionInputPort.createPermission(permissionNameDTO);
            return new ResponseEntity<>(permissionDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating permission {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> findPermissionById(@PathVariable Long id) {
        try {
            PermissionDTO permissionDTO = permissionInputPort.findPermissionById(id);
            return new ResponseEntity<>(permissionDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting permission {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PermissionDTO>> findAllPermission() {
        try {
            List<PermissionDTO> permissionDTOList = permissionInputPort.findAllPermissions();
            return new ResponseEntity<>(permissionDTOList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all permissions {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermissionById(@PathVariable Long id) {
        try {
            String msj  = permissionInputPort.deletePermissionById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting permission {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
