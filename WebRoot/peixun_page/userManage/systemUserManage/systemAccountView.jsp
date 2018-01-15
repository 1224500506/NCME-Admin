<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看学员</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<style>
	.sel_btn{
		height: 25px;
		line-height: 25px;
		padding: 0 15px;
		background: #02bafa;
		border: 1px #26bbdb solid;
		border-radius: 3px;
		/*color: #fff;*/
		display: inline-block;
		text-decoration: none;
		font-size: 14px;
		outline: none;
	}
	.ch_cls{
	    background: #e4e4e4;
	}
</style>
<div>
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="thi_shitineirong" style="">
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>个人信息</p>
					<div class="clear"></div>
				    <!-- 第一行 帐号类型  -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>帐号类型：</span>
					</p>
                    <select class="fl select" id="userType" name="model.userType" disabled>
                        <c:forEach items="${typeList}" var="typeList">   
                        	<c:if test="${typeList.id != 1}">
	                       		<option value="${typeList.id}" <c:if test="${typeList.id == item.userType}">selected</c:if>>${typeList.user_type_name}</option>
                        	</c:if>
                        </c:forEach>
                    </select>
					<div class="clear"></div>
					
					<!-- 第二行 学员账号 学员姓名 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>账号：</span>
						<input type="text" id="accountName" name="model.accountName" value="${item.accountName}" maxlength="18" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>姓名：</span>
						<input type="text" id="realName" name="model.realName" value="${item.realName}" disabled/>
					</p>
					<div class="clear"></div>
					
				    <!-- 第三行 证件类型  证件号码 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em></em>证件类型：</span>
					</p>
					<select class="fl select" id="certificateType" name="model.certificateType" disabled>
						<option value="1" <c:if test="${item.certificateType == 1}"> selected="selected"</c:if>>身份证</option>
						<option value="2" <c:if test="${item.certificateType == 2}"> selected="selected"</c:if>>军官证</option>
						<option value="3" <c:if test="${item.certificateType == 3}"> selected="selected"</c:if>>港澳台通行证</option>
						<option value="4" <c:if test="${item.certificateType == 4}"> selected="selected"</c:if>>护照</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em></em>证件号码：</span>
						<input type="text" id="certificateNo" name="model.certificateNo" value="${item.certificateNo}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第四行 学科  性别 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学科：</span>
						<input type="hidden" id="propIds" name="model.propIds" value="${item.propIds}"/>
						<input type="hidden" id="propNames" name="propNames" value="${item.deptName}"/>
						<input type="text" value="${item.deptName}" disabled/>
						<!-- 
						<a class="duouan" id="propNames01">${item.deptName}</a>
						<a class="duouan">${item.deptName}</a>
						 -->
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">性别：</span>
					</p>
					<select class="fl select" id="sex" name="model.sex" disabled>
						<option value="1" <c:if test="${item.sex == 1}"> selected="selected"</c:if>>男</option>
						<option value="2" <c:if test="${item.sex == 2}"> selected="selected"</c:if>>女</option>
					</select>
					<div class="clear"></div>
					
					<!-- 第五行 手机号 所属机构   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>手机号码：</span>
						<input type="text" id="mobilPhone" name="model.mobilPhone" value="${item.mobilPhone}" maxlength="11" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>所属机构：</span>
						<c:if test="${item.userType == 3}">
							<input type="text" value="中国继续医学教育网" disabled/>
						</c:if>
						<c:if test="${item.userType != 3}">
							<input type="text" id="workUnit" name="model.workUnit" value="${item.workUnit}" disabled/>
							<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "${item.work_unit_id}" /> 
						</c:if>
					</p>
					<div class="clear"></div>
				</div>
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p style="width:5em;text-align:right;">角色信息</p>	
					<div class="clear"></div>	
					<!-- 第六行  角色信息 -->
					<p class="fl" style="margin:0 0 10px 0;line-height:25px;">
						<span style="width:5em;text-align:right;"></span>
					</p>
					<div class="fl" style="margin:0;width:600px;font-size:12px;">
						<c:forEach items="${roleList}" var="role">
							<p class="fl" style="margin:0 20px 10px 0;line-height:25px;width:30%;">
								<input type="checkbox" class="chk" id = "chk_${role.roleId}" style = "width:20px;height:20px;" value="${role.roleId}" disabled/> ${role.roleNameDesc}
							</p>  
						</c:forEach>
						<input type = "hidden" id = "account_role_${item.accountId}" value='<c:forEach items="${item.roleList}" var="role">${role.roleId},</c:forEach>' />
						<input type = "hidden" id = "userId" value="${item.accountId}"/>
					</div>
					<div class="clear"></div>
				</div>
				
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>创建时间信息</p>			
					<div class="clear"></div>	
					<!-- 第七行  创建时间  更新时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">创建时间：</span>
						<input type="text" id="regDate" value="${item.regDatee}" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">更新时间：</span>
						<input type="text" id="lastUpdateDate" value="${item.lastUpdateDatee}" disabled/>
					</p>
					<div class="clear"></div>
				</div>
				
				<div class="tiaojian" style="min-height:40px;">	
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>其它信息</p>		
					<div class="clear"></div>	
					<!-- 第八行  备注说明  备注时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注说明：</span>
						<input type="text" id="reason" value="${item.reason}" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注时间：</span>
						<input type="text" id="reasondate" value="${item.reasondate}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第九行  保存 返回   -->
					<p class="fl" style="margin-bottom:25px;width:75%">
						<span style="width:100%;text-align:center; display:block;">
							<a href="javascript:history.back(-1)" class="sel_btn ch_cls">返回</a>
						</span>
					</p>
				</div>
			</div>
		</div>	
	</div>			
</div>
<script type="text/javascript">
	$(function(){
		//选择目录弹出框
		$('#propNames01').click(function() {
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		
	});
	
	//显示之前选中的角色
	$(".chk").each(function(key,val){
		$(this).prop("checked",false);
	});
	var id = $("#userId").val();
	var account = "#account_role_" + id;
	var account_role = $(account).val();
	var roleArray = account_role.split(",");
	$(roleArray).each(function(key, val){
		$("#chk_" + val).prop("checked",true);
	});	

</script>
