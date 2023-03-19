package jsystem_3;
// using module2

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// 予瑾澄，欸~今天陪我一天好嗎?在被子裡ˇˇ
/* 1
 * 2
 * */
// implement action listener
public class AddDele_copy2 extends JFrame implements ActionListener{
	JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1, jb2, jb3, jb4;
    JScrollPane jsp;
    JTextField jtf;
    /*sm存在內存洩漏的問題()，當你在每個方法上，都(區域)創建一個
     * 新的StuModel2 sm(即便名字相同)，系統仍是他為不同個體，而
     * 重複創建，前者們會固定指向他的對應對象，但後續無法再被呼叫
     * 使用(因為有新創建重名者)，但java回收機制GC也無法清掃他(因
     * 為有指向對象，所以不是孤兒，更無法回收)就這樣越來越多，造成性能問題!!!*/
    StuModel2 sm;
    
    // 要封裝delete了!------------------------
	//    PreparedStatement ps = null;
	//	ResultSet rs = null;
	//	Connection ct =null;
	// ------------------------------
    
    //Vector rowData, columnNames;
	JTable table = null;
	
	//PreparedStatement ps = null;
	//ResultSet rs = null;
	//Connection ct =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AddDele_copy2 ad = new AddDele_copy2();
        ad.test3();
	}
	// 現在要做一個新增東西的小視窗
	public void test3() {
		// 創建一個數據模型對象
		sm = new StuModel2();
		// 1=?必須統一格式，不得已而為之(reparedStatement不是不接這個嗎?)
		String[] paras = {"1"};
		sm.queryStu("select * from production.brands where 1=?", paras);
		
		// 上部分
		jp1 = new JPanel();
		
		jtf = new JTextField(10);
		jb1 = new JButton("search");
		// listen search action
		jb1.addActionListener(this);
		jl1 = new JLabel("please keyin name");
		// 將各部件加入panel1中，順序對應版面位置
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		// 下部分
		jp2 = new JPanel();
		
		jb2 = new JButton("add");
		jb2.addActionListener(this);
		jb3 = new JButton("modify");
		jb3.addActionListener(this);
		jb4 = new JButton("eliminate");
		jb4.addActionListener(this);
		
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
//		// 中部分
//		Vector columnNames = new Vector();
//		columnNames.add("stID");
//		columnNames.add("name");
//		columnNames.add("sex");
//		columnNames.add("age");
//		columnNames.add("nick");
//		rowData = new Vector();
//		// rowData為行形式，可存入多值
//		Vector row1 = new Vector();
//		row1.add("1561");
//		row1.add("貝利辛根");
//		row1.add("man");
//		row1.add("52");
//		row1.add("斡磐 毒龍");
//		// 加入到rowData
//		rowData.add(row1);
//        /**/
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			// 127.0.0.1表示你要連接到的數據庫ip
//			//1433表示sql server默認端口值
//			ct = DriverManager.getConnection
//			("jdbc:sqlserver://LAPTOP-AVJCSDFI;DatabaseName=db_rider;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
//			ps = ct.prepareStatement("select * from production.brands");
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				Vector row2 = new Vector();
//				row2.add(rs.getString(1));
//				row2.add(rs.getString(2));
//				row2.add(rs.getString(2));
//				row2.add(rs.getString(2));
//				row2.add(rs.getString(1));
//				rowData.add(row2);
//				// select * 在選取時要知道順序號
////				int brand_id = rs.getInt(1);
////				String brand_name = rs.getString(2);
////				String brand_name2 = rs.getString("brand_name");// 直接下名也可以
////				System.out.println(brand_id+' '+brand_name+' '+brand_name2);
////				
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//        		// 強化
//        		if(rs!=null) {
//        			rs.close();
//        		}if(ct!=null) {
//        			ct.close();
//        		}if(ps!=null) {
//        			ps.close();
//        		}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		// 初始化我們對應的數據模型對向一個
		sm = new StuModel2();
	    String[] paras2 = {"1"};
	    sm.queryStu("select * from production.brands where 1=?", paras2);
		// 初始化JTable
		//table = new JTable(rowData, columnNames);
		table = new JTable(sm);
		
		// 初始化jsp(JScrollPane
		jsp = new JScrollPane(table);
		// 把jsp放入到JFrame
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 判斷哪個按鈕被點擊?
		if(e.getSource() == jb1) {
			System.out.println("用戶希望查詢");
			
			// 現在我們已經完成將表的數據封裝到StuModel中了
			// 接下來要執行search就更容易了
			String name = this.jtf.getText().trim();
			String[] paras = {name};
			// sql query
			String sql = "select * from production.brands where brand_id=?";
			// 建構新的數據類模型，並更新
			// 這裡的StuModel2(sql)就沒意義了
			// sm = new StuModel2(sql);
			sm = new StuModel2();
			sm.queryStu(sql, paras);
			// 更新JTable
			table.setModel(sm);
		}
		// 衍生想法，將重複操作對象封裝成一個類，在後續使用上重複呼叫即可
		// setModel new StuModel.java
		
		// 新增學生
		else if(e.getSource() == jb2) {
			stuAddDialog sa = new stuAddDialog(this, "adding students", true);
			// 建構新的數據類模型，並更新
			sm = new StuModel2();
			String[] paras2 = {"1"};
			sm.queryStu("select * from production.brands where 1=?", paras2);
			// 更新JTable
			table.setModel(sm);
			
		}else if(e.getSource() == jb3) {
			int rowNum = this.table.getSelectedRow();
			if(rowNum==-1) {
				JOptionPane.showMessageDialog(this, "choose one at least...");
				return;
			}
			stuUpDialog sa = new stuUpDialog(this, "modifying students", true, rowNum, sm);
			// 建構新的數據類模型，並更新
			sm = new StuModel2();
			String[] paras2 = {"1"};
			sm.queryStu("select * from production.brands where 1=?", paras2);
			// 更新JTable
			table.setModel(sm);
			
		}else if(e.getSource() == jb4) {
			// 刪除用戶
			// 1.得到學生id號
			// getSelectRow會返回用戶選中的行；都沒選擇為default -1
			int rowNum = this.table.getSelectedRow();
			if(rowNum==-1) {
				JOptionPane.showMessageDialog(this, "choose one at least...");
				return;
			}
			// 得到學生編號
			String stuId = (String) sm.getValueAt(rowNum, 0);
			//System.out.println("id= "+stuId);
			// 創建一個sql
			String sql = "delete from production.brands where brand_id=?";
			// 這邊一定要強轉string stuId進入String[]我們class吃的格式
			String[] paras = {stuId};
			StuModel2 temp = new StuModel2();
			temp.updStu(sql, paras);
//			try {
//				System.out.print("here~");
//				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//				ct = DriverManager.getConnection
//				("jdbc:sqlserver://LAPTOP-AVJCSDFI;DatabaseName=db_rider;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
//				ps = ct.prepareStatement("delete from production.brands where brand_name=?");
//				ps.setString(1, stuId);
//				ps.executeUpdate();			
//				// 關閉 子項目對話框
//				this.dispose();
//			}catch(Exception et) {
//				et.printStackTrace();
//			}finally {
//				try {
//		    		// 強化
//		    		if(ct!=null) { ct.close();
//		    		}if(rs!=null) { rs.close();
//		    		}if(ps!=null) { ps.close();}
//				} catch (SQLException ex) {
//					// TODO Auto-generated catch block
//					ex.printStackTrace();
//				}
//			}
			
			// 最後，更新到數據模型
			sm = new StuModel2();
			String[] paras2 = {"1"};
			sm.queryStu("select * from production.brands where 1=?", paras2);
			// 更新JTable
			table.setModel(sm);
		}
	}

}
