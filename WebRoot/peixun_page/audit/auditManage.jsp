<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>培训后台</title>
    <%@include file="/commons/taglibs.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>
<%@include file="/peixun_page/top.jsp" %>
<body>
<div class="clear"></div>
<!-- 查询条件 -->
<div class="center">
    <form id="fm1" name="fm1" method="post" action="${ctx}/systemManage/getMenu.do?method=systemlog">
        <div class="tiaojian" style="min-height:40px;">
            <p class="fl" style="margin:0 20px 10px 0">
                <span style="width:5em;text-align:right;">用户名：</span>
                <input type="text" name="loginName" id="loginName" value="${loginName}"/>
            </p>
            <p class="fl" style="margin:0 0 10px 0">
                <span style="width:5em;text-align:right;">日志类型：</span>
                <select name="operateType" id="operateType" >
                    <option value="">全部</option>
                    <option value="view"<c:if test="${operateType eq 'view'}"> selected</c:if>>查看</option>
                    <option value="login"<c:if test="${operateType eq 'login'}"> selected</c:if>>登录</option>
                    <option value="exit"<c:if test="${operateType eq 'exit'}"> selected</c:if>>退出</option>
                    <option value="add"<c:if test="${operateType eq 'add'}"> selected</c:if>>新增</option>
                    <option value="edit"<c:if test="${operateType eq 'edit'}"> selected</c:if>>修改</option>
                    <option value="delete"<c:if test="${operateType eq 'delete'}"> selected</c:if>>删除</option>
                    <option value="qt_bindCard"<c:if test="${operateType eq 'qt_bindCard'}"> selected</c:if>>绑定学习卡</option>
                    <%--<option value="buy"<c:if test="${operateType eq 'buy'}"> selected</c:if>>购买项目</option>--%>

                </select>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
            <p class="fl" style="margin:0 0 10px 0">
                <span style="width:5em;text-align:right;">操作平台：</span>
                <select name="operatePlatform" id="operatePlatform" >
                    <option value="">全部</option>
                    <option value="qiantai"<c:if test="${operatePlatform eq 'qiantai'}"> selected</c:if>>中国继续医学教育网</option>
                    <option value="peixun"<c:if test="${operatePlatform eq 'peixun'}"> selected</c:if>>学习培训后台</option>
                    <option value="admin"<c:if test="${operatePlatform eq 'admin'}"> selected</c:if>>资源管理系统</option>
                </select>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>

            <p class="fl" style="margin:0 20px 10px 0">
                <span style="width:5em;text-align:right;">日志时间：</span>
                <input type="text" name="start_date" id="start_date" class="editInput"
                       datatype="*" nullmsg="请选择开始时间！" value="${start_date}"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'start_date\')}'})"/>
                <span>--</span>
                <input type="text" name="end_date" id="end_date" class="editInput"
                       datatype="*" nullmsg="请选择结束时间！" value="${end_date}"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'end_date\')}'})"/>

            </p>

            <p class="fl cas_anniu">
                <a href="javascript:search();" style="width:70px;margin:0 0 20px 20px;">查询</a>

            </p>
            <p class="fr cas_anniu" style="margin:20px 0;">
                <a href="javascript:exportExcel();" style="width:120px;margin:0 0 0 20px;">导出</a>
            </p>
        </div>

    </form>


</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
    <div class="center01">
        <display:table   id="systemMenu" cellpadding="0" cellspacing="0" partialList="true"
                       requestURI="${ctx}/systemManage/getMenu.do?method=systemlog"
                       size="${totalCount}" pagesize="20" list="${page}" style="font-size:12px;width:100%;"
                       class="mt10 table"
                       decorator="com.hys.exam.displaytag.TableOverOutWrapper">
            <%--<display:column title="序号" style="width:5%">--%>
                <%--${systemMenu_rowNum}--%>
            <%--</display:column>--%>
            <display:column title="序号" property="operate_id" style="text-align:center"/>
            <display:column title="日志类型" style="text-align:center">
                <c:choose>
                    <c:when test="${systemMenu.operate_type == 'login'}">登录</c:when>
                    <c:when test="${systemMenu.operate_type == 'exit'}">退出</c:when>
                    <c:when test="${systemMenu.operate_type == 'add'}">新增</c:when>
                    <c:when test="${systemMenu.operate_type == 'edit'}">修改</c:when>
                    <c:when test="${systemMenu.operate_type == 'delete'}">删除</c:when>
                    <c:when test="${systemMenu.operate_type == 'qt_bindCard'}">绑定学习卡</c:when>
                    <%--<c:when test="${systemMenu.operate_type == 'buy'}">购买项目</c:when>--%>
                    <c:otherwise>查看</c:otherwise>
                </c:choose>
            </display:column>
            <display:column title="操作平台"  style="text-align:center">
                <c:if test="${systemMenu.operate_platform == 'peixun'}">学习培训后台</c:if>
                <c:if test="${systemMenu.operate_platform == 'admin'}">资源管理系统</c:if>
                <c:if test="${systemMenu.operate_platform == 'qiantai'}">中国继续医学教育网</c:if>
            </display:column>
            <display:column title="模块名称" property="module_name" style="text-align:center"/>
            <display:column title="日志内容" property="operate_context" style="text-align:center"/>
            <display:column title="操作时间" style="text-align:center">
                ${systemMenu.operate_time}
                <%--<fmt:formatDate value="${systemMenu.operate_time}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            </display:column>
            <display:column title="来访IP" property="visit_ip" style="text-align:center"/>
            <display:column title="操作用户" property="operate_login_name" style="text-align:center"/>

            <%--<display:column title="操作" style="text-align:center">--%>
                <%--<a href="javascript:Del( ${systemMenu.operate_id});">删除</a>--%>
            <%--</display:column>--%>
        </display:table>
    </div>
</div>
<script type="text/javascript">
    /**
     *  数据查询
     */
    function search() {
        $("#system_type").val($("#systemName").text());
        var url =" ${ctx}/systemManage/getMenu.do?method=systemlog";
        $("#fm1").attr("action", url);
        $('#fm1').submit();
    }
    /**
     *  数据导出
     */
    function exportExcel(){
        var url ="${ctx}/systemManage/getMenu.do?method=exportExecl";
        $("#fm1").attr("action", url);
        $('#fm1').submit();
    }
    function Del(id_){
        if (!confirm("确定删除?"))
        {
            return;
        }
        $.ajax({
            type:'post',
            url: '${ctx}/systemManage/getMenu.do',
            data:'method=delsystemlog&id='+id_,
            dataType:'text',
            success:function(result){
                if(result == 'success'){
                    alert('删除成功！');
                    document.location.href = document.location.href.split("#")[0];
                }else{
                    alert('删除失败！');
                }
            },
            error:function(){
                alert('删除失败!');
            }
        });
    }
</script>
</body>

