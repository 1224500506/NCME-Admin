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
 <!-- 详情信息 -->
<div class="cl_chakan">
	<div class="cl_xuexi">
		<h1>学习卡信息</h1>
		<ul>
			<li>
				<span>学习卡卡号：</span>
				<em>${card.cardNumber}</em>
			</li>
			<li>
				<span>学习卡密码：</span>
				<em>${card.cardPassword }</em>
			</li>
			<li>
				<span>卡余额：</span>
				<em>￥${card.balance}</em>
			</li>
			<li>
				<span>学习卡状态：</span>
				<em>
					<c:if test="${card.status == 2 }">已绑定</c:if>
					<c:if test="${card.status == 1 }">有效</c:if>
					<c:if test="${card.status == 0 }">待生效</c:if>
					<c:if test="${card.status == -2}">禁用</c:if>
        			<c:if test="${card.status == -1 }">无效</c:if>  
        		</em>
			</li>
			<li>
				<span>父卡：</span>
				<em>${card.cardNumber}</em>
			</li>
			<li>
				<span>订单号：</span>
				<em>${card.cardNumber}</em>
			</li>
		
		</ul>
		<div class="clear"></div>
	</div>
	<div class="mt30 cl_xuexi">
		<h1>用户信息</h1>
		<ul>
			<li>
				<span>用户名：</span>
				<em>${userInfo.accountName}</em>
			</li>
			<li>
				<span>真实姓名：</span>
				<em>${userInfo.realName }</em>
			</li>
			<li>
				<span>绑定时间：</span>
				<em>${fn:substringBefore(userInfo.bindDate, ".")}</em>
			</li>
			<li>
				<span>销售渠道：</span>
				<em>
				 <c:if test="${cardType.isNetpay == 1 }">仅限网上销售</c:if>
        		 <c:if test="${cardType.isNetpay == 2 }">仅限地面销售（前台不需要销售）</c:if>
        		 <c:if test="${cardType.isNetpay == 3 }">地面网上销售</c:if>
				</em>
			</li>
			<li>
				<span>所在医院：</span>
				<em>${userInfo.workUnit}</em>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="mt30 cl_xuexi">
		<h1>卡类型信息</h1>
		<ul>
			<li>
				<span>卡类型（编号）：</span>
				<em>${cardType.id}</em>
			</li>
			<li>
				<span>卡类型（中文）：</span>
				<em>${cardType.cardTypeName }</em>
			</li>
			<li>
				<span>生效时间：</span>
				<em>${fn:substringBefore(cardType.startDate, ".")}</em>
			</li>
			<li>
				<span>失效时间：</span>
				<em>${fn:substringBefore(cardType.endDate, ".")}</em>
			</li>
			<li>
				<span>总学分：</span>
				<em>${cardType.creditSum}</em>
			</li>
			<li>
				<span>学分范围：</span>
				<em>${cardType.creditScope }</em>
			</li>
			<li>
				<span>付费类型：</span>
				<em>${cardType.creditScope }</em>
			</li>
			<%-- <li>
				<span>课程列表：</span>				
				<em>
				<select name="choices_c" size="5" style="width: 100%" multiple="multiple" >
                 		<c:forEach items="${course_list }" var="c" varStatus="status">
                 		<option value="${c.id }">
                			${status.index + 1 }：${c.studyCourseName }--${c.summary }
                		</option>
                		</c:forEach>
            	</select>
				</em>
				<em>
				
                 		<c:forEach items="${course_list }" var="c" varStatus="status">
                 		
                			${status.index + 1 }：${c.studyCourseName }--${c.summary }
                		
                		</c:forEach>
            	
				</em>
			</li> --%>
			<li>
				<span>使用地区：</span>
				<%-- <em>
				<select name="choices_o" size="5" style="width: 100%" multiple="multiple" >
                 		<c:forEach items="${org_list }" var="c" varStatus="status_o">
                 		<option value="${c.organId }">
                			${status_o.index + 1 }：${c.name }
                		</option>
                		 </c:forEach>
                </select>
                </em> --%>
                
                 		<c:forEach items="${org_list }" var="c" varStatus="status_o">
                 		
                			${status_o.index + 1 }：${c.name }
                		
                		 </c:forEach>
                
				
			</li>
			
		</ul>
		<div class="clear"></div>
	</div>
	<div class="cas_anniu" style="width:70px;margin:40px auto;">
		<a href="${ctx}/cardManage/SystemCardStatus.do?method=list" class="fl queren" style="width:70px;">返回</a>
	</div>

</div>

<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>

</body>
</html>
