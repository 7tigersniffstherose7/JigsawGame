package com.game;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame的子类也是界面窗体
    //规定：GameFrame这个界面为游戏主界面，和游戏相关逻辑写在这里
    //创建二维数组，加载数据时用这个数组来维护
    int[][] data = new int[4][4];
    //优化路径的问题：
    String path = "image\\image\\animal\\animal3\\";//之后可以改
    int count = 0;//记步数

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImg();


        //最后才让他显示
        this.setVisible(true);
    }

    int x = 0;
    int y = 0;//这是记录空白的位置
    //定义二维数组，存正确数据：
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public boolean isVictory() {
        for (int i = 0; i < data.length; i++) {
            //data[i]代表一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    private void initData() {
        //把0-15打乱存入二维数组

        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //变成二维
        /*法一
        int n = 0;
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j) {
                data[i][j] = tempArr[n++];
            }*/
        //法二：
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    private void initImg() {
        //创建图片ImageIcon对象
        //创建一个JLabel对象（管理容器）
        //指定图片位置：
        //把管理容器添加到界面,但是要通过这个隐藏容器才行
        //优化后
        //这是根据二维数据来添加图片

        //先清空已经出现的图片：
        this.getContentPane().removeAll();

        if (isVictory()) {
            //显示胜利的图标：
            JLabel winLabel = new JLabel(new ImageIcon("image\\image\\win.png"));
            winLabel.setBounds(220, 230, 197, 73);
            this.getContentPane().add(winLabel);//这一步不能忘记
        }
        //计步
        JLabel stepCount = new JLabel("步数：" + count);
        stepCount.setLayout(null);
        stepCount.setBounds(30, 15, 100, 20);
        //改变字体大小
        stepCount.setFont(new Font("宋体", Font.BOLD, 20));

        this.getContentPane().add(stepCount);


        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                int num = data[j][i];
                //最好还是相对路径，同一个项目的目录下即可
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                jLabel.setBounds(105 * i + 110, 105 * j + 103, 105, 105);
                //给图片加边框BevelBorder
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));//让图片凹下去
                this.getContentPane().add(jLabel);
            }
        }
        //添加背景图片,注意先加载的上层，后加载的在下层。
        ImageIcon bg = new ImageIcon("image\\image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(65, 8, 508, 560);
        this.getContentPane().add(background);

        //要刷新一下：
        this.getContentPane().repaint();


    }

    Font f = new Font("sans-serif", Font.PLAIN, 27);
    //创建选项的条目对象：
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem helpItem = new JMenuItem("帮助");
    JMenuItem girlItem = new JMenuItem("女子");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    private void initJMenuBar() {
        //初始化菜单：
        //创建菜单对象：
        //Font font1=new Font("宋体", Font.BOLD,15);
        JMenuBar jMenuBar = new JMenuBar();
        //变换字体，这可真难，有些方法失效的，终于找到一个可以的
        //对菜单（法一）：
        UIManager.put("Menu.font", f);
        //对选项（法二）：
        replayItem.setFont(f);
        reLoginItem.setFont(f);
        closeItem.setFont(f);
        helpItem.setFont(f);
        sportItem.setFont(f);
        girlItem.setFont(f);
        animalItem.setFont(f);
        //创建菜单上两个选项（功能、关于我们）
        JMenu functionJMenu = new JMenu(" 功能 ");
        JMenu aboutJMenu = new JMenu(" 关于 ");
        JMenu changeJMenu = new JMenu(" 更换图片 ");

        //将每一个选项的条目分别添加到对应选项中
        functionJMenu.add(changeJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(helpItem);
        changeJMenu.add(sportItem);
        changeJMenu.add(girlItem);
        changeJMenu.add(animalItem);


        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        helpItem.addActionListener(this);
        girlItem.addActionListener(this);
        sportItem.addActionListener(this);
        animalItem.addActionListener(this);
        //将两个选项添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给界面设置菜单：
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //宽度
        this.setSize(700, 740);
        //标题
        this.setTitle("拼图游戏单机版");
        //设置页面置顶于idea
        this.setAlwaysOnTop(true);
        //让页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式：
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中方式，只有取消了才能自己设置xy轴
        this.setLayout(null);
        //给界面添加键盘监听事件：
        this.addKeyListener(this);


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //把界面中所有的图片都删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片;
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(110, 103, 420, 420);
            all.setBorder(new BevelBorder(BevelBorder.LOWERED));
            this.getContentPane().add(all);
            //加载背景图片
            ImageIcon bg = new ImageIcon("image\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(65, 8, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，胜利就结束不能再玩
        if (isVictory()) {
            return;//结束方法，不过每次这样判断有点慢的。
        }
        int code = e.getKeyCode();
        //左37，上38，右39，下40
        switch (code) {
            case 37:
                // System.out.println("左");
                count++;
                if (y == 3) {
                    break;
                }
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                initImg();
                break;
            case 38:
                // System.out.println("上");
                count++;
                if (x == 3) {
                    break;
                }
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                initImg();
                break;
            case 39:
                //System.out.println("右");
                count++;
                if (y == 0) {
                    break;
                }
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                initImg();
                break;
            case 40:
                // System.out.println("下");
                count++;
                if (x == 0) {
                    break;
                }
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                initImg();
                break;
            case 65:
                initImg();
                break;
            case 87:
                data = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                initImg();
                break;
            default:
                break;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //判断
        if (source == replayItem) {
            //必须先清零步数：
            count = 0;
            //再次打乱二维数组数据
            //这里注意，可能会导致空白块给整没了，自己去读一读这个initdata，所以把else删了
            initData();
            //重新加载图片
            initImg();

        } else if (source == reLoginItem) {
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();

        } else if (source == helpItem) {
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //创建管理图片的容器
            JLabel jLabel = new JLabel(new ImageIcon("image\\image\\help.jpg"));
            //设置位置
            jLabel.setBounds(0, 0, 100, 100);
            //把图片加到弹窗
            jDialog.getContentPane().add(jLabel);//一定要获得隐藏窗口对象用get才行
            //给弹窗设置大小：
            jDialog.setSize(1039, 784);
            //让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //让弹窗居中：
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法操作其他界面
            jDialog.setModal(true);
            //最后，让弹窗显示
            jDialog.setVisible(true);


        } else if (source == closeItem) {
            //直接关闭虚拟机
            System.exit(0);
        } else if (source == girlItem) {
            path = "image\\image\\girl\\girl10\\";
            count = 0;
            initData();
            initImg();

        } else if (source == sportItem) {
            Random r = new Random();
            path = "image\\image\\sport\\sport" + (r.nextInt(10) + 1) + "\\";
            count = 0;
            initData();
            initImg();

        } else if (source == animalItem) {
            Random r = new Random();
            path = "image\\image\\animal\\animal" + (r.nextInt(8) + 1) + "\\";
            count = 0;
            initData();
            initImg();

        }


    }
}
