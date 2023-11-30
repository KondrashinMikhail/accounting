package ru.ulstu.accounting.logics.services.interfaces;

import ru.ulstu.accounting.logics.models.Admin;

public interface AdminService {
    void addAdmin(String login, String password);
    Boolean isExistsByLogin(String login);
    Admin getAdmin(String login, String password);
}
