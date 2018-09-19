package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2017/5/11.
 */

public class WeekDayBean {
    /**
     * code : 800
     * message : 成功
     * data : {"cur_year":"2017","cur_date":"May","return_week_data":[{"week_data":"MON","date_date":"2017-05-08","date_data":"08","has_course":1},{"week_data":"TUES","date_date":"2017-05-09","date_data":"09","has_course":0},{"week_data":"WEDNES","date_date":"2017-05-10","date_data":"10","has_course":0},{"week_data":"THURS","date_date":"2017-05-11","date_data":"11","has_course":0},{"week_data":"FRI","date_date":"2017-05-12","date_data":"12","has_course":1},{"week_data":"SATUR","date_date":"2017-05-13","date_data":"13","has_course":0},{"week_data":"SUN","date_date":"2017-05-14","date_data":"14","has_course":0},{"week_data":"MON","date_date":"2017-05-15","date_data":"15","has_course":0},{"week_data":"TUES","date_date":"2017-05-16","date_data":"16","has_course":0},{"week_data":"WEDNES","date_date":"2017-05-17","date_data":"17","has_course":0},{"week_data":"THURS","date_date":"2017-05-18","date_data":"18","has_course":1},{"week_data":"FRI","date_date":"2017-05-19","date_data":"19","has_course":0},{"week_data":"SATUR","date_date":"2017-05-20","date_data":"20","has_course":1},{"week_data":"SUN","date_date":"2017-05-21","date_data":"21","has_course":0}]}
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
         * cur_date : May
         * return_week_data : [{"week_data":"MON","date_date":"2017-05-08","date_data":"08","has_course":1},{"week_data":"TUES","date_date":"2017-05-09","date_data":"09","has_course":0},{"week_data":"WEDNES","date_date":"2017-05-10","date_data":"10","has_course":0},{"week_data":"THURS","date_date":"2017-05-11","date_data":"11","has_course":0},{"week_data":"FRI","date_date":"2017-05-12","date_data":"12","has_course":1},{"week_data":"SATUR","date_date":"2017-05-13","date_data":"13","has_course":0},{"week_data":"SUN","date_date":"2017-05-14","date_data":"14","has_course":0},{"week_data":"MON","date_date":"2017-05-15","date_data":"15","has_course":0},{"week_data":"TUES","date_date":"2017-05-16","date_data":"16","has_course":0},{"week_data":"WEDNES","date_date":"2017-05-17","date_data":"17","has_course":0},{"week_data":"THURS","date_date":"2017-05-18","date_data":"18","has_course":1},{"week_data":"FRI","date_date":"2017-05-19","date_data":"19","has_course":0},{"week_data":"SATUR","date_date":"2017-05-20","date_data":"20","has_course":1},{"week_data":"SUN","date_date":"2017-05-21","date_data":"21","has_course":0}]
         */

        private String cur_year;
        private String cur_date;
        private String cur_day_sign;
        private List<ReturnWeekDataBean> return_week_data;

        public String getCur_day_sign() {
            return cur_day_sign;
        }

        public void setCur_day_sign(String cur_day_sign) {
            this.cur_day_sign = cur_day_sign;
        }

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

        public List<ReturnWeekDataBean> getReturn_week_data() {
            return return_week_data;
        }

        public void setReturn_week_data(List<ReturnWeekDataBean> return_week_data) {
            this.return_week_data = return_week_data;
        }

        public static class ReturnWeekDataBean {
            /**
             * week_data : MON
             * date_date : 2017-05-08
             * date_data : 08
             * has_course : 1
             */

            private String week_data;
            private String date_date;
            private String date_data;
            private int has_course;

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

            public int getHas_course() {
                return has_course;
            }

            public void setHas_course(int has_course) {
                this.has_course = has_course;
            }
        }
    }
}
