package origin.com.java;

public class FinallyTest {

    public static void main(String[] args) {
        FinallyTest mFinallyTest = new FinallyTest();
//        System.out.println("test1 =" + mFinallyTest.test1());
//        System.out.println("test5 =" + mFinallyTest.test5());
        System.out.println("test6 =" + mFinallyTest.test6());
    }


    //正常的
    private int test1() {
        int b = 1;
        try {
            System.out.println("test1 try block");
            ++b;
            return b;
        } catch (Exception e) {
            ++b;
            System.out.println("catch block");
        } finally {
            b = b + 10;
            System.out.println("finally block");
        }
        System.out.println("last block");
        return b;
    }

    // 验证 finally 语句执行在 try/catch return 执行之后 返回之前
    private String test2() {
        int b = 1;
        try {
            System.out.println("test2 try block");
            ++b;
            return test3("try");
        } catch (Exception e) {
            ++b;
            System.out.println("catch block");
        } finally {
            b = b + 10;
            System.out.println("finally block");
        }
        System.out.println("last block");
        return b + "";
    }

    private String test3(String block) {
        System.out.println(" test3 return block = " + block);
        return "output return block ="+ block;
    }

    //验证 finally 语句执行在 catch return 执行之后 返回之前
    private int test4() {
        int b = 1;
        try {
            System.out.println("test4 try block");
            ++b;
            return b / 0;
        } catch (Exception e) {
            ++b;
            System.out.println("catch block");
            return b;
        } finally {
            b = b + 10;
            System.out.println("finally block");
        }

    }
    //验证 finally 中return有，返回结果，为 finally 的return
    private String test5() {
        int b = 1;
        try {
            System.out.println("test5 try block");
            ++b;
            return test3("try");
        } catch (Exception e) {
            ++b;
            System.out.println("catch block");
        } finally {
            b = b + 10;
            System.out.println("finally block");
            return test3("finally");
        }
    }
    //方法的return 通常情况是不会执行的，除非触发了catch
    private int test6() {
        int b = 1;
        try {
            System.out.println("test6 try block");
            ++b;
            return b/0;
        } catch (Exception e) {
            ++b;
            System.out.println("catch block");
        } finally {
            b = b + 10;
            System.out.println("finally block");
        }
        System.out.println("last block");
        return b ;
    }

}
