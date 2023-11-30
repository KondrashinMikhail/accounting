package ru.ulstu.accounting.logics.services.interfaces;


import ru.ulstu.accounting.logics.models.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> findAll();
    void deleteById(Long id);
    Worker findById(Long id);
    void addWorker(String name, String surname, Double salary, String book);
    void changeWorker(Long id, String name, String surname, Double salary, String book);
}
