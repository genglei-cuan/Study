package cn.steve.rxandroid;

/**
 * Created by yantinggeng on 2015/11/2.
 */
public class Student {

    private int id;
    private Course[] courses;

    public Student(int id, Course[] courses) {
        this.id = id;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }
}
