package util;

import java.net.URL;

import javax.swing.ImageIcon;


import zzx.MainUI;

public class CreatecdIcon {
    public static ImageIcon add(String ImageName) {
        URL IconUrl = MainUI.class.getResource("/" + ImageName);// 获得图标资源的URL
        ImageIcon icon = new ImageIcon(IconUrl);// 创建图标
        return icon;// 返回图标
    }
}
