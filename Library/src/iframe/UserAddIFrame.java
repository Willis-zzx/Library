package iframe;
/*
 * 添加新管理员界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import zzx.DBUtil;
import javax.swing.JRadioButton;

public class UserAddIFrame extends JInternalFrame {

	private static final long serialVersionUID = -7153508096008641460L;
	private JButton btnsave;
	private JButton btnexit;
	private JTextField txtuser;
	private JPasswordField txtpwd1;
	private JPasswordField txtpwd2;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtaddress;
	private JTextField txtemail;
	private String fileString;
	private String idstring;
	private String pwd1string;
	private String pwd2string;
	private String namestring;
	private String sexstring;
	private String phonestring;
	private String addressstring;
	private String emailstring;
	private JRadioButton man;
	private JRadioButton women;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String sex="男";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAddIFrame frame = new UserAddIFrame();
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
	public UserAddIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("用户信息添加");
		setBounds(100, 100, 701, 397);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		btnsave = new JButton();
		btnsave.setText("保存");
		panel.add(btnsave);
		btnsave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtuser.getText().equals("") || txtpwd1.getText().equals("") || txtpwd2.getText().equals("")
						||txtphone.getText().equals("")
						|| txtaddress.getText().equals("") || txtemail.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "填写信息不完整");
				} 
				else if(txtuser.getText().length()>12){
					JOptionPane.showMessageDialog(rootPane, "用户名位数不能大于十二位");
				}
				else if(txtpwd1.getText().length()>15){
					JOptionPane.showMessageDialog(rootPane, "密码位数不能大于十五位");
				}
				/*else if(sexstring.length()>1){
					JOptionPane.showMessageDialog(rootPane, "性别位数不能大于1位");
				}*/
				else if(txtphone.getText().length()>13){
					JOptionPane.showMessageDialog(rootPane, "号码位数不能大于十一位");
				}
				else if(fileString==null){
					JOptionPane.showMessageDialog(rootPane, "请选择照片！");
				}
				else {
					if (txtpwd1.getText().trim().equals(txtpwd2.getText().trim())) {
						idstring = txtuser.getText().trim();
						pwd1string = new String(txtpwd1.getPassword());
						pwd2string = new String(txtpwd2.getPassword());
						namestring = txtname.getText().trim();
						if(women.isSelected()){
							sex="女";
						}
						sexstring=sex.trim();
						phonestring = txtphone.getText().trim();
						addressstring = txtaddress.getText().trim();
						emailstring = txtemail.getText().toString();
						File image = new File(fileString);
						FileInputStream fis = null;
						try {
							fis = new FileInputStream(image);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(rootPane, "请选择照片！");
						}
						Connection conn = DBUtil.getConnection();
						String sqlcheck = "select * from admin where id = ? ";
						try {
							PreparedStatement stmtcheck = (PreparedStatement) conn.prepareStatement(sqlcheck);
							stmtcheck.setString(1, idstring);
							ResultSet rs = stmtcheck.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(rootPane, "用户名已存在，请重新输入");
							} else {
								String sql = "insert into admin(id,password,name,sex,phone,address,email,picture) values(?,?,?,?,?,?,?,?)";
								try {
									PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
									stmt.setString(1, idstring);
									stmt.setString(2, pwd1string);
									stmt.setString(3, namestring);
									stmt.setString(4, sexstring);
									stmt.setString(5, phonestring);
									stmt.setString(6, addressstring);
									stmt.setString(7, emailstring);
									stmt.setBinaryStream(8, fis, (int) image.length());
									stmt.executeUpdate();
									JOptionPane.showMessageDialog(rootPane, "保存成功");
									doDefaultCloseAction();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "两次密码输入不一致");
					}

				}
			}

		});

		btnexit = new JButton();
		btnexit.setText("取消");
		panel.add(btnexit);
		btnexit.addActionListener(new CloseActionListener());

		setVisible(true);

		final JPanel panel_1 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(20);
		panel_1.setLayout(flowLayout);
		panel_1.setPreferredSize(new Dimension(0, 400));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setPreferredSize(new Dimension(500, 400));
		panel_1.add(panel_2);

		final JLabel label = new JLabel();
		label.setBounds(0, 0, 140, 26);
		label.setText("用户账号：");
		panel_2.add(label);

		txtuser = new JTextField();
		txtuser.setBounds(72, 0, 177, 26);
		panel_2.add(txtuser);

		final JLabel label_2 = new JLabel();
		label_2.setBounds(0, 36, 140, 26);
		label_2.setText("密    码：");
		panel_2.add(label_2);

		txtpwd1 = new JPasswordField();
		txtpwd1.setBounds(72, 36, 177, 26);
		txtpwd1.setEchoChar('*');
		panel_2.add(txtpwd1);

		final JLabel label_3 = new JLabel();
		label_3.setBounds(0, 72, 140, 26);
		label_3.setText("确认密码：");
		panel_2.add(label_3);

		txtpwd2 = new JPasswordField();
		txtpwd2.setBounds(72, 72, 177, 26);
		txtpwd2.setEchoChar('*');
		panel_2.add(txtpwd2);

		final JLabel label_4 = new JLabel();
		label_4.setBounds(0, 108, 140, 26);
		label_4.setText("姓    名：");
		panel_2.add(label_4);

		txtname = new JTextField();
		txtname.setBounds(72, 108, 177, 26);
		panel_2.add(txtname);

		final JLabel label_5 = new JLabel();
		label_5.setBounds(0, 146, 77, 26);
		label_5.setText("性    别：");
		panel_2.add(label_5);

		final JLabel label_6 = new JLabel();
		label_6.setBounds(0, 182, 140, 26);
		label_6.setText("联系电话：");
		panel_2.add(label_6);

		txtphone = new JTextField();
		txtphone.setBounds(72, 182, 177, 26);
		panel_2.add(txtphone);

		final JLabel label_7 = new JLabel();
		label_7.setBounds(0, 218, 140, 26);
		label_7.setText("地    址：");
		panel_2.add(label_7);

		txtaddress = new JTextField();
		txtaddress.setBounds(72, 218, 177, 26);
		panel_2.add(txtaddress);

		final JLabel label_8 = new JLabel();
		label_8.setBounds(0, 254, 140, 26);
		label_8.setText("地    址：");
		panel_2.add(label_8);

		txtemail = new JTextField();
		txtemail.setBounds(72, 254, 177, 26);
		panel_2.add(txtemail);

		final JLabel imagelbl = new JLabel();
		imagelbl.setBounds(303, 0, 170, 248);
		panel_2.add(imagelbl);

		JButton btnimage = new JButton("\u6D4F\u89C8");
		btnimage.setBounds(330, 254, 113, 27);
		panel_2.add(btnimage);
		
		man = new JRadioButton("\u7537");
		man.setSelected(true);
		man.setBounds(72, 146, 58, 27);
		buttonGroup.add(man);
		panel_2.add(man);
		
		women = new JRadioButton("\u5973");
		women.setBounds(136, 146, 157, 27);
		panel_2.add(women);
		buttonGroup.add(women);
		
		btnimage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int width = 200;
				int height = 200;
				BufferedImage newImage;
				JFileChooser jc = new JFileChooser();
				jc.setFileFilter(new FileFilter() {
					public boolean accept(File f) { // 设定可用的文件的后缀名
						if (f.getName().endsWith(".jpg") || f.isDirectory() || f.getName().endsWith(".gif")
								|| f.getName().endsWith(".bmp")) {
							return true;
						}
						return false;
					}

					public String getDescription() {
						return "图片(*.jpg,*.gif,*bmp)";
					}
				});
				int returnValue = jc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jc.getSelectedFile();
					if (selectedFile != null) {
						fileString = selectedFile.getAbsolutePath();
						try {
							newImage = ImageIO.read(new File(fileString));

							ImageIcon image = new ImageIcon(newImage);
							image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
							imagelbl.setIcon(image);

						} catch (IOException ex) {
							System.out.println(ex);
						}

					}
				}
			}

		});

		this.setVisible(true);
	}

	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		@Override
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
