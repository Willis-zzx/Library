package iframe;

/*
 * ������Ϣ��ӽ���
 * */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import zzx.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class StuAddIFrame extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtname;//����
	private JTextField txtage;//����
	private JTextField txtphone;//�绰����
	private JFormattedTextField txtmaxnum;//��������
	private JFormattedTextField txtbztime;//��֤ʱ��
	private JTextField txtzy;//ְҵ
	private JFormattedTextField date;//��Ա֤��Ч����;
	private JTextField txtidnumber;//���֤����
	private JFormattedTextField txtmoney;//Ѻ��
	private JTextField txtisbn;//����������
	private String sex="��";
	private String fileString;//��Ƭ
	private String namestring;//����
	private String sexstring;//�Ա�
	private String agestring;//����
	private String idnumberstring;//���֤
	private String datestring;//��Ա֤��Ч����
	private String maxnumstring;//��������
	private String phonestring;//�ֻ�����
	private String moneystring;//Ѻ��
	private String idcardstring;//֤������
	private String zystring;//ְҵ
	private String isbnstring;//����������
	private String bztimestring;//��֤����
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuAddIFrame frame = new StuAddIFrame();
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
	public StuAddIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("���������Ϣ���");// ���ô�����⣭��������
	    setIconifiable(true); // ���ô������С������������
	    setClosable(true); // ���ô���ɹرգ���������
	                           
		
		JLabel lblNewLabel = new JLabel("\u59D3  \u540D\uFF1A");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(30, 70, 105, 20);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5E74  \u9F84\uFF1A");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label.setBounds(30, 110, 105, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_1.setBounds(30, 190, 122, 20);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u6027  \u522B\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_2.setBounds(293, 70, 75, 20);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u6709\u6548\u8BC1\u4EF6\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_3.setBounds(30, 150, 101, 20);
		contentPane.add(label_3);
		
		txtname = new JTextField();
		txtname.setBounds(145, 69, 136, 24);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(145, 110, 136, 24);
		contentPane.add(txtage);
		txtage.addKeyListener(new NumberListener());
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(145, 190, 136, 24);
		contentPane.add(txtphone);
		txtphone.addKeyListener(new TelListener());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8EAB\u4EFD\u8BC1", "\u5DE5\u4F5C\u8BC1", "\u519B\u4EBA\u8BC1", "\u5B66\u751F\u8BC1", ""}));
		comboBox.setBounds(145, 150, 136, 24);
		contentPane.add(comboBox);
		
		JRadioButton man = new JRadioButton("\u7537");
		man.setSelected(true);
		man.setBounds(441, 67, 51, 27);
		contentPane.add(man);
		
		JRadioButton woman = new JRadioButton("\u5973");
		woman.setBounds(500, 67, 77, 27);
		contentPane.add(woman);
		
		JLabel label_4 = new JLabel("\u804C  \u4E1A\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_4.setBounds(293, 110, 75, 26);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_5.setBounds(293, 150, 101, 26);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u4F1A\u5458\u6709\u6548\u65E5\u671F\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_6.setBounds(293, 190, 140, 20);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u6700\u5927\u501F\u4E66\u6570\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_7.setBounds(30, 230, 122, 20);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("\u529E\u8BC1\u65E5\u671F\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_8.setBounds(30, 262, 122, 20);
		contentPane.add(label_8);
		
		txtmaxnum = new JFormattedTextField();
		txtmaxnum.setColumns(10);
		txtmaxnum.setBounds(145, 227, 136, 24);
		contentPane.add(txtmaxnum);
		
		
		txtbztime = new JFormattedTextField();
		txtbztime.setColumns(10);
		txtbztime.setBounds(145, 263, 136, 24);
		contentPane.add(txtbztime);
		txtbztime.addKeyListener(new DateListener());
		
		txtzy = new JTextField();
		txtzy.setColumns(10);
		txtzy.setBounds(441, 111, 136, 24);
		contentPane.add(txtzy);
		
		
		date = new JFormattedTextField(DateFormat.getDateInstance());
		java.util.Date date2 = new java.util.Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date2);
        calendar.add(Calendar.YEAR, 1);
        date2.setTime(calendar.getTimeInMillis());
        date.setValue(date2);
		date.setColumns(10);
		date.setBounds(441, 190, 136, 24);
		contentPane.add(date);
		date.addKeyListener(new DateListener());
		
		txtidnumber = new JTextField();
		txtidnumber.setColumns(10);
		txtidnumber.setBounds(441, 150, 136, 24);
		contentPane.add(txtidnumber);
		
		txtmoney = new JFormattedTextField();
		txtmoney.setColumns(10);
		txtmoney.setBounds(441, 231, 136, 24);
		contentPane.add(txtmoney);
        txtmoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String numStr = "0123456789" + (char) 8;// ֻ���������������˸��
                if (numStr.indexOf(e.getKeyChar()) < 0) {
                    e.consume();
                }
                if (txtmoney.getText().length() > 2 || txtmoney.getText().length() < 0) {
                    e.consume();
                }
            }
        });
		
		txtisbn = new JTextField();
		txtisbn.setColumns(10);
		txtisbn.setBounds(441, 263, 136, 24);
		contentPane.add(txtisbn);
		
		JLabel label_9 = new JLabel("\u62BC  \u91D1\uFF1A");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_9.setBounds(293, 230, 122, 20);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("\u8BFB\u8005\u6761\u5F62\u7801\uFF1A");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		label_10.setBounds(293, 266, 122, 20);
		contentPane.add(label_10);
		
		JLabel imgelbl = new JLabel("");
		imgelbl.setHorizontalAlignment(SwingConstants.CENTER);
		imgelbl.setBounds(601, 74, 151, 176);
		contentPane.add(imgelbl);
		
		JButton btnimage = new JButton("\u6D4F\u89C8");
		btnimage.setBounds(622, 262, 113, 27);
		contentPane.add(btnimage);
		btnimage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int width = 200;
				int height = 200;
				BufferedImage newImage;
				JFileChooser jc = new JFileChooser();
				jc.setFileFilter(new FileFilter() {
					public boolean accept(File f) { // �趨���õ��ļ��ĺ�׺��
						if (f.getName().endsWith(".jpg") || f.isDirectory() || f.getName().endsWith(".gif")
								|| f.getName().endsWith(".bmp")) {
							return true;
						}
						return false;
					}

					public String getDescription() {
						return "ͼƬ(*.jpg,*.gif,*bmp)";
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
							imgelbl.setIcon(image);

						} catch (IOException ex) {
							System.out.println(ex);
						}

					}
				}
			}

		});
		
		JButton btnsave = new JButton("\u4FDD\u5B58");
		btnsave.setBounds(255, 331, 113, 27);
		contentPane.add(btnsave);
		btnsave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sex="��";//Ĭ���Ա�Ϊ��
				String idcard = null;
				if(!man.isSelected()){
					sex="Ů";
				}
				if(comboBox.getSelectedItem().equals("���֤")){
					idcard="���֤";
				}else if(comboBox.getSelectedItem().equals("����֤")){
					idcard="����֤";
				}else if(comboBox.getSelectedItem().equals("ѧ��֤")){
					idcard="ѧ��֤";
				}else if(comboBox.getSelectedItem().equals("����֤")){
					idcard="����֤";
				}
				
				if(txtname.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "������������Ϊ��");
				}
				else if(txtage.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "�������䲻��Ϊ��");
				}
				else if(txtzy.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "ְҵ����Ϊ��");
				}
				else if(txtzy.getText().length()>20){
					JOptionPane.showMessageDialog(rootPane, "ְҵλ��Ϊ20λ");
				}
				else if(txtidnumber.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "����֤���Ų���Ϊ��");
				}
				else if(txtidnumber.getText().length()<13){
					JOptionPane.showMessageDialog(rootPane, "����֤����Ϊ13λ");
				}
				else if(txtphone.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "���ߵ绰���벻��Ϊ��");
				}
				else if(txtphone.getText().length()!=11){
					JOptionPane.showMessageDialog(rootPane, "���ߵ绰����Ϊ11λ");
				}
				else if(txtmaxnum.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "������������Ϊ��");
				}
				else if (txtmaxnum.getText().length() > 2) {
	                JOptionPane.showMessageDialog(rootPane, "��������Ϊ��λ����");
	                return;
	            }
				else if(txtmoney.getText().length()==0){
					JOptionPane.showMessageDialog(rootPane, "Ѻ�𲻿�Ϊ��");
				}
				else if(txtbztime.getText().isEmpty() || date.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "ʱ���ʽ��ʹ��\"2011-07-05\"��ʽ");
               }else if(fileString==null){
					JOptionPane.showMessageDialog(rootPane, "��ѡ����Ƭ��");
				}else{
					namestring=txtname.getText().trim();//����
					sexstring=sex.trim();//�Ա�
					agestring=txtage.getText().trim();//����
					idcardstring=idcard.trim();//֤������
					idnumberstring=txtidnumber.getText().trim();//֤������
					datestring=date.getText().trim();//��Ա֤��Ч����
					maxnumstring=txtmaxnum.getText().trim();//��������
					phonestring=txtphone.getText().trim();//�ֻ�����
					moneystring=txtmoney.getText().trim();//Ѻ��
					zystring=txtzy.getText().trim();//ְҵ
					isbnstring=txtisbn.getText().trim();//����������
					bztimestring=txtbztime.getText().trim();//��֤����
					File image = new File(fileString);
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(image);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					Connection conn = DBUtil.getConnection();
					String sqlcheck="select * from reader where ISBN = ?";
					try{
						PreparedStatement stmtcheck = (PreparedStatement) conn.prepareStatement(sqlcheck);
						stmtcheck.setString(1, isbnstring);
						ResultSet rs = stmtcheck.executeQuery();
						if(rs.next()){
							JOptionPane.showMessageDialog(rootPane, "������Ϣ�Ѵ��ڣ�����������");
						}else{
							String sql="insert into reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,ISBN,bztime,picture) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
							try{
								PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
								stmt.setString(1, namestring);//name
								stmt.setString(2, sexstring);//sex
								stmt.setString(3, agestring);//age
								stmt.setString(4, idnumberstring);//identityCard
								stmt.setString(5, datestring);//date
								stmt.setString(6, maxnumstring);//maxNum
								stmt.setString(7, phonestring);//tel
								stmt.setString(8, moneystring);//keepMoney
								stmt.setString(9, idcardstring);//zj
								stmt.setString(10, zystring);//zy
								stmt.setString(11, isbnstring);//ISBN
								stmt.setString(12, bztimestring);//bztime
								stmt.setBinaryStream(13, fis, (int) image.length());//picture
								stmt.executeUpdate();
								JOptionPane.showMessageDialog(rootPane, "����ɹ�");
								doDefaultCloseAction();
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		JButton btnexit = new JButton("\u8FD4\u56DE");
		btnexit.setBounds(421, 331, 113, 27);
		contentPane.add(btnexit);
		btnexit.addActionListener(new CloseActionListener());
		this.setVisible(true);
	}
	
	
	/*
	 *�����¼� 
	 */
    class NumberListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789" + (char) 8;
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
        }
    }
    
    class DateListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (txtbztime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "ʱ���ʽ��ʹ��\"2011-07-05\"��ʽ");
            }
        }
    }
    
    class CloseActionListener implements ActionListener { // ��ӹرհ�ť���¼�������
        @Override
        public void actionPerformed(final ActionEvent e) {
            doDefaultCloseAction();
        }
    }
    
    class TelListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789-" + (char) 8;
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
        }
    }
}
