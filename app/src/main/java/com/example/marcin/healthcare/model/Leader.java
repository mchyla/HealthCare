package com.example.marcin.healthcare.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Leader {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<College> collegeList;

    public Leader(ArrayList<College> collegeList) {
        this.collegeList = collegeList;
    }

    public Leader() {
    }

    public ArrayList<College> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(ArrayList<College> collegeList) {
        this.collegeList = collegeList;
    }
}
