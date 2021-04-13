public enum Tasks {
    WRITE_CODE(2, WorkPositions.PROGRAMMER),
    DRAW_LAYOUT(1, WorkPositions.DESIGNER),
    TESTING_PROGRAM(1, WorkPositions.TESTER),
    SELL_SERVICES(1, WorkPositions.MANAGER),
    MAKE_REPORTING(1, WorkPositions.ACCOUNTANT);

    private final int workTime;
    private final WorkPositions demand;

    Tasks(int workTime, WorkPositions demand) {
        this.workTime = workTime;
        this.demand = demand;
    }

    public int getWorkTime() {
        return workTime;
    }

    public WorkPositions getDemand() {
        return demand;
    }
}