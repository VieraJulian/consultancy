package com.consultancy.consultancies.infrastructure.outputadapter;

import com.consultancy.consultancies.domain.Professional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional, Long> {

    @Query(value = "SELECT p FROM Professional")
    List<Professional> getAllProfessionals(Pageable pageable);
}
