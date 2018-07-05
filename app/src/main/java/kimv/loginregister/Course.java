package kimv.loginregister;

public class Course {

    String courseID;
    String courseName;
    String courseYear;
    String coursePeriod;
    String courseEC;
    String courseGrade;
    String courseComments;
    String courseSpecial;
    String courseStatus;

    public Course(){

    }

    public Course(String courseID, String courseName, String courseYear, String coursePeriod, String courseEC, String courseGrade, String courseComments, String courseSpecial, String courseStatus) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.coursePeriod = coursePeriod;
        this.courseEC = courseEC;
        this.courseGrade = courseGrade;
        this.courseComments = courseComments;
        this.courseSpecial = courseSpecial;
        this.courseStatus = courseStatus;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public String getCourseEC() {
        return courseEC;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public String getCourseComments() {
        return courseComments;
    }

    public String getCourseSpecial() {
        return courseSpecial;
    }

    public String getCourseStatus() {
        return courseStatus;
    }
}
