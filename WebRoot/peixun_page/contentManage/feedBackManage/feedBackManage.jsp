<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	input{
    border: none;
    outline: none;
    
}
  .mian{
	padding:20px;
	}
	.trHeight{
    height: 36px;
    line-height: 36px;
	}
</style>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/peixun_page/contentManage/feedBackManage/css/style.css">
<%@include file="/peixun_page/top.jsp"%>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/peixun_page/contentManage/feedBackManage/js/jquery.min.js"></script>
	<script type="text/javascript" src='${ctx}/peixun_page/contentManage/feedBackManage/js/lightBox.js'></script>
<body>
<form name="Form" id="Form" action="${ctx}/contentManage/feedbackManage.do?method=list" method="post">

<div class="center">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">操作系统：</span>
		</p>
		<div class="fl select"  style="margin:0 15px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
			 	<select class="fl select"  style="display:none;" name="model.system">
			 		<option selected>全部</option>
			 		<option value='windows' >windows</option>
					<option value='IOS' >IOS</option>
					<option value='Android' >Android</option>
					<option value='Mac' >Mac</option>
			 	</select>
			 	
			 <li>全部</li>
			 <li>windows</li>
			 <li>IOS</li>
			 <li>Android </li>
			 <li>Mac</li>
			 </ul>
			 
			 
		</div>
		
        <p class="fl" style="margin:0 15px 10px 0">
			<span style="width:5em;text-align:right;">内容：</span>
			<input type="text" id = "title" name = "model.content" value="${model.content}"/>
		</p>
		
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">反馈时间：</span>
			<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择开始时间！" value="${model.start_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'start_date\')}'})"/>
			<span>--</span>
			<input type="text" name="end_date" id="end_date" class="editInput"
				datatype="*" nullmsg="请选择结束时间！"  value="${model.end_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'end_date\')}'})" />
			
		</p>
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
	 		<select class="fl select" style="display:none;" name="model.state" value="<%=request.getAttribute("state")%>">
		 		<option value='0' <c:if test="${model.state == 0}">selected</c:if> >全部</option>
		 		<option value='1' <c:if test="${model.state == 1}">selected</c:if> >未回复</option>
				<option value='2' <c:if test="${model.state == 2}">selected</c:if> >已回复</option>
	 		</select>
	 		 <li>全部</li>
			 <li>未回复</li>
			 <li>已回复</li></ul>
	 	</div>	
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="javascript:$('#Form').submit();" class="fl" style="width:70px;margin-bottom:10px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a href="javascript:delBatch();" class="fl" style="width:80px;margin-left:10px;">批量删除</a>
		</p>
	</div>
	
</div>
</form>
<div class="clear"></div>
<div class="bjs"   ></div>
 
<!-- 内容 -->

<div class="center none" style="">
	<div class="tk_center01">
			
		<display:table name="list" requestURI="${ctx}/contentManage/feedbackManage.do?method=list" id="p" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" 
				 class="mt10 table"   >
             <display:column title="<input type='checkbox' class='chkall' name='selectall'/>" style="width:4%;">
            	<input type='checkbox' class='chk' value="${p.id}" name='stuCheckBox'/>
            </display:column>   
            
			<display:column title="序号" style="width:5%;text-align:center">
				${p_rowNum}
			</display:column>			
	
			<display:column property="creater" title="用户名"  style="width:10%" > </display:column>
			<display:column property="telphone" title="手机号"  style="width:10%" > </display:column>
		    <display:column property="content" title="反馈内容"  style="width:10%" > </display:column>
			<display:column title="操作系统"  style="width:10%" > 
				${p.system }
			</display:column>
			<display:column  title="操作系统版本"  style="width:10%" >
				${p. systemversion}
			 </display:column>
		   	<display:column  title="授权站点"  style="width:10%" >
		   		${p.site }
		   	 </display:column>
			<display:column title="反馈时间"  style="width:10%" value="" property="backtime" format="{0,date,yyyy-MM-dd  HH:mm:ss}">
			</display:column>
			<display:column title="状态" style="width:5%">
				<c:if test="${p.state == 1}">未回复</c:if> 
				<c:if test="${p.state == 2}">已回复</c:if>
			</display:column>
			<display:column title="操作" style="width:25%">
			<a href="javascript:infoView('${p.id}','${p.state}');" title="查看" class="btn btn_blue btn_s QueryView">查看</a>
				<a href="javascript:deletefeedback(${p.id});">删除</a>
			</display:column>				
			
		</display:table>
		<div class="clear"></div>
	</div>
</div>


<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">

	<input type="hidden" id="userId" name="feedback.id" value="0" />
	<div class="tiaojian" style="min-height:50px;">
		<table>
          <tr class="trHeight">
            <td style="text-align:right;width:100px;" >用户名：</td>
            <td><input type="text" id="creater" value=""  border="0"></td>
            <td style="text-align:right;width:100px;">手机号：</td>
            <td><input type="text" id="telphone" value=""><td>
          </tr>
          <tr class="trHeight">
            <td style="text-align:right;width:100px;"> 反馈时间：</td>
            <td><input type="text" id="backtime" value="">
            
            	</td>
            <td style="text-align:right;width:100px;">站点：</td>
            <td><input type="text" id="site" value=""></td>
          </tr>
          <tr class="trHeight">
            <td style="text-align:right;width:100px;">操作系统：</td>
            <td><input type="text" id="system" value=""></td>
            <td style="text-align:right;width:100px;">操作系统版本：</td>
            <td><input type="text" id="systemversion" value=""><td>
          </tr>
          <tr class="trHeight">
            <td style="text-align:right;width:100px;vertical-align: top;">反馈内容：</td>
            <td><textarea id="content"></textarea>
            <td>
          </tr>
         
          <tr style="position:relative;top:10px;">
            <td style="text-align:right;vertical-align: top;" >附图：</td>
            <td>
            <div id="box"></div>
          </tr>
        </table>
		<hr width="500x">
		<!-- 		json遍历得到 -->

        <div id="tbodyX"></div>
		<!-- 		form表单提交 -->
	<div id="hide">
		<form name="Form" id="replyform" action="${ctx}/contentManage/replyManage.do?method=replyAdd" method="post">
		<input type="hidden" id="fid" name="fid" value="fid">
		<input type="hidden" id="feedbackstate" name="state" value="state">
		<table>
           <tr>
               <td style="text-align:left">回复内容:</td>
            </tr>
            <tr>
               <td style="text-align:left">
              <textarea id="reply_content" name="reply_content" rows="8" cols="50" placeholder="不超过200字"></textarea>
               </td>
           </tr>
			<tr>
               <td style="text-align:left">
               	<input type="button" onclick="replySubmit()" class="fl" style="width:65px;height: 50px;font-size: 20px;color: white;background-color: #2e8ded;"  value="提交">
               </td>
       </table>
       	
       </form>
		</div>
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
	var reply_content = $('#reply_content').val();
	if ($.trim(reply_content) == ""){
		alert ("请输入内容，再提交！");
		return;
	}
	$.ajax({
		url :"${ctx}/contentManage/replyManage.do?method=replyAdd",
		data:{
				'fid':$("#fid").val(),
				'state':$("#feedbackstate").val(),
				'reply_content':$("#reply_content").val(),
			},
		type:'post',
		dataType:'text',
		success:function(result){
			if (result == "success") {
				alert("评论成功");
				window.location.reload(true);
				document.location.href = "${ctx}/contentManage/feedbackManage.do?method=list";
			}else {
				alert("添加失败，稍后重试");
			}
		}
	});
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
		//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
		
		var creater = $('#creater').val();
		if ($.trim(creater) == ""){
			$("#creater").hide();
			return;
		}
		
		//没有用户名，不能评论
		var telphone = $('#telphone').val();
		if ($.trim(telphone) == ""){
			$("#telphone").hide();
			return;
		}
		

});
 jQuery(document).ready(function($) {
	  $.LightBox({speed:500})
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


 
function deletefeedback(id,state) {
	if(confirm("删除?")){

		var url = "${ctx}/contentManage/feedbackManage.do";
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
//查看反馈信息
function edit(id, state){
	/* if(state == 2){
		alert("不能编辑已发布的文章！");
		return;
	} */
	document.location.href = "${ctx}/contentManage/feedbackManage.do?method=edit&id="+id;
}

//#####################################
var win_w = "800px";
var win_h = "500px";
var add_cont = $('#layercontent').html();
$('#layercontent').remove();
$(".QueryView").click(function(){
	layer.open({
		type: 1,
		title: "查看反馈意见",
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
		$.post("${ctx}/contentManage/feedbackManage.do?method=delete", p,
  			function(data){
  				if(data !="fail"){
  					alert("所选项删除成功!");
  					window.location.reload(true);
  				}
  				else{
  					alert("该选项已被删除，不能删除！");
  					return true;
  				}
  				document.location.href = document.location.href.split("#")[0];
  		});
	}
}



//#####################################

function infoView(id,state){
         	 $.ajax({
				type: 'POST',
				url: "${ctx}/contentManage/feedbackManage.do?method=edit&id="+id+"&state="+id,
				dataType: 'json',
				success : function(data){
					$("#creater").val(data.feedback.creater);
		          	$("#telphone").val(data.feedback.telphone);
	            	$("#backtime").val(timeStampExchange(data.feedback.backtime.time));
	            	$("#site").val(data.feedback.site);
	            	$("#system").val(data.feedback.system);
	            	$("#systemversion").val(data.feedback.systemversion);
	            	$("#content").val(data.feedback.content);
	            	$("#fid").val(data.feedback.id);   //回复需要反馈的id 作为外键
	            	$("#feedbackstate").val(data.feedback.state); //状态   提交回复表单，修改反馈表状态，为已回复
	            	
	            	var  v = "";
					var img=data.feedback;
				for (var j = 0; j < img.images.length; j++) {
// 					v+="<table>"
// 						+ "<tr><td>"
// 						+var $image=$("<img src='"+img.images[j]+"'  width='50px' height='50px'/>");
// // 						+ img.images[j]
// 						+ "</td>"
// 						+ "</tr>"
// 						+ "</table>";
					if(img.images[0]==''){
						img.images.splice(0,1);
					}
					var $image=$("<img class='js-lightBox' data-title='picture-title3-1' data-group='group-4' src='"+img.images[j]+"'  width='150px' height='100px'/>");
					$image.appendTo("#box");
				}
				$.LightBox({speed:500})
// 				$("#image").html(v);
// 				$("#image").attr("src",'v');
// 				$('#image').attr("src", v);
	            	var msg = data;
	            	var str = "";
					for (var i = 0; i < msg.replyList.length; i++) {
						str += "<table width='60%'>"
							+ "<tr>"
							+ "<td>时间</td>"
							+ "<td>"
							+ (msg.replyList[i].reply_time).substring(0,19)
							+ "</td>"
							+ "</tr>"
							+ "<br>"
							+ "<tr>" + "<td width=30%>回复内容</td>" + "<td>"
							+ msg.replyList[i].reply_content
							+ "</td>"
							+ "</tr>"
							+ "</table>"

					}
					$("#tbodyX").html(str);
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
function ChangeDateFormat(jsondate) {     
    jsondate = jsondate.replace("/Date(", "").replace(")/", "");     
    if (jsondate.indexOf("+") > 0) {    
        jsondate = jsondate.substring(0, jsondate.indexOf("+"));     
    }     
     else if (jsondate.indexOf("-") > 0) {    
         jsondate = jsondate.substring(0, jsondate.indexOf("-"));     
     }     
     
    var date = new Date(parseInt(jsondate, 10));   
     var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;    
     var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();    
     return date.getFullYear() + "-" + month + "-" + currentDate;    
 }  
    
/***
 * 日期格式化
 * @param inputTime
 * @returns {*}
 */
function formatDateTime(inputTime) {
    if(typeof(inputTime) =='undefined') {
        return "";
	}else {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
	}
};
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
<script type="text/javascript">
jQuery(document).ready(function($) {
  $.LightBox({speed:500})
});
</script>
</body>

