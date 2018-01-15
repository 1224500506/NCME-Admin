<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/commons/taglibs.jsp"%>
	<title></title>
	<link rel="stylesheet" href="${ctxAdminURL}/new_page/css/reset.css" />
	<link rel="stylesheet" href="${ctxAdminURL}/new_page/css/index.css" />
	<script type="text/javascript" src="${ctxAdminURL}/new_page/js/jquery.js"></script>
	 <script src="${ctxAdminURL}/js/jquery-1.11.1.min.js"></script>
	 <script src="${ctxAdminURL}/js/jquery.mobile.custom.min.js"></script>
	 <script src="${ctxAdminURL}/js/jquery.bxslider.js"></script>
	 <script src="${ctxAdminURL}/js/iconfont.js"></script>
	 <script src="${ctxAdminURL}/js/main.min.js"></script>
	 <link href="${ctxAdminURL}/css/animate.min.css" rel="stylesheet">
	 <link href="${ctxAdminURL}/css/jquery.bxslider.css" rel="stylesheet">
	 <link href="${ctxAdminURL}/css/global.css" rel="stylesheet">
	 
	 		<!-- 样式开始 -->
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/jquery.bxslider.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/animate.min.css"/>
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/global.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/rating.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/iconfont.css" />

        <!-- 样式结束 -->
	 
</head>
<body>
<div class="container">
    <div class="content no_padding" style="width:90%;margin-top:100px;">
  <div class="news_cont" style="width:90%">
   <c:if test="${not empty topicDiscussion}">
   
  
            <div class="top_cont">
                <h1>${topicDiscussion.talkContent}</h1>
                <%-- <h3>
                    <span>${topicDiscussion.createDate}</span>
                    <span>${topicDiscussion.createUser}</span>
                </h3> --%>
            </div>
            </c:if>
            <div>
                <div class="float_right">
                    <!-- <i class="fa fa-thumbs-o-up"></i>
                    <i class="fa fa-reply"></i> -->
                </div>
                <span class="go_comment btn btn_small" style="cursor:pointer;color:#333"> <i class="icon iconfont">&#xe607;</i> 去讨论 </span>
            </div>
            <div class="comment_form">
                <textarea name="comment_cont" class="comment_cont"></textarea>
                <div class="foot">
                    <button name="comment_submit" type="button" id="comment_submit" class="btn btn_orange btn_small">发布</button>
                    <span class="text_count">还能输入<span class="num_count">200</span>字</span>
                </div>
            </div>
            <div id="taolun">
            <ul class="comments_list">
                 <c:forEach items="${topicDiscussion.cvSetUnitDiscussList}" var="l">    
                <li>
                    <span class="avatar tiny">
                    <c:if test="${l.userAvatar !=  null}">
	               		<img src="${l.userAvatar}" />
	               	</c:if>
	               	<c:if test="${l.userAvatar == null}">
	               		<c:if test="${l.sex == 1}">
	               			<img src="${ctxAll}/peixun_page/images/user_avatar.jpg">
	               		</c:if>
	               		<c:if test="${l.sex == 2}">
	               			<img src="${ctxAll}/peixun_page/images/user_avatar1.png"/>
	               		</c:if>
	               	</c:if>
                    </span>
                    <p class="cont">${l.discussContent}</p>
                    <p>
                        <span class="float_right">
                            <!-- <i class="fa fa-thumbs-o-up"></i>
                            <i class="fa fa-reply"></i> -->
                        </span>
                        <span class="name">${l.systemUserName}</span>
                        <span class="datetime">${fn:substringBefore(l.discussDate, ".")}</span>
                    </p>
                </li>
                </c:forEach>
                
            </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

//window.parent.QueryQuota(window.parent.unitId);
    var discussId = '${topicDiscussion.id}';
    var saveTopicDiscussion = true ; //YXT，2017-08-16，保存主题讨论（防止保存操作重复触发）

    $(function () {
        $(".go_comment").click(function(){
            $(".comment_starts,.comment_form").show();
        });
        
        //计算评论字数
        $('.comment_form .comment_cont').on("keyup",function(){
            var txtLeng = $('.comment_form .comment_cont').val().length;      //把输入字符的长度赋给txtLeng
            //拿输入的值做判断
            if( txtLeng>200 ){
                //输入长度大于200时span显示0
                $('.comment_form .num_count').text(' 0 ');
                //截取输入内容的前300个字符，赋给fontsize
                var fontsize = $('.comment_form .comment_cont').val().substring(0,200);
                //显示到textarea上
                $('.comment_form .comment_cont').val( fontsize );
            }else{
                //输入长度小于300时span显示300减去长度
                $('.comment_form .num_count').text(200-txtLeng);
            }
        });
        
        /*
         * @author 张建国
         * @time   2017-02-20
         * 说      明：提交评论
         */
        $("#comment_submit").click(function(){
        	var content = $(".comment_cont").val();
        	if(content==null || content==''){
        		alert("请输入评论内容.");
        		return;
        	}
        	//获取父级页面变量信息
        	var cvsetId = window.parent.projectId;
        	
        	var unitId = ${unitId} ;//window.parent.unitId;
        	
        	var url = window.parent.adminURL;
        	
        	var userId = window.parent.userId;

        	//通过AJAX保存评论的信息
        	if(saveTopicDiscussion){
        		saveTopicDiscussion = false;
        		$.ajax({
            		url:url+"/caseManage/caseSaveDiscuss.do",
         		    type: 'POST',
         		    dataType: 'json',
         		    data:{
         		    	cvsetId:cvsetId,
         		    	unitId:unitId,
         		    	content:content,
         		    	discussId:discussId,
         		    	userId:userId
         		    },
          		    success: function(data){   
          		    	saveTopicDiscussion = true;
          		    	var result = eval(data);
          		    	if(result.message=='success'){
          		    		//重置信息
          		    		$(".comment_cont").val('');
          		    		//$("#discussId").val('');
          		    		//本页刷新
          		    		//location.reload();
          		    		//div局部刷新---by scp
          		    		$("#taolun").load(location.href+" #taolun"); 
          		    		//window.parent.load();
          		    		if(result.isPass == '1') {
          		    			//parent.location.reload();
          		    			$('#pm' + unitId , window.parent.document).html('&#xe61c;');
     		    				$('#unit' + unitId , window.parent.document).css("color","#ADADAD");
          		    		}
          		    	}
          		    },
    			    error: function(data){
    			    	saveTopicDiscussion = true;
    			    	alert('发布讨论出错：' + data) ;			    	
    			    }
          		});
        	}else{
        		alert("不要重复发布");
        	}
   		 
        });
    });
    
   /*
   更改框架页面样式
   
   **/ 
    window.onscroll = function () { 
   	 var t = document.documentElement.scrollTop || document.body.scrollTop;
   	 if (t > 0) { 
   		 window.parent.disp();
   	 } else { 
   		 window.parent.disn();
   	 } 
   	}
   
    
</script>
</body>