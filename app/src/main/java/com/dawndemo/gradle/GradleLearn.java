package com.dawndemo.gradle;

import com.dawndemo.BuildConfig;

/**
 * gradle 学习整理
 * Created by zc on 2018/9/19
 */
public class GradleLearn {
    //1.自定义，buildConfig
//1.debug 模式
// 在gradle 中添加 {}， 在debug模式下 BuildConfig中会生成BASE_URL
//    buildTypes {
//        debug{
//            buildConfigField "String", "BASE_URL", "http://www.jianshu.com"
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
//        }
//    }
    public static void main(String[] args) {
        GradleLearn gradleLearn = new GradleLearn();
        gradleLearn.debug();
    }

    /**
     * https://www.jianshu.com/p/3d9b23afe514 <br/>
     */
    private void debug() {
        //切换 Build Variant 打印 BuildConfig.DEBUG 结果
        System.out.println("BuildConfig.DEBUG= " + BuildConfig.DEBUG);
        if (BuildConfig.DEBUG) {
            System.out.println("打印debug 模式下的 Url = " + BuildConfig.BASE_URL);
        }
    }

//    buildTypes.all {
//    }

    //构建不同版本 ： https://developer.android.com/studio/build/build-variants?hl=zh-cn

    //多渠道打包

    //配置签名
}