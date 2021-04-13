import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Freelance extends Department
{
    List<Employee> freelanceStaff = new ArrayList<>();
    Map<String, Employee> busyEmployee = new HashMap<>();
    private int amountTask;

    @Override
    boolean checkFreeEmployeeDepartment() {
        if (busyEmployee.size() == freelanceStaff.size()) {
            System.out.println("Все работники департамента заняты");
            return false;
        }
        return true;

    }
}