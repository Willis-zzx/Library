package iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import util.BookInfo;
import util.BookType;
import util.CreatecdIcon;
import util.Item;
import util.MapPz;
import util.MyDocument;
import zzx.DBUtil;

/**
 * ���ƣ�ͼ���޸Ĵ���
 * 
 */
public class BookModiAndDelIFrame extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -8606902330305142771L;
    private JTable table;
    private JFormattedTextField price;
    private JFormattedTextField pubDate;
    private JTextField translator;
    private JTextField publisher;
    private JTextField writer;
    private JTextField ISBN;
    private JTextField bookName;
    private JTextField kucun;
    private JComboBox bookType;
    DefaultComboBoxModel bookTypeModel;
    private Item item;
    private String[] columnNames;

    // ȡ���ݿ���ͼ�������Ϣ��������
    private Object[][] getFileStates(List<BookInfo> list) throws SQLException {
        String[] columnNames = { "ͼ����", "ͼ�����", "ͼ������", "����", "����", "������", "��������", "�۸�" ,"���"};
        Object[][] results = new Object[list.size()][columnNames.length];

        for (int i = 0; i < list.size(); i++) {
            BookInfo bookinfo = list.get(i);
            results[i][0] = bookinfo.getISBN();
            String booktypename = String.valueOf(MapPz.getMap().get(bookinfo.getTypeid()));
            results[i][1] = booktypename;
            results[i][2] = bookinfo.getBookname();
            results[i][3] = bookinfo.getWriter();
            results[i][4] = bookinfo.getTranslator();
            results[i][5] = bookinfo.getPublisher();
            results[i][6] = bookinfo.getDate();
            results[i][7] = bookinfo.getPrice();
            results[i][8] = bookinfo.getkucun();
        }
        return results;

    }

    public BookModiAndDelIFrame() throws SQLException {
        super();
        final BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        setIconifiable(true);
        setClosable(true);
        setTitle("ͼ����Ϣ�޸�");
        setBounds(100, 50, 593, 406);

        final JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel_1.setLayout(flowLayout);

        final JButton button = new JButton();
        button.addActionListener(new addBookActionListener());
        button.setText("�޸�");
        panel_1.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                doDefaultCloseAction();
            }
        });
        button_1.setText("�ر�");
        panel_1.add(button_1);
        ImageIcon bookModiAndDelIcon = CreatecdIcon.add("bookmodify.jpg");

        final JPanel panel_2 = new JPanel();
        final BorderLayout borderLayout_1 = new BorderLayout();
        borderLayout_1.setVgap(5);
        panel_2.setLayout(borderLayout_1);
        panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
        getContentPane().add(panel_2);

        final JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane);

        Object[][] results = getFileStates(DBUtil.selectBookInfo());
        columnNames = new String[] { "ͼ����", "ͼ�����", "ͼ������", "����", "����", "������", "��������", "�۸�","���" };
        table = new JTable(results, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // ��굥������е����ݲ����¼�,������е����ݷ����ı�����
        table.addMouseListener(new TableListener());

        scrollPane.setViewportView(table);

        final JPanel bookPanel = new JPanel();
        panel_2.add(bookPanel, BorderLayout.SOUTH);
        final GridLayout gridLayout = new GridLayout(0, 6);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        bookPanel.setLayout(gridLayout);

        final JLabel label_2 = new JLabel();
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setText("��       �ţ�");
        bookPanel.add(label_2);

        ISBN = new JTextField();
        ISBN.setEditable(false);
        ISBN.setDocument(new MyDocument(13));
        bookPanel.add(ISBN);
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("��       ��");
        bookPanel.add(label);

        bookType = new JComboBox();
        bookTypeModel = (DefaultComboBoxModel) bookType.getModel();
        List<BookType> list = DBUtil.selectBookCategory();
        for (int i = 0; i < list.size(); i++) {
            BookType booktype = list.get(i);
            item = new Item();
            item.setId(booktype.getId());
            item.setName(booktype.getTypeName());
            bookTypeModel.addElement(item);

        }
        bookPanel.add(bookType);

        final JLabel label_1 = new JLabel();
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setText("��    ����");
        bookPanel.add(label_1);

        bookName = new JTextField();
        bookPanel.add(bookName);

        final JLabel label_3 = new JLabel();
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setText("��       �ߣ�");
        bookPanel.add(label_3);

        writer = new JTextField();
        bookPanel.add(writer);

        final JLabel label_2_1 = new JLabel();
        label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_2_1.setText("��  ��  �磺");
        bookPanel.add(label_2_1);

        publisher = new JTextField();
        bookPanel.add(publisher);

        final JLabel label_4 = new JLabel();
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setText("��    �ߣ�");
        bookPanel.add(label_4);

        translator = new JTextField();
        bookPanel.add(translator);

        final JLabel label_1_1 = new JLabel();
        label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1_1.setText("�� �� �� �ڣ�");
        bookPanel.add(label_1_1);

        pubDate = new JFormattedTextField(DateFormat.getDateInstance());
        pubDate.setValue(new java.util.Date());
        bookPanel.add(pubDate);

        final JLabel label_3_1 = new JLabel();
        label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_3_1.setText("��      �ۣ�");
        bookPanel.add(label_3_1);

        price = new JFormattedTextField();
        price.addKeyListener(new NumberListener());
        
        
        bookPanel.add(price);
        
        final JLabel label_4_1 = new JLabel();
        label_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_4_1.setText("\u5E93    \u5B58\uFF1A");
        bookPanel.add(label_4_1);
        
        kucun = new JTextField();
        kucun.addKeyListener(new NumberListener());
        bookPanel.add(kucun);
        
        
        setVisible(true);
    }

    class TableListener extends MouseAdapter {
        @Override
        public void mouseClicked(final MouseEvent e) {
            String ISBNs, typeids, bookNames, writers, translators, publishers, dates, prices,kucuns;
            int selRow = table.getSelectedRow();
            ISBNs = table.getValueAt(selRow, 0).toString().trim();
            typeids = table.getValueAt(selRow, 1).toString().trim();
            bookNames = table.getValueAt(selRow, 2).toString().trim();
            writers = table.getValueAt(selRow, 3).toString().trim();
            translators = table.getValueAt(selRow, 4).toString().trim();
            publishers = table.getValueAt(selRow, 5).toString().trim();
            dates = table.getValueAt(selRow, 6).toString().trim();
            prices = table.getValueAt(selRow, 7).toString().trim();
            kucuns = table.getValueAt(selRow, 8).toString().trim();

            ISBN.setText(ISBNs);

            for (int i = 0; i < bookType.getItemCount(); i++) {
                Item item = (Item) bookType.getItemAt(i);
                if (item.getName().trim().equals(typeids)) {
                    bookTypeModel.setSelectedItem(item);
                    break;
                }
            }

            bookName.setText(bookNames);
            writer.setText(writers);
            translator.setText(translators);
            publisher.setText(publishers);
            pubDate.setText(dates);
            price.setText(prices);
            kucun.setText(kucuns);
            
        }
    }

    class addBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            // �޸�ͼ����Ϣ��
            if (ISBN.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "����ı��򲻿���Ϊ�ջ����������ֲ����Դ���13��");
                return;
            }
            if (ISBN.getText().length() != 13) {
                JOptionPane.showMessageDialog(rootPane, "����ı�������λ��Ϊ13λ");
                return;
            }
            if (bookName.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "ͼ�������ı��򲻿���Ϊ��");
                return;
            }
            if (writer.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "�����ı��򲻿���Ϊ��");
                return;
            }
            if (publisher.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "�������ı��򲻿���Ϊ��");
                return;
            }
            // �����뵥�۽���������֤���룿
            if (pubDate.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "���������ı��򲻿���Ϊ��");
                return;
            }
            if (price.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "�����ı��򲻿���Ϊ��");
                return;
            }

            String ISBNs = ISBN.getText().trim();

            // ����
            Object selectedItem = bookTypeModel.getSelectedItem();
            if (selectedItem == null)
                return;
            Item item = (Item) selectedItem;
            String bookTypes = item.getId();
            System.out.println(bookTypes);

            String translators = translator.getText().trim();
            String bookNames = bookName.getText().trim();
            String writers = writer.getText().trim();
            String publishers = publisher.getText().trim();
            String pubDates = pubDate.getText().trim();
            String prices = price.getText().trim();
            String kucuns = kucun.getText().trim();

            int i = DBUtil.Updatebook(ISBNs, bookTypes, bookNames, writers, translators, publishers, Date.valueOf(pubDates), Double.parseDouble(prices),Integer.parseInt(kucuns));
            System.out.println(i);

            if (i == 1) {

                JOptionPane.showMessageDialog(rootPane, "�޸ĳɹ�");
                Object[][] results = null;
				try {
					results = getFileStates(DBUtil.selectBookInfo());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                // ע�ʹ���Ϊʹ�ñ��ģ��
                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);
                model.setDataVector(results, columnNames);

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
