package com.task7.office.freelance;

import com.task7.office.Tasks;

public interface IupWork {

    void giveTaskFreelance(Tasks tasks, double costWorkPerHour);

    int getAmountTasks();

    double getSalaryFreelance();

}
