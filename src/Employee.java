import java.util.*;

public class Employee extends Person {
    private final String name;
    private final PlacesOfWork placeOfWork;
    private final WorkPositions profession;
    private final String position;
    private final int workSchedule;
    private final int rate;
    private final String id;
    private final List<String> secondProfessions;
    private int completedTasks;
    private int busyNow;
    private int restWorkingTime;
    private boolean atWork;
    private double salary;

    public Employee(WorkPositions positions, PlacesOfWork placeOfWork) {
        if (positions.equals(WorkPositions.DIRECTOR)) {
            this.name = getSurname();
            this.placeOfWork = placeOfWork;
            this.profession = WorkPositions.DIRECTOR;
            this.position = profession.getPosition();
            this.workSchedule = profession.getWorkSchedule();
            this.rate = profession.getRate();
            this.id = generateId();
            this.secondProfessions = new ArrayList<>();
            this.completedTasks = 0;
            this.busyNow = 0;
            this.restWorkingTime = workSchedule;
            this.atWork = cameToWork();
            this.salary = 0.0;
        } else {
            this.name = getSurname();
            this.placeOfWork = placeOfWork;
            this.profession = positions;
            this.position = profession.getPosition();
            this.workSchedule = profession.getWorkSchedule();
            this.rate = profession.getRate();
            this.id = generateId();
            List<String> listSecondProfessions = Arrays.asList(WorkPositions.DESIGNER.getPosition(),
                    WorkPositions.TESTER.getPosition(), WorkPositions.PROGRAMMER.getPosition());
            this.secondProfessions = identifySecondProfession(listSecondProfessions, position);
            this.completedTasks = 0;
            this.busyNow = 0;
            this.restWorkingTime = workSchedule;
            this.atWork = cameToWork();
            this.salary = 0.0;
        }
    }

    public PlacesOfWork getPlaceOfWork() {
        return placeOfWork;
    }

    public WorkPositions getProfession() {
        return profession;
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

    public String getId() {
        return id;
    }

    public List<String> getSecondProfessions() {
        return secondProfessions;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getBusyNow() {
        return busyNow;
    }

    public int getRestWorkingTime() {
        return restWorkingTime;
    }

    public boolean isAtWork() {
        return atWork;
    }

    public double getSalary() {
        return salary;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks += completedTasks;
    }

    public void setBusyNow(int busyNow) {
        this.busyNow = busyNow;
    }

    public void setRestWorkingTime(int restWorkingTime) {
        this.restWorkingTime = restWorkingTime;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private List<String> identifySecondProfession(List<String> listSecondProfessions, String position) {
        Set<String> firstList = new LinkedHashSet<>();
        String profession;
        boolean getPosition = Math.random() < 0.8;
        while (getPosition) {
            profession = Utils.randomChoice(listSecondProfessions);
            if (!profession.equals(position)) {
                firstList.add(profession);
                getPosition = Math.random() < 0.5;
            }
        }
        return new ArrayList<>(firstList);
    }

    public void setAtWork(boolean atWork) {
        this.atWork = atWork;
    }

    String generateId() {
        return String.valueOf(UUID.randomUUID());
    }

    boolean cameToWork() {
        return restWorkingTime != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return rate == employee.rate && Objects.equals(name, employee.name) && profession == employee.profession && Objects.equals(position, employee.position) && Objects.equals(id, employee.id) && Objects.equals(secondProfessions, employee.secondProfessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profession, position, rate, id, secondProfessions);
    }

    @Override
    public String toString() {
        return placeOfWork + "\t" +
                name + "\t" +
                position + "\t" +
                completedTasks + "\t" +
                busyNow + "\t" +
                restWorkingTime + "\t" +
                salary + "\t" +
                secondProfessions + "\t" +
                "\n";
    }
}
