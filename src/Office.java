import java.util.*;

public class Office {
    private int numberEmployeesNeededForTask;

    Iterator<Employee> iterEmployee;

    List<Employee> staff = new ArrayList<>();
    List<Employee> freelanceStaff = new ArrayList<>();
    List<Tasks> tasks = Arrays.asList(Tasks.values());

    TesterDepartment testers = new TesterDepartment();
    ProgrammerDepartment programmers = new ProgrammerDepartment();
    DesignerDepartment designers = new DesignerDepartment();
    AccountantDepartment accountants = new AccountantDepartment();
    ManagerDepartment managers = new ManagerDepartment();

    List<Employee> director = new ArrayList<>();

    List<Employee> listDoubleProfessionProgrammer = new ArrayList<>();
    List<Employee> listDoubleProfessionTester = new ArrayList<>();
    List<Employee> listDoubleProfessionDesigner = new ArrayList<>();
    List<Employee> listDoubleProfessionAccountant = new ArrayList<>();
    List<Employee> listDoubleProfessionManager = new ArrayList<>();

    Map<String, Employee> listEmployeeBusyNow = new HashMap<>();
    Map<String, Employee> listFreelanceBusyNow = new HashMap<>();

    void directorGiveTask() {
        for (int hours = WorkPositions.DIRECTOR.getWorkSchedule(); hours > 0; hours--) {
            if (checkRestWorkingTime(staff.get(0))) {
                int rangeAmountTask = 5;
                int numberTasks = Utils.randomChoiceBound(rangeAmountTask);
                for (int amountTask = numberTasks; amountTask >= 1; amountTask--) {       // для тестов - колличество заданий в час
                        numberEmployeesNeededForTask = requiredNumberEmployeesForTask();
                        giveTask(Utils.randomChoice(tasks));
                    director.get(0).setCompletedTasks(1);
                }
            }
            nextHour();
        }
    }

    public void giveTask(Tasks tasks) {
        switch (tasks) {
            case SELL_SERVICES:
                findEmployee(managers, managers.departmentStaff,Tasks.SELL_SERVICES, listDoubleProfessionManager);
                break;
            case MAKE_REPORTING:
                findEmployee(accountants, accountants.departmentStaff,Tasks.MAKE_REPORTING, listDoubleProfessionAccountant);
                break;
            case WRITE_CODE:
                findEmployee(programmers, programmers.departmentStaff,Tasks.WRITE_CODE, listDoubleProfessionProgrammer);
                break;
            case TESTING_PROGRAM:
                findEmployee(testers, testers.departmentStaff,Tasks.TESTING_PROGRAM, listDoubleProfessionTester);
                break;
            case DRAW_LAYOUT:
                findEmployee(designers, designers.departmentStaff,Tasks.DRAW_LAYOUT, listDoubleProfessionDesigner);
                break;
            default:
                break;
        }
    }

    void findEmployee(Department department, List<Employee> departmentStaff, Tasks tasks, List<Employee> listDoubleProfessions) {
        if (department.checkFreeEmployeeDepartment()) {
            makeWork(departmentStaff, tasks);
            if (numberEmployeesNeededForTask != 0) {
                makeWork(listDoubleProfessions, tasks);
                if (numberEmployeesNeededForTask != 0) {
                    findFreelanceEmployee(tasks);
                }
            }
        } else {
            if (listDoubleProfessions.size() != 0) {
                makeWork(listDoubleProfessions, tasks);
            } else if (numberEmployeesNeededForTask != 0) {
                findFreelanceEmployee(tasks);
            }
        }
    }


    void makeWork(List<Employee> department, Tasks tasks) {
        iterEmployee = department.iterator();
        while (iterEmployee.hasNext() && numberEmployeesNeededForTask != 0) {
            Employee employee = iterEmployee.next();
            if (checkEmployeeAtWork(employee)) {
                if (checkEmployeeAreFree(employee) && checkRestWorkingTime(employee)) {
                    startTask(employee, tasks.getWorkTime());
                    numberEmployeesNeededForTask--;
                }
            }
        }
    }

    void startTask(Employee employee, int workTime) {
        if (employee.getBusyNow() == 0) {
            employee.setCompletedTasks(workTime);
            employee.setBusyNow(workTime);
            registerBusyEmployees(employee);
        }
    }

    void nextHour() {
        hourHasPassed(staff);
        hourHasPassed(freelanceStaff);
    }

    void hourHasPassed(List<Employee> listStaff) {
        if (listEmployeeBusyNow.size() != 0) {
            iterEmployee = listStaff.iterator();
            while (iterEmployee.hasNext()) {
                Employee employee = iterEmployee.next();
                if (employee.getRestWorkingTime() != 0) {
                    if (!checkEmployeeAreFree(employee) && employee.getBusyNow() != 0) {
                        employee.setBusyNow(employee.getBusyNow() - 1);
                        if (!checkEmployeeAreFree(employee) && employee.getBusyNow() == 0) {
                            workerFreed(employee);
                        }
                    }
                    employee.setRestWorkingTime(employee.getRestWorkingTime() - 1);
                }
            }
        }
    }

    int allCompletedTask() {
        return director.get(0).getCompletedTasks();
    }

    double calculateAllPaid() {
        return calculatePaidSalary(staff) + calculatePaidSalary(freelanceStaff);
    }

    double calculatePaidSalary(List<Employee> listStaff) {
        double allPaid = 0;
        iterEmployee = listStaff.iterator();
        while (iterEmployee.hasNext()) {
            Employee employee = iterEmployee.next();
            allPaid += employee.getSalary();
        }
        return allPaid;
    }

    void calculateAllSalaryWeek() {
        calculateStaffSalary(staff);
        calculateStaffSalary(freelanceStaff);
    }

    void calculateStaffSalary(List<Employee> listStaff){
        iterEmployee = listStaff.iterator();
        while (iterEmployee.hasNext()) {
            Employee employee = iterEmployee.next();
            employee.setSalary(employee.getSalary() + calculateSalary(employee));
        }
    }

    double calculateSalary(Employee employee){
        if (employee.getProfession().equals(WorkPositions.DIRECTOR) ||
                employee.getProfession().equals(WorkPositions.ACCOUNTANT) ||
                employee.getProfession().equals(WorkPositions.MANAGER)) {
            return (double) employee.getRate() / 4;
        } else {
            return (double) employee.getRate() * employee.getCompletedTasks();
        }
    }

    void distributeByDepartments() {
        iterEmployee = staff.iterator();
        while (iterEmployee.hasNext()) {
            Employee employee = iterEmployee.next();
            if (employee.getPosition().equals(WorkPositions.PROGRAMMER.getPosition())) {
                programmers.departmentStaff.add(employee);
            } else if (employee.getPosition().equals(WorkPositions.TESTER.getPosition())) {
                testers.departmentStaff.add(employee);
            } else if (employee.getPosition().equals(WorkPositions.DESIGNER.getPosition())) {
                designers.departmentStaff.add(employee);
            } else if (employee.getPosition().equals(WorkPositions.ACCOUNTANT.getPosition())) {
                accountants.departmentStaff.add(employee);
            } else if (employee.getPosition().equals(WorkPositions.MANAGER.getPosition())) {
                managers.departmentStaff.add(employee);
            } else {
                director.add(employee);
            }
            if (employee.getSecondProfessions().size() != 0) {
                distributeEmployeesDoubleProfession(employee);
            }
        }
    }

    void distributeEmployeesDoubleProfession(Employee employee) {
        for (int index = 0; index < employee.getSecondProfessions().size(); index++) {
            if (employee.getSecondProfessions().get(index).equals(WorkPositions.PROGRAMMER.getPosition())) {
                listDoubleProfessionProgrammer.add(employee);
            } else if (employee.getSecondProfessions().get(index).equals(WorkPositions.TESTER.getPosition())) {
                listDoubleProfessionTester.add(employee);
            } else if (employee.getSecondProfessions().get(index).equals(WorkPositions.DESIGNER.getPosition())) {
                listDoubleProfessionDesigner.add(employee);
            }
        }
    }

    public void registerBusyEmployees(Employee employee) {
        listEmployeeBusyNow.put(employee.getId(), employee);
        registerDepartmentOrFreelance(employee);
    }

    void registerDepartmentOrFreelance(Employee employee) {
        if (employee.getPlaceOfWork().equals(PlacesOfWork.OFFICE)) {
            if (employee.getPosition().equals(WorkPositions.PROGRAMMER.getPosition())) {
                programmers.busyEmployee.put(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.TESTER.getPosition())) {
                testers.busyEmployee.put(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.DESIGNER.getPosition())) {
                designers.busyEmployee.put(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.MANAGER.getPosition())) {
                managers.busyEmployee.put(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.ACCOUNTANT.getPosition())) {
                accountants.busyEmployee.put(employee.getId(), employee);
            }
        } else {
            listFreelanceBusyNow.put(employee.getId(), employee);
        }
    }

    int requiredNumberEmployeesForTask() {
        int rangeNumberEmployeeForTask = 5;
        return (int) (1 + (Math.random() * rangeNumberEmployeeForTask));
    }

    boolean checkEmployeeAreFree(Employee employee) {
        if (listEmployeeBusyNow.containsKey((employee.getId()))) {
            return false;
        } else if (!listEmployeeBusyNow.containsKey((employee.getId()))) {
            return true;
        }
        return true;
    }

    void workerFreed(Employee employee) {
        listEmployeeBusyNow.remove(employee.getId());
        freedDepartmentOrFreelance(employee);
    }

    void freedDepartmentOrFreelance(Employee employee) {
        if (employee.getPlaceOfWork().equals(PlacesOfWork.OFFICE)) {
            if (employee.getPosition().equals(WorkPositions.PROGRAMMER.getPosition())) {
                programmers.busyEmployee.remove(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.TESTER.getPosition())) {
                testers.busyEmployee.remove(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.DESIGNER.getPosition())) {
                designers.busyEmployee.remove(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.MANAGER.getPosition())) {
                managers.busyEmployee.remove(employee.getId(), employee);
            } else if (employee.getPosition().equals(WorkPositions.ACCOUNTANT.getPosition())) {
                accountants.busyEmployee.remove(employee.getId(), employee);
            }
        } else {
            listFreelanceBusyNow.remove(employee.getId(), employee);
        }
    }

    boolean checkRestWorkingTime(Employee employee) {
        if (employee.getRestWorkingTime() == 0) {
            employee.setAtWork(false);
            return false;
        }
        return true;
    }

    boolean checkEmployeeAtWork(Employee employee) {
        return employee.isAtWork();
    }

    void startNewWorkDayAllStaff() {
        startNewWorkDay(staff);
        startNewWorkDay(freelanceStaff);
    }

    void startNewWorkDay(List<Employee> staff) {
        iterEmployee = staff.iterator();
        while (iterEmployee.hasNext()) {
            Employee employee = iterEmployee.next();
            employee.setAtWork(true);
            employee.setRestWorkingTime(employee.getWorkSchedule());
            employee.setBusyNow(0);
            employee.setCompletedTasks(0);
        }
    }

    //======================= FREELANCE ==========
    public void findFreelanceEmployee(Tasks tasks) {
        if (freelanceStaff.size() != 0) {
            iterEmployee = freelanceStaff.iterator();
            while (iterEmployee.hasNext() && numberEmployeesNeededForTask != 0) {
                Employee employee = iterEmployee.next();
                if (listEmployeeBusyNow.get(employee.getId()) == null && employee.getProfession().equals(tasks.getDemand())) {
                    startTask(employee, tasks.getWorkTime());
                    numberEmployeesNeededForTask--;
                }
            }
        }
        while (numberEmployeesNeededForTask != 0) {
            startTask(createFreelanceEmployee(tasks.getDemand()), tasks.getWorkTime());
            numberEmployeesNeededForTask--;
        }
    }

    public Employee createFreelanceEmployee(WorkPositions workPositions) {
        Employee freelanceEmployee = new Employee(workPositions, PlacesOfWork.FREELANCE);
        freelanceStaff.add(freelanceEmployee);
        return freelanceEmployee;
    }
}