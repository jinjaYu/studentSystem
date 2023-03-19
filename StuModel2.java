package jsystem_3;
// using module2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
// import javax.swing.table get AbstractTableModel
public class StuModel2 extends AbstractTableModel {
    
	// rowData用存放行數據
	// columnNames存放列名
	Vector rowData, columnNames;
	
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	Connection ct =null;
//	
//	String url = "jdbc:sqlserver://LAPTOP-AVJCSDFI;DatabaseName=db_rider;";
//	String security = "IntegratedSecurity=true;encrypt=true;trustServerCertificate=true";
//	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	

	// 因為包含增刪改addStu> updStu
	public boolean updStu(String sql, String[] paras) {
		// 現在來使用我們sqlHelper
		// 階段三，使用sqlHelper，想法如果城市的併發性不高(單一程式不會被多種的程式調用的意思?)
		// 可以使用static(但我們這裡跟static有業障，一直叫我們用static)
		
		sqlHelper sqlhelper = new sqlHelper();
		return sqlhelper.updExecute(sql, paras);
//		boolean b = true;
//		Properties info = new Properties( );
//		info.put("IntegratedSecurity", "true");
//		info.put("encrypt", "true");
//		info.put("trustServerCertificate", "true");
//
//		try {
//			// 加載驅動
//			Class.forName(driver);
//			// 建立連接
//			ct = DriverManager.getConnection(url, info);
//			ps = ct.prepareStatement(sql);
//			// 應用，循環附值
//			for(int i=0; i<paras.length; i++) {
//				ps.setString(i+1, paras[i]);
//			}
//			// 操作執行，會回傳值1代表成功，查查其他情況勒
//			if(ps.executeUpdate() != 1) {
//				b = false;
//			}
//		}catch(Exception e) {
//			b = false;
//			e.printStackTrace();
//		}finally {
//			try {
//	       		// 強化
//	       		if(rs!=null) {
//	       			rs.close();
//	       		}if(ct!=null) {
//	       			ct.close();
//	       		}if(ps!=null) {
//	       			ps.close();
//	       		}
//			}catch(SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		// return result in the end
//		return b;
	}
	// 查詢的部分，我們原使用init名稱(這個結構太簡單，無法符合需求)
	// 加強版queryStu
	// 查詢是一切的開始，查詢的本質就是初始化
	// 它會創建Vector rowData, columnNames，以此生成一切
	public void queryStu(String sql, String[] paras) {
//		if(sql.equals("")) {
//			sql="select * from dbo.Table_3";
//		}
		// 中部分
		// 最初的查詢配置位置
		columnNames = new Vector();
		columnNames.add("stID");
		columnNames.add("name");
		columnNames.add("sex");
		columnNames.add("age");
		columnNames.add("nick");
		
		rowData = new Vector();
		// rowData為行形式，可存入多值
		Vector row1 = new Vector();
		row1.add("1123");
		row1.add("貝利辛根");
		row1.add("man");
		row1.add("52");
		row1.add("斡磐 毒龍");
		// 加入到rowData
		rowData.add(row1);
	    // 修改，創建到這裡
		sqlHelper sqlhelper = null;
		try {
			// 看這裡，sqlhelper被創建在try裡面(結束後消失)，
			// 無法在關閉資源finally中被調用
			sqlhelper = new sqlHelper();
			ResultSet rs = sqlhelper.queryExecute(sql, paras);
			
			
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			// 127.0.0.1表示你要連接到的數據庫ip
//			//1433表示sql server默認端口值
//			ct = DriverManager.getConnection
//			("jdbc:sqlserver://LAPTOP-AVJCSDFI;DatabaseName=db_rider;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
//			ps = ct.prepareStatement(sql);
//			rs = ps.executeQuery();
			while(rs.next()) {
				Vector row2 = new Vector();
				row2.add(rs.getString(1));
				row2.add(rs.getString(2));
				row2.add(rs.getString(2));
				row2.add(rs.getString(2));
				row2.add(rs.getString(1));
				rowData.add(row2);
				// select * 在選取時要知道順序號
//				int brand_id = rs.getInt(1);
//				String brand_name = rs.getString(2);
//				String brand_name2 = rs.getString("brand_name");// 直接下名也可以
//				System.out.println(brand_id+' '+brand_name+' '+brand_name2);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 關閉資源，直接調用close方法即可
			sqlhelper.close();
//			try {
//	       		// 強化
//	       		if(rs!=null) {
//	       			rs.close();
//	       		}if(ct!=null) {
//	       			ct.close();
//	       		}if(ps!=null) {
//	       			ps.close();
//	       		}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
// StuModel2(String sql)的使用沒有意義了，因為我們數據模型層 是運用sqlHelper方法
// 來增刪改查，而不需要自己進行查詢(如原本的init)
//	// 通過傳遞sql來獲得數據模型
//	public StuModel2(String sql) {
//        this.init(sql);
//	}
	// 做一個構造函數，用於初始化我們的數據模型
//	public StuModel2(String sql){// 這個空殼留著不知道有甚麼用
//		//this.init("");
//	}

// AbstractTableModel所建立的三者	
// 下面三者在小視窗生成時就會被調用，看看	
	// 得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		//int num=111-getColumnCount();
		//System.out.println("holding in threads "+ getColumnCount()+"got interfered, trying to retrive missing data instantly after quantian-sec "+ num);
		return this.rowData.size();
	}
    // 得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stu
		return this.columnNames.size();
	}
    // 得到某行某列的資訊
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// 密集知識點，如何將每格格的數據顯示出來
		// 提醒因為是由AbstractTableModel建立的method，這裡的邏輯也會依照他的邏輯
		// 鏈結，想想看之前我們create new Vector--> n= Vector.add(value)*s次 -->最後，rowData.add(n)
		// 這樣rowData確實存有vectors的列數據群
		// 這裡的rowData.get(rowIndex)).get(columnIndex)
		// 對應每一個row在對應每一個column，取出數據
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
		// 這裡Vector包的地方很特別，是針對rowData get包落的
		
		// 再來要修正上端欄目顯示abc的問題
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
}
