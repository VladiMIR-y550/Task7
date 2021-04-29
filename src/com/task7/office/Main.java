package com.task7.office;

import com.task7.office.freelance.FreelanceUpWork;
import com.task7.office.freelance.IupWork;
import com.task7.office.staff.BasicEmployee;
import com.task7.office.staff.IAccountant;
import com.task7.office.staff.IManageEmployee;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        com.task7.office.IOffice office = new com.task7.office.Office();
        office.createRandomStaff();
        IManageEmployee director = office.getStaff().get(0);
        IAccountant accountant = office.findFreeEmployee(com.task7.office.Tasks.MAKE_REPORTING);
        IupWork freelance = new FreelanceUpWork();

        for (int weeks = 4; weeks > 0; weeks--) {
            for (int days = 5; days > 0; days--) {
                for (int hours = 8; hours > 0; hours--) {
                    office.giveTask(director, freelance);
                }
                office.startNewWorkDay();
            }
            accountant.calculateSalaryWeek(office.getStaff());
        }
        printReport(office.getStaff(), office.calculatePaidSalary(freelance), office.getNumberCompletedTasks(freelance));
    }

    public static void printReport(List<BasicEmployee> staff, double allPaid, int allTasks) {
        File myFile = new File("month_report.txt");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile)));
            writer.println("Money spent " + allPaid + "\t" + "The number of assignments given by the director - " + allTasks);
            writer.println();
            writer.println(staff);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
