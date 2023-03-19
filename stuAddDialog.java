package jsystem_3;


import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class stuAddDialog extends JDialog implements ActionListener {
	// 定義我們需要的swing組件
	JLabel jl1, jl2, jl3, jl4, jl5;
    JButton jb1, jb2, jb3;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
    JPanel jp1, jp2, jp3;
    
    // owner他的父窗口;title視窗名稱;model指定他為 模式/非模式窗口
    public stuAddDialog(Frame owner, String title, boolean model) {
    	super(owner, title, model);// 調用父類別結構方法，達到對話框效果
    	jl1 = new JLabel("學");
    	jl2 = new JLabel("生");
    	jl3 = new JLabel("日");
    	jl4 = new JLabel("之");
    	jl5 = new JLabel("路");
    	
    	jtf1 = new JTextField();
    	jtf2 = new JTextField();
    	jtf3 = new JTextField();
    	jtf4 = new JTextField();
    	jtf5 = new JTextField();
    	
    	jb1 = new JButton("add");
    	jb2 = new JButton("deny");
    	jb3 = new JButton("unlight");
    	
    	jp1 = new JPanel();
    	jp2 = new JPanel();
    	jp3 = new JPanel();
    	
    	// 設置布局
    	jp1.setLayout(new GridLayout(5, 1));
    	jp2.setLayout(new GridLayout(5, 1));
    	
    	// 添加組件
    	// 欄位框框名稱
    	jp1.add(jl1);
    	jp1.add(jl2);
    	jp1.add(jl3);
    	jp1.add(jl4);
    	jp1.add(jl5);
    	
    	// 內容輸入對應
    	jp2.add(jtf4);// jp2 jtf4 jtf5
    	jp2.add(jtf5);
    	
    	jp3.add(jb1);
    	jp3.add(jb2);
    	jp3.add(jb3);
    	
    	this.add(jp1, BorderLayout.WEST);
    	this.add(jp2, BorderLayout.CENTER);
    	this.add(jp3, BorderLayout.SOUTH);
    	// 由jb1(add)執行action listener
    	jb1.addActionListener(this);
    	jb2.addActionListener(this);
    	jb3.addActionListener(this);
    	
    	// 展現
    	this.setSize(300, 200);
    	this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource()==jb1) {
    		StuModel2 temp = new StuModel2();
    		String sql = "insert into production.brands (brand_name) values(?)";
    		String[] paras = {jtf1.getText()};
    		//temp.addStu就是我們在StuModel2寫的addStu
    		if(temp.updStu(sql, paras)) {
    			JOptionPane.showMessageDialog((this, "加載失敗");
    		}
    		// 關閉對話視窗
    		this.dispose();
    	}else {
    		System.out.print("third~");
    		this.dispose();
    	}
    }
}
