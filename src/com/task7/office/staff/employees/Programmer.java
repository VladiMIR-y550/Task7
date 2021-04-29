package com.task7.office.staff.employees;

import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.WorkPositions;

import java.util.Set;

public class Programmer extends BasicEmployee {

    public Programmer(String name, String id, Set<WorkPositions> professions) {
        this.setName(name);
        this.setPosition(WorkPositions.PROGRAMMER);
        this.setProfessions(this.getPosition(), professions);
        this.setRate(50);
        this.setSchedule(6);
        this.setBusyNow(0);
        this.setId(id);
        this.setCompletedTasks(0);
        this.setSalary(0.0);
        this.setRestWorkingTime(this.getSchedule());
        this.setJobDuties(WorkPositions.PROGRAMMER.getJobDuties());
    }
}