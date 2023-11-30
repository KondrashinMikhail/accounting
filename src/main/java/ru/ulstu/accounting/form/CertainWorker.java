package ru.ulstu.accounting.form;

import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationLocation;
import ru.ulstu.accounting.form.customGraphics.notification.notificationEnums.NotificationType;
import ru.ulstu.accounting.form.customGraphics.table.CustomTable;
import ru.ulstu.accounting.logics.models.NonWorkPeriod;
import ru.ulstu.accounting.logics.models.NonWorkingReason;
import ru.ulstu.accounting.logics.models.Worker;
import ru.ulstu.accounting.utils.DesignUtils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import static ru.ulstu.accounting.utils.ServicesInitialization.nonWorkingPeriodServiceStatic;
import static ru.ulstu.accounting.utils.ServicesInitialization.workerServiceStatic;

public class CertainWorker extends JFrame {
    private Worker worker;
    private List<NonWorkPeriod> vacations;
    private List<NonWorkPeriod> medical;
    private JPanel mainPanel;
    private JTextField nameTF;
    private JTextField surnameTF;
    private JTextField salaryTF;
    private JTextField bookTF;
    private JButton cancelButton;
    private JButton saveButton;
    private JScrollPane vacationScrollPane;
    private JTable vacationTable;
    private JScrollPane medicalScrollPane;
    private JTable medicalTable;
    private JButton addVacation;
    private JTextField fromVacationTF;
    private JTextField toVacationTF;
    private JTextField paymentVacationTF;
    private JTextField fromMedicalTF;
    private JTextField toMedicalTF;
    private JTextField paymentMedicalTF;
    private JButton addMedical;


    public CertainWorker(JFrame parent, Long workerId) {
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
        setContentPane(mainPanel);
        setVisible(true);

        defaultFieldsPaint();

        worker = workerServiceStatic.findById(workerId);
        vacations = nonWorkingPeriodServiceStatic.findByReasonAndWorker(NonWorkingReason.VACATION, workerId);
        medical = nonWorkingPeriodServiceStatic.findByReasonAndWorker(NonWorkingReason.MEDICAL, workerId);

        nameTF.setText(worker.getName());
        surnameTF.setText(worker.getSurname());
        salaryTF.setText(worker.getSalary().toString());
        bookTF.setText(worker.getBook());

        updateTableMedicalsUI(fillInMedicals(workerId));
        updateTableVacationsUI(fillInVacations(workerId));

        cancelButton.addActionListener(e -> {
            new AllWorkers(this);
            dispose();
        });

        saveButton.addActionListener(e -> {

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

            workerServiceStatic.changeWorker(
                    workerId,
                    nameTF.getText(),
                    surnameTF.getText(),
                    Double.parseDouble(salaryTF.getText()),
                    bookTF.getText()
            );

            new AllWorkers(this);
            dispose();
        });

        addMedical.addActionListener(e -> {
            LocalDate from;
            LocalDate to;
            Double payment;

            if (toMedicalTF.getText().isEmpty() || fromMedicalTF.getText().isEmpty() || paymentMedicalTF.getText().isEmpty()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Все поля для больничного должны быть заполнены!").showNotification();
                return;
            }
            
            try {
                from = LocalDate.parse(fromMedicalTF.getText());
                to = LocalDate.parse(toMedicalTF.getText());
            } catch (Exception exception) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат даты!").showNotification();
                return;
            }

            try {
                payment = Double.parseDouble(paymentMedicalTF.getText());
            } catch (Exception exception) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат даты!").showNotification();
                return;
            }

            nonWorkingPeriodServiceStatic.addPeriod(from, to, payment, NonWorkingReason.MEDICAL, workerId);

            fromMedicalTF.setText("");
            toMedicalTF.setText("");
            paymentMedicalTF.setText("");
            updateTableMedicalsUI(fillInMedicals(workerId));
        });

        addVacation.addActionListener(e -> {
            LocalDate from;
            LocalDate to;
            Double payment;

            if (toVacationTF.getText().isEmpty() || fromVacationTF.getText().isEmpty() || paymentVacationTF.getText().isEmpty()) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Все поля для отпуска должны быть заполнены!").showNotification();
                return;
            }

            try {
                from = LocalDate.parse(fromVacationTF.getText());
                to = LocalDate.parse(toVacationTF.getText());
            } catch (Exception exception) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат даты!").showNotification();
                return;
            }

            try {
                payment = Double.parseDouble(paymentVacationTF.getText());
            } catch (Exception exception) {
                new NotificationForm(this, NotificationType.WARNING, NotificationLocation.TOP_CENTER, "Неверный формат даты!").showNotification();
                return;
            }

            nonWorkingPeriodServiceStatic.addPeriod(from, to, payment, NonWorkingReason.VACATION, workerId);

            fromVacationTF.setText("");
            toVacationTF.setText("");
            paymentVacationTF.setText("");
            updateTableVacationsUI(fillInVacations(workerId));
        });
    }

    private void updateTableVacationsUI(DefaultTableModel model) {
        vacationTable.setModel(model);
        vacationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vacationTable.removeColumn(vacationTable.getColumnModel().getColumn(0));
        vacationTable.setFont(DesignUtils.REGULAR_FONT);
    }

    private void updateTableMedicalsUI(DefaultTableModel model) {
        medicalTable.setModel(model);
        medicalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medicalTable.removeColumn(medicalTable.getColumnModel().getColumn(0));
        medicalTable.setFont(DesignUtils.REGULAR_FONT);
    }

    private DefaultTableModel fillInVacations(Long workerId) {
        String[] header = new String[]{"Id", "С", "По", "Выплата"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        for (NonWorkPeriod period : nonWorkingPeriodServiceStatic.findByReasonAndWorker(NonWorkingReason.VACATION, workerId))
            model.addRow(new Object[]{
                    period.getId(),
                    period.getDateFrom(),
                    period.getDateTo(),
                    period.getPayment()

            });
        return model;
    }

    private DefaultTableModel fillInMedicals(Long workerId) {
        String[] header = new String[]{"Id", "С", "По", "Выплата"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        for (NonWorkPeriod period : nonWorkingPeriodServiceStatic.findByReasonAndWorker(NonWorkingReason.MEDICAL, workerId))
            model.addRow(new Object[]{
                    period.getId(),
                    period.getDateFrom(),
                    period.getDateTo(),
                    period.getPayment()

            });
        return model;
    }

    private void defaultFieldsPaint() {
        nameTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        surnameTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        bookTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        salaryTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        fromVacationTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        toVacationTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        paymentVacationTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        fromMedicalTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        toMedicalTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
        paymentMedicalTF.setBorder(new MatteBorder(0 ,0, 2, 0, DesignUtils.MAIN_COLOR));
    }

    private void createUIComponents() {
        vacationTable = new CustomTable();
        vacationScrollPane = new JScrollPane();
        ((CustomTable) vacationTable).fixTable(vacationScrollPane);

        medicalTable = new CustomTable();
        medicalScrollPane = new JScrollPane();
        ((CustomTable) medicalTable).fixTable(medicalScrollPane);
    }
}
