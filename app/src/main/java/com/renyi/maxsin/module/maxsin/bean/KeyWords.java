package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/27.
 */

public class KeyWords {
    /**
     * code : 800
     * message : 成功
     * data : {"daoshilist":["季轼为"],"zuopinlist":["版画"],"zixunlist":["艺术"],"huodonglist":["创意工坊"]}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> daoshilist;
        private List<String> zuopinlist;
        private List<String> zixunlist;
        private List<String> huodonglist;

        public List<String> getDaoshilist() {
            return daoshilist;
        }

        public void setDaoshilist(List<String> daoshilist) {
            this.daoshilist = daoshilist;
        }

        public List<String> getZuopinlist() {
            return zuopinlist;
        }

        public void setZuopinlist(List<String> zuopinlist) {
            this.zuopinlist = zuopinlist;
        }

        public List<String> getZixunlist() {
            return zixunlist;
        }

        public void setZixunlist(List<String> zixunlist) {
            this.zixunlist = zixunlist;
        }

        public List<String> getHuodonglist() {
            return huodonglist;
        }

        public void setHuodonglist(List<String> huodonglist) {
            this.huodonglist = huodonglist;
        }
    }
}
