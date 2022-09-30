package com.carproject.application.repository;

import com.carproject.application.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
}
