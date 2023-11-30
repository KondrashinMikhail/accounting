package ru.ulstu.accounting.form;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
    private JPanel registrationPanel;
    private JLabel loginLabel;
    private JTextField textFieldLogin;
    private JLabel passwordLabel;
    private JPasswordField passwordFieldPassword;
    private JButton registerButton;
    private JButton cancelButton;
    private JTextField textFieldName;
    private JLabel nameLabel;
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

        cancelButton.addActionListener(e -> {
            new Login(this);
            dispose();
        });

    }
}
