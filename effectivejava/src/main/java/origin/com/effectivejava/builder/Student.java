package origin.com.effectivejava.builder;

/**
 * Created by zc on 2018/4/4.
 */
public class Student{
    //name 是必须赋值
    public String name;
    public int age;
    public String address;

    public Student() {
    }

    //遇到多个构造器参数时要考虑用构建器
    public Student(Builder builder) {
       this.name = builder.name;
       this.age = builder.age;
    }

    /**
     * 构建器 ---避免构造函数中参数太多，
     */
    private void testMethod(){
        Student student = new Student.Builder("lily").age(12).builder();
    }

    public class Builder{
        // //name 是必须赋值
        private String name;
        private int age;
        private String address;

        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Student builder(){
            return new Student(this);
        }
    }
}
