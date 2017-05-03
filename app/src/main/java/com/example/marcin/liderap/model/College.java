package com.example.marcin.liderap.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class College {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(foreign = true)
    private SchoolCoordinator schoolCoordinator;
    //@ForeignCollectionField
    //private ForeignCollection<Volunteer> volunteers;
    //@ForeignCollectionField
    //private ForeignCollection<Kid> kids;

    public College(SchoolCoordinator schoolCoordinator, ForeignCollection<Volunteer> volunteers, ForeignCollection<Kid> kids) {
        this.schoolCoordinator = schoolCoordinator;
        //this.volunteers = volunteers;
       // this.kids = kids;
    }

    public College(SchoolCoordinator schoolCoordinator, ForeignCollection<Kid> kids) {
        this.schoolCoordinator = schoolCoordinator;
        //this.kids = kids;
    }

    public College() {
    }

    public SchoolCoordinator getSchoolCoordinator() {
        return schoolCoordinator;
    }

    public void setSchoolCoordinator(SchoolCoordinator schoolCoordinator) {
        this.schoolCoordinator = schoolCoordinator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   //public ForeignCollection<Volunteer> getVolunteers() {
   //     return volunteers;
    //}

    //public void setVolunteers(ForeignCollection<Volunteer> volunteers) {
    //    this.volunteers = volunteers;
   // }

    //public ForeignCollection<Kid> getKids() {
    //    return kids;
    //}

    //public void setKids(ForeignCollection<Kid> kids) {
        //this.kids = kids;
    //}
}
