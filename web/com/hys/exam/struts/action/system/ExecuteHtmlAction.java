package com.hys.exam.struts.action.system;

import com.hys.auth.util.RequestUtil;
import com.hys.exam.struts.action.AppBaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteHtmlAction extends AppBaseAction {
    protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String method = RequestUtil.getParameter(request, "method");
        if (method.equals("executeHtml")) {
            return "executeHtml";
        } else {
            return null;
        }
    }
}
