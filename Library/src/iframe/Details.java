package iframe;

/*
 * 删除管理员信息时可以查看详情的界面
 * */

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import zzx.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Details extends JFrame {

	private JPanel contentPane;
	private JLabel imagelbl;
	private JLabel lbluser;
	private JLabel lblname;
	private JLabel lblpwd;
	private JLabel lblsex;
	private JLabel lblphone;
	private JLabel lbladdress;
	private JLabel lblemail;
	private int id=UserDelIFrame.idstr;
	private String struser=String.valueOf(id);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Details frame = new Details();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Details() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.setResizable(false);//界面大小不可更改
		this.setVisible(true);
		//激活窗口事件  
        this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);  
		
		JLabel label = new JLabel("\u7528\u6237\u8D26\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label.setBounds(75, 40, 110, 43);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_1.setBounds(75, 80, 110, 43);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8D26\u6237\u7C7B\u522B\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_2.setBounds(44, 120, 141, 43);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u59D3\u540D\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_3.setBounds(75, 160, 110, 43);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u6027\u522B\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_4.setBounds(75, 200, 110, 43);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_5.setBounds(75, 240, 110, 43);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u5730\u5740\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_6.setBounds(75, 280, 110, 43);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_7.setBounds(75, 320, 110, 43);
		contentPane.add(label_7);
		
		imagelbl = new JLabel("");
		imagelbl.setBounds(360, 53, 235, 243);
		contentPane.add(imagelbl);
		
		//按钮：删除
		JButton btnnext = new JButton("\u5220\u9664");
		btnnext.setBounds(165, 405, 113, 27);
		contentPane.add(btnnext);
		btnnext.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 String idstring=String.valueOf(id);
				Connection conn=DBUtil.getConnection();
				String sql="delete from admin where id= ?";
				try{
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					 stmt.setString(1, idstring);
					 stmt.executeUpdate();
					 JOptionPane.showMessageDialog(rootPane, "删除成功"); 
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		
		//按钮：返回
		JButton btnexit = new JButton("\u8FD4\u56DE");
		btnexit.setBounds(386, 405, 113, 27);
		contentPane.add(btnexit);
		btnexit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new LoginUI();
				setVisible(false);
			}
		});
		
		lblpwd = new JLabel("\u5BC6\u7801");
		lblpwd.setBounds(199, 94, 200, 18);
		contentPane.add(lblpwd);
		
		lblname = new JLabel("\u59D3\u540D");
		lblname.setBounds(199, 174, 200, 18);
		contentPane.add(lblname);
		
		JLabel lblaccount = new JLabel("\u8D26\u6237\u7C7B\u522B");
		lblaccount.setBounds(199, 134, 200, 18);
		contentPane.add(lblaccount);
		lblaccount.setText("管理员");
		
		lblsex = new JLabel("\u6027\u522B");
		lblsex.setBounds(199, 214, 200, 18);
		contentPane.add(lblsex);
		
		lblphone = new JLabel("\u7535\u8BDD");
		lblphone.setBounds(199, 254, 200, 18);
		contentPane.add(lblphone);
		
		lbladdress = new JLabel("\u5730\u5740");
		lbladdress.setBounds(199, 294, 200, 18);
		contentPane.add(lbladdress);
		
		lblemail = new JLabel("\u90AE\u7BB1");
		lblemail.setBounds(199, 334, 200, 18);
		contentPane.add(lblemail);
		
		lbluser = new JLabel("\u8D26\u53F7");
		lbluser.setBounds(199, 54, 200, 18);
		contentPane.add(lbluser);
		lbluser.setText(struser);
		
		JLabel label_8 = new JLabel("\u6CE8\u518C\u65E5\u671F\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		label_8.setBounds(75, 360, 110, 43);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_9.setBounds(199, 374, 200, 18);
		contentPane.add(label_9);
		querydb(struser);
		
	}

	//重写这个方法  
    @Override  
    protected void processWindowEvent(WindowEvent e) {  
        if (e.getID() == WindowEvent.WINDOW_CLOSING)  
            return; //直接返回，阻止默认动作，阻止窗口关闭  
        super.processWindowEvent(e); //该语句会执行窗口事件的默认动作(如：隐藏)  
    }  
	public void querydb(String idstring){
    	idstring=String.valueOf(id);
		Connection conn=DBUtil.getConnection();
		String sql="select * from admin where id= ?";
		try{
			 PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			 stmt.setString(1, idstring);
			 ResultSet rs=stmt.executeQuery();
			 while(rs.next()){
				 ByteBuffer bb = ByteBuffer.allocate(2048 * 2048); 
				 byte[] buffer=new byte[1];
				 InputStream is=rs.getBinaryStream(8);
				 try{
					 while(is!=null&&is.read(buffer)>0){
						 bb.put(buffer);
					 }
				 }catch(IOException e2){
					 e2.printStackTrace();
				 }
				 ImageIcon icon=new ImageIcon(bb.array());
				 icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
				 imagelbl.setIcon(icon); 
				 lblpwd.setText("**********");
				 lblname.setText(rs.getString(3));
				 lblsex.setText(rs.getString(4));
				 lblphone.setText(rs.getString(5));
				 lbladdress.setText(rs.getString(6));
				 lblemail.setText(rs.getString(7)); 
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}
    }
}
