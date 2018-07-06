package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/9.我的学习
 */

public class MyStudyBean {

    /**
     * code : 800
     * message : 成功
     * data : {"putong":{"has_flag":"1","finished":"3","total":"4","data":[{"id":1,"entry_name":"普通项目1","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":2,"entry_name":"排版项目2","status":"2","pre_finished_time":"无","project_progress":30,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":3,"entry_name":"排版项目3","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":4,"entry_name":"排版项目4","status":"1","pre_finished_time":"无","project_progress":0,"status_text":"导师发起审核","pre_finished_time_text":"进行中"}]},"paiban":{"has_flag":"1","finished":"0","total":"2","data":[{"id":1803,"entry_name":"排版项目1","status":"0","pre_finished_time":"无","project_progress":38,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"},{"id":1804,"entry_name":"排版项目2","status":"0","pre_finished_time":"无","project_progress":0,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"}]},"keshi":{"has_flag":"0","finished":"0","total":"0","data":[{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]},"base":{"has_flag":"1","finished":"0","total":"2","data":[{"id":2156,"entry_name":"111","pre_finished_time":"无","total_number":2,"finishend_count":1,"status_text":"进行中","pre_finished_time_text":"进行中"},{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]}}
     */

    private String code;
    private String message;
    private DataBeanXXXX data;

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

    public DataBeanXXXX getData() {
        return data;
    }

    public void setData(DataBeanXXXX data) {
        this.data = data;
    }

    public static class DataBeanXXXX {
        /**
         * putong : {"has_flag":"1","finished":"3","total":"4","data":[{"id":1,"entry_name":"普通项目1","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":2,"entry_name":"排版项目2","status":"2","pre_finished_time":"无","project_progress":30,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":3,"entry_name":"排版项目3","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":4,"entry_name":"排版项目4","status":"1","pre_finished_time":"无","project_progress":0,"status_text":"导师发起审核","pre_finished_time_text":"进行中"}]}
         * paiban : {"has_flag":"1","finished":"0","total":"2","data":[{"id":1803,"entry_name":"排版项目1","status":"0","pre_finished_time":"无","project_progress":38,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"},{"id":1804,"entry_name":"排版项目2","status":"0","pre_finished_time":"无","project_progress":0,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"}]}
         * keshi : {"has_flag":"0","finished":"0","total":"0","data":[{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]}
         * base : {"has_flag":"1","finished":"0","total":"2","data":[{"id":2156,"entry_name":"111","pre_finished_time":"无","total_number":2,"finishend_count":1,"status_text":"进行中","pre_finished_time_text":"进行中"},{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]}
         */

        private PutongBean putong;
        private PaibanBean paiban;
        private KeshiBean keshi;
        private BaseBean base;

        public PutongBean getPutong() {
            return putong;
        }

        public void setPutong(PutongBean putong) {
            this.putong = putong;
        }

        public PaibanBean getPaiban() {
            return paiban;
        }

        public void setPaiban(PaibanBean paiban) {
            this.paiban = paiban;
        }

        public KeshiBean getKeshi() {
            return keshi;
        }

        public void setKeshi(KeshiBean keshi) {
            this.keshi = keshi;
        }

        public BaseBean getBase() {
            return base;
        }

        public void setBase(BaseBean base) {
            this.base = base;
        }

        public static class PutongBean {
            /**
             * has_flag : 1
             * finished : 3
             * total : 4
             * data : [{"id":1,"entry_name":"普通项目1","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":2,"entry_name":"排版项目2","status":"2","pre_finished_time":"无","project_progress":30,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":3,"entry_name":"排版项目3","status":"2","pre_finished_time":"无","project_progress":0,"status_text":"审核通过","pre_finished_time_text":"已完成"},{"id":4,"entry_name":"排版项目4","status":"1","pre_finished_time":"无","project_progress":0,"status_text":"导师发起审核","pre_finished_time_text":"进行中"}]
             */

            private String has_flag;
            private String finished;
            private String total;
            private List<DataBean> data;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getFinished() {
                return finished;
            }

            public void setFinished(String finished) {
                this.finished = finished;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * id : 1
                 * entry_name : 普通项目1
                 * status : 2
                 * pre_finished_time : 无
                 * project_progress : 0
                 * status_text : 审核通过
                 * pre_finished_time_text : 已完成
                 */

                private int id;
                private String entry_name;
                private String status;
                private String pre_finished_time;
                private int project_progress;
                private String status_text;
                private String pre_finished_time_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEntry_name() {
                    return entry_name;
                }

                public void setEntry_name(String entry_name) {
                    this.entry_name = entry_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPre_finished_time() {
                    return pre_finished_time;
                }

                public void setPre_finished_time(String pre_finished_time) {
                    this.pre_finished_time = pre_finished_time;
                }

                public int getProject_progress() {
                    return project_progress;
                }

                public void setProject_progress(int project_progress) {
                    this.project_progress = project_progress;
                }

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public String getPre_finished_time_text() {
                    return pre_finished_time_text;
                }

                public void setPre_finished_time_text(String pre_finished_time_text) {
                    this.pre_finished_time_text = pre_finished_time_text;
                }
            }
        }

        public static class PaibanBean {
            /**
             * has_flag : 1
             * finished : 0
             * total : 2
             * data : [{"id":1803,"entry_name":"排版项目1","status":"0","pre_finished_time":"无","project_progress":38,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"},{"id":1804,"entry_name":"排版项目2","status":"0","pre_finished_time":"无","project_progress":0,"evaluate_result":"5","status_text":"发起审核","pre_finished_time_text":"进行中"}]
             */

            private String has_flag;
            private String finished;
            private String total;
            private List<DataBeanX> data;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getFinished() {
                return finished;
            }

            public void setFinished(String finished) {
                this.finished = finished;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<DataBeanX> getData() {
                return data;
            }

            public void setData(List<DataBeanX> data) {
                this.data = data;
            }

            public static class DataBeanX {
                /**
                 * id : 1803
                 * entry_name : 排版项目1
                 * status : 0
                 * pre_finished_time : 无
                 * project_progress : 38
                 * evaluate_result : 5
                 * status_text : 发起审核
                 * pre_finished_time_text : 进行中
                 */

                private int id;
                private String entry_name;
                private String status;
                private String pre_finished_time;
                private int project_progress;
                private String evaluate_result;
                private String status_text;
                private String pre_finished_time_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEntry_name() {
                    return entry_name;
                }

                public void setEntry_name(String entry_name) {
                    this.entry_name = entry_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPre_finished_time() {
                    return pre_finished_time;
                }

                public void setPre_finished_time(String pre_finished_time) {
                    this.pre_finished_time = pre_finished_time;
                }

                public int getProject_progress() {
                    return project_progress;
                }

                public void setProject_progress(int project_progress) {
                    this.project_progress = project_progress;
                }

                public String getEvaluate_result() {
                    return evaluate_result;
                }

                public void setEvaluate_result(String evaluate_result) {
                    this.evaluate_result = evaluate_result;
                }

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public String getPre_finished_time_text() {
                    return pre_finished_time_text;
                }

                public void setPre_finished_time_text(String pre_finished_time_text) {
                    this.pre_finished_time_text = pre_finished_time_text;
                }
            }
        }

        public static class KeshiBean {
            /**
             * has_flag : 0
             * finished : 0
             * total : 0
             * data : [{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]
             */

            private String has_flag;
            private String finished;
            private String total;
            private List<DataBeanXX> data;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getFinished() {
                return finished;
            }

            public void setFinished(String finished) {
                this.finished = finished;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<DataBeanXX> getData() {
                return data;
            }

            public void setData(List<DataBeanXX> data) {
                this.data = data;
            }

            public static class DataBeanXX {
                /**
                 * id : 2157
                 * entry_name : 222
                 * pre_finished_time : 无
                 * total_number : 3
                 * finishend_count : 0
                 * status_text : 进行中
                 * pre_finished_time_text : 进行中
                 */

                private int id;
                private String entry_name;
                private String pre_finished_time;
                private int total_number;
                private int finishend_count;
                private String status_text;
                private String pre_finished_time_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEntry_name() {
                    return entry_name;
                }

                public void setEntry_name(String entry_name) {
                    this.entry_name = entry_name;
                }

                public String getPre_finished_time() {
                    return pre_finished_time;
                }

                public void setPre_finished_time(String pre_finished_time) {
                    this.pre_finished_time = pre_finished_time;
                }

                public int getTotal_number() {
                    return total_number;
                }

                public void setTotal_number(int total_number) {
                    this.total_number = total_number;
                }

                public int getFinishend_count() {
                    return finishend_count;
                }

                public void setFinishend_count(int finishend_count) {
                    this.finishend_count = finishend_count;
                }

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public String getPre_finished_time_text() {
                    return pre_finished_time_text;
                }

                public void setPre_finished_time_text(String pre_finished_time_text) {
                    this.pre_finished_time_text = pre_finished_time_text;
                }
            }
        }

        public static class BaseBean {
            /**
             * has_flag : 1
             * finished : 0
             * total : 2
             * data : [{"id":2156,"entry_name":"111","pre_finished_time":"无","total_number":2,"finishend_count":1,"status_text":"进行中","pre_finished_time_text":"进行中"},{"id":2157,"entry_name":"222","pre_finished_time":"无","total_number":3,"finishend_count":0,"status_text":"进行中","pre_finished_time_text":"进行中"}]
             */

            private String has_flag;
            private String finished;
            private String total;
            private List<DataBeanXXX> data;

            public String getHas_flag() {
                return has_flag;
            }

            public void setHas_flag(String has_flag) {
                this.has_flag = has_flag;
            }

            public String getFinished() {
                return finished;
            }

            public void setFinished(String finished) {
                this.finished = finished;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<DataBeanXXX> getData() {
                return data;
            }

            public void setData(List<DataBeanXXX> data) {
                this.data = data;
            }

            public static class DataBeanXXX {
                /**
                 * id : 2156
                 * entry_name : 111
                 * pre_finished_time : 无
                 * total_number : 2
                 * finishend_count : 1
                 * status_text : 进行中
                 * pre_finished_time_text : 进行中
                 */

                private int id;
                private String entry_name;
                private String pre_finished_time;
                private int total_number;
                private int finishend_count;
                private String status_text;
                private String pre_finished_time_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEntry_name() {
                    return entry_name;
                }

                public void setEntry_name(String entry_name) {
                    this.entry_name = entry_name;
                }

                public String getPre_finished_time() {
                    return pre_finished_time;
                }

                public void setPre_finished_time(String pre_finished_time) {
                    this.pre_finished_time = pre_finished_time;
                }

                public int getTotal_number() {
                    return total_number;
                }

                public void setTotal_number(int total_number) {
                    this.total_number = total_number;
                }

                public int getFinishend_count() {
                    return finishend_count;
                }

                public void setFinishend_count(int finishend_count) {
                    this.finishend_count = finishend_count;
                }

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public String getPre_finished_time_text() {
                    return pre_finished_time_text;
                }

                public void setPre_finished_time_text(String pre_finished_time_text) {
                    this.pre_finished_time_text = pre_finished_time_text;
                }
            }
        }
    }
}
