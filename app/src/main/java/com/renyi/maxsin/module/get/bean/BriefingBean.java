package com.renyi.maxsin.module.get.bean;

/**
 * Created by zhangyuliang on 2018/4/8.简报
 */

public class BriefingBean {

    /**
     * code : 800
     * message : 成功
     * data : {"c_total":"0","c_not_done":"0","c_have_done":"0","s_name":"刘美娜","s_head":"http://edu.mxsyzen.com/data_sourse/s_head_img/2017-11-14-15-24-09-5a0a9a19d68fd.jpg","base_data":{"has_flag":"1","persent_num":"0%","persent":"0/2"},"keshi_data":{"has_flag":"0","persent_num":"0%","persent":"0/0"},"putong_data":{"has_flag":"1","persent_num":"75%","persent":"3/4"},"paiban_data":{"has_flag":"1","persent_num":"0%","persent":"0/2"}}
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
         * c_total : 0
         * c_not_done : 0
         * c_have_done : 0
         * s_name : 刘美娜
         * s_head : http://edu.mxsyzen.com/data_sourse/s_head_img/2017-11-14-15-24-09-5a0a9a19d68fd.jpg
         * base_data : {"has_flag":"1","persent_num":"0%","persent":"0/2"}
         * keshi_data : {"has_flag":"0","persent_num":"0%","persent":"0/0"}
         * putong_data : {"has_flag":"1","persent_num":"75%","persent":"3/4"}
         * paiban_data : {"has_flag":"1","persent_num":"0%","persent":"0/2"}
         */

        private String c_total;
        private String c_not_done;
        private String c_have_done;
        private String s_name;
        private String s_head;
        private BaseDataBean base_data;
        private KeshiDataBean keshi_data;
        private PutongDataBean putong_data;
        private PaibanDataBean paiban_data;

        public String getC_total() {
            return c_total;
        }

        public void setC_total(String c_total) {
            this.c_total = c_total;
        }

        public String getC_not_done() {
            return c_not_done;
        }

        public void setC_not_done(String c_not_done) {
            this.c_not_done = c_not_done;
        }

        public String getC_have_done() {
            return c_have_done;
        }

        public void setC_have_done(String c_have_done) {
            this.c_have_done = c_have_done;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getS_head() {
            return s_head;
        }

        public void setS_head(String s_head) {
            this.s_head = s_head;
        }

        public BaseDataBean getBase_data() {
            return base_data;
        }

        public void setBase_data(BaseDataBean base_data) {
            this.base_data = base_data;
        }

        public KeshiDataBean getKeshi_data() {
            return keshi_data;
        }

        public void setKeshi_data(KeshiDataBean keshi_data) {
            this.keshi_data = keshi_data;
        }

        public PutongDataBean getPutong_data() {
            return putong_data;
        }

        public void setPutong_data(PutongDataBean putong_data) {
            this.putong_data = putong_data;
        }

        public PaibanDataBean getPaiban_data() {
            return paiban_data;
        }

        public void setPaiban_data(PaibanDataBean paiban_data) {
            this.paiban_data = paiban_data;
        }

        public static class BaseDataBean {
            /**
             * has_flag : 1
             * persent_num : 0%
             * persent : 0/2
             */

            private String has_flag;
            private String persent_num;
            private String persent;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getPersent_num() {
                return persent_num;
            }

            public void setPersent_num(String persent_num) {
                this.persent_num = persent_num;
            }

            public String getPersent() {
                return persent;
            }

            public void setPersent(String persent) {
                this.persent = persent;
            }
        }

        public static class KeshiDataBean {
            /**
             * has_flag : 0
             * persent_num : 0%
             * persent : 0/0
             */

            private String has_flag;
            private String persent_num;
            private String persent;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getPersent_num() {
                return persent_num;
            }

            public void setPersent_num(String persent_num) {
                this.persent_num = persent_num;
            }

            public String getPersent() {
                return persent;
            }

            public void setPersent(String persent) {
                this.persent = persent;
            }
        }

        public static class PutongDataBean {
            /**
             * has_flag : 1
             * persent_num : 75%
             * persent : 3/4
             */

            private String has_flag;
            private String persent_num;
            private String persent;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getPersent_num() {
                return persent_num;
            }

            public void setPersent_num(String persent_num) {
                this.persent_num = persent_num;
            }

            public String getPersent() {
                return persent;
            }

            public void setPersent(String persent) {
                this.persent = persent;
            }
        }

        public static class PaibanDataBean {
            /**
             * has_flag : 1
             * persent_num : 0%
             * persent : 0/2
             */

            private String has_flag;
            private String persent_num;
            private String persent;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getPersent_num() {
                return persent_num;
            }

            public void setPersent_num(String persent_num) {
                this.persent_num = persent_num;
            }

            public String getPersent() {
                return persent;
            }

            public void setPersent(String persent) {
                this.persent = persent;
            }
        }
    }
}
