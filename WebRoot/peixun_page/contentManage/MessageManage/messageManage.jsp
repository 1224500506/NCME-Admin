<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>

<%@include file="/commons/taglibs.jsp"%>
<style type="text/css">
	  table{
            border-collapse: collapse;
            margin:0 auto;
          }
          td{
          	font-size: 20px;
          }

</style>
</head>

<%@include file="/peixun_page/top.jsp"%>

<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script> 
<body>
<form name="Form" id="Form" action="${ctx}/contentManage/messageManage.do?method=list" method="post">

<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	
	<!--xiaoxi 标题 -->
        <p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">消息内容：</span>
			<input type="text" id = "" name = "model.content" value="${model.content}" />
		</p>
		
		<!--分类 -->
 	    <p class="fl" style="margin:0 0 10px 0">
 			<span style="width:5em;text-align:right;">消息类型：</span>
 		</p>
	 	<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
	 		<select class="fl select" style="display:none;" name="model.messageType" value="">
	 			<option value='0' <c:if test="${model.messageType == 0}">selected</c:if> >全部</option>
		 		<option value='1' <c:if test="${model.messageType == 1}">selected</c:if> >版本更新</option>
				<option value='2' <c:if test="${model.messageType == 2}">selected</c:if> >项目更新</option>
	 			<option value='3' <c:if test="${model.messageType == 3}">selected</c:if> >项目下线</option>
	 			<option value='4' <c:if test="${model.messageType == 4}">selected</c:if> >活动通知</option>
	 			<option value='5' <c:if test="${model.messageType == 5}">selected</c:if> >新闻通知</option>
	 		</select>
	 		 <li>全部</li>
			 <li>版本更新</li>
			 <li>项目更新</li>
			 <li>项目下线</li>
			 <li>活动通知</li>
			 <li>新闻通知</li>
			 </ul>
	 	</div>
		
		
		
		<!--消息管理时间段 -->
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">发布时间：</span>
			<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择开始时间！" value="${model.start_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'start_date\')}'})"/>
			<span>--</span>
			<input type="text" name="end_date" id="end_date" class="editInput"
				datatype="*" nullmsg="请选择结束时间！"  value="${model.end_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'end_date\')}'})" />
			
		</p>
		
		<!--banner状态 -->
	 	<div class="clear"></div>
	    <p class="fl" style="margin:0 0 10px 0">
	 		<span style="width:5em;text-align:right;">状态：</span>
	 		</p>
	 	<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
	 		<select class="fl select" style="display:none;" name="model.sendState" value="">
		 		<option value='0' <c:if test="${model.sendState == 0}">selected</c:if> >全部</option>
		 		<option value='1' <c:if test="${model.sendState == 1}">selected</c:if> >未发送</option>
				<option value='2' <c:if test="${model.sendState == 2}">selected</c:if> >已发送</option>
	 		</select>
	 		 <li>全部</li>
			 <li>未发送</li>
			 <li>已发送</li></ul>
	 	</div>
	 	
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="javascript:$('#Form').submit();" class="fl" style="width:70px;margin-bottom:10px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a class="fr" style="width:140px;" href="${ctx}/contentManage/messageManage.do?method=add">添加消息</a>
		</p>
		<p class="fr cas_anniu">
			<a href="javascript:delBatch();" class="fl" style="width:80px;margin-left:10px;">批量删除</a>
		</p>
		
	</div>
	
</div>
</form>
<div class="clear"></div>
<div class="bjs"   ></div>
 

<div class="center none" style="">
	<div class="tk_center01">
			
		<display:table name="list" requestURI="${ctx}/contentManage/messageManage.do?method=list" id="p" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" 
				 class="mt10 table"   >
            <display:column title="<input type='checkbox' class='chkall' name='selectall'/>" style="width:4%;">
            	<input type='checkbox' class='chk' value="${p.id}" name='stuCheckBox'/>
            </display:column>    
            
			<display:column title="序号" style="width:5%;text-align:center">
				${p_rowNum}
			</display:column>			
			<display:column  title="消息类型" style="width:5% ">
			    <c:if test="${p.messageType == 1}">版本更新</c:if> 
				<c:if test="${p.messageType == 2}">项目更新</c:if>
				<c:if test="${p.messageType == 3}">项目下线</c:if> 
				<c:if test="${p.messageType == 4}">活动通知</c:if>
				<c:if test="${p.messageType == 5}">新闻通知</c:if>
			</display:column> 
			<display:column property="title" title="消息标题"  style="width:10%" escapeXml="true"  maxLength="40"> </display:column>
		    
		    <display:column property="content" title="消息内容"  style="width:10%" escapeXml="true"  maxLength="40"> 
		    </display:column>
			
			<display:column property="receiver" title="接收用户"  style="width:10%" > </display:column>
<!-- 			property="sendTime" -->
			<display:column property="sendTime" title="发送时间"  style="width:10%" format="{0,date,yyyy-MM-dd  HH:mm:ss}"> 
<%-- 				<fmt:formatDate value="${model.sendTime }" pattern="yyyy-MM-dd" /> --%>
			</display:column>
		   	<display:column property="creater" title="操作用户"  style="width:10%" > </display:column>
			<display:column title="状态" style="width:5%">
				<c:if test="${p.sendState == 1}">未发送</c:if> 
				<c:if test="${p.sendState == 2}">已发送</c:if>
			</display:column>
			<input type="text" id = "sendstate" name = "model.sendState" value="${p.sendState}">
			<display:column title="操作" style="width:25%">
			<c:if test="${p.sendState == 2}">
			<a href="javascript:infoView('${p.id}','${p.sendState}');" title="查看" class="btn btn_blue btn_s QueryView">查看</a>
			</c:if>
			<c:if test="${p.sendState == 1}">
			<a href="javascript:edit(${p.id});">编辑</a>
			<a href="javascript:send(${p.id},${p.sendState});">发送</a>
			<a href="javascript:deletemessage(${p.id});">删除</a>
			</c:if>
			</display:column>				
			
		</display:table>
		<div class="clear"></div>
	</div>
</div>

<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">

	<input type="hidden" id="userId" name="feedback.id" value="0" />
	<div class="tiaojian" style="min-height:40px;" align="center">
		<table>
          <tr>
            <td style="text-align:right;font-size: 20px" >消息类型：</td>
            <td><input style="text-align:center;font-size: 20px" type="text" id="messageType" value=""  border="0"  readonly  unselectable="on"></td>
            <td>
            </td>
          </tr>
          <tr>
            <td style="text-align:right;font-size: 20px">消息标题：</td>
            <td><input style="text-align:center;font-size: 20px" type="text" id="title" value=""  readonly  unselectable="on"><td>
            <td>
            </td>
          </tr>
          <tr>
            <td style="text-align:right;font-size: 20px"> 消息内容：</td>
            <td>
            <textarea style="text-align:center;font-size: 20px" rows="" cols="" id="content" readonly  unselectable="on"></textarea>
<!--             <input style="text-align:center;font-size: 20px" type="text" id="content" value=""  readonly  unselectable="on"></td> -->
            <td>
            </td>
            </tr>
          <tr>
            <td style="text-align:right;font-size: 20px">接收用户：</td>
            <td><input style="text-align:center;font-size: 20px" type="text" id="receiver" value=""  readonly  unselectable="on"></td>
            <td>
            </td>
          </tr>
          <tr >
            <td style="text-align:right;font-size: 20px">发送时间：</td>
            <td><input style="text-align:center;font-size: 20px"  type="text" id="sendTime" value=""  readonly  unselectable="on"></td>
            <td>
            </td>
            </tr>
          <tr>
            <td style="text-align:right;font-size: 20px">操作用户：</td>
            <td><input style="text-align:center;font-size: 20px" type="text" id="creater" value="" readonly  unselectable="on"><td>
            <td>
            </td>
          </tr>
          <tr >
            <td style="text-align:right;font-size: 20px">状态：</td>
            <td><input style="text-align:center;font-size: 20px" type="text" id="sendState" value="" readonly  unselectable="on"><td>
            <td>
            </td>
          </tr>
        </table>
	</div>
</div>
</div>


<script type="text/javascript">
function replySubmit() {
	if($("#reply_content").val().length>400){   //长度可自定义
        alert("超出了标题的最大长度，请控制标题数为200字");
        $("#reply_content").focus();
		return;
    }else{
        //未超出……
    }
}

var msg = "${msg}";
if(msg != "")
{
	alert(msg);
}

/* //[修改] 
function edit(id) {
		document.location.href = "${ctx}/contentManage/feedbackManage.do?method=edit&id="+id;
}
 */

	$(function(){	
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");	
		
	selectInit();
		$('.att_setorder').click(function(){
		setorderNum();
	       });
	       
		$.extend( $.fn.pickadate.defaults, {
		monthsFull: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
		monthsShort: [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
		weekdaysFull: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		weekdaysShort: [ '日', '一', '二', '三', '四', '五', '六' ],
		today: '今日',
		clear: '删',
		firstDay: 1,
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyy-mm-dd'
	});

	// 日期选择控件
	var input = $(".datepicker").pickadate({
		monthSelector: true,
		today:false,
		clear:'清空',
		close:'关闭',
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});
		
		$(document).click(function(){
			$('.list').hide('fast');
		});		
});


	



 function selectInit(){
	$('.select').click(function(){
		$(".list").css("display","none");
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
}

 function send(id,state) {
		if(confirm("确定发送该消息？一旦发送，全部学员可接收此消息，且不可撤回。")){

			var url = "${ctx}/contentManage/messageManage.do";
			var params = "method=sendMessage&id=" + id;
			
			var sendstate = $("#sendstate").val();
			if(sendstate=1 && sendstate!=''){
			$.ajax({
				url	:	url,
				data :	params,
				dataType:"text",
				type: 'POST',
				success: function(result){
					if(result == "success")
					{
						alert("发送成功!");
						window.location.reload(true);
					}
					else
					{
						alert("发送失败!");
					}
				}		
			});	
		}else {
			$.ajax({
				url	:	url,
				data :	params,
				dataType:"text",
				type: 'POST',
				success: function(result){
					if(result == "success")
					{
						alert("发送成功!");
						window.location.reload(true);
					}
					else
					{
						alert("发送失败!");
					}
				}		
			});	
		}
		
	}

 }
function deletemessage(id) {
	/* if(state == 2){
		alert("不能删除已发布的文章！");
		return;
	} */
	if(confirm("删除?")){

		var url = "${ctx}/contentManage/messageManage.do";
		var params = "method=delete&id=" + id;
		
		$.ajax({
			url	:	url,
			data :	params,
			dataType:"text",
			type: 'POST',
			success: function(result){
				if(result == "success")
				{
					alert("删除成功!");
					window.location.reload(true);
				}
				else
				{
					alert("删除失败!");
				}
			}		
		});	
	}
	
}
//判断数组里是否存在重复元素，有重复返回true；否则返回false
function mm(a){
   return /(\x0f[^\x0f]+)\x0f[\s\S]*\1/.test("\x0f"+a.join("\x0f\x0f") +"\x0f");
}
//编辑
function edit(id){
	/* if(state == 2){
		alert("不能编辑已发布的文章！");
		return;
	} */
	document.location.href = "${ctx}/contentManage/messageManage.do?method=edit&id="+id;
}

//#####################################
var win_w = "500px";
var win_h = "400px";
var add_cont = $('#layercontent').html();
$('#layercontent').remove();
$(".QueryView").click(function(){
	layer.open({
		type: 1,
		title: "查看发送详情",
		skin: 'layui-layer-rim', //加上边框
		area: [win_w, win_h], //宽高
		content: add_cont,
		closeBtn: 0,
		btn: ['返回'],
		yes: function (index, layero) {
			//缩写保存功能
			layer.close(index);
		},
		btn2: function (index, layero) {
			layer.close(index);
		},
		success: function(layerb, index){
			$(".btn1").click(function(){
				layer.close(index);
			});
		}
	});
});

//[批量删除] 
function delBatch() {
//	alert("暂不允许删除卡类型！");
//	return;
	var cardTypeIds = $(".chk[type='checkbox']:checked");
	if(cardTypeIds.length == 0){
		alert("请先选择删除的选项!");
	    return;
	}
	var selectedIds = '';
	if(cardTypeIds != '' && cardTypeIds.length >0 ){
		for(var i=0; i<cardTypeIds.length; i++){
			selectedIds += cardTypeIds[i].value + ',';
		}
	}
	if(confirm("确认删除吗?")){
		var p = {'typeIds':selectedIds};
		$.post("${ctx}/contentManage/messageManage.do?method=delete", p,
  			function(data){
  				if(data != "fail"){
  					alert("所选项删除成功!");
  				}
  				else{
  					alert("该选项已被删除，不能删除！");
  				}
  				document.location.href = document.location.href.split("#")[0];
  		});
	}
}



//#####################################

function infoView(id,state){
         	 $.ajax({
				type: 'POST',
				url: "${ctx}/contentManage/messageManage.do?method=showView&id="+id+"&state="+id,
				dataType: 'json',
				success : function(data){
					var format="yyyy-mm-dd hh:mm:ss";
		          	$("#title").val(data.message.title);
	            	$("#content").val(data.message.content);
	            	$("#receiver").val(data.message.receiver);
	            	$("#sendTime").val(timeStampExchange(data.message.sendTime.time));
	            	$("#creater").val(data.message.creater);
	            	$("#sendType").val(data.message.sendType);
					var state ="";
					var sendState=data.message.sendState;
					if (sendState==2) {
						status='已发送';
						$("#sendState").val(status);
					}else {
						state='未发送';
						$("#sendState").val(status);
					}
					
					var messageType=data.message.messageType;
					var type="";
					if (messageType==1) {
						type='版本更新';
						$("#messageType").val(type);
					}else if (messageType==2) {
						type='项目更新';
						$("#messageType").val(type);
					}else if (messageType==3) {
						type='项目下线';
						$("#messageType").val(type);
					}else if (messageType==4) {
						type='活动通知';
						$("#messageType").val(type);
					}else{
						type='新闻通知';
						$("#messageType").val(type);
					}
	            	
	            	
	            },
				error:function(data2){
					alert("数据异常");
				}	  
	    }); 
    }
    

    
//------------时间转换类型-------
 function timeStampExchange(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0"
			+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
			: datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
			: datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute
			+ ":" + second;
} 
 $('input[name="selectall"]').click(function(){  
     //alert(this.checked);  
     if($(this).is(':checked')){  
         $('input[name="stuCheckBox"]').each(function(){  
             //此处如果用attr，会出现第三次失效的情况  
             $(this).prop("checked",true);  
         });  
 }else{  
     $('input[name="stuCheckBox"]').each(function(){  
             $(this).removeAttr("checked",false);  
         });  
         //$(this).removeAttr("checked");  
 }  
       
});  
</script>
</body>

