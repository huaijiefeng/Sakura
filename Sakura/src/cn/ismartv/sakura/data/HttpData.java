package cn.ismartv.sakura.data;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class HttpData {
    public ArrayList<Node> getCdn_list() {
        return cdn_list;
    }

    public void setCdn_list(ArrayList<Node> cdn_list) {
        this.cdn_list = cdn_list;
    }

    private ArrayList<Node> cdn_list;


    private String retcode;
    private String retmsg;
}
