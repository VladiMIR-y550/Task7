package com.task7.office.staff.employees;

import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.WorkPositions;

import java.util.List;
import java.util.Set;

public class Accountant extends BasicEmployee {

    public Accountant(String name, String id, Set<WorkPositions> professions) {
        this.setName(name);
        this.setPosition(WorkPositions.ACCOUNTANT);
        this.setProfessions(this.getPosition(), professions);
        this.setRate(2000);
        this.setSchedule(6);
        this.setBusyNow(0);
        this.setId(id);
        this.setCompletedTasks(0);
        this.setSalary(0.0);
        this.setRestWorkingTime(this.getSchedule());
        this.setJobDuties(WorkPositions.ACCOUNTANT.getJobDuties());
    }

    private double calculateSalaryEmployee(BasicEmployee employee) {
        if (employee.getPosition().equals(WorkPositions.DIRECTOR) ||
                employee.getPosition().equals(WorkPositions.ACCOUNTANT) ||
                employee.getPosition().equals(WorkPositions.MANAGER)) {
            return (double) employee.getRate() / 4;
        } else {
            return (double) employee.getCompletedTasks() * employee.getRate();
        }
    }

    @Override
    public void calculateSalaryWeek(List<BasicEmployee> staff) {
        for (BasicEmployee employee : staff) {
            employee.setSalary(employee.getSalary() + calculateSalaryEmployee(employee));
        }
    }
}