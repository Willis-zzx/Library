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
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
    private static Map<String, JInternalFrame> frames; // 子窗体集合

    public static PasswordModiAction MODIFY_PASSWORD; // 修改密码窗体动作
    public static UserModiAction USER_MODIFY; // 修改用户资料窗体动作
    public static UserAddAction USER_ADD; // 用户添加窗体动作
    public static BookSearchAction BOOK_SEARCH; // 图书搜索窗体动作
    public static GiveBackAction GIVE_BACK; // 图书归还窗体动作
    public static BorrowAction BORROW; // 图书借阅窗体动作
    public static CheckAndAcceptNewBookAction NEWBOOK_CHECK_ACCEPT;// 修改密码动作
    public static BoodOrderAction NEWBOOK_ORDER; // 新书定购窗体动作
    public static BookTypeModiAction BOOKTYPE_MODIFY; // 图书类型修改窗体动作
    public static BookTypeAddAction BOOKTYPE_ADD; // 图书类型添加窗体动作
    public static ReaderModiAction READER_MODIFY; // 读者信息修改窗体动作
    public static ReaderAddAction READER_ADD; // 读者信息添加窗体动作
    public static BookModiAction BOOK_MODIFY; // 图书信息修改窗体动作
    public static BookAddAction BOOK_ADD; // 图书信息添加窗体动作
    public static ExitAction EXIT; // 系统退出动作

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
     * 管理员更改密码事件 
     * */
    private static class PasswordModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 4118894226961035914L;

        PasswordModiAction() {
            putValue(Action.NAME, "更改密码");
            putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
            putValue(Action.SHORT_DESCRIPTION, "更改密码");// 在“更改密码”提示中显示的文字
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("更改密码") || frames.get("更改密码").isClosed()) {
                GengGaiMiMa iframe = new GengGaiMiMa();
                frames.put("更改密码", iframe);
                MainUI.addIFame(frames.get("更改密码"));
            }
        }
    }

    /*
     * 管理员信息删除
     * */
    private static class UserModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -6901135336359749399L;

        UserModiAction() {
            super("用户信息删除", null);
            putValue(Action.LONG_DESCRIPTION, "用户信息删除");
            putValue(Action.SHORT_DESCRIPTION, "用户删除");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("用户信息修改与删除") || frames.get("用户信息删除").isClosed()) {
                UserDelIFrame iframe = null;
				try {
					iframe = new UserDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("用户信息删除", iframe);
                MainUI.addIFame(frames.get("用户信息删除"));
            }
        }
    }

    /*
     * 添加新管理员
     * */
    private static class UserAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 3565132464861051243L;

        UserAddAction() {
            super("用户信息添加", null);
            putValue(Action.LONG_DESCRIPTION, "添加新的用户");
            putValue(Action.SHORT_DESCRIPTION, "用户信息添加");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("用户信息添加") || frames.get("用户信息添加").isClosed()) {
                //UserAddIFrame iframe = new UserAddIFrame();
            	UserAddIFrame iframe = new UserAddIFrame();
                frames.put("用户信息添加", iframe);
                MainUI.addIFame(frames.get("用户信息添加"));
            }

        }
    }

    /*
     * 图书查询
     * */
    private static class BookSearchAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 9038556913706472085L;

        BookSearchAction() {
            super("图书查询", null);
            putValue(Action.LONG_DESCRIPTION, "查询入库的图书信息");
            putValue(Action.SHORT_DESCRIPTION, "图书查询");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书查询") || frames.get("图书查询").isClosed()) {
                BookSearchIFrame iframe = new BookSearchIFrame();
                frames.put("图书查询", iframe);
                MainUI.addIFame(frames.get("图书查询"));
            }
        }
    }

    /*
     * 图书归还
     * */
    private static class GiveBackAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 8637856962740003135L;

        GiveBackAction() {
            super("图书归还", null);
            putValue(Action.LONG_DESCRIPTION, "归还借阅的图书");
            putValue(Action.SHORT_DESCRIPTION, "图书归还");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书归还管理") || frames.get("图书归还管理").isClosed()) {
                BookBackIFrame iframe = new BookBackIFrame();
                frames.put("图书归还管理", iframe);
                MainUI.addIFame(frames.get("图书归还管理"));
            }
        }
    }
    /*
     * 图书借阅
     * */
    private static class BorrowAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -2422648799177854909L;

        BorrowAction() {
            super("图书借阅", null);
            putValue(Action.LONG_DESCRIPTION, "从图书馆借阅图书");
            putValue(Action.SHORT_DESCRIPTION, "图书借阅");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书借阅管理") || frames.get("图书借阅管理").isClosed()) {
                BookBorrowIFrame iframe = null;
				try {
					iframe = new BookBorrowIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("图书借阅管理", iframe);
                MainUI.addIFame(frames.get("图书借阅管理"));
            }
        }
    }

    /*
     * 图书验收
     * */
    private static class CheckAndAcceptNewBookAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -7480864882452636910L;

        CheckAndAcceptNewBookAction() {
            super("图书验收", null);
            putValue(Action.LONG_DESCRIPTION, "验收订阅的新图书");
            putValue(Action.SHORT_DESCRIPTION, "图书验收");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("图书验收") || frames.get("图书验收").isClosed()) {
                NewBookCheckIFrame iframe = null;
				try {
					iframe = new NewBookCheckIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("图书验收", iframe);
                MainUI.addIFame(frames.get("图书验收"));
            }
        }
    }

    /*
     * 新书订购
     * */
    private static class BoodOrderAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -1054053203403366137L;

        BoodOrderAction() {
            super("新书定购", null);
            putValue(Action.LONG_DESCRIPTION, "定购新的图书");
            putValue(Action.SHORT_DESCRIPTION, "新书定购");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("新书定购管理") || frames.get("新书定购管理").isClosed()) {
                NewBookOrderIFrame iframe = null;
				try {
					iframe = new NewBookOrderIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("新书定购管理", iframe);
                MainUI.addIFame(frames.get("新书定购管理"));
            }
        }
    }

    /*
     * 图书类别修改
     * */
    private static class BookTypeModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -5545406617372873314L;

        BookTypeModiAction() {
            super("图书类别修改", null);
            putValue(Action.LONG_DESCRIPTION, "修改图书的类别信息");
            putValue(Action.SHORT_DESCRIPTION, "图书类别修改");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书类别修改") || frames.get("图书类别修改").isClosed()) {
                BookTypeModiAndDelIFrame iframe = null;
				try {
					iframe = new BookTypeModiAndDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("图书类别修改", iframe);
                MainUI.addIFame(frames.get("图书类别修改"));
            }
        }
    }

    /*
     * 图书类别添加
     * */
    private static class BookTypeAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -5322756862760704000L;

        BookTypeAddAction() {
            super("图书类别添加", null);
            putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书类别");
            putValue(Action.SHORT_DESCRIPTION, "图书类别添加");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书类别添加") || frames.get("图书类别添加").isClosed()) {
                BookTypeAddIFrame iframe = new BookTypeAddIFrame();
                frames.put("图书类别添加", iframe);
                MainUI.addIFame(frames.get("图书类别添加"));
            }
        }
    }

    /*
     * 读者信息修改与删除
     * */
    private static class ReaderModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 6581188798409980760L;

        ReaderModiAction() {
            super("读者信息修改与删除", null);
            putValue(Action.LONG_DESCRIPTION, "修改和删除读者的基本信息");
            putValue(Action.SHORT_DESCRIPTION, "读者信息修改与删除");
        }

        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("读者信息修改与删除") || frames.get("读者信息修改与删除").isClosed()) {
            	StuDelIFrame iframe = new StuDelIFrame();
                frames.put("读者信息修改与删除", iframe);
                MainUI.addIFame(frames.get("读者信息修改与删除"));
            }
        }
    }

    /*
     * 读者信息添加
     * */
    private static class ReaderAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 7620574898636570861L;

        ReaderAddAction() {
            super("读者信息添加", null);
            putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的读者会员信息");
            putValue(Action.SHORT_DESCRIPTION, "读者信息添加");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("读者相关信息添加") || frames.get("读者相关信息添加").isClosed()) {
            	StuAddIFrame iframe = new StuAddIFrame();
                frames.put("读者相关信息添加", iframe);
                MainUI.addIFame(frames.get("读者相关信息添加"));
            }
        }
    }

    // 图书信息修改与删除
    private static class BookModiAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -8809934214424040443L;

        BookModiAction() {
            super("图书信息修改", null);
            putValue(Action.LONG_DESCRIPTION, "修改和删除图书信息");
            putValue(Action.SHORT_DESCRIPTION, "图书信息修改");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书信息修改") || frames.get("图书信息修改").isClosed()) {
                BookModiAndDelIFrame iframe = null;
				try {
					iframe = new BookModiAndDelIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("图书信息修改", iframe);
                MainUI.addIFame(frames.get("图书信息修改"));
            }
        }
    }

    // 图书信息添加－－－已经实现，请参照
    private static class BookAddAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1915692957437386827L;

        BookAddAction() {

            super("图书信息添加", null);
            putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
            putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
        }

        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书信息添加") || frames.get("图书信息添加").isClosed()) {
                BookAddIFrame iframe = null;
				try {
					iframe = new BookAddIFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                frames.put("图书信息添加", iframe);
                MainUI.addIFame(frames.get("图书信息添加"));
            }
        }
    }

    /*
     * 退出系统事件
     * */
    private static class ExitAction extends AbstractAction { // 退出系统动作
        /**
         * 
         */
        private static final long serialVersionUID = -8984591677736504367L;

        public ExitAction() {
            super("退出系统", null);
            putValue(Action.LONG_DESCRIPTION, "退出图书馆管理系统");
            putValue(Action.SHORT_DESCRIPTION, "退出系统");
        }

        public void actionPerformed(final ActionEvent e) {
            System.exit(0);
        }
    }

    private MenuActions() {
        super();
    }

}
