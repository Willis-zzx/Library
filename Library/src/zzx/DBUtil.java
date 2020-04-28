package zzx;

/*
 * MySQL数据包操作
 * */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import util.Back;
import util.BookInfo;
import util.BookType;
import util.Borrow;
import util.Operater;
import util.Order;
import util.OrderAndBookInfo;
import util.Reader;
import util.User;

public class DBUtil {
	
    public static Connection getConnection()
	{
		Connection conn=null; 
		try
		{           
           	Class.forName("org.gjt.mm.mysql.Driver");	//驱动的加载		
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8","root","123456");  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
    
    public static void delete(String table, String whereClause, Object[] whereArgs)
    {
    	try
		{
			Connection conn=getConnection();			
			PreparedStatement ps = null;			
	        ps = (PreparedStatement) conn.prepareStatement("DELETE FROM " + table
                    + (!(boolean)(whereClause.equals(""))
                    ? " WHERE " + whereClause : ""));
	        if (whereArgs != null) 
	        {
                int numArgs = whereArgs.length;
                for (int i = 0; i < numArgs; i++) 
                {
                    DBUtil.bindObjectToProgram(ps, i + 1, whereArgs[i]);
                }
	        }      
            ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }
        
    public static void insert(String table,HashMap<String, Object> mValues)
    {
    	try
		{
			Connection conn=getConnection();		
			StringBuilder sql = new StringBuilder();
	        sql.append("INSERT");	       
	        sql.append(" INTO ");
	        sql.append(table);
	       
	        StringBuilder values = new StringBuilder();
	       
	        if (mValues != null && mValues.size() > 0)
	        {
	            
	            Iterator<Entry<String, Object>> entriesIter = mValues.entrySet().iterator();
	            sql.append('(');
	            boolean needSeparator = false;
	            while (entriesIter.hasNext())
	            {
	            	if (needSeparator) 
	            	{
	                    sql.append(", ");
	                    values.append(", ");
	                }
	                needSeparator = true;
	                Map.Entry<String, Object> entry = entriesIter.next();
	                sql.append(entry.getKey());
	                values.append('?');         
	                
	            }
	            sql.append(')');  

	        sql.append(" VALUES(");
	        sql.append(values);
	        sql.append(");");
	        
	        PreparedStatement ps = null;	       
	        ps = (PreparedStatement) conn.prepareStatement(sql.toString());

	            // Bind the values
	            if (mValues != null) 
	            {
	                int size = mValues.size();
	                Iterator<Entry<String, Object>> entriesIter2 = mValues.entrySet().iterator();
	                for (int i = 0; i < size; i++)
	                {
	                    Map.Entry<String, Object> entry = entriesIter2.next();
	                    DBUtil.bindObjectToProgram(ps, i + 1, entry.getValue());
	                }         

	      
		         }
	            
	            ps.executeUpdate();
				ps.close();
				conn.close(); 
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
    }
    
    public static void update(String table, HashMap<String, Object> mValues,String whereClause, Object[] whereArgs) 
    {
       
    	Connection conn=getConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");        
        sql.append(table);
        sql.append(" SET ");

        if (mValues != null && mValues.size() > 0)
        {            
           Iterator<Entry<String, Object>> entriesIter = mValues.entrySet().iterator();
           while (entriesIter.hasNext()) 
           {
            Map.Entry<String, Object> entry = entriesIter.next();
            sql.append(entry.getKey());
            sql.append("=?");
            if (entriesIter.hasNext()) 
            {
                sql.append(", ");
            }
           }
        }

        if (!(boolean)(whereClause.equals(""))) {
            sql.append(" WHERE ");
            sql.append(whereClause);
        }

        
        PreparedStatement ps = null;	       
       
        try {
        	 ps = (PreparedStatement) conn.prepareStatement(sql.toString());

            // Bind the values
            int msize = mValues.size();
            Iterator<Entry<String, Object>> entriesIter = mValues.entrySet().iterator();
            int bindArg = 1;
            for (int i = 0; i < msize; i++) {
                Map.Entry<String, Object> entry = entriesIter.next();
                DBUtil.bindObjectToProgram(ps, bindArg, entry.getValue());
                bindArg++;
            }

            if (whereArgs != null) {
                int wsize = whereArgs.length;
                for (int i = 0; i < wsize; i++) {
                	DBUtil.bindObjectToProgram(ps, bindArg,whereArgs[i]);
                    bindArg++;
                }
            }

            // Run the program and then cleanup
            ps.executeUpdate();
			ps.close();
			conn.close();
            
           }
			catch(Exception e)
			{
				e.printStackTrace();
			}
    }
    
    public static ResultSet rawQuery(String sql, Object[] selectionArgs) throws SQLException 
    {    
    	Connection conn=getConnection();
    	PreparedStatement ps = null;	       
	    ps = (PreparedStatement) conn.prepareStatement(sql);    	
    	ResultSet rs=null;
    	if (selectionArgs != null) 
        {
            int numArgs = selectionArgs.length;
            for (int i = 0; i < numArgs; i++) 
            {
                DBUtil.bindObjectToProgram(ps, i + 1, selectionArgs[i]);
            }
        }      
        rs=ps.executeQuery();
		
     return rs;
    }
    
    public static void execSQL(String sql, Object[] selectionArgs) throws SQLException 
    {    
    	 Connection conn=getConnection();
    	 PreparedStatement ps = null;	       
	     ps = (PreparedStatement) conn.prepareStatement(sql);    	
    	
    	if (selectionArgs != null) 
        {
            int numArgs = selectionArgs.length;
            for (int i = 0; i < numArgs; i++) 
            {
                DBUtil.bindObjectToProgram(ps, i + 1, selectionArgs[i]);
            }
        }      
        ps.executeUpdate();
		ps.close();
		conn.close();
     
    }
 
    public static void bindObjectToProgram(PreparedStatement prog, int index,
            Object value) throws SQLException {
        if (value == null) {
        	prog.setString(index, value.toString());
        } else if (value instanceof Double || value instanceof Float) {
            prog.setDouble(index, ((Number)value).doubleValue());
        } else if (value instanceof Number) {
            prog.setLong(index, ((Number)value).longValue());
        } else if (value instanceof Boolean) {
            Boolean bool = (Boolean)value;
            if (bool) {
                prog.setLong(index, 1);
            } else {
                prog.setLong(index, 0);
            }
        } else if (value instanceof byte[]){
            prog.setBytes(index, (byte[]) value);
        } else {
            prog.setString(index, value.toString());
        }
    }

    /*
     * LoginUI界面管理员登陆数据库操作
     */
    public static Operater check(String id,String password) throws SQLException{
    	Operater operater =new Operater();
    	Connection conn= DBUtil.getConnection();
    	String sql="Select * from admin where id='"+ id + "' and password='" + password + "'" ;
    	PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
    	ResultSet rs=stmt.executeQuery(sql);
    	if(rs.next()){
    			operater.setId(rs.getString("id"));
    			operater.setName(rs.getString("name"));
    			operater.setPassword(rs.getString("password"));
    		}
    	return operater;
    }

    /*
     * 查询管理员信息
     * */
    public static List<User>selectuser() throws SQLException{
    	List<User> list = new ArrayList<User>();
    	Operater user = LoginUI.getUser();
    	String idstring=user.getId();
    	Connection conn= DBUtil.getConnection();
        String sql = "select *  from admin ";
        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery(sql);
        try{
        	while(rs.next()){
        		User use = new User();
                use.setId(rs.getInt(1));
                use.setName(rs.getString(3));
                use.setPassword(rs.getString(2));
                list.add(use);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        conn.close();
		return list;
    }

    /*
     * 查询读者信息
     * */
    public static List<Reader> selectReader() {
        List<Reader> list = new ArrayList<Reader>();
        String sql = "select *  from reader";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                Reader reader = new Reader();
                reader.setName(rs.getString("name"));
                reader.setSex(rs.getString("sex"));
                reader.setAge(rs.getString("age"));
                reader.setIdentityCard(rs.getString("identityCard"));
                reader.setDate(rs.getDate("date"));
                reader.setMaxNum(rs.getString("maxNum"));
                reader.setTel(rs.getString("tel"));
                reader.setKeepMoney(rs.getDouble("keepMoney"));
                reader.setZj(rs.getString("zj"));
                reader.setZy(rs.getString("zy"));
                reader.setISBN(rs.getString("ISBN"));
                reader.setBztime(rs.getDate("bztime"));
                list.add(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }
    /*
     * 删除管理员信息
     * */
    public static int Deluser(int id) {
        int i = 0;
        Connection conn= DBUtil.getConnection();
        try {
            String sql = "delete from admin where id='" + id + "'";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //conn.close();
        return i;
    }
    
    /*
     * 查询类别方法
     */
    public static List<BookType> selectBookCategory() throws SQLException{
    	List<BookType> list = new ArrayList<BookType>();
    	Connection conn= DBUtil.getConnection();
    	String sql="select * from bookType";
    	PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery(sql);
        try{
        	while(rs.next()){
        		BookType bookType=new BookType();
        		bookType.setId(rs.getString("id"));
        		bookType.setTypeName(rs.getString("typeName"));
        		bookType.setDays(rs.getString("days"));
        		bookType.setFk(rs.getString("fk"));
        		list.add(bookType);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        conn.close();
		return list;
    }

    /*
     * 
     * */
    public static List<BookInfo> selectbookserch() {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select *  from bookInfo";
        ResultSet s = DBUtil.executeQuery(sql);
        try {
            while (s.next()) {
                BookInfo bookinfo = new BookInfo();
                bookinfo.setISBN(s.getString(1));
                bookinfo.setTypeid(s.getString(2));
                bookinfo.setBookname(s.getString(3));
                bookinfo.setWriter(s.getString(4));
                bookinfo.setTranslator(s.getString(5));
                bookinfo.setPublisher(s.getString(6));
                bookinfo.setDate(s.getDate(7));
                bookinfo.setPrice(s.getDouble(8));
                list.add(bookinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }
    
    
    public static List<BookType> selectBookTypeFk(String bookType) {// 取每种书超过规定时间罚款金额
        List<BookType> list = new ArrayList<BookType>();
        String sql = "select *  from bookType where typeName='" + bookType + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                BookType type = new BookType();
                type.setFk(rs.getString("fk"));
                type.setDays(rs.getString("days"));
                list.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;

    }
    
    /*
     * 图书类别表相关操作
     */
    public static int InsertBookType(String bookTypeName, String days, Double fk) {
        int i = 0;
        try {
            String sql = "insert into bookType(typeName,days,fk) values('" + bookTypeName + "','" + days + "'," + fk + ")";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

	public static int executeUpdate(String sql) {// 执行其他操作
    	Connection conn= DBUtil.getConnection();
        try {
            if (conn == null)
                new DBUtil();
            return conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


	/*
     * 查询类别方法
     */
    public static List<BookType> selectBookCategory1() {
        List<BookType> list = new ArrayList<BookType>();
        String sql = "select *  from bookType";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                BookType bookType = new BookType();
                bookType.setId(rs.getString("id"));
                bookType.setTypeName(rs.getString("typeName"));
                bookType.setDays(rs.getString("days"));
                bookType.setFk(rs.getString("fk"));
                list.add(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;

    }

    public static List<BookType> selectBookCategory(String bookType) {
        List<BookType> list = new ArrayList<BookType>();
        String sql = "select days  from bookType where typeName='" + bookType + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                BookType type = new BookType();
                type.setDays(rs.getString("days"));
                list.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;

    }
    
    /*
     * 查询图书相关信息
     */

    public static List<BookInfo> selectBookInfo() {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select *  from bookInfo";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                BookInfo bookinfo = new BookInfo();
                bookinfo.setISBN(rs.getString("ISBN"));
                bookinfo.setTypeid(rs.getString("typeid"));
                bookinfo.setBookname(rs.getString("bookname"));
                bookinfo.setWriter(rs.getString("writer"));
                bookinfo.setTranslator(rs.getString("translator"));
                bookinfo.setPublisher(rs.getString("publisher"));
                bookinfo.setDate(rs.getDate("date"));
                bookinfo.setPrice(rs.getDouble("price"));
                bookinfo.setkucun(rs.getInt("kucun"));
                list.add(bookinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }

    public static List<BookInfo> selectBookInfo(String ISBN) {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select *  from bookInfo where ISBN='" + ISBN + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                BookInfo bookinfo = new BookInfo();
                bookinfo.setISBN(rs.getString("ISBN"));
                bookinfo.setTypeid(rs.getString("typeid"));
                bookinfo.setBookname(rs.getString("bookname"));
                bookinfo.setWriter(rs.getString("writer"));
                bookinfo.setTranslator(rs.getString("translator"));
                bookinfo.setPublisher(rs.getString("publisher"));
                bookinfo.setDate(rs.getDate("date"));
                bookinfo.setPrice(rs.getDouble("price"));
                bookinfo.setkucun(rs.getInt("kucun"));
                list.add(bookinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }


    
    public static List<Reader> selectReader_borrow(String readerISBN) {
        List<Reader> list = new ArrayList<Reader>();
        String sqlnum = "select count(*) number from borrow where readerISBN='" + readerISBN + "' and isback=1";
        ResultSet rsnum = DBUtil.executeQuery(sqlnum);
        int num = 0;
        try {
            if (rsnum.next()) {
                num = rsnum.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String sql = "select *  from reader where ISBN='" + readerISBN + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                Reader reader = new Reader();
                reader.setName(rs.getString("name"));
                reader.setSex(rs.getString("sex"));
                reader.setAge(rs.getString("age"));
                reader.setIdentityCard(rs.getString("identityCard"));
                reader.setDate(rs.getDate("date"));
                reader.setMaxNum(rs.getString("maxNum"));
                /****************** 计算可借数量 ***********************/
                if (Integer.parseInt(reader.getMaxNum()) - num > 0) {
                    reader.setMaxNum(String.valueOf(Integer.parseInt(reader.getMaxNum()) - num));
                } else {
                    reader.setMaxNum("0");
                }
                /*****************************************************/
                reader.setTel(rs.getString("tel"));
                reader.setKeepMoney(rs.getDouble("keepMoney"));
                reader.setZj(rs.getString("zj"));
                reader.setZy(rs.getString("zy"));
                reader.setISBN(rs.getString("ISBN"));
                reader.setBztime(rs.getDate("bztime"));
                list.add(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }

    public static List<Reader> selectReader(String readerISBN) {
        List<Reader> list = new ArrayList<Reader>();
        String sql = "select *  from reader where ISBN='" + readerISBN + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                Reader reader = new Reader();
                reader.setName(rs.getString("name"));
                reader.setSex(rs.getString("sex"));
                reader.setAge(rs.getString("age"));
                reader.setIdentityCard(rs.getString("identityCard"));
                reader.setDate(rs.getDate("date"));
                reader.setMaxNum(rs.getString("maxNum"));
                reader.setTel(rs.getString("tel"));
                reader.setKeepMoney(rs.getDouble("keepMoney"));
                reader.setZj(rs.getString("zj"));
                reader.setZy(rs.getString("zy"));
                reader.setISBN(rs.getString("ISBN"));
                reader.setBztime(rs.getDate("bztime"));
                list.add(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }
    
    public static int UpdateCheckBookOrder(String ISBN,int kucun) {
        int i = 0;
        int a=0;
        int b=0;
        try {
            String sql = "update order set checkAndAccept=0 where ISBN='" + ISBN + "'";
            String sql1= "update bookinfo set kucun='" + kucun + "'where ISBN='" + ISBN + "'";
            a = DBUtil.executeUpdate(sql);
            b = DBUtil.executeUpdate(sql1);
            if(a==1&&b==1){
                i=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;

    }

    /*
     * 对借阅表进行操作
     */
    public static int InsertBookBorrow(String bookISBN, String readerISBN, String operatorId, Timestamp borrowDate, Timestamp backDate,int isback) {
        int i = 0;
        try {
            String sql = "insert into borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate,isback)values('" + bookISBN + "','" + readerISBN + "','"
                    + operatorId + "','" + borrowDate + "','" + backDate+ "','" + isback + "')";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;

    }

    public static List<Borrow> selectBorrow(String readerISBN) {
        List<Borrow> list = new ArrayList<Borrow>();
        String sql = "select *  from borrow where readerISBN='" + readerISBN + "'";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(rs.getInt("id"));
                borrow.setBookISBN(rs.getString("bookISBN"));
                borrow.setReaderISBN(rs.getString("readerISBN"));
                borrow.setBorrowDate(rs.getString("borrowDate"));
                borrow.setBackDate(rs.getString("backDate"));
                borrow.setBookName(rs.getString("borrowBookName"));
                list.add(borrow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }

    /**
     * 判断图书是已经被该读者借阅
     * 
     * @return true为已经被借阅；false为没有被借阅
     */
    public static boolean checkBorrow(String readerISBNs, String ISBNs) {
        String sql = "select *  from borrow where bookISBN='" + ISBNs + "' and readerISBN='" + readerISBNs + "' and isback=1";
        ResultSet rs = executeQuery(sql);
        boolean flag = false;
        try {
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    
	/*
     * 查询还书内容，bookinfo reader borrow之间的查询
     */
    public static List<Back> selectBookBack(String readerISBN) {
        List<Back> list = new ArrayList<Back>();
        String sql = "SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM bookInfo a INNER JOIN borrow b ON a.ISBN = b.bookISBN INNER JOIN reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '"
                + readerISBN + "' and isback=1)";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                Back back = new Back();
                back.setBookISBN(rs.getString("bookISBN"));
                back.setBookname(rs.getString("bookname"));
                back.setTypeId(rs.getInt("typeId"));
                back.setOperatorId(rs.getString("operatorId"));
                back.setBorrowDate(rs.getString("borrowDate"));
                back.setBackDate(rs.getString("backDate"));
                back.setReaderName(rs.getString("readerName"));
                back.setReaderISBN(rs.getString("readerISBN"));
                back.setId(rs.getInt("id"));
                list.add(back);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }

    public static int UpdateBookBack(String bookISBN, String readerISBN, int id) {// 归还图书操作
        int i = 0;
        try {
            String sql = "update borrow set isback=0 where bookISBN='" + bookISBN + "'and readerISBN='" + readerISBN + "' and id=" + id + "";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;

    }

    public static int UpdateBookBack(String bookISBN, String readerISBN) {// 归还图书操作
        int i = 0;
        try {
            String sql = "update borrow set isback=0 where bookISBN='" + bookISBN + "'and readerISBN='" + readerISBN + "' and isback=1";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;

    }

    private static ResultSet executeQuery(String sql) {// 执行查询操作
    	Connection conn= DBUtil.getConnection();
        try {
            if (conn == null)
                new DBUtil();
            return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void close() {// 关闭连接
    	Connection conn= DBUtil.getConnection();
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    
    public static List<OrderAndBookInfo> selectBookOrder() {
        List<OrderAndBookInfo> list = new ArrayList<OrderAndBookInfo>();
        String sql = "SELECT `order`.*,bookinfo.* from `order` INNER JOIN bookinfo on `order`.ISBN=bookinfo.ISBN";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                OrderAndBookInfo order = new OrderAndBookInfo();
                order.setISBN(rs.getString(1));
                order.setOrderdate(rs.getDate(2));
                order.setNumber(rs.getString(3));
                order.setOperator(rs.getString(4));
                order.setCheckAndAccept(rs.getString(5));
                order.setZk(rs.getDouble(6));
                order.setTypeId(rs.getString(8));
                order.setBookname(rs.getString(9));
                order.setWriter(rs.getString(10));
                order.setTraslator(rs.getString(11));
                order.setPublisher(rs.getString(12));
                order.setDate(rs.getDate(13));
                order.setPrice(rs.getDouble(14));
                order.setkucun(rs.getInt(15));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }
    
    //查询订购信息
    public static List<Order> selectBookOrder(String ISBN) throws SQLException {
        List<Order> list = new ArrayList<Order>();
        Connection conn=DBUtil.getConnection();
        String sql = "SELECT * FROM order where ISBN = ?";
        try {
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		    stmt.setString(1, ISBN);
		    ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setISBN(rs.getString("ISBN"));
                order.setDate(rs.getDate("date"));
                order.setNumber(rs.getString("number"));
                order.setOperator(rs.getString("operator"));
                order.setZk("zk");
                order.setCheckAndAccept("checkAndAccept");
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return list;
    }
    
    /*
     * 对定购信息表操作
     */
    public static int InsertBookOrder(String ISBN1, String date1, String number1, String operator1, String checkAndAccept1, Double zk1) throws SQLException {
        int i = 0;
        Connection conn=DBUtil.getConnection();
        String sql="insert into order(ISBN,date,number,operator,checkAndAccept,zk) values(?,?,?,?,?,?)";
        try{
        	PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
            stmt.setString(1, ISBN1);
            stmt.setString(2, date1);
            stmt.setString(3, number1);
            stmt.setString(4, operator1);
            stmt.setString(5, checkAndAccept1);
            stmt.setDouble(6, zk1);
            stmt.executeUpdate();
            i=1;
        }catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return i;
    }
    
    /*
     * 图书信息表相关操作
     */
    /*
     * 插入图书信息方法
     */
    public static int Insertbook(String ISBN, String typeId, String bookname, String writer, String translator, String publisher, String date, Double price,int kucun)  {
        int i = 0;
        Connection conn= DBUtil.getConnection();	
        String sql="insert into bookInfo(ISBN,typeId,bookname,writer,translator,publisher,date,price,kucun) values (?,?,?,?,?,?,?,?,?)";
        try{
            java.sql.PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
            stmt.setString(1, ISBN);
            stmt.setString(2, typeId);
            stmt.setString(3, bookname);
            stmt.setString(4, writer);
            stmt.setString(5, translator);
            stmt.setString(6, publisher);
            stmt.setString(7, date);
            stmt.setDouble(8, price);
            stmt.setInt(9, kucun);
            stmt.executeUpdate();
            i=1;
        }catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();//捕获异常
        } 	
        //conn.close();
        return i;
    }

    public static int UpdatebookType(String id, String typeName, String days, String fk) {
        int i = 0;
        try {
            String sql = "update bookType set typeName='" + typeName + "',days='" + days + "',fk='" + fk + "' where id='" + id + "'";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;
    }
    public static int UpdateReader(String id, String name, String sex, String age, String identityCard, Date date, String maxNum, String tel, Double keepMoney,
            String zj, String zy, Date bztime, String ISBN) {
        int i = 0;
        try {
            String sql = "update reader set name='" + name + "',sex='" + sex + "',age='" + age + "',identityCard='" + identityCard + "',date='" + date
                    + "',maxNum='" + maxNum + "',tel='" + tel + "',keepMoney=" + keepMoney + ",zj='" + zj + "',zy='" + zy + "',bztime='" + bztime
                    + "'where ISBN='" + ISBN + "'";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;
    }

    public static int DelReader(String ISBN) {
        int i = 0;
        try {
            String sql = "delete from reader where ISBN='" + ISBN + "'";
            i = DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return i;
    }

    /*
     * 修改图书信息方法
     */
    public static int Updatebook(String ISBN, String typeId, String bookname, String writer, String translator, String publisher, Date date, Double price, int kucun) {
        int i = 0;
        Connection conn= DBUtil.getConnection();
        String sql="update bookinfo set typeId=?,bookname=?,writer=?,translator=?,publisher=?,date=?,price=?,kucun=? where ISBN=?";
        try{
            java.sql.PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
            //stmt.setString(1, ISBN);
            stmt.setString(1, typeId);
            stmt.setString(2, bookname);
            stmt.setString(3, writer);
            stmt.setString(4, translator);
            stmt.setString(5, publisher);
            stmt.setDate(6, date);
            stmt.setDouble(7, price);
            stmt.setInt(8, kucun);
            stmt.setString(9, ISBN);
            stmt.executeUpdate();
            i=1;
        }catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();//捕获异常
        } 	
        return i;
    }

    //模糊查询--查询图书信息--图书名称
    public static List<BookInfo> selectbookmohu(String bookname) {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select * from bookInfo where bookname like '%" + bookname + "%'";
        ResultSet s = DBUtil.executeQuery(sql);
        try {
            while (s.next()) {
                BookInfo bookinfo = new BookInfo();
                bookinfo.setISBN(s.getString(1));
                bookinfo.setTypeid(s.getString(2));
                bookinfo.setBookname(s.getString(3));
                bookinfo.setWriter(s.getString(4));
                bookinfo.setTranslator(s.getString(5));
                bookinfo.setPublisher(s.getString(6));
                bookinfo.setDate(s.getDate(7));
                bookinfo.setPrice(s.getDouble(8));
                list.add(bookinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    //模糊查询--查询图书信息--作者名称
    public static List<BookInfo> selectbookmohuwriter(String writer) {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select * from bookInfo where writer like '%" + writer + "%'";
        ResultSet s = DBUtil.executeQuery(sql);
        try {
            while (s.next()) {
                BookInfo bookinfo = new BookInfo();
                bookinfo.setISBN(s.getString(1));
                bookinfo.setTypeid(s.getString(2));
                bookinfo.setBookname(s.getString(3));
                bookinfo.setWriter(s.getString(4));
                bookinfo.setTranslator(s.getString(5));
                bookinfo.setPublisher(s.getString(6));
                bookinfo.setDate(s.getDate(7));
                bookinfo.setPrice(s.getDouble(8));
                list.add(bookinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


}
