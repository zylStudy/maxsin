package com.renyi.maxsin.module.me.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/5/18.
 */

public class MessageCourseBean {
    /**
     * code : 800
     * message : 成功
     * data : {"total":135,"per_page":10,"current_page":"1","last_page":14,"member_id":24,"list":[{"id":27082,"title":"老师已完成作业评语","content":"'李蔚'已完成作业评语，请查看详情！","add_time":"2018-04-25 10:37:16","has_read":"0"},{"id":27080,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 10:37:14","has_read":"1"},{"id":27078,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 10:01:15","has_read":"1"},{"id":27045,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 03:00:09","has_read":"1"},{"id":24316,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 06:02:22","has_read":"1"},{"id":24314,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 06:01:14","has_read":"1"},{"id":24312,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 05:58:58","has_read":"1"},{"id":24310,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 05:57:17","has_read":"1"},{"id":24304,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 03:51:46","has_read":"1"},{"id":24302,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 03:49:22","has_read":"1"}]}
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
        /**
         * total : 135
         * per_page : 10
         * current_page : 1
         * last_page : 14
         * member_id : 24
         * list : [{"id":27082,"title":"老师已完成作业评语","content":"'李蔚'已完成作业评语，请查看详情！","add_time":"2018-04-25 10:37:16","has_read":"0"},{"id":27080,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 10:37:14","has_read":"1"},{"id":27078,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 10:01:15","has_read":"1"},{"id":27045,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-04-25 03:00:09","has_read":"1"},{"id":24316,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 06:02:22","has_read":"1"},{"id":24314,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 06:01:14","has_read":"1"},{"id":24312,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 05:58:58","has_read":"1"},{"id":24310,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 05:57:17","has_read":"1"},{"id":24304,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 03:51:46","has_read":"1"},{"id":24302,"title":"最新周课表已发布","content":"您的周课表已发布,请前往查看周课表哦!","add_time":"2018-03-26 03:49:22","has_read":"1"}]
         */

        private int total;
        private int per_page;
        private String current_page;
        private int last_page;
        private int member_id;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 27082
             * title : 老师已完成作业评语
             * content : '李蔚'已完成作业评语，请查看详情！
             * add_time : 2018-04-25 10:37:16
             * has_read : 0
             */

            private int id;
            private String title;
            private String content;
            private String add_time;
            private String has_read;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getHas_read() {
                return has_read;
            }

            public void setHas_read(String has_read) {
                this.has_read = has_read;
            }
        }
    }
}
