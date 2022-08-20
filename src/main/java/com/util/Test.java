package com.util;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        //1.创建一个游戏的主界面
        //javabean类描述界面
        //属性：高、宽
        /*业务逻辑：
         * 上下移动
         * 统计步数
         * 查看最终结果
         * 一键通关
         * .......
         * */
        JFrame game = new JFrame();
        game.setSize(605, 682);
        game.setVisible(true);//也可以用.show()，但是这个已经过时


        //2.创建一个登录界面
        /*业务逻辑:
         * 用户名、密码
         * 生成验证码
         * 比较判断
         * */
        JFrame login = new JFrame();
        login.setSize(488, 430);
        login.setVisible(true);

        //3.创建一个注册界面
        /*业务逻辑：
         * 获取用户名、密码（两次比较）
         * 提示成功或失败
         * */
        JFrame register = new JFrame();
        register.setSize(488, 500);
        register.setVisible(true);


    }

}
