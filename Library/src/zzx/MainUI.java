package zzx;
/*
 * 主界面UI
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;



import util.CreatecdIcon;
import util.Operater;

public class MainUI extends JFrame {

	public static Operater user;
	private JPanel contentPane;
    private static final long serialVersionUID = 8464777341466831085L;
    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
        DESKTOP_PANE.add(iframe);
    }
	/**
	 * Create the frame.
	 */
	public MainUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
		setBounds(100, 100, 829, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("图书管理系统");
		JMenuBar menubar=createMenu();// 调用创建菜单栏的方法
		setJMenuBar(menubar);
		final JLabel label = new JLabel();
        label.setBounds(0, 0, 0, 0);
        label.setIcon(null); // 窗体背景
        
        DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                label.setSize(e.getComponent().getSize());
                label.setText("<html><img width=" + size.width + " height=" + size.height + " src='" + this.getClass().getResource("/logo.jpg")
                        + "'></html>");
            }
        });
        DESKTOP_PANE.add(label, new Integer(Integer.MIN_VALUE));
        getContentPane().add(DESKTOP_PANE);
        setVisible(true);
	}
	/*
	 *创建菜单栏
	 **/
	private JMenuBar createMenu(){
		JMenuBar menuBar=new JMenuBar();
		
		JMenu bookOrderMenu=new JMenu();
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
        bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);
        
        JMenu baseMenu=new JMenu();
        baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
        {
        	JMenu readerManagerMItem=new JMenu("读者信息管理");
        	  readerManagerMItem.add(MenuActions.READER_ADD);
              readerManagerMItem.add(MenuActions.READER_MODIFY);

              JMenu bookTypeManageMItem = new JMenu("图书类别管理");
              bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
              bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

              JMenu menu = new JMenu("图书信息管理");
              menu.add(MenuActions.BOOK_ADD);
              menu.add(MenuActions.BOOK_MODIFY);

              baseMenu.add(readerManagerMItem);
              baseMenu.add(bookTypeManageMItem);
              baseMenu.add(menu);
              baseMenu.addSeparator();
              baseMenu.add(MenuActions.EXIT);
        }
        JMenu borrowManageMenu = new JMenu(); // 借阅管理
        borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
        borrowManageMenu.add(MenuActions.BORROW); // 借阅
        borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
        borrowManageMenu.add(MenuActions.BOOK_SEARCH); // 搜索

        JMenu sysManageMenu = new JMenu(); // 系统维护
        sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
        JMenu userManageMItem = new JMenu("用户管理"); // 用户管理
        userManageMItem.add(MenuActions.USER_ADD);
        userManageMItem.add(MenuActions.USER_MODIFY);
        sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
        sysManageMenu.add(userManageMItem);

        menuBar.add(baseMenu); // 添加基础数据维护菜单到菜单栏
        menuBar.add(bookOrderMenu); // 添加新书定购管理菜单到菜单栏
        menuBar.add(borrowManageMenu); // 添加借阅管理菜单到菜单栏
        menuBar.add(sysManageMenu); // 添加系统维护菜单到菜单栏
		return menuBar;
	}
}
