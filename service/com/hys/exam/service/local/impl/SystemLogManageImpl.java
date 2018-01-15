package com.hys.exam.service.local.impl;

import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.util.FuncMySQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.util.Map;

public class SystemLogManageImpl implements SystemLogManage {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcTemplate simpleJdbcTemplate;

    public Long addSystemLog(SystemLog systemLog) {
        Long id = getNextId("system_operate_log");
        systemLog.setOperate_id(String.valueOf(id));

        String add_sql = "insert into system_operate_log (visit_ip,request_url,user_id, operate_login_name, operate_type, operate_context,operate_platform, module_name, operate_time, create_time) values (:visit_ip,:request_url,:user_id,:operate_login_name,:operate_type,:operate_context,:operate_platform,:module_name,:operate_time,:create_time)";
         getSimpleJdbcTemplate().update(add_sql,new BeanPropertySqlParameterSource(systemLog));
        return id;
    }

    protected Long getNextId(String seqName) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("show table status like '" + seqName + "'");
        return Long.valueOf(resultMap.get("Auto_increment").toString());
    }

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
