package zzx;
/*
 * 登陆界面UI
 * */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import iframe.GengGaiMiMa;
import util.Operater;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField txtpwd;
	private static Operater user;
	public static String idstring;
	String pwdstring;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 578);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //居中显示
		this.setResizable(false);//界面大小不可更改
		this.setVisible(true);
		setTitle("图书馆管理系统");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8BFB\u8005", "\u7BA1\u7406\u5458"}));
		comboBox.setBounds(205, 400, 107, 38);
		contentPane.add(comboBox);
		
		JButton btnforget = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
		btnforget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnforget.setBounds(543, 400, 126, 38);
		contentPane.add(btnforget);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\uFF1A");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblNewLabel.setBounds(111, 175, 141, 53);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 40));
		label.setBounds(111, 277, 141, 53);
		contentPane.add(label);
		
		txtuser = new JTextField();
		txtuser.setBounds(253, 190, 416, 33);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(253, 282, 419, 34);
		contentPane.add(txtpwd);
		
		JLabel label_1 = new JLabel("\u767B\u9646");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 58));
		label_1.setBounds(115, 63, 557, 77);
		contentPane.add(label_1);
		
		JButton btnlogin = new JButton("\u767B\u9646");
		btnlogin.setBounds(201, 464, 424, 38);
		contentPane.add(btnlogin);
		btnlogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				idstring=txtuser.getText().trim();//用户名
				pwdstring=new String (txtpwd.getPassword());//密码
				Connection conn= DBUtil.getConnection();
				if(comboBox.getSelectedItem().equals("读者")){
					try{
						String sql="Select * from stu where id = ? and password=?";
						PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
						stmt.setString(1,idstring);
						stmt.setString(2,pwdstring);
						ResultSet rs=stmt.executeQuery();
						if(rs.next()){
							JOptionPane.showMessageDialog(rootPane, "登陆成功");
							doDefaultCloseAction();
						}else{
							JOptionPane.showMessageDialog(rootPane, "登陆失败");
							txtpwd.setText("");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
				else if(comboBox.getSelectedItem().equals("管理员")){
					try {
						user=DBUtil.check(idstring, pwdstring);
						if(user.getName()!=null){
							JOptionPane.showMessageDialog(rootPane, "登陆成功");
							//new GengGaiMiMa();
							new MainUI();
							doDefaultCloseAction();
						}else{
							JOptionPane.showMessageDialog(rootPane, "登陆失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
	}
    public static Operater getUser() {
        return user;
    }

    public static void setUser(Operater user) {
        LoginUI.user = user;
    }
    public void doDefaultCloseAction() {
        this.setVisible(false);// 我们只让该JInternalFrame隐藏，并不是真正的关闭
    }
}
