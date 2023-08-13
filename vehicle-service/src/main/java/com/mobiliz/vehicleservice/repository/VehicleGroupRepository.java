package com.mobiliz.vehicleservice.repository;

import com.mobiliz.vehicleservice.entity.VehicleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VehicleGroupRepository extends JpaRepository<VehicleGroup,Long> {

    @Query(value = "select id from tbl_vehicle_group ", nativeQuery = true)
    List<Long> getAllIds();


    @Query(value = "WITH RECURSIVE GroupHierarchy AS (\n" +
            "    SELECT id, name, parent_group_id\n" +
            "    FROM tbl_vehicle_group\n" +
            "    WHERE id IN :groupIds\n" +
            "    UNION\n" +
            "    SELECT g.id, g.name, g.parent_group_id\n" +
            "    FROM tbl_vehicle_group g\n" +
            "    INNER JOIN GroupHierarchy gh ON g.parent_group_id = gh.id\n" +
            ")\n" +
            "SELECT v.plate_number, vh.name AS group_name\n" +
            "FROM tbl_vehicle v\n" +
            "INNER JOIN GroupHierarchy gh ON v.vehicle_group_id = gh.id\n" +
            "INNER JOIN tbl_vehicle_group vh ON v.vehicle_group_id = vh.id\n" +
            "WHERE v.company_id = :companyId  AND v.status != 'DELETED'", nativeQuery = true)
    Set<String> getAuthenticatedVehicleGroups(@Param("groupIds") Set<Long> groupIds, @Param("companyId") Long companyId);

}
