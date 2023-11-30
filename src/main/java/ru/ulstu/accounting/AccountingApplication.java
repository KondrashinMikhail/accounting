package ru.ulstu.accounting;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ulstu.accounting.form.Login;

import javax.swing.*;

@SpringBootApplication
public class AccountingApplication {
    @SneakyThrows
    public static void main(String[] args) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SpringApplication.run(AccountingApplication.class, args);
        new Login(null);
    }

}
