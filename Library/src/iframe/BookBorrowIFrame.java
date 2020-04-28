package iframe;

/*
 * 图书借阅界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import util.BookInfo;
import util.BookType;
import util.Item;
import util.MapPz;
import util.MyDocument;
import util.Operater;
import util.Reader;
import zzx.DBUtil;
import zzx.LoginUI;

public class BookBorrowIFrame extends JInternalFrame {

	private JPanel contentPane;
	 private static final long serialVersionUID = 3665183115912995743L;

	    private Operater user = LoginUI.getUser();

	    private final JTextField operator;
	    private JTextField todaydate;
	    private JTable table;
	    private JTextField price;
	    private JTextField bookType;
	    private JTextField bookName;
	    private JTextField bookISBN;
	    private JTextField keepMoney;
	    private JTextField number;
	    private JTextField readerName;
	    private JTextField readerISBN;
		private JButton buttonBorrow;
		int kucuns;

	    private String[] columnNames = { "书籍编号", "借书日期", "应还日期", "读者编号","库存" };

	    
	    
	    private Map<String, Item> map = MapPz.getMap();
	   

	    DefaultTableModel model = new DefaultTableModel();
	    SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	    /**
	     * Create the frame
	     */
	    public final void add() {
	        String str[] = new String[5];
	        str[0] = bookISBN.getText().trim();
	        str[1] = String.valueOf(myfmt.format(new java.util.Date()));
	        str[2] = DateFormat.getDateInstance().format(getBackTime());
			str[3] = readerISBN.getText().trim();
			str[4] =  String.valueOf(kucuns);
	        model.addRow(str);
	    }

	    public Date getBackTime() { // 取还书时间
	        String days = "0";
	        List<BookType> list2 = DBUtil.selectBookCategory(bookType.getText().trim());
	        for (int j = 0; j < list2.size(); j++) {
	            BookType type = list2.get(j);
	            days = type.getDays();
	        }
	        Calendar calendar = new GregorianCalendar();
	        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(days));
	        return calendar.getTime();
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBorrowIFrame frame = new BookBorrowIFrame();
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
	public BookBorrowIFrame() throws SQLException{
        super();
        System.out.println(user.getName());
        setTitle("图书借阅管理");
        setIconifiable(true); // 设置窗体可最小化－－－必须
        setClosable(true); // 设置窗体可关闭－－－必须
        setBounds(100, 50, 513, 375);

        final JPanel panel = new JPanel();
        getContentPane().add(panel);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(480, 140));
        panel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        model.setColumnIdentifiers(columnNames);

        table.setModel(model);

        final JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(0, 120));
        getContentPane().add(panel_1, BorderLayout.NORTH);

        final JSplitPane splitPane = new JSplitPane();
        panel_1.add(splitPane);

        final JPanel panel_3 = new JPanel();
        panel_3.setPreferredSize(new Dimension(240, 110));
        splitPane.setLeftComponent(panel_3);

        final JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setPreferredSize(new Dimension(230, 100));
        panel_3.add(panel_5);

        final JLabel label = new JLabel();
        label.setBounds(0, 5, 74, 20);
        label.setText("读者编号：");
        panel_5.add(label);

        readerISBN = new JTextField();
        readerISBN.setBounds(76, 5, 140, 20);
        readerISBN.setDocument(new MyDocument(13));
        readerISBN.addKeyListener(new ISBNListenerlostFocus());
        panel_5.add(readerISBN);

        final JLabel label_1 = new JLabel();
        label_1.setBounds(0, 30, 74, 20);
        label_1.setText("读者姓名：");
        panel_5.add(label_1);

        readerName = new JTextField();
        readerName.setBounds(76, 30, 140, 20);
        readerName.setEditable(false);
        panel_5.add(readerName);

        final JLabel label_2 = new JLabel();
        label_2.setBounds(0, 55, 74, 20);
        label_2.setText("可借数量：");
        panel_5.add(label_2);

        number = new JTextField();
        number.setBounds(76, 55, 140, 20);
        number.setEditable(false);
        panel_5.add(number);

        final JLabel label_4 = new JLabel();
        label_4.setBounds(0, 80, 74, 20);
        label_4.setText("押    金：");
        panel_5.add(label_4);

        keepMoney = new JTextField();
        keepMoney.setBounds(76, 80, 140, 20);
        keepMoney.setEditable(false);
        panel_5.add(keepMoney);

        final JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setPreferredSize(new Dimension(240, 100));
        splitPane.setRightComponent(panel_4);

        final JLabel label_5 = new JLabel();
        label_5.setBounds(15, 5, 65, 20);
        label_5.setText("书籍编号：");
        panel_4.add(label_5);

        bookISBN = new JTextField();
        bookISBN.setBounds(85, 5, 140, 20);
        bookISBN.setDocument(new MyDocument(13));
        bookISBN.addKeyListener(new bookISBNListenerlostFocus());
        panel_4.add(bookISBN);

        final JLabel label_6 = new JLabel();
        label_6.setBounds(15, 30, 65, 20);
        label_6.setText("书籍名称：");
        panel_4.add(label_6);

        bookName = new JTextField();
        bookName.setBounds(85, 30, 140, 20);
        bookName.setEditable(false);
        panel_4.add(bookName);

        final JLabel label_7 = new JLabel();
        label_7.setBounds(15, 55, 65, 20);
        label_7.setText("书籍类别：");
        panel_4.add(label_7);

        bookType = new JTextField();
        bookType.setBounds(85, 55, 140, 20);
        bookType.setEditable(false);
        panel_4.add(bookType);

        final JLabel label_8 = new JLabel();
        label_8.setBounds(15, 80, 65, 20);
        label_8.setText("书籍价格：");
        panel_4.add(label_8);

        price = new JTextField();
        price.setBounds(85, 80, 140, 20);
        price.setEditable(false);
        panel_4.add(price);

        final JPanel panel_2 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(3);
        panel_2.setLayout(flowLayout);
        panel_2.setPreferredSize(new Dimension(0, 70));
        getContentPane().add(panel_2, BorderLayout.SOUTH);

        final JPanel panel_7 = new JPanel();
        final GridLayout gridLayout_2 = new GridLayout(0, 2);
        gridLayout_2.setVgap(10);
        panel_7.setLayout(gridLayout_2);
        panel_7.setPreferredSize(new Dimension(280, 50));
        panel_2.add(panel_7);

        final JLabel label_9 = new JLabel();
        label_9.setText("当前时间：");
        panel_7.add(label_9);

        todaydate = new JTextField();
        todaydate.setEditable(false);
        todaydate.setPreferredSize(new Dimension(0, 0));
        todaydate.addActionListener(new TimeActionListener());
        todaydate.setFocusable(false);
        panel_7.add(todaydate);

        final JLabel label_11 = new JLabel();
        label_11.setText("操作员：");
        panel_7.add(label_11);

        operator = new JTextField(user.getName());
        operator.setEditable(false);
        panel_7.add(operator);

        final JPanel panel_8 = new JPanel();
        panel_8.setLayout(new FlowLayout());
        panel_8.setPreferredSize(new Dimension(200, 67));
        panel_2.add(panel_8);

        buttonBorrow = new JButton();
        buttonBorrow.setText("借出当前图书");
        buttonBorrow.addActionListener(new BorrowActionListener());
        buttonBorrow.setEnabled(false);
        panel_8.add(buttonBorrow);

        final JButton buttonClear = new JButton();
        buttonClear.setText("清除所有记录");
        buttonClear.addActionListener(new ClearActionListener(model));
        panel_8.add(buttonClear);

        setVisible(true);
    }
	 class bookISBNListenerlostFocus extends KeyAdapter {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
	                if (readerISBN.getText().trim().length() != 0 && bookISBN.getText().trim().length() != 0) {
	                    String ISBNs = bookISBN.getText().trim();
	                    List<BookInfo> list = DBUtil.selectBookInfo(ISBNs);
	                    for (int i = 0; i < list.size(); i++) {
	                        BookInfo book = list.get(i);
	                        bookName.setText(book.getBookname());
	                        bookType.setText(String.valueOf(map.get(book.getTypeid())));
							price.setText(String.valueOf(book.getPrice()));
							kucuns=book.getkucun();
	                    }
	                    String readerISBNs = readerISBN.getText().trim();
	                    List<Reader> list5 = DBUtil.selectReader(readerISBNs);// 此读者是否在reader表中
	                    List<BookInfo> list4 = DBUtil.selectBookInfo(ISBNs);// 此书是否在bookInfo表中
	                    if (!readerISBNs.isEmpty() && list5.isEmpty()) {
	                        JOptionPane.showMessageDialog(rootPane, "此读者编号没有注册，查询输入读者编号是否有误！");
	                        return;
	                    }
	                    if (list4.isEmpty() && !ISBNs.isEmpty()) {
	                        JOptionPane.showMessageDialog(rootPane, "本图书馆没有此书，查询输入图书编号是否有误！");
	                        return;
	                    }
	                    if (Integer.parseInt(number.getText().trim()) <= 0) {
	                        JOptionPane.showMessageDialog(rootPane, "借书量已经超过最大借书量！");
	                        return;
	                    }
	                    boolean isBorrow = DBUtil.checkBorrow(readerISBNs, ISBNs);
	                    if (isBorrow) {
	                        JOptionPane.showMessageDialog(rootPane, "该图书已经借阅，请归还后再借！");
	                        bookName.setText("");
	                        bookType.setText("");
	                        price.setText("");
	                        buttonBorrow.setEnabled(false);
	                        return;
	                    } else {
	                        buttonBorrow.setEnabled(true);
	                        add();
	                        number.setText(String.valueOf(Integer.parseInt(number.getText().trim()) - 1));
	                    }
	                } else
	                    JOptionPane.showMessageDialog(rootPane, "请输入读者条形码！");
	            }

	        }
	    }

	    class ISBNListenerlostFocus extends KeyAdapter {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车
	                String ISBNs = readerISBN.getText().trim();

	                List<Reader> list = DBUtil.selectReader_borrow(ISBNs);
	                if (list.isEmpty() && !ISBNs.isEmpty()) {
	                    JOptionPane.showMessageDialog(rootPane, "此读者编号没有注册，查询输入读者编号是否有误！");
	                }
	                for (int i = 0; i < list.size(); i++) {
	                    Reader reader = list.get(i);
	                    readerName.setText(reader.getName());
	                    number.setText(reader.getMaxNum());
	                    keepMoney.setText(reader.getKeepMoney() + "");
	                }
	            }
	        }
	    }

	    class BorrowActionListener implements ActionListener {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	            String bookISBNs = "";
	            String readerISBNs = "";
	            String operatorId = "";
	            String borrowDate = "";
	            String backDate = "";
	            int back=1;
	            boolean flag = true;
	            for (int i = 0; i < table.getRowCount(); i++) {
	                bookISBNs = (String) table.getValueAt(i, 0);
	                operatorId = user.getId();
	                borrowDate = myfmt.format(new java.util.Date());
	                backDate = myfmt.format(getBackTime());
	                readerISBNs = (String) table.getValueAt(i, 3);
	                int ret = DBUtil
	                        .InsertBookBorrow(bookISBNs, readerISBNs, operatorId, java.sql.Timestamp.valueOf(borrowDate), java.sql.Timestamp.valueOf(backDate),back);
	                
	                int a=0;
	                Connection conn= DBUtil.getConnection();
	                String sql="update bookinfo set kucun=? where ISBN=?";
	                try{
	                	PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
	    			    stmt.setInt(1, kucuns-1);                //设置SQL语句第一个"?"的参数值
	    				stmt.setString(2, bookISBNs);                    //设置SQL语句第二个"?"的参数值    
	    				stmt.executeUpdate(); 
	    				a=1;
	    				conn.close();
	                }catch (SQLException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();					
	    				}          
	                if (flag == true && ret == 1 && a==1) {
	                    flag = true;
	                } else {
	                    flag = false;
	                }
	            }
	            if (flag) {
	                JOptionPane.showMessageDialog(rootPane, "图书借阅完成！");
	                doDefaultCloseAction();
	            }
	        }
	    }

	    class ClearActionListener implements ActionListener {
	        private final DefaultTableModel model;

	        ClearActionListener(DefaultTableModel model) {
	            this.model = model;
	        }

	        @Override
	        public void actionPerformed(final ActionEvent e) {
	            System.out.println(table.getRowCount());
	            if (table.getRowCount() != 0) {
	                model.removeRow(table.getRowCount() - 1);
	                bookISBN.setText("");
	                bookType.setText("");
	                bookName.setText("");
	                price.setText("");
	                readerISBN.setText("");
	                readerName.setText("");
	                number.setText("");
	                keepMoney.setText("");
	            } else {
	                JOptionPane.showMessageDialog(rootPane, "表格中暂时没有数据，请进行借阅操作");
	            }
	        }
	    }

	    class TimeActionListener implements ActionListener {
	        public TimeActionListener() {
	            Timer t = new Timer(1000, this);
	            t.start();
	        }

	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            todaydate.setText(myfmt.format(new java.util.Date()).toString());
	        }
	    }
}
