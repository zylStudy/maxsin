package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2017/11/24.
 */

public class FirstPageBean {

    /**
     * code : 800
     * message : 成功
     * data : {"cur_year":"2017","cur_date":"Nov","cur_week":"48","cur_day_sign":"1","return_week_data":[{"week_data":"Mon","date_date":"2017-11-27","date_data":"27"},{"week_data":"Tue","date_date":"2017-11-28","date_data":"28"},{"week_data":"Wed","date_date":"2017-11-29","date_data":"29"},{"week_data":"Thu","date_date":"2017-11-30","date_data":"30"},{"week_data":"Fri","date_date":"2017-12-01","date_data":"01"},{"week_data":"Sat","date_date":"2017-12-02","date_data":"02"},{"week_data":"Sun","date_date":"2017-12-03","date_data":"03"}],"hen_to_shu_arr":[{"c_id":6542,"c_date":"2017-11-27","c_long":7.9333333969116,"c_start_time":"03:04","c_end_time":"11:00","c_status":9,"change_recorder":"","sname":"刘德华","user_name":"李蔚","c_type":"基础课"},{},{"c_id":6541,"c_date":"2017-11-29","c_long":5.9833331108093,"c_start_time":"05:05","c_end_time":"11:04","c_status":9,"change_recorder":"","sname":"刘德华","user_name":"李蔚","c_type":"项目课"},{},{},{},{}]}
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
         * cur_year : 2017
         * cur_date : Nov
         * cur_week : 48
         * cur_day_sign : 1
         * return_week_data : [{"week_data":"Mon","date_date":"2017-11-27","date_data":"27"},{"week_data":"Tue","date_date":"2017-11-28","date_data":"28"},{"week_data":"Wed","date_date":"2017-11-29","date_data":"29"},{"week_data":"Thu","date_date":"2017-11-30","date_data":"30"},{"week_data":"Fri","date_date":"2017-12-01","date_data":"01"},{"week_data":"Sat","date_date":"2017-12-02","date_data":"02"},{"week_data":"Sun","date_date":"2017-12-03","date_data":"03"}]
         * hen_to_shu_arr : [{"c_id":6542,"c_date":"2017-11-27","c_long":7.9333333969116,"c_start_time":"03:04","c_end_time":"11:00","c_status":9,"change_recorder":"","sname":"刘德华","user_name":"李蔚","c_type":"基础课"},{},{"c_id":6541,"c_date":"2017-11-29","c_long":5.9833331108093,"c_start_time":"05:05","c_end_time":"11:04","c_status":9,"change_recorder":"","sname":"刘德华","user_name":"李蔚","c_type":"项目课"},{},{},{},{}]
         */

        private String cur_year;
        private String cur_date;
        private String cur_week;
        private String cur_day_sign;
        private List<ReturnWeekDataBean> return_week_data;
        private List<HenToShuArrBean> hen_to_shu_arr;

        public String getCur_year() {
            return cur_year;
        }

        public void setCur_year(String cur_year) {
            this.cur_year = cur_year;
        }

        public String getCur_date() {
            return cur_date;
        }

        public void setCur_date(String cur_date) {
            this.cur_date = cur_date;
        }

        public String getCur_week() {
            return cur_week;
        }

        public void setCur_week(String cur_week) {
            this.cur_week = cur_week;
        }

        public String getCur_day_sign() {
            return cur_day_sign;
        }

        public void setCur_day_sign(String cur_day_sign) {
            this.cur_day_sign = cur_day_sign;
        }

        public List<ReturnWeekDataBean> getReturn_week_data() {
            return return_week_data;
        }

        public void setReturn_week_data(List<ReturnWeekDataBean> return_week_data) {
            this.return_week_data = return_week_data;
        }

        public List<HenToShuArrBean> getHen_to_shu_arr() {
            return hen_to_shu_arr;
        }

        public void setHen_to_shu_arr(List<HenToShuArrBean> hen_to_shu_arr) {
            this.hen_to_shu_arr = hen_to_shu_arr;
        }

        public static class ReturnWeekDataBean {
            /**
             * week_data : Mon
             * date_date : 2017-11-27
             * date_data : 27
             */

            private String week_data;
            private String date_date;
            private String date_data;

            public String getWeek_data() {
                return week_data;
            }

            public void setWeek_data(String week_data) {
                this.week_data = week_data;
            }

            public String getDate_date() {
                return date_date;
            }

            public void setDate_date(String date_date) {
                this.date_date = date_date;
            }

            public String getDate_data() {
                return date_data;
            }

            public void setDate_data(String date_data) {
                this.date_data = date_data;
            }
        }

        public static class HenToShuArrBean {
            /**
             * c_id : 6542
             * c_date : 2017-11-27
             * c_long : 7.9333333969116
             * c_start_time : 03:04
             * c_end_time : 11:00
             * c_status : 9
             * change_recorder :
             * sname : 刘德华
             * user_name : 李蔚
             * c_type : 基础课
             */

            private int c_id;
            private String c_date;
            private double c_long;
            private String c_start_time;
            private String c_end_time;
            private int c_status;
            private String change_recorder;
            private String sname;
            private String user_name;
            private String c_type;

            public int getC_id() {
                return c_id;
            }

            public void setC_id(int c_id) {
                this.c_id = c_id;
            }

            public String getC_date() {
                return c_date;
            }

            public void setC_date(String c_date) {
                this.c_date = c_date;
            }

            public double getC_long() {
                return c_long;
            }

            public void setC_long(double c_long) {
                this.c_long = c_long;
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

            public int getC_status() {
                return c_status;
            }

            public void setC_status(int c_status) {
                this.c_status = c_status;
            }

            public String getChange_recorder() {
                return change_recorder;
            }

            public void setChange_recorder(String change_recorder) {
                this.change_recorder = change_recorder;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getC_type() {
                return c_type;
            }

            public void setC_type(String c_type) {
                this.c_type = c_type;
            }
        }
    }
}
