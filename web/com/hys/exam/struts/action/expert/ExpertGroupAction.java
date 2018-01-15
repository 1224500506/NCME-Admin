package com.hys.exam.struts.action.expert;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupModel;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * @author Han
 * 专家委员会管理
 */
public class ExpertGroupAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	private ExpertManageManage localExpertManageManage;
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}
	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		else if (method.equals("editZWH")){
			return editZWH(mapping, form, request, response);
		}
		else if (method.equals("viewZWH")){
			return viewZWH(mapping, form, request, response);
		}
		else if (method.equals("detailZWH")){
			return detailZWH(mapping, form, request, response);
		}
		else if (method.equals("lockView")){
			return lockView(mapping, form, request, response);
		}
		else if (method.equals("lockEdit")){
			return lockEdit(mapping, form, request, response);
		}
		//查看已绑定内容排序
		else if (method.equals("setorder")){
			return setorder(mapping, form, request, response);
		}
		else if (method.equals("lockViewAll")){
			return lockViewAll(mapping, form, request, response);
		}
		else if (method.equals("lockEditAll")){
			return lockEditAll(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}
	/**
	 * 绑定全部  lockstate =1
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private String lockEditAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		long[] selIdArr = ParamUtils.getLongParametersFromString(request, "typeIds", 0);
		ExpertGroup group = new ExpertGroup();
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long sid : selIdArr ){
				group.setId(sid);
					group.setCheck_state(1);
					boolean b = localExpertgroupManage.updateExpertGroupLockstate(group);
					if(b){
						StrutsUtil.renderText(response, "success");
					}else {
						StrutsUtil.renderText(response, "fail");
					}
			}
		}
		return null;
	}
	/**
	 * 解绑     lockstate =-1
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private String lockViewAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		long[] selIdArr = ParamUtils.getLongParametersFromString(request, "typeIds", 0);
		ExpertGroup group = new ExpertGroup();
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long sid : selIdArr ){
				group.setId(sid);
					group.setCheck_state(-1);
					boolean b = localExpertgroupManage.updateExpertGroupLockstate(group);
					if(b){
						StrutsUtil.renderText(response, "success");
					}else {
						StrutsUtil.renderText(response, "fail");
					}
			}
		}
		return null;
	}

	//设置--导航--专委会--绑定
	private String lockEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
			group.setId(eform.getId());
			group = localExpertgroupManage.getExpertGroup(group);
			//绑定
			group.setCheck_state(1);
			boolean flag = localExpertgroupManage.updateExpertGroupLockstate(group);
			if(flag){
				StrutsUtil.renderText(response, "success");
			}else {
				StrutsUtil.renderText(response, "error");
			}
			return null;
	}
	//查看--导航--专委会--解绑---lockView
	private String lockView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
			group.setId(eform.getId());
			group = localExpertgroupManage.getExpertGroup(group);
			//解绑
			group.setCheck_state(-1);
			boolean flag = localExpertgroupManage.updateExpertGroupLockstate(group);
			if(flag){
				StrutsUtil.renderText(response, "success");
			}else {
				StrutsUtil.renderText(response, "error");
			}
			return null;
	}

	//查看绑定项目排序
	private String setorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localExpertgroupManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	//页面管理------全貌detailEdit  detailView
	private String detailZWH(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        String id = request.getParameter("id");
        ExpertGroupModel groupModel = new ExpertGroupModel();
        List<ExpertGroupModel> subGroupModel = new ArrayList<ExpertGroupModel>();

        if (id != null && !id.equals("")) {
            ExpertGroup curGroup = localExpertgroupManage.getExpertGroup(Long.valueOf(id));
            groupModel.setGroup(curGroup);
            groupModel.setExpertList(new ArrayList<ExpertInfo>());
            // 主任委员 副主任委员 秘书长 信息
            groupModel.setExpertList(localExpertgroupManage.getGroupExpertInfo(
                    curGroup.getId(), 1, 2, 3));
            for(ExpertInfo ex : groupModel.getExpertList())
            {
            	try{
            		if (ex.getJob() != 0){
            			ExamProp prop = localExamPropValFacade.getSysPropVal(ex.getJob());
            			ex.setJobName(prop.getName());
            		}
            		}catch(Exception e){}
            }
            ExpertGroup temp = new ExpertGroup();
            temp.setParent(curGroup.getId());
            temp.setLockState(1);

            List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
            for (ExpertGroup s : sublist) {
                ExpertGroupModel sub = new ExpertGroupModel();
                sub.setGroup(s);
                
                ExpertInfo expertInfo = new ExpertInfo();
                expertInfo.setGroupIds(s.getId().toString());
                // 取得专家
//                sub.setExpertList(localExpertgroupManage.getGroupExpertInfo(
//                    s.getId(), 4, 5));
                sub.setExpertList(localExpertgroupManage.getGroupExpertInfo(s.getId()));
                subGroupModel.add(sub);
            }
            request.setAttribute("groupInfo", groupModel);
            request.setAttribute("subGroupInfo", subGroupModel);
            
            
        }
        return "detailZWH";
	}

	//页面管理------已绑定内容
	private String viewZWH(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setName(eform.getName());
		group.setLockState(eform.getState());
		group.setPropIds(eform.getPropIds());
		group.setPropNames(eform.getPropNames());
		group.setParent(0L);
		group.setStartDate(eform.getStartDate());
		group.setEndDate(eform.getEndDate());
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "e.id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		localExpertgroupManage.getExpertGroupPageList(pl, group,true);  //false为绑定      true为解绑
		List<ExpertGroup> list =  pl.getList();
		
		for (ExpertGroup e: list){
			
			//取得关联学科数据
			ExpertGroup old = localExpertgroupManage.getExpertGroup(e.getId());
			e.setProp(old.getProp());

			ExpertGroup temp = new ExpertGroup();
			temp.setParent(e.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			String master = "";
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupIds(e.getId().toString()); //参加委员会可能多选择。 2017-01-11 han
				expertInfo.setSubGroupId(s.getId());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				
				for (ExpertInfo ex: expertlist){
					if(null!=ex&&null!=ex.getOffice()&&ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
					
				}
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
//			e.setMaster(master);
			//取得学组数
			e.setNumSubGroup(sublist.size());
			
			//取得届期数据
			List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(e);
			String termStr = "";
			
			for(ExpertGroupTerm t: termlist){
				termStr += t.getStartDateStr() + "至" + t.getEndDateStr() + "<br>";
			}
			e.setTermStr(termStr);
		}
		
		pl.setList(list);

		request.setAttribute("info", group);
		request.setAttribute("extList", pl);
		return "viewZWH";
	}

	//页面管理------绑定内容管理
	private String editZWH(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setName(eform.getName());
		group.setLockState(eform.getState());
		group.setPropIds(eform.getPropIds());
		group.setPropNames(eform.getPropNames());
		group.setParent(0L);
		group.setStartDate(eform.getStartDate());
		group.setEndDate(eform.getEndDate());
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "e.id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		localExpertgroupManage.getExpertGroupPageListEdit(pl, group);
		List<ExpertGroup> list =  pl.getList();
		//List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		
		for (ExpertGroup e: list){
			logger.info(e);
			//取得关联学科数据
			ExpertGroup old = localExpertgroupManage.getExpertGroup(e.getId());
			e.setProp(old.getProp());

			ExpertGroup temp = new ExpertGroup();
			temp.setParent(e.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			String master = "";
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupIds(e.getId().toString()); //参加委员会可能多选择。 2017-01-11 han
				expertInfo.setSubGroupId(s.getId());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				
				for (ExpertInfo ex: expertlist){
					logger.info(ex.getId());
					
					if(null!=ex&&null!=ex.getOffice()&&ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
					
				}
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
//			e.setMaster(master);
			//取得学组数
			e.setNumSubGroup(sublist.size());
			
			//取得届期数据
			List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(e);
			String termStr = "";
			
			for(ExpertGroupTerm t: termlist){
				termStr += t.getStartDateStr() + "至" + t.getEndDateStr() + "<br>";
			}
			e.setTermStr(termStr);
		}
		
		pl.setList(list);
		request.setAttribute("info", group);
		request.setAttribute("extList", pl);
		return "editZWH";
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置解绑状态为2    前台传来  locakState=1  绑定   前台传来  locakState=0  解绑
		group.setId(eform.getId());
		group = localExpertgroupManage.getExpertGroup(group);
		if(group.getLockState() != null && group.getLockState() == 1)
//			前台传来  locakState=1  绑定0   前台传来  locakState=0  解绑 2
			group.setLockState(2);
		else
			//设置绑定状态为0
			group.setLockState(1);
		
		boolean flag = localExpertgroupManage.updateExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}

	/**
	 * 显示添加专委会页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroup group = new ExpertGroup();
		group.setState(1);
		request.setAttribute("info", group);
		return "edit";
	}
	
	/**
	 * 显示修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group = localExpertgroupManage.getExpertGroup(eform.getId());
		request.setAttribute("info", group);
		return "edit";
	}
	/**
	 * 保存委员会信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		
		ExpertGroup oldGroup = new ExpertGroup();
		
		String name = request.getParameter("rsName");
		String propIds = request.getParameter("propIds");
		String summary = request.getParameter("summary");
		String email = request.getParameter("email");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String note = request.getParameter("note");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		String organizeDate = request.getParameter("organizeDate");
		//设置查询条件
		if(id != null && !id.equals(""))
		{
			oldGroup.setId(Long.valueOf(id));
		}
		oldGroup.setName(name);
		List<ExpertGroup> nameList = localExpertgroupManage.getExpertGroupfromName(oldGroup);
		if(nameList.size() != 0)
		{
			StrutsUtil.renderText(response, "repeatname");
			return null;
		}
		ExpertGroup group = new ExpertGroup();
		
		//如果是修改设置ID
		if (id != null && !id.equals(""))
			group.setId(LongUtil.parseLong(id));
		
		//设置信息
		group.setAddress(address);
//		group.setBreakDate(DateUtil.parse(eform.getBreakDate(), "yyyy-MM-dd"));
		group.setContact(contact);
		group.setEmail(email);
//		group.setMaster(eform.getMaster());
		group.setName(name);
		group.setNote(note);
		group.setOrganizeDate(DateUtil.parse(organizeDate, "yyyy-MM-dd"));
		group.setParent(0L);
		group.setPhone1(phone1);
		group.setPhone2(phone2);
		if(state != null && state !="")
			group.setState(Integer.valueOf(state));
		group.setSummary(summary);
		group.setPropIds(propIds);
		
		if (group.getId() == null)	//添加
			localExpertgroupManage.addExpertGroup(group);
		else	//修改
			localExpertgroupManage.updateExpertGroup(group);
		
		StrutsUtil.renderText(response, "success");
		return null;
	}
	
	/**
	 * 删除委员会
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group.setId(eform.getId());
		
		boolean flag = localExpertgroupManage.deleteExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	
	
	/**
	 * 取得符合查询条件的委员会数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setName(eform.getName());
		group.setLockState(eform.getState());
		group.setPropIds(eform.getPropIds());
		group.setPropNames(eform.getPropNames());
		group.setParent(0L);
		group.setStartDate(eform.getStartDate());
		group.setEndDate(eform.getEndDate());
		
		List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		for (ExpertGroup e: list){
			
			//取得关联学科数据
			ExpertGroup old = localExpertgroupManage.getExpertGroup(e.getId());
			e.setProp(old.getProp());

			ExpertGroup temp = new ExpertGroup();
			temp.setParent(e.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			String master = "";
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupId(e.getId());
				expertInfo.setSubGroupId(s.getId());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				
				for (ExpertInfo ex: expertlist){
					logger.info(expertlist.size()+"-----------------------------------------------");
					if(ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
					
				}
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
			e.setMaster(master);
			//取得学组数
			e.setNumSubGroup(sublist.size());
			
			//取得届期数据
			List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(e);
			String termStr = "";
			
			for(ExpertGroupTerm t: termlist){
				termStr += t.getStartDateStr() + " ; " + t.getEndDateStr() + "<br>";
			}
			e.setTermStr(termStr);
		}

		request.setAttribute("info", group);
		request.setAttribute("extList", list);
		return "list";
	}
	/**
	 * 页面管理全貌
	 * 显示添加专委会页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if(id != null && !id.equals(""))
		{
			ExpertGroup group = new ExpertGroup();
			
			group.setParent(0L);
			group.setId(Long.valueOf(id));
			
			ExpertGroup curGroup =  localExpertgroupManage.getExpertGroup(Long.valueOf(id));	

			ExpertGroup temp = new ExpertGroup();
			temp.setParent(curGroup.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			List<ExpertGroup> sublist1 = new ArrayList<ExpertGroup>();
			List<ExpertGroup> sublist2 = new ArrayList<ExpertGroup>();
			String master = "";
			String bumaster = "";
			String secretary = "";
			int subCount = 0;
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupIds(s.getId().toString());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				String teamManager = "";
				String teamMember = "";
				for (ExpertInfo ex: expertlist){
					if(ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
					else if(ex.getOffice().equals(2))
					{
						bumaster += ex.getName() + ",";
					}
					else if(ex.getOffice().equals(3))
					{
						secretary += ex.getName() + " , ";
					}
					else if(ex.getOffice().equals(4))
					{
						teamManager += ex.getName() + ",";
					}
					else
					{
						teamMember += ex.getName() + ",";
					}
				}
				if(teamManager != "")
				{
					teamManager = teamManager.substring(0,teamManager.length() - 1);
				}
				if(teamMember != "")
				{
					teamMember = teamMember.substring(0,teamMember.length() - 1);
				}
				s.setMaster(teamManager);
				s.setTeamMember(teamMember);
				if(subCount % 2 ==0)
				{
					sublist1.add(s);
				}
				else
				{
					sublist2.add(s);
				}
				subCount ++;
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
			if(bumaster != "")
			{
				bumaster = bumaster.substring(0,bumaster.length() - 1);
			}
			if(secretary != "")
			{
				secretary = secretary.substring(0,secretary.length() - 3);
			}
			curGroup.setMaster(master);
			//取得学组数
			curGroup.setNumSubGroup(sublist.size());
			
			request.setAttribute("group", curGroup);
			request.setAttribute("bumaster", bumaster);
			request.setAttribute("secretary", secretary);
			request.setAttribute("subgroup1", sublist1);
			request.setAttribute("subgroup2", sublist2);
			if(sublist1.size() >= sublist2.size())
			{
				request.setAttribute("subcount", sublist1.size());
			}
			else
			{
				request.setAttribute("subcount", sublist2.size());
			}
		}
		return "detail";
	}
}
