import java.util.*;

public class Utils {
    private static final Random index = new Random();
    private static final List<WorkPositions> listWorkPositions = Arrays.asList(WorkPositions.PROGRAMMER, WorkPositions.MANAGER,
            WorkPositions.TESTER, WorkPositions.DESIGNER, WorkPositions.ACCOUNTANT);

    public static <T> T randomChoice(List<T> collection) {
        int randomIndex = index.nextInt(collection.size());
        return collection.get(randomIndex);
    }

    public static <T> int randomChoiceBound(int bound) {
//        return index.nextInt(10);                        //добавил +1 чтоб избавиться от 0
        return (int) (1 + (Math.random() * bound));
    }

    public static <T> void createRandomStaff(Collection<Employee> staff) {
        Employee director = new Employee(WorkPositions.DIRECTOR, PlacesOfWork.OFFICE);
        staff.add(director);
        int amountStaff = (int) (10 + (Math.random() * 90));
        for (int i = amountStaff; i > 0; i--) {
            Employee employee = new Employee(randomChoice(listWorkPositions), PlacesOfWork.OFFICE);
            staff.add(employee);
        }
    }


    public static <T> void createFinalStaff(Collection<Employee> staff) {
        Employee director = new Employee(WorkPositions.DIRECTOR, PlacesOfWork.OFFICE);
        staff.add(director);
        int amountStaff = 10;
        for (int i = amountStaff; i > 0; i--) {
            Employee employee = new Employee(randomChoice(listWorkPositions), PlacesOfWork.OFFICE);
            staff.add(employee);
        }
    }
//    public static <T> T randomChoice(List<T> collection) {
//        return collection.get((int) (Math.random() * collection.size()));
//    }
}
