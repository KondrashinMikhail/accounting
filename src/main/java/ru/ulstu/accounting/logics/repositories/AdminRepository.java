package ru.ulstu.accounting.logics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulstu.accounting.logics.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
