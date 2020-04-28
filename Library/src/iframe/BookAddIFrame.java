package iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.BookType;
import util.CreatecdIcon;
import util.Item;
import util.MyDocument;
import zzx.DBUtil;



/**
 * 名称：图书添加窗体
 * 
 */
public class BookAddIFrame extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 8246510759341256066L;
    private JComboBox publisher;
    private JTextField price;
    private JFormattedTextField pubDate;
    private JTextField translator;
    private JTextField writer;
    private JTextField ISBN;
    private JTextField bookName;
    private JTextField kucun;
    private JComboBox bookType;
    private JButton buttonadd;
    private JButton buttonclose;
    private DefaultComboBoxModel bookTypeModel;

    public BookAddIFrame() throws SQLException {
        super();
        final BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        setIconifiable(true); // 设置窗体可最小化－－－必须
        setClosable(true); // 设置窗体可关闭－－－必须
        setTitle("图书信息添加"); // 设置窗体标题－－－必须
        setBounds(100, 100, 396, 260); // 设置窗体位置和大小－－－必须

        final JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 10, 5, 5));
        getContentPane().add(panel);
        panel.setLayout(null);

        final JLabel label_2 = new JLabel();
        label_2.setBounds(10, 6, 86, 30);
        label_2.setText("图书编号：");
        panel.add(label_2);

        ISBN = new JTextField("请输入13位书号", 13);
        ISBN.setBounds(101, 6, 86, 24);
        ISBN.setDocument(new MyDocument(13)); // 设置书号文本框最大输入值为13

        ISBN.setColumns(13);
        ISBN.addKeyListener(new ISBNkeyListener());
        ISBN.addFocusListener(new ISBNFocusListener());
        panel.add(ISBN);

        final JLabel label = new JLabel();
        label.setBounds(192, 6, 86, 30);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("类别：");
        panel.add(label);

        bookType = new JComboBox();
        bookType.setBounds(283, 6, 86, 30);
        bookTypeModel = (DefaultComboBoxModel) bookType.getModel();

        // 从数据库中取出图书类别
        List<BookType> list = DBUtil.selectBookCategory();
        for (int i = 0; i < list.size(); i++) {
            BookType booktype = list.get(i);
            Item item = new Item();
            item.setId(booktype.getId());
            item.setName(booktype.getTypeName());
            bookTypeModel.addElement(item);
        }
        panel.add(bookType);

        final JLabel label_1 = new JLabel();
        label_1.setBounds(10, 40, 86, 30);
        label_1.setText("书名：");
        panel.add(label_1);

        bookName = new JTextField();
        bookName.setBounds(101, 40, 86, 24);
        panel.add(bookName);

        final JLabel label_3 = new JLabel();
        label_3.setBounds(192, 40, 86, 30);
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setText("作者：");
        panel.add(label_3);

        writer = new JTextField();
        writer.setBounds(283, 40, 86, 30);
        writer.setDocument(new MyDocument(10));
        panel.add(writer);

        final JLabel label_2_1 = new JLabel();
        label_2_1.setBounds(10, 74, 86, 30);
        label_2_1.setText("出版社：");
        panel.add(label_2_1);

        publisher = new JComboBox();
        publisher.setBounds(101, 74, 86, 30);
        String[] array = new String[] { "***出版社", "**信息出版社", "**大型出版社", "***小型出版社" };
        publisher.setModel(new DefaultComboBoxModel(array));
        panel.add(publisher);

        final JLabel label_4 = new JLabel();
        label_4.setBounds(192, 74, 86, 30);
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setText("译者：");
        panel.add(label_4);

        translator = new JTextField();
        translator.setBounds(283, 74, 86, 30);
        translator.setDocument(new MyDocument(10));
        panel.add(translator);

        final JLabel label_1_1 = new JLabel();
        label_1_1.setBounds(10, 108, 86, 30);
        label_1_1.setText("出版日期：");
        panel.add(label_1_1);

        pubDate = new JFormattedTextField(DateFormat.getDateInstance());
        pubDate.setBounds(101, 108, 86, 30);
        pubDate.setValue(new java.util.Date());
        panel.add(pubDate);
        final JLabel label_3_1 = new JLabel();
        label_3_1.setBounds(192, 108, 86, 30);
        label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_3_1.setText("单价：");
        panel.add(label_3_1);
        price = new JTextField();
        price.setBounds(283, 108, 86, 30);
        price.setDocument(new MyDocument(5));
        price.addKeyListener(new NumberListener());
        panel.add(price);
        
        final JLabel label_4_1=new JLabel();
        label_4_1.setBounds(10,142,86,30);
        label_4_1.setText("库存：");
        panel.add(label_4_1);
        kucun=new JTextField();
        kucun.setBounds(101,142,86,30);
        kucun.setDocument(new MyDocument(10));
        kucun.addKeyListener(new NumberListener());
        panel.add(kucun);
        
        

        final JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel_1.setLayout(flowLayout);

        buttonadd = new JButton();
        buttonadd.addActionListener(new addBookActionListener());
        buttonadd.setText("添加");
        panel_1.add(buttonadd);

        buttonclose = new JButton();
        buttonclose.addActionListener(new CloseActionListener());
        buttonclose.setText("关闭");
        panel_1.add(buttonclose);

        final JLabel label_5 = new JLabel();


        setVisible(true); // 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
    }

    class ISBNFocusListener extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            if (!DBUtil.selectBookInfo(ISBN.getText().trim()).isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "添加书号重复！");
                return;
            }
        }
    }

    class ISBNkeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 13) {
                buttonadd.doClick();
            }

        }
    }

    class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
        @Override
        public void actionPerformed(final ActionEvent e) {
            doDefaultCloseAction();
        }
    }

    class addBookActionListener implements ActionListener { // 添加按钮的单击事件监听器
        @Override
        public void actionPerformed(final ActionEvent e) {
            // 订书业务

            if (ISBN.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "书号文本框不可以为空");
                return;
            }
            if (ISBN.getText().length() != 13) {
                JOptionPane.showMessageDialog(rootPane, "书号文本框输入位数为13位");
                return;
            }
            if (bookName.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "图书名称文本框不可以为空");
                return;
            }
            if (writer.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "作者文本框不可以为空");
                return;
            }
            if (pubDate.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "出版日期文本框不可以为空");
                return;
            }
            if (price.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "单价文本框不可以为空");
                return;
            }
            if(kucun.getText().length()==0){
            	JOptionPane.showMessageDialog(rootPane, "库存文本框不可以为空");
                return;
            }

            String ISBNs = ISBN.getText().trim();

            // 分类
            Object selectedItem = bookType.getSelectedItem();
            if (selectedItem == null)
                return;
            Item item = (Item) selectedItem;
            String bookTypes = item.getId();

            String translators = translator.getText().trim();
            String bookNames = bookName.getText().trim();
            String writers = writer.getText().trim();
            String publishers = (String) publisher.getSelectedItem();
            String pubDates = pubDate.getText().trim();
            String prices = price.getText().trim();
            String kucuns=kucun.getText().trim();
            int ku=Integer.parseInt(kucuns);
            int i = DBUtil.Insertbook(ISBNs, bookTypes, bookNames, writers, translators, publishers, pubDates, Double.parseDouble(prices),ku);
            if (i == 1) {

                JOptionPane.showMessageDialog(rootPane, "添加成功");
                doDefaultCloseAction();
            }
        }
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
