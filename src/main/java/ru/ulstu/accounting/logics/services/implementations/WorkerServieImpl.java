package ru.ulstu.accounting.logics.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ulstu.accounting.logics.models.Worker;
import ru.ulstu.accounting.logics.repositories.WorkerRepository;
import ru.ulstu.accounting.logics.services.interfaces.WorkerService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkerServieImpl implements WorkerService {
    private final WorkerRepository repository;

    @Override
    public List<Worker> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Worker findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void addWorker(String name, String surname, Double salary, String book) {
        Worker worker = Worker.builder()
                .name(name)
                .surname(surname)
                .salary(salary)
                .book(book)
                .dateRegistered(LocalDate.now())
                .build();
        repository.save(worker);
    }

    @Override
    public void changeWorker(Long id, String name, String surname, Double salary, String book) {
        Worker worker = repository.findById(id).get();
        worker.setName(name);
        worker.setSurname(surname);
        worker.setSalary(salary);
        worker.setBook(book);
        repository.save(worker);
    }
}
