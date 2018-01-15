<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/new_page/top.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资源管理平台</title>
</head>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script>
    var filefmtArray = [[], ["MP4", "WMV", "RMVB", "MKV", "FLV"], ["JPG", "PNG", "JPEG", "BMP", "GIF"], ["PPT", "PPTX"], ["DOC", "DOCX", "PDF", "TXT"], ["RAR", "ZIP", "7Z"]];
</script>
<body class="bjs" onkeydown="entersearch();">
<div>
    <!-- 题库内容 -->
    <div class="center">
        <div class="tk_center01" style="min-height:100px;">
            <form name="sfrm" id="sfrm" action="${ctx}/material/MaterialManage.do" method="POST">
                <div class="tk_zuo">
                	<div class="mt10 mll8 fl tk_tixing"  style="margin-left: -45px;">
                        <span class="fl">素材名称：</span>
                        <input type="text" class="fl tik_select" name="name" value="${info.name}"/>
                    </div>
                    <div class="tik_xuanze">
                        <div class="mt10 fl tk_tixing"  style="margin-left: 20px;">
                            <em class="fl">学科：</em>
                            <input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>
                            <input type="hidden" class="fl tik_select" id="propNames" name="propNames"
                                   value="${propNames}"/>
                            <div class="fl tik_select02" id="propNames01">${propNames}</div>
                        </div>
                    </div>
                    <div class="mt10 mll8 fl tk_tixing"  style="margin-left: -10px;">
                        <span class="fl">ICD10：</span>
                        <input type="hidden" class="fl tik_select" id="icdIds" name="icdIds" value="${icdIds}"/>
                        <input type="hidden" class="fl tik_select" id="icdNames" name="icdNames" value="${icdNames}"/>
                        <div class="fl tik_select01 takuang_xk" id="icdNames01">${icdNames}</div>
                        <%-- 						<input type="text"  id="ICD" name="ICD" class="fl tik_select" readonly value="${ICD}"/>
                         --%>                    </div>
                    <div class="mt10 mll8 fl tk_tixing"  style="margin-left: -10px;">
                        <span class="fl">状态：</span>
                        <div class="fl tik_select">
                            <div class="tik_position_2">
                                <b class="ml5">请选择</b>
                                <p class="tik_position01"><i class="tk_bjt10"></i></p>
                            </div>
                            <ul class="fl tk_list1 tk_list " style="display:none;">
                                <select name="state" style="display:none;">
                                    <option value="">请选择</option>
                                    <option value="1"<c:if test="${info.state==1}"> selected</c:if>>未上传</option>
                                    <option value="2"<c:if test="${info.state==2}"> selected</c:if>>未审核</option>
                                    <option value="3"<c:if test="${info.state==3}"> selected</c:if>>不合格</option>
                                    <option value="4"<c:if test="${info.state==4}"> selected</c:if>>合格</option>
                                    <option value="5"<c:if test="${info.state==5}"> selected</c:if>>禁用</option>
                                </select>
                                <li>请选择</li>
                                <li>未上传</li>
                                <li>未审核</li>
                                <li>不合格</li>
                                <li>合格</li>
                                <li>禁用</li>
                            </ul>
                        </div>
                    </div>
                    <div class="mt10 fl tk_tixing"  style="margin-left: 3px;">
                        <em class="fl">类型：</em>
                        <div class="fl tik_select">
                            <div class="tik_position">
                                <b class="ml5">请选择</b>
                                <p class="tik_position01"><i class="tk_bjt10"></i></p>
                            </div>

                            <ul id="type" class="fl tk_list1 tk_list " style="display:none;">
                                <select name="type" style="display:none;">
                                    <option value="">请选择</option>
                                    <option value="1"<c:if test="${info.type==1}"> selected</c:if>>视频</option>
                                    <option value="2"<c:if test="${info.type==2}"> selected</c:if>>图片</option>
                                    <option value="3"<c:if test="${info.type==3}"> selected</c:if>>PPT</option>
                                    <option value="4"<c:if test="${info.type==4}"> selected</c:if>>文本</option>
                                    <option value="5"<c:if test="${info.type==5}"> selected</c:if>>压缩包</option>
                                </select>

                                <li>请选择</li>
                                <li>视频</li>
                                <li>图片</li>
                                <li>PPT</li>
                                <li>文本</li>
                                <li>压缩包</li>
                            </ul>
                        </div>
                    </div>
                    <div class="mt10 mll8 fl tk_tixing"  style="margin-left: -28px;">
                        <span class="fl">主题：</span>

                        <input type="hidden" class="fl tik_select" id="zutiIds" name="zutiIds" value="${zutiIds}"/>
                        <input type="hidden" class="fl tik_select" id="zutiNames" name="zutiNames"
                               value="${zutiNames}"/>
                        <div class="fl tik_select02 takuang_xk01" id="zutiNames01">${zutiNames}</div>

                    </div>
                
                    <!-- <div class="clear"></div>
                    <div class="tik_xuanze"> -->
                        <div class="mt10 fl tk_tixing" style="margin-left: 10px;">
                            <em class="fl">入库时间：</em>
                            <input type="text" class="fl tik_select" style="width:85px;" name="upload_date"
                                   value="${upload_date}" onClick="WdatePicker()" readonly="readonly"/>
                            <em class="fl" style="padding:0px 3px 0px 3px;">至</em>
                            <input type="text" class="fl tik_select" style="width:85px;" name="upload_date1"
                                   value="${upload_date1}" onClick="WdatePicker()" readonly="readonly"/>
                        </div>
                        <div class="mt10 fl tk_tixing" style="margin-left:-10px;">
                            <span class="fl">审核时间：</span>
                            <input type="text" class="fl tik_select" style="width:86px;" name="deli_date"
                                   value="${deli_date}" onClick="WdatePicker()" readonly="readonly"/>
                            <em class="fl" style="padding:0px 3px 0px 3px;">至</em>
                            <input type="text" class="fl tik_select" style="width:86px;" name="deli_date1"
                                   value="${deli_date1}" onClick="WdatePicker()" readonly="readonly"/>
                        </div>
                    <!-- </div> -->
                    </div>
                    <div class="clear"></div>
                </div>

                <!-- 高级查询 -->
                <div class="tk_yingcang" style="display:none;">
                    <div class="tik_xuanze">
                        <div class="mt10 fl tk_tixing" style="">
                            <em class="fl">格<i style="padding:0px 14px 0px 14px;"></i>式：</em>
                            <div class="fl tik_select" style="width:323px;">
                                <div class="tik_position_3" style="width:323px;">
                                    <b class="ml5">请选择</b>
                                    <p class="tik_position02"><i class="tk_bjt10"></i></p>
                                </div>

                                <ul id="format" class="fl tk_list1 tk_list " style="display:none;width:323px;">
                                    <select name="format" style="display:none;">
                                        <option value="">请选择</option>
                                    </select>
                                    <li>请选择</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="tik_xuanze">
                        <div class="mt10 fl tk_tixing">
                            <em class="fl">简<i style="padding:0px 14px 0px 14px;"></i>介：</em>
                            <input type="text" class="fl tik_select" style="width:898px;" name="summary" value=""/>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="tik_xuanze">
                        <div class="mt10 fl tk_tixing">
                            <em class="fl">职<i style="padding:0px 14px 0px 14px;"></i>称：</em>
                            <input type="hidden" name="dutyIds" id="dutyIds" value="${dutyIds}">
                            <input type="hidden" name="dutyNames" id="dutyNames" value="${dutyNames}">
                            <div class="fl tik_select03 takuang_xk" id="dutyNames01">${dutysNames}</div>
                        </div>
                        <div class="mt10 fl tk_tixing" style="margin-left:159px;">
                            <span class="fl">认知水平：</span>
                            <input type="hidden" id="cognizeIds" name="cognizeIds" value="${cognizeIds}"/>
                            <input type="hidden" id="cognizeNames" name="cognizeNames" value="${cognizeNames}"/>
                            <div class="fl tik_select03 takuang_xk" id="cognizeNames01">${cognizeNames}</div>

                            <%-- <div class="fl tik_select" style="width:323px;">
                                <div class="tik_position_3" style="width:323px;">
                                    <b class="ml5"></b>
                                    <p class="tik_position02"><i class="tk_bjt10"></i></p>
                                </div>
                                <ul class="fl tk_list" style="display:none;width:323px;">
                                    <select name="cognize" style="display:none;">
                                        <option value="">请选择</option>
                                        <option value="1"<c:if test="${info.cognize==1}"> selected</c:if>>识记</option>
                                        <option value="2"<c:if test="${info.cognize==2}"> selected</c:if>>理解</option>
                                        <option value="3"<c:if test="${info.cognize==3}"> selected</c:if>>掌握</option>
                                        <option value="4"<c:if test="${info.cognize==4}"> selected</c:if>>应用</option>
                                    </select>
                                        <li>请选择</li>
                                        <li>识记</li>
                                        <li>理解</li>
                                        <li>掌握</li>
                                        <li>应用</li>
                                </ul>
                            </div> --%>
                        </div>
                    </div>
                    <input type="hidden" name="orderItem" id="orderItem" value="${orderItem}">
                    <div class="clear"></div>
                    <div class="tik_xuanze">
                        <div class="mt10 fl tk_tixing">
                            <em class="fl">来<i style="padding:0px 14px 0px 14px;"></i>源：</em>
                            <input type="hidden" name="laiIds" id="laiIds" value="${laiIds}">
                            <input type="hidden" name="laiNames" id="laiNames" value="${laiNames}">
                            <div class="fl tik_select03 takuang_xk" id="laiNames01">${laiNames}</div>
                        </div>
                        <div class="mt10 fl tk_tixing" style="margin-left:159px;">
                            <span class="fl">其他来源：</span>
                            <input type="text" class="fl tik_select" style="width:323px;" name="otherSource" value=""/>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="mt15 tk_you">
                        <a href="javascript:materialSearch();" class="fl tk_chaxun mat1_baochun">查询</a>
                        <a href="javascript:void(0);" class="fl ml30 tk_chaxun01 mat1_baochun">基本查询</a>
                    </div>
                    <div style="height:40px;"></div>
                </div>
        </div>
        <div class="clear"></div>
        <div class="mt15 tk_you shiti" style="">
            <a href="javascript:materialSearch();" class="fl tk_chaxun">查询</a>
            <a href="javascript:void(0);" class="fl ml30 tk_chaxun01 mat1_chaxun">高级查询</a>
        </div>
        <div class="clear"></div>
        </form>
    </div>
    <div class="clear"></div>
</div>
<div class="tkbjs" style="margin-top:10px;"></div>

<!--  -->
<div class="center none" style="">
    <div class="tk_center01">
        <form id="listfrm" name="listfrm" method="POST">
            <div class="mt10 kit1_tiaojia">
                <div class="mt10 fl tk_tixing">
                    <%-- 				<em class="fl">排序规则：</em>
                                    <div class="fl tik_select">
                                        <div class="tik_position_4">
                                            <b class="ml5" id="SEL_OrderBy">
                                                <c:if test="${orderItem eq '1'}">入库时间</c:if>
                                                <c:if test="${orderItem eq '2'}">审核时间</c:if>
                                            </b>
                                            <p class="tik_position01"><i class="tk_bjt10"></i></p>
                                        </div>
                                        <ul class="fl tk_list" style="display:none;">
                                            <li onclick="questionSearch(1);">入库时间</li>
                                            <li onclick="questionSearch(2);">审核时间</li>
                                        </ul>
                                    </div> --%>
                </div>
                <div class="fr cas_anniu">
                    <a href="${ctx}/material/MaterialManage.do?mode=add" class="fl"
                       style="width:95px;margin-right:15px;">新增素材</a>
                    <a href="javascript:void(0);" onclick="exportMaterial();" class="fl"
                       style="width:95px;margin-right:15px;">导出素材</a>
                    <a href="javascript:void(0);" onclick="doLockStates();" class="fl" style="width:95px;">禁用素材</a>
                </div>
            </div>
            <div class="clear"></div>
            <display:table name="mtlList" requestURI="${ctx}/material/MaterialManage.do" id="p" class="mt10 cas_table"
                           size="${totalCount}" pagesize="20" sort="list" defaultsort="7" defaultorder="descending">
                <display:column title="<input type='checkbox' class='chkall'>" style="width:2%;text-align:center">
                    <input type="checkbox" class='chk' name="idArr" value="${p.id}" id="qcid"
                           isUsedInCourse="${p.usedInCource}" disableable="${cureentUserId == p.creatorId && p.state!=5}"/>
                </display:column>
                <display:column title="素材编号" style="width:4%;">
                    ${p.id}
                </display:column>
                <display:column title="素材名称" sortable="true" sortProperty="name" style="width:8%;">
                	<c:choose>
					   <c:when test="${p.format == 'MP4' or p.format == 'WMV' or p.format == 'RMVB' or p.format == 'MKV' or p.format == 'FLV' or 
                			p.format == 'mp4' or p.format == 'wmv' or p.format == 'rmvb' or p.format == 'mkv' or p.format == 'flv'}">  
					        <a href="${ctx}/material/MaterialManage.do?mode=materialView&currUnitMediaIdVal=${p.savePath}&pmId=${p.id}" target="_Blank">${p.name}</a>       
					   </c:when>
					   <c:otherwise> 
					     	<a href="${p.savePath}"  target="_Blank">${p.name}</a> 
					   </c:otherwise>
					</c:choose>
                    
                </display:column>
                <display:column title="学科" style="width:8%;">
                    <c:forEach items="${p.prop_map.get('3')}" var="propxk">
                        ${propxk.prop_val_name}<br>
                    </c:forEach>
                    <c:forEach items="${p.prop_map.get('4')}" var="propxk">
                        ${propxk.prop_val_name}<br>
                    </c:forEach>
                    <c:forEach items="${p.prop_map.get('5')}" var="propxk">
                        ${propxk.prop_val_name}<br>
                    </c:forEach>
                </display:column>
                <display:column title="ICD10" style="width:6%;">
                    <c:forEach items="${p.prop_map.get('11')}" var="propxk" varStatus="stat">
                        <c:if test="${stat.last}">
                            ${propxk.prop_val_name}<br>
                        </c:if>
                    </c:forEach>
                </display:column>
                <display:column title="素材格式" sortable="true" style="width:8%;" property="format"></display:column>
                <display:column title="入库时间" style="width:8%;" property="upload_date" sortable="true"></display:column>
                <display:column title="审核时间" style="width:8%;" sortProperty="deli_date" sortable="true"
                                property="deli_date">
                </display:column>
                <display:column title="素材类型" sortable="true" sortProperty="type" style="width:8%;">
                    <c:if test="${p.type == 1}">视频</c:if>
                    <c:if test="${p.type == 2}">图片</c:if>
                    <c:if test="${p.type == 3}">PPT</c:if>
                    <c:if test="${p.type == 4}">文本</c:if>
                    <c:if test="${p.type == 5}">压缩包</c:if>
                </display:column>
                <display:column title="状态" sortable="true" sortProperty="state" style="width:6%;">
                    <c:if test="${p.state == 1}">未上传</c:if>
                    <c:if test="${p.state == 2}">未审核</c:if>
                    <c:if test="${p.state == 3}">不合格</c:if>
                    <c:if test="${p.state == 4}">合格</c:if>
                    <c:if test="${p.state == 5}">禁用</c:if>
                </display:column>
                <display:column title="审核意见" style="width:8%;" property="deli_opinion"></display:column>
                <display:column title="审核人" style="width:6%;" sortable="true" property="deli_man"></display:column>
                <display:column title="操作" style="width:13%;">
                    <a href="${ctx}/material/MaterialManage.do?mode=detail&spec=view&type=1&id=${p.id}">查看</a>
                    <c:if test="${cureentUserId == p.creatorId }">
                        <c:if test="${p.state!=5}">
                            <a href="${ctx}/material/MaterialManage.do?mode=add&materialInfoId=${p.id}">修改</a>
                        </c:if>
                        <a href="javascript:doLock(${p.id}, ${p.usedInCource}, <c:if test="${p.state==5}">true</c:if><c:if test="${p.state!=5}">false</c:if>);">
                            <c:if test="${p.state==5}">启用</c:if>
                            <c:if test="${p.state!=5}">禁用</c:if>
                        </a>
                        <c:if test="${p.state==5 || p.state==2}"><a href="javascript:doDelMat(${p.id});">删除</a></c:if>
                    </c:if>
                </display:column>
            </display:table>


            <div class="clear"></div>
            <!-- 分页 -->
        </form>
    </div>
</div>
<!-- 试题内容（结束） -->

<script type="text/javascript">

    var ajaxurl;

    $(function () {

        //select menu
        var CDCode = window.localStorage.getItem("CDCode");
        var code = CDCode.split("-");
        var menuindex = code[0];
        var submenuindex = code[1];
        var submenu = "mar_left0" + menuindex;
        $('.tk_mainmenu>ul').find('li').eq(menuindex - 1).addClass("action");
        $('.' + submenu + '').show();
        $('.' + submenu + '>a').eq(submenuindex - 1).addClass("action");

        var type = "${info.type}";
        if (type == "") type = 0;
        else
            type = eval(type);
        var format = "${info.format}";
        str = '<select name="format" style="display:none;"><option value="">请选择</option>';
        for (i = 0; i < filefmtArray[type].length; i++) {
            if (filefmtArray[type][i] == format)
                str += '<option value="' + filefmtArray[type][i] + '" selected>' + filefmtArray[type][i] + '</option>';
            else
                str += '<option value="' + filefmtArray[type][i] + '" >' + filefmtArray[type][i] + '</option>';
        }

        str += '</select><li>请选择</li>';
        for (i = 0; i < filefmtArray[type].length; i++) {
            str += '<li>' + filefmtArray[type][i] + '</li>';
        }

        $('#format').html(str);

        selectInit();

        $('.list_group li').click(function () {
            var selectval = $(this).parent().find('option').eq($(this).index() - 1).val();
            var url = '${ctx}/expert/ExpertManage.do?mode=getsub&gid=' + selectval;
            $.ajax({
                url: url,
                type: 'GET',
                dataType: 'json',
                success: function (result) {
                    if (result != '') {
                        var subgroup = result.sublist;
                        var termlist = result.termlist;

                        $('.list_subgroup select option').remove();
                        $('.list_subgroup li').remove();
                        var newoptions = "<option value=''>请选择</option>";
                        var newlis = "<li>请选择</li>";
                        $.each(subgroup, function (key, val) {
                            newoptions += "<option value='" + val.id + "'>" + val.name + "</option>";
                            newlis += "<li>" + val.name + "</li>";
                        });
                        $('.list_subgroup select').html(newoptions);
                        $('.list_subgroup select').after(newlis);

                        ////////////////////////////////
                        $('.list_termlist select option').remove();
                        $('.list_termlist li').remove();
                        newoptions = "<option value=''>请选择</option>";
                        newlis = "<li>请选择</li>";
                        $.each(termlist, function (key, val) {
                            newoptions += "<option value='" + val.id + "'>" + val.startDateStr + " - " + val.endDateStr + "</option>";
                            newlis += "<li>" + val.startDateStr + " - " + val.endDateStr + "</li>";
                        });
                        $('.list_termlist select').html(newoptions);
                        $('.list_termlist select').after(newlis);

                        selectInit();
                    } else {
                        alert('失败!');
                    }
                    ;
                }
            });

        });

        $(document).click(function () {
            $('.tk_list').hide('fast');
        });

        //更多查询
        $('.mat1_chaxun').click(function () {
            $('.tk_yingcang').show();
            $('.shiti').hide();
            //$('.none').hide();
        });
        //基本查询
        $('.mat1_baochun').click(function () {
            $('.tk_yingcang').hide();
            $('.shiti').show();
            //$('.none').show();
        });
        $('#propNames01').click(function () {
            initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
            $('.att_make01').show();
        });
        $('#zutiNames01').click(function () {
            initPropList("主题", "${ctx}/propManage/getPropListAjax.do", 7, 0, 1, 0, $('#zutiIds'), $('#zutiNames01'));
            $('.att_make01').show();
        });
        $('#icdNames01').click(function () {
            initPropList("ICD10", "${ctx}/propManage/icdList.do", 10, 0, 1, 0, $('#icdIds'), $('#icdNames01'));
            $('.att_make01').show();
        });

        $('#laiNames01').click(function () {
            initPropList("来源", "${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#laiIds'), $('#laiNames01'));
            $('.att_make01').show();
        });
        $('#dutyNames01').click(function () {
            initPropList("职称", "${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 0, $('#dutyIds'), $('#dutyNames01'));
            $('.att_make01').show();
        });
        $('#cognizeNames01').click(function () {
            initPropList("认知水平", "${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#cognizeIds'), $('#cognizeNames01'));
            $('.att_make01').show();
        });

        //
        $('#type li').click(function () {
            var type = $(this).index() - 1;

            str = '<select name="format" style="display:none;"><option value="">请选择</option>';
            for (i = 0; i < filefmtArray[type].length; i++) {
                str += '<option value="' + filefmtArray[type][i] + '">' + filefmtArray[type][i] + '</option>';
            }

            str += '</select><li>请选择</li>';
            for (i = 0; i < filefmtArray[type].length; i++) {
                str += '<li>' + filefmtArray[type][i] + '</li>';
            }

            $('#format').html(str);
            selectInit();
        });

    });

    //禁用
    function doLock(id, isUsedInGroupClass, isEnable) {
        if (!isEnable && isUsedInGroupClass) {
            alert('该素材已经被用于组课，不可禁用！若要禁用，请先将素材从课程中删除！');
            return;
        }
        if (!confirm("确定？")) return;
        var url = '${ctx}/material/MaterialManage.do?mode=lock';
        if (id != '') {
            var params = 'id=' + id;
        }

        $.ajax({
            url: url,
            type: 'POST',
            data: params,
            dataType: 'text',
            success: function (result) {
                if (result == 'success') {
                    alert('操作成功！');
                    document.location.href = document.location.href.split("#")[0];
                } else {
                    alert('操作失败!');
                }
                ;
            }
        });
    }

    function selectInit() {

        $('.tik_select').click(function () {
            $(".tk_list").css("display", "none");
            $(this).find('ul').show();
            return false;
        });
        $('.tk_list li').click(function () {
            var str = $(this).text();
            $(this).parent().parent().find('div').find('b').text(str);
            $(this).parent().find('option').prop('selected', '');
            $(this).parent().find('option').eq($(this).index() - 1).prop('selected', 'selected');
            $('.tk_list').slideUp(50);
        });

        $('.tk_list option:selected').each(function () {
            var str = $(this).text();
            $(this).parent().parent().parent().find('b').text(str);
        });
        //check all
        $('.chkall').click(function () {
            if ($(this).attr('checked') == 'checked')
                $('.chk').attr('checked', 'checked');
            else
                $('.chk').removeAttr('checked');
        });
    }

    function doDelMat(id, state) {
        if (!confirm("您确定要删除吗？")) return;
        var url = '${ctx}/material/MaterialManage.do?mode=del';
        if (id != '') {
            var params = 'id=' + id;
        }

        $.ajax({
            url: url,
            type: 'POST',
            data: params,
            dataType: 'text',
            success: function (result) {
                if (result == 'success') {
                    alert('素材删除成功！');
                    document.location.href = document.location.href.split("#")[0];
                } else {
                    alert('此素材正在使用中，不能删除!');
                }
                ;
            }
        });
    }

    /*
    function doForb(id){
        var url = '${ctx}/material/MaterialManage.do';
	var params = 'mode=update';
	params += '&state=4';
	params += '&id=' + id;

	$.ajax({
		type: 'POST',
		url: url,
		data : params,
		dataType: 'text',
		success : function (b){
	      	alert('成功！');
	      	document.location.href = document.location.href.split("#")[0];
		},
		error: function(){
		   	  alert('失败.请检查属性的关联!');
		}
	});
}
*/
    function materialSearch() {
        var kindOfQuestion = $('#SEL_KindOf').text();
        $('#question_label_name').val(kindOfQuestion);

        var stateOfQuestion = $('#SEL_State').text();
        $('#state').val(stateOfQuestion);

        var isMedia = $('#SEL_Media').text();
        $('#isMedia').val(isMedia);
        $('#propNames').val($('#propNames01').text());
        $('#zutiNames').val($('#zutiNames01').text());
        $('#dutyNames').val($('#dutyNames01').text());
        $('#cognizeNames').val($('#cognizeNames01').text());
        $('#laiNames').val($('#laiNames01').text());
        $('#icdNames').val($('#icdNames01').text());
        $('#sfrm').submit();
    }

    if ($('#SEL_KindOf').text() == '') $('#SEL_KindOf').text("请选择");
    if ($('#SEL_State').text() == '') $('#SEL_State').text("请选择");
    if ($('#SEL_Media').text() == '') $('#SEL_Media').text("请选择");
    if ($('#SEL_OrderBy').text() == '') $('#SEL_OrderBy').text("请选择");


    function exportMaterial() {
        if (!checkMaterialIds()) {
            alert("请选择要导出的素材!");
            return false;
        }
        document.getElementById("listfrm").action = "${ctx}/materialManage/downLoadMaterialListFile.do";
        document.getElementById("listfrm").submit();
    }

    function checkMaterialIds() {
        var checked = false;
        $("input[name='idArr']").each(function () {
            if ($(this).is(':checked')) {
                checked = true;
                return false;
            }
        });
        return checked;
    }

    function downloadFile(id) {
        var url = "${ctx}/material/MaterialManage.do?mode=downloadFile&sel_id=" + id;
        window.open(url, '_blank');
    }

    function questionSearch(orderBy) {
        if (orderBy != null) {
            $('#orderItem').val(orderBy);
        }
        $('#sfrm').submit();
    }

    function doLockStates() {
        if (!checkMaterialIds()) {
            alert("请选择素材!");
            return false;
        }

        var url = '${ctx}/material/MaterialManage.do?mode=lock';
        var params = '&id=';
        var allOK = true;
        $("input[name='idArr']").each(function () {

            if ($(this).is(':checked')) {
                if ($(this).attr("disableable") == "false") {
                    alert('批量禁用素材时请勿选择不可禁用素材！');
                    allOK = false;
                    return false;
                }

                if ($(this).attr("isUsedInCourse") == "true") {
                    alert('选择的素材中包含已经被用于组课的内容，不可禁用！若要禁用，请先将素材从课程中删除！');
                    allOK = false;
                    return false;
                }

                params += $(this).val() + ",";
            }
        });

        if (!allOK) return;

        $.ajax({
            url: url,
            type: 'POST',
            data: params,
            dataType: 'text',
            success: function (result) {
                if (result == 'success') {
                    alert('操作成功！');
                    document.location.href = document.location.href.split("#")[0];
                } else {
                    alert('操作失败!');
                }
                ;
            }
        });
    }

    function entersearch() {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13) {
            materialSearch();
        }
    }
</script>
</body>
</html>