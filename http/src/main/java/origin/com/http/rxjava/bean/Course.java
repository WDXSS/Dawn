package origin.com.http.rxjava.bean;

/**
 * 课程
 * Created by zc on 2018/4/24.
 */

public class Course {
    private String id;
    private String courseName;
    private Score score;//成绩
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
