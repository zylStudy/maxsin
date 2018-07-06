package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/10.课程列表
 */

public class CoursesListBean {
    /**
     * code : 800
     * message : 成功
     * data : {"total":7,"per_page":10,"current_page":"1","last_page":1,"course_list":[{"c_id":"12079","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-09","c_start_time":"17:30","c_end_time":"18:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11759","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-08","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11783","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-07","c_start_time":"15:30","c_end_time":"16:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11755","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-05","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11762","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-04","c_start_time":"17:30","c_end_time":"18:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11746","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-03","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11761","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-02","c_start_time":"14:00","c_end_time":"15:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"}]}
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
         * total : 7
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * course_list : [{"c_id":"12079","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-09","c_start_time":"17:30","c_end_time":"18:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11759","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-08","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11783","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-07","c_start_time":"15:30","c_end_time":"16:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11755","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-05","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11762","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-04","c_start_time":"17:30","c_end_time":"18:30","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11746","c_name":"普通项目1","c_status":"2","c_type":"普通项目课","user_name":"殷玮","c_date":"2018-04-03","c_start_time":"11:00","c_end_time":"12:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/images/addTeacher/img.png","is_first":"0","professional":"服装设计,时尚管理"},{"c_id":"11761","c_name":"普通项目3","c_status":"2","c_type":"普通项目课","user_name":"李达龙","c_date":"2018-04-02","c_start_time":"14:00","c_end_time":"15:00","change_recorder":"","head_url":"http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg","is_first":"0","professional":"服装设计,时尚管理"}]
         */

        private int total;
        private int per_page;
        private String current_page;
        private int last_page;
        private List<CourseListBean> course_list;

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

        public List<CourseListBean> getCourse_list() {
            return course_list;
        }

        public void setCourse_list(List<CourseListBean> course_list) {
            this.course_list = course_list;
        }

        public static class CourseListBean {
            /**
             * c_id : 12079
             * c_name : 普通项目3
             * c_status : 2
             * c_type : 普通项目课
             * user_name : 李达龙
             * c_date : 2018-04-09
             * c_start_time : 17:30
             * c_end_time : 18:30
             * change_recorder :
             * head_url : http://edu.mxsyzen.com/data_sourse/t_head_img/1507691518tuysso.jpg
             * is_first : 0
             * professional : 服装设计,时尚管理
             */

            private String c_id;
            private String c_name;
            private String c_status;
            private String c_type;
            private String user_name;
            private String c_date;
            private String c_start_time;
            private String c_end_time;
            private String change_recorder;
            private String head_url;
            private String is_first;
            private String professional;

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_status() {
                return c_status;
            }

            public void setC_status(String c_status) {
                this.c_status = c_status;
            }

            public String getC_type() {
                return c_type;
            }

            public void setC_type(String c_type) {
                this.c_type = c_type;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getC_date() {
                return c_date;
            }

            public void setC_date(String c_date) {
                this.c_date = c_date;
            }

            public String getC_start_time() {
                return c_start_time;
            }

            public void setC_start_time(String c_start_time) {
                this.c_start_time = c_start_time;
            }

            public String getC_end_time() {
                return c_end_time;
            }

            public void setC_end_time(String c_end_time) {
                this.c_end_time = c_end_time;
            }

            public String getChange_recorder() {
                return change_recorder;
            }

            public void setChange_recorder(String change_recorder) {
                this.change_recorder = change_recorder;
            }

            public String getHead_url() {
                return head_url;
            }

            public void setHead_url(String head_url) {
                this.head_url = head_url;
            }

            public String getIs_first() {
                return is_first;
            }

            public void setIs_first(String is_first) {
                this.is_first = is_first;
            }

            public String getProfessional() {
                return professional;
            }

            public void setProfessional(String professional) {
                this.professional = professional;
            }
        }
    }
}
