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
				    <!-- 第一行 证件类型  证件号码 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>证件类型：</span>
					</p>
					<select class="fl select" id="certificateType" name="model.certificateType" disabled>
						<option value="1" <c:if test="${item.certificateType == 1}"> selected="selected"</c:if>>身份证</option>
						<option value="2" <c:if test="${item.certificateType == 2}"> selected="selected"</c:if>>军官证</option>
						<option value="3" <c:if test="${item.certificateType == 3}"> selected="selected"</c:if>>港澳台通行证</option>
						<option value="4" <c:if test="${item.certificateType == 4}"> selected="selected"</c:if>>护照</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>证件号码：</span>
						<input type="text" id="certificateNo" name="model.certificateNo" value="${item.certificateNo}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第二行 学员账号 学员姓名 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学员账号：</span>
						<input type="text" id="accountName" name="model.accountName" value="${item.accountName}" maxlength="18" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学员姓名：</span>
						<input type="text" id="realName" name="model.realName" value="${item.realName}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第三行 性别  学历 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">性别：</span>
					</p>
					<select class="fl select" id="sex" name="model.sex" disabled>
						<option value="1" <c:if test="${item.sex == 1}"> selected="selected"</c:if>>男</option>
						<option value="2" <c:if test="${item.sex == 2}"> selected="selected"</c:if>>女</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">学历：</span>
					</p>
					<select class="fl select" id="education" name="model.education" disabled>
						<option value="0">请选择</option>
						<option value="1" <c:if test="${item.education == 1}"> selected="selected"</c:if>>高中</option>
						<option value="2" <c:if test="${item.education == 2}"> selected="selected"</c:if>>中专</option>
						<option value="3" <c:if test="${item.education == 3}"> selected="selected"</c:if>>大专</option>
						<option value="4" <c:if test="${item.education == 4}"> selected="selected"</c:if>>本科</option>
						<option value="5" <c:if test="${item.education == 5}"> selected="selected"</c:if>>硕士</option>
						<option value="6" <c:if test="${item.education == 6}"> selected="selected"</c:if>>博士</option>
						<option value="7" <c:if test="${item.education == 7}"> selected="selected"</c:if>>博士后</option>
						<option value="8" <c:if test="${item.education == 8}"> selected="selected"</c:if>>其它</option>
					</select>
					<div class="clear"></div>
					
					<!-- 第四行  单位名称  科室 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>单位名称：</span>
						<input type="text" id="workUnit" name="model.workUnit" value="${item.workUnit}" disabled/>
						<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "${item.work_unit_id}" /> 
					</p>
					<div class="clear"></div>
					
					<!-- 第五行  职称类型  职称 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>职称类别：</span>
                        <select name="model.workExtType" id="workExtType" style="width: 200px;height: 25px;" disabled>
                            <c:forEach items="${jobList}" var="job">   
                        		<option value="${job.code}" <c:if test="${job.code == workExtType}">  selected</c:if>>${job.name}</option>
                            </c:forEach>
                        </select>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>职称：</span>
                        <select name="model.profTitle" id="profTitle" style="width: 200px;height: 25px;" disabled>
                        	<option value="-1">其它</option>
                            <c:forEach items="${myJobList}" var="myjob">   
                        		<option value="${myjob.id}" <c:if test="${myjob.id == userJobId}">  selected</c:if>>${myjob.name}</option>
                            </c:forEach>  
                        </select>
					</p>
					<div class="clear"></div>
					
					<!-- 单独的科室 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>科室：</span>
                        <select name="model.xueke1" id="xueke1" style="width: 168px;height: 25px;" disabled>
                           	<c:forEach items="${xueke1}" var="xue">   
                           		<c:if test="${xue.id == levelOne}"><option value="${xue.id}" selected>${xue.name}</option></c:if>
                               </c:forEach>                                
                        </select>
                        <select name="model.xueke2" id="xueke2" style="width: 168px;height: 25px;" disabled>
                            <c:forEach items="${xueke2}" var="xue2">   
                           		<option value="${xue2.id}" <c:if test="${xue2.id == levelTwo}">selected</c:if>>${xue2.name}</option>
                               </c:forEach>
                        </select>
                        <select name="model.xueke3" id="xueke3" style="width: 170px;height: 25px;" disabled>
                            <c:forEach items="${xueke3}" var="xue3">   
                           		<option value="${xue3.id}" <c:if test="${xue3.id == item.propIds}">selected</c:if>>${xue3.name}</option>
                               </c:forEach>
                        </select>
					</p>
					<div class="clear"></div>
					
					<!-- 第六行 手机号  邮箱   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>手机号码：</span>
						<input type="text" id="mobilPhone" name="model.mobilPhone" value="${item.mobilPhone}" maxlength="11" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">邮箱：</span>
						<input type="text" id="email" name="model.email" value="${item.email}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第七行  来自基层  执业医师号-->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>来自基层：</span>
					</p>
					<select class="fl select" id="grassroot" name="model.grassroot" disabled>
						<option value="0" <c:if test="${item.grassroot == 0}"> selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${item.grassroot == 1}"> selected="selected"</c:if>>是</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">执业医师号：</span>
						<input type="text" id="health" name="model.health_certificate" value="${item.health_certificate}" disabled/>
					</p>	
					<div class="clear"></div>
					
					<!-- 第八行 单位地址-->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>单位地址：</span>
                        <c:if test="${hosAddress != null}">
                        	<c:if test="${hosAddress != ''}">
                        		<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hosAddress}" disabled/>
                        	</c:if>
                        </c:if>
                        <c:if test="${hosAddress == null || hosAddress == ''}">
                        	<c:if test="${hospitalAddress != null && hospitalAddress != ''}">
                        		<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}" disabled/>
                        	</c:if>
                        </c:if>
                        <c:if test="${hosAddress == null and hospitalAddress == null}">
                        	<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}" disabled/>
                        </c:if>
					</p>
					<div class="clear"></div>
					
					<!-- 第九行  注册时间  更新时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">注册时间：</span>
						<input type="text" id="regDate" value="${item.regDatee}" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">更新时间：</span>
						<input type="text" id="lastUpdateDate" value="${item.lastUpdateDatee}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第十行  备注说明  备注时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注说明：</span>
						<input type="text" id="reason" value="${item.reason}" disabled/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注时间：</span>
						<input type="text" id="reasondate" value="${item.reasondate}" disabled/>
					</p>
					<div class="clear"></div>
					
					<!-- 第十一行  保存 返回   -->
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
