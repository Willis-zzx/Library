package util;

import java.net.URL;

import javax.swing.ImageIcon;


import zzx.MainUI;

public class CreatecdIcon {
    public static ImageIcon add(String ImageName) {
        URL IconUrl = MainUI.class.getResource("/" + ImageName);// ���ͼ����Դ��URL
        ImageIcon icon = new ImageIcon(IconUrl);// ����ͼ��
        return icon;// ����ͼ��
    }
}
