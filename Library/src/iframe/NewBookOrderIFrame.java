package iframe;

/*
 * 新书预定界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.BookInfo;
import util.BookType;
import util.Item;
import util.MyDocument;
import util.Operater;
import zzx.DBUtil;
import zzx.LoginUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewBookOrderIFrame extends JInternalFrame {

	private JPanel contentPane;
	/**
     * 
     */
    private static final long serialVersionUID = -8459084306268630826L;
    private JTextField bookName;
    private JTextField zk;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox cbs;
    private JTextField price;
    private JComboBox bookType;
    private JTextField operator;
    private JTextField orderNumber;
    private JTextField ISBN;
    private JTextField kucun;
    private JFormattedTextField orderDate;
    DefaultComboBoxModel bookTypeModel;
    DefaultComboBoxModel cbsModel;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    Map<String, Item> map;
    DefaultTableModel model;
    private Operater user = LoginUI.getUser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBookOrderIFrame frame = new NewBookOrderIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public NewBookOrderIFrame() throws SQLException {
        super();
        setTitle("新书定购管理");
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 500, 320);

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(0, 240));
        getContentPane().add(panel);

        final JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "定购信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel_4.setPreferredSize(new Dimension(480, 120));
        final GridLayout gridLayout_1 = new GridLayout(0, 4);
        gridLayout_1.setVgap(8);
        panel_4.setLayout(gridLayout_1);
        panel.add(panel_4);

        final JLabel label_1 = new JLabel();
        panel_4.add(label_1);
        label_1.setText("定购日期：");
        orderDate = new JFormattedTextField(DateFormat.getDateInstance());
        panel_4.add(orderDate);
        orderDate.setValue(new java.util.Date());
        orderDate.addKeyListener(new DateListener());

        final JLabel label_4 = new JLabel();
        panel_4.add(label_4);
        label_4.setText("定购数量：");

        orderNumber = new JTextField();
        panel_4.add(orderNumber);
        orderNumber.setDocument(new MyDocument(4));
        orderNumber.addKeyListener(new NumberListener());
        final JLabel label_5 = new JLabel();
        panel_4.add(label_5);
        label_5.setText("操作员：");
        operator = new JTextField(user.getName());
        panel_4.add(operator);

        operator.setEditable(false);
        final JLabel label_9 = new JLabel();
        panel_4.add(label_9);
        label_9.setText("是否验收：");

        final JPanel panel_3 = new JPanel();
        panel_4.add(panel_3);

        radioButton1 = new JRadioButton();
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonGroup.add(radioButton1);
        panel_3.add(radioButton1);
        radioButton1.setText("是");

        radioButton2 = new JRadioButton();
        radioButton2.setSelected(true);
        buttonGroup.add(radioButton2);
        panel_3.add(radioButton2);
        radioButton2.setText("否");

        final JLabel label = new JLabel();
        panel_4.add(label);
        label.setText("折扣：");

        zk = new JTextField();
        zk.setDocument(new MyDocument(1));
        zk.addKeyListener(new NumberListener());
        zk.setText("0");
        panel_4.add(zk);

        final JLabel label_9_1 = new JLabel();
        label_9_1.setText("0为无折扣");
        panel_4.add(label_9_1);

        final JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "图书信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final GridLayout gridLayout = new GridLayout(0, 4);
        gridLayout.setVgap(5);
        panel_1.setLayout(gridLayout);
        panel_1.setPreferredSize(new Dimension(0, 100));
        getContentPane().add(panel_1, BorderLayout.NORTH);

        final JLabel label_3 = new JLabel();
        label_3.setText("书籍编号：");
        panel_1.add(label_3);

        ISBN = new JTextField();
        ISBN.setDocument(new MyDocument(13));
        ISBN.addFocusListener(new ISBNListenerlostFocus());
        panel_1.add(ISBN);

        final JLabel label_2 = new JLabel();
        label_2.setText("图书名称：");
        panel_1.add(label_2);

        bookName = new JTextField();
        panel_1.add(bookName);

        final JLabel label_6 = new JLabel();
        label_6.setText("图书类别：");
        panel_1.add(label_6);

        bookType = new JComboBox();
        bookTypeModel = (DefaultComboBoxModel) bookType.getModel();
        panel_1.add(bookType);

        final JLabel label_8 = new JLabel();
        label_8.setText("出版社：");
        panel_1.add(label_8);
        cbs = new JComboBox();
        cbsModel = (DefaultComboBoxModel) cbs.getModel();
        panel_1.add(cbs);

        final JLabel label_7 = new JLabel();
        label_7.setText("图书价格：");
        panel_1.add(label_7);

        price = new JTextField();
        price.setDocument(new MyDocument(5));
        panel_1.add(price);
        
        final JLabel label_10 = new JLabel();
        label_10.setText("库存：");
        panel_1.add(label_10);
        
        kucun = new JTextField();
        kucun.setDocument(new MyDocument(10));
        panel_1.add(kucun);
        

        map = new HashMap<String, Item>();
        // 从数据库中取出图书类别
        List<BookType> list = DBUtil.selectBookCategory();
        for (int i = 0; i < list.size(); i++) {
            BookType booktype = list.get(i);
            Item item = new Item();
            item.setId(booktype.getId());
            item.setName(booktype.getTypeName());
            bookTypeModel.addElement(item);
            map.put(item.getId(), item);

        }
        String[] array = new String[] { "***出版社", "**信息出版社", "**大型出版社", "***小型出版社" };
        cbs.setModel(new DefaultComboBoxModel(array));

        final JPanel panel_2 = new JPanel();
        panel_2.setPreferredSize(new Dimension(0, 50));
        getContentPane().add(panel_2, BorderLayout.SOUTH);

        final JButton buttonAdd = new JButton();
        buttonAdd.setText("添加");
        buttonAdd.addActionListener(new ButtonAddLisenter());
        panel_2.add(buttonAdd);

        final JButton ButtonExit = new JButton();
        ButtonExit.setText("退出");
        ButtonExit.addActionListener(new CloseActionListener());
        panel_2.add(ButtonExit);
        setVisible(true);
    }
	class ButtonAddLisenter implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (orderDate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "订书日期文本框不可为空");
                return;
            }
            if (ISBN.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "图书编号文本框不可为空");
                return;
            }
            if (orderNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "订书数量文本框不可为空");
                return;
            }
            if (operator.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "操作员文本框不可为空");
                return;
            }

            if (price.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "价格文本框不可为空");
                return;
            }
            try {
				if (!DBUtil.selectBookOrder(ISBN.getText().trim()).isEmpty()) {
				    JOptionPane.showMessageDialog(rootPane, "添加书号重复！");
				    return;
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            String checkAndAccept = "0";
            if (radioButton2.isSelected()) {
                checkAndAccept = "1";
            }
            System.out.println(checkAndAccept);
            Double zks = 1.0;
            if (Integer.parseInt(zk.getText()) > 0) {
                zks = Double.valueOf(zk.getText()) / 10;
            }

            try {
                int i = DBUtil.InsertBookOrder(ISBN.getText().trim(), orderDate.getText().toString().trim(), orderNumber.getText().trim(), operator.getText().trim(),
                        checkAndAccept, zks);
                System.out.println(i);
                if (i == 1) {
                    JOptionPane.showMessageDialog(rootPane, "添加成功！");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    class DateListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (orderDate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "时间格式请使用\"2020-07-05\"格式");
            }
        }
    }

    class NumberListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789" + (char) 8;
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
        }
    }

    class ISBNListener extends KeyAdapter {// 使用回车键进行触发事件方法
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
                String ISBNs = ISBN.getText().trim();
                List<BookInfo> list = DBUtil.selectBookInfo(ISBNs);
                System.out.println(list.isEmpty());
                if (list.isEmpty()) {//如果表格为空，说明数据库中没有书的信息，要先去添加书信息。
                    try {
						new BookAddIFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                for (int i = 0; i < list.size(); i++) {
                    BookInfo bookinfo = list.get(i);
                    bookName.setText(bookinfo.getBookname());
                    bookType.setSelectedItem(map.get(bookinfo.getTypeid()));
                    cbs.setSelectedItem(bookinfo.getPublisher());
                    price.setText(String.valueOf(bookinfo.getPrice()));

                }
            }
        }
    }

    class ISBNListenerlostFocus extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            String ISBNs = ISBN.getText().trim();
            try {
				if (!DBUtil.selectBookOrder(ISBN.getText().trim()).isEmpty()) {
				    JOptionPane.showMessageDialog(rootPane, "已经为此编号图书添加定购信息，请输入其他图书编号！");
				    ISBN.setText("");
				    bookName.setText("");
                    price.setText("");
                    kucun.setText("");
				    return;
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            List<BookInfo> list = DBUtil.selectBookInfo(ISBNs);
            if (list.isEmpty() && !ISBN.getText().isEmpty()) {
                ISBN.setText("");
                bookName.setText("");
                price.setText("");
                kucun.setText("");;
                JOptionPane.showMessageDialog(rootPane, "图书信息表中无此书号，请您首先到基础数据维护中进行图书信息添加操作");
            }
            for (int i = 0; i < list.size(); i++) {
                BookInfo bookinfo = list.get(i);
                bookName.setText(bookinfo.getBookname());
                bookType.setSelectedItem(map.get(bookinfo.getTypeid()));
                cbs.setSelectedItem(bookinfo.getPublisher());
                price.setText(String.valueOf(bookinfo.getPrice()));
                kucun.setText(String.valueOf(bookinfo.getkucun()));

            }
        }
    }

    class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
        @Override
        public void actionPerformed(final ActionEvent e) {
            doDefaultCloseAction();
        }
    }
}
