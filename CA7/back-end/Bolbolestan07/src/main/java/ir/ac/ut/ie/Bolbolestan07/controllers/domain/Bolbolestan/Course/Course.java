package ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.Course;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private String code;
    private String name;
    private int units;
    private String type;
    private ArrayList<String> prerequisites;

    public Course(String code, String name, int units, String type, ArrayList<String> prerequisites) {
        this.code = code;
        this.name = name;
        this.units = units;
        this.prerequisites = prerequisites;
        this.type = type;
    }

    public String getCourseCode() { return code; }

    public String getName() { return name; }

    public int getUnits() { return units; }

    public String getType() { return type; }

    public  void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<String> getPrerequisites() { return prerequisites; }

    public HashMap<String, ArrayList<String>> getPrerequisiteInfo() {
        //if(prerequisites.size() == 0)
            //return null;
        HashMap<String, ArrayList<String>> prerequisiteInfo =
            new HashMap<String, ArrayList<String>>(); 
            prerequisiteInfo.put(code, prerequisites);
            return prerequisiteInfo;
    }

    public void print() {
        this.code = code;
        this.name = name;
        this.units = units;
        this.prerequisites = prerequisites;
        this.type = type;
        System.out.println(String.format("course code : %s", code));
        System.out.println(String.format("course name : %s", name));
        System.out.println(String.format("course units : %d", units));
        System.out.println("Prerequisites : [ ");
        for (String prerequisite : prerequisites)
            System.out.println(String.format("  %s  ", prerequisite));
        System.out.println(" ]");
    }
}
