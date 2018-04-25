package origin.com.http.rxjava.bean;

import java.util.List;

/**
 * Created by zc on 2018/4/24.
 */

public class Student {
    private String name;
    private List<Course> courseList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
