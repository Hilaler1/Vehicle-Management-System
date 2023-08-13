package com.mobiliz.vehicleservice.repository;

import com.mobiliz.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    @Query(value = "select plate_number from tbl_vehicle where vehicle_group_id=?1 company_id=?2 and status != 'DELETED'", nativeQuery = true)
    List<String> findAllByGroupId(Long id,Long companyId);

    @Query(value = "select id from tbl_vehicle where status != 'DELETED' ", nativeQuery = true)
    List<Long> getAllIds();

    @Query(value = "select plate_number from tbl_vehicle where company_id=?1 and id=?2 and status != 'DELETED' ", nativeQuery = true)
    List<String> getPlateNumbers(Long companyId);

    @Query(value = "SELECT plate_number FROM tbl_vehicle WHERE id IN :ids AND status != 'DELETED'", nativeQuery = true)
    Set<String> getPlateNumbersByIdInAndStatusNot(@Param("ids") Set<Long> ids);


}
