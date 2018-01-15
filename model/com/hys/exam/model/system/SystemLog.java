package com.hys.exam.model.system;

public class SystemLog {
    private String operate_id;
    private String visit_ip;
    private String request_url;
    private String user_id;
    private String operate_login_name;
    private String operate_type;
    private String operate_context;
    private String operate_platform;
    private String module_name;
    private String operate_time;
    private String create_time;
    private String start_date;
    private String end_date;

    public String getOperate_id() {
        return operate_id;
    }

    public void setOperate_id(String operate_id) {
        this.operate_id = operate_id;
    }

    public String getVisit_ip() {
        return visit_ip;
    }

    public void setVisit_ip(String visit_ip) {
        this.visit_ip = visit_ip;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOperate_login_name() {
        return operate_login_name;
    }

    public void setOperate_login_name(String operate_login_name) {
        this.operate_login_name = operate_login_name;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public String getOperate_context() {
        return operate_context;
    }

    public void setOperate_context(String operate_context) {
        this.operate_context = operate_context;
    }

    public String getOperate_platform() {
        return operate_platform;
    }

    public void setOperate_platform(String operate_platform) {
        this.operate_platform = operate_platform;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "operate_id='" + operate_id + '\'' +
                ", visit_ip='" + visit_ip + '\'' +
                ", request_url='" + request_url + '\'' +
                ", user_id='" + user_id + '\'' +
                ", operate_login_name='" + operate_login_name + '\'' +
                ", operate_type='" + operate_type + '\'' +
                ", operate_context='" + operate_context + '\'' +
                ", operate_platform='" + operate_platform + '\'' +
                ", module_name='" + module_name + '\'' +
                ", operate_time='" + operate_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}
