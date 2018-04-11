package origin.com.effectivejava.builder;

/**
 * Created by zc on 2018/4/4.
 */

public class StudentBuilder implements Builder<Student>{

    private final Student mStudent;

    public StudentBuilder() {
        mStudent = new Student();
    }

    public StudentBuilder setAge(int age){
        mStudent.age = age;
        return this;
    }

    @Override
    public Student build() {
        return mStudent;
    }

    private void testMouth(){
        String name = "";
        Student mStudent = new StudentBuilder().setAge(12).build();
    }

}
