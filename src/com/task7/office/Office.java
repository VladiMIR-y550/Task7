package com.task7.office;

import com.task7.office.freelance.IupWork;
import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.IManageEmployee;
import com.task7.office.staff.WorkPositions;
import com.task7.office.staff.employees.*;

import java.util.*;

public class Office implements IOffice {
    private List<BasicEmployee> staff = new ArrayList<>();
    private final Map<String, BasicEmployee> listEmployeeBusyNow = new HashMap<>();
    private int numberEmployeesNeededForTask;
    private int numberCompletedTasks;

    @Override
    public void createRandomStaff() {
        IncomingData inData = new IncomingData();
        List<WorkPositions> listPosition = Arrays.asList(WorkPositions.ACCOUNTANT, WorkPositions.MANAGER,
                WorkPositions.PROGRAMMER, WorkPositions.TESTER,
                WorkPositions.DESIGNER);
        this.staff.add(new Director(Utils.randomChoice(inData.getNameList()), generateId(), getListProfessions(listPosition)));
        int amountStaff = (int) (10 + (Math.random() * 90));
        WorkPositions position;
        for (int i = amountStaff; i > 0; i--) {
            position = Utils.randomChoice(listPosition);
            if (position.equals(WorkPositions.ACCOUNTANT)) {
                this.staff.add(new Accountant(Utils.randomChoice(inData.getNameList()), generateId(),
                        getListProfessions(listPosition)));
            } else if (position.equals(WorkPositions.MANAGER)) {
                this.staff.add(new Manager(Utils.randomChoice(inData.getNameList()), generateId(),
                        getListProfessions(listPosition)));
            } else if (position.equals(WorkPositions.PROGRAMMER)) {
                this.staff.add(new Programmer(Utils.randomChoice(inData.getNameList()), generateId(),
                        getListProfessions(listPosition)));
            } else if (position.equals(WorkPositions.TESTER)) {
                this.staff.add(new Tester(Utils.randomChoice(inData.getNameList()), generateId(),
                        getListProfessions(listPosition)));
            } else if (position.equals(WorkPositions.DESIGNER)) {
                this.staff.add(new Designer(Utils.randomChoice(inData.getNameList()), generateId(),
                        getListProfessions(listPosition)));
            }
        }
    }

    @Override
    public List<BasicEmployee> getStaff() {
        return staff;
    }

    @Override
    public void giveTask(IManageEmployee director, IupWork freelance) {
        com.task7.office.Tasks relyTask;
        final int numberTasks = Utils.randomChoiceBound(15);
        numberCompletedTasks += numberTasks;
        for (int amountTask = numberTasks; amountTask >= 1; amountTask--) {
            numberEmployeesNeededForTask = requiredNumberEmployeesForTask();
            relyTask = director.giveTask();
            completingTaskFreeEmployee(relyTask);
            if (numberEmployeesNeededForTask != 0) {
                for (int employeesLeft = numberEmployeesNeededForTask; employeesLeft > 0; employeesLeft--) {
                    freelance.giveTaskFreelance(relyTask, relyTask.getCostWorkPerHour());
                }
            }
        }
        hourHasPassed();
    }

    @Override
    public int getNumberCompletedTasks(IupWork freelance) {
        return numberCompletedTasks + freelance.getAmountTasks();
    }

    @Override
    public BasicEmployee findFreeEmployee(com.task7.office.Tasks tasks) {
        for (BasicEmployee employee : staff) {
            if (employee.getPosition().getJobDuties().equals(tasks) &&
                    listEmployeeBusyNow.get(employee.getId()) == null) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void startNewWorkDay() {
        for (BasicEmployee employee : staff) {
            employee.getOutOfWork();
        }
    }

    @Override
    public double calculatePaidSalary(IupWork freelance) {
        double officePaid = 0;
        for (BasicEmployee employee : staff) {
            officePaid += employee.getSalary();
        }
        return officePaid + freelance.getSalaryFreelance();
    }

    int requiredNumberEmployeesForTask() {
        final int rangeNumberEmployeeForTask = 20;
        return (int) (1 + (Math.random() * rangeNumberEmployeeForTask));
    }

    void completingTaskFreeEmployee(com.task7.office.Tasks tasks) {
        Iterator<BasicEmployee> iterator = staff.iterator();
        while (iterator.hasNext() && numberEmployeesNeededForTask != 0) {
            BasicEmployee employee = iterator.next();
            if (employee.getRestWorkingTime() != 0) {
                if (checkEmployeeNotBusy(employee) && checkingEmployeeProfessions(employee, tasks)) {
                    employee.doTheJob();
                    registerBusyEmployee(employee);
                    numberEmployeesNeededForTask--;
                }
            }
        }
    }

    boolean checkEmployeeNotBusy(BasicEmployee employee) {
        return listEmployeeBusyNow.get(employee.getId()) == null;
    }

    boolean checkingEmployeeProfessions(BasicEmployee employee, com.task7.office.Tasks tasks) {
        for (WorkPositions profession : employee.getProfessions()) {
            if (profession.getJobDuties().equals(tasks)) {
                return true;
            }
        }
        return false;
    }

    void registerBusyEmployee(BasicEmployee employee) {
        listEmployeeBusyNow.put(employee.getId(), employee);
    }

    void workerFreed(BasicEmployee employee) {
        listEmployeeBusyNow.remove(employee.getId());
    }

    void hourHasPassed() {
        Iterator<BasicEmployee> iterator = staff.iterator();
        if (listEmployeeBusyNow.size() != 0) {
            while (iterator.hasNext()) {
                BasicEmployee employee = iterator.next();
                if (employee.getRestWorkingTime() != 0) {
                    if (!checkEmployeeNotBusy(employee) && employee.getBusyNow() != 0) {
                        employee.setBusyNow(employee.getBusyNow() - 1);
                        if (!checkEmployeeNotBusy(employee) && employee.getBusyNow() == 0) {
                            workerFreed(employee);
                        }
                    }
                    employee.setRestWorkingTime(employee.getRestWorkingTime() - 1);
                }
            }
        }
    }

    String generateId() {
        return String.valueOf(UUID.randomUUID());
    }

    Set<WorkPositions> getListProfessions(List<WorkPositions> profession) {
        final int maxNumberProfessionsEmployee = 2;
        Set<WorkPositions> professions = new HashSet<>();
        int amount = Utils.randomChoiceBound(maxNumberProfessionsEmployee);
        for (int amountProfession = amount; amountProfession > 0; amountProfession--) {
            professions.add(Utils.randomChoice(profession));
        }
        return professions;
    }
}