package ru.ulstu.accounting.form;

import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationType;
import ru.ulstu.accounting.form.customGraphics.table.CustomTable;
import ru.ulstu.accounting.logics.models.Worker;
import ru.ulstu.accounting.utils.DesignUtils;
import ru.ulstu.accounting.utils.ServicesInitialization;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ulstu.accounting.utils.ServicesInitialization.workerServiceStatic;

public class AllWorkers extends JFrame {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton exitButton;
    private JButton deleteButton;
    private JButton profileButton;
    private JButton addButton;

    private Long selectedItemId;

    public AllWorkers(JFrame parent) {
        setTitle("Все работники");
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

        updateTableUI(fillInWorkers());
        deleteButton.setVisible(false);
        profileButton.setVisible(false);

        addButton.addActionListener(e -> {
            new AddWorker(this);
            dispose();
        });

        profileButton.addActionListener(e -> {
            new CertainWorker(this, selectedItemId);
            dispose();
        });

        deleteButton.addActionListener(e -> {
            workerServiceStatic.deleteById(selectedItemId);
            updateTableUI(fillInWorkers());
        });

        exitButton.addActionListener(e -> exitFromAccount());
    }

    private DefaultTableModel fillInWorkers() {
        String[] header = new String[]{"Id", "Имя", "Фамилия", "Дата регистрации", "Зарплата", "Трудовая"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        for (Worker worker : workerServiceStatic.findAll())
            model.addRow(new Object[]{
                    worker.getId(),
                    worker.getName(),
                    worker.getSurname(),
                    worker.getDateRegistered(),
                    worker.getSalary(),
                    worker.getBook(),

            });
        return model;
    }

    private void updateTableUI(DefaultTableModel model) {
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.removeColumn(table.getColumnModel().getColumn(0));
        table.setFont(DesignUtils.REGULAR_FONT);
    }

    private void exitFromAccount() {
        ServicesInitialization.currentAdmin = null;
        new Login(null).showNotification(NotificationType.SUCCESS, "Выход произведен успешно!");
        dispose();
    }

    private void createUIComponents() {
        table = new CustomTable();
        scrollPane = new JScrollPane();
        ((CustomTable) table).fixTable(scrollPane);
        table.getSelectionModel().addListSelectionListener(event -> {
            int viewRow = table.getSelectedRow();
            if (!event.getValueIsAdjusting() && viewRow != -1) {
                int modelRow = table.convertRowIndexToModel(viewRow);
                List<String> state = new ArrayList<>();
                for (int i = 0; i <= table.getColumnCount(); i++) {
                    Object modelValue = table.getModel().getValueAt(modelRow, i);
                    state.add(modelValue.toString());
                }

                selectedItemId = Long.decode(state.get(0));
                deleteButton.setVisible(true);
                profileButton.setVisible(true);
            }
        });
    }
}
