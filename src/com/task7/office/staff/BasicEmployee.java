package com.task7.office.staff;

import com.task7.office.Tasks;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class BasicEmployee implements IEmployee, IManageEmployee, IAccountant {
    private String name;
    private WorkPositions position;
    private final Set<WorkPositions> professions = new LinkedHashSet<>();
    private int schedule;
    private int rate;
    private Tasks jobDuties;
    private String id;
    private int completedTasks;
    private int busyNow;
    private int restWorkingTime;
    private double salary;

    public void setName(String name) {
        this.name = name;
    }

    public WorkPositions getPosition() {
        return position;
    }

    public void setPosition(WorkPositions position) {
        this.position = position;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setJobDuties(Tasks jobDuties) {
        this.jobDuties = jobDuties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks += completedTasks;
    }

    public int getBusyNow() {
        return busyNow;
    }

    public void setBusyNow(int busyNow) {
        this.busyNow = busyNow;
    }

    public int getRestWorkingTime() {
        return restWorkingTime;
    }

    public void setRestWorkingTime(int restWorkingTime) {
        this.restWorkingTime = restWorkingTime;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<WorkPositions> getProfessions() {
        return professions;
    }

    public void setProfessions(WorkPositions profession, Set<WorkPositions> professions) {
        if (professions.size() != 0) {
            this.professions.add(profession);
            if (!profession.equals(WorkPositions.DIRECTOR)) {
                this.professions.addAll(professions);
            }
        }
    }

    @Override
    public void doTheJob() {
        if (this.getBusyNow() == 0) {
            setCompletedTasks(1);
            setBusyNow(this.jobDuties.getWorkTime());
        }
    }

    @Override
    public void getOutOfWork() {
        setRestWorkingTime(this.schedule);
        setBusyNow(0);
    }

    @Override
    public void calculateSalaryWeek(List<BasicEmployee> staff) {
        System.out.println("This employee does not know how to calculate the salary");
    }

    @Override
    public Tasks giveTask() {
        return null;            //это плохо, возвращать null
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicEmployee)) return false;
        BasicEmployee employee = (BasicEmployee) o;
        return schedule == employee.schedule && rate == employee.rate && completedTasks == employee.completedTasks &&
                busyNow == employee.busyNow && restWorkingTime == employee.restWorkingTime &&
                Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name) &&
                Objects.equals(professions, employee.professions) && jobDuties == employee.jobDuties &&
                Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, professions, schedule, rate, jobDuties, id, completedTasks,
                busyNow, restWorkingTime, salary);
    }

    @Override
    public String toString() {
        return this.name +
                "\t" + this.professions +
                "\t" + this.completedTasks +
                "\t" + this.busyNow +
                "\t" + this.jobDuties +
                "\t" + this.salary +
                "\n";
    }
}
