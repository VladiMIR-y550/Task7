package com.task7.office.freelance;

import com.task7.office.Tasks;

public class FreelanceUpWork implements IupWork {
private int amountTasks;
private double salaryFreelance;

    public int getAmountTasks() {
        return amountTasks;
    }

    public double getSalaryFreelance() {
        return salaryFreelance;
    }

    @Override
    public void giveTaskFreelance(Tasks tasks, double costWorkPerHour) {
        this.amountTasks++;
        this. salaryFreelance += costWorkPerHour;

    }
}
