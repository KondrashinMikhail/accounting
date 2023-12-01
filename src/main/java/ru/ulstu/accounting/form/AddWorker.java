package ru.ulstu.accounting.form;

import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationLocation;
import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationType;
import ru.ulstu.accounting.utils.DesignUtils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.regex.Pattern;

import static ru.ulstu.accounting.utils.ServicesInitialization.workerServiceStatic;

public class AddWorker extends JFrame{
    private JPanel mainPanel;
    private JTextField nameTF;
    private JTextField bookTF;
    private JTextField salaryTF;
    private JTextField surnameTF;
    private JButton cancelButton;
    private JButton addButton;

    public AddWorker(JFrame parent) {
        setTitle("Добавление сотрудника");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 750));
        if (parent != null) {
            setSize(parent.getSize());
            setLocation(parent.getLocation());
        } else {
            setSize(new Dimension(1280, 832));
            setLocationRelativeTo(null);
        }
        setContentPane(mainPanel);
        setVisible(true);

        defaultFieldsPaint();

        addButton.addActionListener(e -> {
            if (nameTF.getText().isEmpty() ||
                    surnameTF.getText().isEmpty() ||
                    salaryTF.getText().isEmpty() ||
                    bookTF.getText().isEmpty()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Необходимо заполнить все поля!").showNotification();
                return;
            }

            try {
                Double.parseDouble(salaryTF.getText());
            } catch (Exception exception) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат зарплаты!").showNotification();
                return;
            }

            if (!Pattern.compile("^([0-9][0-9][0-9][0-9][.][0-9])").matcher(bookTF.getText()).matches()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат кода трудовой книжки!").showNotification();
                return;
            }

            workerServiceStatic.addWorker(
                    nameTF.getText(),
                    surnameTF.getText(),
                    Double.parseDouble(salaryTF.getText()),
                    bookTF.getText()
            );

            new AllWorkers(this);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            new AllWorkers(this);
            dispose();
        });
    }

    private void defaultFieldsPaint() {
        nameTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        surnameTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        salaryTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        bookTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
    }
}
