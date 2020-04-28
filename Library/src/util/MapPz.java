package util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzx.DBUtil;



public class MapPz {
    private static Map<String, Item> map = new HashMap<String, Item>();

    public static Map<String, Item> getMap() throws SQLException {
        List<BookType> list = DBUtil.selectBookCategory();
        for (int i = 0; i < list.size(); i++) {
            BookType booktype = list.get(i);
            Item item = new Item();
            item.setId(booktype.getId());
            item.setName(booktype.getTypeName());
            map.put(item.getId(), item);

        }
        return map;
    }
}
