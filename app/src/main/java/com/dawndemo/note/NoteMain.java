package com.dawndemo.note;

/**
 * 注释展示类{@link com.dawndemo.note.NoteMain} <br/>
 * 使用别名 ：{@linkplain com.dawndemo.note.NoteMain 注释}展示类 <br/>
*   NoteMain 中包含的方法{@link com.dawndemo.note.NoteUtils.Method} <br/>
 *   {@linkplain #img() 添加图片}
 *  @see #method() <br/>
 *  @see <a href="https://www.jianshu.com/p/54e8964730b4">注释参考url</a>
 */
public class NoteMain {

    public void method(){

    }

    /**
     *
     * @deprecated 废弃的method <br/>
     * 参考{@link #method(int)} (int)<br/>
     *
     * @param param 方法param
     */
    public void method(String param){

    }

    /**
     * 这是方法说明
     * @param age
     * @exception IllegalArgumentException 校验参数有问题将抛出，如age < 0
     */
    void method(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must >= 0!!!");
        }
        // TODO ...
    }

    /**
     * <p>
     * <img width="640" height="310" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnNext.png" alt=""> 图片</img><br>
     *
     * </p>
     */
    void img(){

    }

}
