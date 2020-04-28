package iframe;

/*
 * 图书类别添添加界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.CreatecdIcon;
import util.MyDocument;
import zzx.DBUtil;
import javax.swing.SwingConstants;

public class BookTypeAddIFrame extends JInternalFrame {

	private JPanel contentPane;
    private static final long serialVersionUID = 8573213655082560212L;
    private JTextField bookTypeName;
    private JTextField days;
    private JTextField fakuan;
    private JButton btnsave;
    private JButton btnexit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddIFrame frame = new BookTypeAddIFrame();
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
	public BookTypeAddIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconifiable(true); // 设置窗体可最小化－－－必须
        setClosable(true);
        setTitle("图书类别添加");
        setBounds(100, 100, 545, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		label.setBounds(74, 110, 122, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u53EF\u501F\u5929\u6570\uFF1A");
		label_1.setBounds(74, 150, 99, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u7F5A\u6B3E\uFF1A");
		label_2.setBounds(74, 190, 122, 18);
		contentPane.add(label_2);
		
		bookTypeName = new JTextField();
		bookTypeName.setBounds(193, 107, 201, 24);
		contentPane.add(bookTypeName);
		bookTypeName.setColumns(10);
		
		days = new JTextField();
		days.setColumns(10);
		days.setBounds(193, 149, 201, 24);
		contentPane.add(days);
		
		fakuan = new JTextField();
		fakuan.setColumns(10);
		fakuan.setBounds(193, 187, 201, 24);
		contentPane.add(fakuan);
		
		btnsave = new JButton("\u4FDD\u5B58");
		btnsave.setBounds(107, 286, 113, 27);
		contentPane.add(btnsave);
        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (bookTypeName.getText().length() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "图书类别文本框不可为空");
                    return;
                }
                if (days.getText().length() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "可借天数文本框不可为空");
                    return;
                }
                if (fakuan.getText().length() == 0 || fakuan.getText().trim().equals("单位为角")) {
                    JOptionPane.showMessageDialog(rootPane, "罚款文本框不可为空");
                    return;
                }
                int i = DBUtil.InsertBookType(bookTypeName.getText().trim(), days.getText().trim(), Double.valueOf(fakuan.getText().trim()) / 10);
                if (i == 1) {
                    JOptionPane.showMessageDialog(rootPane, "添加成功！");
                    doDefaultCloseAction();
                }else{
                	JOptionPane.showMessageDialog(rootPane, "添加失败！");
                }
            }
        });
		
		btnexit = new JButton("\u5173\u95ED");
		btnexit.setBounds(288, 286, 113, 27);
		contentPane.add(btnexit);
		btnexit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//setVisible(false);
			}
			
		});
	        setVisible(true);
	}
    class NumberListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789." + (char) 8;
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
        }
    }

}
