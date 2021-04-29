package com.task7.office.staff;

import com.task7.office.Tasks;

public enum WorkPositions {
    DIRECTOR(Tasks.GIVE_TASK),
    MANAGER(Tasks.SELL_SERVICES),
    ACCOUNTANT(Tasks.MAKE_REPORTING),
    PROGRAMMER(Tasks.WRITE_CODE),
    TESTER(Tasks.TESTING_PROGRAM),
    DESIGNER(Tasks.DRAW_LAYOUT);

    private final Tasks jobDuties;

    WorkPositions(Tasks jobDuties) {
        this.jobDuties = jobDuties;
    }

    public Tasks getJobDuties() {
        return jobDuties;
    }
}
