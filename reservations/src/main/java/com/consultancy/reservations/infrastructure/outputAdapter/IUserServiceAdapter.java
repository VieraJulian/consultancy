package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "users")
public interface IUserServiceAdapter {

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
