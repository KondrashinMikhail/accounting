package ru.ulstu.accounting.logics.services.interfaces;

import ru.ulstu.accounting.logics.models.NonWorkPeriod;
import ru.ulstu.accounting.logics.models.NonWorkingReason;

import java.time.LocalDate;
import java.util.List;

public interface NonWorkingPeriodService {
    List<NonWorkPeriod> findByReasonAndWorker(NonWorkingReason reason, Long workerId);
    void addPeriod(LocalDate from, LocalDate to, Double payment, NonWorkingReason reason, Long workerId);
}
