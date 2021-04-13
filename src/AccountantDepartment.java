import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountantDepartment extends Department {

    List<Employee> departmentStaff = new ArrayList<>();
    Map<String, Employee> busyEmployee = new HashMap<>();

    @Override
    boolean checkFreeEmployeeDepartment() {
        return busyEmployee.size() != departmentStaff.size();
    }
}