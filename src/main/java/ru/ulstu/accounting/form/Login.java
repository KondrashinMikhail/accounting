package ru.ulstu.accounting.form;


import javax.swing.*;
import java.awt.*;

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

        loginButton.addActionListener(e -> {
        });

        registerButton.addActionListener(e -> {
            new Register(this);
            dispose();
        });
    }
}
