package com.yang.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class FileChooser extends JFrame implements ActionListener{
    JButton open=null;
    JTextField field = null;
    JTable table = null;
    public static void main(String[] args) {
        new FileChooser();
    }
    public FileChooser(){
        //设置布局类型
        FlowLayout layout = new FlowLayout(3);
        this.getContentPane().setLayout(layout);
        this.setBounds(500, 500, 600, 300);
        this.setResizable(false);
        //添加菜单
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        //添加文本label
        JLabel label = new JLabel("请选择文件:");
        label.setSize(20,20);
        this.add(label);
        //添加文档框
        field = new JTextField(40);
        this.add(field);
        //添加文件选择按钮
        open=new JButton("open");
        open.setBounds(30,300, 80, 40);
        this.add(open);

        //
        table = new JTable(5,2);


        this.add(table);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹:"+file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件:"+file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());
        field.setText(file.getAbsolutePath());
    }

}
