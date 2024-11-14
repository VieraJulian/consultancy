package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.ProfessionalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "professionals")
public interface IProfessionalAdapter {

    @GetMapping("/professionals/{id}")
    public ProfessionalDTO getProfessional(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
