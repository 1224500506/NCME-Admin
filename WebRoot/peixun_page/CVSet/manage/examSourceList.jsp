<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/peixun_page/topjs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资源管理平台</title>
    <link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/peixun_page/css/examtable.css" />
    <script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
</head>

<body class="bjs" onkeydown="entersearch();">
<div>
    <div class="center none" style="">
        <div class="tk_center01">
            <div class="att1_leixing">
                <!-- 来源 -->
                <div class="att1_zhuti">
                    <div class="mt10 cas_chaxun" style="height:40px;">
                        <form id="sfrm" action="${ctx}/propManage/sourceList.do" method="POST">
                            <div class="tik_xuanze">
                                <div class="mt10 fl tk_tixing">
                                    <em class="fl">来源类型：</em>
                                    <div class="fl tik_select">
                                        <div class="tik_position_1">
                                            <b class="ml5">请选择</b>
                                            <p class="tik_position01"><i class="tk_bjt10"></i></p>
                                        </div>
                                        <ul class="fl tk_list1 tk_list list_group" style="display:none;">
                                            <select name="stype" style="display:none;">
                                                <option value="-1">请选择</option>
                                                <c:forEach items="${typeList}" var="group">
                                                    <option value="${group.id}"<c:if
                                                            test="${group.id==stype}"> selected</c:if>>${group.type_name}</option>
                                                </c:forEach>
                                            </select>
                                            <li>请选择</li>
                                            <c:forEach items="${typeList}" var="group">
                                                <li>${group.type_name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <%-- 								<input type="text"  class="fl tik_select" name="stype" value='${stype}'/>
                                     --%>
                                </div>
                                <div class="mt10 ml30 fl tk_tixing">
                                    <span class="fl">学科：</span>
                                    <input type="hidden" class="fl tik_select" id="groupIds" name="propIds"
                                           value="${propIds}"/>
                                    <input type="hidden" class="fl tik_select" id="propNames" name="propNames"
                                           value="${propNames}"/>
                                    <div class="fl tik_select01 takuang_xk" id="groupNames">${propNames}</div>
                                </div>
                                <div class="mt10 mll8 fl tk_tixing">
                                    <span class="fl">来源名称：</span>
                                    <input type="text" class="fl tik_select" name="sname" value='${sname}'/>
                                </div>
                            </div>
                        </form>
                        <div class="mt10 mll8 fl tk_tixing">
                            <a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="fr cas_anniu">
                        <input type="hidden" id="chooseSourseBookID" value="${chooseSourseBookID}"/>
                        <input type="hidden" id="chooseSourseKnowledgeID" value="${chooseSourseKnowledgeID}"/>
                        <input type="hidden" id="chooseSourseReferenceID" value="${chooseSourseReferenceID}"/>
                        <input type="hidden" id="chooseSourseIDs" value="${chooseSourseIDs}"/>
                        <input type="hidden" id="soure_type" value="${soure_type}"/>
                        <a href="javascript:void(0);" id="addSource" class="fl att_tianjia" style="margin-right:5px;">添加</a>
                    </div>
                    <div class="clear"></div>
                    <display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/sourceList.do"
                                   id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20" sort="list"
                                   defaultsort="1">
                        <display:column title="<input type='checkbox' class='chkall'/>" style="width:3%;"><input
                                type='checkbox' name="chk" class='chk' value="${p.id}"/></display:column>
                        <display:column title="序号" style="width:3%;" property="id"></display:column>
                        <display:column title="来源类型" sortable="true" style="width:5%;"
                                        property="typeName"></display:column>
                        <display:column title="来源名称" sortable="true" style="width:14%;"
                                        property="name"></display:column>
                        <display:column title="来源编码" sortable="true" style="width:7%;" property="code"></display:column>
                        <display:column title="来源出处" sortable="true" style="width:7%;" property="source"></display:column>
                        <display:column title="出版年限" sortable="true" style="width:8%;" property="old"></display:column>
                        <display:column title="关联单元" style="width:10%;" property="propNames"></display:column>
                        <display:column title="主题" style="width:10%;" property="zhutiNames"></display:column>
                        <display:column title="状态" style="width:5%;">
                            <c:if test="${p.state==0}">未审核</c:if>
                            <c:if test="${p.state==1}">已审核</c:if>
                            <c:if test="${p.state==2}">禁用</c:if>
                        </display:column>
                        <display:column title="作者" style="width:5%;" property="author"></display:column>
                        <display:column title="网址" style="width:5%;" property="website"></display:column>
                        <%--<display:column title="操作" style="width:5%;">--%>
                            <%--<a href="javascript:doDelProp(${p.id});">添加</a>--%>
                            <%--<a href="javascript:void(0);" onclick="selectPaper('${p.id}','${p.typeName}');" class="">选择</a>--%>
                        <%--</display:column>--%>
                    </display:table>

                    <div class="clear"></div>
                </div>
                <!-- end -->
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        var soure_type = $("#soure_type").val();

        var str='';
        if(soure_type==1){
            str = $("#chooseSourseBookID").val();
        }else if(soure_type==2){
            str = $("#chooseSourseKnowledgeID").val();
        } else if(soure_type==4){
            str = $("#chooseSourseIDs").val();
        } else{
            str = $("#chooseSourseReferenceID").val();
        }
        $(str.split(",")).each(function (i,dom){
            $(":checkbox[value='"+dom+"']").prop("checked",true);
            $(":checkbox[id='"+dom+"']").prop("checked",true);
        });

        /**
         * 添加来源
         */
        $("#addSource").click(function () {
            var chk_value =[];
            var chk_str="";
            var class_str="";
            var soure_type = $("#soure_type").val();
            if(soure_type==1){
                class_str='bookImgDel';
            }else if(soure_type==2){
                class_str='knowledgeBaseImgDel';
            } else{
                class_str='referenceImgDel';
            }
            $('input[name="chk"]:checked').each(function(index){
                var $v= jQuery(this).closest("tr");
                var name = jQuery.trim($v.find("td:eq(3)").text()); //来源名称
                var source = jQuery.trim($v.find("td:eq(5)").text()); //来源出处
                var old = jQuery.trim($v.find("td:eq(6)").text());//出版年限
                var author = jQuery.trim($v.find("td:eq(10)").text()); //作者
                // chk_str+= '<span style=" height: 35px; display:block;"> '
                chk_str+= '<span style="display:block;"> '
                    + '<span style="display:inline-block;padding-left:12px;"><span  style="padding-right:12px;">'+(index+1)+'</span>来源名称 :</span>'
                    + name
                    + '<span style="display:inline-block;padding-left:12px;">来源出处 :</span>'
                    + source
                    + '<span style="display:inline-block;padding-left:12px;">作者 :</span>'
                    + author
                    + '<span style="display:inline-block;padding-left:12px;">出版年限 :</span>'
                    + old+'&nbsp;&nbsp;<span class="'+class_str+'" style="width: 32px;height: 32px;margin-left: 5px;">'
                    +'<img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>'
                    + '</span><br/>' ;
                // console.log("  name="+name+"  source="+source+"  old="+old+"  author="+author);
                chk_value.push($(this).val());
            });
            chk_str='<span style="display:block;">'+chk_str+'</span>';
            if(soure_type==1){
                $("#bookDiv",window.parent.document).html(chk_str);
                parent.document.getElementById("chooseSourseBookID").value=chk_value;
            }else if(soure_type==2){
                $("#knowledgeBaseDiv",window.parent.document).html(chk_str);
                parent.document.getElementById("chooseSourseKnowledgeID").value=chk_value;
            }else if(soure_type==4){
                //添加课程时
                $("#sourceBaseDiv",window.parent.document).html(chk_str);
                parent.document.getElementById("chooseSourseIDs").value=chk_value;
            } else{
                $("#referenceDiv",window.parent.document).html(chk_str);
                parent.document.getElementById("chooseSourseReferenceID").value=chk_value;
            }
            alert('添加成功！');
        });
    });
</script>

<script type="text/javascript">

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


        $('#laiyuan').click(function () {
            document.location.href = "${ctx}/propManage/sourceTypeList.do";
        });


        //查询学科
        $('.tk_chaxun').click(function () {
            $('#propNames').val($('#groupNames').text());
            submitSearch();

        });
        //添加学科
        $('.att_tianjia').click(function () {
            $('.att_xueke').show();
            $('#rsName').val('');
            $('#rsCode').val('');
            $('#rsId').val('');
            $('#rsOld').val('');
            $('#rsSource').val('');
            $('#rsProp_val1').val('0');
            $('#newgroupIds').val('');
            $('#newgroupNames').text('');
            $('#mode').val('add');
        });

        $('.att_tianjia03').click(function () {
            if ($('.chk:checked').length <= 1) {
                alert("请选择两个以上来源。");
                return;
            }
            $('.srcleixing').val('');
            $('#targetSrc').html('<option value="">请选择</option>');
            $('.att_xueke03').show();
        });

        $('.srcleixing').change(function () {
            var url = '${ctx}/propManage/sourceList.do?mode=getListByType';
            var params = 'typeid=' + $(this).val();
            params += '&state=-1';

            $.ajax({
                url: url,
                type: 'POST',
                data: params,
                dataType: 'json',
                success: function (result) {
                    if (result !== "") {
                        var str = '<option value="">请选择</option>';

                        $(result.list).each(function (key, value) {
                            str += '<option value="' + value.id + '">' + value.name + '</option>';
                        });

                        $('.laiyuan').html(str);
                    }
                }
            });
        });

        $('.att_fanhui').click(function () {
            $('.att_xueke').hide();
            $('.att_xueke03').hide();
        });
        //check all
        $('.chkall').click(function () {
            if ($(this).attr('checked') === 'checked')
                $('.chk').attr('checked', 'checked');
            else
                $('.chk').removeAttr('checked');
        });


        $('.takuang_xk').click(function () {
            initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
            $('.att_make01').show();
        });
        $('.takuang_xk_edit').click(function () {
            initPropList("主题", "${ctx}/propManage/getPropListAjax.do", 7, 0, 1, 0, $('#newgroupIds'), $('#newgroupNames'));
            $('.att_make01').show();
        });

        //
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

        $(document).click(function () {
            $('.tk_list').hide('fast');
        });


    });
    function entersearch() {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode === 13) {
            $('#propNames').val($('#groupNames').text());
            submitSearch();
        }
    }

    function submitSearch() {
        if (!INPUT_PATTERN.PROPERTY_OTHER_PATTERN.exec($("#sname").val())) {
            alert(INPUT_PATTERN.PROPERTY_OTHER_MESSAGE);
        } else {
            $('#sfrm').submit();
        }
    }

 /*
 * 选择来源
 */
    function selectSource(paperId,paperName){
        var examId=980;
        var paperName="neweew";
            var html = "<img src=\"${ctxAll}/images/paper.jpg\" width=\"100\" height=\"100\" _url='" + examId + "' alt=\"paper\" title='" + paperName + "' onclick=\"preview(" + examId + ",'paper');\">";
            window.parent.ue.execCommand( 'inserthtml', html );
            var dialog = window.parent.ue.getDialog("insertframe");
            //判断该单元任务点是否选中
            if(window.parent.isRWD=='true'){
                //调用父页面的视频完成指标函数
                window.parent.comPaper();
            }
            dialog.close();

    }
</script>
<script type="text/javascript" src="${ctx}/peixun_page/js/validate.js"></script>
</body>
</html>
