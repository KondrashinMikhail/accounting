package ru.ulstu.accounting.utils;

import ru.ulstu.accounting.logics.models.Admin;
import ru.ulstu.accounting.logics.services.interfaces.AdminService;
import ru.ulstu.accounting.logics.services.interfaces.NonWorkingPeriodService;
import ru.ulstu.accounting.logics.services.interfaces.WorkerService;

public class ServicesInitialization {
    public static AdminService adminServiceStatic;
    public static WorkerService workerServiceStatic;
    public static NonWorkingPeriodService nonWorkingPeriodServiceStatic;

    public static Admin currentAdmin = null;
}
