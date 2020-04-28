package iframe;

/*
 * 管理员更改密码界面
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import util.MyDocument;
import util.Operater;
import zzx.DBUtil;
import zzx.LoginUI;

public class GengGaiMiMa extends JInternalFrame {

	private JPanel contentPane;
    private static final long serialVersionUID = -1432361462868608179L;
    private JPasswordField oldPass;
    private JPasswordField newPass2;
    private JPasswordField newPass1;
    private JTextField userid;
    private Operater user = LoginUI.getUser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GengGaiMiMa frame = new GengGaiMiMa();
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
	public GengGaiMiMa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		setContentPane(contentPane);
        setTitle("更改密码");
        //setLocationRelativeTo(null); //居中显示
        setIconifiable(true); // 设置窗体可最小化－－－必须
        setClosable(true); // 设置窗体可关闭－－－必须
		this.setResizable(false);//界面大小不可更改
        
        final JLabel label_4 = new JLabel();
        label_4.setFont(new Font("", Font.PLAIN, 14));
        label_4.setForeground(Color.RED);
        label_4.setText("<html>注：每个<b>操作员</b>只能修改自己的密码。</html>");
        final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
        gridBagConstraints_10.weighty = 1.0;
        gridBagConstraints_10.gridwidth = 4;
        gridBagConstraints_10.gridx = 0;
        gridBagConstraints_10.gridy = 0;
        getContentPane().add(label_4, gridBagConstraints_10);
        
        final JLabel label_5 = new JLabel();
        label_5.setFont(new Font("", Font.PLAIN, 14));
        label_5.setText("用 户 名：");
        final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
        gridBagConstraints_11.gridy = 2;
        gridBagConstraints_11.gridx = 0;
        getContentPane().add(label_5, gridBagConstraints_11);
        
        userid = new JTextField(user.getId());
        final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
        gridBagConstraints_12.gridy = 2;
        gridBagConstraints_12.gridx = 1;
        gridBagConstraints_12.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(userid, gridBagConstraints_12);

        userid.setEditable(false);
        
        final JLabel label_1 = new JLabel();
        label_1.setFont(new Font("", Font.PLAIN, 14));
        label_1.setText("旧  密  码：");
        final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
        gridBagConstraints_2.gridy = 3;
        gridBagConstraints_2.gridx = 0;
        getContentPane().add(label_1, gridBagConstraints_2);
        
        //旧密码
        oldPass = new JPasswordField();
        oldPass.setDocument(new MyDocument(6));
        final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
        gridBagConstraints_3.weighty = 1.0;
        gridBagConstraints_3.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_3.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_3.gridwidth = 3;
        gridBagConstraints_3.gridy = 3;
        gridBagConstraints_3.gridx = 1;
        getContentPane().add(oldPass, gridBagConstraints_3);
        
        final JLabel label_2 = new JLabel();
        label_2.setFont(new Font("", Font.PLAIN, 14));
        label_2.setText("新  密  码：");
        final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
        gridBagConstraints_4.gridy = 4;
        gridBagConstraints_4.gridx = 0;
        getContentPane().add(label_2, gridBagConstraints_4);
        
        //新密码
        newPass1 = new JPasswordField();
        newPass1.setDocument(new MyDocument(6));
        newPass1.setFont(new Font("", Font.PLAIN, 14));
        final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
        gridBagConstraints_5.weighty = 1.0;
        gridBagConstraints_5.ipadx = 30;
        gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_5.gridwidth = 3;
        gridBagConstraints_5.gridy = 4;
        gridBagConstraints_5.gridx = 1;
        getContentPane().add(newPass1, gridBagConstraints_5);
        
        final JLabel label_3 = new JLabel();
        label_3.setFont(new Font("", Font.PLAIN, 14));
        label_3.setText("确认新密码：");
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.gridy = 5;
        gridBagConstraints_6.gridx = 0;
        getContentPane().add(label_3, gridBagConstraints_6);
         
        //再次确认密码
        newPass2 = new JPasswordField();
        newPass2.setDocument(new MyDocument(6));
        newPass2.setFont(new Font("", Font.PLAIN, 14));
        final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
        gridBagConstraints_7.weighty = 1.0;
        gridBagConstraints_7.ipadx = 30;
        gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_7.weightx = 1.0;
        gridBagConstraints_7.gridwidth = 3;
        gridBagConstraints_7.gridy = 5;
        gridBagConstraints_7.gridx = 1;
        getContentPane().add(newPass2, gridBagConstraints_7);
        
        //确认按钮
        final JButton button = new JButton();
        button.setText("确认");
        final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
        gridBagConstraints_8.weighty = 1.0;
        gridBagConstraints_8.anchor = GridBagConstraints.EAST;
        gridBagConstraints_8.gridy = 6;
        gridBagConstraints_8.gridx = 1;
        getContentPane().add(button, gridBagConstraints_8);
        button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idstring=LoginUI.idstring;
				String oldpwd=oldPass.getText().trim();
				String newpwd1=newPass1.getText().trim();
				String newpwd2=newPass2.getText().trim();
				String old = null;
				Connection conn= DBUtil.getConnection();
				String sql1="select * from admin where id = ?";
				try{
					PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql1);
					stmt.setString(1,idstring);
					ResultSet rs=stmt.executeQuery();
					while(rs.next()){
						old=rs.getString(2);
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				if(oldpwd.equals(old)){
					if(newpwd1.equals(newpwd2)){
						
					    String sql = "update admin set password=? where id=?";  //update语句
					    try{
					    	PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
						    stmt.setString(1, newpwd1);                //设置SQL语句第一个"?"的参数值
							stmt.setString(2, idstring);                    //设置SQL语句第二个"?"的参数值    
							stmt.executeUpdate(); 
							conn.close();
							JOptionPane.showMessageDialog(rootPane, "修改成功");
							//doDefaultCloseAction();
					    }catch(SQLException e1){
					    	e1.printStackTrace();
					    }
					}else{
						JOptionPane.showMessageDialog(rootPane, "两次密码输入不一致");
					}
					 oldPass.setText("");
					 newPass1.setText("");
					 newPass2.setText("");
				}else{
					JOptionPane.showMessageDialog(rootPane, "旧密码输入错误");
					oldPass.setText("");
				}
			}
        });
        
        //重写按钮
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                oldPass.setText(null);
                newPass1.setText(null);
                newPass2.setText(null);
            }
        });
        button_1.setText("重写");
        final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
        gridBagConstraints_9.gridwidth = 2;
        gridBagConstraints_9.weighty = 1.0;
        gridBagConstraints_9.gridy = 6;
        gridBagConstraints_9.gridx = 2;
        getContentPane().add(button_1, gridBagConstraints_9);

        setVisible(true);
	}

}
