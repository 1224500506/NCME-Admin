<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>培训后台</title>
    <%@include file="/commons/taglibs.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>
<%@include file="/peixun_page/top.jsp" %>

<body><br/>
<br/><br/><br/><br/><br/>
<center><button id="executeHtml" class="btn_blue" style="width:222px;">生成静态首页</button></center>

<script type="text/javascript">
    $(document).ready(function () {
        $("#executeHtml").click(function () {
            if (confirm("确定要重新生成静态页面吗？")) {
                $.ajax({
                    //接口地址
                    url:'${ctxQiantaiURL}/first.do?eh=626',
                    //请求方式
                    type:'post',
                    //返回数据类型
                    dataType:'json',
                    //请求失败时处理方式
                    error:function(){},
                    //请求成功时处理方式
                    success:function(result){
//                        console.log(JSON.stringify(result))
//                        if(result.message == 'loginHasLogin'){
//                            window.localStorage.setItem("isLogin","true");
//                        }else{
//                            window.localStorage.setItem("isLogin","false");
//                        }
                    },
                });
                alert("重新生成静态页面成功！");
            } else {
                alert("取消重新生成静态页面成功！");
            }
        });
    });
</script>
</body>

