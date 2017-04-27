package com.dawndemo.design.pattern;

/**
 * 码农的计算机
 * 需要输入密码
 * Created by zc on 2017/4/27.
 */

public class CodeComputer extends AbstractComputer {

    public CodeComputer() {
        System.out.println("-----码农的计算机------");
    }

    @Override
    protected void login() {
        System.out.println("码农只需要进行用户和密码验证就可以了");
    }
}
