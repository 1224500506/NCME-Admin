<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="${ctxAll}/new_page/css/reset.css" />
	<link rel="stylesheet" href="${ctxAll}/new_page/css/index.css" />
	<script type="text/javascript" src="${ctxAll}/new_page/js/jquery.js"></script>
	 <script src="${ctxAll}/js/jquery-1.11.1.min.js"></script>
	 <script src="${ctxAll}/js/jquery.mobile.custom.min.js"></script>
	 <script src="${ctxAll}/js/jquery.bxslider.js"></script>
	 <script src="${ctxAll}/js/iconfont.js"></script>
	 <script src="${ctxAll}/js/main.min.js"></script>
	 <script src="${ctxAll}/new_page/js/jquery-1.8.3.min.js"></script>
	 <script src="${ctxAll}/new_page/js/imgzoom.js"></script>
	 <script src="${ctxAll}/new_page/js/imgload.js"></script>
	 <script type="text/javascript" src="${ctxAll}/js/My97Date/WdatePicker.js"></script>
	 
	 <link href="${ctxAll}/css/animate.min.css" rel="stylesheet">
	 <link href="${ctxAll}/css/jquery.bxslider.css" rel="stylesheet">
	 <link href="${ctxAll}/css/global.css" rel="stylesheet">
	     
     <link href="${ctxAll}/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
     <link href="${ctxAll}/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
     <link href="${ctxAll}/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    
     <link href="${ctxAll}/new_page/css/iconfont/iconfont.css" rel="stylesheet">    
    
    
    <style type="text/css">
    .xl{
    margin-left: 30px;
    display: block;
    position: absolute;
    left: 751px;
   margin-top:7px;
   
    z-index: -99;
    color:rgb(18,188,225);
    
    }
    .selp{
    
    height: 30px;
    width: 70%;
    margin-top: -52px;
    
    }
    
    
   .half_cont li{
    padding-bottom: 8px;
   }
   
   .pd{
    padding-bottom: 8px;
   
   
   }
   
  
    
    #shade{
    width: 100%;
    height: 100%;
    position: absolute;
    padding: 0;
    margin: 0;
    left: 0;
    top: 0;
    background-color: rgba(128, 128, 128, 0.18);
    z-index: 1;
    display: none;
     
    }
    
    .zoom{
    z-index: 0;
    
    }
    
    
    </style>
 
	 
	 
</head>
<body>
<div id="shade"></div>
<div class="container">
        <div class="content no_padding" style="width:90%">
        <div class="tabs" style="margin-top: 90px;">
            <ul class="tab_list">
                <li id="tab1" class="tab active">基本信息</li>
                <li id="tab2" class="tab">病例信息</li>
            </ul>
            <div class="tab1 tab_cont">
                <div class="half_cont">
                    <ul>
                     			<li>学科：${courseStr}</li>
		                        <li>主题：</li>
		                        <li>患者姓名：${caseData.patientObject.PName}</li>
		                        <li>患者出生日期：${caseData.patientObject.birthday}</li>
		                        <li>患者身份证号：${caseData.patientObject.certificate}</li>
		                        <li>编号类型1：
		                        	<c:if test="${caseData.patientObject.number1Type == 0}">门诊号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 1}">住院号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 2}">床位号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 3}">其他编号</c:if>
		                        	${caseData.patientObject.number1}</li>
		                        <li>手机号：${caseData.patientObject.phoneNumber1}</li>
                    </ul>
                    
                </div>
                <div class="half_cont">
                 <ul>
		                        <li>ICD10：${ICD}</li>
		                        <li>就诊日期：${caseData.patientObject.diagDate}</li>
		                        <li>性别：
		                        	<c:if test="${caseData.patientObject.sex == 0}">男</c:if>
		                        	<c:if test="${caseData.patientObject.sex == 1}">女</c:if>
		                        </li>
		                        <li>年龄：${caseData.patientObject.pAge}</li>
		                        <li>国家库：
		                        	<c:if test="${caseData.patientObject.nationalState == 1}">是</c:if>
		                        	<c:if test="${caseData.patientObject.nationalState != 1}">否</c:if>
		                        </li>
		                        <li>编号类型2：
		                        	<c:if test="${caseData.patientObject.number2Type == 0}">门诊号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 1}">住院号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 2}">床位号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 3}">其他编号</c:if>
		                        	${caseData.patientObject.number2}
		                        </li>
		                        <li>固定电话：${caseData.patientObject.phoneNumber2}</li>
		                    </ul>
                </div>
                     <p class="desc">诊断：
		                	<%-- <script>var diagIndex=1;</script>
							<c:forEach items = "${caseData.patientDiagnosis}" var = "diagnosis" varStatus="status">
								<div class="mt10 paDiag1">
									<div class="fl shitin_xinzeng01">
										<p class="fl" style="width:680px; overflow:auto; height:auto;"><span class="fl diagnose" style="text-align:left">诊断${caseData.patientDiagnosisObject}</span></p>										
									</div>
									<div class="clear"></div>
								</div>
							</c:forEach> --%>
							诊断：${caseData.patientDiagnosisObject}
		                </p>
            </div>
            <div class="tab2 tab_cont" style="display:none">
                <div class="tabs tag_tabs">
                    <ul class="tab_list">
                        <li id="tab3" class="tab active">主诉</li>
                        <li id="tab4" class="tab">现病史</li>
                        <li id="tab5" class="tab">个人史</li>
                        <li id="tab6" class="tab">体征</li>
                        <li id="tab7" class="tab">辅助检查</li>
                        <li id="tab8" class="tab">诊断</li>
                        <li id="tab9" class="tab">治疗方案</li>
                        <li id="tab10" class="tab">预后</li>
                    </ul>
                    <div class="tab3 tab_cont">
                        <div class="half_cont">
                            <ul>
                                <li>主诉:${caseData.caseDiseaseObject.complaint}</li>
                            </ul>
                        </div>
                      
                    </div>
                    <div class="tab4 tab_cont" style="display:none">
                        <p>现病史：${caseData.caseDiseaseObject.currentDisease}</p>
                    </div>
                    <div class="tab5 tab_cont" style="display:none">
                        <ul>
                            <li class="pd">既往史：${caseData.caseDiseaseObject.history1}</li>
		                    <li class="pd">个人史：${caseData.caseDiseaseObject.history2}</li>
		                    <li class="pd">过敏史：${caseData.caseDiseaseObject.history3}</li>
		                    <li class="pd">家族史：${caseData.caseDiseaseObject.history4}</li>
                        </ul>
                    </div>
                    <div class="tab6 tab_cont" style="display:none;height: 140px;">
                        <div class="half_cont">
                            <ul>
                               <li>神经系统：
			                                    <c:if test="${caseData.caseDiseaseObject.bodyState1Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 1}">异样</c:if>
											</li>
		                                    <li>头部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>颈部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>胸部：
		                                   		<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 1}">异样</c:if>	
		                                    </li>
		                                    <li>腹部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>四肢：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>皮肤：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>淋巴：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 1}">异样</c:if>
		                                    </li>
                            </ul>
                        </div>
                    </div>
                    <div class="tab7 tab_cont" style="display:none">
		                  <p>化验：<img src="${caseData.caseDiseaseObject.assayFile}" width="50px;" height="50px;" class="zoom pd" onerror="imgonload(this,1)"/>${caseData.caseDiseaseObject.assay}</p>
		                  <p>影像：<img src="${caseData.caseDiseaseObject.imageFile}" width="50px;" height="50px;" class="zoom pd" onerror="imgonload(this,1)"/>${caseData.caseDiseaseObject.image}</p>
		                  <p>其他：<img src="${caseData.caseDiseaseObject.restFile}" width="50px;" height="50px;" class="zoom pd" onerror="imgonload(this,1)"/>${caseData.caseDiseaseObject.rest}</p>
		                 
                    </div>
                    <div class="tab8 tab_cont" style="display:none">
                        <p>
                       	诊断 :${caseData.diseaseDiagnosisObject}
                        </p>
                    </div>
                    <div class="tab9 tab_cont" style="display:none">
                        <p>治疗方案：<img src="${caseData.caseDiseaseObject.planFile}" width="50px;" height="50px;" class="zoom" onerror="imgonload(this,1)"/>
                        ${caseData.caseDiseaseObject.plan}</p>
                    </div>
                    <div class="tab10 tab_cont" style="display:none">
                        <p>预后：${caseData.caseDiseaseObject.futureState}
                        </p>
                    </div>
                </div>

            </div>
            <div class="comment_top">
                <h1 class="main_title_2">
                 	   讨论点                  
                </h1>
               	 <p class="selp">
                 <i class="iconfont xl">&#xe604;</i>
					<select id ="discussId" onchange="selectDiscuss(this);" style="width: 700px; height:28px; margin-left:108px;border:1px solid #dddddd;padding-left:5px;">
						 <option value="">选择讨论点</option>
		                 <c:forEach items="${caseData.caseCourseDiscussList}" var="list" varStatus="status">
		                     <option <c:if test="${status.index==0}">selected="selected"</c:if> value="${list.id}">${status.index+1}:${list.prop}</option>
		                 </c:forEach>
					</select>  
                </p>
            </div>
             <div class="comment_form">
                <textarea name="comment_cont" class="comment_cont"></textarea>
                <div class="foot" style="margin-top:10px;">
                  <button name="comment_submit" id="comment_submit" type="button" class="btn btn_orange btn_small">评论</button>
                    <span class="text_count">还能输入<span class="num_count">200</span>字</span>
                </div>
            </div>
                    
                    
            <!-- 循环遍历讨论信息 -->
            <c:forEach items="${caseData.caseCourseDiscussList}" var="list">
              <div id="taolun" class="news_cont clearfix" style="width:90%">
                <ul class="comments_list">
                    <!-- 循环遍历讨论点对应的评论信息 -->
                    <c:forEach items="${list.discussList}" var="discussList">
                       <li>
		                    <span class="avatar tiny"><img src="${ctx}/images/user_avatar.jpg"></span>
		                    <p class="cont">${discussList.discussContent}</p>
		                    <p>
		                        <span class="float_right">
		                            <!-- <i class="fa fa-thumbs-o-up"></i>
		                            <i class="fa fa-reply"></i> -->
		                        </span>
		                        <span class="name">${discussList.systemUserName}</span>
		                        <span class="datetime">${discussList.discussDate}</span>
		                    </p>
	                   </li>
                    </c:forEach>
                 </ul>
              </div>
            </c:forEach>

        </div>
    </div>
</div>
<script>

    //讨论点
    var discussId =$("#discussId").val();
    
    //window.parent.QueryQuota(window.parent.unitId);

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
        	if(!$("#discussId").val()){
        		alert("请选择讨论点.");
        		return;
        	}
        	//获取父级页面变量信息
        	var cvsetId = window.parent.projectId;
        	
        	var unitId = ${unitId} ;//window.parent.unitId;
        	
        	var url = window.parent.adminURL;
        	
        	var userId = window.parent.userId;
        	
        	//通过AJAX保存评论的信息
        	$.ajax({
        		url:"${ctxAll}/caseManage/caseSaveDiscuss.do",
     		    type: 'POST',
     		    dataType: 'json',
     		    data:{
     		    	cvsetId:cvsetId,
     		    	unitId:unitId,
     		    	content:content,
     		    	discussId:discussId,
     		    	caseId:'${caseId}',
     		    	userId:userId
     		    },
     		   success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		//重置信息
     		    		$(".comment_cont").val('');
     		    		//$("#discussId").val('');
     		    		//本页刷新
     		    		//location.reload();
     		    		//div局部刷新---by scp
     		    		if(result.isPass == '1') {
     		    			//parent.location.reload();
     		    			$('#pm' + unitId , window.parent.document).html('&#xe61c;');
     		    			$('#unit' + unitId , window.parent.document).css("color","#ADADAD");
     		    		}
     		    		$("#taolun").load(location.href+" #taolun"); 
     		    	}
     		    }
     		  
      		});
        	
   		 //window.parent.load();
        });
    });
    
    /*
     * @author 张建国
     * @time   2017-02-20
     * 说       明：选择讨论点
     */
    function selectDiscuss(obj){
    	var id = $(obj).val();
    	if(id!=null && id!=''){
    		discussId = id;
    	}
    }
    
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