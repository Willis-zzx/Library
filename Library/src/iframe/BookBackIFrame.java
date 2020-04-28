package iframe;

/*
 * ͼ��黹����
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.Back;
import util.BookType;
import util.MapPz;
import util.MyDocument;
import zzx.DBUtil;
import zzx.LoginUI;

public class BookBackIFrame extends JInternalFrame {

	private JPanel contentPane;
    private static final long serialVersionUID = -1699483500627984711L;
    private JTable table;
    private JTextField operator;
    private JTextField todaydate;
    private JTextField fkmoney;
    private JTextField ccdays;
    private JTextField realdays;
    private JTextField borrowdays;
    private JTextField borrowDate;
    private JTextField readerISBN;
    private String[] columnNames = { "ͼ������", "ͼ��������","ͼ�����","��������","����������","����ʱ��","�黹ʱ��" };
    DefaultTableModel model = new DefaultTableModel();
    SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private String bookISBNs=null;
    private String readerISBNs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBackIFrame frame = new BookBackIFrame();
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
	public BookBackIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
        setIconifiable(true);                           // ���ô������С������������
        setClosable(true);                              // ���ô���ɹرգ���������
        setTitle("ͼ��黹����");
        setBounds(100, 50, 560, 427);
        
        final JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel.setPreferredSize(new Dimension(0, 200));
        getContentPane().add(panel, BorderLayout.NORTH);
    
        final JPanel panel_5 = new JPanel();
        final GridLayout gridLayout_1 = new GridLayout(0, 2);
        gridLayout_1.setVgap(5);
        panel_5.setLayout(gridLayout_1);
        panel_5.setPreferredSize(new Dimension(400, 20));
        panel.add(panel_5);
    
        final JLabel label_4 = new JLabel();
        label_4.setText("���߱�ţ�");
        panel_5.add(label_4);
    
        readerISBN = new JTextField();
        readerISBN.setDocument(new MyDocument(13));
        readerISBN.addKeyListener(new readerISBNListenerlostFocus());
        panel_5.add(readerISBN);
    
        final JPanel panel_4 = new JPanel();
        panel_4.setLayout(new FlowLayout());
        panel_4.setPreferredSize(new Dimension(450, 130));
        panel.add(panel_4);
    
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(450, 120));
        panel_4.add(scrollPane);
    
        table = new JTable();
        scrollPane.setViewportView(table);
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        table.addMouseListener(new TableListener());
        
    
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1);
    
        final JPanel panel_2 = new JPanel();
        final GridLayout gridLayout_2 = new GridLayout(0, 2);
        gridLayout_2.setVgap(5);
        panel_2.setLayout(gridLayout_2);
        panel_2.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel_2.setPreferredSize(new Dimension(250, 180));
        panel_1.add(panel_2);
    
        final JLabel label_11 = new JLabel();
        label_11.setText("�������ڣ�");
        panel_2.add(label_11);
    
        borrowDate = new JTextField();
        borrowDate.setEditable(false);
    
        panel_2.add(borrowDate);
    
        final JLabel label_12 = new JLabel();
        label_12.setText("�涨������");
        panel_2.add(label_12);
    
        borrowdays = new JTextField();
        borrowdays.setEditable(false);
        panel_2.add(borrowdays);
    
        final JLabel label_13 = new JLabel();
        label_13.setText("ʵ��������");
        panel_2.add(label_13);
    
        realdays = new JTextField();
        realdays.setEditable(false);
        panel_2.add(realdays);
    
        final JLabel label_14 = new JLabel();
        label_14.setText("����������");
        panel_2.add(label_14);
    
        ccdays = new JTextField();
        ccdays.setEditable(false);
        panel_2.add(ccdays);
    
        final JLabel label_15 = new JLabel();
        label_15.setText("�����");
        panel_2.add(label_15);
    
        fkmoney = new JTextField();
        fkmoney.setEditable(false);
        panel_2.add(fkmoney);
    
        final JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "ϵͳ��Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel_3.setPreferredSize(new Dimension(280, 180));
        panel_1.add(panel_3);
    
        final JPanel panel_7 = new JPanel();
        final GridLayout gridLayout_3 = new GridLayout(0, 2);
        gridLayout_3.setVgap(10);
        panel_7.setLayout(gridLayout_3);
        panel_7.setPreferredSize(new Dimension(260, 50));
        panel_3.add(panel_7);
    
        final JLabel label_10_1 = new JLabel();
        label_10_1.setText("��ǰʱ�䣺");
        panel_7.add(label_10_1);
    
        todaydate = new JTextField();
        todaydate.setEditable(false);
        todaydate.setPreferredSize(new Dimension(0, 0));
        todaydate.addActionListener(new TimeActionListener());
        todaydate.setFocusable(false);
        panel_7.add(todaydate);
    
        final JLabel label_11_1 = new JLabel();
        label_11_1.setText("����Ա��");
        panel_7.add(label_11_1);
    
        operator  =new JTextField();
        operator.setText(LoginUI.getUser().getName());
        operator.setEditable(false);
        panel_7.add(operator);
    
        final JButton buttonback = new JButton();
        buttonback.setText("ͼ��黹");
        buttonback.addActionListener(new BookBackActionListener(model));
        panel_3.add(buttonback);
    
    
    
    
        final JButton buttonExit= new JButton();
        buttonExit.setText("�˳�");
        buttonExit.addActionListener(new CloseActionListener());
        panel_3.add(buttonExit);
        setVisible(true);
	}
	public final void add() throws SQLException {
        readerISBNs=readerISBN.getText().trim();
        List<Back> list=DBUtil.selectBookBack(readerISBNs);
        /*************��ձ��***********************/
        int rowCount = model.getRowCount();
        for (int i=rowCount-1;i >= 0 ; i--) {
            model.removeRow(i);
        }
        /************************************************/
        for(int i=0;i<list.size();i++){
            Back back=list.get(i);
            String str[] = new String[7];
            str[0] =back.getBookname();
            str[1] =back.getBookISBN();
            str[2]=String.valueOf(MapPz.getMap().get(back.getTypeId()+""));
            str[3] =back.getReaderName();
            str[4] =back.getReaderISBN();
            str[5] =back.getBorrowDate();
            str[6]=back.getBackDate();
            model.addRow(str);
        }

    }
    
    class readerISBNListenerlostFocus extends KeyAdapter{
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '\n') { // �ж����ı����Ƿ�����س���
                try {
					add();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }
    class TimeActionListener implements ActionListener{
        public TimeActionListener(){
            Timer t=new Timer(1000,this);
            t.start();
        }
        public void actionPerformed(ActionEvent ae){
            todaydate.setText(myfmt.format(new java.util.Date()).toString());
        }
    }
    class TableListener extends MouseAdapter {
        public void mouseClicked(final MouseEvent e) {
            String fk="";
            String days1="";
            int selRow=table.getSelectedRow();
            List<BookType> list =DBUtil.selectBookTypeFk(table.getValueAt(selRow, 2).toString().trim());
            for(int i=0;i<list.size();i++){
                BookType booktype=list.get(i);
                fk=booktype.getFk();
                days1=booktype.getDays();
            }
            borrowDate.setText(table.getValueAt(selRow, 5).toString().trim());
            int days2,days3;
            borrowdays.setText(days1+"");
            java.sql.Timestamp stamp = java.sql.Timestamp.valueOf(table.getValueAt(selRow, 5).toString().trim());
            Calendar calendar = new GregorianCalendar();
            days2 = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.setTime(stamp);
            days2-=calendar.get(Calendar.DAY_OF_MONTH);
            realdays.setText(days2+"");
            days3=days2-Integer.parseInt(days1);
            if(days3>0){
                ccdays.setText(days3+"");
                Double zfk=Double.valueOf(fk)*days3;
                fkmoney.setText(zfk+"Ԫ");
            }
            else{
                ccdays.setText("û�г����涨����");
                fkmoney.setText("0");
            }
            bookISBNs=table.getValueAt(selRow, 1).toString().trim();
        }
    }
    class BookBackActionListener implements ActionListener{
        private final DefaultTableModel model;

        BookBackActionListener(DefaultTableModel model) {
            this.model = model;
        }
        public void actionPerformed(ActionEvent e) {
            if(readerISBNs==null){
                JOptionPane.showMessageDialog(rootPane, "��������߱�ţ�");
                return;
            }
            System.out.println(bookISBNs==null);

            if(table.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(rootPane, "��ѡ����Ҫ�黹��ͼ�飡");
                return; 
            }

        
            int i=DBUtil.UpdateBookBack(bookISBNs, readerISBNs);
            System.out.print(i);
             if(i==1){  
                int selectedRow = table.getSelectedRow();
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(rootPane, "���������ɣ�");     
            }
        }
    }
    class CloseActionListener implements ActionListener {           // ��ӹرհ�ť���¼�������
        public void actionPerformed(final ActionEvent e) {
            doDefaultCloseAction();
        }
    }
}
