package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "users")
public interface IUserServiceAdapter {

    @GetMapping("/users/{id}")
    public UserDTO getUser(Long id);
}
