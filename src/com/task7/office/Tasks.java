package com.task7.office;

public enum Tasks {
    GIVE_TASK(0, 0.0),
    WRITE_CODE(2, 50.0),
    DRAW_LAYOUT(1, 40.0),
    TESTING_PROGRAM(1, 35.0),
    SELL_SERVICES(1, (5000.0 / (8 * 5 * 4))),
    MAKE_REPORTING(1, (2000.0 / (6 * 5 * 4)));

    private final int workTime;
    private final double costWorkPerHour;

    Tasks(int workTime, double costWorkPerHour) {
        this.workTime = workTime;
        this.costWorkPerHour = costWorkPerHour;
    }

    public int getWorkTime() {
        return workTime;
    }

    public double getCostWorkPerHour() {
        return costWorkPerHour;
    }
}
