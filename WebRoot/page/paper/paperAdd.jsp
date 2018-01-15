<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html>
	<head>
		<title>组卷</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/admin/admin.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay2.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/tip-green/tip-green.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
		
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/popupcheckbox2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.poshytip.js"></script>
		<script type="text/javascript" src="${ctx}/js/loading-min.js"></script>
		<script type="text/javascript" src="${ctx}/js/addPaper.js"></script>
	</head>
	<body>
		<div id="content">
			<form id="fm1" name="fm1" method="post">
				<div id="pBase">
					<table id="pBase_t" cellpadding="0" cellspacing="0" class="gridtable">
						<tr>
							<th colspan="4" style="text-align: center">新增试卷</th>
						</tr>
						<tr>
							<th>
								<font style="color: red;">*</font>试卷名称:</th>
							<td>
								<input type="text" name="name" id="name" maxlength="50" />
							</td>
							<th>试卷目录:</th>
							<td>
								<input type="text" name="typeName" id="typeName" readonly />
								<input type="hidden" name="typeId" id="typeId" />
							</td>
						</tr>
						<tr>
							<th>
								<font style="color: red;">*</font>试卷分数:
							</th>
							<td>
								<input type="text" name="paperScore" id="paperScore" maxlength="50" />
							</td>
							<th>分数显示:</th>
							<td>
								是 &nbsp;<input type="radio" name="isNotScore" checked />&nbsp;&nbsp;&nbsp;&nbsp;
								否 &nbsp;<input type="radio" name="isNotScore" />
							</td>
						</tr>
						<tr>
							<th>
								<font style="color: red;">*</font>试题数量:
							</th>
							<td>
								<input type="text" name="questionNum" id="questionNum" maxlength="50" />
							</td>
							<th>试卷导出:</th>
							<td>
								是 &nbsp;<input type="radio" value="2" name="isNotExp" checked />&nbsp;&nbsp;&nbsp;&nbsp;
								否 &nbsp;<input type="radio" name="isNotExp" value="1" />
							</td>
						</tr>
						<tr id="paper-Num">
							<th>试卷数量:</th>
							<td>
								<input type="text" name="childPaperNum" id="childPaperNum" maxlength="50" value="1" readonly="readonly" />
							</td>
						</tr>

						<tr id="">
							<th>策略分类:</th>
							<td colspan="3">
								<select name="paperMode" id="paperMode" style="width: 130px;">
									<option value="-1" selected>
										请选择策略
									</option>
									<option value="3">
										手工组卷
									</option>
								</select>
								<span style="margin-left: 10px;"><a id="showModeTip" href="#">策略说明</a></span>
							</td>
						</tr>
					</table>
				</div>

				<div id="modeTip">
					<div><a id="close" href="#">关闭</a></div>
					<dl id="faq">
						<dt style="text-align: center; font-size: 15;padding-top:10px;">
							策略说明
						</dt>
						<dt>
							手工组卷:
						</dt>
						<dd>
							用户设置试题查询条件，系统提供符合条件的试题，用户手工选择题目生成试卷。
						</dd>
						<dt>&nbsp;</dt>
						<dd>&nbsp;</dd>
						<%-- 
							<dt>
								快捷策略1:
							</dt>
							<dd>
								用户设定所需试卷的综合条件，由系统自动选题生成试卷。
							</dd>
							<dt>
								快捷策略2:
							</dt>
							<dd>
								快捷策略1升级版，可设置内容比例，由系统自动选题生成试卷。
							</dd>
	
							<dt>
								精细策略:
							</dt>
							<dd>
								用户对每种题型设置搜索条件，系统自动选题生成试卷。（暂未开放，研发中……）
							</dd>
							<dt>
								卷中卷:
							</dt>
							<dd>
								用户设置试题要求，系统从已有试卷中选择试题组成试卷。
							</dd>
						 --%>
					</dl>
				</div>

				<div id="extends">
					<input type="hidden" id="questType_2" name="questType_2" />
					<input type="hidden" id="question_label_ids" name="question_label_ids" />
					<input type="hidden" id="renderSelIds" name="renderSelIds" />
					<input type="hidden" id="questCognize_8_2" name="questCognize_8_2" />
					<input type="hidden" id="questTitle_9" name="questTitle_9" />
					<input type="hidden" id="seleQues" name="seleQues">
					<input type="hidden" id="papersIds" name="papersIds">
					<input type="hidden" id="midinfo" name="midinfo">


					
					<div id="mode_quick" class="modes">
						<table>
							<tr>
								<td class="mode_tital">
									<img src="${ctx}/images/qxztk.gif" id="txtIndDrop" name="txtIndDrop"/>
								</td>
								<td class="mode_content" >
									<textarea style="overflow: visible; padding: 3px;width: 99%;" class="xsxjc_search_dh2 inp_txt inp_txtsel inp_wm inp_cue" id="txtInd" name="txtInd" readonly>
									</textarea>
								</td>
							</tr>
							<tr>
								<td class="mode_tital" style="height: 200px;">
									<img src="${ctx}/images/qxztx.gif" id="txtIndDrop2" name="txtIndDrop2"/>
								</td>
								<td class="mode_content" style="height: 200px;">
								<div class="mode_quick_movediv" style="height: 200px !important;">
									<table style="overflow: visible;width: 98%;" class="paperInfo" id="txtInd2" name="txtInd2" >
										<tr>
											<th>题型</th>
											<th>试题数量</th>
											<th>所占分数</th>
										</tr>
									</table>
								</div>
								</td>
							</tr>
							<tr>
							<td class="mode_tital">
								<img src="${ctx}/images/qxznd.gif" id="txtIndDrop4" name="txtIndDrop4" />
							</td>
							<td class="mode_content">
								<textarea style="overflow: visible; padding: 3px;width: 99%;height: 30px;" class="xsxjc_search_dh2 inp_txt inp_txtsel inp_wm inp_cue" id="txtInd4" name="txtInd4" readonly>
								</textarea>
							</td>
							</tr>
							<tr>
							<td class="mode_tital">
								<img src="${ctx}/images/qxzzc.gif" id="txtIndDrop6" name="txtIndDrop6" />
							</td>
							<td class="mode_content">
								<textarea style="overflow: visible; padding: 3px;width: 99%;height: 30px;" class="xsxjc_search_dh2 inp_txt inp_txtsel inp_wm inp_cue" id="txtInd6" name="txtInd6" readonly>
								</textarea>
							</td>
							</tr>
							<tr>
							<td class="mode_tital" style="height: 300px;">
								<img src="${ctx}/images/qxznr.gif" style="margin-top: 20px;" id="txtLocDrop" name="txtLocDrop"/>
							</td>
							<td class="mode_content" style="height: 300px;">
							<div class="mode_quick_movediv" style="height: 300px !important;">
								<table style="overflow: visible;width: 98%;" class="paperInfo" id="txtLoc" name="txtLoc" >
									<tr>
										<th width="45%">内容</th>
										<th width="20%">请分配内容比例</th>
										<th width="35%">说明</th>
									</tr>
								</table>
							</div>
							</td>
							</tr>
						</table>
						<div class="mode_end">
							<!-- 
								<img src="${ctx}/images/bcmb_qbcz.gif" />
								<img src="${ctx}/images/scmb.gif" width="85" height="25"/>
							 -->
							<img src="${ctx}/images/xjsj_fh.gif" width="57" height="25" onclick="window.history.back()"/>
							<img id="mode_quick_submit" src="${ctx}/images/xjsj_bc.gif" />
						</div>
					</div>
					
					


					<div id="mode_handmade" class="modes">
						<div id="mode_handmade_cont">
							<table id="mode_handmade_cont_t" cellpadding="0" cellspacing="0">
								<tr id="mode_handmade_cont_th">
									<th width="10%">题型</th>
									<th width="12%">题库</th>
									<th width="35%">行业</th>
									<th width="6%">每题分数</th>
									<th width="6%">知识分类</th>
									<th width="6%">适用对象</th>
									<th width="10%">操作</th>
								</tr>
								<tr>
									<td>
										<select id="question_label_id" name="question_label_id">
											<c:forEach items="${questLableList}" var="lable">
												<option value="${lable.id }">
													${lable.name }
												</option>
											</c:forEach>
										</select>
									</td>
									<td>
										<input type="button" class="btn_04s" value="设置" id="txtIndDrop_hand" name="txtIndDrop_hand" />
									</td>
									<td>
										<input type="button" class="btn_04s" value="设置" id="txtLocDrop_hand" name="txtLocDrop_hand" />
									</td>
									<td>
										<input type="text" size="10" id="quesScor" name="quesScor" value="1" />
									</td>
									<td>
										<input type="button" class="btn_04s" value="设置" id="txtIndDrop4_hand" name="txtIndDrop4_hand" />
									</td>
									<td>
										<input type="button" class="btn_04s" value="设置" id="txtIndDrop6_hand" name="txtIndDrop6_hand" />
									</td>
									<td>
										<input type="button" class="btn_03s" value="搜 索" id="sele-link-hand" />
									</td>
								</tr>
							</table>
						</div>

						<div class="mode_end">
							剩余题量：
							<input name="hand_quesNum" type="text" class="xzsj_input" id="hand_quesNum" style="margin-right: 30px;" readonly />
							剩余分数：
							<input name="hand_quesSocr" type="text" class="xzsj_input" id="hand_quesSocr" readonly />
						</div>
						<div id="mode_fine_top" style="padding-bottom:10px;">
							<input type="button" class="btn_03s" value="预 览" id="showQues-link-hand" />
							<input type="button" class="btn_03s" value="返 回" id="back-link-hand" />
							<input type="button" class="btn_03s" value="保 存" id="submit-link-hand" />
						</div>
					</div>
					
				</div>
			</form>
		</div>
		<div id="mask" style="top: 0pt; left: 0pt; position: absolute; z-index: 1000; height: 1188px; display: none;" class="bg62"></div>


		<div class="sech indx">
			<div class="sechbar">
				<div id="popupSelector" style="position: absolute; z-index: 1777; top: 0pt; left: 50px; display: none;">
					<div id="pslayer" class="alert_lay sech_lay lm lay_wl">
						<!--背景圆角上-->
						<div class="alert_t"></div>
						<div class="box">
							<h1>
								<span id="psHeader"></span><a href="javascript:void(0);" class="butn3" id="imgClose"> </a>
							</h1>
							<div class="blk">
								<div style="display: none;" id="divSelecting" class="sech_layt">
									<h3>
										<span id="selectingHeader"></span>
										<b class="btn_fst">
											<input id="btnCheckAll" name="" class="btn_fst" value="全选" type="button">
											<input id="lnkOK" name="" value="确定" class="btn_fst" type="button">
											<input id="lnkEmpty" name="" value="清空" class="butde" type="button">
										</b>
									</h3>
									<ul id="selecting"></ul>
								</div>
								<div style="display: none;" id="noSelectedLoc" class="sech_layt btn_fst">
									<h3>
										<span>提示：</span>
										<b>
											<input type="button" id="btnOkLoc" name="" class="fst" value="确定" />
											<input type="button" id="" name="" class="butdef_n" value="清空" disabled="disabled">
										</b>
									</h3>
									<p>
										请您通过＂<img src="${ctx}/images/ico1.gif" alt="">＂至少选择一个行业，您最多可以选择15个行业
									</p>
								</div>
								<div class="sech_layb">
									<h2 style="display: none;" id="subHeader1">
										<span></span>
									</h2>
									<ol id="allItems1"></ol>
									<h2 style="display: none;" id="subHeader2">
										<span></span>
									</h2>
									<ol id="allItems2"></ol>
								</div>
							</div>
						</div>
						<!--背景圆角下-->
						<div class="alert_b">
							<img src="ttt/laybj_br.gif" alt="">
						</div>
					</div>
				</div>
				<div id="subItems" class="alert_lay sech_lay2 lay_wl2" style="position: absolute; z-index: 1778; top: 0pt; left: 554px; display: none;">
					<!--背景圆角上-->
					<div class="alert_t"></div>
					<div id="subBox" class="box"></div>
					<!--背景圆角下-->
					<div class="alert_b">
						<img src="ttt/laybj_br.gif" alt="">
					</div>
				</div>
				<div id="thirdItems" class="alert_lay sech_lay2 lay_wl2" style="display: none; position: absolute; z-index: 1779;">
					<!--背景圆角上-->
					<div class="alert_t"></div>
					<div id="thirdBox" class="box"></div>
					<!--背景圆角下-->
					<div class="alert_b"></div>
				</div>
				<div id="fourthItems" class="alert_lay sech_lay2 lay_wl2" style="display: none; position: absolute; z-index: 1780;">
					<!--背景圆角上-->
					<div class="alert_t"></div>
					<div id="fourthBox" class="box"></div>
					<!--背景圆角下-->
					<div class="alert_b"></div>
				</div>
				<div id="fifthItems" class="alert_lay sech_lay2 lay_wl2" style="display: none; position: absolute; z-index: 1781;">
					<!--背景圆角上-->
					<div class="alert_t"></div>
					<div id="fifthBox" class="box"></div>
					<!--背景圆角下-->
					<div class="alert_b"></div>
				</div>
			</div>
		</div>




	</body>
</html>