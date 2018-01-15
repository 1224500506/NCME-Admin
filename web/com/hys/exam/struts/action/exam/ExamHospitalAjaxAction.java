package com.hys.exam.struts.action.exam;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamHospitalVO;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 获取医院列表
 *
 * @author whq
 * @creat 2017-10-25
 */
public class ExamHospitalAjaxAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;


    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }


    public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    @Override
    protected String actionExecute(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String organName = request.getParameter("organName");
        if (organName != null) {
            List<ExamHospitalVO> list = localExamPropValFacade.getHospitalList(organName);
            JSONArray jsonArray2 = JSONArray.fromObject(list);
//            System.out.println("过滤结果=" + jsonArray2.toString());
            StrutsUtil.renderText(response, jsonArray2.toString());
            return null;
        } else {
            StrutsUtil.renderText(response, "");
            return null;
        }

    }
}
