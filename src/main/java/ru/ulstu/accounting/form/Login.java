package ru.ulstu.accounting.form;


import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationLocation;
import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationType;
import ru.ulstu.accounting.utils.DesignUtils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ru.ulstu.accounting.utils.ServicesInitialization.*;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField textFieldLogin;
    private JLabel passwordLabel;
    private JPasswordField passwordFieldPassword;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel mainPanel;

    public Login(JFrame parent) {
        setTitle("Авторизация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 750));
        if (parent != null) {
            setSize(parent.getSize());
            setLocation(parent.getLocation());
        } else {
            setSize(new Dimension(1280, 832));
            setLocationRelativeTo(null);
        }
        setContentPane(loginPanel);
        setVisible(true);

        defaultFieldsPaint();

        loginButton.addActionListener(e -> {
            defaultFieldsPaint();

            if (textFieldLogin.getText().isEmpty() || String.valueOf(passwordFieldPassword.getPassword()).isEmpty()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Необходимо заполнить поля 'Логин' и 'Пароль'!").showNotification();
                return;
            }

            boolean isUserExist = adminServiceStatic.isExistsByLogin(textFieldLogin.getText());

            if (!isUserExist) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Такого пользователя нет в системе").showNotification();
            }
            else {
                currentAdmin = adminServiceStatic.getAdmin(textFieldLogin.getText(), String.valueOf(passwordFieldPassword.getPassword()));
                new AllWorkers(this);
                dispose();
            }
        });

        registerButton.addActionListener(e -> {
            new Register(this);
            dispose();
        });
    }

    private void defaultFieldsPaint() {
        loginLabel.setForeground(DesignUtils.MAIN_COLOR);
        passwordLabel.setForeground(DesignUtils.MAIN_COLOR);
        textFieldLogin.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        passwordFieldPassword.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
    }

    public void showNotification(NotificationType type, String message) {
        new NotificationForm(this, type, NotificationLocation.TOP_CENTER, message).showNotification();
    }
}
