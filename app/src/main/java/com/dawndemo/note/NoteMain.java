package com.dawndemo.note;

/**
 * 注释展示类{@link com.dawndemo.note.NoteMain} <br/>
 * 使用别名 ：{@linkplain com.dawndemo.note.NoteMain 注释}展示类 <br/>
 * NoteMain 中包含的方法{@link com.dawndemo.note.NoteUtils.Method} <br/>
 * {@linkplain #img() 添加图片} <br/>
 * {@linkplain #group() 分组} <br/>
 *
 * @see #method() <br/>
 * @see <a href="https://www.jianshu.com/p/54e8964730b4">注释参考url</a>
 */
public class NoteMain {

    public void method() {

    }

    /**
     * @param param 方法param
     * @deprecated 废弃的method <br/>
     * 参考{@link #method(int)} (int)<br/>
     */
    public void method(String param) {
    }

    /**
     * 这是方法说明
     *
     * @param age
     * @throws IllegalArgumentException 校验参数有问题将抛出，如age < 0
     */
    void method(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must >= 0!!!");
        }
        // TODO ...
    }

    /**
     * <p>
     * <img width="320" height="1550" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnNext.png" alt=""> 图片</img><br>
     * <img width="80" height="80" src="file//:G:\demospace\Dawn\app\src\main\res\mipmap-mdpi\ic_launcher_round.png" ></img>
     * </p>
     */
    void img() {
    }

    /**
     * 分组
     * <ul>
     * <li>第一组</li>
     * <li>第二组</li>
     * </ul>
     */
    void group() {

    }
}
