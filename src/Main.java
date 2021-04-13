import java.io.*;
import java.util.List;

public class Main {
    public static double paid;
    public static int tasks;

    public static void main(String[] args) {
        Office office = new Office();
        Utils.createRandomStaff(office.staff);
        office.distributeByDepartments();
        for (int weeks = 4; weeks > 0; weeks--) {
            for (int days = 5; days > 0; days--) {
                office.directorGiveTask();
                office.startNewWorkDayAllStaff();
            }
            office.calculateAllSalaryWeek();
        }
        paid = office.calculateAllPaid();
        tasks = office.allCompletedTask();

        printReport(office.staff, office.freelanceStaff);


    }

    public static void printReport(List<Employee> listStaff, List<Employee> listFreelanceStaff) {
        File myFile = new File("month_report.txt");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile)));
            writer.println("Money spent " + paid + "\t" + "The number of assignments given by the director - " + tasks);
            writer.println();
            writer.println(listStaff);
            writer.println(listFreelanceStaff);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
