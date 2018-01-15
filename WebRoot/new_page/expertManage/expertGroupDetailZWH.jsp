<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hys.exam.model.ExpertGroup" %>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训平台</title>

	<link href="${ctx}/new_page/css/global.css" rel="stylesheet">	
		
	<style type="text/css">
		@font-face {font-family: "iconfont";
          src: url('${ctx}/new_page/css/iconfont/iconfontUser.eot'); /* IE9*/
          src: url('${ctx}/new_page/css/iconfont/iconfontUser.eot#iefix') format('embedded-opentype'), /* IE6-IE8 */
          url('${ctx}/new_page/css/iconfont/iconfontUser.woff') format('woff'), /* chrome, firefox */
          url('${ctx}/new_page/css/iconfont/iconfontUser.ttf') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
          url('${ctx}/new_page/css/iconfont/iconfontUser.svg#iconfont') format('svg'); /* iOS 4.1- */
        }
        .iconfont {
          font-family:"iconfont" !important;
          font-size:16px;
          font-style:normal;
          -webkit-font-smoothing: antialiased;
          -webkit-text-stroke-width: 0.2px;
          -moz-osx-font-smoothing: grayscale;
        }
        
		.name_list {
		    padding: 0 20px;
		    box-sizing: border-box;
		}
		.dot_name li { font-weight: bold; }
		.dot_name li i { color: #12bce1; font-weight: normal; margin-right: 5px; }
		
		.name_list li span {
		    margin-left: 30px;
		    font-weight: normal;
		    color: #666;
		}
		
	</style>
	
	
<div class="bjs">
	<!-- 题库内容 -->
	<div class = "tkbjs"></div>
	<div class="center">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="content no_padding">
				        <div class="fr cas_anniu">
							<a href="javascript:goBack();" class="fl" style="width:95px;margin-right:15px;">返回</a>
						</div>
			        <div class="cont">
				        <h1 class="main_title_2">${groupInfo.group.name}</h1>
				        <p style="text-indent:2em">${groupInfo.group.summary}</p>
				        <p style="text-indent:2em">&nbsp;</p>
			        </div>
			        <div class="content top_border">
			        	<c:forEach items = "${groupInfo.expertList}" var = "expert">
			        	<c:if test="${expert.office == 1}">
				            <div class="cont">
				                <h2 class="main_title"><span>主任委员</span></h2>
				                <div class="main_user_info">
				                    <a>
				                        <span class="user_avatar">
				                            <img src="${expert.photo}"></img>
				                        </span>
				                        <p class="user_name">${expert.name}</p>
				                        <p class="user_cont">
				                            <span>${expert.workUnit}</span><span>
					                            <c:if test="${expert.workUnit_office==1}">院长</c:if>
												<c:if test="${expert.workUnit_office==2}">副院长</c:if>
												<c:if test="${expert.workUnit_office==3}">主任</c:if>
												<c:if test="${expert.workUnit_office==4}">副主任</c:if>
												<c:if test="${expert.workUnit_office==5}">所长</c:if>
												<c:if test="${expert.workUnit_office==6}">副所长</c:if>
												<c:if test="${expert.workUnit_office==7}">护士长</c:if>
											</span>
											<span>${expert.jobName}</span><br>
				                           	${expert.summary}
				                        </p>
				                    </a>
				                </div>
			             </c:if>
			             </c:forEach>
			            </div>
			            <div class="cont">
			                <h2 class="main_title">副主任委员</h2>
			                <ul class="name_list dot_name">
			                    <c:forEach items = "${groupInfo.expertList}" var = "expert">
			        				<c:if test="${expert.office == 2}">
			                    		<li><i class="iconfont">&#xe600;</i> ${expert.name}<span>${expert.workUnit}</span></li>
			                    	</c:if>
			                    </c:forEach>
			                </ul>
			            </div>
			            <div class="cont">
			                <h2 class="main_title">秘书长</h2>
			                <ul class="name_list dot_name">
			                 <c:forEach items = "${groupInfo.expertList}" var = "expert">
			        			<c:if test="${expert.office == 3}">
			                    	<li><i class="iconfont">&#xe600;</i> ${expert.name} <span>${expert.workUnit}</span></li>
			                    </c:if>
			                 </c:forEach>
			                </ul>
			            </div>
			            <div class="cont">
			            <c:forEach items = "${subGroupInfo}" var = "sub">
			                <h2 class="main_title_3"><span>${sub.group.name}</span></h2>
			                <ul class="name_list">
			                  <c:forEach items = "${sub.expertList}" var = "expert">
			        			<c:if test="${expert.office == 4}">
			                    	<li><strong>组长：</strong> ${expert.name} <span>${expert.workUnit}</span></li>
			                    </c:if>
			                    </c:forEach>
			                </ul>
			                <h3 style="padding-left:20px;font-size:14px;font-weight:bold">委员：</h3>
			                <ul class="name_list dot_name">
			                    <c:forEach items = "${sub.expertList}" var = "expert">
			        				<c:if test="${expert.office != 4}">
				                    	<li style="width:50%;padding: 0 20px;"><i class="iconfont">&#xe600;</i> ${expert.name} <span>${expert.workUnit}</span></li>
				                    </c:if>
				                </c:forEach>
			                </ul>
			            </c:forEach>    
			            </div>
			    </div>
			</div>
		</div>
	</div>
<div class="clear"></div>
</div>

<script type="text/javascript">
function goBack() {
		window.history.back();
}
					

</script>
</body>
</html>
