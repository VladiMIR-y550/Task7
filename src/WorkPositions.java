public enum WorkPositions {
    DIRECTOR("Director", 8, 10000), ACCOUNTANT("Accountant", 6, 2000),
    MANAGER("Manager", 8, 5000), PROGRAMMER("Programmer", 6, 50),
    TESTER("Tester", 7, 35), DESIGNER("Designer", 7, 40);

    private final String position;
    private final int workSchedule;
    private final int rate;

    WorkPositions(String position, int workSchedule, int rate) {
        this.position = position;
        this.workSchedule = workSchedule;
        this.rate = rate;
    }

    public String getPosition() {
        return position;
    }

    public int getWorkSchedule() {
        return workSchedule;
    }

    public int getRate() {
        return rate;
    }
}