<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/peixun_page/top.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/addExam_init.js"></script>
    	<script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    	 <script type="text/javascript">
	        var message = "'${message}'";
	        if (message == "添加成功") {
	            alert("添加成功!");
	        }
			
			function formatDate(){ 
				if($("#startTime").val() != ""){
					WdatePicker({dateFmt:'yyyy-MM-dd', minDate:$("#startTime").val(), errDealMode:'-1'});
				}
				else WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-%d', errDealMode:'-1'});
			}
	        function checkThis(obj) {
	            if (obj.value.length == 1) {
	                obj.value = obj.value.replace(/[^1-9]/g, '')
	            } else {
	                obj.value = obj.value.replace(/\D/g, '')
	            }
	        }
	    
	    </script>
		<title>培训后台</title>
	</head>

<!-- 内容 -->
<form id="fm1" name="fm1" method="post">
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
<div class="center" style="">
<div id = "tabs-1">
	<ul class="kaoshi_ul">
		<li><i class="action">1</i><p class="fl action">考试基本信息</p><span class="action"></span></li>
		<li><i class="ml30">2</i><p class="fl">选择试卷、监考人、评卷人</p><span></span></li>
		<li><i class="ml30">3</i><p class="fl">选择机构或考生</p></li>
	</ul>
	<div class="clear"></div>
	<div id="divMessage">${msg}</div>
	
	<div class="thi_shitineirong ca1_jichu" style="width:1040px;margin:0 auto;">
		 <input type="hidden" name="stuNamesId" id="stuNamesId"/>
         <input type="hidden" name="userNamesId" id="userNamesId"/>
         <input type="hidden" name="paperNamesId" id="paperNamesId"/>
         <input type="hidden" name="orgNamesId" id="orgNamesId"/>
         <input type="hidden" name="orgNames" id="orgNames"/>
         <input type="hidden" name="teacherNamesId" id="teacherNamesId"/>
         <input type = "hidden" name= "examExamination.id" id = "examExamination.id" />
         <input type="hidden" id="eexamType" name="examExamination.examType" value="1"/>
		<ul style="margin-top:30px;">
			<li style="margin-top:20px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;">*</i>考试名称：</span></p>
					<input name="examExamination.name" type="text" id="examExaminationName" class="fl tki_bianji"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;">*</i>所属目录：</span></p>
					<input type="hidden" id="curTypeId" name ="examExamination.exam_type_id" />
					<input type="hidden" id="typeNames" name="examExamination.exam_type_name"/>
					<input type="hidden" id="examType" name="examExamination.exam_type" value = "1"/>
					<div  id="typeNames01" class="duouan" style="width:300px;height:30px;"></div>	
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">考试状态：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio"  name="examExamination.state" value="2"
                                checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">正常</span>
						<input type="radio" class="fl mt10"  name="examExamination.state" value="2" style="margin-left:58px;"/>
						<span class="fl ml5">禁用</span>
					</p>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;">*</i>考试时长：</span></p>
					<input name="examExamination.exam_times" onkeyup="this.value= this.value.replace(/[^\d]/g, '');" class="validate[required,custom[number]] fl tki_bianji" id="examTimes"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">考试方式：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio"  name="examExamination.exam_time" value="1"
                                checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">随时开考</span>
						<input type="radio" class="fl mt10 ml30" name="examExamination.exam_time" value="2"/>
						<span class="fl ml5">统一开考</span>
					</p>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;">*</i>通过标准：</span></p>
					<input type="hidden" id="passCondition1" name="examExamination.pass_condition" value="1"/>
					<input type="text" onkeyup="this.value= this.value.replace(/[^\d]/g, '');" class="fl tki_bianji" style="width:63px;" size="22" name="examExamination.pass_value" id="passValue"/>
					<p class="fl ml20" style=""><span class="fl">得分率（%）</span></p>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;">*</i>开始时间：</span></p>
					<input name="examExamination.start_time" id="startTime"
                   	 type="text" class="validate[required] fl tki_bianji"
                   	 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\',{d:0});}',errDealMode:'-1'})"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;">*</i>结束时间：</span></p>
					<input name="examExamination.end_time" id="endTime"
                    class="validate[required] fl tki_bianji" type="text"
                    onclick="javascript:formatDate()"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">显示方式：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio" name="examExamination.paper_display_mode" class="fl mt10 ml20" value="1" checked="checked"/>
						<span class="fl ml5">单项显示</span>
						<input type="radio" name="examExamination.paper_display_mode" value="2" style="margin-left:30px;" class="fl mt10"/>
						<span class="fl ml5">双向显示</span>
					</p>
				</div>
				<div class="fl shitin_xinzeng01"  style="margin-left:100px;">
					<p class="fl ml30"><span class="fr">答题顺序：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio" name="examExamination.question_display_mode" value="1" checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">正常</span>
						<input type="radio" name="examExamination.question_display_mode" value="2" class="fl mt10" style="margin-left:58px;"/>
						<span class="fl ml5">随机</span>
					</p>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">是否限制切屏：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio" name="examExamination.is_cut_screen" value="1" title="ok" checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">是</span>
						<input type="radio" class="fl mt10" name="examExamination.is_cut_screen" value="0" title="no" id="mustCutScreen" style="margin-left:75px;"/>
						<span class="fl ml5">否</span>
					</p>
				</div>
				<div class="fl shitin_xinzeng01"  style="margin-left:100px;">
					<p class="fl ml30"><span class="fr">是否盲判：</span></p>
					<p class="fl" style="width:300px;">
					<input type="radio" name="examExamination.isnot_decide" value="1" checked="checked"  class="fl mt10 ml20"/>
						<span class="fl ml5">是</span>
						<input type="radio" name="examExamination.isnot_decide" value="0" class="fl mt10" style="margin-left:73px;"/>
						<span class="fl ml5">否</span>
					</p>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">是否查看测评报告：</span></p>
					<p class="fl" style="width:300px;">
						<input type="radio" name="examExamination.isnot_see_test_report" value="0" checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">不可查看</span>
						<input type="radio" name="examExamination.isnot_see_test_report" value="1" class="fl mt10" style="margin-left:34px;"/>
						<span class="fl ml5">可查看</span>
					</p>
				</div>
				<div class="fl shitin_xinzeng01"  style="margin-left:100px;">
					<p class="fl ml30"><span class="fr">是否显示正确答案：</span></p>
					<p class="fl" style="width:300px;">
					<input type="radio" name="examExamination.isnot_see_result" value="1" checked="checked" class="fl mt10 ml20"/>
						<span class="fl ml5">是</span>
						<input type="radio" name="examExamination.isnot_see_result" value="2" class="fl mt10" style="margin-left:73px;"/>
						<span class="fl ml5">否</span>
					</p>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">注意事项：</span></p>
					<textarea name="examExamination.remark" id="textarea" cols="" rows="" class="fl" style="width:860px;height:100px;border:1px solid #dddddd;"></textarea>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:15px;">
				<div class="cas_anniu" style="width:240px;margin:40px auto;">
					<a href="#" id = "buttonOneDown" class="fl queren" style="width:70px;">下一步</a>&nbsp;&nbsp;
					<a href="${ctx }/examManage/examList.do" class="fl queren" style="margin-left:10px">返回列表</a>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
<div id = "tabs-2">
	<ul class="kaoshi_ul">
		<li><i class="action">1</i><p class="fl action">考试基本信息</p><span class="action"></span></li>
		<li><i class="ml30 action">2</i><p class="fl action">选择试卷、监考人、评卷人</p><span class=""></span></li>
		<li><i class="ml30">3</i><p class="fl">选择机构或考生</p></li>
	</ul>
	<div class="clear"></div>
	<div class="thi_shitineirong ca1_jichu" style="width:1040px;margin:0 auto;">		
		<div class="mt30 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl" id="addPaper-link" style="width:80px;">添加试卷</a>
			</div>
		</div>
		<div class="clear"></div>
		<table id="stuPapers" class="mt10 table">
			<tr class="tr">
				<th width="25%">试卷名称</th>
				<th width="25%">组卷方式</th>
				<th width="10%">试卷分数</th>
				<th width="10%">试卷题量</th>
				<th width="20%">创建时间</th>
				<th width="10%">操作</th>
			</tr>
			<tr align="center" valign="middle">
                    <td colspan="6" id="paper_td">--请选择组卷--</td>
             </tr>
		</table>
		<div class="mt30 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" id="addStudent-link"  class="fl" style="width:80px;">添加监考人</a>
			</div>
		</div>
		<div class="clear"></div>
		<table id="stuNames" class="mt10 table">
			<tr class="tr">
				<th width="10%">姓名</th>
				<th width="20%">登录名</th>
				<th width="20%">单位</th>
				<th width="10%">科室</th>
				<th width="10%">职称</th>
				<th width="20%">联系电话</th>
				<th width="10%">操作</th>
			</tr>
			 <tr align="center" valign="middle">
                   <td colspan="7" id="stu_td">--请选择监考人--</td>
               </tr>
		</table>
		<div class="cas_anniu" style="width:170px;margin:40px auto;">
			<a href="#" class="fl queren" id = "buttonTwoUp" style="width:70px;">上一步</a>
			<a href="#" class="fl queren" id = "buttonTwoDown" style="width:70px;margin-left:20px;">下一步</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div id = "tabs-3">
	<ul class="kaoshi_ul">
		<li><i class="action">1</i><p class="fl action">考试基本信息</p><span class="action"></span></li>
		<li><i class="ml30 action">2</i><p class="fl action">选择试卷、监考人、评卷人</p><span class=" action"></span></li>
		<li><i class="ml30 action">3</i><p class="fl action">选择机构或考生</p></li>
	</ul>
	<div class="clear"></div>
	<div class="thi_shitineirong ca1_jichu" style="width:1040px;margin:0 auto;">		
		<div class="mt30 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl kao_jigou" id = "addOrganization" style="width:80px;">添加机构</a>
				<input type = "hidden" name = "examCommonType.name" id = "showtw" />
				<input type = "hidden" name = "examCommonType.id" id = "commonTypeId" />
			</div>
		</div>
		<div class="clear"></div>
		<table class="mt10 table" id = "orgTable">
			<tr class="tr">
				<th width="70%">机构名称</th>
				<th width="30%">操作</th>
			</tr>
			<tr align="center" valign="middle" id="org_tr">
                    <td colspan="2" id="org_td">--添加机构--</td>
              </tr>
		</table>
		<div class="mt30 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl" id="addUser-link" style="width:80px;">添加考生</a>
			</div>
		</div>
		<div class="clear"></div>
		<table id = "userNames" class="mt10 table">
			<tr class="tr">
				<th width="10%">姓名</th>
				<th width="20%">登录名</th>
				<th width="20%">单位</th>
				<th width="10%">科室</th>
				<th width="10%">职称</th>
				<th width="20%">联系电话</th>
				<th width="10%">操作</th>
			</tr>
			 <tr align="center" valign="middle">
                   <td colspan="7" id="user_td">--请选择考生--</td>
               </tr>
		</table>
		<div class="cas_anniu" style="width:260px;margin:40px auto;">
			<a href="#" class="fl queren" id="buttonThreeUp" style="width:70px;">上一步</a>
			<a href="#" class="fl queren" id="submit-link" style="width:70px;margin-left:20px;">确认</a>
			<a href="${ctx}/examManage/examList.do" class="fl queren" style="width:70px;margin-left:20px;">取消</a>
		</div>
		<div class="clear"></div>
	</div>

</div>
</div>
</form>
<!-- 修改密码 -->

<!-- 添加用户 -->
<div class="toumingdu  exp_tang03" style="display:none;">
	<div class="exper_tangkuang01" style="height:400px;margin:160px auto;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;background:#fff;width:700px;">
		<div class="exr_mingchengz">
			<div class="thi_shitineirong" style="width:700px;padding-top:50px;">
					<ul>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">账号：</span></p>
								<input type="hidden" id="sysId" name="id"/>
								<input type="hidden" id="roleIds" name="roleIds"/>
								<input class="fl tki_bianji011" readonly id="userId" name="userId" maxlength = "20"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">用户类型：</span></p>
								<input type = "text" readonly id="curRolesNames" name="curRolesNames" readonly class="fl tki_bianji011 takuang_xk editRole"/>
								<input type = "hidden" class="fl tki_bianji011" id = "curRolesIds" name = "curRolesIds" >
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">姓名：</span></i></p>
								<input class="fl tki_bianji011" id="realName" readonly name="realName" maxlength = "50"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">性别：</span></p>
								<input type = "text" class="fl tki_bianji011" id="sex" readonly name="sex" value = "">
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">单位：</span></p>
								<input class="fl tki_bianji011" readonly id="workUnit" name="workUnit"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">手机号：</span></p>
								<input class="fl tki_bianji011" readonly id="mobilPhone" name="mobilPhone"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">状态：</span></p>
								<input type = "text" readonly class="fl tki_bianji011" style="width:202px;" id="curStatus" name="curStatus"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">学科：</span></p>
								<input type = "text" id="curPropNames" name="curPropNames" readonly class="fl tki_bianji011 takuang_xk editProp"/>
								<input type = "hidden" id = "curPropIds" name = "curPropIds" >
								
							</div>
							<div class="clear"></div>
						</li>

						<li>
							<div class="ca1_anniu" style="width:200px;">
								<a href="#" class="fl hide01" >返回</a>
							</div>
							<div class="clear"></div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
		</div>

	</div>
</div>
<!-- 添加编辑 -->
<div id = "layercontent">
<div class="center">
	<form action="" id="fm1" name="fm1" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0"/>
	<input type="hidden" name="model.userType" value="2"/>
			<div id="" class="layui-layer-content" style="height: 205px;">
				<div class="center">
					<div class="tiaojian" style="min-height:40px;">
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">编辑姓名：</span>
							<input type="text" id="realName1" name="model.realName" value="">
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">编辑账号：</span>
							<input type="text" id="accountName" name="model.accountName" value="">
						</p>
						<div class="clear"></div>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">学科：</span>
							<input type="text" class="subject_val" id="deptName" name="model.deptName" value="">
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">联系电话：</span>
							<input type="text" id="mobilPhone1" name="model.mobilPhone" value="">
						</p>
					</div>
				</div>
			</div>
	</form>
</div>
</div>

<!-- layer内容 -->
<div id="layercontent1">
<div class="center">
<form name="editfrm" action="${ctx}/system/systemUserStudent.do?method=save" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0" />
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件类型：</span>
		</p>
		<select class="fl select" id="certificateType" name="model.certificateType">
			<option value="1">身份证</option>
			<option value="2">军官证</option>
			<option value="3">港澳台通行证</option>
		</select>

		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件号码：</span>
			<input type="text" id="certificateNo" name="model.certificateNo" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员账号：</span>
			<input type="text" id = "accountName" value="" />
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员姓名：</span>
			<input type="text" id="realName1" name="model.realName" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">性别：</span>
		</p>
		<select class="fl select" id="sex1" name="model.sex">
			<option value="1" <c:if test="${sex1==1}">selected</c:if>>男</option>
			<option value="2" <c:if test="${sex1==2}">selected</c:if>>女</option>
		</select>

		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学历：</span>
		</p>
		<select class="fl select" id="education" name="model.education">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">科室：</span>
			<input type="text" id="deptName" name="model.deptName" value="" />
		</p>
		<!-- <select class="fl select" id="deptName1" name="model.deptName">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select> -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">感兴趣的学科：</span>
			<input type="text" id="profession" name="model.profession" value="" />
		</p>
		<div class="clear"></div> 
		<p class="fl">
			<span style="width:8em;text-align:right;">职称：</span>
			<input type="text" id="profTitle" name="model.profTitle" value="" />
		</p>
		<!-- <select class="fl select" id="profTitle" name="model.profTitle">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select> -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">手机号码：</span>
			<input type="text" id="mobilPhone1" name="model.mobilPhone" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位名称：</span>
			<input type="text" id="workUnit1" name="model.workUnit"  readonly value="" style="width:640px;" />
		</p>
		<div class="clear"></div><p class="fl" style="margin:0 0 20px 0">
			<span style="width:8em;text-align:right;">地区：</span>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<input type="text" id="userProvinceName" name="model.userConfig.userProvinceName" value="" />
		</p>
		<!-- <select class="fl select" id="userProvinceId" name="model.userConfig.userProvinceId">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select> -->
		<p class="fl" style="margin-bottom:20px;">
			<input type="text" id="userCityName" name="model.userConfig.userCityName" value="" />
		</p>
		<!-- <select class="fl ml20 select" id="userCityId" name="model.userConfig.userCityId" >
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select> -->
		<p class="fl" style="margin-bottom:20px;">
			<input type="text" id="userCountiesName" name="model.userConfig.userCountiesName" value="" />
		</p>
		<!-- <select class="fl ml20 select" id="userCountiesId" name="model.userConfig.userCountiesId">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select> -->
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位详细地址：</span>
			<input type="text" id="address" name="model.userConfig.address" value="" style="width:640px;" />
		</p>
	</div>
</form>
</div>
</div>
<script type="text/javascript">
var basePath = "${ctx}";
var win_w;
var	win_h;
var	add_cont;
var	win_w1;
var	win_h1;
var	add_user_cont;

$(function(){
		
		//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
		$('.select').click(function(){
			$('.list').css("display","none");
			$(this).find('ul').show();
			return false;
		});
		
		$('.list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.list').slideUp(50);
		});
		
		$('.list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
		$('#typeNames01').click(function(){
			initPropList("选择目录","${ctx}/examManage/getExamListType.do",-1,0,5,0,$('#curTypeId'),$('#typeNames'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('.bjt_kt').click(function(){
			$('.att_make01').hide();

		});
		$('.hide01').click(function(){
			$('.exp_tang03').hide();
		});
});
	var kuangcode;
	var kuang;
	
	var initsubmenu;
	var sort;
	var selarray;
function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
		if(_title == "选择目录")
			sort = 1;
		else if(_title == "医院")
			sort =2;
		$('.tit_biaoti').text(_title);
		$('.xs_selectlvl').text(_selectLvl);
		$('.xs_checklvl').text(_checkLvl);
		$('.xs_currentid').text(_initId);

		$('.xs_er').eq(0).removeClass('xs_er').addClass('xs_san');
		$('.xs_er').remove();
		$('.xs_san').eq(0).removeClass('xs_san').addClass('xs_er');
		$('.xs_er i').show();
		$('.xs_er em').hide();
		
		kuangcode = _kuangcode;
		kuang = _kuang;
		ajaxurl = _ajaxurl;
		
		if (_title == "选择目录"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('选择目录');
			initsubmenu="选择目录";
		}
		if (_title == "医院"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('选择目录');
			initsubmenu="医院";
		}
			
		$('.xs_kuangcode').text($(_kuangcode).val());
		$('.xs_kuang').text($(_kuang).val());
		var url;
		
		if(sort == 2){
			$('.attr_xueke01').text(initsubmenu);
			$('.attr_xueke01').removeAttr("id");
			var selstr = $('.xs_kunag').text();
			url = "${ctx}/propManage/getPropListAjax.do?type="+ _initType +"&id="+ _initId;
		}
		if(sort == 1){
			var selstr = $(_kuang).val();
			 url = ajaxurl + "?parentId=" +_initType;
		}
		selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
		});
		$('.xs_kuang').html(newnarray.toString());		

		$('.delem').click(function(){
			delem($(this));
		});
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){			   		
			   		updatePropList(result);
			   };
		    }
		});		
	}

	function initPropWndProp(){
		
		$('.attr_xueke04').off('click');
		$('.attr_xueke04').click(function(){
		
			var curid = eval($('.xs_currentid').text());
			var selid = $(this).prop('id');
			var selname = $(this).text();
			var inp;
			if(sort == 1){
				var a = $(this).find('i').length;
				if (!a) return false;
				ajaxData(selid, selname, curid);
			}
			if(sort == 2){
				inp = $(this).parent().find('input').prop("type");
				if(inp == null)
				ajaxData(selid, selname, curid);
			}
		});
		
		$('.attr_xueke01').off('click');
		$('.attr_xueke01').click(function(){
			var curid = eval($('.xs_currentid').text());
			var selid = $(this).prop('id');
			var selname = $(this).text();
			if (selid == '') return false;
			
			$('.xs_currentid').text(selid);
			var inx = $(this).parent().index();
			if (inx<0)return;
			$('.xs_er').each(function(key, val){
				if (key >= inx)
					$(val).remove();
				if (key == inx-1){
					$(val).find('i').show();
					$(val).find('em').hide();
				}
					
			});

			if (selid == 0) $('.xs_biaoti').html('<div class="fl xs_er"><p class="fl attr_xueke01">'+initsubmenu+'</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>');
			
			
			if(sort == 1){
				if(selid == 0)
					url = ajaxurl + "?parentId=-1";
				else
					url = ajaxurl + "?parentId=" +selid;
			}
			else if(sort == 2) url = "${ctx}/propManage/getPropListAjax.do?type=23";
		
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList(result);
				   };
			    }
			});
		});
		
		if(sort == 1){
			$('.xs_ul input[type="radio"]').off('click');
			$('.xs_ul input[type="radio"]').click(function(){
				var p = $(this).parent().find('.attr_xueke04').eq(0);
				var id = $(p).prop('id');
				var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
				
				if ($(this).prop('checked')){
					var newarray = new Array();
					$('.xs_kuangcode').text("");
					$('.xs_kuang').text("");
					newarray.push(id);
					$('.xs_kuangcode').text(newarray.toString());
	
					selstr = $('.xs_kuang').html();
					selarray = selstr.split(",");
					var newnarray = new Array();
					$(selarray).each(function(key, val){
						if (val != "") newnarray.push(val);
					});
					newnarray.push(propname);
					$('.xs_kuang').html(newnarray.toString());
				}
				$('.delem').off('click');
				$('.delem').click(function(){
					delem($(this));
				});
			
			});		
			//selected item mark checked.
			$('.xs_ul input[type="radio"]').each(function(key, val){
				var p = $(this).parent().find('p').eq(0);
				var id = $(p).prop('id');
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var idx = selarray.indexOf(id);
				
				if (idx>=0) $(this).prop("checked", true);
			});
		}
		if(sort == 2){
			$('.xs_ul input[type="checkbox"]').off('click');
			$('.xs_ul input[type="checkbox"]').click(function(){


			var p = $(this).parent().find('p').eq(0);
			var id = $(p).prop('id');
			if(id == "chkall")
			{
				$('.xs_ul input[type="checkbox"]').each(function(key, val){					
					var p = $(this).parent().find('p').eq(0);
					var id = $(p).prop('id');
					if(id != "chkall")
					{
						var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
						
						if ($(this).prop('checked') != true){
							$(this).prop("checked", true);
							var selstr = $('.xs_kuangcode').text();
							var selarray = selstr.split(",");
							var newarray = new Array();
							$(selarray).each(function(key, val){
								if (val != "") newarray.push(val);
							});
							newarray.push(id);
							$('.xs_kuangcode').text(newarray.toString());
			
							selstr = $('.xs_kuang').html();
							selarray = selstr.split(",");
							var newnarray = new Array();
							$(selarray).each(function(key, val){
								if (val != "") newnarray.push(val);
							});
							newnarray.push(propname);
							$('.xs_kuang').html(newnarray.toString());
						}
						else{
							$(this).prop("checked", false);
							var selstr = $('.xs_kuangcode').text();
							var selarray = selstr.split(",");
							var idx = selarray.indexOf(id);
							var newarray = new Array();
							$(selarray).each(function(key, val){
								if (key != idx) newarray.push(val);
							});
							$('.xs_kuangcode').text(newarray.toString());
			
							selstr = $('.xs_kuang').html();
							selarray = selstr.split(",");
							var newnarray = new Array();
							$(selarray).each(function(key, val){
								if (key != idx) newnarray.push(val);
							});
							$('.xs_kuang').html(newnarray.toString());
						}
						
						$('.delem').off('click');
						$('.delem').click(function(){
							delem($(this));
						});
					}

				});
				return;
			}
			var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
			
			if ($(this).prop('checked')){
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var newarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newarray.push(val);
				});
				newarray.push(id);
				$('.xs_kuangcode').text(newarray.toString());

				selstr = $('.xs_kuang').html();
				selarray = selstr.split(",");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newnarray.push(val);
				});
				newnarray.push(propname);
				$('.xs_kuang').html(newnarray.toString());
			}
			else{
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var idx = selarray.indexOf(id);
				var newarray = new Array();
				$(selarray).each(function(key, val){
					if (key != idx) newarray.push(val);
				});
				$('.xs_kuangcode').text(newarray.toString());

				selstr = $('.xs_kuang').html();
				selarray = selstr.split(",");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (key != idx) newnarray.push(val);
				});
				$('.xs_kuang').html(newnarray.toString());
			}
			
			$('.delem').off('click');
			$('.delem').click(function(){
				delem($(this));
			});
		
		});
	
	//selected item mark checked.
		$('.xs_ul input[type="checkbox"]').each(function(key, val){
			var p = $(this).parent().find('p').eq(0);
			var id = $(p).prop('id');
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			
			if (idx>=0) $(this).prop("checked", true);
		});
	}
		
	}	

	function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		
		if(sort == 1){
			$(result).each(function(key, value){
				str += "<li><div class=''>";
				if(value.state != "closed")
					str += '<input class="fl" style="margin-top:5px;" type="radio" name = "examtype" value ="'+value.id+'">';
	
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.text + '</em>';
				if(value.state == "closed")
					str += '<i class="fl ml10 kti_bjt2"></i>';
			});
		}
		if(sort == 2){
			$(result.list).each(function(key, value){
				str += "<li><div class=''>";
				
				if(result.state != "closed")
				{
					str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.name + '</em></p>';
					str += '<i class="fl ml10 kti_bjt2"></i>';
				}
				else
				{
					str += '<input class="fl" style="margin-top:5px;" type="checkbox"/>';
					str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.name + '</em></p>';
				}
			});
		}
		str += "</div><div class='clear'></div></li>";		
		$('.xs_ul').html(str);
		initPropWndProp();
	}
	
function ajaxData(selid, selname, curid){	
	$('.xs_currentid').text(selid);
	var ms = $('.xs_biaoti .attr_xueke01').length-1;
	$('.xs_er i').hide();
	$('.xs_er em').show();
	
	var str = '<div class="fl xs_er"><p class="fl attr_xueke01" id=' + curid + '>' + selname + '</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>';
	$('.xs_er').eq(ms).after(str);
	if(curid == 0)	$('.xs_er').eq(0).remove();
	
	var url = ajaxurl + "?parentId="+selid;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList(result);
		   };
	    }
	});
}
	
function delem(obj){
		var i = $(obj).index();
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('.xs_kuang').html(newnarray.toString());
		
		//delete code
		var deletecode = "";
		selstr = $('.xs_kuangcode').text();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('.xs_kuangcode').text(newarray.toString());

		//delete check
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});

		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
		
}

function selectProp(){
	
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).val($('.xs_kuang').text());
		$('.att_make01').hide();
		$('#typeNames01').text($('.xs_kuang').text());
}


	function putTextIn(hosIds, hosNames) {
		
		if (hosIds == undefined && hosIds == null) {
			return false;
		}
		else
		{
			hIds = hosIds.slice(0, hosIds.length-1);
			hNames = hosNames.slice(0, hosNames.length-1);
		}
	
	    $("#commonTypeId").val("");
	    $("#showtw").val("");
	    
		var names = hNames.split(",");
		var ids = hIds.split(",");
	
	    orgNamesId = [];
	    orgNamesInfo = [];
	    orgNamesId1 = [];
	    
	    for (i = 0; i < names.length; i++) {
	        var name = names[i];
	        var orgNameInfo = new Array();
	        var orgNameId1 = new Array();
	        var commonTypeStr = ids[i] + "_" + name;
	
	        var temp = commonTypeStr.split("_");
	        var temp1 = temp[0];
	
	        orgNamesId.push(temp[0]);
	        orgNameInfo.push(temp[1]);
	        orgNameId1.push(ids[i]);
	   
	        orgNamesInfo.push(orgNameInfo);
	        orgNamesId1.push(orgNameId1);
	    }
	    userNamesInfo.splice(0,userNamesInfo.length);//清空数组 
	    setOrg(ids);
	}
	win_w = "600px";
	win_h = "300px";
	add_cont = $('#layercontent').html();
	$('#layercontent').remove();
	
	win_w1 = "1000px";
	win_h1 = "700px";
	add_user_cont = $('#layercontent1').html();
	$('#layercontent1').remove();
	
</script>
</html>