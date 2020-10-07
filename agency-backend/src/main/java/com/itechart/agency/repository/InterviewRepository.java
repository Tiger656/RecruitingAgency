package com.itechart.agency.repository;

import com.itechart.agency.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface InterviewRepository extends JpaRepository<Interview,Long> {

    @Query(value = "select  EXTRACT(HOUR from i.start_date_time), EXTRACT(HOUR from end_date_time) start_date_time FROM interviews i" +
            " where i.agency_id = :agencyId and i.expert_id = :expertId and EXTRACT(YEAR from start_date_time) = :year AND EXTRACT(MONTH from start_date_time) = :month AND EXTRACT(DAY from start_date_time) = :day", nativeQuery = true)
    List<Object[]> getManagersBusyHours(@Param("agencyId") Long agencyId, @Param("expertId") Long expertId, @Param("year") Integer year, @Param("month") Integer month, @Param("day") Integer day);

    List<Interview> findByAgencyIdAndManagerId(Long agencyId, Long managerId);

    List<Interview> findByAgencyId(Long agencyId);

    List<Interview> findByAgencyIdAndExpertIdAndStatusId(Long agencyId, Long managerId, Long statusId);
}
