var PopupSelector = {
  InitClass : function(temp, type) {
    if (temp.length != 0) {
      if (type == "cat") {
        $("#txtCat").removeClass("inp_txt inp_txtsel inp_wl inp_cue gray");
        $("#txtCat").addClass("inp_txt inp_txtsel inp_wl");
      }
      if (type == "loc") {
        $("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm inp_cue gray");
        $("#txtLoc").addClass("inp_txt inp_txtsel inp_wm");
      }
      if (type == "ind") {
        $("#txtInd").removeClass("inp_txt inp_txtsel inp_wm inp_cue gray");
        $("#txtInd").addClass("inp_txt inp_txtsel inp_wm");
      }

    } else {
      if (type == "cat") {
        $("#txtCat").removeClass("inp_txt inp_txtsel inp_wl");
        $("#txtCat").addClass("inp_txt inp_txtsel inp_wl inp_cue gray");
        $("#txtCat").val("请选择职业类别")
      }
      if (type == "loc") {
        $("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm");
        $("#txtLoc").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
        $("#txtLoc").val("请选择内容")
      }
      if (type == "ind") {
        $("#txtInd").removeClass("inp_txt inp_txtsel inp_wm");
        $("#txtInd").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
        $("#txtInd").val("请选择题库");
      }
    }
  },
  popup : function(type, ref, selectdtids, tagPrefix, checkType) {
    this._type = type.toString().toLowerCase();
    if (this._selItems[this._type].length == 0) {
      var str;
      var dat;
      if (type == "loc") {
        str = jQuery("#questionStudyIds").val();
        dat = getLoc();
      }
      if (type == "ind") {
        str = jQuery("#questCommonTypeIds").val();
        dat = getInd();
      }
      if (type == "ind2") {
        str = jQuery("#questionLabelIds").val();
        dat = getInd2();
      }
      if (type == "ind5") {
        str = jQuery("#questionLabelIds").val();
        dat = getInd5();
      }
      if (type == "ind3") {
        str = jQuery("#questPoint2_7_2").val();
        dat = getInd4("7");
      }
      if (type == "ind4") {
        str = jQuery("#questionCognizeIds").val();
        dat = getInd4("8");
      }
      if (type == "ind6") {
        str = jQuery("#questionTitleIds").val();
        dat = getInd4("9");
      }

      if (str != "") {
        var tem = new Array();
        var strA = str.split("+");

        for ( var i = 0; i < dat.length; i++) {
          for ( var j = 0; j < strA.length; j++) {
            if (dat[i].id == strA[j]) {
              tem.push(dat[i]);
            }
          }
        }
        this._selItems[this._type] = tem.clone();
      }
    }
    this.AarearenderSelItemEleId = selectdtids;
    this.AareatagPrefix = tagPrefix;
    this.AarearenderSelItemDiv = ref;
    var selLocIDs;
    if (this._box == null)
      this._box = jQuery("#popupSelector");
    if (this._subBox == null)
      this._subBox = document.getElementById("subItems");
    this._ref = ref;

    // 初始化菜单数据
    if (this.AarearenderSelItemEleId != null)
      selLocIDs = document.getElementById(this.AarearenderSelItemEleId).value
          .split(',');
    var allLocs = getLoc();
    this._curItems.length = 0;

    this._curItems = this._selItems[this._type].clone();
    if (arguments.length == 3) {
      this._checkType = checkType;
    }

    var offset = jQuery("#" + ref.id).offset();
    if (offset.left < 10) {
      offset.left = 50;
    }
    if (offset.top < 10) {
      offset.top = 50;
    }

    var scrollHeight = $(document).scrollTop();    //获得被卷上去的高
    var windowHeight = $(window).height();         //获得窗口高
    var layerHeight = $("#pslayer").height();
    var jgHeight = scrollHeight+(windowHeight/2)-(layerHeight/2);                    //计算应该得高度

    this._style.left = offset.left;
    this._style.top = jgHeight - 250;//offset.top;
    this.render();
  },

  render : function(event) {

    var pos = {
      top : (this._style.top + this._style.offset.levelOne.Y),
      left : this._style.left + this._style.offset.levelOne.X
    };
    //var pos;

    var scrolTop = document.documentElement.scrollTop;
//    if (jQuery.browser.msie && jQuery.browser.version == '6.0')
//      scrolTop = document.body.scrollTop;
//    else
//      scrolTop = document.documentElement.scrollTop;

    // 当前窗口对照显示参照位置可用的高度，如果可用高度小于弹出层的高度，则进行调整
    var remainHeight = jQuery(window).height() - pos.top + scrolTop;
    if (remainHeight < this._style.height[this._type])
      pos.top = pos.top - (this._style.height[this._type] - remainHeight);
    // 如果当前窗口高度不足以显示弹出层高度，则顶部对齐即可
    if (this._style.height[this._type] > jQuery(window).height())
      pos.top = scrolTop;

//    if (this._type == "loc") {
//      pos.top = 0;
//    }

    var pslayer = jQuery("#pslayer");
    for ( var i = 0; i < this._types.length; i++) {
      pslayer.removeClass(this._style.className.levelOne[this._types[i]]);
    }
    pslayer.addClass(this._style.className.levelOne[this._type]);
    if (this.InSalaryPage) {
      this._gtYourSelected["ind"] = '请选择你的行业类别：';
    }

    jQuery("#selectingHeader").html(this._gtYourSelected[this._type]);

    jQuery("#psHeader").html(this._gtPopupSelectorHeader[this._type]);

    // this._curItems = this._selItems[this._type];
    var html = [];
    var popupLayer;
    var parItems = this.allItems[this._type]();
    var start = 0;
    var end = parItems.length;
    if (this._type == "loc") {
      jQuery("#subHeader1").html('<div class="gltop_btn">一级学科：</div>');
      jQuery("#subHeader1").show();
      jQuery("#subHeader2").hide();
      jQuery("#allItems2").hide();
    } else {
      if (this._type == "ind") {
        start = 0;
        jQuery("#subHeader1").html('<div class="gltop_btn">考试系统题库：</div>');
        jQuery("#subHeader2").html('<div class="gltop_btn">本医院题库：</div>').show();
        for ( var i = 0; i < parItems.length; i++) {
          if (parItems[i].id < 1000) {
            end = i;// 自建题库id从1001开始
            continue;
          }
          if (this._curItems.has({
            id : parItems[i].id,
            name : parItems[i].name,
            parObj : null
          })) {
            html.push("<li id=jQuery" + i + " name=" + parItems[i].id
                + " class=\"layon\"><a href=\"javascript:void(0);\">");
          } else {
            html.push("<li id=jQuery" + i + " name=" + parItems[i].id
                + " class=\"nonelay\"><a href=\"javascript:void(0);\">");
          }

          html.push("<label for=\"pcbx");
          html.push(parItems[i].id);
          html.push("\">");
          html.push("<input id=\"pcbx");
          html.push(parItems[i].id);
          html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
          html.push(parItems[i].id);
          html.push("@");
          html.push(parItems[i].name);
          html.push("\"");
          if (this._curItems.has({
            id : parItems[i].id,
            name : parItems[i].name,
            parObj : null
          }))
            html.push(" checked");
          html.push(" onclick=\"PopupSelector.click(" + i + ",this, null,"
              + parItems[i].id + ")\" />");
          html.push(parItems[i].name);
          html.push("</label>");
          html.push("</a></li>");
        }
        jQuery("#subHeader1").show();
        jQuery("#subHeader2").show();
        jQuery("#allItems2").html(html.join("")).show();
      } else {
        jQuery("#subHeader1").hide();
        jQuery("#subHeader2").hide();
        jQuery("#allItems2").hide();
      }
    }

    html = [];
    var IsAllOption = false;
    for ( var j = 0; j < this._curItems.length; j++) {
      if (this._curItems[j].id == "999999") {
        IsAllOption = true;
      }
    }

    // var ql_ids = jQuery("#questionLabelIds").val();
    // var ql_arry = ql_ids.split("+");

    if (this._type != "ind") {
      end = parItems.length - 1;
    }

    if (this._type == "ind2") {
      html.push("<table class=\"ind2_table\" >");
      html.push("<tr>");
      html.push("<td>");
      html.push("试卷题量分数设置：");
      html.push("</td>");
      html.push("<td>");
      html.push("所设总题量：");
      html.push($("#questionNum").val());
      html.push("</td>");
      html.push("<td>");
      html.push("所设总分数：");
      html.push($("#paperScore").val());
      html.push("</td>");
      html.push("</tr>");
      html.push("<tr>");
      html.push("<th class=\"nonelay\" >");
      html.push("题型");
      html.push("</th>");
      // html.push("<th class=\"nonelay\" >");
      // html.push("可选试题量");
      // html.push("</th>");
      html.push("<th class=\"nonelay\" >");
      html.push("本次选题量");
      html.push("</th>");
      html.push("<th class=\"nonelay\" >");
      html.push("每题分数");
      html.push("</th>");
      html.push("</tr>");
    }
    if (this._type == "ind5") {
      html.push('<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_c">');
      html.push('<tr class="table_a_title" align="center">');
      html.push('<td>');
      html.push("题型");
      html.push("</td>");
      html.push('<td>');
      html.push("可选试题量");
      html.push("</td>");
      html.push('<td>');
      html.push("本次选题量");
      html.push("</td>");
      html.push('<td>');
      html.push("每题分数");
      html.push("</td>");
      html.push("</tr>");
    }

    for ( var i = start; i <= end; i++) {
      var parItem = parItems[i];
      var d = parItems[i];
      if(typeof(parItem) != "undefined") {
        if(typeof(parItem.state) != "undefined" && parItem.id > 1000) {
          continue;
        }
      } else {
        continue;
      }
      if (parItem.subItems && parItem.subItems.length >= 1) {

        html.push("<li id=jQuery" + i + " name=" + parItem.id
            + " onclick=\"PopupSelector.showSubItems(");
        html.push(i);
        html.push(","
                + parItem.id
                + ", this, true)\" onmouseout=\"PopupSelector.hideSubItems(this)\">");
        html.push("<a href=\"javascript:void(0);\">");

        html.push("<input id=\"pcbx");
        html.push(parItem.id);
        html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
        html.push(parItem.id);
        html.push("@");
        html.push(parItem.name);
        html.push("\"");
        if (IsAllOption) {
          if (parItem.id != "999999")
            html.push(" disabled=\"true\"");
          html.push(" checked");
        } else if (this._curItems.has({
          id : parItem.id,
          name : parItem.name,
          parObj : null
        }))
          html.push(" checked");
        html.push(" onclick=\"PopupSelector.click(");
        html.push(i + ",this, null," + parItem.id + ")\" />");
        html.push(dbcToSbc(parItem.name));

        // html.push("</label>");
        html.push("</a></li>");
      } else {
        if (this._type == "ind2") {
          html.push("<tr>");
          if (this._curItems.has({
            id : parItem.id,
            name : parItem.name,
            parObj : null
          }))
            html.push("<td id=jQuery" + i + " name=" + parItem.id
                + " class=\"layon\" style='width:150;'>");
          else
            html.push("<td id=jQuery" + i + " name=" + parItem.id
                + " class=\"nonelay\" style='width:150px;'>");

          html.push(dbcToSbc(parItem.name));

          html.push("</td>");
          // html.push("<td id=jq" + parItem.id + " class=\"nonelay\"
          // style='width:150px;'>");
          // html.push("<span style='color:red;'>");
          // html.push("总题量：");
          // html.push(parItem.quesCount);
          // html.push("</span>");
          // html.push("</td>");
          html.push("<td id=jq" + parItem.id
              + " class=\"nonelay\" style='width:130px;'>");
          html.push("<input id='shu_count_" + parItem.id
              + "' type='hidden' value='" + parItem.quesCount + "' />");
          html.push("<input type='hidden' value='" + parItem.id + "' />");
          if (this._curItems.has({
            id : parItem.id,
            name : parItem.name,
            parObj : null
          })) {
            for ( var m = 0; m < this._ql_ids.length; m++) {
              if (this._ql_ids[m].id == parItem.id) {
                html
                    .push("<input id='shu"
                        + parItem.id
                        + "' type='text' value='"
                        + this._ql_ids[m].shu
                        + "' class='shu' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
              }
            }
          } else {
            html
                .push("<input id='shu"
                    + parItem.id
                    + "' type='text' value='0' class='shu' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
          }
          html.push("<input type='hidden' value='" + parItem.name + "' />");
          html.push("</td>");
          html.push("<td id=jq" + parItem.id
              + " class=\"nonelay\" style='width:130px;'>");
          html.push("<span>");
          if (this._curItems.has({
            id : parItem.id,
            name : parItem.name,
            parObj : null
          })) {
            for ( var m = 0; m < this._ql_ids.length; m++) {
              if (this._ql_ids[m].id == parItem.id) {
                html.push("<input id='fen"
                        + parItem.id
                        + "' type='text' value='"
                        + this._ql_ids[m].fen
                        + "' class='fen' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
              }
            }
          } else {
            html.push("<input id='fen"
                    + parItem.id
                    + "' type='text' class='fen1' value='1' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
          }
          html.push("</span>");
          html.push("</td>");

          html.push("</tr>");

        } else if (this._type == "ind5") {
          html.push('<tr align="center">');
          if (this._curItems.has({
            id : d.id,
            name : d.name,
            parObj : null
          })) {
            html.push("<td id=jQuery" + i + " name=" + d.id + ">");
          } else {
            html.push("<td id=jQuery" + i + " name=" + d.id + ">");
          }
          html.push(dbcToSbc(d.name));
          html.push("</td>");
          html.push("<td id=jq" + d.id + ">");
          html.push(d.quesCount);
          html.push("</td>");
          html.push("<td id=jq" + d.id + ">");
          html.push("<input id='shu_count_" + d.id + "' type='hidden' value='"
              + d.quesCount + "' />");
          html.push("<input type='hidden' value='" + d.id + "' />");
          if (this._curItems.has({
            id : d.id,
            name : d.name,
            parObj : null
          })) {
            for ( var p = 0; p < this._ql_ids.length; p++) {
              if (this._ql_ids[p].id == d.id) {
                html.push("<input id='shu" + d.id + "' type='text' value='"
                    + this._ql_ids[p].shu + "' class='shu' onfocus='checkTotalQues();' onblur='checkTotalQues();' />");
              }
            }
          } else {
            html.push("<input id='shu" + d.id
                + "' type='text' value='0' class='shu' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
          }
          html.push("<input type='hidden' value='" + d.name + "' />");
          html.push("</td>");
          html.push("<td id=jq" + d.id + ">");
          //html.push("<span>");
          if (this._curItems.has({
            id : d.id,
            name : d.name,
            parObj : null
          })) {
            for ( var p = 0; p < this._ql_ids.length; p++) {
              if (this._ql_ids[p].id == d.id) {
                html.push("<input id='fen" + d.id + "' type='text' value='"
                    + this._ql_ids[p].fen + "' class='fen' onfocus='checkTotalQues();' onblur='checkTotalQues();'/>");
              }
            }
          } else {
            html.push("<input id='fen" + d.id + "' type='text' value='1' class='fen1' onfocus='checkTotalQues();' onblur='checkTotalQues();' />");
          }
          //html.push("</span>");
          html.push("</td>");
          html.push("</tr>");
        } else {
          if (this._curItems.has({
            id : parItem.id,
            name : parItem.name,
            parObj : null
          }))
            html.push("<li id=jQuery" + i + " name=" + parItem.id
                + " class=\"layon\">");
          else
            html.push("<li id=jQuery" + i + " name=" + parItem.id
                + " class=\"nonelay\">");
          html.push("<a href=\"javascript:void(0);\">");

          html.push("<label for=\"pcbx");
          html.push(parItems[i].id);
          html.push("\">");

          html.push("<input id=\"pcbx");
          html.push(parItem.id);
          html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
          html.push(parItem.id);
          html.push("@");
          html.push(parItem.name);
          html.push("\"");
          if (IsAllOption) {
            if (parItem.id != "999999")
              html.push(" disabled=\"true\"");
            html.push(" checked");
          } else if (this._curItems.has({
            id : parItem.id,
            name : parItem.name,
            parObj : null
          }))
            html.push(" checked");
          html.push(" onclick=\"PopupSelector.click(");
          html.push(i + ",this, null," + parItem.id + ")\" />");
          html.push(dbcToSbc(parItem.name));
          html.push("</label>");
          html.push("</a>");
          html.push("</li>");
        }
      }

    }

    if (this._type == "ind2") {
      html.push("<tr>");
      html.push("<td>");
      html.push("已选题量分数汇总：");
      html.push("</td>");
      html.push("<td>");
      html.push("已选总题量：");
      html.push("<input type='text' id='totalQues' readonly size='10'>");
      html.push("</td>");
      html.push("<td>");
      html.push("已选总分数：");
      html.push("<input type='text' id='totalQuesSoc' readonly size='10'>");
      html.push("</td>");
      html.push("</tr>");
      html.push("</table>");
    }

    if (this._type == "ind5") {
      html.push("<tr>");
      html.push("<td>");
      html.push("已选题量分数汇总：");
      html.push("</td>");
      html.push("<td>");
      html.push("已选总题量：");
      html.push("<input type='text' id='totalQues' readonly size='10'>");
      html.push("</td>");
      html.push("<td>");
      html.push("已选总分数：");
      html.push("<input type='text' id='totalQuesSoc' readonly size='10'>");
      html.push("</td>");
      html.push("<td>");
      html.push("</td>");
      html.push("</tr>");
      html.push("</table>");
    }

    jQuery("#allItems1").html(html.join("").toString());
    html.length = 0;

    jQuery("#divSelecting").css("display",
        (this._curItems.length == 0) ? "none" : "block");
    var displayNoSelected = (this._curItems.length == 0) ? "block" : "none";
    if (this._type == "cat") {
      jQuery("#noSelectedCat").css("display", displayNoSelected);
      jQuery("#noSelectedLoc").hide();
    } else if (this._type == "loc") {
      jQuery("#noSelectedLoc").css("display", displayNoSelected);
      jQuery("#noSelectedCat").hide();
    } else {
      jQuery("#noSelectedCat").hide();
      jQuery("#noSelectedLoc").hide();
    }
    for ( var i = 0; i < this._curItems.length; i++) {

      html.push("<li id=\"");
      html.push(this._curItems[i].id);
      html.push("\"><a href=\"javascript:;\" onclick=\"PopupSelector.remove(");
      html.push(this._curItems[i].id);
      if (this._curItems[i].parObj != null) {
        if (this._curItems[i].parObj.parObj != null) {
          html.push("," + this._curItems[i].parObj.parObj.id + ")\">");
        } else {
          html.push("," + this._curItems[i].parObj.id + ")\">");
        }
      } else
        html.push(")\">");
      var nameString;
      var parthis = this;
      if (this._curItems[i].parObj && this._type == "loc") {
        if (this._curItems[i].parObj.parObj != null) {
          if (this._curItems[i].parObj != null
              && null == this._curItems[i].parObj.pparObj) {
            nameString = this._curItems[i].parObj.name + "  >  "
                + this._curItems[i].name;
          } else if (this._curItems[i].parObj.pparObj != null
              && null == this._curItems[i].parObj.ppparObj) {
            nameString = parthis.allItems[parthis._type]()[this._curItems[i].parObj.pparObj].name
                + "  >  "
                + this._curItems[i].parObj.name
                + "  >  "
                + this._curItems[i].name;
          } else if (this._curItems[i].parObj.ppparObj != null
              && null == this._curItems[i].parObj.pppparObj) {
            nameString = parthis.allItems[parthis._type]()[this._curItems[i].parObj.ppparObj].name
                + "  >  "
                + parthis.allItems[parthis._type]()[this._curItems[i].parObj.ppparObj].subItems[this._curItems[i].parObj.pparObj].name
                + "  >  "
                + this._curItems[i].parObj.name
                + "  >  "
                + this._curItems[i].name;
          } else if (this._curItems[i].parObj.pppparObj != null) {
            nameString = parthis.allItems[parthis._type]()[this._curItems[i].parObj.pppparObj].name
                + "  >  "
                + parthis.allItems[parthis._type]()[this._curItems[i].parObj.pppparObj].subItems[this._curItems[i].parObj.ppparObj].name
                + "  >  "
                + parthis.allItems[parthis._type]()[this._curItems[i].parObj.pppparObj].subItems[this._curItems[i].parObj.ppparObj].subItems[this._curItems[i].parObj.pparObj].name
                + "  >  "
                + this._curItems[i].parObj.name
                + "  >  "
                + this._curItems[i].name;
          } else {
            nameString = this._curItems[i].name;
          }
        } else {
          nameString = this._curItems[i].parObj.name + "  >  "
              + this._curItems[i].name;
        }
      } else
        nameString = this._curItems[i].name;

      html.push(dbcToSbc(nameString));
      html.push("</a></li>");
    }
    jQuery("#selecting").html(html.join(""));

    if (this._type == "ind" || this._type == "ind2" || this._type == "ind3"
        || this._type == "ind4" || this._type == "ind6" || this._type == "ind5"
        || this._type == "comptype") {
      jQuery("#divSelecting").show();
      jQuery("#selecting").hide();
    } else
      jQuery("#selecting").show();
    jQuery("#shield").width(this._style.width[this._type]).height(
        this._style.height[this._type]);
    jQuery(this._box).css(pos).show();
    //jQuery("#mask").bgiframe();
    jQuery("#mask").height(jQuery(document).height()).show();

    // 定位焦点
    jQuery("#pcbx30000").focus();

  },

  close : function() {
    if (this._type == "loc") {
      jQuery("#fifthItems").hide();
      jQuery("#fourthItems").hide();
      jQuery("#thirdItems").hide();
      jQuery("#subItems").hide();
    }
    jQuery("#mask").hide();
    // jQuery("html").css("overflow", "auto");
    this._ref = null;
    jQuery(this._box).hide();
  },

  OK : function() {
    var html = [], tmpSelectedItem = popupLayer.selectedItems;
    var renderSelItemDiv = this.AarearenderSelItemDiv;
    var questionStudyIds = this.AarearenderSelItemEleId;
    var renderfix = this.AareatagPrefix;
    var ids = [];
    var names_ids = [];
    var temp = [];
    var parthis = this;
    var isQueNum = false;
    var p_num_sum = 0;// 合计题量
    var p_score_sum = 0;// 合计分数
    var sele_ques_num = 0;// 所选题型数量
    var html2 = [];
    var html3 = [];
    var studyNamesIds= [];
    var tot_ti = 0;
    var tot_fen = 0;

    if (this._type == "ind2") {
      parthis._ql_ids.clear();
      clearQuesNum();
      $(".shu").each(function(i) {
        if ($(this).val() > 0) {
          sele_ques_num = sele_ques_num + 1;

          var id = $(this).prev().val();
          var name = $(this).next().val();
          var item = {
            id : id,
            name : name
          };

          parthis._curItems.push(item);

          var shu_count = $(this).prev().prev().val();
          var shu = jQuery("#shu" + id).val();
          var fen = jQuery("#fen" + id).val();

          var patrn = /^[1-9]{1}[0-9]{0,2}$/;
          if (testDemo(patrn, shu)) {
          } else {
            alert(name + "试题数量至少大于1的整数，小于等于" + shu_count + "！");
            isQueNum = true;
            return false;
          }
          if (testDemo(patrn, fen)) {
          } else {
            alert(name + "试题分数至少大于等于1的整数！");
            isQueNum = true;
            return false;
          }

          if (shu == 0) {
            alert('请设置' + name + '题量！');
            isQueNum = true;
            return false;
          }
          // if(eval(shu) > eval(shu_count)){
          // alert(name+"题量不能超过该题型总题量！");
          // isQueNum = true;
          // return false;
          // }
          if (fen == '' || fen == 0) {
            fen = 1;
            jQuery("#fen" + this.id).val(fen);
          }
          var sf = {
            id : id,
            shu : shu,
            fen : fen
          };
          parthis._ql_ids.push(sf);

          nameString = "【" + name + " " + shu + " 道，每题" + fen + "分】";
          ids.push(id + "_" + shu + "_" + fen);

          p_num_sum = p_num_sum + eval(shu);
          p_score_sum = p_score_sum + eval(eval(fen) * eval(shu));
          temp.push(nameString);

          html2.push("<tr class='tr_ind2'>");
          html2.push("<td id='name");
          html2.push(id);
          html2.push("'>");
          html2.push(name);
          html2.push("</td>");
          html2.push("<td id='shuliang");
          html2.push(id);
          html2.push("'>");
          html2.push(shu);
          html2.push("</td>");
          html2.push("<td id='zongfen");
          html2.push(id);
          html2.push("'>");
          html2.push(eval(eval(fen) * eval(shu)));
          html2.push("</td>");
          html2.push("</tr>");

          tot_ti = tot_ti + eval(shu);
          tot_fen = tot_fen + eval(eval(fen) * eval(shu));

        }
      });
    }

    if (this._type == "ind5") {
      parthis._ql_ids.clear();
      $(".shu").each(function(i) {
        if ($(this).val() > 0) {
          sele_ques_num = sele_ques_num + 1;
          var id = $(this).prev().val();
          var name = $(this).next().val();
          var item = {
            id : id,
            name : name
          };
          parthis._curItems.push(item);
          var shu_count = $(this).prev().prev().val();
          var shu = jQuery("#shu" + id).val();
          var fen = jQuery("#fen" + id).val();
          var patrn = /^[1-9]{1}[0-9]{0,2}$/;
          if (testDemo(patrn, shu)) {
          } else {
            alert(name + "试题数量至少大于1的整数，小于等于" + shu_count + "！");
            isQueNum = true;
            return false;
          }
          if (testDemo(patrn, fen)) {
          } else {
            alert(name + "试题分数至少大于等于1的整数！");
            isQueNum = true;
            return false;
          }
          if (shu == 0) {
            alert("请设置" + name + "题量！");
            isQueNum = true;
            return false;
          }
          if (eval(shu) > eval(shu_count)) {
            alert(name + "题量不能超过该题型总题量！");
            isQueNum = true;
            return false;
          }
          if (fen == "" || fen == 0) {
            fen = 1;
            jQuery("#fen" + this.id).val(fen);
          }
          var sf = {
            id : id,
            shu : shu,
            fen : fen
          };
          parthis._ql_ids.push(sf);
          nameString = "【" + name + " " + shu + " 道，每题" + fen + "分】";
          ids.push(id + "_" + shu + "_" + fen);
          p_num_sum = p_num_sum + eval(shu);
          p_score_sum = p_score_sum + eval(eval(fen) * eval(shu));
          temp.push(nameString);
        }
      });
    }

    if (parthis._type != "ind2" && parthis._type != "ind5") {
      jQuery
          .each(
              this._curItems,
              function() {
                var nameString;
                var idstemp = [];
                if (this.parObj && parthis._type == "loc") {
                  var parItemsVal = parthis.allItems[parthis._type]();
                  if (this.parObj != null && null == this.parObj.pparObj) {
                    nameString = this.parObj.name + "  >  " + this.name;
                    ids.push(this.parObj.id + "-" + this.id);
                    names_ids.push(this.parObj.id + "-" + this.id+"_"+nameString);
                    idstemp.push(this.parObj.id + "-" + this.id);
                  } else if (this.parObj.pparObj != null
                      && null == this.parObj.ppparObj) {
                    nameString = parthis.allItems[parthis._type]()[this.parObj.pparObj].name
                        + "  >  " + this.parObj.name + "  >  " + this.name;
                    ids.push(parthis.allItems[parthis._type]()[this.parObj.pparObj].id + "-" + this.parObj.id + "-" + this.id);
                    names_ids.push(parthis.allItems[parthis._type]()[this.parObj.pparObj].id + "-" + this.parObj.id + "-" + this.id+"_"+nameString);
                    idstemp.push(parthis.allItems[parthis._type]()[this.parObj.pparObj].id+ "-" + this.parObj.id + "-" + this.id);
                  } else if (this.parObj.ppparObj != null
                      && null == this.parObj.pppparObj) {
                    nameString = parthis.allItems[parthis._type]()[this.parObj.ppparObj].name
                        + "  >  "
                        + parthis.allItems[parthis._type]()[this.parObj.ppparObj].subItems[this.parObj.pparObj].name
                        + "  >  " + this.parObj.name + "  >  " + this.name;
                    ids.push(parthis.allItems[parthis._type]()[this.parObj.ppparObj].id+ "-"+ parthis.allItems[parthis._type]()[this.parObj.ppparObj].subItems[this.parObj.pparObj].id+ "-" + this.parObj.id + "-" + this.id);
                    names_ids.push(parthis.allItems[parthis._type]()[this.parObj.ppparObj].id+ "-"+ parthis.allItems[parthis._type]()[this.parObj.ppparObj].subItems[this.parObj.pparObj].id+ "-" + this.parObj.id + "-" + this.id+"_"+nameString);
                    idstemp.push(parthis.allItems[parthis._type]()[this.parObj.ppparObj].id
                            + "-"
                            + parthis.allItems[parthis._type]()[this.parObj.ppparObj].subItems[this.parObj.pparObj].id
                            + "-" + this.parObj.id + "-" + this.id);
                  } else if (this.parObj.pppparObj != null) {
                    nameString = parthis.allItems[parthis._type]()[this.parObj.pppparObj].name
                        + "  >  "
                        + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].name
                        + "  >  "
                        + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].subItems[this.parObj.pparObj].name
                        + "  >  " + this.parObj.name + "  >  " + this.name;
                    ids.push(parthis.allItems[parthis._type]()[this.parObj.pppparObj].id
                            + "-"
                            + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].id
                            + "-"
                            + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].subItems[this.parObj.pparObj].id
                            + "-" + this.parObj.id + "-" + this.id);
                    names_ids.push(parthis.allItems[parthis._type]()[this.parObj.pppparObj].id
                        + "-"
                        + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].id
                        + "-"
                        + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].subItems[this.parObj.pparObj].id
                        + "-" + this.parObj.id + "-" + this.id
                        +"_"+nameString);
                    idstemp
                        .push(parthis.allItems[parthis._type]()[this.parObj.pppparObj].id
                            + "-"
                            + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].id
                            + "-"
                            + parthis.allItems[parthis._type]()[this.parObj.pppparObj].subItems[this.parObj.ppparObj].subItems[this.parObj.pparObj].id
                            + "-" + this.parObj.id + "-" + this.id);
                  }
                  html2.push("<tr class='tr_loc'>");
                  html2.push("<td id='td1_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push(nameString);
                  html2.push("</td>");
                  html2.push("<td id='td2_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2
                      .push("<input class='input_loc' onfocus='checkTotalbfb();' onblur='checkTotalbfb();'  type='text' id='");
                  html2.push(idstemp.join('-'));
                  html2.push("'>");
                  html2.push("%");
                  html2.push("</td>");
                  html2.push("<td id='td3_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push("最多可分配");
                  html2.push("<span class='span_loc' id='span_loc_");
                  html2.push(idstemp.join('-'));
                  html2.push("'>");
                  html2.push("</span> ");
                  html2.push(" %");
                  html2.push("</td>");
                  html2.push("</tr>");

                } else {
                  nameString = this.name;
                  ids.push(this.id);
                  names_ids.push(this.id+"_"+this.name);
                  html2.push("<tr class='tr_loc'>");
                  html2.push("<td id='td1_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push(nameString);
                  html2.push("</td>");
                  html2.push("<td id='td2_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2
                      .push("<input onfocus='checkTotalbfb();' onblur='checkTotalbfb();' class='input_loc' size='10' type='text' id='");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push("%");
                  html2.push("</td>");
                  html2.push("<td id='td3_loc");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push("最多可分配");
                  html2.push("<span class='span_loc' id='span_loc_");
                  html2.push(this.id);
                  html2.push("'>");
                  html2.push("</span> ");
                  html2.push(" %");
                  html2.push("</td>");
                  html2.push("</tr>");
                }
                temp.push(nameString);
                html.push('<div class="list_m" id="' + renderfix
                    + '_divSelItem_' + this.id + '">');
                html.push('<div class="list_c">');
                html.push(nameString);
                html.push('</div>');
                html.push('<input type="button" value="删除" title="删除"');
                html.push('onclick="pLayer.remove(\'' + renderfix
                    + '_divSelItem_' + this.id + '\',\'' + questionStudyIds
                    + '\',\'' + renderSelItemDiv + '\')" />');
                html.push('</div>');
                // ids.push(this.id);
              });
    }

    if (isQueNum) {
      return;
    }

    if (parthis._type == "ind2") {
      if (sele_ques_num > 0) {
        jQuery(parthis._ref).val("");
        parthis._selItems[parthis._type] = parthis._curItems.clone();
        var p_num = jQuery("#questionNum").val();
        if (eval(p_num_sum) > eval(p_num)) {
          alert("合计试题数量： " + p_num_sum + " 道，超过了试卷总题量： " + p_num + " 道，请调整题量！");
          isQueNum = true;
        }
        if (eval(p_num_sum) < eval(p_num)) {
          alert("合计试题数量： " + p_num_sum + " 道，试卷总题量： " + p_num + " 道，还差 "
              + eval(eval(p_num) - eval(p_num_sum)) + " 道，请调整题量！");
          isQueNum = true;
          return false;
        }
        var p_score = jQuery("#paperScore").val();
        if (eval(p_score_sum) > eval(p_score)) {
          alert("合计试卷分数： " + p_score_sum + " 分，超过了试卷总分数： " + p_score
              + " 分，请调整分数！");
          isQueNum = true;
        }
        if (eval(p_score_sum) < eval(p_score)) {
          alert("合计试卷分数： " + p_score_sum + " 分，试卷总分数： " + p_score + " 分，还差 "
              + eval(eval(p_score) - eval(p_score_sum)) + " 分，请调整分数！");
          isQueNum = true;
        }

      } else {
        parthis._ql_ids.clear();
        parthis._selItems[parthis._type].clear();
        jQuery(parthis._ref).val("");
        alert("请设置题量！");
        isQueNum = true;
      }
      if (isQueNum) {
        return;
      }
    }

    if (parthis._type == "ind5") {
      if (sele_ques_num > 0) {
        jQuery(parthis._ref).val("");
        parthis._selItems[parthis._type] = parthis._curItems.clone();
        if(eval(p_num_sum) > 300) {
          alert("已选试题总量不能超过300道！");
          isQueNum = true;
        }

        /*var p_num = jQuery("#questionNum").val();
        if (eval(p_num_sum) > eval(p_num)) {
          alert("合计试题数量： " + p_num_sum + " 道，超过了试卷总题量： " + p_num + " 道，请调整题量！");
          isQueNum = true;
        }*/
        /*if (eval(p_num_sum) < eval(p_num)) {
          alert("合计试题数量： " + p_num_sum + " 道，试卷总题量： " + p_num + " 道，还差 "
              + eval(eval(p_num) - eval(p_num_sum)) + " 道，请调整题量！");
          isQueNum = true;
          return false;
        }*/
        //var p_score = jQuery("#paperScore").val();
        if (eval(p_score_sum) > 500) {
          /*alert("合计试卷分数： " + p_score_sum + " 分，超过了试卷总分数： " + p_score
              + " 分，请调整分数！");*/
          alert("已选试题总分数不能超过500分！");
          isQueNum = true;
        }
        /*if (eval(p_score_sum) < eval(p_score)) {
          alert("合计试卷分数： " + p_score_sum + " 分，试卷总分数： " + p_score + " 分，还差 "
              + eval(eval(p_score) - eval(p_score_sum)) + " 分，请调整分数！");
          isQueNum = true;
        }*/
      } else {
        parthis._ql_ids.clear();
        parthis._selItems[parthis._type].clear();
        //jQuery(parthis._ref).val("");
        alert("请设置题量！");
        isQueNum = true;
      }
      if (isQueNum) {
        return;
      } else {
        //设置试卷总题数和总分数
        jQuery("#questionNum").val(eval(p_num_sum));
        jQuery("#paperScore").val(eval(p_score_sum));
      }

      //精细策略      百分比
      if(renderSelItemDiv.name == 'txtInd2') {
        var studyNameIds = jQuery("#questionStudyNameIds").val();
        studyNamesIds = studyNameIds.split(",");
        for(i=0; i<studyNamesIds.length; i++) {
          var studyName_id = studyNamesIds[i].split("_");
          var id_Str = studyName_id[0]
          var name_String = studyName_id[1];

          html3.push("<tr class='tr_loc'>");
          html3.push("<td id='td1_loc");
          html3.push(id_Str);
          html3.push("'>");
          html3.push(name_String);
          html3.push("</td>");
          html3.push("<td id='td2_loc");
          html3.push(id_Str);
          html3.push("'>");
          html3.push("<input onfocus='checkTotalbfb();' onblur='checkTotalbfb();' class='input_loc' size='10' type='text' id='");
          html3.push(id_Str);
          html3.push("'>");
          html3.push("%");
          html3.push("</td>");
          html3.push("<td id='td3_loc");
          html3.push(id_Str);
          html3.push("'>");
          html3.push("最多可分配");
          html3.push("<span class='span_loc' id='span_loc_");
          html3.push(id_Str);
          html3.push("'>");
          html3.push("</span> ");
          html3.push(" %");
          html3.push("</td>");
          html3.push("</tr>");
        }

        html3.push("<tr class='tr_loc'>");
        html3.push("<td>");
        html3.push("汇总：");
        html3.push("</td>");
        html3.push("<td>");
        html3.push("<input type='text' id='totalbfb' value='0' size='10' readonly>%");
        html3.push("</td>");
        html3.push("</tr>");

        //if(renderSelItemDiv.name == 'txtInd2') {
          //jQuery(this._ref).val(dbcToSbc(temp.join("\r\n")));
        var lastTr = jQuery("#txtLoc").find("tr").last();
        lastTr.after(html3.join(""));
        //}
      }
    }

    $(renderSelItemDiv).innerHTML = html.join("");

    // document.getElementById(renderSelItemDiv).innerHTML = html.join("");
    // document.getElementById(questionStudyIds).value = ids.join(',');
    if (this._type == "loc") {
      jQuery("#fifthItems").hide();
      jQuery("#fourthItems").hide();
      jQuery("#thirdItems").hide();
      jQuery("#subItems").hide();
    }
    if (this._curItems) {
      if (this._type == "loc") {
        jQuery("#questionStudyIds").val(ids.join(','));
        jQuery("#questionStudyNameIds").val(names_ids.join(','));
      }
      if (this._type == "ind") {
        jQuery("#questCommonTypeIds").val(ids.join(','));
      }
      if (this._type == "ind2") {
        jQuery("#questionLabelIds").val(ids.join(','));
      }
      if (this._type == "ind5") {
        jQuery("#questionLabelIds").val(ids.join(','));
      }
      if (this._type == "ind3") {
        jQuery("#questPoint2_7_2").val(ids.join(','));
      }
      if (this._type == "ind4") {
        jQuery("#questionCognizeIds").val(ids.join(','));
      }
      if (this._type == "ind6") {
        jQuery("#questionTitleIds").val(ids.join(','));
      }
      if (this._type == "cat") {
        jQuery("#hidCat").val(ids.join(','));
      }
    }

    if (this._type == "loc" || this._type == "ind5") {
      if (jQuery("#questionStudyIds").val() == '') {
        alert("请选择内容！");
        return;
      }
      if ($("#paperMode").val() == 2 && renderSelItemDiv.name == 'txtInd2') {
        var data = countQues('true');
        if (data != '') {
          alert(data);
          jQuery("#questionStudyIds").val("");
          return;
        }
      }
    }

    if (this._type == "loc") {
      // jQuery(this._ref).val(dbcToSbc(temp.join("\r\n")));
      /*html2.push("<tr class='tr_loc'>");
      html2.push("<td>");
      html2.push("汇总：");
      html2.push("</td>");
      html2.push("<td>");
      html2
          .push("<input type='text' id='totalbfb' value='0' size='10' readonly>%");
      html2.push("</td>");
      html2.push("</tr>");

      if(renderSelItemDiv.name == 'txtInd2') {
        //jQuery(this._ref).val(dbcToSbc(temp.join("\r\n")));
        var lastTr = jQuery("#txtLoc").find("tr").last();
        lastTr.after(html2.join(""));
      }*/
    } else if (this._type == "ind2") {
      html2.push("<tr class='tr_ind2'>");
      html2.push("<td>");
      html2.push("汇总：");
      html2.push("</td>");
      html2.push("<td>");
      html2.push(tot_ti);
      html2.push("</td>");
      html2.push("<td>");
      html2.push(tot_fen);
      html2.push("</td>");
      html2.push("</tr>");
      var lastTr = jQuery(this._ref).find("tr").last();
      lastTr.after(html2.join(""));

    } else {
      jQuery(this._ref).val(dbcToSbc(temp.join("        ")));
    }

    if (renderSelItemDiv.name == 'txtLocc') {
      jQuery(this._ref).val(dbcToSbc(temp.join("\r\n")));
    }

    if (renderSelItemDiv.name == 'txtLoccc') {
      jQuery(this._ref).val(dbcToSbc(temp.join("\r\n")));
    }

    this.InitClass(temp, this._type);
    this._ref = null;
    this._selItems[this._type] = this._curItems.clone();
    this._curItems.clear();
    jQuery("#selecting").html("");
    jQuery("#mask").hide();
    jQuery(this._box).hide();

    if (this._type == "ind5") {
      if ($("#paperMode").val() == 2) {
        var data = countQues('');
        writeCount(data);
      }
    }
  },

  empty : function() {
    jQuery("#selecting").html("");
    jQuery("#allItems1 input").each(function(i) {
      this.checked = false;
      this.disabled = false;
    });
    jQuery("#allItems2 input").each(function(i) {
      this.checked = false;
    });
    jQuery(".shu").each(function(i) {
      this.value = 0;
    });
    jQuery(".fen").each(function(i) {
      this.value = 1;
    });
    jQuery("#allItems1 li").each(function(i) {
      if (this.className == "layicon")
        this.className = "";
      if (this.className == "layon")
        this.className = "nonelay";
    });
    jQuery("#allItems2 li").each(function(i) {
      if (this.className == "layicon")
        this.className = "";
      if (this.className == "layon")
        this.className = "nonelay";
    });
    this._curItems.clear();
    PopupSelector.showtips();
    // this.showHideSelecting(this);
  },
  checkAll : function() {
    var custem = this._curItems;
    jQuery("#allItems1 input").each(function(i) {
      this.checked = true;
      var item = {
        id : this.value.split("@")[0],
        name : this.value.split("@")[1],
        parObj : null
      };
      if (!custem.has(item)) {
        custem.push(item);
      }
    });
    if (this._type == 'ind') {
      jQuery("#allItems2 input").each(function(i) {
        this.checked = true;
        var item = {
          id : this.value.split("@")[0],
          name : this.value.split("@")[1],
          parObj : null
        };
        if (!custem.has(item)) {
          custem.push(item);
        }
      });
    }
    jQuery("#allItems1 li").each(function(i) {
      if (this.className == "nonelay")
        this.className = "layon";
    });
    jQuery("#allItems2 li").each(function(i) {
      if (this.className == "nonelay")
        this.className = "layon";
    });
  },
  InSalaryPage : false,
  InAdvanceSearchPage : false,
  click : function(ref, cbx, parObj, parName, lev, event) {
    // jQuery("#debugInfo").html("ref: " + ref + " cbx: " + cbx + " parObj:" +
    // parObj + " parName: " + parName + " lev:" + lev);
    if (this._type == "loc") {
      stopPropagation(event);
      if (cbx.checked && this._curItems.length == this._maxSize) {
        alert(this._gtMaxLimit);
        cbx.checked = false;
        return;
      }
    }
    var item = {
      id : cbx.value.split("@")[0],
      name : cbx.value.split("@")[1],
      parObj : parObj
    };
    var selecting = document.getElementById("selecting");
    var selItem = null;

    if (cbx.checked) {
      if (parName != "999999") {
        selItem = document.createElement("li");
        selItem.id = item.id;
        if (parObj == null) {
          // 移除2,3,4,5级开始
          var parItem = this.allItems[this._type]()[ref];
          var parthis = this;
          jQuery(parthis._curItems)
              .each(
                  function() {
                    var tt = this.id;
                    itemSubstring = document.getElementById(tt);
                    if (itemSubstring != null) {
                      var tmp = false;
                      if (parItem.subItems && parItem.subItems.length > 0) {
                        for ( var i = 0; i < parItem.subItems.length; i++) {
                          if (parItem.subItems[i].id == tt)
                            tmp = true;
                          if (parItem.subItems[i].subItems
                              && parItem.subItems[i].subItems.length > 0) {
                            for ( var j = 0; j < parItem.subItems[i].subItems.length; j++) {
                              if (parItem.subItems[i].subItems[j].id == tt)
                                tmp = true;
                              if (parItem.subItems[i].subItems[j].subItems
                                  && parItem.subItems[i].subItems[j].subItems.length > 0) {
                                for ( var m = 0; m < parItem.subItems[i].subItems[j].subItems.length; m++) {
                                  if (parItem.subItems[i].subItems[j].subItems[m].id == tt)
                                    tmp = true;
                                  jQuery(
                                      parItem.subItems[i].subItems[j].subItems[m].subItems)
                                      .each(function() {
                                        if (this.id == tt)
                                          tmp = true;
                                      });
                                }
                              }
                            }
                          }

                        }
                      }
                      if (tmp == true) {
                        selecting.removeChild(itemSubstring);
                        parthis._curItems.remove(this.id);
                      }
                    }
                  });
          // 移除2,3,4,5级结束
          selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove("
              + item.id + ",null);\">" + dbcToSbc(item.name) + "</a>";
        } else {
          var nameString;
          if (lev == 5) {
            // var parItemsVal = this.allItems[this._type]();
            // var indexval = parseInt(parObj.parObj);
            // parItem = parItemsVal[indexval];
            parItem = this.allItems[this._type]()[parObj.pppparObj].subItems[parObj.ppparObj].subItems[parObj.pparObj].subItems[parObj.parObj].subItems[ref];
            // nameString = parItem.name + " > " + parObj.name + " > " +
            // item.name;
            nameString = this.allItems[this._type]()[parObj.pppparObj].name
                + "  >  "
                + this.allItems[this._type]()[parObj.pppparObj].subItems[parObj.ppparObj].name
                + "  >  "
                + this.allItems[this._type]()[parObj.pppparObj].subItems[parObj.ppparObj].subItems[parObj.pparObj].name
                + "  >  " + parObj.name + "  >  " + item.name;
            selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.removelv3("
                + item.id
                + ","
                + parObj.id
                + ","
                + parItem.id
                + ","
                + parObj.parObj + ");\">" + dbcToSbc(nameString) + "</a>";
          } else {
            // 移除3级开始
            var parItem;
            if (lev == 2) {
              parItem = this.allItems[this._type]()[parObj.parObj].subItems[ref];
            } else if (lev == 3) {
              parItem = this.allItems[this._type]()[parObj.pparObj].subItems[parObj.parObj].subItems[ref];
            } else if (lev == 4) {
              parItem = this.allItems[this._type]()[parObj.ppparObj].subItems[parObj.pparObj].subItems[parObj.parObj].subItems[ref];
            }
            var parthis = this;
            jQuery(parthis._curItems)
                .each(
                    function() {
                      var tt = this.id;
                      itemSubstring = document.getElementById(tt);
                      if (itemSubstring != null) {
                        var tmp = false;
                        if (parItem.subItems && parItem.subItems.length > 0) {
                          for ( var i = 0; i < parItem.subItems.length; i++) {
                            if (parItem.subItems[i].id == tt)
                              tmp = true;
                            if (parItem.subItems[i].subItems
                                && parItem.subItems[i].subItems.length > 0) {
                              for ( var j = 0; j < parItem.subItems[i].subItems.length; j++) {
                                if (parItem.subItems[i].subItems[j].id == tt)
                                  tmp = true;
                                if (parItem.subItems[i].subItems[j].subItems
                                    && parItem.subItems[i].subItems[j].subItems.length > 0) {
                                  for ( var m = 0; m < parItem.subItems[i].subItems[j].subItems.length; m++) {
                                    if (parItem.subItems[i].subItems[j].subItems[m].id == tt)
                                      tmp = true;
                                    jQuery(
                                        parItem.subItems[i].subItems[j].subItems[m].subItems)
                                        .each(function() {
                                          if (this.id == tt)
                                            tmp = true;
                                        });
                                  }
                                }
                              }
                            }

                          }
                        }
                        if (tmp == true) {
                          selecting.removeChild(itemSubstring);
                          parthis._curItems.remove(this.id);
                        }
                      }
                    });
            // 移除3级结束
            if (this._type == "loc") {
              if (lev == 2) {
                nameString = parObj.name + "  >  " + item.name;
              } else if (lev == 3) {
                nameString = this.allItems[this._type]()[parObj.pparObj].name
                    + "  >  " + parObj.name + "  >  " + item.name;
              } else if (lev == 4) {
                nameString = this.allItems[this._type]()[parObj.ppparObj].name
                    + "  >  "
                    + this.allItems[this._type]()[parObj.ppparObj].subItems[parObj.pparObj].name
                    + "  >  " + parObj.name + "  >  " + item.name;
              }
            } else {
              nameString = item.name;
            }
            selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove("
                + item.id
                + ","
                + parObj.id
                + ","
                + parObj.parObj
                + ");\">"
                + dbcToSbc(nameString) + "</a>";
          }
        }
        selecting.appendChild(selItem);
        this._curItems.push(item);
      } else {
        var parthis = this;

        // 移除先前选择过的地区
        if (parthis._curItems.length > 0) {

          jQuery(parthis._curItems).each(function() {
            var tt = this.id;
            itemSubstring = document.getElementById(tt);
            if (itemSubstring != null) {
              var tmp = false;
              jQuery("#allItems2 input").each(function() {
                if (this.value.split("@")[0] == tt)
                  tmp = true;
              });
              if (tmp != true) {
                selecting.removeChild(itemSubstring);
                parthis._curItems.remove(this.id);
              }
            }
          });

        }
        PopupSelector.showtips();
        // 添加所有地区
        jQuery("#allItems1 input")
            .each(
                function() {
                  if (this.id != "pcbx999999") {
                    var itemSubstring = null;
                    var selItemSubstring = null;
                    itemSubstring = {
                      id : this.value.split("@")[0],
                      name : this.value.split("@")[1],
                      parObj : null
                    };
                    selItemSubstring = document.createElement("li");
                    selItemSubstring.id = itemSubstring.id;
                    selItemSubstring.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove("
                        + itemSubstring.id
                        + ",null);\">"
                        + dbcToSbc(itemSubstring.name) + "</a>";
                    selecting.appendChild(selItemSubstring);
                    parthis._curItems.push(itemSubstring);
                  }
                });
      }
      this.showHideSelecting(this);
    } else {
      if (item.id == "999999") {
        var parthis = this;
        jQuery("#allItems1 input").each(function() {
          if (this.id != "pcbx999999") {
            var itemSubstring = null;
            itemSubstring = document.getElementById(this.value.split("@")[0]);
            if (itemSubstring != null) {
              selecting.removeChild(itemSubstring);
              parthis._curItems.remove(this.value.split("@")[0]);
            }
          }
        });
      } else {
        selItem = document.getElementById(item.id);
        selecting.removeChild(selItem);
        this._curItems.remove(item.id);
      }
      PopupSelector.showtips();
    }
    // 设置样式
    if (parObj == null) {// 选择省一级，将下一级全部选中
      var parItems = this.allItems[this._type]();
      parItem = parItems[ref];
      if (cbx.checked) {
        if (parItem.subItems && parItem.subItems.length > 1)
          document.getElementById('jQuery' + ref).className = "layicon";
        else
          document.getElementById('jQuery' + ref).className = "layon";

      } else {
        if ((parItem.subItems && parItem.subItems.length > 1))
          document.getElementById('jQuery' + ref).className = "";
        else
          document.getElementById('jQuery' + ref).className = "nonelay";

      }
      if (parName == "999999") {
        jQuery("#allItems1 input").each(function(i) {
          if (this.id != "pcbx999999")
            this.checked = this.disabled = cbx.checked;
          var parItemSub = parItems[i];
          if (cbx.checked) {
            if (parItemSub.subItems && parItemSub.subItems.length > 1)
              document.getElementById('jQuery' + i).className = "layicon";
            else
              document.getElementById('jQuery' + i).className = "layon";
          } else {
            if (parItemSub.subItems && parItemSub.subItems.length > 1)
              document.getElementById('jQuery' + i).className = "";
            else
              document.getElementById('jQuery' + i).className = "nonelay";
          }
        });
      } else {
        try {
          if (parItem.subItems && parItem.subItems.length > 1)
            var itemType = this._type;
          jQuery("#subItems input").each(
              function(i) {
                this.checked = this.disabled = cbx.checked;
                var ItemSubCollection = parItem.subItems[i];
                var idString = "^" + i;
                if (itemType != "loc")
                  idString = "^" + (i + 1);
                var subItems = document.getElementById(idString);
                if (subItems != null) {
                  if (cbx.checked) {
                    if (ItemSubCollection.subItems
                        && ItemSubCollection.subItems.length > 1) {
                      subItems.className = "layicon";
                    } else {
                      subItems.className = "layon";
                    }
                  } else {
                    if (ItemSubCollection.subItems
                        && ItemSubCollection.subItems.length > 1)
                      subItems.className = "";
                    else
                      subItems.className = "nonelay";
                  }
                }
              });
        } catch (err) {

        }
      }
    } else {
      var parItems = this.allItems[this._type]();
      var indexString;
      if (lev == 2) {
        for ( var i = 0; i < parItems.length; i++) {
          if (parItems[i].id == parObj.id) {
            parItem = parItems[i];
            indexString = i;
            break;
          }
        }
      } else {
        indexString = parObj.parObj;
      }

      if (lev == "2") {
        jQuery("#thirdItems input").each(function(i) {
          this.checked = this.disabled = cbx.checked;
        });
        if (cbx.checked) {
          jQuery("#thirdItems li").each(function(i) {
            this.className = "layon";
          });
        } else {
          jQuery("#thirdItems li").each(function(i) {
            this.className = "nonelay";
          });
        }
      } else if (lev == 3) {
        jQuery("#fourthItems input").each(function(i) {
          this.checked = this.disabled = cbx.checked;
        });
        if (cbx.checked) {
          jQuery("#fourthItems li").each(function(i) {
            this.className = "layon";
          });
        } else {
          jQuery("#fourthItems li").each(function(i) {
            this.className = "nonelay";
          });
        }
      } else if (lev == 4) {
        jQuery("#fifthItems input").each(function(i) {
          this.checked = this.disabled = cbx.checked;
        });
        if (cbx.checked) {
          jQuery("#fifthItems li").each(function(i) {
            this.className = "layon";
          });
        } else {
          jQuery("#fifthItems li").each(function(i) {
            this.className = "nonelay";
          });
        }
      }

    }

  },
  showtips : function() {
    if (this._curItems.length == 0) {
      if (this._type != "ind" && this._type != "ind2" && this._type != "ind3"
          && this._type != "ind4" && this._type != "ind5"
          && this._type != "ind6")
        jQuery("#divSelecting").css("display", "none");
      if (this._type == "cat") {
        jQuery("#noSelectedLoc").hide();
        if (this.InSalaryPage) {
          jQuery("#noSelectedCat p").html(
              jQuery("#noSelectedCat p").html().replace("您最多可以选择5个职位类别",
                  "&nbsp;"));
        }
        jQuery("#noSelectedCat").show();
      }
      if (this._type == "loc") {
        jQuery("#noSelectedCat").hide();
        if (this.InSalaryPage) {
          jQuery("#noSelectedLoc p").html(
              jQuery("#noSelectedLoc p").html().replace("您最多可以选择5个工作地点", ""));
        }
        jQuery("#noSelectedLoc").show();
      }

    }
  },

  remove : function(id, parObj, index) {
    document.getElementById("selecting").removeChild(
        document.getElementById(id));
    var pcbx = document.getElementById("pcbx" + id);
    if (pcbx)
      pcbx.checked = false;
    var array = this._curItems.clone();
    this._curItems.clear();
    for ( var i = 0; i < array.length; i++) {
      if (array[i].id != id)
        this._curItems.push(array[i]);
    }

    if (parObj == null) {
      jQuery("li[name=" + id + "]").removeClass("layicon");
      jQuery("li[name=" + id + "]").removeClass("layon");
      for ( var i = 32; i < this.allItems[this._type]().length; i++) {

        if (this.allItems[this._type]()[i].id == id
            && this.allItems[this._type]()[i].subItems.length <= 1) {
          jQuery("li[name=" + id + "]").addClass("nonelay");
          break;
        }
      }
    } else {
      var flag = false;
      if (index != undefined) {
        var tmpdata = this.allItems[this._type]()[index].subItems;
        for ( var i = 0; i < tmpdata.length; i++) {
          for ( var m = 0; m < this._curItems.length; m++) {
            if (this._curItems[m].id != null
                && this._curItems[m].id == tmpdata[i].id)
              flag = true;
            if (this._curItems[m].parObj != null
                && this._curItems[m].parObj.id == tmpdata[i].id)
              flag = true;
          }
        }
      }
      if (flag == false)
        if (!(this._curItems.GetFather({
          id : parObj,
          name : null,
          parObj : null
        })) && !(this._curItems.GetFaFather({
          id : parObj,
          name : null,
          parObj : null
        })))
          jQuery("li[name=" + parObj + "]").removeClass("layicon");
    }
    var temp = jQuery("#allItems1 li[name=" + id + "]");
    if (temp.length > 0) {
      var pcbxAll = document.getElementById("pcbx999999");
      if (pcbxAll != null && pcbxAll.checked) {
        jQuery("#allItems1 input").each(function(i) {
          this.disabled = false;
        });
        pcbxAll.checked = false;
        jQuery("li[name=999999]").attr("class", "nonelay");
      }
    }
    PopupSelector.showtips();
    // this.showHideSelecting(this);
  },
  removelv3 : function(id, parObj, fname, index) {
    document.getElementById("selecting").removeChild(
        document.getElementById(id));
    var pcbx = document.getElementById("pcbx" + id);
    if (pcbx)
      pcbx.checked = false;
    var array = this._curItems.clone();
    this._curItems.clear();
    for ( var i = 0; i < array.length; i++) {
      if (array[i].id != id)
        this._curItems.push(array[i]);
    }
    if (parObj == null) {
      jQuery("li[name=" + id + "]").removeClass("layicon");
      jQuery("li[name=" + id + "]").removeClass("layon");
    } else {
      var flag = false;
      var tmpdata = this.allItems[this._type]()[index].subItems;
      for ( var i = 0; i < tmpdata.length; i++) {
        for ( var m = 0; m < this._curItems.length; m++) {
          if (this._curItems[m].id != null
              && this._curItems[m].id == tmpdata[i].id)
            flag = true;
          if (this._curItems[m].parObj != null
              && this._curItems[m].parObj.id == tmpdata[i].id)
            flag = true;
        }
      }
      if (flag == false)
        jQuery("li[name=" + fname + "]").removeClass("layicon");
    }
    PopupSelector.showtips();
    // this.showHideSelecting(this);
  },

  // private method
  showHideSelecting : function(selector) {
    jQuery("#noSelectedCat").hide();
    jQuery("#noSelectedLoc").hide();

    if (selector._curItems.length > 0) {
      jQuery("#divSelecting").show();
    }
    // else {
    // if (selector._type != "ind") jQuery("#divSelecting").hide();
    // if (selector._type == "cat") jQuery("#noSelectedCat").show();
    // else if (selector._type == "loc") jQuery("#noSelectedLoc").show();
    // }
  },

  showSubItems : function(index, firstid, ref, isDelay, ev) {
    var subItems = jQuery("#subItems");
    if (this._hideTimer)
      clearTimeout(this._hideTimer);
    if (this._showTimer)
      clearTimeout(this._showTimer);
    if (index == this._lastPopupIndex && subItems.css("display") == "block") {
      jQuery(ref).addClass("layshow");
      return;
    }
    if (!isDelay)
      loadSubItems(index, ref, this);
    var self = this;
    this._showTimer = setTimeout(function() {
      loadSubItems(index, ref, self);
    }, this._delay);

    function loadSubItems(index, ref, self) {
      var parItem = self.allItems[self._type]()[index];
      var offset = jQuery(ref).offset();
      var pos = {
        top : offset.top + self._style.offset.levelTwo[self._type].Y,
        left : offset.left + self._style.offset.levelTwo[self._type].X
      };
      if (document.body.clientWidth < pos.left + 296) {
        pos.left = offset.left - 296;
      }
      var actualSubItemCount = parItem.subItems.length - 1;
      var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2
          : (actualSubItemCount / 2 + 1);
      if (actualSubItemCount <= self._oneColumnLimit[self._type])
        calItemCount = actualSubItemCount;
      var subBoxHeight = self._style.lineHeight * parseInt(calItemCount)
          + self._style.topBottomMargin;
      var winHeight = jQuery(window).height();
      // if (jQuery.browser.msie && jQuery.browser.version == '6.0') scrolTop =
      // document.body.scrollTop;
      /*if (jQuery.browser.msie || jQuery.browser.mozilla)
        scrolTop = document.body.scrollTop;
      else*/
      var scrolTop = document.documentElement.scrollTop;
      var remainHeight = winHeight - pos.top + scrolTop;
//      alert("1742 pos.top"+pos.top)
//      if (remainHeight < subBoxHeight){
//    	  pos.top = pos.top - (subBoxHeight - remainHeight);
//    	  alert("1745 pos.top"+pos.top)
//      }

      // 如果整个IE窗口的高度小于弹出层的高度，则弹出层以对齐顶部显示
      if (subBoxHeight > winHeight){
    	  pos.top = scrolTop;
    	  //alert("1751 pos.top"+pos.top)
      }


      var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled "
          : "";
      var item = {
        id : parItem.id,
        name : parItem.name,
        parObj : null
      };
      var html = [];
      html.push("<ol >");
      var startIndex = 1; // 因为职类的子项的第一项是父项用于职位分类搜索 所以应该把第一个子项过滤掉

      if (self._type == "loc") {
        startIndex = 0;
      }
      for ( var i = startIndex; i < parItem.subItems.length; i++) {
        var subItem = parItem.subItems[i];
        subItem.parObj = item;
        if (ref.getElementsByTagName("input")[0].checked == true
            || self._curItems.has(subItem) || self._curItems.GetFather(subItem)) {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html
                .push("<li id=^"
                    + i
                    + " name="
                    + parItem.id
                    + "  class=\"layicon\"  onclick=\"PopupSelector.showThirdItems(");
            html.push(index);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideThirdItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"scbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','2')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"layon\" onclick='PopupSelector.clearThirdHidden()' onclick='PopupSelector.hideThirdItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"scbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"scbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','2')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        } else {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html.push("<li id=^" + i + " name=" + parItem.id
                + " onclick=\"PopupSelector.showThirdItems(");
            html.push(index);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideThirdItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"scbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','2')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"nonelay\" onclick='PopupSelector.clearThirdHidden()' onclick='PopupSelector.hideThirdItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"scbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"scbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','2')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        }

      }
      html.push("</ol>");
      var subBox = jQuery("#subBox");
      for ( var i = 0; i < self._types.length; i++) {
        subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
        subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
      }
      var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type]
          : self._style.className.levelTwo1[self._type];
      if (self._type == "loc") {
        if (parItem.id == "8000" || parItem.id == "15000") {
          levelTwo = self._style.className.levelTwo1[self._type];
        }
      }
      self._lastPopupIndex = index;
      jQuery("#subItems").hover(function(e) {
        self.showSubItems(index, firstid, ref, true, e);
      }, function(e) {
        self.hideSubItems(ref);

      });
      subBox.html(html.join(""));

      //
      //pos.top +=250;

      subItems.addClass(levelTwo).css(pos).show();
    }
  },

  showThirdItems : function(index, secondIndex, ref, isDelay, ev) {
    var subItems = jQuery("#thirdItems");
    if (this._showThirdTimer)
      clearTimeout(this._showThirdTimer);
    if (this._hideThirdTimer)
      clearTimeout(this._hideThirdTimer);

    if ((secondIndex + 50) == this._lastPopupIndex
        && subItems.css("display") == "block") {
      jQuery("#jQuery" + index).addClass("layshow");
      jQuery(ref).addClass("layshow");
      return;
    }
    if (!isDelay)
      loadThirdItems(index, secondIndex, ref, this);
    var self = this;
    this._showThirdTimer = setTimeout(function() {
      loadThirdItems(index, secondIndex, ref, self);
    }, this._delay);

    function loadThirdItems(index, secondIndex, ref, self) {
      var parItem = self.allItems[self._type]()[index].subItems[secondIndex];
      var offset = jQuery(ref).offset();
      var pos = {
        top : offset.top + self._style.offset.levelTwo[self._type].Y,
        left : offset.left + self._style.offset.levelTwo[self._type].X
      };
      if (document.body.clientWidth < pos.left + 296) {
        pos.left = offset.left - 296;
      }
      var actualSubItemCount = parItem.subItems.length - 1;
      var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2
          : (actualSubItemCount / 2 + 1);
      if (actualSubItemCount <= self._oneColumnLimit[self._type])
        calItemCount = actualSubItemCount;
      var subBoxHeight = self._style.lineHeight * parseInt(calItemCount)
          + self._style.topBottomMargin;
      var winHeight = jQuery(window).height();
      // if (jQuery.browser.msie && jQuery.browser.version == '6.0') scrolTop =
      // document.body.scrollTop;
      /*if (jQuery.browser.msie || jQuery.browser.mozilla)
        scrolTop = document.body.scrollTop;
      else*/
      var scrolTop = document.documentElement.scrollTop;
      var remainHeight = winHeight - pos.top + scrolTop;
//      if (remainHeight < subBoxHeight)
//        pos.top = pos.top - (subBoxHeight - remainHeight);
      // 如果整个IE窗口的高度小于弹出层的高度，则弹出层以对齐顶部显示
      if (subBoxHeight > winHeight)
        pos.top = scrolTop;

      var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled "
          : "";
      var item = {
        id : parItem.id,
        name : parItem.name,
        parObj : null
      };
      var html = [];
      html.push("<ol >");
      var startIndex = 1; // 因为职类的子项的第一项是父项用于职位分类搜索 所以应该把第一个子项过滤掉

      if (self._type == "loc") {
        startIndex = 0;
      }
      for ( var i = startIndex; i < parItem.subItems.length; i++) {
        var subItem = parItem.subItems[i];
        subItem.parObj = item;
        if (ref.getElementsByTagName("input")[0].checked == true
            || self._curItems.has(subItem) || self._curItems.GetFather(subItem)) {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html
                .push("<li id=^^"
                    + i
                    + " name="
                    + parItem.id
                    + "  class=\"layicon\"  onclick=\"PopupSelector.showFourthItems(");
            html.push(index);
            html.push(",")
            html.push(secondIndex);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideFourthItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"tcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html
                .push("', parObj: " + secondIndex + ",pparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','3')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"layon\" onclick='PopupSelector.clearFourthHidden()' onclick='PopupSelector.hideFourthItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"tcbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"tcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html
                .push("', parObj: " + secondIndex + ",pparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','3')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        } else {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html.push("<li id=^^" + i + " name=" + parItem.id
                + " onclick=\"PopupSelector.showFourthItems(");
            html.push(index);
            html.push(",")
            html.push(secondIndex);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideFourthItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"tcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html
                .push("', parObj: " + secondIndex + ",pparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','3')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"nonelay\" onclick='PopupSelector.clearFourthHidden()' onclick='PopupSelector.hideFourthItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"tcbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"tcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html
                .push("', parObj: " + secondIndex + ",pparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','3')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        }

      }
      html.push("</ol>");
      var subBox = jQuery("#thirdBox");
      for ( var i = 0; i < self._types.length; i++) {
        subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
        subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
      }
      var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type]
          : self._style.className.levelTwo1[self._type];
      if (self._type == "loc") {
        if (parItem.id == "8000" || parItem.id == "15000") {
          levelTwo = self._style.className.levelTwo1[self._type];
        }
      }
      self._lastPopupIndex = secondIndex + 50;
      jQuery("#thirdItems").hover(function(e) {
        // alert(self.thirdHoverIn());
        // debugger;
        if (self._hideTimer)
          clearTimeout(self._hideTimer);
        if (self._hideThirdTimer)
          clearTimeout(self._hideThirdTimer);
        // self.showThirdItems(index, secondIndex, ref, isDelay, ev);
        self.showThirdItems(index, secondIndex, ref, true, e);
      }, function(e) {
        self.hideThirdItems();
      });
      subBox.html(html.join(""));
      subItems.addClass(levelTwo).css(pos).show();
    }
  },
  showFourthItems : function(index, secondIndex, thirdIndex, ref, isDelay, ev) {
    var subItems = jQuery("#fourthItems");
    if (this._showFourthTimer)
      clearTimeout(this._showFourthTimer);
    if (this._hideFourthTimer)
      clearTimeout(this._hideFourthTimer);

    if ((thirdIndex + 50) == this._lastPopupIndex
        && subItems.css("display") == "block") {
      jQuery("#jQuery" + thirdIndex).addClass("layshow");
      jQuery(ref).addClass("layshow");
      return;
    }
    if (!isDelay)
      loadFourthItems(index, secondIndex, thirdIndex, ref, this);
    var self = this;
    this._showFourthTimer = setTimeout(function() {
      loadFourthItems(index, secondIndex, thirdIndex, ref, self);
    }, this._delay);

    function loadFourthItems(index, secondIndex, thirdIndex, ref, self) {
      var parItem = self.allItems[self._type]()[index].subItems[secondIndex].subItems[thirdIndex];

      var offset = jQuery(ref).offset();
      var pos = {
        top : offset.top + self._style.offset.levelTwo[self._type].Y,
        left : offset.left + self._style.offset.levelTwo[self._type].X
      };
      if (document.body.clientWidth < pos.left + 296) {
        pos.left = offset.left - 296;
      }
      var actualSubItemCount = parItem.subItems.length - 1;
      var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2
          : (actualSubItemCount / 2 + 1);
      if (actualSubItemCount <= self._oneColumnLimit[self._type])
        calItemCount = actualSubItemCount;
      var subBoxHeight = self._style.lineHeight * parseInt(calItemCount)
          + self._style.topBottomMargin;
      var winHeight = jQuery(window).height();
      // if (jQuery.browser.msie && jQuery.browser.version == '6.0') scrolTop =
      // document.body.scrollTop;
      /*if (jQuery.browser.msie || jQuery.browser.mozilla)
        scrolTop = document.body.scrollTop;
      else*/
      var scrolTop = document.documentElement.scrollTop;
      var remainHeight = winHeight - pos.top + scrolTop;
//      if (remainHeight < subBoxHeight)
//        pos.top = pos.top - (subBoxHeight - remainHeight);
      // 如果整个IE窗口的高度小于弹出层的高度，则弹出层以对齐顶部显示
      if (subBoxHeight > winHeight)
        pos.top = scrolTop;

      var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled "
          : "";
      var item = {
        id : parItem.id,
        name : parItem.name,
        parObj : null
      };
      var html = [];
      html.push("<ol >");
      var startIndex = 1; // 因为职类的子项的第一项是父项用于职位分类搜索 所以应该把第一个子项过滤掉

      if (self._type == "loc") {
        startIndex = 0;
      }
      for ( var i = startIndex; i < parItem.subItems.length; i++) {
        var subItem = parItem.subItems[i];
        subItem.parObj = item;
        if (ref.getElementsByTagName("input")[0].checked == true
            || self._curItems.has(subItem) || self._curItems.GetFather(subItem)) {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html
                .push("<li id=^^^"
                    + i
                    + " name="
                    + parItem.id
                    + "  class=\"layicon\"  onclick=\"PopupSelector.showFifthItems(");
            html.push(index);
            html.push(",")
            html.push(secondIndex);
            html.push(",")
            html.push(thirdIndex);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideFifthItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"fcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + thirdIndex + ",pparObj: " + secondIndex
                + ",ppparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','4')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^^^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"layon\" onclick='PopupSelector.clearFifthHidden()' onclick='PopupSelector.hideFifthItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"fcbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"fcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + thirdIndex + ",pparObj: " + secondIndex
                + ",ppparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','4')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        } else {
          if (parItem.subItems[i].subItems
              && parItem.subItems[i].subItems.length > 0) {
            html.push("<li id=^^^" + i + " name=" + parItem.id
                + " onclick=\"PopupSelector.showFifthItems(");
            html.push(index);
            html.push(",")
            html.push(secondIndex);
            html.push(",")
            html.push(thirdIndex);
            html.push(",")
            html.push(i);
            html
                .push(", this, true)\" onmouseout=\"PopupSelector.hideFifthItems(this)\">");
            html.push("<a href=\"javascript:void(0);\">");
            html.push("<input id=\"fcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + thirdIndex + ",pparObj: " + secondIndex
                + ",ppparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','4')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</a></li>");
          } else {
            html
                .push("<li id=^^^"
                    + i
                    + " name="
                    + parItem.id
                    + " class=\"nonelay\" onclick='PopupSelector.clearFifthHidden()' onclick='PopupSelector.hideFifthItems(this)'>");
            html.push("<a href=\"javascript:void(0);\"><label for=\"fcbx");
            html.push(subItem.id);
            html.push("\">");
            html.push("<input id=\"fcbx");
            html.push(subItem.id);
            html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
            html.push(subItem.id);
            html.push("@");
            html.push(subItem.name);
            html.push("\"");
            html.push(parentChecked);
            if (self._curItems.has(subItem))
              html.push(" checked");
            html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
            html.push(parItem.id);
            html.push(", name: '");
            html.push(parItem.name);
            html.push("', parObj: " + thirdIndex + ",pparObj: " + secondIndex
                + ",ppparObj: " + index + " }");
            // html.push("', parObj:null }");
            // html.push(",'')\" />");
            html.push(",'','4')\" />");
            html.push(dbcToSbc(subItem.name));
            html.push("</label></a></li>");
          }
        }

      }
      html.push("</ol>");
      var subBox = jQuery("#fourthBox");
      for ( var i = 0; i < self._types.length; i++) {
        subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
        subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
      }
      var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type]
          : self._style.className.levelTwo1[self._type];
      if (self._type == "loc") {
        if (parItem.id == "8000" || parItem.id == "15000") {
          levelTwo = self._style.className.levelTwo1[self._type];
        }
      }
      self._lastPopupIndex = thirdIndex + 50;
      jQuery("#fourthItems").hover(function(e) {
        // alert(self.thirdHoverIn());
        // debugger;
        if (self._hideTimer)
          clearTimeout(self._hideTimer);
        if (self._hideThirdTimer)
          clearTimeout(self._hideThirdTimer);
        if (self._hideFourthTimer)
          clearTimeout(self._hideFourthTimer);
        // self.showThirdItems(index, secondIndex, ref, isDelay, ev);
        self.showFourthItems(index, secondIndex, thirdIndex, ref, true, e);
      }, function(e) {
        self.hideFourthItems();
      });
      subBox.html(html.join(""));
      subItems.addClass(levelTwo).css(pos).show();
    }
  },

  hideSubItems : function(ref) {
    jQuery(ref).removeClass("layshow");
    if (this._showTimer)
      clearTimeout(this._showTimer);
    if (this._hideTimer)
      clearTimeout(this._hideTimer);
    this._hideTimer = setTimeout(function() {
      jQuery("#subItems").hide();
    }, 100);
  },
  clearThirdHidden : function() {
    if (this._showThirdTimer)
      clearTimeout(this._showThirdTimer);
    if (this._hideThirdTimer)
      clearTimeout(this._hideThirdTimer);
    jQuery("#thirdItems").hide();
  },
  clearFourthHidden : function() {
    if (this._showFourthTimer)
      clearTimeout(this._showFourthTimer);
    if (this._hideFourthTimer)
      clearTimeout(this._hideFourthTimer);
    jQuery("#fourthItems").hide();
  },
  clearFifthHidden : function() {
    if (this._showFifthTimer)
      clearTimeout(this._showFifthTimer);
    if (this._hideFifthTimer)
      clearTimeout(this._hideFifthTimer);
    jQuery("#fifthItems").hide();
  },
  showFifthItems : function(index, secondIndex, thirdIndex, fourthIndex, ref,
      isDelay, ev) {

    var subItems = jQuery("#fifthItems");

    if (this._showFifthTimer)
      clearTimeout(this._showFifthTimer);
    if (this._hideFifthTimer)
      clearTimeout(this._hideFifthTimer);

    if ((fourthIndex + 50) == this._lastPopupIndex
        && subItems.css("display") == "block") {
      jQuery("#jQuery" + index).addClass("layshow");
      jQuery(ref).addClass("layshow");
      return;
    }
    if (!isDelay)
      loadFifthItems(index, secondIndex, thirdIndex, fourthIndex, ref, this);
    var self = this;
    this._showFifthTimer = setTimeout(function() {
      showFifthItems(index, secondIndex, thirdIndex, fourthIndex, ref, self);
    }, this._delay);

    function showFifthItems(index, secondIndex, thirdIndex, fourthIndex, ref,
        self) {

      // var parItem = self.allItems[self._type]()[index];
      var parItem = self.allItems[self._type]()[index].subItems[secondIndex].subItems[thirdIndex].subItems[fourthIndex];
      var offset = jQuery(ref).offset();
      var pos = {
        top : offset.top + self._style.offset.levelTwo[self._type].Y,
        left : offset.left + self._style.offset.levelTwo[self._type].X
      };
      if (document.body.clientWidth < pos.left + 296) {
        pos.left = offset.left - 296;
      }
      var actualSubItemCount = parItem.subItems.length - 1;
      var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2
          : (actualSubItemCount / 2 + 1);
      if (actualSubItemCount <= self._oneColumnLimit[self._type])
        calItemCount = actualSubItemCount;
      var subBoxHeight = self._style.lineHeight * parseInt(calItemCount)
          + self._style.topBottomMargin;
      var winHeight = jQuery(window).height();
      /*if (jQuery.browser.msie || jQuery.browser.mozilla)
        scrolTop = document.body.scrollTop;
      else*/
      var scrolTop = document.documentElement.scrollTop;
      var remainHeight = winHeight - pos.top + scrolTop;
//      if (remainHeight < subBoxHeight)
//        pos.top = pos.top - (subBoxHeight - remainHeight);
      if (subBoxHeight > winHeight)
        pos.top = scrolTop;
      var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled "
          : "";
      var item = {
        id : parItem.id,
        name : parItem.name,
        parObj : null
      };
      var html = [];
      html.push("<ol >");
      for ( var i = 0; i < parItem.subItems.length; i++) {
        var subItem = parItem.subItems[i];
        subItem.parObj = item;

        if (ref.getElementsByTagName("input")[0].checked == true
            || self._curItems.has(subItem)) {
          html.push("<li id=Q" + i + " class=\"layon\"  >");
        } else {
          html.push("<li id=Q" + i + " class=\"nonelay\"  >");
        }
        html.push("<a href=\"javascript:void(0);\"><label for=\"ffcbx");
        html.push(subItem.id);
        html.push("\"><input id=\"ffcbx");
        html.push(subItem.id);
        html.push("\" type=\"checkbox\" value=\"");
        html.push(subItem.id);
        html.push("@");
        html.push(subItem.name);
        html.push("\"");
        html.push(parentChecked);
        if (self._curItems.has(subItem))
          html.push(" checked");
        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
        html.push(parItem.id);
        html.push(", name: '");
        html.push(parItem.name);
        html.push("', parObj: " + fourthIndex + ",pparObj: " + thirdIndex
            + ",ppparObj: " + secondIndex + ",pppparObj: " + index + ",tid:'Q"
            + i + "'}");
        html.push(",'','5')\" />");
        html.push(addSpan(subItem.name));
        html.push("</label></a></li>");
      }
      html.push("</ol>");
      var subBox = jQuery("#fifthBox");
      for ( var i = 0; i < self._types.length; i++) {
        subItems.removeClass(self._style.className.levelThree1[self._types[i]]);
        subItems.removeClass(self._style.className.levelThree2[self._types[i]]);
      }
      var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelThree2[self._type]
          : self._style.className.levelThree1[self._type];
      if (self._type == "loc") {
        if (parItem.id == "8000" || parItem.id == "15000") {
          levelTwo = self._style.className.levelThree1[self._type];
        }
      }
      self._lastPopupIndex = fourthIndex + 50;
      jQuery("#fifthItems").hover(
          function(e) {
            // alert(self.thirdHoverIn());
            // debugger;
            if (self._hideTimer)
              clearTimeout(self._hideTimer);
            if (self._hideThirdTimer)
              clearTimeout(self._hideThirdTimer);
            if (self._hideFourthTimer)
              clearTimeout(self._hideFourthTimer);
            if (self._hideFifthTimer)
              clearTimeout(self._hideFifthTimer);

            self.showFifthItems(index, secondIndex, thirdIndex, fourthIndex,
                ref, true, e);
          }, function(e) {
            self.hideFifthItems();
          });

      subBox.html(html.join(""));
      subItems.css("display", "block");
      subItems.addClass(levelTwo).css(pos).show();
      // debugger;
    }
    ;
    // loadSubItems end
  },
  hideThirdItems : function(ref) {
    jQuery(ref).removeClass("layshow");

    if (this._showTimer)
      clearTimeout(this._showTimer);
    if (this._hideTimer)
      clearTimeout(this._hideTimer);
    if (this._showThirdTimer)
      clearTimeout(this._showThirdTimer);
    if (this._hideThirdTimer)
      clearTimeout(this._hideThirdTimer);

    this._hideThirdTimer = setTimeout(function() {
      jQuery("#thirdItems").hide();
    }, 100);
  },
  hideFourthItems : function(ref) {
    jQuery(ref).removeClass("layshow");

    if (this._showTimer)
      clearTimeout(this._showTimer);
    if (this._hideTimer)
      clearTimeout(this._hideTimer);
    if (this._showThirdTimer)
      clearTimeout(this._showThirdTimer);
    if (this._hideThirdTimer)
      clearTimeout(this._hideThirdTimer);
    if (this._showFourthTimer)
      clearTimeout(this._showFourthTimer);
    if (this._hideFourthTimer)
      clearTimeout(this._hideFourthTimer);

    this._hideFourthTimer = setTimeout(function() {
      jQuery("#fourthItems").hide();
    }, 100);
  },
  hideFifthItems : function(ref) {
    jQuery(ref).removeClass("layshow");

    if (this._showTimer)
      clearTimeout(this._showTimer);
    if (this._hideTimer)
      clearTimeout(this._hideTimer);
    if (this._showThirdTimer)
      clearTimeout(this._showThirdTimer);
    if (this._hideThirdTimer)
      clearTimeout(this._hideThirdTimer);
    if (this._showFourthTimer)
      clearTimeout(this._showFourthTimer);
    if (this._hideFourthTimer)
      clearTimeout(this._hideFourthTimer);
    if (this._showFifthTimer)
      clearTimeout(this._showFifthTimer);
    if (this._hideFifthTimer)
      clearTimeout(this._hideFifthTimer);

    this._hideFifthTimer = setTimeout(function() {
      jQuery("#fifthItems").hide();
    }, 100);
  },
  _showTimer : null,
  _hideTimer : null,
  _showThirdTimer : null,
  _hideThirdTimer : null,
  _showFourthTimer : null,
  _hideFourthTimer : null,
  _showFifthTimer : null,
  _hideFifthTimer : null,
  _lastPopupIndex : null,
  _box : null,
  _subbox : null,
  _ref : null,
  _type : null,
  _types : [ "cat", "ind", "ind2", "ind3", "ind4", "ind5", "ind6", "loc",
      "comptype" ],
  _maxSize : 15,
  _checkType : "checkbox",

  // _curItems：在弹出层显示时，用户增加或减少的选项都在这个数组里，当用户清空时，也是清空此数组，
  // 当点击确定时，将此数组中的选项复制到_selItems中对应的数组做持久保存，然后清空此数组
  // 示例：[{id:xxx, name:xxxx, parObj:null/object ref, isParent:true/false},
  // .....]
  _curItems : [],
  _ql_ids : [],
  _selItems : {
    cat : [],
    loc : [],
    ind : [],
    ind2 : [],
    ind3 : [],
    ind4 : [],
    ind5 : [],
    ind6 : [],
    comptype : []
  },

  _gtMaxLimit : "对不起,您的已选项已经达到了15项.请减少已选项,再继续选择",
  _gtYourSelected : {
    cat : "您选择的职位类别是：",
    ind : "选择一个以上题库将按三级学科查询",
    ind2 : "设置题量和分数，每题至少1分",
    ind3 : "",
    ind4 : "",
    ind6 : "",
    loc : "您选择的内容是",
    ind5 : "设置题量和分数，每题至少1分",
    comptype : "您选择的公司性质是："
  },
  _gtPopupSelectorHeader : {
    cat : "职位类别",
    ind : "请选择题库",
    ind2 : "请选择题型",
  //  ind3 : "请选择副知识点",
    ind4 : "请选择认知水平",
    loc : "请选择内容",
    ind5 : "请选择题型",
    ind6 : "请选择适用级别",
    comptype : "公司性质"
  },
  _oneColumnLimit : {
    cat : 12,
    loc : 11
  }, // 二级类别显示成一列的最大条目数，超过这个数就要显示成两列
  _delay : 10,
  _style : {
    className : {
      levelOne : {
        "cat" : "lay_wl",
        "loc" : "lay_wls",
        "ind" : "lay_wll",
        "ind2" : "lay_wll",
        "ind3" : "lay_wll",
        "ind4" : "lay_wll",
        "ind5" : "lay_wll",
        "ind6" : "lay_wll",
        "comptype" : "lay_wms"
      },
      // 二级类别一列显示的样式
      levelTwo1 : {
        "cat" : "lay_wm",
        "loc" : "lay_ws lm",
        "ind" : "",
        "ind2" : "",
        "ind3" : "",
        "ind4" : "",
        "ind5" : "",
        "ind6" : "",
        "comptype" : ""
      },
      // 二级类别两列显示的样式
      levelTwo2 : {
        "cat" : "lay_wl2",
        "loc" : "lay_ws lm",
        "ind" : "",
        "ind2" : "",
        "ind3" : "",
        "ind4" : "",
        "ind5" : "",
        "ind6" : "",
        "comptype" : ""
      },
      // 二级类别一列显示的样式
      levelThree1 : {
        "cat" : "lay_wm",
        "loc" : "lay_ws",
        "ind" : "",
        "ind2" : "",
        "ind3" : "",
        "ind4" : "",
        "ind5" : "",
        "ind6" : "",
        "comptype" : ""
      },
      // 3级类别两列显示的样式
      levelThree2 : {
        "cat" : "lay_wl2",
        "loc" : "lay_ws",
        "ind" : "",
        "ind2" : "",
        "ind3" : "",
        "ind4" : "",
        "ind5" : "",
        "ind6" : "",
        "comptype" : ""
      }
    },
    left : 0,
    top : 0,
    width : {
      "cat" : 594,
      "loc" : 466,
      "ind" : 346,
      "ind2" : 346,
      "ind3" : 346,
      "ind4" : 346,
      "ind5" : 346,
      "ind6" : 346,
      "comptype" : 360
    },
    height : {
      "cat" : 517,
      "loc" : 500,
      "ind" : 450,
      "ind2" : 450,
      "ind3" : 450,
      "ind4" : 450,
      "ind5" : 450,
      "ind6" : 450,
      "comptype" : 173
    },
    lineHeight : 20,
    topBottomMargin : 17,
    offset : {
      levelOne : {
        X : 0,
        Y : 20,
        "ind" : {
          X : 100,
          Y : 20
        },
        "ind2" : {
          X : 100,
          Y : 20
        },
        "ind3" : {
          X : 100,
          Y : 20
        },
        "ind4" : {
          X : 100,
          Y : 20
        },
        "ind5" : {
          X : 100,
          Y : 20
        },
        "ind6" : {
          X : 100,
          Y : 20
        },
        "loc" : {
          X : 250,
          Y : 20
        }
      },
      levelTwo : {
        "ind" : {
          X : 104,
          Y : 0
        },
        "ind2" : {
          X : 104,
          Y : 0
        },
        "ind3" : {
          X : 104,
          Y : 0
        },
        "ind4" : {
          X : 104,
          Y : 0
        },
        "ind5" : {
          X : 104,
          Y : 0
        },
        "ind6" : {
          X : 104,
          Y : 0
        },
        "loc" : {
          X : 190,
          Y : 10
        }
      }
    }
  },
  allItems : {
    cat : function() {
      return getCat();
    },
    ind : function() {
      return getInd();
    },
    ind2 : function() {
      return getInd2();
    },
    ind5 : function() {
      return getInd5();
    },
    ind3 : function() {
      return getInd4('7');
    },
    ind4 : function() {
      return getInd4('8');
    },
    ind6 : function() {
      return getInd4('9');
    },
    loc : function() {
      return getLoc();
    },
    comptype : function() {
      return getCompanyType();
    }
  }
};
jQuery(function() {
  jQuery("#txtCat").click(function() {
    jQuery("#p").hide();
    PopupSelector.popup("cat", this);
  });
  /*jQuery("#txtInd3").click(function() {
    jQuery("#p").hide();
    PopupSelector.popup("ind3", this);
  });*/// 副知识点
  jQuery("#txtInd4").click(function() {
    jQuery("#btnCheckAll").show();
    jQuery("#p").hide();
    PopupSelector.popup("ind4", this);
  });// 认知水平
  jQuery("#txtInd6").click(function() {
    jQuery("#btnCheckAll").show();
    jQuery("#p").hide();
    PopupSelector.popup("ind6", this);
  });// 适用级别
  jQuery("#txtIndDrop").click(function() {
    if ($("#questCommonTypeIds").val() == '') {
      jQuery("#btnCheckAll").show();
      jQuery("#p").hide();
      PopupSelector.popup("ind", jQuery("#txtInd")[0]);
    } else {
      if (confirm("您已选择了题库，是否要重新选择？")) {
        PopupSelector._selItems["loc"].clear();
        clearQuesContent();
        PopupSelector._selItems["ind"].clear();
        clearTypes();
        jQuery("#btnCheckAll").show();
        jQuery("#p").hide();
        PopupSelector.popup("ind", jQuery("#txtInd")[0]);
      } else {
        return;
      }
    }
  });

  jQuery("#txtIndDrop_hand").click(function() {
    jQuery("#btnCheckAll").show();
    jQuery("#p").hide();
    PopupSelector.popup("ind", jQuery("#txtInd")[0]);
  });

  jQuery("#txtIndDrop6_hand").click(function() {
    jQuery("#btnCheckAll").show();
    jQuery("#p").hide();
    PopupSelector.popup("ind6", jQuery("#txtInd")[0]);
  });
  jQuery("#txtIndDrop2").click(function() {
    if ($("#questCommonTypeIds").val() == "") {
      alert("请选择题库！");
      $("#txtIndDrop").click();
      return;
    }

    if ($("#questionStudyIds").val() == "") {
      alert("请选择内容！");
      $("#txtLocDrop").click();
      return;
    }

    if ($("#questionLabelIds").val() == '') {
      jQuery("#btnCheckAll").hide();
      jQuery("#p").hide();
      jQuery("#mask").height(jQuery(document).height()).show();
      PopupSelector.popup("ind5", jQuery("#txtInd2")[0]);
    } else {
      if (confirm("您已设置了题型题量，是否要重新设置？")) {
        //PopupSelector._selItems["loc"].clear();
        //clearQuesContent();
        clearStudysContent();
        jQuery("#btnCheckAll").hide();
        jQuery("#p").hide();
        jQuery("#mask").height(jQuery(document).height()).show();
        PopupSelector.popup("ind5", jQuery("#txtInd2")[0]);
      } else {
        return;
      }
    }
  });
  jQuery("#txtIndDrop4").click(function() {
    if ($("#questionCognizeIds").val() == '') {
      jQuery("#btnCheckAll").show();
      jQuery("#p").hide();
      jQuery("#mask").height(jQuery(document).height()).show();
      PopupSelector.popup("ind4", jQuery("#txtInd4")[0]);
    } else {
      if (confirm("您已设置了难度，是否要重新设置？")) {
        PopupSelector._selItems["loc"].clear();
        $("#txtInd2").val("");
        $("#questionLabelIds").val("");
        clearQuesContent();
        jQuery("#btnCheckAll").hide();
        jQuery("#p").hide();
        jQuery("#mask").height(jQuery(document).height()).show();
        PopupSelector.popup("ind4", jQuery("#txtInd4")[0]);
      } else {
        return;
      }
    }
  });
  jQuery("#txtIndDrop6").click(function() {
    if ($("#questionTitleIds").val() == '') {
      jQuery("#btnCheckAll").show();
      //jQuery("#btnCheckAll").hide();
      jQuery("#p").hide();
      jQuery("#mask").height(jQuery(document).height()).show();
      PopupSelector.popup("ind6", jQuery("#txtInd6")[0]);
    } else {
      if (confirm("您已设置了适用级别，是否要重新设置？")) {
    	jQuery("#btnCheckAll").show();
    	jQuery("#p").hide();
        PopupSelector._selItems["loc"].clear();
        $("#txtInd2").val("");
        $("#questionLabelIds").val("");
        clearQuesContent();
        //jQuery("#btnCheckAll").hide();
        jQuery("#mask").height(jQuery(document).height()).show();
        PopupSelector.popup("ind6", jQuery("#txtInd6")[0]);
      } else {
        return;
      }
    }
  });

  jQuery("#txtLocDrop").click(function() {
    /*if ($("#questCommonTypeIds").val() == '') {
      alert("请选择题库！");
      $("#txtIndDrop").click();
      return;
    }
    if ($("#questionLabelIds").val() == '') {
      alert("请选择题型设置题量！");
      $("#txtIndDrop2").click();
      return;
    }*/

    if ($("#questionStudyIds").val() == '') {
      jQuery("#btnCheckAll").hide();
      jQuery("#p").show();
      PopupSelector.popup("loc", jQuery("#txtLoccc")[0]);
    } else {
      if (confirm("您已设置了内容，是否要重新设置？")) {
        PopupSelector._selItems["loc"].clear();
        $("#txtInd2").val("");
        clearQuesContent();
        jQuery("#btnCheckAll").hide();
        jQuery("#p").show();
        $("#questionLabelIds").val("");
        PopupSelector.popup("loc", jQuery("#txtLoccc")[0]);
      } else {
        return;
      }
    }
  });
  jQuery("#txtLocDrop_hand").click(function() {
    jQuery("#btnCheckAll").hide();
    jQuery("#p").show();
    PopupSelector.popup("loc", jQuery("#txtLoc")[0]);
  });

  jQuery("#txtCatDrop").click(function() {
    PopupSelector.popup("cat", jQuery("#txtCat")[0]);
  });

  jQuery("#1txtIndDrop").click(function() {
    if ($("#questCommonTypeIds").val() == '') {
      jQuery("#btnCheckAll").show();
      jQuery("#p").hide();
      PopupSelector.popup("ind", jQuery("#1txtInd")[0]);
    } else {
      if (confirm("您已选择了题库，是否要重新选择？")) {
        PopupSelector._selItems["ind5"].clear();
        $("#1txtInd2").val("");
        jQuery("#btnCheckAll").show();
        jQuery("#p").hide();
        $("#questionLabelIds").val("");
        PopupSelector.popup("ind", jQuery("#1txtInd")[0]);
      } else {
        return;
      }
    }
  });

  jQuery("#1txtLocDrop").click(function() {
    if ($("#questionStudyIds").val() == '') {
      jQuery("#btnCheckAll").hide();
      jQuery("#p").show();
      PopupSelector.popup("loc", jQuery("#txtLocc")[0]);
    } else {
      if (confirm("您已选择了内容，是否要重新选择？")) {
        PopupSelector._selItems["ind5"].clear();
        $("#1txtInd2").val("");
        jQuery("#btnCheckAll").hide();
        jQuery("#p").show();
        PopupSelector.popup("loc", jQuery("#txtLocc")[0]);
      } else {
        return;
      }
    }
  });
  jQuery("#1txtIndDrop4").click(function() {
    if ($("#questionCognizeIds").val() == '') {
      jQuery("#btnCheckAll").show();
      jQuery("#p").hide();
      PopupSelector.popup("ind4", jQuery("#1txtInd4")[0]);
    } else {
      if (confirm("您已选择了难度，是否要重新选择？")) {
        PopupSelector._selItems["ind5"].clear();
        $("#txtInd2").val("");
        $("#questionLabelIds").val("");
        jQuery("#btnCheckAll").show();
        jQuery("#p").hide();
        PopupSelector.popup("ind4", jQuery("#1txtInd4")[0]);
      } else {
        return;
      }
    }
  });
  jQuery("#1txtIndDrop6").click(function() {
    if ($("#questionTitleIds").val() == '') {
      jQuery("#btnCheckAll").show();
      jQuery("#p").hide();
      PopupSelector.popup("ind6", jQuery("#1txtInd6")[0]);
    } else {
      if (confirm("您已选择了适用级别，是否要重新选择？")) {
        PopupSelector._selItems["ind5"].clear();
        $("#1txtInd2").val("");
        jQuery("#btnCheckAll").show();
        jQuery("#p").hide();
        PopupSelector.popup("ind6", jQuery("#1txtInd6")[0]);
      } else {
        return;
      }
    }
  });

  jQuery("#1txtIndDrop2").click(function() {
    if ($("#questCommonTypeIds").val() == "") {
      alert("请选择题库！");
      $("#1txtIndDrop").click();
      return;
    }
    if ($("#questionStudyIds").val() == "") {
      alert("请选择内容！");
      $("#1txtLocDrop").click();
      return;
    }
    jQuery("#btnCheckAll").hide();
    jQuery("#p").hide();
    jQuery("#mask").height(jQuery(document).height()).show();
    PopupSelector.popup("ind5", jQuery("#1txtInd2")[0]);
  });

  popupLayer = $(".sech_layb");
  jQuery("#lnkEmpty").click(function() {
  	jQuery(jQuery("input[id^=fen]")).each(function() {
  		$(this).val(1);
  	});
  	jQuery("#questionNum").val("");
    jQuery("#paperScore").val("");
  	PopupSelector.empty();
  });
  jQuery("#btnCheckAll").click(function() {
    PopupSelector.checkAll();
  });
  jQuery("#lnkCancel").click(function() {
    PopupSelector.cancel();
  });
  jQuery("#lnkOK").click(function() {
    PopupSelector.OK();
  });
  jQuery("#btnOk").click(function() {
    PopupSelector.OK();
  });
  jQuery("#btnOkLoc").click(function() {
    PopupSelector.OK();
  });
  jQuery("#imgClose").click(function() {
    PopupSelector.close();
  });

});

jQuery.extend(Array.prototype, {
  // 特殊方法，不适用于普通数组
  remove : function(item) {
    var array = this.clone();
    this.length = 0;
    for ( var i = 0; i < array.length; i++) {
      if (array[i].id != item)
        this.push(array[i]);
    }
    return this;
  },

  // 特殊方法，不适用于普通数组
  has : function(item) {
    for ( var i = 0; i < this.length; i++) {
      if (this[i] == item)
        return true;
      if (typeof item == "object" && this[i].id == item.id)
        return true;
    }
    return false;
  },
  // 特殊方法，找子类的父类
  GetFather : function(item) {
    for ( var i = 0; i < this.length; i++) {
      if (this[i].parObj == null)
        continue;
      if (this[i].parObj.id == item.id)
        return true;
      if (typeof item == "object" && this[i].id == item.id)
        return true;
    }
    return false;
  },
  parItems : getLoc(),
  GetFaFather : function(item) {

    for ( var i = 0; i < this.length; i++) {
      if (this[i].parObj == null)
        continue;
      if (this[i].parObj.parObj == null)
        continue;
      var parItem = this.parItems[parseInt(this[i].parObj.parObj)];
      if (parItem != null) {
        if (parItem.id == item.id)
          return true;
      } else {
        if (typeof this[i].parObj.parObj == "object"
            && this[i].parObj.parObj.id == item.id)
          return true;
      }
    }
    return false;
  },

  // 特殊方法，不适用于普通数组
  findById : function(id) {
    for ( var i = 0; i < this.length; i++) {
      if (this[i].id == id) {
        this[i].parObj = null;
        return this[i];
      }
      if (this[i].subItems != null && this[i].subItems != "undefined") {
        for ( var j = 0; j < this[i].subItems.length; j++) {
          if (this[i].subItems[j].id == id) {
            this[i].subItems[j].parObj = this[i];
            return this[i].subItems[j];
          }
          if (this[i].subItems[j].subItems != null
              && this[i].subItems[j].subItems != "undefined") {
            for ( var k = 0; k < this[i].subItems[j].subItems.length; k++) {
              if (this[i].subItems[j].subItems[k].id == id) {
                this[i].subItems[j].subItems[k].parObj = this[i].subItems[j];
                this[i].subItems[j].subItems[k].parObj.parObj = this[i];
                return this[i].subItems[j].subItems[k];
              }
            }
          }
        }
      }
    }
    return null;
  },
  // 特殊方法，不适用于普通数组，重载方法，第二个参数用来判断是不是只遍历一级节点
  findById : function(id, isFromRoot) {
    for ( var i = 0; i < this.length; i++) {
      if (this[i].id == id) {
        this[i].parObj = null;
        return this[i];
      }
      if (isFromRoot)
        continue;
      if (this[i].subItems != null && this[i].subItems != "undefined") {
        for ( var j = 0; j < this[i].subItems.length; j++) {
          if (this[i].subItems[j].id == id) {
            this[i].subItems[j].parObj = this[i];
            return this[i].subItems[j];
          }
          if (this[i].subItems[j].subItems != null
              && this[i].subItems[j].subItems != "undefined") {
            for ( var k = 0; k < this[i].subItems[j].subItems.length; k++) {
              if (this[i].subItems[j].subItems[k].id == id) {
                this[i].subItems[j].subItems[k].parObj = this[i].subItems[j];
                this[i].subItems[j].subItems[k].parObj.parObj = this[i];
                return this[i].subItems[j].subItems[k];
              }
            }
          }
        }
      }
    }
    return null;
  },

  clear : function() {
    this.length = 0;
    return this;
  },

  clone : function() {
    return [].concat(this);
  }
});

function dbcToSbc(str) {
  return str.replace(/（/g, "(").replace(/）/g, ")");
}
function addSpan(str) {
  str = dbcToSbc(str);
  if (str.length > 9)
    str = "<span>" + str + "</span>";
  return str;
}

// 字典资源
// ********************************************************************

function getInd() {
  var rd;
  $.ajax({
    type : "post",
    url : "manage/commonType/findUserCommonTypes.do",// ?method=quesTypes",
    dataType : "json",
    async : false,
    cache : true,
    success : function(rdata) {
      rd = rdata;
    },
    error : function() {
      alert("error");
    }
  });
  return rd;
}
function getInd2() {
  var rd;
  $.ajax({
    type : "post",
    data : "questCommonTypeIds=" + $("#questCommonTypeIds").val()
        + "&questionCognizeIds=" + $("#questionCognizeIds").val()
        + "&questionTitleIds=" + $("#questionTitleIds").val(),
    url : "manage/examQuestion/getQuestionList.do?flag=true",//flag=true",
    dataType : "json",
    async : false,
    cache : true,
    success : function(rdata) {
      rd = rdata;
    },
    error : function() {
      alert("error");
    }
  });
  return rd;
}

//查询试题列表
function getInd5() {
  var a;
  $.ajax({
    type : "post",
    data : "questCommonTypeIds=" + $("#questCommonTypeIds").val()
        + "&questionStudyIds=" + $("#questionStudyIds").val()
        + "&questionCognizeIds=" + $("#questionCognizeIds").val()
        + "&questionTitleIds=" + $("#questionTitleIds").val(),
    url : "manage/examQuestion/getQuestionList.do",// ?method=quesLable",
    dataType : "json",
    async : false,
    cache : true,
    success : function(b) {
      a = b;
    }
    //暂时去掉
    /*,
    error : function() {
      alert("撒旦法撒旦法");
      alert("error");
    }*/
  });
  return a;
}

function getInd4(propType) {
  var rd;
  $.ajax({
    type : "post",
    url : "manage/examPropVal/findExamPropValList.do",// ?method=quesPorp",
    data : "propType=" + propType,
    dataType : "json",
    async : false,
    cache : true,
    success : function(rdata) {
      rd = rdata;
    },
    error : function() {
      alert("error");
    }
  });
  return rd;
}

function countQues(flag) {
  var rd;
  $.ajax({
        type : "post",
        data : "questCommonTypeIds=" + $("#questCommonTypeIds").val()
            + "&questionCognizeIds=" + $("#questionCognizeIds").val()
            + "&questionTitleIds=" + $("#questionTitleIds").val()
            + "&questionStudyIds=" + $("#questionStudyIds").val()
            + "&questionLabelIds=" + $("#questionLabelIds").val() + "&flag="
            + flag,
        url : "manage/examQuestion/getQuestionCount.do",
        async : false,
        cache : true,
        success : function(rdata) {
          rd = rdata;
        },
        error : function() {
          alert("error");
          return;
        }
      });
  return rd;
}

function writeCount(data) {
  var object = eval('(' + data + ')');
  $(".span_loc").each(function() {
    var idstr = this.id;
    var index = idstr.indexOf('loc_');
    var ids = idstr.substr(index + 4, idstr.length);
    $(this).text(object[0][ids]);

  });
}

function clearTypes() {
  $("#questCommonTypeIds").val("");
  $("#txtInd").val("");
}
function clearQuesNum() {
  $("#questionLabelIds").val("");
  $(".tr_ind2").each(function() {
    $(this).detach();
  });
}
function clearQuesContent() {
  $("#questionStudyIds").val("");
  $("#questionStudyNameIds").val("");
  $(".tr_loc").each(function() {
    $(this).detach();
  });
}
function clearStudysContent() {
  $(".tr_loc").each(function() {
    $(this).detach();
  });
}
function stopPropagation(e) {
  var evt = getEvent();
  if (evt.stopPropagation) { // W3C阻止冒泡方法
    evt.stopPropagation();
  } else {
    evt.cancelBubble = true; // IE阻止冒泡方法
  }
}

function getEvent() { // 同时兼容ie和ff的写法
  if (document.all)
    return window.event;
  func = getEvent.caller;
  while (func != null) {
    var arg0 = func.arguments[0];
    if (arg0) {
      if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
          || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
        return arg0;
      }
    }
    func = func.caller;
  }
  return null;
}

function checkTotalQues() {
  var tot_ti = 0;
  var tot_fen = 0;
  $(".shu").each(function(i) {
    if ($(this).val() > 0) {

      var id = $(this).prev().val();
      var name = $(this).next().val();

      var shu = jQuery("#shu" + id).val();
      var fen = jQuery("#fen" + id).val();

      tot_ti = tot_ti + eval(shu);
      tot_fen = tot_fen + eval(eval(fen) * eval(shu));
    }
  });
  jQuery("#totalQues").val(tot_ti);
  jQuery("#totalQuesSoc").val(tot_fen);
}

function checkTotalbfb() {
  var tot_bfb = 0;
  $(".input_loc").each(function() {
    var val = $(this).val();
    if (val > 0) {
      tot_bfb = tot_bfb + eval(val);
    }
  });
  jQuery("#totalbfb").val(tot_bfb);
}
