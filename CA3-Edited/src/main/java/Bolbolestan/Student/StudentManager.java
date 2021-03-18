package Bolbolestan.Student;

import Bolbolestan.Offering.Offering;
import Bolbolestan.exeptions.BolbolestanRulesViolationError;
import Bolbolestan.exeptions.BolbolestanStudentNotFoundError;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    String loggedInStudent = null;

    public Student getStudentById(String studentId) throws Exception{
        for (Student student : students)
            if (student.getId().equals(studentId))
                return student;
        throw new BolbolestanStudentNotFoundError();
    }

    public ArrayList<String> getStudentIds() {
        ArrayList<String> studentIds = new ArrayList<>();
        for (Student student : students)
            studentIds.add(student.getId());
        return studentIds;
    }

    public boolean doesStudentExist(String studentId) {
        for (Student student : students)
            if (student.getId().equals(studentId))
                return true;
        return false;
    }

    public boolean doesStudentExist(Student student) {
        for (Student existingStudent: students)
            if (existingStudent.isEqual(student))
                return true;
        return false;
    }

    public void addStudent(Student student) throws Exception {
        if (doesStudentExist(student))
            throw new BolbolestanRulesViolationError(String.format("Student with id %s already exists.", student.getId()));
        students.add(student);
    }

    public void addGradeToStudent(String studentId, Grade grade) throws Exception{
        Student student = getStudentById(studentId);
        student.addGrade(grade);
    }

    public void addToSelectedOfferings(String studentId, Offering offering) throws Exception {
        Student student = getStudentById(studentId);
        student.addToSelectedOfferings(offering);
    }

    public void removeFromWeeklySchedule(String studentId, Offering offering) throws Exception {
        Student student = getStudentById(studentId);
        student.removeFromWeeklySchedule(offering);
    }

//    public WeeklySchedule getWeeklySchedule(String studentId) throws Exception {
//        Student student = getStudentById(studentId);
//        return student.getWeeklySchedule();
//    }

    public WeeklySchedule getSelectedOfferings(String studentId) throws Exception {
        Student student = getStudentById(studentId);
        return student.getSelectedOfferings();
    }

    public WeeklySchedule getSubmittedOfferings(String studentId) throws Exception {
        Student student = getStudentById(studentId);
        return student.getSubmittedOfferings();
    }

    public boolean finalizeSchedule(String studentId) throws Exception {
        Student student = getStudentById(studentId);
        student.setSubmissionErrors();
        if (student.getSubmissionErrors().size() == 0) {
            student.finalizeSchedule();
            return true;
        }
        else
            return false;
    }

//    public int getTotalUnits(String studentId) throws Exception{
//        WeeklySchedule weeklySchedule = getWeeklySchedule(studentId);
//        if (weeklySchedule == null)
//            return  0;
//        int units = weeklySchedule.getTotalUnits();
//        return units;
//    }

    public ArrayList<Offering> getClassTimeConflicts(Student student, Offering offering, List<Offering> schedule) throws Exception {
        ArrayList<Offering> conflictingOfferings = null;
        for (Offering scheduleOffering : schedule) {
            if (offering.doesClassTimeCollide(scheduleOffering )) {
                if (conflictingOfferings == null)
                    conflictingOfferings = new ArrayList<>();
                conflictingOfferings.add(scheduleOffering);
            }
        }
        return conflictingOfferings;
    }

    public ArrayList<Offering> getClassTimeConflicts(String studentId, Offering offering) throws Exception {
        ArrayList<Offering> conflictingOffering = null;
//        Student student = getStudentById(studentId);
//        WeeklySchedule tempSchedule = student.getTempWeeklySchedule();
//        WeeklySchedule finalSchedule = student.getFinalWeeklySchedule();
//        if (tempSchedule != null) {
//
//        }
//        if (student.getWeeklySchedule() != null) {
//            List<Offering> studentWeeklySchedule = student.getWeeklySchedule().getOfferings();
//            for (Offering weekOffering : studentWeeklySchedule) {
//                if (offering.doesClassTimeCollide(weekOffering))
//                    if (conflictingOffering == null) {
//                        conflictingOffering = new ArrayList<>();
//                        conflictingOffering.add(weekOffering);
//                    }
//            }
//        }
        // HAVE TO CHANGE THE LOGIC
        return conflictingOffering;
    }

    public ArrayList<Offering> getExamTimeConflicts(String studentId, Offering offering) throws Exception {
        ArrayList<Offering> conflictingOffering = null;
//        Student student = getStudentById(studentId);
//        if (student.getWeeklySchedule() != null){
//            List<Offering> studentWeeklySchedule = student.getWeeklySchedule().getOfferings();
//            for (Offering weekOffering : studentWeeklySchedule) {
//                if (offering.doesExamTimeCollide(weekOffering))
//                    if (conflictingOffering == null) {
//                        conflictingOffering = new ArrayList<Offering>();
//                        conflictingOffering.add(weekOffering);
//                    }
//            }
//        }
        // HAVE TO CHANGE THE LOGIC
        return conflictingOffering;
    }

    public ArrayList<String> getPrerequisitesNotPassed(String studentId, Offering offering) throws Exception {
        Student student = getStudentById(studentId);
        return student.getPrerequisitesNotPassed(offering);
    }

    public void addCourseToStudent(String studentId, Offering offering) throws Exception {
        Student student = getStudentById(studentId);
//        ArrayList<Offering> conflictingClassTimes = getClassTimeConflicts(studentId, offering);
//        ArrayList<Offering> conflictingExamTimes = getExamTimeConflicts(studentId, offering);
//        ArrayList<String> prerequisitesNotPassed = getPrerequisitesNotPassed(studentId, offering);
//        if (conflictingClassTimes == null && conflictingExamTimes == null )
        student.addToSelectedOfferings(offering);
    }

//    public void removeAllOfferingsFromStudent(String studentId) throws Exception {
//        Student student = getStudentById(studentId);
//        student.getWeeklySchedule().removeAllCourses();
//    }

    public String getLoggedInId() { return this.loggedInStudent; }

    public Boolean isAnybodyLoggedIn() {
        if (loggedInStudent == null)
            return false;
        return true;
    }

    public void makeLoggedIn(String studentId) {
        this.loggedInStudent = studentId;
    }

    public void makeLoggedOut() {
        this.loggedInStudent = null;
    }

}
