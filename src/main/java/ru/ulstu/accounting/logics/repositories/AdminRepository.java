package ru.ulstu.accounting.logics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulstu.accounting.logics.models.Admin;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Boolean existsByLogin(String login);
    List<Admin> findAllByLogin(String login);
}
