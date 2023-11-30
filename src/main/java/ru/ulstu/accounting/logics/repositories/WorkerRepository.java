package ru.ulstu.accounting.logics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulstu.accounting.logics.models.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
