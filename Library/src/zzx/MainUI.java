package zzx;
/*
 * ������UI
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

	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
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
		setTitle("ͼ�����ϵͳ");
		JMenuBar menubar=createMenu();// ���ô����˵����ķ���
		setJMenuBar(menubar);
		final JLabel label = new JLabel();
        label.setBounds(0, 0, 0, 0);
        label.setIcon(null); // ���屳��
        
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
	 *�����˵���
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
        	JMenu readerManagerMItem=new JMenu("������Ϣ����");
        	  readerManagerMItem.add(MenuActions.READER_ADD);
              readerManagerMItem.add(MenuActions.READER_MODIFY);

              JMenu bookTypeManageMItem = new JMenu("ͼ��������");
              bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
              bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

              JMenu menu = new JMenu("ͼ����Ϣ����");
              menu.add(MenuActions.BOOK_ADD);
              menu.add(MenuActions.BOOK_MODIFY);

              baseMenu.add(readerManagerMItem);
              baseMenu.add(bookTypeManageMItem);
              baseMenu.add(menu);
              baseMenu.addSeparator();
              baseMenu.add(MenuActions.EXIT);
        }
        JMenu borrowManageMenu = new JMenu(); // ���Ĺ���
        borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
        borrowManageMenu.add(MenuActions.BORROW); // ����
        borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
        borrowManageMenu.add(MenuActions.BOOK_SEARCH); // ����

        JMenu sysManageMenu = new JMenu(); // ϵͳά��
        sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
        JMenu userManageMItem = new JMenu("�û�����"); // �û�����
        userManageMItem.add(MenuActions.USER_ADD);
        userManageMItem.add(MenuActions.USER_MODIFY);
        sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
        sysManageMenu.add(userManageMItem);

        menuBar.add(baseMenu); // ��ӻ�������ά���˵����˵���
        menuBar.add(bookOrderMenu); // ������鶨������˵����˵���
        menuBar.add(borrowManageMenu); // ��ӽ��Ĺ���˵����˵���
        menuBar.add(sysManageMenu); // ���ϵͳά���˵����˵���
		return menuBar;
	}
}
