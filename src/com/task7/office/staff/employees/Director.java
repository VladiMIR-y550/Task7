package com.task7.office.staff.employees;

import com.task7.office.Tasks;
import com.task7.office.Utils;
import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.WorkPositions;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Director extends BasicEmployee {
    private final List<Tasks> listTasks = Arrays.asList(Tasks.MAKE_REPORTING, Tasks.SELL_SERVICES,
            Tasks.WRITE_CODE, Tasks.TESTING_PROGRAM, Tasks.DRAW_LAYOUT);

    public Director(String name, String id, Set<WorkPositions> professions) {
        this.setName(name);
        this.setPosition(WorkPositions.DIRECTOR);
        this.setProfessions(this.getPosition(), professions);
        this.setRate(10000);
        this.setSchedule(8);
        this.setBusyNow(0);
        this.setId(id);
        this.setCompletedTasks(0);
        this.setSalary(0.0);
        this.setRestWorkingTime(this.getSchedule());
        this.setJobDuties(WorkPositions.DIRECTOR.getJobDuties());
    }

    @Override
    public Tasks giveTask() {
        return Utils.randomChoice(listTasks);
    }
}