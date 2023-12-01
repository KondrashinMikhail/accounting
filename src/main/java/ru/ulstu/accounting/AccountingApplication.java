package ru.ulstu.accounting;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ulstu.accounting.form.AllWorkers;
import ru.ulstu.accounting.logics.services.interfaces.AdminService;
import ru.ulstu.accounting.logics.services.interfaces.NonWorkingPeriodService;
import ru.ulstu.accounting.logics.services.interfaces.WorkerService;
import ru.ulstu.accounting.utils.ServicesInitialization;

import javax.swing.*;

@SpringBootApplication
@AllArgsConstructor
public class AccountingApplication {
    private final AdminService adminService;
    private final WorkerService workerService;
    private final NonWorkingPeriodService nonWorkingPeriodService;

    @PostConstruct
    private void init() {
        ServicesInitialization.adminServiceStatic = adminService;
        ServicesInitialization.workerServiceStatic = workerService;
        ServicesInitialization.nonWorkingPeriodServiceStatic = nonWorkingPeriodService;
    }

    @SneakyThrows
    public static void main(String[] args) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SpringApplication.run(AccountingApplication.class, args);
        new AllWorkers(null);
    }
}
