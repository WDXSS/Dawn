package com.dawndemo.design.pattern;

/**
 * 军用计算机
 *
 * 检测硬件
 * 用户认证
 * Created by zc on 2017/4/27.
 */

public class MilitaryComputer extends AbstractComputer {

    public MilitaryComputer() {
        System.out.println("-----军用计算机-----");
    }

    @Override
    protected void checkHardware() {
        super.checkHardware();
        System.out.println("检查硬件防火墙");
    }

    @Override
    protected void login() {
        System.out.println("进行指纹之别等复杂的用户验证");
    }
}
