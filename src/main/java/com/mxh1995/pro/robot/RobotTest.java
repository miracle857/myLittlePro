package com.mxh1995.pro.robot;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotTest {
    public static void main(String[] args) throws Exception {
        RobotTest test = new RobotTest();
        Robot r=new Robot();//创建自动化工具对象
        while(true){
            test.wait(1000);
            r.keyPress(KeyEvent.VK_A);//按下左Contrl  keycode为17

            r.keyRelease(KeyEvent.VK_A);//释放左Control键
        }

    }
}
