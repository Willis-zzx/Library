package iframe;

/*
 * 管理员删除界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import util.Operater;
import util.User;
import zzx.DBUtil;

public class UserDelIFrame extends JInternalFrame {

	private JPanel contentPane;
	public static int idstr;
	private static Operater userid;
    /**
     * 
     */
    private static final long serialVersionUID = -3557385683564991916L;
    private JTable table;
    private String[] str;

    private Object[][] getFileStates(List<User> list) {
        String[] str = { "用户编号", "用户姓名", "密码" };
        Object[][] users = new Object[list.size()][str.length];
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            users[i][0] = user.getId();
            users[i][1] = user.getName();
            users[i][2] = "******";
        }
        return users;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDelIFrame frame = new UserDelIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public UserDelIFrame() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("用户信息删除");
        setBounds(200, 100, 300, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Object[][] results = getFileStates(DBUtil.selectuser());
        str = new String[] { "用户编号", "用户姓名", "密码" };
        
        final JPanel panel_1 = new JPanel();
        panel_1.setBounds(30, 250, 237, 37);
        panel_1.setPreferredSize(new Dimension(0, 50));
        getContentPane().add(panel_1);
        final JButton button_2 = new JButton();
        button_2.setText("删除");
        panel_1.add(button_2);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
                int i = DBUtil.Deluser(id);
                if (i == 1) {
                    JOptionPane.showMessageDialog(rootPane, "删除成功");
                    Object[][] results = null;
					try {
						results = getFileStates(DBUtil.selectuser());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    model.setDataVector(results, str);
                }
            }
        });
        
        final JButton button_1 = new JButton();
        button_1.setText("退出");
        panel_1.add(button_1);
        button_1.addActionListener(new CloseActionListener());

        final JButton button_3=new JButton();
        button_3.setText("详情");
        panel_1.add(button_3);
        button_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 idstr = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				 new Details();
				 System.out.print(idstr);
			}
        });
        
        
        setVisible(true);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 10, 237, 110);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(237, 120));
        table = new JTable(results, str);
        TableColumn column = table.getColumn("用户姓名");
        column.setPreferredWidth(90);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        scrollPane.setViewportView(table);
	}
    class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
        @Override
        public void actionPerformed(final ActionEvent e) {
            doDefaultCloseAction();
        }
    }
    public void doDefaultCloseAction() {
        this.setVisible(false);// 我们只让该JInternalFrame隐藏，并不是真正的关闭
    }

}
