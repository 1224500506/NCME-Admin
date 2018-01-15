<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>
<script type="text/javascript" src="${ctx}/new_page/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<body class="bjs">
<div>
	
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">
				<c:if test="${info.id==null || info.id==0 }">添加学组</c:if>
				<c:if test="${info.id>0}">修改学组</c:if>
				</h2>
			</div>
			<form id="frm" action="${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=save" method="POST">
			<div class="thi_shitineirong" style="">
				<ul>
					<li class="mt30">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学组名称：</span><em class="fr">*</em></p>
							<input type="hidden" name="id" id="id" value="${info.id}" />
							<input type="text" id="rsName" class="fl tki_bianji" style="width:258px;" maxlength = 50 name="name" value="${info.name}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">专委会：</span></p>
							<input type="hidden" class="fl tki_bianji" style="width:258px;" name="parent" id = "parent" value="${gid}"/>
							<input type="text" class="fl tki_bianji" style="width:258px;" value="${parentName}" id = "parentName" readonly/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
					 	<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr" id="display_expert_type">学组长：</span></p>
                            <input type="text" class="fl tki_bianji" style="width:258px;" readonly="readonly" name="expertName" id="expertName" value="${expertInfo.name}" />
							<input name="manager" id="manager" type="hidden" value="${expertInfo.id}" />
							<input name="managerOld" id="managerOld" type="hidden" value="${expertInfo.id}" />
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">成立时间：</span><em class="fr">*</em></p>
							<input type="text" class="fl tki_bianji breakdate01" style="width:258px;display:none;" value="" readonly />
							<input type="text" class="fl tki_bianji breakdate02" style="width:258px;display:none;" name="breakDate" id = "breakDate" value="<fmt:formatDate value="${info.breakDate}" pattern="yyyy-MM-dd"/>"  onClick="WdatePicker()" readonly />
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">联系人：</span><em class="fr">*</em></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="contact" id = "contact" maxlength = 20 value="${info.contact}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">手机号：</span><em class="fr">*</em></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="phone2" id = "phone2" maxlength = 11 value="${info.phone2}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">邮箱：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="email" id = "email" maxlength = 50 value="${info.email}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
							<a href="${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=list" class="fl ml30 anniu">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
			</form>
		</div>
		
	</div>
	
		<!-- 试题内容（结束） -->
	
<div class="clear"></div>
</div>


<script type="text/javascript">
$(function(){

	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//下拉框
		$('.tik1_select').click(function(){
			 $(".tk1_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			breakdateEnable($(this).parent().find('option').eq($(this).index()-1).val());
			$('.tk1_list').slideUp(50);
		});
	//设置查询条件	
		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});
	//保存
		$('.att_baocun').click(function(){
			var re = /^[_a-z0-9-]+([_a-z0-9-]+)*@/i;
			if(!$('#rsName').val()){
				alert("请输入学组名称！");
				return;
			}else if(!$('#contact').val()){
				alert("请输入联系人！");
				return;
			}else if($('#phone2').val() == "" || $('#phone2').val().length != 11){
				alert("手机号必须得是11位！");
				return;
			}else if($('#breakDate').val() == ""){
				alert("请输入成立时间！");
				return;/*
			}else if($('#state').val() == 0){
				alert("请选择任职状态！");
				return;
			}else if($('#email').val() !="" && !re.test($('#email').val())){
				alert("邮箱格式不对！");
				return;*/
			}else{
				var url ="${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=save";
				var params = "rsName=" + $('#rsName').val();
				params += "&parent="+ $('#parent').val();
				params += "&email="+ $('#email').val();
				params += "&phone2="+ $('#phone2').val();
				params += "&contact="+ $('#contact').val();
				params += "&breakDate="+ $('#breakDate').val();
				params += "&manager="+ $('#manager').val();
				params += "&managerOld="+ $('#managerOld').val();
				params += "&id="+ $('#id').val();
				$.ajax({
							type: 'POST',
							url: url,
							data : params,
							dataType: 'text',
							success : function (b){
								if(b == "success")
								{
									alert('保存成功！');
									document.location.href = "${ctx}/expert/ExpertSubGroup.do?gid=${gid}" ;
								}
								else{
									alert('学组名称不能重复！');
								}
							},
							error: function(){
							   	alert('保存失败');
							   	document.location.href = document.location.href.split("#")[0];
							}
					});
			}
		});

	breakdateEnable(2);
	
	var teacher_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +

	'<div class="clear"></div>' +
	'<div class="clear teacher_Name" style="margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:80px;padding:10px;margin-top:10px;line-height:25px;">' +
	'</div>' +

	'<div class="clear"></div>' +
	'<p class="fl" style="margin-bottom:10px;"><input type="hidden" id="managerId" />' +
	'<input type="text" placeholder="请输入专家名称搜索" id="teacher" style="height:23px;">' +
	'</p>' +
	'<p class="fl cas_anniu" style="margin-left:20px;padding-top:0px;" id="add_teacher"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
	'<div class="clear"></div>' +
	'<div class="clear" id="teacherName" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;position:absolute;height:80px;width:530px;overflow:auto;">' +

	'</div>' +
	'</div>';

	$("#expertName").click(function () {
		var _this = $(this);
		var layerTitle = "专家" ;
		_this_id = $('input[name="manager"]');
		var currValTmp = '' ;
		layer.open({
			type: 1,
			title: layerTitle,
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "420px"], //宽高
			content: teacher_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {
			    _this_id.val($('#managerId').val());
			    _this.val($('.teacher_Name').text());
			    document.getElementById('expertName').innerHTML = _this.val() ;
				layer.close(index);
			},
			success: function(layerb, index){
				currValTmp = _this_id.val() ;
				if (currValTmp != "") { //加载
		            var s1 = "" ;
		            var s2= "" ;
	                if (_this.val() != "") {
		                var delNameR = "yhqName" + Math.floor(Math.random() * 10000 + 1) ;
		                s1 += '<em onClick="javascript:delem1Add(\'' + delNameR + '\');" class="delem" id="' + delNameR + '">' + _this.val() + "" + '</em>';
					    s2 += currValTmp + "";
				    }
		            $('.teacher_Name').html(s1);
					$('#managerId').val(s2);
				}
				$('#add_teacher').click(function(){
					// 更改下拉列表框的为单选按钮
					if($('.teacher_Name').html()){
						alert('已经添加专家，请移除后再添加！');
					}else{
						var str = ''; str2 = '';
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $('.chk_teacher:checked').prop("title") + "" + '</em>';
						str2 += $('.chk_teacher:checked').val()+"";
						$('.teacher_Name').html(str);
						$('#managerId').val(str2);
						$('.chk_teacher:checked').prop("checked", false);
					}
				});
				$('#teacher').keyup(function(e){
					var keyword = $(this).val().trim();
					if (keyword == "") return false;
					switch(e.keyCode){
						case 37:
						case 38:
						case 39:
						case 40:
						case 13:
						case 9:
						return false;
					}
					//var urll=encodeURI('${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacher').val()); xh-2017-08-23
					var pam = '';
					if($('#id').val()){
						pam = 'mode=searchByGroup&name=' + keyword + '&groupId=' + $('#parent').val() + '&subGroupId=' + $('#id').val();
					}else{
						pam = 'mode=searchByGroup&name=' + keyword + '&groupId=' + $('#parent').val();
					}
					$.ajax({
						type:'post',
						url:'${ctx}/expert/ExpertManage.do',
						data:pam,
						dataType:'json',
						success:function(result){
							var strTeacher = '';
							for(var i=0;i<result.name.length;i++){
								strTeacher += '<input type="radio" id="chk_teacher_' + result.name[i].id+'"  class="chk_teacher"  value="'+result.name[i].id+'" name="radioname" title="'+result.name[i].name+'" />'+result.name[i].name+'<br>';
							}
							$('#teacherName').html(strTeacher);
						}
					});
				});
			}
		});
	});
	
});


function delem1Add(emIdVal) {
	   var thisVal = $("#" + emIdVal).html() ;
	   var tnObj = $('.teacher_Name') ;
	   if (tnObj.length > 0) {
	      var tNameObj = tnObj[0].children ;
	      var strA = $('#managerId').val().split(',') ;
	      for (var itemXi = 0 ; itemXi < tNameObj.length ; itemXi++) {
	         if (tNameObj[itemXi].innerHTML ==  thisVal) {
	            var thisIdVal = $('#managerId').val() ;
	             thisIdVal = thisIdVal.replace(strA[itemXi] + "," , "") ;
	             $('#managerId').val(thisIdVal);
	             $("#" + emIdVal).remove() ;
	             break ;
	         }
	      }
	   }
}
function delem1(obj){
      var i ;
      var selstr ;
      var selarray ;
      var newnarray ;
      var deletecode = "";

      var delFromWhoObj ;
      delFromWhoObj = $('.teacher_Name').html() ;
      if (delFromWhoObj != undefined && $('#managerId').val() != undefined) {
           i = $(obj).index();
           selstr = $(obj).parent().html();
           selarray = selstr.split(",");
           newnarray = new Array();
           $(selarray).each(function(key, val){
			   if (key != i) newnarray.push(val);
		   });
		   $('.teacher_Name').html(newnarray.toString());
		   selstr = $('#managerId').val();
		   selarray = selstr.split(",");
		   newarray = new Array();
		   $(selarray).each(function(key, val){
		       if (key != i) newarray.push(val);
			   else
			   deletecode = val;
		   });
		   $('#managerId').val(newarray.toString());
      }

      delFromWhoObj = $('#xueke').val() ;
      if (delFromWhoObj != undefined) {
          i = $(obj).index();
          deletecode = "";
	      selstr = $('#xueke').val();
	      selarray = selstr.split(",");
		  newarray = new Array();
		  $(selarray).each(function(key, val){
			  if (key != i) newarray.push(val);
			  else
				  deletecode = val;
		  });
		  $('#xueke').val(newarray.toString());
		  var checklist = $('.attr_xueke04');
		  $(checklist).each(function(key, val){
			  if(deletecode == $(val).prop('id'))
				  $(val).siblings().eq(0).prop("checked", "");
		  });
      }

	  $('.delem1').off('click');
	  $('.delem1').click(function(){
		  delem1($(this));
	  });
}

function breakdateEnable(e)
{
	if (e == "2"){
		$('.breakdate02').show();
		$('.breakdate01').hide();
	}
	else{
		$('.breakdate02').show();
		$('.breakdate01').hide();
	}
}
</script>
</body>
</html>