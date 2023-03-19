package jsystem_3;


import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class stuUpDialog extends JDialog implements ActionListener {
	// 定義我們需要的swing組件
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
    JButton jb1, jb2, jb3;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
    JPanel jp1, jp2, jp3;
    
    // owner他的父窗口;title視窗名稱;model指定他為 模式/非模式窗口
    public stuUpDialog(Frame owner, String title, boolean model,int rowNum, StuModel2 sm) {
    	super(owner, title, model);// 調用父類別結構方法，達到對話框效果
    	jl1 = new JLabel("product_id");
    	jl2 = new JLabel("brand_id");
    	jl3 = new JLabel("quant");
    	jl4 = new JLabel("玥");
    	jl5 = new JLabel("。");
    	jl6 = new JLabel("蒿");
    	
    	jtf1 = new JTextField();
    	jtf2 = new JTextField();
    	jtf3 = new JTextField();
    	jtf4 = new JTextField();
    	jtf5 = new JTextField();
    	jtf6 = new JTextField();
    	// field不能再是空白，要把我們選中對象的值們當作預設對象
    	jtf1.setText((String) sm.getValueAt(rowNum, 0));
    	jtf2.setText((String) sm.getValueAt(rowNum, 1));
    	//jtf3.setText((String) sm.getValueAt(rowNum, 2));
    	//jtf4.setText((String) sm.getValueAt(rowNum, 3));
    	//jtf5.setText((String) sm.getValueAt(rowNum, 4));
    	//jtf6.setText((String) sm.getValueAt(rowNum, 5));
    	
    	jb1 = new JButton("modify");
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
//    		PreparedStatement ps = null;
//    		ResultSet rs = null;
//    		Connection ct =null;
//    		try {
//    			System.out.print("here~");
//    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    			// 127.0.0.1表示你要連接到的數據庫ip
//    			//1433表示sql server默認端口值
//    			ct = DriverManager.getConnection
//    			("jdbc:sqlserver://LAPTOP-AVJCSDFI;DatabaseName=db_rider;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
//    			ps = ct.prepareStatement("UPDATE db_rider.production.brands\r\n"
//    									+ "SET brand_name = ?\r\n"
//    									+ "WHERE brand_id = ?");
//    			// sending value into ?
//    			// 附值即便是string也會自動轉成int,float
//    			//ps.setInt(1, 177);
//    			//ps.setInt(, )問題，那這個用意何在??
//    			ps.setString(1, jtf2.getText());
//    			ps.setString(2, jtf1.getText());
//    			//ps.setString(3, jtf1.getText());
////    			ps.setString(3, jtf3.getText());
////    			ps.setString(4, jtf4.getText());
////    			ps.setString(5, jtf5.getText());
//    			ps.executeUpdate();
//    			
//    			// 關閉 子項目對話框
//    			this.dispose();
//    			
//    		}catch(Exception et) {
//    			et.printStackTrace();
//    		}finally {
//    			try {
//            		// 強化
//            		if(ct!=null) {
//            			ct.close();
//            		}if(rs!=null) {
//            			rs.close();
//            		}if(ps!=null) {
//            			ps.close();
//            		}
//    			} catch (SQLException ex) {
//    				// TODO Auto-generated catch block
//    				ex.printStackTrace();
//    			}
//    		}
//    	}else {
//    		System.out.print("third~");
//    		this.dispose();
    	    // 做一個sql
    		String sql = ("UPDATE db_rider.production.brands\r\n"
					+ "SET brand_name = ?\r\n"
					+ "WHERE brand_id = ?");
    		String[] paras = {jtf1.getText(), jtf2.getText()};
    		StuModel2 temp = new StuModel2();
    		temp.updStu(sql, paras);
    		this.dispose();
    	    
    	} 		
    }
}
