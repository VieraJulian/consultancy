package com.consultancy.consultancies.infrastructure.outputadapter;

import com.consultancy.consultancies.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvailabilityRepository extends JpaRepository<Availability, Long> {
}
