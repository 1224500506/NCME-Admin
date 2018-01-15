<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>
<body>
<div>
	<!-- 查询条件 -->
	<div class="center">
		<form id="queryForm" name="queryForm" action="${ctx}/cardManage/SystemCardStatus.do?method=getBindUser" method="post">
		<input type="hidden" name="isBind" id="isBind" value="${isBind }"/>
		<input type="hidden" name="cardId" id="cardId" value="${cardId }"/>
		<div class="tiaojian" style="min-height:40px;">
			<%-- <p class="fl">
				<span>站点：</span>
				<select class="fl select" id="domainName" name="domainName">
			      <c:forEach items="${siteList}" var="site">
			       	 <option value="${site.domainName}" <c:if test="${param.domainName eq  site.domainName}">selected</c:if> >${site.aliasName}
			      </c:forEach>
				</select>
			</p> --%>
			<p class="fl" style="margin-left:60px;">
				<span>姓名：</span>
				<input type="text" class="ka_click" name="name" id="name" value="${name}" />
			</p>
			<p class="fl" style="margin-left:60px;">
				<span>证件号：</span>
				<input type="text" class="ka_click" name="certificateNo" id="certificateNo" value="${certificateNo}" />
			</p>
			<div class="fl cas_anniu" >
				<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
		</form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center">
		<div class="center01">
			<div class="mt5 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="javascript:updateBindUser('${cardId }','${isBind}');" class="fl lca_queren" style="width:80px;"><c:if test="${isBind ==1 }">解绑用户</c:if><c:if test="${isBind ==0 }">绑定用户</c:if></a>
					<a href="javascript:history.go(-1);" class="fl lca_queren" style="width:80px;margin-left:10px;">返回</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCardStatus.do?method=getBindUser" id="p" class="mt10 table" 
				list="${page.list}" size="${page.count}" pagesize="20" partialList="true" 
				excludedParams="method"  keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
<%-- 				<display:caption>查询结果 <span style="color:red">${meg}</span></display:caption>
 --%>				<%-- <display:column title="<input type='checkbox' class='chkall'/>" style="width:10%;"><input type='checkbox' class='chk' value="${p.userId}"/></display:column> --%>
				<<display:column title='选择' style="text-align:center">
					<input type='radio' name="chk" value="${p.userId}"/>
				</display:column>
				<display:column title="序号" style="width:10%;">${p_rowNum}</display:column>
				<display:column title="证件号码" style="width:20%;" property="certificateNo"></display:column>
				<display:column title="姓名" style="width:10%;" property="realName"></display:column>
				<display:column title="手机号" style="width:20%;" property="mobilPhone"></display:column>
				<display:column title="单位" style="width:30%;" property="workUnit"></display:column>
			</display:table>
    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />

	</div>
		<!-- 内容（结束） -->
		
</div>

<!-- 添加 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:420px;">
		<div style="padding-top:20px;">
			<div class="mt10 lc_shengcheng">
				<span>卡号：</span>
				<input type="text" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>密码：</span>
				<input type="text" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>卡有效期：</span>
				<input type="text" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>是否绑定：</span>
				<select class="lc_list">
					<option value="">请选择</option>
					<option value="">是</option>
					<option value="">否</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>总学时：</span>
				<input type="text" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>剩余学时：</span>
				<input type="text" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>销售方式：</span>
				<select class="lc_list">
					<option value="">请选择</option>
					<option value="">网上销售</option>
					<option value="">地面销售</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="#" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
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
	
	//check all   业务上暂时未一张卡一个用户，所以改为单选模式，此处注释--taoliang
		/* $('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		}); */

});

//[查询] 
function query() {
	$('#queryForm').submit();
}

	//绑定/解绑
	function updateBindUser(cardId, isBind){
		//var userIds = $("input[type='checkbox']:checked");
		var userIds = $("input[type='radio'][name='chk']:checked");
		if(userIds == '' || userIds.length == 0){
			alert("请先选择学员!");
		    return;
		}
		var selectedIds = '';
		for(var i=0; i<userIds.length; i++){
			selectedIds += userIds[i].value + ',';
		}
		//isBind为0,表示需绑定;为1表示解绑
		if(isBind==0){
			/* var retdata = $.ajax({url: "${ctx}/cardManage/SystemCardStatus.do?method=bindUser&cardId="
				+ cardId+"&userIds="+selectedIds+"&siteId=${siteId}", async: false}).responseText;
			if(retdata>0){
				alert("绑定用户成功!");
			}else{
				alert("由于网络原因,绑定用户不成功,请稍候再试!");
			} */
			$.ajax({
				type: 'POST',
				url: "${ctx}/cardManage/SystemCardStatus.do?method=bindUser&cardId="+ cardId+"&userIds="+selectedIds+"&siteId=${siteId}",
				dataType: 'JSON',
				async: false,
				success : function(data){
					var result = eval(data);
					if(result.message == 'success'){
						//暂时播放
			        	alert("绑定用户成功!");
					}else if(result.message == 'userno'){						
						alert("用户信息异常，请重新选择用户"); //卡状态不可用
						return false;
					}else if(result.message == 'noenable'){						
						alert("该卡已被使用，无法重复添加"); //卡状态不可用
						return false;
					}else if(result.message == 'time'){
						alert("您的学习卡已经过期");
						return false;
					}else if(result.message == 'notfind') {
						alert("输入的卡号或密码错误");
						return false;
					}else if(result.message == 'typeno') {
						alert("该卡未绑定项目，无法添加成功"); //卡类型无效
						return false;
					}else if(result.message == 'typedisable') {
						alert("该学习卡处于禁用状态，不可绑定用户"); //卡类型无效
						return false;
					}
				},
				error:function(data2){
					alert("数据异常");
				}
	    	});
		}else if(isBind == 1){
			var retdata = $.ajax({url: "${ctx}/cardManage/SystemCardStatus.do?method=unBindUser&cardId="
				+ cardId+"&userIds="+selectedIds, async: false}).responseText;
			if(retdata >0){
 				alert("解绑用户成功!");
 			}
 			else{
 				alert("由于网络原因,解绑用户不成功,请稍候再试!");
 			}
		}
		window.location.reload(true);
		
	}

</script>
</body>
</html>