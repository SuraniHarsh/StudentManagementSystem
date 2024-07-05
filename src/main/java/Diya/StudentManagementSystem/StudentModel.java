package Diya.StudentManagementSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StudentModel class represents a student with attributes mapped from JSON fields.
 */
public class StudentModel {
    @JsonProperty("Sr. No")
    private int srNo;

    @JsonProperty("Division Wise Student Count")
    private int divisionWiseStudentCount;

    @JsonProperty("EnrollmentNo.")
    private long enrollmentNo;

    @JsonProperty("Student")
    private String student;

    @JsonProperty("Division")
    private String division;

    @JsonProperty("Email ID")
    private String emailId;

    @JsonProperty("Remark, based on MIS")
    private String remarkBasedOnMIS;

    public StudentModel() {
    }

    public StudentModel(int srNo, int divisionWiseStudentCount, long enrollmentNo, String student, String division, String emailId, String remarkBasedOnMIS) {
        this.srNo = srNo;
        this.divisionWiseStudentCount = divisionWiseStudentCount;
        this.enrollmentNo = enrollmentNo;
        this.student = student;
        this.division = division;
        this.emailId = emailId;
        this.remarkBasedOnMIS = remarkBasedOnMIS;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getDivisionWiseStudentCount() {
        return divisionWiseStudentCount;
    }

    public void setDivisionWiseStudentCount(int divisionWiseStudentCount) {
        this.divisionWiseStudentCount = divisionWiseStudentCount;
    }

    public long getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRemarkBasedOnMIS() {
        return remarkBasedOnMIS;
    }

    public void setRemarkBasedOnMIS(String remarkBasedOnMIS) {
        this.remarkBasedOnMIS = remarkBasedOnMIS;
    }
}
