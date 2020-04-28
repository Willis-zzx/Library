package zzx;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import iframe.BookAddIFrame;
import iframe.BookBackIFrame;
import iframe.BookBorrowIFrame;
import iframe.BookModiAndDelIFrame;
import iframe.BookSearchIFrame;
import iframe.BookTypeAddIFrame;
import iframe.BookTypeModiAndDelIFrame;
import iframe.GengGaiMiMa;
import iframe.NewBookCheckIFrame;
import iframe.NewBookOrderIFrame;
import iframe.StuAddIFrame;
import iframe.StuDelIFrame;
import iframe.UserAddIFrame;
import iframe.UserDelIFrame;



/**
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
    private static Map<String, JInternalFrame> frames; // �Ӵ��弯��

    public static PasswordModiAction MODIFY_PASSWORD; // �޸����봰�嶯��
    public static UserModiAction USER_MODIFY; // �޸��û����ϴ��嶯��
    public static UserAddAction USER_ADD; // �û���Ӵ��嶯��
    public static BookSearchAction BOOK_SEARCH; // ͼ���������嶯��
    public static GiveBackAction GIVE_BACK; // ͼ��黹���嶯��
    public static BorrowAction BORROW; // ͼ����Ĵ��嶯��
    public static CheckAndAcceptNewBookAction NEWBOOK_CHECK_ACCEPT;// �޸����붯��
    public static BoodOrderAction NEWBOOK_ORDER; // ���鶨�����嶯��
    public static BookTypeModiAction BOOKTYPE_MODIFY; // ͼ�������޸Ĵ��嶯��
    public static BookTypeAddAction BOOKTYPE_ADD; // ͼ��������Ӵ��嶯��
    public static ReaderModiAction READER_MODIFY; // ������Ϣ�޸Ĵ��嶯��
    public static ReaderAddAction READER_ADD; // ������Ϣ��Ӵ��嶯��
    public static BookModiAction BOOK_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
    public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
    public static ExitAction EXIT; // ϵͳ�˳�����

    static {
        frames = new HashMap<String, JInternalFrame>();

        MODIFY_PASSWORD = new PasswordModiAction();
        USER_MODIFY = new UserModiAction();
        USER_ADD = new UserAddAction();
        BOOK_SEARCH = new BookSearchAction();
        GIVE_BACK = new GiveBackAction();
        BORROW = new BorrowAction();
        NEWBOOK_CHECK_ACCEPT = new CheckAndAcceptNewBookAction();
        NEWBOOK_ORDER = new BoodOrderAction();
        BOOKTYPE_MODIFY = new BookTypeModiAction();
        BOOKTYPE_ADD = new BookTypeAddAction();
        READER_MODIFY = new ReaderModiAction();
        READER_ADD = new ReaderAddAction();
        BOOK_MODIFY = new BookModiAction();
        BOOK_ADD = new BookAddAction();
        EXIT = new ExitAction();
    }

    /*
     * ����Ա���������¼� 
     * */
    private static class PasswordModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 4118894226961035914L;

        PasswordModiAction() {
            putValue(Action.NAME, "��������");
            putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
            putValue(Action.SHORT_DESCRIPTION, "��������");// �ڡ��������롱��ʾ����ʾ������
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("��������") || frames.get("��������").isClosed()) {
                GengGaiMiMa iframe = new GengGaiMiMa();
                frames.put("��������", iframe);
                MainUI.addIFame(frames.get("��������"));
            }
        }
    }

    /*
     * ����Ա��Ϣɾ��
     * */
    private static class UserModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -6901135336359749399L;

        UserModiAction() {
            super("�û���Ϣɾ��", null);
            putValue(Action.LONG_DESCRIPTION, "�û���Ϣɾ��");
            putValue(Action.SHORT_DESCRIPTION, "�û�ɾ��");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("�û���Ϣ�޸���ɾ��") || frames.get("�û���Ϣɾ��").isClosed()) {
                UserDelIFrame iframe = null;
				try {
					iframe = new UserDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("�û���Ϣɾ��", iframe);
                MainUI.addIFame(frames.get("�û���Ϣɾ��"));
            }
        }
    }

    /*
     * ����¹���Ա
     * */
    private static class UserAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 3565132464861051243L;

        UserAddAction() {
            super("�û���Ϣ���", null);
            putValue(Action.LONG_DESCRIPTION, "����µ��û�");
            putValue(Action.SHORT_DESCRIPTION, "�û���Ϣ���");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("�û���Ϣ���") || frames.get("�û���Ϣ���").isClosed()) {
                //UserAddIFrame iframe = new UserAddIFrame();
            	UserAddIFrame iframe = new UserAddIFrame();
                frames.put("�û���Ϣ���", iframe);
                MainUI.addIFame(frames.get("�û���Ϣ���"));
            }

        }
    }

    /*
     * ͼ���ѯ
     * */
    private static class BookSearchAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 9038556913706472085L;

        BookSearchAction() {
            super("ͼ���ѯ", null);
            putValue(Action.LONG_DESCRIPTION, "��ѯ����ͼ����Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "ͼ���ѯ");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ���ѯ") || frames.get("ͼ���ѯ").isClosed()) {
                BookSearchIFrame iframe = new BookSearchIFrame();
                frames.put("ͼ���ѯ", iframe);
                MainUI.addIFame(frames.get("ͼ���ѯ"));
            }
        }
    }

    /*
     * ͼ��黹
     * */
    private static class GiveBackAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 8637856962740003135L;

        GiveBackAction() {
            super("ͼ��黹", null);
            putValue(Action.LONG_DESCRIPTION, "�黹���ĵ�ͼ��");
            putValue(Action.SHORT_DESCRIPTION, "ͼ��黹");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ��黹����") || frames.get("ͼ��黹����").isClosed()) {
                BookBackIFrame iframe = new BookBackIFrame();
                frames.put("ͼ��黹����", iframe);
                MainUI.addIFame(frames.get("ͼ��黹����"));
            }
        }
    }
    /*
     * ͼ�����
     * */
    private static class BorrowAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -2422648799177854909L;

        BorrowAction() {
            super("ͼ�����", null);
            putValue(Action.LONG_DESCRIPTION, "��ͼ��ݽ���ͼ��");
            putValue(Action.SHORT_DESCRIPTION, "ͼ�����");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ����Ĺ���") || frames.get("ͼ����Ĺ���").isClosed()) {
                BookBorrowIFrame iframe = null;
				try {
					iframe = new BookBorrowIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("ͼ����Ĺ���", iframe);
                MainUI.addIFame(frames.get("ͼ����Ĺ���"));
            }
        }
    }

    /*
     * ͼ������
     * */
    private static class CheckAndAcceptNewBookAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -7480864882452636910L;

        CheckAndAcceptNewBookAction() {
            super("ͼ������", null);
            putValue(Action.LONG_DESCRIPTION, "���ն��ĵ���ͼ��");
            putValue(Action.SHORT_DESCRIPTION, "ͼ������");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("ͼ������") || frames.get("ͼ������").isClosed()) {
                NewBookCheckIFrame iframe = null;
				try {
					iframe = new NewBookCheckIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("ͼ������", iframe);
                MainUI.addIFame(frames.get("ͼ������"));
            }
        }
    }

    /*
     * ���鶩��
     * */
    private static class BoodOrderAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -1054053203403366137L;

        BoodOrderAction() {
            super("���鶨��", null);
            putValue(Action.LONG_DESCRIPTION, "�����µ�ͼ��");
            putValue(Action.SHORT_DESCRIPTION, "���鶨��");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("���鶨������") || frames.get("���鶨������").isClosed()) {
                NewBookOrderIFrame iframe = null;
				try {
					iframe = new NewBookOrderIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("���鶨������", iframe);
                MainUI.addIFame(frames.get("���鶨������"));
            }
        }
    }

    /*
     * ͼ������޸�
     * */
    private static class BookTypeModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -5545406617372873314L;

        BookTypeModiAction() {
            super("ͼ������޸�", null);
            putValue(Action.LONG_DESCRIPTION, "�޸�ͼ��������Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "ͼ������޸�");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ������޸�") || frames.get("ͼ������޸�").isClosed()) {
                BookTypeModiAndDelIFrame iframe = null;
				try {
					iframe = new BookTypeModiAndDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("ͼ������޸�", iframe);
                MainUI.addIFame(frames.get("ͼ������޸�"));
            }
        }
    }

    /*
     * ͼ��������
     * */
    private static class BookTypeAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -5322756862760704000L;

        BookTypeAddAction() {
            super("ͼ��������", null);
            putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ�����");
            putValue(Action.SHORT_DESCRIPTION, "ͼ��������");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ��������") || frames.get("ͼ��������").isClosed()) {
                BookTypeAddIFrame iframe = new BookTypeAddIFrame();
                frames.put("ͼ��������", iframe);
                MainUI.addIFame(frames.get("ͼ��������"));
            }
        }
    }

    /*
     * ������Ϣ�޸���ɾ��
     * */
    private static class ReaderModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 6581188798409980760L;

        ReaderModiAction() {
            super("������Ϣ�޸���ɾ��", null);
            putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ�����ߵĻ�����Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "������Ϣ�޸���ɾ��");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("������Ϣ�޸���ɾ��") || frames.get("������Ϣ�޸���ɾ��").isClosed()) {
            	StuDelIFrame iframe = new StuDelIFrame();
                frames.put("������Ϣ�޸���ɾ��", iframe);
                MainUI.addIFame(frames.get("������Ϣ�޸���ɾ��"));
            }
        }
    }

    /*
     * ������Ϣ���
     * */
    private static class ReaderAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 7620574898636570861L;

        ReaderAddAction() {
            super("������Ϣ���", null);
            putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µĶ��߻�Ա��Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("���������Ϣ���") || frames.get("���������Ϣ���").isClosed()) {
            	StuAddIFrame iframe = new StuAddIFrame();
                frames.put("���������Ϣ���", iframe);
                MainUI.addIFame(frames.get("���������Ϣ���"));
            }
        }
    }

    // ͼ����Ϣ�޸���ɾ��
    private static class BookModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -8809934214424040443L;

        BookModiAction() {
            super("ͼ����Ϣ�޸�", null);
            putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��ͼ����Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ�޸�");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ����Ϣ�޸�") || frames.get("ͼ����Ϣ�޸�").isClosed()) {
                BookModiAndDelIFrame iframe = null;
				try {
					iframe = new BookModiAndDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("ͼ����Ϣ�޸�", iframe);
                MainUI.addIFame(frames.get("ͼ����Ϣ�޸�"));
            }
        }
    }

    // ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
    private static class BookAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1915692957437386827L;

        BookAddAction() {

            super("ͼ����Ϣ���", null);
            putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ����Ϣ");
            putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ���");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("ͼ����Ϣ���") || frames.get("ͼ����Ϣ���").isClosed()) {
                BookAddIFrame iframe = null;
				try {
					iframe = new BookAddIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("ͼ����Ϣ���", iframe);
                MainUI.addIFame(frames.get("ͼ����Ϣ���"));
            }
        }
    }

    /*
     * �˳�ϵͳ�¼�
     * */
    private static class ExitAction extends AbstractAction { // �˳�ϵͳ����
        /**
         * 
         */
        private static final long serialVersionUID = -8984591677736504367L;

        public ExitAction() {
            super("�˳�ϵͳ", null);
            putValue(Action.LONG_DESCRIPTION, "�˳�ͼ��ݹ���ϵͳ");
            putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
        }

        public void actionPerformed(final ActionEvent e) {
            System.exit(0);
        }
    }

    private MenuActions() {
        super();
    }

}
