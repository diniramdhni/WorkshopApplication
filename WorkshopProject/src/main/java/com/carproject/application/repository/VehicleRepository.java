package com.carproject.application.repository;

import com.carproject.application.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("""
            SELECT veh
            FROM Vehicle AS veh
            WHERE veh.platNumber = :platNumber
            """)
    public Vehicle getByPlatNumber(@Param("platNumber") String platNumber);

    @Query("""
            SELECT COUNT(*)
            FROM Vehicle AS veh
            WHERE veh.platNumber = :platNumber
            """)
    public Integer count(@Param("platNumber") String platNumber);
}
