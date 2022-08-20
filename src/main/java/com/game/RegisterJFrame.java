package com.game;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //与注册有关的写在这里
    public RegisterJFrame() {
        this.setSize(976, 1000);
        //标题
        this.setTitle("拼图游戏_注册");
        //设置页面置顶于idea
        this.setAlwaysOnTop(true);
        //让页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式：
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //让他显示
        this.setVisible(true);

    }
}
