package com.task7.office.baseData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Name implements GetName {

    @Override
    public List<String> getNameList() {
        Set<String> nameList = new HashSet<>();
        String path = "C:\\Java\\Task7\\Surname.txt";
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                nameList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(nameList);
    }
}
