package com.consultancy.reservations.infrastructure.outputport;

import com.consultancy.reservations.domain.dto.ProfessionalDTO;

public interface IProfessionalServicePort {

    ProfessionalDTO getProfessional(Long professionalId, String token);
}
