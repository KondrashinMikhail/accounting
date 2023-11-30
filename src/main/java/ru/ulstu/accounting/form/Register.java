package ru.ulstu.accounting.form;

import lombok.SneakyThrows;
import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationLocation;
import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationType;
import ru.ulstu.accounting.utils.DesignUtils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ru.ulstu.accounting.utils.ServicesInitialization.adminServiceStatic;

public class Register extends JFrame {
    private JPanel registrationPanel;
    private JLabel loginLabel;
    private JTextField textFieldLogin;
    private JLabel passwordLabel;
    private JPasswordField passwordFieldPassword;
    private JButton registerButton;
    private JButton cancelButton;
    private JLabel passwordConfirmLabel;
    private JPasswordField passwordFieldPasswordConfirm;
    private JPanel mainPanel;

    @SneakyThrows
    public Register(JFrame parent) {
        setTitle("Регистрация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 750));
        if (parent != null) {
            setSize(parent.getSize());
            setLocation(parent.getLocation());
        } else {
            setSize(new Dimension(1280, 832));
            setLocationRelativeTo(null);
        }
        setContentPane(registrationPanel);
        setVisible(true);

        defaultFieldsPaint();

        registerButton.addActionListener(e -> {
            defaultFieldsPaint();

            if (textFieldLogin.getText().isEmpty()
                    || String.valueOf(passwordFieldPassword.getPassword()).isEmpty() || String.valueOf(passwordFieldPasswordConfirm.getPassword()).isEmpty()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Необходимо заполнить все поля").showNotification();
                return;
            }

            if (!String.valueOf(passwordFieldPasswordConfirm.getPassword()).equals(String.valueOf(passwordFieldPassword.getPassword()))) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Пароли не совпадают").showNotification();
                return;
            }

            if (adminServiceStatic.isExistsByLogin(textFieldLogin.getText())) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "В системе есть пользователь с таким логином").showNotification();
                return;
            }

            adminServiceStatic.addAdmin(textFieldLogin.getText(), String.valueOf(passwordFieldPassword.getPassword()));

            new Login(this).showNotification(NotificationType.SUCCESS, "Регистрация выполнена, необходимо войти");
            dispose();
        });

        cancelButton.addActionListener(e -> {
            new Login(this);
            dispose();
        });

    }

    private void defaultFieldsPaint() {
        loginLabel.setForeground(DesignUtils.SUB_MAIN_COLOR);
        passwordLabel.setForeground(DesignUtils.SUB_MAIN_COLOR);
        passwordConfirmLabel.setForeground(DesignUtils.SUB_MAIN_COLOR);
        textFieldLogin.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.SUB_MAIN_COLOR));
        passwordFieldPassword.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.SUB_MAIN_COLOR));
        passwordFieldPasswordConfirm.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.SUB_MAIN_COLOR));
    }
}
