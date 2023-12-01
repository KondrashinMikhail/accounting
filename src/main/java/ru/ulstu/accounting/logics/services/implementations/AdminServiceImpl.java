package ru.ulstu.accounting.logics.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ulstu.accounting.logics.models.Admin;
import ru.ulstu.accounting.logics.repositories.AdminRepository;
import ru.ulstu.accounting.logics.services.interfaces.AdminService;
import ru.ulstu.accounting.utils.Security;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;

    @Override
    public void addAdmin(String login, String password) {
        Admin admin = Admin.builder()
                .login(login)
                .password(Security.encrypt(password))
                .build();
        repository.save(admin);
    }

    @Override
    public Boolean isExistsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public Admin getAdmin(String login, String password) {
        List<Admin> admins = repository.findAllByLogin(login);
        for (Admin admin : admins)
            if (Objects.equals(Security.decrypt(admin.getPassword()), password))
                return admin;
        return null;
    }
}
