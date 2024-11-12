package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.ProfessionalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "professionals")
public interface IProfessionalAdapter {

    @GetMapping("/professionals/{id}")
    public ProfessionalDTO getProfessional(Long id);
}
