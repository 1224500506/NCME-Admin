package com.hys.auth.audit;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.utils.CusAccessObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditLogFilter implements Filter {
    private static SystemMenuManage localSystemMenuManage;
    private static SystemLogManage systemLogManage;
    private static List<SystemMenu> systemMenuList;

    static {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:application*.xml");
        localSystemMenuManage = (SystemMenuManage) factory.getBean("localSystemMenuManage");
        systemLogManage = (SystemLogManage) factory.getBean("systemLogManage");
        systemMenuList = localSystemMenuManage.getAllMenu();
    }

    public static final Logger logger = Logger.getLogger(AuditLogFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            SystemUser currentUser = (SystemUser) ((HttpServletRequest) request).getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
            if (null != currentUser) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String uri = req.getRequestURI();
                String newUrl = (null == uri) ? "" : uri.substring(uri.indexOf("/", uri.indexOf("/") + 1));
                String ip = req.getRemoteAddr();
                String operatePlatform = "peixun";//平台
                String mode = RequestUtil.getParameter(req, "mode");
                String method = RequestUtil.getParameter(req, "method");
                String handle = ParamUtils.getParameter(req, "handle");
                if (null != mode && !mode.equals("")) {
                    mode = method;
                }
                if (null != method && !method.equals("")) {
                    mode = method;
                }
                if (null != handle && !handle.equals("")) {
                    mode = handle;
                }

                SystemLog systemLog = new SystemLog();
                for (SystemMenu systemMenu : systemMenuList) {
                    if (systemMenu.getUrl().equals(newUrl)||systemMenu.getUrl().contains(newUrl)) {
                        if (StringUtils.isNotBlank(systemMenu.getName())) {
                            systemLog.setModule_name(systemMenu.getName().trim());
                        }
                    }
                }
                //模块名称简写
                String abbreviated_Mode_Name = (null != systemLog.getModule_name() || "".equals(systemLog.getModule_name())) ? systemLog.getModule_name().replace("管理", "") : "";
                List<String> exList = new ArrayList<String>();
                exList.add("exportExecl");
                exList.add("systemlog");
                exList.add("delsystemlog");
                exList.add("delete");
                exList.add("edit");
                exList.add("add");
                exList.add("update");
                exList.add("insert");
                if (null != mode && !"".equals(mode) && !exList.contains(mode) && (null != systemLog.getModule_name()) && (!systemLog.getModule_name().equals("null"))) {
                    systemLog.setOperate_type(mode);
                    systemLog.setOperate_context("查看了该" + systemLog.getModule_name() + "模块");
                } else {
                    if (uri.contains("logout.do")) {
                        systemLog.setModule_name("退出");
                        systemLog.setOperate_type("exit");
                        systemLog.setOperate_context("退出学习培训后台成功！");
                    } else if (uri.contains("add") || method.contains("add")|| method.contains("insert")) {
                        systemLog.setOperate_type("add");
                        systemLog.setOperate_context("新增" + abbreviated_Mode_Name + "成功！");
                    } else if (uri.contains("edit") || method.contains("edit") || method.contains("update")) {
                        systemLog.setOperate_type("edit");
                        systemLog.setOperate_context("修改" + abbreviated_Mode_Name + "完成！");
                    }  else if (uri.contains("delete") || method.contains("delete")) {
                        systemLog.setOperate_type("delete");
                        systemLog.setOperate_context("删除" + abbreviated_Mode_Name + "成功！");
                    } else if (uri.contains("delsystemlog")) {
                        systemLog.setOperate_type("delsystemlog");
                        systemLog.setOperate_context("删除操作日志");
                    } else if(method.contains("executeHtml")){

                    }else {
                        systemLog.setOperate_type("other");
                        systemLog.setOperate_context("查看了" + systemLog.getModule_name() + "模块");
                    }
                }
                if (null != systemLog.getOperate_type() && !systemLog.getOperate_type().equals("null") && systemLog.getModule_name() != null && !"审查日志".equals(systemLog.getModule_name()) && (null != systemLog.getModule_name()) && (!systemLog.getModule_name().equals("null"))) {

                    logger.debug("AuditLogFilter >>>>>>>>>操作平台=" + operatePlatform + ",请求web路径：" + newUrl + ";mode=" + mode + ",登录账号=" + currentUser.getRealName() + ",操作时间==" + sdf.format(new Date()) + ",访问IP=" + ip);
                    systemLog.setUser_id(String.valueOf(currentUser.getUserId()));
                    systemLog.setOperate_login_name(currentUser.getRealName());
                    systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(req));
                    systemLog.setRequest_url(uri);
                    systemLog.setOperate_platform(operatePlatform);
                    systemLog.setOperate_time(sdf.format(new Date()));
                    systemLog.setCreate_time(sdf.format(new Date()));
                    systemLogManage.addSystemLog(systemLog);
                }
            }
        } catch (RuntimeException e) {
            logger.error("添加审计日志期间发生异常:" + e.getMessage());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
