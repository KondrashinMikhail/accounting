package ru.ulstu.accounting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.accounting.logics.services.interfaces.AdminService;
import ru.ulstu.accounting.logics.services.interfaces.NonWorkingPeriodService;
import ru.ulstu.accounting.logics.services.interfaces.WorkerService;

@SpringBootTest
class AccountingApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private NonWorkingPeriodService nonWorkingPeriodService;
    @Autowired
    private WorkerService workerService;

    @Test
    void contextLoads() {
    }

    @Test
    void insertWorkers() {
        workerService.addWorker(
                "name",
                "surname",
                35000.0,
                "1234.5"
        );
    }

    @Test
    void findAllWorkers() {
        workerService.addWorker(
                "name",
                "surname",
                35000.0,
                "1234.5"
        );
        workerService.findAll();
    }

    @Test
    void findWorker() {
        workerService.addWorker(
                "name",
                "surname",
                35000.0,
                "1234.5"
        );
        workerService.findById(1L);
    }

    @Test
    void changeWorker() {
        workerService.addWorker(
                "name",
                "surname",
                35000.0,
                "1234.5"
        );
        workerService.changeWorker(
                1L,
                "name_1",
                "surname_1",
                35000.0,
                "1234.5");
    }
}
