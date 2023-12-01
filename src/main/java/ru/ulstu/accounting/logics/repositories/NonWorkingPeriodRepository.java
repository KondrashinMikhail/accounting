package ru.ulstu.accounting.logics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulstu.accounting.logics.models.NonWorkPeriod;
import ru.ulstu.accounting.logics.models.NonWorkingReason;

import java.util.List;

@Repository
public interface NonWorkingPeriodRepository extends JpaRepository<NonWorkPeriod, Long> {
    List<NonWorkPeriod> findByReasonAndWorkerId(NonWorkingReason reason, Long workerId);

}
