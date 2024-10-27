package com.consultancy.consultancies.infrastructure.outputadapter;

import com.consultancy.consultancies.domain.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional, Long> {
}
