package com.carproject.application.repository;

import com.carproject.application.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Integer> {

    @Query("""
            SELECT rep
            FROM Repair AS rep
            INNER JOIN rep.workshop AS wo
            INNER JOIN rep.vehicle AS veh
            WHERE wo.id = :workshopId 
            """)
    public List<Repair> findAllByWorkshop_Id(@Param("workshopId") Integer workshopId
                                            );


    @Query("""
            SELECT rep
            FROM Repair AS rep
            INNER JOIN rep.workshop AS wo
            INNER JOIN rep.vehicle AS veh
            WHERE wo.id = :workshopId AND rep.id = :repairId 
            """)
    Repair findByWorkshopIdAndRepairId(@Param("workshopId") Integer workshopId,
                                       @Param("repairId") Integer repairId);


    @Query("""
            SELECT rep
            FROM Repair AS rep
            INNER JOIN rep.workshop AS wo
            INNER JOIN rep.vehicle AS veh
            WHERE veh.platNumber = :platNumber
            """)
    List<Repair> getRepairsByPlatNumber(@Param("platNumber") String platNumber);
}
