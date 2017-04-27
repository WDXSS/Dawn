package com.dawndemo.design.pattern;

import org.junit.Test;

/**
 * 设计模式之模板模式 pattern
 * AbstractClass :抽象类，定义一套算法结构
 * ConcreteClass1: 具体的实现类
 * ConcreteClass2: 具体的实现类
 * example:
 * 模拟电脑开机：1.首先打开电脑电源，2.电脑检测自身状态没有问题时，3.载入操作系统 4.对用户进行验证之后即可登录电脑
 *
 * Created by zc on 2017/4/27.
 */

public class Pattern {
    @Test
    public void testPattern(){
        CodeComputer codeComputer = new CodeComputer();
        codeComputer.startUp();
        MilitaryComputer militaryComputer = new MilitaryComputer();
        militaryComputer.startUp();
    }
}
