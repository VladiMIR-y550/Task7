package com.task7.office;

import com.task7.office.freelance.IupWork;
import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.IManageEmployee;

import java.util.List;

public interface IOffice {

    void createRandomStaff();

    List<BasicEmployee> getStaff();

    void giveTask(IManageEmployee director, IupWork freelance);

    BasicEmployee findFreeEmployee(com.task7.office.Tasks tasks);

    void startNewWorkDay();

    double calculatePaidSalary(IupWork freelance);

    int getNumberCompletedTasks(IupWork freelance);

}
