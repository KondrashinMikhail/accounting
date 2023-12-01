package ru.ulstu.accounting.logics.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ulstu.accounting.logics.models.NonWorkPeriod;
import ru.ulstu.accounting.logics.models.NonWorkingReason;
import ru.ulstu.accounting.logics.repositories.NonWorkingPeriodRepository;
import ru.ulstu.accounting.logics.services.interfaces.NonWorkingPeriodService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class NonWorkingPeriodServiceImpl implements NonWorkingPeriodService {
    private final NonWorkingPeriodRepository repository;

    @Override
    public List<NonWorkPeriod> findByReasonAndWorker(NonWorkingReason reason, Long workerId) {
        return repository.findByReasonAndWorkerId(reason, workerId);
    }

    @Override
    public void addPeriod(LocalDate from, LocalDate to, Double payment, NonWorkingReason reason, Long workerId) {
        NonWorkPeriod period = NonWorkPeriod.builder()
                .dateFrom(from)
                .dateTo(to)
                .payment(payment)
                .reason(reason)
                .workerId(workerId)
                .build();
        repository.save(period);
    }
}
