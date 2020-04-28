package iframe;

/*
 * 读者信息删除界面
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import util.CreatecdIcon;
import util.MyDocument;
import util.Reader;
import zzx.DBUtil;

public class StuDelIFrame extends JInternalFrame {

	private JPanel contentPane;
    private static final long serialVersionUID = -3858241200958502278L;
    private JTextField keepmoney;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JTable table;
    private JTextField ISBN;
    private JTextField zy;
    private JTextField tel;
    private JTextField date;
    private JTextField maxnumber;
    private JTextField bztime;
    private JTextField zjnumber;
    private JComboBox comboBox;
    private JTextField age;
    private JTextField readername;
    private JRadioButton JRadioButton1;
    private JRadioButton JRadioButton2;
    private String zj;
    private String[] columnNames = { "读者名称", "读者性别", "读者年龄", "证件号码", "会员证有效日期", "最大借书量", "电话", "押金", "证件", "职业", "读者编号", "读者办证时间" };
    String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuDelIFrame frame = new StuDelIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Object[][] getFileStates(List<Reader> list) {
        Object[][] results = new Object[list.size()][columnNames.length];
        for (int i = 0; i < list.size(); i++) {
            Reader reader = list.get(i);
            results[i][0] = reader.getName();
            String sex;
            if (reader.getSex().equals("1")) {
                sex = "男";
            } else
                sex = "女";
            results[i][1] = sex;

            results[i][2] = reader.getAge();
            results[i][3] = reader.getIdentityCard();
            results[i][4] = reader.getDate();
            results[i][5] = reader.getMaxNum();
            results[i][6] = reader.getTel();
            results[i][7] = reader.getKeepMoney();
            results[i][8] = reader.getZj();
            results[i][9] = reader.getZy();
            results[i][10] = reader.getISBN();
            results[i][11] = reader.getBztime();
        }
        return results;
    }
	/**
	 * Create the frame.
	 */
	public StuDelIFrame() {
        super();
        setIconifiable(true);
        setClosable(true);
        setTitle("读者信息修改与删除");
        setBounds(100, 50, 600, 420);

        final JPanel panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout());
        getContentPane().add(panel_1);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(0, 182));
        panel_1.add(scrollPane, BorderLayout.NORTH);

        final DefaultTableModel model = new DefaultTableModel();
        Object[][] results = getFileStates(DBUtil.selectReader());
        model.setDataVector(results, columnNames);

        table = new JTable();
        table.setModel(model);
        scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new TableListener());

        final JPanel panel_2 = new JPanel();
        final GridLayout gridLayout = new GridLayout(0, 4);
        gridLayout.setVgap(4);
        panel_2.setLayout(gridLayout);
        panel_2.setPreferredSize(new Dimension(0, 200));
        panel_1.add(panel_2, BorderLayout.SOUTH);

        final JLabel label_1 = new JLabel();
        label_1.setText("  姓    名：");
        panel_2.add(label_1);

        readername = new JTextField();
        readername.setDocument(new MyDocument(10));
        panel_2.add(readername);

        final JLabel label_2 = new JLabel();
        label_2.setText("  性    别：");
        panel_2.add(label_2);

        final JPanel panel_3 = new JPanel();
        final FlowLayout flowLayout_1 = new FlowLayout();
        flowLayout_1.setVgap(0);
        panel_3.setLayout(flowLayout_1);
        panel_2.add(panel_3);

        JRadioButton1 = new JRadioButton();
        JRadioButton1.setSelected(true);
        buttonGroup.add(JRadioButton1);
        panel_3.add(JRadioButton1);
        JRadioButton1.setText("男");

        JRadioButton2 = new JRadioButton();
        buttonGroup.add(JRadioButton2);
        panel_3.add(JRadioButton2);
        JRadioButton2.setText("女");

        final JLabel label_3 = new JLabel();
        label_3.setText("  年    龄：");
        panel_2.add(label_3);

        age = new JTextField();
        age.setDocument(new MyDocument(2));
        age.addKeyListener(new NumberListener());
        panel_2.add(age);

        final JLabel label_5 = new JLabel();
        label_5.setText("  职    业：");
        panel_2.add(label_5);

        zy = new JTextField();
        zy.setDocument(new MyDocument(30));
        panel_2.add(zy);

        final JLabel label = new JLabel();
        label.setText("  有效证件：");
        panel_2.add(label);

        comboBox = new JComboBox();

        comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8EAB\u4EFD\u8BC1", "\u519B\u4EBA\u8BC1", "\u5B66\u751F\u8BC1", "\u5DE5\u4F5C\u8BC1"}));
        panel_2.add(comboBox);

        final JLabel label_6 = new JLabel();
        label_6.setText("  证件号码：");
        panel_2.add(label_6);

        zjnumber = new JTextField();
        zjnumber.setDocument(new MyDocument(13));
        zjnumber.addKeyListener(new NumberListener());
        panel_2.add(zjnumber);

        final JLabel label_7 = new JLabel();
        label_7.setText("  办证日期：");
        panel_2.add(label_7);

        bztime = new JFormattedTextField(DateFormat.getDateInstance());

        panel_2.add(bztime);

        final JLabel label_9 = new JLabel();
        label_9.setText("  最大借书量：");
        panel_2.add(label_9);

        maxnumber = new JTextField();
        maxnumber.addKeyListener(new NumberListener());
        panel_2.add(maxnumber);

        final JLabel label_13 = new JLabel();
        label_13.setText("  会员证有效日期：");
        panel_2.add(label_13);

        date = new JFormattedTextField(DateFormat.getDateInstance());

        panel_2.add(date);

        final JLabel label_8 = new JLabel();
        label_8.setText("  电    话：");
        panel_2.add(label_8);

        tel = new JFormattedTextField();
        tel.addKeyListener(new TelListener());
        tel.setDocument(new MyDocument(11));
        panel_2.add(tel);

        final JLabel label_14 = new JLabel();
        label_14.setText("  押    金：");
        panel_2.add(label_14);

        keepmoney = new JTextField();
        keepmoney.addKeyListener(new KeepmoneyListener());
        panel_2.add(keepmoney);

        final JLabel label_4 = new JLabel();
        label_4.setText("  读者编号：");
        panel_2.add(label_4);

        ISBN = new JTextField();
        ISBN.setEditable(false);
        ISBN.setDocument(new MyDocument(13));
        panel_2.add(ISBN);

        final JPanel panel_4 = new JPanel();
        panel_4.setMaximumSize(new Dimension(0, 0));
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(4);
        panel_4.setLayout(flowLayout);
        panel_2.add(panel_4);

        final JButton button = new JButton();
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(60, 25));
        panel_4.add(button);
        button.setText("修改");
        button.addActionListener(new ModiButtonListener(model));

        final JButton buttonDel = new JButton();
        buttonDel.setPreferredSize(new Dimension(60, 25));
        panel_4.add(buttonDel);
        buttonDel.setText("删除");
        buttonDel.addActionListener(new DelButtonListener(model));
        setVisible(true);
	}
	class TableListener extends MouseAdapter {
        @Override
        public void mouseClicked(final MouseEvent e) {

            int selRow = table.getSelectedRow();
            readername.setText(table.getValueAt(selRow, 0).toString().trim());
            if (table.getValueAt(selRow, 1).toString().trim().equals("男"))
                JRadioButton1.setSelected(true);
            else
                JRadioButton2.setSelected(true);
            age.setText(table.getValueAt(selRow, 2).toString().trim());
            zjnumber.setText(table.getValueAt(selRow, 3).toString().trim());
            date.setText(table.getValueAt(selRow, 4).toString().trim());
            maxnumber.setText(table.getValueAt(selRow, 5).toString().trim());
            tel.setText(table.getValueAt(selRow, 6).toString().trim());
            keepmoney.setText(table.getValueAt(selRow, 7).toString().trim());
            comboBox.setSelectedItem(table.getValueAt(selRow, 8).toString().trim());
            zy.setText(table.getValueAt(selRow, 9).toString().trim());
            ISBN.setText(table.getValueAt(selRow, 10).toString().trim());
            bztime.setText(table.getValueAt(selRow, 11).toString().trim());

        }
    }

    final class NumberListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789" + (char) 8;
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
        }
    }

    private final class DelButtonListener implements ActionListener {
        private final DefaultTableModel model;

        private DelButtonListener(DefaultTableModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            int i = DBUtil.DelReader(ISBN.getText().trim());
            if (i == 1) {
                JOptionPane.showMessageDialog(rootPane, "删除成功");
                Object[][] results = getFileStates(DBUtil.selectReader());
                model.setDataVector(results, columnNames);
                table.setModel(model);
            }
        }
    }

    class ModiButtonListener implements ActionListener {
        private final DefaultTableModel model;

        ModiButtonListener(DefaultTableModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (readername.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
                return;
            }
            if (age.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
                return;
            }

            if (zjnumber.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "证件号码文本框不可为空");
                return;
            }
            if (keepmoney.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "押金文本框不可为空");
                return;
            }
            if (zy.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "职业文本框不可为空");
                return;
            }
            if (ISBN.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "读者条形码文本框不可为空");
                return;
            }
            if (ISBN.getText().length() != 13) {
                JOptionPane.showMessageDialog(null, "读者条形码文本框为13位");
                return;
            }
            if (bztime.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "办证时间文本框不可为空");
                return;
            }
            if (tel.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "电话号码文本框不可为空");
                return;
            }
            if (tel.getText().length() > 11 || tel.getText().length() < 0) {
                JOptionPane.showMessageDialog(null, "电话号码位数小于11位");
                return;
            }
            if (maxnumber.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
                return;
            }
            if (maxnumber.getText().length() > 2 || tel.getText().length() < 0) {
                JOptionPane.showMessageDialog(null, "最大借书量为两位数字");
                return;
            }
            String sex = "男";
            if (!JRadioButton1.isSelected()) {
                sex = "女";
            }
            //String zj = String.valueOf(comboBox.getSelectedIndex());
            if(comboBox.getSelectedItem().equals("身份证")){
            	zj="身份证";
            }else if(comboBox.getSelectedItem().equals("学生证")){
            	zj="学生证";
            }else if(comboBox.getSelectedItem().equals("军人证")){
            	zj="军人证";
            }else if(comboBox.getSelectedItem().equals("工作证")){
            	zj="工作证";
            }
            System.out.println(zj);
            int i = DBUtil.UpdateReader(id, readername.getText().trim(), sex, age.getText().trim(), zjnumber.getText().trim(),
                    Date.valueOf(date.getText().trim()), maxnumber.getText().trim(), tel.getText().trim(), Double.valueOf(keepmoney.getText().trim()), zj, zy
                            .getText().trim(), Date.valueOf(bztime.getText().trim()), ISBN.getText().trim());
            System.out.println(i);
            if (i == 1) {
                JOptionPane.showMessageDialog(rootPane, "修改成功");
                Object[][] results = getFileStates(DBUtil.selectReader());
                model.setDataVector(results, columnNames);
                table.setModel(model);
            }
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

    class KeepmoneyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String numStr = "0123456789" + (char) 8;// 只允许输入数字与退格键
            if (numStr.indexOf(e.getKeyChar()) < 0) {
                e.consume();
            }
            if (keepmoney.getText().length() > 2 || keepmoney.getText().length() < 0) {
                e.consume();
            }
        }
    }
}
