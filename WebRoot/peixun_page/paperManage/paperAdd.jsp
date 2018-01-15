<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/peixun_page/top.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/addPaper.js"></script>
		<script type="text/javascript" src="${ctx}/js/popupcheckbox6.js"></script>
		<script type="text/javascript" src="${ctx}/js/check.js"></script>
		
		<title>培训后台</title>
		<style type="text/css">
	        #pageModes div {
	            display: none;
	        }
    	</style>
	</head>


<body>
<!-- 内容 -->
<form id="fm1" name="fm1" method="post">
<div class="center" style="">
	<div class="ks_biaoti">
		<span class="ml20">新增试卷</span>
	</div>
	<div class="kc_xinzeng">
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span style="color:#333;"><i style="color:red;">*</i>试卷名称：</span>
				<input type="text" id = "paperName" name = "name" style="width:300px;"/>
			</p>
			<p class="fl" style="margin-left:100px;">
				<span style="color:#333;"><i style="color:red;">*</i>选择目录：&nbsp;</span>
					<input type="hidden" id="curTypeId" name ="typeId" />
					<input type="hidden" id="typeNames" name="typeName"/>
					<div  id="typeNames01" class="duouan" style="align:'center';valign:'middle';width:300px;"></div>
			</p>
		</div>
		<div class="mt10 tiaojian" style="min-height:40px;">
			<p class="fl">
				<span style="color:#333;"><i style="color:red;">*</i>试卷数量：</span>
				     <input name="childPaperNum" type="text" class="input_e" id="childPaperNum" style="width:300px;" value="1"/>
				<span style="color:#333;">（要小于50）</span>
			</p>
			<p class="fl" style="margin-left:18px;">
				<span style="color:#333;"><i style="color:#fff;;">*</i>试卷导出：</span>
				<div class="fl select" style="width:300px;" >
				<div class="tik_position" style="width:300px;">
					<b class="ml5" >请选择</b>
					<p class="position01" ><i class="bjt10" ></i></p>
				</div>
				<ul class="fl list" style="display:none;width:300px;">
					<select name="isNotExp" class="fl select" style="display:none;">
						<option value="-1">请选择</option>
						<option value="1">是</option>						
						<option value="0">否</option>
					</select>
					<li>请选择</li>
					<li>是</li>
					<li>否</li>
					</ul>
				</div>
		</div>
		<div class="mt10 tiaojian" style="min-height:40px;">
			<p class="fl">
				<span style="color:#333;"><i style="color:red;">*</i>组卷策略：</span>
				<div class="fl select" style="width:300px;" >
				<div class="tik_position" style="width:300px;">
					<b class="ml5" id="paperModeSelect">请选择</b>
					<p class="position01" ><i class="bjt10" ></i></p>
				</div>
				<ul class="fl list" style="display:none;width:300px;"  onclick="javascript:foo($(this).children().eq(0));">
					<select name="paperMode" id = "paperMode" class="fl select" style="width:300px;display:none;">
						<option value="-1">请选择</option>
						<option value="1">快捷策略</option>						
						<option value="2">精细策略</option>
						<option value="3">手工组卷</option>
						<option value="4">卷中卷</option>
					</select>
					<li>请选择</li>
					<li>快捷策略</li>
					<li>精细策略</li>
					<li>手工组卷</li>
					<li>卷中卷</li>
				</ul>
				</div>
			</p>
			<div class="fl" style="margin-left:20px;margin-top:8px;">
				<!-- <input type="button" value="策略说明" class="button" style=" margin-left:20px;border:1px solid #dddddd;color:#333; "/> -->
				<a href="javascript:void(0);" class="button" style="border:1px solid #dddddd;color:#333;">策略说明</a>
			</div>
		</div>
	</div>
</div>
<div id="pageModes" >
<!-- manual mode -->
<input type="hidden" id="questType_2" name="questType_2" value="1"/>

<!-- questCommonTypes  题库IDs -->
<input type="hidden" id="questCommonTypeIds" name="questCommonTypeIds" value = "1"/>
<input type="hidden" id="questionLabelIds" name="questionLabelIds"/>
<input type="hidden" id="questionLabelId" name="questionLabelId" value="1"/>
<!-- 三级学科IDs -->
<input type="hidden" id="questionStudyIds" name="questionStudyIds"/>
<!-- 三级学科names_ids -->
<input type="hidden" id="questionStudyNameIds" name="questionStudyNameIds"/>
<!-- 试题认知IDs -->
<input type="hidden" id="questionCognizeIds" name="questionCognizeIds"/>
<!--试题来源IDS  来源选择框确定后保存所有的来源ID和名字存入数据库-->
<input type="hidden" id="questionSourceIds" name="questionSourceIds"/>
<input type="hidden" id="questionSourceNames" name="questionSourceNames"/>
<!-- 适用级别IDs -->
<input type="hidden" id="questionTitleIds" name="questionTitleIds"/>
<input type="hidden" id="seleQues" name="seleQues"/>
<input type="hidden" id="papersIds" name="papersIds"/>
<input type="hidden" id="midinfo" name="midinfo"/>
<input type="hidden" id="paperScore" name="paperScore"/>
<input type="hidden" id="questionNum" name="questionNum"/>

<!-- 只用于存模版 -->
<input type="hidden" id="questionTypeNames" name="questionTypeNames"/>
<input type="hidden" id="questionStudyNames" name="questionStudyNames"/>
<!-- 难度 -->
<input type="hidden" id="questionCognizeNames" name="questionCognizeNames"/>
<!-- 适用级别 -->
<input type="hidden" id="questionTitleNames" name="questionTitleNames"/>
<input type="hidden" id="questionLabelNames" name="questionLabelNames"/>
<!-- 试题分组 -->
<input type="hidden" id="questionGroup" name="questionGroup" value="12000"/>
<!-- 结束 -->
<input type="hidden" id="tacticEditFlag" name="tacticEditFlag" value="0"/>

<!-- 快捷策略 -->
<div id="paperMode_1" class="center">
	<div class="ks_biaoti">
		<span class="ml20">快捷策略</span>
	</div>
	<div class="kc_xinzeng">
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span style="color:#333;"><i style="color:red;">*</i>选择学科 ： </span>
				<input type = "hidden" id = "propIds" name = "propIds" class = "prop"/>
				<div style = "width:300px;height:30px;text-align" class="duouan" id="1txtInd"></div>
			<p class="fl" style="margin-left:80px;">
				<span style="color:#333;">认知水平 &nbsp;:&nbsp;</span>
				<input type = "hidden" id = "levelIds" name = "levelIds" class = "prop"/>
				<input type = "hidden" id = "levelNames" name = "levelNames" class = "prop"/>
				<div style = "width:300px;height:30px;" class="duouan" id="1txtInd4"></div>
       </div>
       	<div class="mt20 tiaojian" style="min-height:40px;">
			<p class="fl ml10" >
				<span style="color:#333;">试题来源 :&nbsp;&nbsp;</span>
				<input type = "hidden" id = "laiIds" name = "laiIds" class = "prop"/>
				<input type = "hidden" id = "laiNames" name = "laiNames" class = "prop"/>
				<div style = "width:300px;height:30px;" id="1txtInd5" class="duouan"></div>
			<p class="fl" style="margin-left:35px;">
				<span style="color:#333;">适用级别与难度 &nbsp;:&nbsp;</span>
				<div class="fl select" style="width:300px;" >
					<div class="tik_position" style="width:300px;">
						<b class="ml5" >请选择</b>
						<p class="position01" ><i class="bjt10" ></i></p>
					</div>
					<ul class="fl list" style="display:none;width:300px;">
						<select name="grade" id="grade" class="fl select" style="width:300px;display:none;">
							<option value="-1">请选择</option>
							<option value="1">初级</option>						
							<option value="2">中级</option>
							<option value="3">高级</option>
						</select>
						<li>请选择</li>
						<li>初级</li>						
						<li>中级</li>
						<li>高级</li>
					</ul>
				</div>
       </div>
		<div class="mt30 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="javascript:void(0);" class="fl" id="addExamType-link" style="width:80px;">选择题型</a>
				<a href="javascript:void(0);" class="fl" id="init-examTypeNum" style="width:80px;margin-left:10px;">清空</a>
			</div>
		</div>
		<div class="clear"></div>
		<table id="questionLabelList" class="mt10 table">
			<tr class="tr">
				<th width="30%">题型</th>
				<th width="30%">可选择题量</th>
				<th width="30%">本次选题量</th>
				<th width="10%">每题分数</th>
			</tr>
			<tr align="center" valign="middle">
                    <td colspan="4" id="labeltd">--请选择题型--</td>
             </tr>
		</table>		
    <div class="cas_anniu" style="margin-top:70px;margin-left:300px;">
    
			<a href="javascript:void(0);" id="paperMode_1_submit" class="fl queren" style="width:70px;;margin-left:40px;">确认</a>
			<a href="${ctx}/paperManage/paperList.do" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
	</div>
	</div>
</div>
<!-- 精细策略 -->
<div id="paperMode_2">
    <div class="ks_biaoti">
		<span class="ml20">精细策略</span>
	</div>
	<div class="kc_xinzeng">
		<div class="tiaojian" style="min-height:40px;">				
			<p class="fl">		
				<span style="color:#333;"><i style="color:red;">*</i>选择学科 ： </span>
				<input type = "hidden" id = "page2propIds" name = "page2propIds" class ="prop"/>
				<input type = "hidden" id = "page2propNames" name = "page2propNames" class ="prop"/>
				<div name="1txtInd" class="duouan" style = "width:300px;height:30px;" id="page2typeNames" readonly="readonly"></div>
			</p>
			<p class="fl" style="margin-left:80px;">
				<span style="color:#333;">认知水平 &nbsp;:&nbsp;</span>
				<input type = "hidden" id = "page2levelIds" name = "page2levelIds" class ="prop"/>
				<input type = "hidden" id = "page2levelNames" name = "page2levelNames" class ="prop"/>
				<div name="1txtInd" class="duouan" style = "width:300px;height:30px;" id="page2levels" readonly="readonly"></div>
			</p>
        </div>
       	<div class="mt20 tiaojian" style="min-height:40px;">
			<p class="fl">
				<span style="color:#333;">&nbsp;试题来源 &nbsp;:&nbsp;&nbsp;</span>
				<input type = "hidden" id = "page2laiIds" name = "page2laiIds" class ="prop"/>
				<input type = "hidden" id = "page2laiNames" name = "page2laiNames" class ="prop"/>
				<div name="1txtInd" class="duouan" style = "margin-right:2px;width:300px;height:30px;" id="page2lais" readonly="readonly"></div>
			</p>
			<p class="fl" style="margin-left:35px;">
				<span style="color:#333;">适用级别与难度 &nbsp;:&nbsp;</span>
				<div class="fl select" style="width:300px;" >
					<div class="tik_position" style="width:300px;">
						<b class="ml5" >请选择</b>
						<p class="position01" ><i class="bjt10" ></i></p>
					</div>
					<ul class="fl list" style="display:none;width:300px;">
						<select name="page2grade" id="page2grade" class="fl select" style="width:300px;display:none;">
							<option value="-1">请选择</option>
							<option value="1">初级</option>						
							<option value="2">中级</option>
							<option value="3">高级</option>
						</select>
						<li>请选择</li>
						<li>初级</li>						
						<li>中级</li>
						<li>高级</li>
					</ul>
				</div>
			</p>
        </div>
       	<div class="mt20 tiaojian" style="min-height:40px;">				
				<div class="mt30 kit1_tiaojia">
					<div class="fr cas_anniu">
						<a href="javascript:void(0);" class="fl" id="addExamType-link2" style="width:80px;">选择题型</a>
						<a href="javascript:void(0);" class="fl" id="init-examTypeNum2" style="width:80px;margin-left:10px;">清空</a>
					</div>
				</div>
				<div class="clear"></div>
				<table id="questionLabelList2" class="mt10 table" style="margin-top:20px;">
					<tr class="tr">
						<th width="30%">题型</th>
						<th width="30%">可选择题量</th>
						<th width="20%">本次选题量</th>
						<th width="20%">每题分数</th>
					</tr>
					<tr align="center" valign="middle">
		                <td colspan="4" id="labeltd2">--请选择题型--</td>
		            </tr>
				</table>
        </div>
        <div class="clear"></div>
        <div class="thi_shitineirong ca1_jichu" style="width:730px;margin-left:0px;">
			<table id="stuNames" class="mt10 table" style="margin-top:35px;">
				<tr class="tr">
					<th width="25%">学科</th>
					<th width="35%">请分配内容比例</th>
					<th width="40%">说明</th>				
				</tr>			
				<tr align="center" valign="middle">
                   <td colspan="7" id="stu_td">--请选择学科--</td>
                </tr>				
			</table>
	     </div>
	     <div class="cas_anniu" style="margin-top:40px;margin-left:300px;">
				<a href="javascript:void(0);" id="mode_quick_submit" class="fl queren" style="width:70px;">确认</a>
				<a href="${ctx}/paperManage/paperList.do" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
		 </div>
		 <div class="clear"></div>
	</div>
</div>

<!-- 手工组卷 -->
<div id="mode_handmade" style="margin-top:20px; margin-right:95px;">
	<div class="ks_biaoti" style="margin-left:50px;">
		<span class="ml20">手工组卷</span>
	</div>
	<div id="mode_handmade_cont_t"  class="kc_xinzeng" >
		<div style="width:1100px;min-height:50px;">
			<p class="fl lc_shengcheng">
				<span style="width:60px">题型 :&nbsp;</span>
				<select id="question_label_id" name="question_label_id" class="lc_list" style="width:150px">
		        	  <option value="1">单选题(A1)</option>
		        	  <option value="2">单选题(A2)</option>
		        	  <option value="3">单选题(A3/A4)</option>
		        	  <option value="9">单选题(B1</option>
		        	  <option value="11">多选题(X)</option>
		        	  <option value="12">填空题</option>
		        	  <option value="13">判断题</option>
		        	  <option value="14">简答题</option>
		        	  <option value="18">名词解释</option>
		        	  <option value="20">案例分析题</option>     
      	        </select>
				<span style="width:100px">学科 :&nbsp;</span>
				<input type = "hidden" id = "renderSelIds" name = "renderSelIds" class ="prop"/>
				<textarea  readonly id="3txtInd" class="duouan" style="width:150px;height:30px"></textarea>

				<span style="width:100px">认知水平 :&nbsp;</span>
				<input type = "hidden" id = "questCognize_8_2" name = "questCognize_8_2"  class ="prop"/>
				<textarea readonly id="3txtInd8" class="duouan" style="width:150px;height:30px"></textarea>

				<span style="width:100px">职称 :&nbsp;</span>
				<input type="hidden" id="zhichengId" name ="questTitle_9_2"  class ="prop"/>
				<textarea readonly id="3txtInd9" name="3txtInd9" class="duouan" style="width:150px;height:30px"></textarea>
				
    		</p>
       	</div>
		<div style="width:1100px;min-height:50px;">
			<p class="fl cas_anniu lc_shengcheng">
				<span style="width:60px">内容 :&nbsp;</span>
				<input type="text" id="txtIndDrop4_hand" name="content" style="width:150px" />

				<span style="width:100px">每题分数 : &nbsp;</span>
				<input type="text" id="quesScor" name="quesScor" style="width:150px" value=""/>
				
				<span style="width:100px">试题数量 : &nbsp;</span>
				<input type="text" id="3questionNum" style="width:150px" value=""/>
			</p>
			<p class="fl cas_anniu" style="margin-left:100px">
				<a href="javascript:void(0);" id="sele-link-hand" class="fl queren" style="margin-top:2px;width:80px;">搜索试题</a>
			</p>

      	</div>

		<div class="clear"></div>
		<div class="kch_chelv" style="width:1000px;display:none;" id="handmode_question">
			<div>
				<p class="fl cas_anniu lc_shengcheng">
					<span style="width:110px">已选总题量 :&nbsp;</span>
					<input type="text" readonly id="_hand_quesNum" style="width:100px" value="0" />
					<input type="hidden" readonly id="hand_quesNum" style="width:100px" value="0" />
	
					<span style="width:100px">已选总分数 : &nbsp;</span>
					<input type="text" readonly id="_hand_quesSocr" style="width:100px" value="0" />
					<input type="hidden" readonly id="hand_quesSocr" style="width:100px" value="100" />
				</p>
			</div>
			<div class="clear"></div>
			<div class="mt10">
				<table class="table" id="handmode_question_table">
					<tr class="tr">
						<!-- <th width="10%">序号</th> -->
						<th width="15%">题型</th>
						<th width="50%">内容</th>
						<th width="10%">分数</th>
						<th width="15%">操作</th>
					</tr>
				</table>
			</div>
			<div class="cas_anniu" style="margin-top:20px;margin-left:400px;">
				<a href="javascript:void(0);" id="submit-link-hand" class="fl queren" style="width:70px;">确认</a>
				<a href="${ctx}/paperManage/paperList.do" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>		

 	</div>
</div>

<!-- 卷中卷 -->
<div id="mode_mid" class="center">
		<div class="ks_biaoti">
			<span class="ml20">卷中卷</span>
		</div>
	    <div class="mt10">
			<p class="fr cas_anniu">
				<a href="javascript:void(0);" class="fl" id="selPaper-link" style="width:80px;">选择试卷</a>
			</p>
		</div>	
    	<div class="clear"></div>
	    <table id="paperInfo" class="mt10 table center" class="table_a">
			<tr class="tr table_a_title" id="lastTr" align="center">
				<th width="20%">试卷名称</th>
				<th width="20%">组卷方式</th>
				<th width="12%">试卷分数</th>
				<th width="12%">试卷题量</th>
				<th width="20%">创建时间</th>
				<th width="16%">操作</th>
			</tr>
			<tr id="paperInfo_temp">
	               <td colspan="6" align="center">请选择试卷，最多可选中10张试卷！</td>
	        </tr>
	    </table>
    	<table id="paperInfo2" class="mt10 table center" class="table_a">
	        <tr class="table_a_title" id="lastTr2" align="center">
	            <th width="30%">题型列表</th>
	            <th width="30%">可选试题数量</th>
	            <th width="30%">本次选题量</th>
	            <th width="10%">每题分数</th>
	        </tr>
	        <tr id="labelList_temp">
	               <td colspan="6" align="center">目前没有选择试题！</td>
	        </tr>
		</table>
		<div class="cas_anniu" style="margin-top:70px;margin-left:750px;">
				<a href="javascript:void(0);" id="submit-link-mid" class="fl queren" style="width:70px;">确认</a>
				<a href="${ctx}/paperManage/paperList.do" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
		</div>
	</div> 
</div>
</form>

<!-- 策略说明弹框 -->
<div class="toumingdu make1" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti" style = "cursor:default;">
			<span class="fl" style="margin-left:290px;color:#fff;">策略说明</span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="kch_chelv">
			<div class="kch_shuoming">
				<h2>快捷策略</h2>
				<p>根据设定条件，由系统自动选题生成试卷，同一策略支持生成多套试卷。</p>
			</div>
			<div class="kch_shuoming">
				<h2>精细策略</h2>
				<p>可以根据内容所属学科进行取题百分比设定，同一策略支持生成多套试卷。</p>
			</div>
			<div class="kch_shuoming">
				<h2>手工组卷</h2>
				<p>手工组卷：设置试题查询条件，系统提供符合条件的试题，用户手工选择试题生成试卷，每次只生成一套试卷。</p>
			</div>
			<div class="kch_shuoming">
				<h2>卷中卷</h2>
				<p>用户选择已经生成好的一套或多套试卷，系统从已有试卷中选择试题组成试卷，每次只生成一套试卷。</p>
			</div>
			<div class="kch_shuoming">
				<h2>提示</h2>
				<p>手工组卷和卷中卷试卷数量固定为1。</p>
			</div>
		</div>
	</div>	
</div>
<script type="text/javascript">
var basePath = "${ctx}";


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
		
		$(document).click(function(){
		    $('.list').hide('fast');
	    });	
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.make1').hide();
		});
		$('#typeNames01').click(function(){
			initPropList("选择目录","${ctx}/paperManage/getPaperListType.do",-1,0,5,0,$('#curTypeId'),$('#typeNames01'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('#1txtInd').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#1txtInd'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('#1txtInd4').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 5, 3, $('#levelIds'), $('#1txtInd4'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('#1txtInd5').click(function(){
			initPropList("来源","${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#laiIds'), $('#1txtInd5'));
			$('.att_make01').show();
		});
		
		$('#page2typeNames').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#page2propIds'), $('#page2typeNames'));
			$('.att_make01').show();
		});
		$('#page2levels').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 5, 3, $('#levelIds'), $('#page2levels'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('#page2lais').click(function(){
			initPropList("来源","${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#page2laiIds'), $('#page2lais'));
			$('.att_make01').show();
		});
		$('#3txtInd').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#renderSelIds'), $('#3txtInd'));
			$('.att_make01').show();
		});
		$('#3txtInd8').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#questCognize_8_2'), $('#3txtInd8'));
			$('.att_make01').show();
		});
		$('#3txtInd9').click(function(){
			initPropList("职称","${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 0, $('#zhichengId'), $('#3txtInd9'));
			$('.att_make01').show();
		});
		
		$("#childPaperNum").change(function(key, val){
			var patrn = /^[0-9]{1}[0-9]{0,2}$/;
  			var childPaperNum = $("#childPaperNum").val();
  			if (testDemo(patrn, childPaperNum)) {
	  			 if (childPaperNum > 50) {
	     			 alert("试卷数量不能大于50！");
	     			 $(this).val(1);
	      			 return false;
	    		 }
  			} else {
  				alert("试卷数量至少大于1，小于等于50！");
  				$(this).val(1);
   				return false;
  			}
		});

		$('.queren').click(function(){
			$('.make02').hide();
		});
		$('.button').click(function(){
			$('.make1').show();
		});
});
//加载模版
initTemplate();
function initTemplate() {
    if ('${xxxxx}' != '') {
        $("#commonTypeId").val('${paperTemplate.paperTypeId}');
        $("#showtw").val('${paperTemplate.paperTypeName}');
        if ('${paperTemplate.isnotViewScore}' == 1) {
            $("input[name='examTestpaper.isnotViewScore'][value='1']").attr("checked", true);
        } else {
            $("input[name='examTestpaper.isnotViewScore'][value='0']").attr("checked", true);
        }
        if ('${paperTemplate.isnotExpPaper}' == 1) {
            $("input[name='examTestpaper.isnotExpPaper'][value='1']").attr("checked", true);
        } else {
            $("input[name='examTestpaper.isnotExpPaper'][value='0']").attr("checked", true);
        }
        $("#childPaperNum").val('${paperTemplate.childPaperNum}');
        if ('${paperTemplate.paperMode}' == 1) {
            $("#paperMode").val(1);
            showTemplateMode(1);
            initHiddenkj();
        }
        if ('${paperTemplate.paperMode}' == 2) {
            $("#paperMode").val(2);
            showTemplateMode(2);
            initHiddenjx();
        }
        initTitleAndLvl();
    }
}

//展开策略
function showTemplateMode(modeId) {
    if (modeId == 1) {
        //显示填写详细内容部分
        $("#paperMode_1").slideDown("slow", function () {
            $("#paperMode_1").find("div").css("display", "block");
        });
    }
    if (modeId == 2) {
        $("#paperMode_2").slideDown("slow", function () {
            $("#paperMode_2").find("div").css("display", "block");
        });
    }
}

//初始化快接策略
function initHiddenkj() {
    $("#questCommonTypeIds").val('${paperTemplate.questionTypeId}');
    $("#1txtInd").val('${paperTemplate.questionTypeName}');
    $("#questionStudyIds").val('${paperTemplate.questionStudyId}');

    var studyname = '${paperTemplate.questionStudyName}';
    var questionTitleName = '${paperTemplate.questionTitleName}'
    $("#txtLocc").val(formatStr(studyname));
    $("#questionCognizeIds").val('${paperTemplate.questionCognizeId}');
    $("#1txtInd4").val('${paperTemplate.questionCognizeName}');
    $("#questionSourceIds").val('${parperTemplate.questionSourceId}');
    $("#1txtInd5").val('${paperTemplate.questionSourceName}');
    $("#questionTitleIds").val('${paperTemplate.questionTitleId}');
    $("textarea[name='titleText']").text(formatStr(questionTitleName));
    $("#questionLabelIds").val('${paperTemplate.questionLabelId}');
    $("#1txtInd2").val('${paperTemplate.questionLabelName}');
    $("#seleQues").val('${paperTemplate.seleQues}');

    $("#questionStudyNameIds").val('${paperTemplate.questionStudyNameId}')
    $("#questionNum").val('${paperTemplate.questionNum}');
    $("#paperScore").val('${paperTemplate.paperScore}');
    selectedMode = "1";
}
//初始化精细策略
function initHiddenjx() {
    $("#questCommonTypeIds").val('${paperTemplate.questionTypeId}');
    $("#txtInd").val('${paperTemplate.questionTypeName}');
    $("#questionStudyIds").val('${paperTemplate.questionStudyId}');

    var studyname = '${paperTemplate.questionStudyName}';
    var questionTitleName = '${paperTemplate.questionTitleName}';

    $("#txtLoccc").val(formatStr(studyname));

    $("#questionCognizeIds").val('${paperTemplate.questionCognizeId}');
    $("#txtInd4").val('${paperTemplate.questionCognizeName}');
    $("#questionSourceIds").val('${parperTemplate.questionSourceId}');
    $("#txtInd5").val('${paperTemplate.questionSourceName}');
    $("#questionTitleIds").val('${paperTemplate.questionTitleId}');
    $("textarea[name='titleText']").text(formatStr(questionTitleName));
    $("#questionLabelIds").val('${paperTemplate.questionLabelId}');
    $("#txtInd2").val('${paperTemplate.questionLabelName}');
    $("#seleQues").val('${paperTemplate.seleQues}');

    $("#questionStudyNameIds").val('${paperTemplate.questionStudyNameId}');

    var html3 = [];
    var studyNameIds = '${paperTemplate.questionStudyNameId}';
    studyNamesIds = studyNameIds.split(",");
    for (i = 0; i < studyNamesIds.length; i++) {

        var studyName_id = studyNamesIds[i].split("_");
        var id_Str = studyName_id[0];
        var name_String = studyName_id[1];

        html3.push("<tr class='tr_loc'>");
        html3.push("<td id='td1_loc");
        html3.push(id_Str);
        html3.push("'>");
        html3.push(name_String);
        html3.push("</td>");
        html3.push("<td id='td2_loc");
        html3.push(id_Str);
        html3.push("'>");
        html3.push("<input onfocus='checkTotalbfb();' onblur='checkTotalbfb();' class='input_loc' size='10' type='text' id='");
        html3.push(id_Str);
        html3.push("'>");
        html3.push("%");
        html3.push("</td>");
        html3.push("<td id='td3_loc");
        html3.push(id_Str);
        html3.push("'>");
        html3.push("最多可分配");
        html3.push("<span class='span_loc' id='span_loc_");
        html3.push(id_Str);
        html3.push("'>");
        html3.push("</span> ");
        html3.push(" %");
        html3.push("</td>");
        html3.push("</tr>");
    }
    html3.push("<tr class='tr_loc'>");
    html3.push("<td>");
    html3.push("汇总：");
    html3.push("</td>");
    html3.push("<td>");
    html3.push("<input type='text' id='totalbfb' value='0' size='10' readonly>%");
    html3.push("</td>");
    html3.push("</tr>");
    var lastTr = jQuery("#txtLoc").find("tr").last();
    lastTr.after(html3.join(""));

    //写入百分比
    var data = countQuesTemp('');
    writeCountTemp(data);

    $("#questionNum").val('${paperTemplate.questionNum}');
    $("#paperScore").val('${paperTemplate.paperScore}');
    selectedMode = "2";
}

//初始化试用级别和难度
function initTitleAndLvl() {
    <c:forEach items="${ept3List}" var="title">
    <c:forEach items="${title.searchLvls}" var="lvl">
    var val = ${title.titleId}+'_' +${lvl};
    $("input[value='" + val + "'][type='checkbox']").attr("checked", true);
    </c:forEach>
    </c:forEach>
}

function writeCountTemp(data) {
    var percentAll = 0;
    var object = eval('(' + data + ')');
    $(".span_loc").each(function () {
        var idstr = this.id;
        var index = idstr.indexOf('loc_');
        var ids = idstr.substr(index + 4, idstr.length);
        $(this).text(object[0][ids]);
        percentAll += object[0][ids];
    });
    //百分比之和小于100时
    if (percentAll < 100) {
        alert("试题以不满足，请重新选择！");
        clearStudysContentTemp();
        $("#btnCheckAll").hide();
        $("#p").hide();
        $("#questionNum").val("");
        $("#paperScore").val("");
        $("#txtIndDrop2").click();
    }
}
function clearStudysContentTemp() {
    $("#questionLabelIds").val("");
    $("#txtInd2").val("");
    $(".tr_loc").each(function () {
        $(this).detach();
    });
}
function countQuesTemp(flag) {
    var rd;
    $.ajax({
        type: "post",
        data: "questCommonTypeIds=" + $("#questCommonTypeIds").val()
                + "&questionCognizeIds=" + $("#questionCognizeIds").val()
                + "&questionTitleIds=" + $("#questionTitleIds").val()
                + "&questionStudyIds=" + $("#questionStudyIds").val()
                + "&questionLabelIds=" + $("#questionLabelIds").val()
                + "&questionSourceIds" +$("#questionSourceIds").val()+ "&flag="
                + flag,
        url: "manage/examQuestion/getQuestionCount.do",
        async: false,
        cache: true,
        success: function (rdata) {
            rd = rdata;
        },
        error: function () {
            alert("error");
            return;
        }
    });
    return rd;
}
function formatStr(nameStr) {
    var resTr = "";
    for (i = 0; i < nameStr.split(",").length; i++) {
        if (i != (nameStr.split(",").length - 1)) {
            resTr += nameStr.split(",")[i] + "\r\n";
        } else {
            resTr += nameStr.split(",")[i];
        }
    }
    return resTr;
}

$(window).height(); //获取浏览器显示区域的高度
$(window).width();//获取浏览器显示区域的宽度
$(document.body).height();//获取页面的文档高度
$(document.body).width();//获取页面的文档宽度
$(document).scrollTop();//获取滚动条到顶部的垂直高度
$(document).scrollLeft();//获取滚动条到左边的垂直宽度
//这是快捷策略试题来源的方法
$('#1txtIndDrop5').click(function(){
    $("#1txtIndDrop5dialog").dialog('open');//这是快捷策略单击的时候弹出对话框
    $("#1txtIndDrop5dialog").panel("move",{top:$(document).scrollTop() + ($(window).height()-250) * 0.5});//让弹出框居中
    jQuery("#mask").height(jQuery(document).height()).show();//显示遮盖效果
});
//这是精细策略试题来源的方法
$('#txtIndDrop5').click(function(){
    $("#txtIndDrop5dialog").dialog('open');//这是精细单击的时候弹出对话框
    $("#txtIndDrop5dialog").panel("move",{top:$(document).scrollTop() + ($(window).height()-250) * 0.5});//让弹出框居中
    jQuery("#mask").height(jQuery(document).height()).show();//显示遮盖效果
});

/* function viewQuest(id){
	var url = "${ctx}/paperManage/viewQuestion.do?id="+id+"&handle=v";
	var bHeight = $(window).height()-100;
	var bWidth = $(window).width()-200;
	window.open(url, "viewQuestion", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
} */
</script>
</body>
</html>