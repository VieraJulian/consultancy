package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.ProfessionalDTO;
import com.consultancy.reservations.infrastructure.outputport.IProfessionalServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalServiceAdapterImpl implements IProfessionalServicePort {

    private final IProfessionalAdapter professionalAdapter;

    @Autowired
    public ProfessionalServiceAdapterImpl(IProfessionalAdapter professionalAdapter) {
        this.professionalAdapter = professionalAdapter;
    }

    @Override
    public ProfessionalDTO getProfessional(Long professionalId) {
        return professionalAdapter.getProfessional(professionalId);
    }
}
