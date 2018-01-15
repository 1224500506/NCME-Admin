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
				<i></i><h2 class="fl"><c:if test="${info.id eq null}">添加专委会</c:if><c:if test="${info.id ne null}">修改专委会</c:if></h2>
			</div>
			<form id="frm" action="${ctx}/expert/ExpertGroupManage.do?mode=save" method="POST">
			<div class="thi_shitineirong" style="">
				<ul>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">专委会名称：</span><em class="fr">*</em></p>
							<input type="hidden" name="id" id = "id" value="${info.id}" />
							<input type="hidden" name="state" id = "state" value="${info.state}" />
							<input type="text" id="rsName" class="fl tki_bianji" style="width:258px;" name="name" maxLength= 50 value="${info.name}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">学科：</span><em class="fr">*</em></p>
							<input type="hidden" id="groupIds" name="propIds" value="${info.propIds}"/>
							<div id="groupNames" class="fl tki_bianji takuang_xk">${info.propNames}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr" id="display_expert_type">主任委员：</span></p>
                            <input type="text" class="fl tki_bianji expertName" style="width:258px;" readonly="readonly" name="expertName1" id="expertName1" value="${info.maName}" />
							<input name="manager1" id="manager1" type="hidden" value="${info.ma}" />
							<input name="manager1Old" id="manager1Old" type="hidden" value="${info.ma}" />
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr" id="display_expert_type">副主任委员：</span></p>
                            <input type="text" class="fl tki_bianji expertName" style="width:258px;" readonly="readonly" name="expertName2" id="expertName2" value="${info.mbName}" />
							<input name="manager2" id="manager2" type="hidden" value="${info.mb}" />
							<input name="manager2Old" id="manager2Old" type="hidden" value="${info.mb}" />
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr" id="display_expert_type">秘书长：</span></p>
                            <input type="text" class="fl tki_bianji expertName" style="width:258px;" readonly="readonly" name="expertName3" id="expertName3" value="${info.mcName}" />
							<input name="manager3" id="manager3" type="hidden" value="${info.mc}" />
							<input name="manager3Old" id="manager3Old" type="hidden" value="${info.mc}" />
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">成立时间：</span><em class="fr">*</em></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "organizeDate" name="organizeDate"  onClick="WdatePicker({maxDate:'%y-%M-%d'})" readonly="readonly" value="<fmt:formatDate value="${info.organizeDate}" pattern="yyyy-MM-dd"/>"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">联系人：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "contact" maxLength = 20 name="contact" value="${info.contact}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">手机号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "phone2" maxLength = 11 name="phone2" value="${info.phone2}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">固定电话：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "phone1" maxLength = 13 name="phone1" value="${info.phone1}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">邮箱：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "email" name="email" maxLength = 50 value="${info.email}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">地址：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" id = "address" name="address" maxLength = 100 value="${info.address}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl "><span class="fr">简介：</span></p>
							<textarea class="fl tki_bianji" style="width:678px;height:80px" id = "summary" name="summary" onKeyup="javascript:view_count(this);" placeholder="最多500字" maxLength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)">${info.summary}</textarea>
						</div>
						<div class="clear"></div>
						<div  class="shitin_xinzeng">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="fl shitin_xinzeng">
							<p class="fl "><span class="fr">备注：</span></p>
							<textarea class="fl tki_bianji" style="width:678px;height:80px" id = "note" name="note" onKeyup="javascript:view_countNote(this);" placeholder="最多500字" maxLength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)">${info.note}</textarea>
						</div>
						<div class="clear"></div>
						<div  class="shitin_xinzeng">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNumNote" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
							<a href="${ctx}/expert/ExpertGroupManage.do" class="fl ml30 anniu">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
			</form>
		</div>
	</div>
		<!-- 试题内容（结束） -->
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
	
		$('.att_baocun').click(function(){
			var patrn = /^[0-9]{3,4}[-,－][0-9]{7,8}$/;
			var re = /^[_a-z0-9-]+([_a-z0-9-]+)*@/i;
			if(!$('#rsName').val()){
				alert("请输入专委会名称！");
				return;
			}else if(!$('#groupIds').val()){
				alert("请选择学科！");
				return;
			}
// 			else if(!$('#contact').val()){
// 				alert("请输入联系人！");
// 				return;
// 			}
			else if($('#phone2').val() != "" && $('#phone2').val().length != 11){
				alert("手机号必须得是11位！");
				return;
			}else if(!$('#organizeDate').val()){
				alert("请输入成立时间！");
				return;
			}else if($('#phone1').val() != "" && !patrn.test($('#phone1').val())){
				alert("请按照如下格式填写固定电话：010-80101010!");
				return;/*
			}else if ($('#email').val() !="" && !re.test($('#email').val())){
				alert("邮箱格式不对！");
				return;*/
			}else{
				var url ="${ctx}/expert/ExpertGroupManage.do?mode=save";
				var params = "rsName=" + $('#rsName').val();
				params += "&propIds="+ $('#groupIds').val();
				params += "&summary="+ $('#summary').val();
				params += "&email="+ $('#email').val();
				params += "&phone1=" + $('#phone1').val();
				params += "&phone2="+ $('#phone2').val();
				params += "&contact="+ $('#contact').val();
				params += "&address="+ $('#address').val();
				params += "&note="+ $('#note').val();
				params += "&organizeDate="+ $('#organizeDate').val();
				params += "&manager1="+ $('#manager1').val();
				params += "&manager2="+ $('#manager2').val();
				params += "&manager3="+ $('#manager3').val();
				params += "&manager1Old="+ $('#manager1Old').val();
				params += "&manager2Old="+ $('#manager2Old').val();
				params += "&manager3Old="+ $('#manager3Old').val();
				params += "&id="+ $('#id').val();
				params += "&state="+ $('#state').val();
				$.ajax({
							type: 'POST',
							url: url,
							data : params,
							dataType: 'text',
							success : function (b){
								if(b == "success")
								{
									alert('保存成功！');
									document.location.href = "${ctx}/expert/ExpertGroupManage.do";
								}
								else if(b == "repeatname"){
									alert('名称不能重复！');
								}
								else{
									alert('保存失败！');
								}
							},
							error: function(){
							   	alert('保存失败');
							   	document.location.href = document.location.href.split("#")[0];
							}
				});
			}
		});
		
		$('.takuang_xk').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 3, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		
	//init avilable input character num
		$('#summary').each(function(){
			view_count($(this));
		});	
		$('#note').each(function(){
			view_countNote($(this));
		});	
		
		
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

		$(".expertName").click(function () {
			var _this = $(this);
			var layerTitle = "专家" ;
			if(_this.attr('name')=='expertName1'){
				_this_id = $('input[name="manager1"]');
				_this_expertName = 'expertName1';
			}else if(_this.attr('name')=='expertName2'){
				_this_id = $('input[name="manager2"]');
				_this_expertName = 'expertName2';
			}else if(_this.attr('name')=='expertName3'){
				_this_id = $('input[name="manager3"]');
				_this_expertName = 'expertName3';
			}
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
					if (_this_expertName!='expertName1'&&StringHaveRepeat($('#managerId').val())) {
				          alert('已选人员有重复，请重新选择！') ;
				          return;
				    }
					if(_this_expertName!='expertName1'){
						_this_id.val($('#managerId').val().slice(0, -1));
					    _this.val($('.teacher_Name').text().slice(0, -1));
					}else{
						_this_id.val($('#managerId').val());
					    _this.val($('.teacher_Name').text());
					}
				    document.getElementById(_this_expertName).innerHTML = _this.val() ;
					layer.close(index);
				},
				success: function(layerb, index){
					currValTmp = _this_id.val() ;
					if (currValTmp != "") { //加载
			            var s1 = "" ;
			            var s2= "" ;
			            if(_this_expertName!='expertName1'){
							var strA = currValTmp.split(',') ;
				            var strB = _this.val().split(',') ;
				            for (var xI in strB) {
				               if (strB[xI] != "") {
					               var delNameR = "yhqName" + Math.floor(Math.random() * 10000 + 1) ;
					               s1 += '<em onClick="javascript:delem1Add(\'' + delNameR + '\');" class="delem" id="' + delNameR + '">' + strB[xI] + "," + '</em>';
								   s2 += strA[xI] + ",";
							   }
				            }
			            }else{
			                if (_this.val() != "") {
				                var delNameR = "yhqName" + Math.floor(Math.random() * 10000 + 1) ;
				                s1 += '<em onClick="javascript:delem1Add(\'' + delNameR + '\');" class="delem" id="' + delNameR + '">' + _this.val() + "" + '</em>';
							    s2 += currValTmp + "";
						    }
			            }
			            $('.teacher_Name').html(s1);
						$('#managerId').val(s2);
					}
					$('#add_teacher').click(function(){
						// 更改下拉列表框的为单选按钮
						if(_this_expertName!='expertName1'){
							var str = ''; str2 = '';
							$('.chk_teacher:checked').each(function(){
								str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + "," + '</em>';
								str2 += $(this).val()+",";
							});
							$('.teacher_Name').html($('.teacher_Name').html() + str);
							$('#managerId').val($('#managerId').val()+str2);
							$('.chk_teacher:checked').each(function(){
								$(this).prop("checked", false);
							});
						}else{
							if($('.teacher_Name').html()){
								alert('已经添加专家，请移除后再添加！');
								return;
							}else{
								var str = ''; str2 = '';
								str += '<em onClick="javascript:delem1(this);" class="delem">' + $('.chk_teacher:checked').prop("title") + "" + '</em>';
								str2 += $('.chk_teacher:checked').val()+"";
								$('.teacher_Name').html(str);
								$('#managerId').val(str2);
								$('.chk_teacher:checked').prop("checked", false);
							}
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
							pam = 'mode=searchByExpertGroup&name=' + keyword 
								+ '&expertType=' + _this_expertName 
								+ '&manager1=' + $('#manager1').val() 
								+ '&manager2=' + $('#manager2').val() 
								+ '&manager3=' + $('#manager3').val()
								+ '&groupId=' + $('#id').val();
						}else{
							pam = 'mode=searchByExpertGroup&name=' + keyword
								+ '&expertType=' + _this_expertName 
								+ '&manager1=' + $('#manager1').val() 
								+ '&manager2=' + $('#manager2').val() 
								+ '&manager3=' + $('#manager3').val();
						}
						$.ajax({
							type:'post',
							url:'${ctx}/expert/ExpertManage.do',
							data:pam,
							dataType:'json',
							success:function(result){
								var strTeacher = '';
								for(var i=0;i<result.name.length;i++){
									if(_this_expertName!='expertName1'){
										strTeacher += '<input type="radio" id="chk_teacher_' + result.name[i].id+'"  class="chk_teacher"  value="'+result.name[i].id+'" name="'+result.name[i].name+'" />'+result.name[i].name+'<br>';
									}else{
										strTeacher += '<input type="radio" id="chk_teacher_' + result.name[i].id+'"  class="chk_teacher"  value="'+result.name[i].id+'" name="radioname" title="'+result.name[i].name+'" />'+result.name[i].name+'<br>';
									}
								}
								$('#teacherName').html(strTeacher);
							}
						});
					});
				}
			});
		});
});

function StringHaveRepeat(strVal) {
	   var repeatFlag = false ;
	   var ary = strVal.split(',') ;
	   var nary=ary.sort();
	   for(var i=0;i<ary.length;i++) {
	      if (nary[i] != "" && nary[i+1] != "" && nary[i] == nary[i+1]){
	          repeatFlag = true ;
	          break ;
		  }
	   }
	   return repeatFlag ;
}

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

function view_count(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}	
function view_countNote(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNumNote');
	$(spell).text(left_count);
}	

//手机校验
$('#phone2').blur(function(){
	if($(this).val().length != 11 && $(this).val().length != 0){
  		alert("手机号码不正确！");
  		$('#phone2').val('');
  		return false;
  	}else{
  		var obj = document.getElementById("phone2");
  	    var value = obj.value;
  	    var regTel1 = /^1(3|4|5|7|8)\d{9}$/.test(value);
  	    if (value != "") {
  	      if (!regTel1) { 
  	        alert("手机号输入有误！");
  	        $('#phone2').val("");
  	        return false;
  	      }
  	    }
  	}
});

//固话校验
$('#phone1').blur(function(){
  	var patrn = /^[0-9]{3,4}[-,－][0-9]{8,9}$/;
	if($(this).val() =="" || !patrn.test($(this).val()))
	{
		alert("固定电话号码不正确！请按照如下格式填写：123-12345678或1234-12345678");
		$(this).val('');
		return false;
	}
});

//邮箱校验
$('#email').blur(function(){
	var obj = document.getElementById("email");
    var value = obj.value;
    var regTel1 = /^[^\.@]+@[^\.@]+\.[a-z]+$/.test(value);
    if (value != "") {
      if (!regTel1) { 
        alert("邮箱输入有误！");
        $('#email').val("");
        return false; 
      }
    }
});
</script>
</body>
</html>