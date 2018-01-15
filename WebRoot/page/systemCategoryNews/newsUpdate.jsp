<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加新闻</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script src="${ctx}/js/ckeditor4/ckeditor.js"></script>  
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		<style type="text/css">
			
		</style>
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
			<div class="subnav" style="text-align:center">
				&nbsp;修改新闻
			</div>
			<table class="main_table" border="1"
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1" enctype="multipart/form-data"
								action="${ctx}/system/SystemNews.do?method=update"
								method="post">
								<input type="hidden" id="categoryId" name="categoryId" value="${categoryId }" />
								<input type="hidden" id="id" name="id" value="${systemNews.id }" />
						<div id="main" style="width: 100%; display: block;overflow:auto;">
							<table border="0"
								style="width: 60%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
								cellpadding="3" cellspacing="1" align="center">
								<tr valign="top">
									<td style="width: 90%;" align="center">
										<table border="1">
											<tr>
												<td>
													行业选择:
												</td>
												<td><input type="checkbox" onclick="selectAll(this)" value="-1">全选
													<c:forEach var="industry" items="${industryList }" varStatus="status">
														<input type="checkbox" name="systemIndustryIds" id="systemIndustryIds_${industry.id }" value="${industry.id }"/>&nbsp;&nbsp;${industry.industryName }
														<c:if test="${status.index!=0 &&status.index %5 == 0 && (status.index+1) != fn:length(industryList)}">
															<br/>
														</c:if>
													</c:forEach>
												</td>
											</tr>
								<tr>
									<td>
										<label for="create-form:create-name">
											文章类型:
										</label>
									</td>
									<td>
										
										<input type="radio" name="type" value="1" ${systemNews.type==1?'checked':'' }
											onclick="selectAddType(this.value)"
											checked/>
										普通文章
										<input type="radio" name="type" value="2" ${systemNews.type==2?'checked':'' }
											onclick="selectAddType(this.value)"
											  />
										下载资源
										<input type="radio" name="type" value="3" ${systemNews.type==3?'checked':'' }
											onclick="selectAddType(this.value)"
											 />
										外部连接
									</td>
								</tr>
								<!-- 外连接栏目部分 start -->
								<tr id="linkId" >
									<td>
										<label for="create-form:create-name">
											连接地址:
										</label>
									</td>
									<td>
										<input type="text" name="url" id="url" class="inputa" size="30" value="${systemNews.url }"/>
										<span> 必须以http://开始 </span>
									</td>
								</tr>
								<!-- 外连接栏目部分 end -->
								<!-- 如果是父栏目的subCateImageForeignLink==1 则不显示  start-->
								<tr>
									<td>
										<label for="post-form:post-title">
											标 题:
										</label>
									</td>
									<td>
										<input type="text" name="title" class="inputa" id="title" value="${systemNews.title }"
											size="36" maxlength="40" />
											<span style="color:red">*</span>
										<span class="description msg">最长50个字。</span>
									</td>
								</tr>
								<tr id="contentId4" >
									<td>
										<label for="post-form:post-author">
											作者:
										</label>
									</td>
									<td>
										<input id="post-form:post-author" type="text" class="inputa" value="${systemNews.author }"
											name="author" size="36" maxlength="40" />
									</td>
								</tr>
								<tr id="contentId5" >
									<td>
										<label for="post-form:post-original">
											来源:
										</label>
									</td>
									<td>
										<input id="original" type="text" id="" class="inputa" value="${systemNews.original }"
											name="original" size="36" maxlength="40"/>
									</td>
								</tr>
								<tr id="contentId3" style="overflow:auto;">
									<td>
										<label for="post-form:post-content">
											内容:
										</label>
									</td>
									<td>

										<textarea cols="80" id="editor1" name="content" rows="5">${systemNews.content }</textarea>
										<script type="text/javascript">
											CKEDITOR.replace( 'editor1', {
												 toolbar: 'Basic',
												 height:'50px'
											});
							
										</script>

									</td>
								</tr>

								<tr id="relateCateId" >
									<td>
										<label for="post-form:post-image">
											上传资源:
										</label>
									</td>
									<td>
										<input type="file" name="file" id="file" /><span class="STYLE1">文件大小限制在10M内</span>
										<c:if test="${systemNews.type==2&& systemNews.url!=null }">
											附件:<a href="${ctx}/${systemNews.url}" target="_blank" title="点击查看附件">${systemNews.url}</a>
										</c:if>
									</td>
								</tr>
								
								<tr >
								    <td>&nbsp;</td>
								    <td align="center">
								    	<input type="button" onclick="checkForm()" id="button" value="更新" class="btn_03s">
								    	<input type="button" class="btn_03s"  value="返回"
											onclick="window.location='${ctx}/system/SystemNews.do?method=list&categoryId=${categoryId }'" />
								    </td>
								  </tr>
										</table>
									</td>
								</tr>
							</table>

						</div>

						</form>
						
					</td>
				</tr>
			</table>
	<script type="text/javascript">
	//选中限制    id="systemIndustryIds_${industry.id }"
	<c:forEach var="industry" items="${systemNews.systemIndustryList }" varStatus="status">
		$("#systemIndustryIds_${industry.id}").attr("checked","true");
	</c:forEach>
	
	var nowType = ${systemNews.type};
	function selectAddType(type) {
		var linkElem = document.getElementById("linkId");
	    var contentElem3 = document.getElementById("contentId3");
		var contentElem4 = document.getElementById("contentId4");
		var contentElem5 = document.getElementById("contentId5");
		var relateCateElem = document.getElementById("relateCateId");
		nowType = type;
		if(type ==1){
			linkElem.style.display="none";
			contentElem3.style.display="block";
			contentElem4.style.display="block";
			contentElem5.style.display="block";
			relateCateElem.style.display="none";
			//relateCateElem2.style.display="none";
		}
		else if(type==2){
			linkElem.style.display="none";
			contentElem3.style.display="none";
			contentElem4.style.display="none";
			contentElem5.style.display="none";
			relateCateElem.style.display="block";
			//relateCateElem2.style.display="block";
		}
		else if(type==3){
			linkElem.style.display="block";
			contentElem3.style.display="none";
			contentElem4.style.display="none";
			contentElem5.style.display="none";
			relateCateElem.style.display="none";
			//relateCateElem2.style.display="none";
		}
	}
	selectAddType(nowType);
	function checkForm(){
		if($("#title").val() == ""){
			alert("请输入标题!");
			return;
		}else if($("#title").val().length > 50){
			alert("标题长度不能大于50!");
			return;
		}
		if(nowType == 2){
			if($("#file").val() == ""){
				alert("请上传文件!");
				return;
			}
		}else if(nowType == 3){
			var url = $("#url").val();
			if(url == ""){
				alert("请输入链接地址!");
				return;
			}
			if(url.split("ttp://").length!=2){
				alert("链接地址必须以http://开始");
				return;
			}
			if(url.split("ttp://")[0] != "h"){
				alert("链接地址必须以http://开始");
				return;
			}
		}
		$("#fm1").submit();
	}
	
	//行业全选/全不选
	function selectAll(obj){
    	if($(obj).attr("checked") == "checked"){
    		$("input[name='systemIndustryIds']").attr("checked", true);
    	} else {
    		$("input[name='systemIndustryIds']").attr("checked", false);
    	}
        
	}
	</script>
	</body>
</html>
