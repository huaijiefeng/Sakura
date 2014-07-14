package cn.ismartv.sakura.data;

/**
 * Created by fenghb on 14-7-11.
 */
public class Node {
    private String cdnID;
    private String flag;
    private String name;
    private String route_trace;
    private String url;
    private String ping;


    private String nick;

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    private String speed;

    public String getTestFile() {
        return "http://" + getUrl() + "/cdn/speedtest.ts";
    }

    public String getCdnID() {
        return cdnID;
    }

    public void setCdnID(String cdnID) {
        this.cdnID = cdnID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name.replace("|", "-").split("-")[0];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute_trace() {
        return route_trace;
    }

    public void setRoute_trace(String route_trace) {
        this.route_trace = route_trace;
    }

    public String getUrl() {
        return url.replace("|", "-").split("-")[0];
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNick() {
        return name.replace("|", "-").split("-")[1];
    }

}
