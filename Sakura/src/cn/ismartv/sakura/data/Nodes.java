package cn.ismartv.sakura.data;

import java.util.List;

/**
 * Created by fenghb on 14-7-4.
 */
public class Nodes {

    private List<Node> cdn_list;

    public List<Node> getCdn_list() {
        return cdn_list;
    }

    public void setCdn_list(List<Node> cdn_list) {
        this.cdn_list = cdn_list;
    }

    public static class Node {
        private String cdnID;
        private String flag;
        private String name;
        private String route_trace;
        private String url;

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
            return name;
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
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
