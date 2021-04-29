package com.task7.office.staff.employees;

import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.WorkPositions;

import java.util.Set;

public class Designer extends BasicEmployee {

    public Designer(String name, String id, Set<WorkPositions> professions) {
        this.setName(name);
        this.setPosition(WorkPositions.DESIGNER);
        this.setProfessions(this.getPosition(), professions);
        this.setRate(40);
        this.setSchedule(7);
        this.setBusyNow(0);
        this.setId(id);
        this.setCompletedTasks(0);
        this.setSalary(0.0);
        this.setRestWorkingTime(this.getSchedule());
        this.setJobDuties(WorkPositions.DESIGNER.getJobDuties());
    }
}
