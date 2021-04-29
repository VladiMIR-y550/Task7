package com.task7.office;

import com.task7.office.baseData.GetName;
import com.task7.office.baseData.Name;

import java.util.List;

public class IncomingData implements GetName {

    @Override
    public List<String> getNameList() {
        GetName getNameData = new Name();
        return getNameData.getNameList();
    }
}
