<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
	<style>
		input:disabled{background:#f0f0f0;color:#ccc;}
		.course_list{width:350px;height:300px;float:left;margin-right:20px;background-color:#f9f9f9;}
		.course_list .course_img{width:350px;height:200px;overflow:hidden;}
		.course_list .course_img img{width:100%;}
		.course_list a{outline: none;display:inline-block;width:100%;height:100%;}
		.course_list .title{font-size:16px;color:#333;font-weight:600;margin:10px;}
		.course_list .desc{margn:10px 0;padding:5px 10px;list-style:none;}
		.course_list .desc li{width:50%;float:left;line-height:20px;font-size:14px;color:#333;}
	</style>
</head>
<%@include file="/peixun_page/top.jsp"%>
<link rel="stylesheet" href="${ctx}/css/pagination.css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.pagination.js"></script>

<script language="javascript" type="text/javascript">
    var pageSize=20;
	$(document).ready(function () {
        //加入分页的绑定

        $("#page").pagination(${CVSetNum}, {
            callback: pageselectCallback,
            items_per_page: pageSize,
            current_page: 0,
            num_edge_entries: 0
        });
    });

    //这个事件是在翻页时候用的
    function pageselectCallback(page_id, jq) {
        var data = {
            pageSize: pageSize,
            page: page_id,
            propIds:$("#propIds").val(),
            forma:$("#forma").val(),
            propNames:$("#propIds").val(),
            CVSetStatus:$("#CVSetStatus").val(),
            CVSetName:$("#CVSetName").val()
        };
        var url = "${ctx}/CVSetManage.do?mode=myXiangMu_p";
        $.getJSON(url, data, function (json) {
            $("#ctx").empty()  ;
            var array = json;
            for(var i=0;i<array.length;i++){
                var item=array[i];
                    $("#ctx").append('<div class="course_list" style="margin-bottom:20px;height:350px">'+
                        '<a href="${ctx}/CVSetManage.do?id='+ item.id+'&mode=get_CVS" style="color:black;">'+
                        '<div class="course_img">'+
                        '<img src="'+item.file_path+'" style="width:200px;"/>'+
                        '</div>'+
                        '<div class="title">'+item.name+'</div>'+
                        '<ul class="fl">'+
                        '<li>项目负责人：'+formatManagerList(item.managerList)+'</li>'+
                        '<li>项目归属机构：'+formatManagerList(item.organizationList)+'</li>'+
                        '<li>状态：'+  formatStatus(item.status)+'</li>'+
                        '<li class="clear" style="width:100%;">项目审核截止日期：'+formatDateTime(item.deli_date)+'</li>'+
                        '</ul>'+
                        '</a>'+
                        '</div>')
            }
        });

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

        /***
		 * 格式化项目状态
         * @param status
         * @returns {*}
         */
        function formatStatus(status) {
			if(status=="1"){
				return '未提交';
			} else if(status=="2"){
				return '审核中';
			} else if(status=="3"){
                return '审核合格';
            }  else if(status=="4"){
                return '审核不合格';
            } else if(status=="5"){
                return '已发布';
            }else if(status=="6"){
                return '已下线';
            } else {
                return '';
			}
        };
        
        function  formatManagerList(managerList) {
            var str="";
            if(managerList.length>0){
                for(var i=0;i<managerList.length;i++){
                    var item=managerList[i];
					str=item.name+" ";
                }
			}
			return str;
        }
    }
</script>
<body>
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	<form id="qualify_main_frm" action="${ctx}/CVSetManage.do?mode=myXiangMu" method="post">
		<p class="fl">
			<span>学科：</span>			
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" style="margin-right:20px" id="propNames01">${propNames}</div>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>项目名称：</span>
			<input type="text" id="CVSetName" name="CVSetName" value="${sname}"/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:9em;text-align:right;">项目授课形式：</span>
		</p>
		<div class="fl select" style="margin:0 20px 20px 0;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select name="forma" id="forma" style="display:none">
					<option value="0" <c:if test="${forma == 0}">selected</c:if>>全部</option>
					<option value="1" <c:if test="${forma == 1}">selected</c:if>>远程</option>
					<option value="3" <c:if test="${forma == 3}">selected</c:if>>面授</option>
				</select>
				<li>全部</li>
				<li>远程</li>
				<li>面授</li>
			</ul>
		</div>
		<p class="fl" style="margin-right:20px;">
			<span>项目状态：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="CVSetStatus" id="CVSetStatus" style="display:none;">
				<option value="0">全部</option>
				<option value="1" <c:if test="${status == 1}">selected</c:if>>未提交</option>
				<option value="2" <c:if test="${status == 2}">selected</c:if>>审核中</option>
				<option value="3" <c:if test="${status == 3}">selected</c:if>>审核合格</option>
				<option value="4" <c:if test="${status == 4}">selected</c:if>>审核不合格</option>
				<option value="5" <c:if test="${status == 5}">selected</c:if>>已发布</option>
				<option value="6" <c:if test="${status == 6}">selected</c:if>>已下线</option>
			</select>
				<li>全部</li>
				<li>未提交</li>
				<li>审核中</li>
				<li>审核合格</li>
				<li>审核不合格</li>
				<li>已发布</li>
				<li>已下线</li>
			</ul>
		</div>
	</form>
		<p class="fl cas_anniu">
			<a href="javascript:search();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
		
		<p class="fl cas_anniu">
			<a href="${ctx}/peixun_page/CVSet/manage/CVS_add.jsp?projectfrom=1" class="fl" style="width:70px;margin-left:40px;">添加项目</a>
		</p>
		<div class="clear"></div>
		<%--<input type="hidden" value="${expertXueke.propNames}" id="propNames"/>--%>
		<%--<input type="hidden" value="${expertXueke.propIds}" id="propIds"/>--%>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01" id="main_page" style=" clear:both">
		<div id="ctx"  style="text-align: center">
			<%--<c:forEach items = "${CVSet}" var = "item">--%>
			<%--<div class="course_list" style="margin-bottom:20px;height:350px">--%>
			<%--<a href="${ctx}/CVSetManage.do?id=${item.id}&mode=get_CVS" style="color:black;">--%>
			<%--<div class="course_img">--%>
			<%--<img src="${item.file_path}" style="width:200px;"/>--%>
			<%--</div>--%>
			<%--<div class="title">${item.name}</div>--%>
			<%--<ul class="fl">--%>
			<%--<li>项目负责人： <c:forEach items = "${item.managerList}" var="manager">${manager.name}&nbsp;</c:forEach></li>--%>
			<%--<li>项目归属机构：<c:forEach items = "${item.organizationList}" var="org">${org.name}&nbsp;</c:forEach></li>--%>
			<%--<li>状态：<c:if test="${item.status == 1}">未提交</c:if>--%>
			<%--<c:if test="${item.status == 2}">审核中</c:if>--%>
			<%--<c:if test="${item.status == 3}">审核合格</c:if>--%>
			<%--<c:if test="${item.status == 4}">审核不合格</c:if>--%>
			<%--<c:if test="${item.status == 5}">已发布</c:if>--%>
			<%--<c:if test="${item.status == 6}">已下线</c:if>  </li>--%>
			<%--<li class="clear" style="width:100%;">项目审核截止日期：<fmt:formatDate value="${item.deli_date}" pattern="yyyy-MM-dd HH:mm:ss"/></li>--%>
			<%--</ul>--%>
			<%--</a>--%>
			<%--</div>--%>
			<%--</c:forEach>--%>
		</div>

	</div>
	<div id="page" class="pagination" style=" clear:both"></div>
</div>
<script type="text/javascript">
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
	//下拉框
		select_init();	
		
// 		学科弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});	
		
		
});
	//////////load relation xueke
		var str ='<select name="propIds" style="display:none;"><option value="">全部</option>';
		var xkNames = $('#propNames').val().split(",");
		var xkIds = $('#propIds').val().split(",");
		for(var i=0; i<xkNames.length; i++){
			var propIds = "${propIds}";
			if(propIds == xkIds[i])
			{
				str += '<option value ="'+xkIds[i] + '" selected>'+xkNames[i]+'</option>';
			}
			else
			{
				str += '<option value ="'+xkIds[i] + '">'+xkNames[i]+'</option>';
			}
		}
		str += '</select><li>全部</li>';
		for(var i=0; i<xkNames.length; i++){
			str += '<li>'+xkNames[i]+'</li>';
		}	
		
		$('#xkdisplay').html(str);
	////////	
function select_init() {
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

function search(){
	$('#propNames').val($('#propNames01').text());
	$(qualify_main_frm).submit();
}
</script>
</body>
</html>