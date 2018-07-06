package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/25.
 */

public class OperationBean {
    /**
     * code : 800
     * message : 成功
     * data : [{"handle_name":"魏星","handle_time":"2017-12-20 01:28:10","handle_content":"课程确认完成"},{"handle_name":"徐博","handle_time":"2017-12-08 09:35:01","handle_content":"发布课程2017-12-15 11:00-12:00"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "OperationBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "handle_name='" + handle_name + '\'' +
                    ", handle_time='" + handle_time + '\'' +
                    ", handle_content='" + handle_content + '\'' +
                    '}';
        }

        /**
         * handle_name : 魏星
         * handle_time : 2017-12-20 01:28:10
         * handle_content : 课程确认完成
         */

        private String handle_name;
        private String handle_time;
        private String handle_content;

        public String getHandle_name() {
            return handle_name;
        }

        public void setHandle_name(String handle_name) {
            this.handle_name = handle_name;
        }

        public String getHandle_time() {
            return handle_time;
        }

        public void setHandle_time(String handle_time) {
            this.handle_time = handle_time;
        }

        public String getHandle_content() {
            return handle_content;
        }

        public void setHandle_content(String handle_content) {
            this.handle_content = handle_content;
        }
    }
}
