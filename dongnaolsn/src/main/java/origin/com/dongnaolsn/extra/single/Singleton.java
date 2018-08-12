package origin.com.dongnaolsn.extra.single;

/**
 * instance 实例
 * 单例模式<br>
 * <p>
 * <ul>
 * <li> <a href="https://www.cnblogs.com/dolphin0520/p/3920373.html">关键字：volatile </a> </li>
 * <li><a href="https://www.jianshu.com/p/cf57726e77f2">1.原子性，2.可见性 3.有序性</a></li>
 * <li><a href="https://www.jianshu.com/p/d52fea0d6ba5">Java 内存模型</a></li>
 * <li><a href="https://www.jianshu.com/p/157279e6efdb">volatile</a></li>
 * </ul>
 * <p>
 * <ul>
 * <li>并发编程中的三个概念1.原子性，2.可见性 3.有序性,保证数据的正确</pr><br>
 * <pr>原子性：执行过程中不可以被打断</pr><br>
 * <pr>可见性：多个线程访问同一个变量时，变量值改变后，其他线程可以看到变量发生变化</pr><br>
 * <pr>有序性：代码的执行顺序；Java 内存模型中为了提高效率， 在不影响执行结果的情况下编译器和处理器会对指令进行重新排序（单线程）</pr><br>
 * </li>
 * <li>volatile 保证可见性：有序性<br>
 * <pr>保证可见性：当以个共享变量被volatile 修饰，他会保证值被改变后立即更新到主内存中，其他线程取值时回去主内存中取</pr>
 * </li>
 * <ul>
 */
public class Singleton {


}
