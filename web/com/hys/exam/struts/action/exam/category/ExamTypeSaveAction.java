package com.hys.exam.struts.action.exam.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试分类保存
 * 
 * 作者：陈明凯 2013-3-7
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamTypeSaveAction extends BaseAction {
	private ExamExaminTypeFacade examExaminTypeFacade;

	public void setExamExaminTypeFacade(ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		if (isTokenValid(request, true)) {
			Long id = ParamUtils.getLongParameter(request, "id", 0L);
			Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", -1L);

			if (curTypeId != -1L) {
				if (id == 0) {
					// 新增
					ExamExaminType type = examExaminTypeFacade
							.getExamExaminTypeById(curTypeId);

					ExamExaminType et = new ExamExaminType();
					et.setName(request.getParameter("examinTypeName"));
					et.setParent_id(type.getId());
					et.setLayer(type.getLayer() + 1);
					et.setState(Constants.STATUS_1);
					et.setSub_sys_id(type.getSub_sys_id());
					et.setRemark(request.getParameter("remark"));

					examExaminTypeFacade.addExamExaminType(et);

					request.setAttribute(Constants.IS_RELOAD_ADD, Constants.IS_RELOAD_ADD);
				} else {
					// 修改
					ExamExaminType et = new ExamExaminType();
					et.setName(request.getParameter("examinTypeName"));
					et.setRemark(request.getParameter("remark"));
					et.setId(id);

					examExaminTypeFacade.updateExamExaminType(et);
				}
			}
			request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_1);
		}else{
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
