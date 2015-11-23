package cn.steve.dagger.school;

/**
 * Created by yantinggeng on 2015/11/19.
 */
public class Student {

    public Student() {
        System.out.println("Student被构造");
    }

    public void study() {
        System.out.println(toString() + "Student is studing!");
    }


}
