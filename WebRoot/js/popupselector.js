
jQuery.extend(Array.prototype, {
    //特殊方法，不适用于普通数组
    remove: function(item) {
        var array = this.clone();
        this.length = 0;
        for (var i = 0; i < array.length; i++) {
            if (array[i].id != item) this.push(array[i]);
        }
        return this;
    },

    //特殊方法，不适用于普通数组
    has: function(item) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == item) return true;
            if (typeof item == "object" && this[i].id == item.id) return true;
        }
        return false;
    },
    //特殊方法，找子类的父类
    GetFather: function(item) {
        for (var i = 0; i < this.length; i++) {
            if (this[i].parObj == null) continue;
            if (this[i].parObj.id == item.id) return true;
            if (typeof item == "object" && this[i].id == item.id) return true;
        }
        return false;
    },
    parItems: getLoc(), 
    //特殊方法，找子类的父父类
    GetFaFather: function(item) {

        for (var i = 0; i < this.length; i++) {
            if (this[i].parObj == null) continue;
            if (this[i].parObj.parObj == null) continue;
            var parItem = this.parItems[parseInt(this[i].parObj.parObj)];
            if (parItem != null) {
                if (parItem.id == item.id)
                    return true;
            } else {
                if (typeof this[i].parObj.parObj == "object" && this[i].parObj.parObj.id == item.id)
                    return true;
            }
        }
        return false;
    },
    //特殊方法，找子类的父父类
    GetFaFaFather: function(item) {

        for (var i = 0; i < this.length; i++) {
            if (this[i].parObj == null) continue;
            if (this[i].parObj.parObj == null) continue;
            if (this[i].parObj.parObj.parObj == null) continue;
            var parItem = this.parItems[parseInt(this[i].parObj.parObj.parObj)];
            if (parItem != null) {
                if (parItem.id == item.id)
                    return true;
            } else {
                if (typeof this[i].parObj.parObj.parObj == "object" && this[i].parObj.parObj.parObj.id == item.id)
                    return true;
            }
        }
        return false;
    },

    //特殊方法，不适用于普通数组
    findById: function(id) {
        for (var i = 0; i < this.length; i++) {
            if (this[i].id == id) {
                this[i].parObj = null;
                return this[i];
            }
            if (this[i].subItems != null && this[i].subItems != "undefined") {
                for (var j = 0; j < this[i].subItems.length; j++) {
                    if (this[i].subItems[j].id == id) {
                        this[i].subItems[j].parObj = this[i];
                        return this[i].subItems[j];
                    }
                    if (this[i].subItems[j].subItems != null && this[i].subItems[j].subItems != "undefined") {
                        for (var k = 0; k < this[i].subItems[j].subItems.length; k++) {
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
    //特殊方法，不适用于普通数组，重载方法，第二个参数用来判断是不是只遍历一级节点
    findById: function(id, isFromRoot) {
        for (var i = 0; i < this.length; i++) {
            if (this[i].id == id) {
                this[i].parObj = null;
                return this[i];
            }
            if (isFromRoot)
                continue;
            if (this[i].subItems != null && this[i].subItems != "undefined") {
                for (var j = 0; j < this[i].subItems.length; j++) {
                    if (this[i].subItems[j].id == id) {
                        this[i].subItems[j].parObj = this[i];
                        return this[i].subItems[j];
                    }
                    if (this[i].subItems[j].subItems != null && this[i].subItems[j].subItems != "undefined") {
                        for (var k = 0; k < this[i].subItems[j].subItems.length; k++) {
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

    clear: function() {
        this.length = 0;
        return this;
    },

    clone: function() {
        return [].concat(this);
    }
});

var requestFromStr = {
    QueryString: function(Str, val) {
        var uri = Str.substring(0, 1) == "?" ? Str : "?" + Str;
        var re = new RegExp("[&?]" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 2)) : "");
    }
};

function locConverter(locIDs) {
    var arrLoc = locIDs.split(",");
    var temp = [];
    for (var i = 0; i < arrLoc.length; i++) {
        temp.push(ConverterLoc(arrLoc[i]));
    }
    return temp;
}

function traversal(id, items) {
    var p = items.findById(id);
    if (p != null) {
        return { id: p.id, name: p.name, parObj: null };
    }
    else {
        var subItems, c;
        for (var i = 0; i < items.length; i++) {
            p = items[i];
            c = p.subItems.findById(id);
            if (c != null) return { id: c.id, name: c.name, parObj: { id: p.id, name: p.name, parObj: null} };
        }
    }
    return null;
}

function dbcToSbc(str) {
    return str.replace(/（/g, "(").replace(/）/g, ")");
}
function addSpan(str) {
    str = dbcToSbc(str);
    if (str.length > 9) str = "<span>" + str + "</span>";
    return str;
}
var PopupSelector = {
    loadSelected: function(url) {
        var temp = [];
        var selCatParentIDs = requestFromStr.QueryString(url, "occParentIDList");
        if (selCatParentIDs != "") {
            if (selCatParentIDs.indexOf(",") > 0) selCatParentIDs = selCatParentIDs.split(",");
            else {
                selCatParentIDs = selCatParentIDs.replace(/%2C/g, "%2c");
                selCatParentIDs = selCatParentIDs.split("%2c");
            }
        }
        var selCatIDs = requestFromStr.QueryString(url, "occIDList");
        if (selCatIDs.indexOf(",") > 0) selCatIDs = selCatIDs.split(",");
        else {
            selCatIDs = selCatIDs.replace(/%2C/g, "%2c");
            selCatIDs = selCatIDs.split("%2c");
        }
        var addCatID = requestFromStr.QueryString(url, "addOccIDList");
        if (addCatID != "") {
            selCatIDs.clear();
            selCatIDs[0] = addCatID;
        }
        this._selItems["cat"].clear();
        var allCats = getCat();
        //如果带有addOccIDList，则忽略occIDList和occParentIDList
        if (addCatID != "") {
            var catItem;
            for (var i = 0; i < selCatIDs.length; i++) {
                catItem = traversal(selCatIDs[i], allCats);
                if (catItem != null) {
                    this._selItems["cat"].push(catItem);
                    temp.push(catItem.name);
                }
            }
        }
        if (addCatID == "" && selCatParentIDs == "") {
            var cateIdList = new Array();
            for (var i = 0; i < selCatIDs.length; i++) {
                var cateItem = allCats.findById(selCatIDs[i]);
                if (cateItem != null) {
                    if (cateItem.parObj == null)
                        cateIdList.push(cateItem.id);
                    else
                        cateIdList.push(cateItem.parObj.id);
                }
            }
            selCatParentIDs = cateIdList;
        }
     
        //通常状况下occIDList和occParentIDList都有的状况
        if (selCatIDs.length > 0 && selCatIDs[0] != "" && selCatParentIDs != "" && addCatID == "") {
            for (var i = 0; i < selCatParentIDs.length; i++) {
                if (selCatParentIDs[i] == selCatIDs[i]) {
                    if (selCatIDs[i] != 255) {
                        var catItem = allCats.findById(selCatIDs[i]);
                        this._selItems["cat"].push({ id: catItem.id, name: catItem.name, parObj: null });
                        temp.push(catItem.name);
                    }
                }
                else {
                    var parCatItem = allCats.findById(selCatParentIDs[i]);
                    var subCatItem = parCatItem.subItems.findById(selCatIDs[i]);
                    this._selItems["cat"].push({ id: subCatItem.id, name: subCatItem.name, parObj: { id: parCatItem.id, name: parCatItem.name, parObj: null} });
                    temp.push(subCatItem.name);
                }
            }
        }

        jQuery("#txtCat").val(temp.join("+"));
        this.InitClass(temp, "cat");
        temp.length = 0;

        var selLocIDs = requestFromStr.QueryString(url, "myLocIDList");
        if (selLocIDs.indexOf(",") > 0) selLocIDs = selLocIDs.split(",");
        else {
            selLocIDs = selLocIDs.replace(/%2C/g, "%2c");
            selLocIDs = selLocIDs.split("%2c");
        }
        // selLocIDs = locConverter(selLocIDs.replace(/%2C/g, "%2c").replace(/%2c/g, ","));
        var selLocParentIDs = requestFromStr.QueryString(url, "myLocParentIDList");
        if (selLocParentIDs != "") {
            if (selLocParentIDs.indexOf(",") > 0) selLocParentIDs = selLocParentIDs.split(",");
            else {
                selLocParentIDs = selLocParentIDs.replace(/%2C/g, "%2c");
                selLocParentIDs = selLocParentIDs.split("%2c");
            }
            //selLocParentIDs = locConverter(selLocParentIDs);
        }
        //        if (selLocParentIDs.indexOf(",") > 0) selLocParentIDs = selLocParentIDs.split(",");
        //        else selLocParentIDs = selLocParentIDs.split("%2c");

        //var selLocIDs = requestFromStr.QueryString(url, "myLocIDList");
        //selLocIDs = locConverter(selLocIDs.replace(/%2C/g, "%2c").replace(/%2c/g, ","));
        //        if (selLocIDs.indexOf(",") > 0) selLocIDs = selLocIDs.split(",");
        //        else selLocIDs = selLocIDs.split("%2c");

        var addLocID = requestFromStr.QueryString(url, "addLocIDList");
        if (addLocID != "") {
            selLocIDs.clear();
            selLocIDs[0] = addLocID;
        }

        if (selLocParentIDs == "" && selLocIDs != "") {
            var arrayParentLocIds = new Array();
            for (var i = 0; i < selLocIDs.length; i++) {
                var item = this.allItems.loc().findById(selLocIDs[i]);
                if (item.parObj == null)
                    arrayParentLocIds.push(item.id);
                else
                    arrayParentLocIds.push(item.parObj.id);
            }
            selLocParentIDs = arrayParentLocIds;
        }
        // alert(document.referrer);
        //填充地区数据 
        this._selItems["loc"].clear();
        var allLocs = getLoc();
        //如果带有addLocIDList，则忽略myLocIDList和myLocParentIDList
        if (addLocID != "" || selLocParentIDs == "") {
            var locItem;

            for (var i = 0; i < selLocIDs.length; i++) {
                locItem = allLocs.findById(selLocIDs[i]);
                if (locItem != null) {
                    this._selItems["loc"].push(locItem);
                    if (locItem.parObj != null) {
                        if (locItem.parObj.parObj != null) {
                            temp.push(locItem.parObj.parObj.name + "-" + locItem.parObj.name + "-" + locItem.name);
                        }
                        else {
                            temp.push(locItem.parObj.name + "-" + locItem.name);
                        }
                    }
                    else {
                        temp.push(locItem.name);
                    }
                }
            }

        }

        //通常状况下myLocIDList和myLocParentIDList都有的状况
        if (selLocIDs.length > 0 && selLocIDs[0] != "" && selLocParentIDs != "" && addLocID == "") {
            for (var i = 0; i < selLocParentIDs.length; i++) {
                if (selLocParentIDs[i] == selLocIDs[i] || selLocParentIDs[i] == -1) {
                    if (selLocIDs[i] != 255) {
                        var locItem = allLocs.findById(selLocIDs[i]);
                        this._selItems["loc"].push({ id: locItem.id, name: locItem.name, parObj: null });
                        temp.push(locItem.name);
                    }

                }
                else {

                    var curLocItem = allLocs.findById(selLocParentIDs[i]);
                    if (curLocItem != null) {
                        var subLocItem = curLocItem.subItems.findById(selLocIDs[i]);
                        //this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: curLocItem.id, name: curLocItem.name, parObj: null} });
                        //temp.push(subLocItem.name)
                        var parItems = this.allItems["loc"]();
                        var location = '';
                        var curLocItem = allLocs.findById(selLocParentIDs[i]);
                        if (curLocItem != null && curLocItem.parObj != null) {
                            var parItem = parItems[parseInt(curLocItem.parObj)];
                            if (parItem == "undefined" || parItem == null)
                                parItem = curLocItem.parObj;
                            if (parItem.name == curLocItem.parObj.name) {
                                location = parItem.name + '-' + curLocItem.name + '-' + subLocItem.name;
                                this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: curLocItem.id, name: curLocItem.name, parObj: { id: parItem.id, name: parItem.name, parObj: null}} });
                            }
                        }
                        else {
                            location = curLocItem.name + '-' + subLocItem.name;
                            this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: curLocItem.id, name: curLocItem.name, parObj: null} });
                        }
                        temp.push(location);
                    }
                    //                        if (parLocItem.parObj == null)
                    //                            this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: parLocItem.id, name: parLocItem.name, parObj: null} });
                    //                        else
                    //                            this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: parLocItem.id, name: parLocItem.name, parObj: { id: parLocItem.parObj.id, name: parLocItem.parObj.name, parObj: null}} });
                    //                    }
                    //var subLocItem = parLocItem.subItems.findById(selLocIDs[i]);
                    //this._selItems["loc"].push({ id: subLocItem.id, name: subLocItem.name, parObj: { id: parLocItem.id, name: parLocItem.name, parObj: null} });
                    //temp.push(subLocItem.name);
                }
            }
        }

        jQuery("#txtLoc").val(temp.join("+"));
        this.InitClass(temp, "loc");
        temp.length = 0;
        var selIndIDs = requestFromStr.QueryString(url, "indIDList");
        if (selIndIDs.indexOf(",") > 0) selIndIDs = selIndIDs.split(",");
        else {
            selIndIDs = selIndIDs.replace(/%2C/g, "%2c");
            selIndIDs = selIndIDs.split("%2c");
        }
        //转换行业字典不一致的问题
        //selIndIDs = ConverterInd(selIndIDs.replace(/%2C/g, "%2c").replace(/%2c/g, ",")).split(",");
        //        if (selIndIDs.indexOf(",") > -1) selIndIDs = selIndIDs.split(",");
        //        else selIndIDs = selIndIDs.split("%2c");
        var addIndID = requestFromStr.QueryString(url, "addIndIDList");
        var allInds = getInd();
        var addIndItem = allInds.findById(addIndID);
        this._selItems["ind"].clear();
        if (addIndItem != null) {
            this._selItems["ind"].push({ id: addIndItem.id, name: addIndItem.name, parObj: null });
            temp.push(addIndItem.name);
        }

        //        if(selIndIDs[0]==255)
        //        {
        //             this._selItems["ind"].push({ id: 255, name: "不限", parObj: null });
        //             temp.push("不限");
        //        }

        if (selIndIDs.length > 0 && selIndIDs[0] != "" && addIndItem == null) {
            for (var i = 0; i < selIndIDs.length; i++) {
                if (selIndIDs[i] != 255) {
                    var indItem = allInds.findById(selIndIDs[i]);
                    if (indItem == null) continue;
                    this._selItems["ind"].push({ id: indItem.id, name: indItem.name, parObj: null });
                    temp.push(indItem.name);
                }
            }
        }
        jQuery("#txtInd").val(temp.join("+"));
        this.InitClass(temp, "ind");
        temp.length = 0;

        // 公司类别
        if (jQuery("#txtAdvanceCompType").length > 0) {
            var companyTypeID = requestFromStr.QueryString(url, "companyTypeIDList");
            if (companyTypeID != "") {
                if (companyTypeID.indexOf(",") > 0) companyTypeID = companyTypeID.split(",");
                else {
                    companyTypeID = companyTypeID.replace(/%2C/g, "%2c");
                    companyTypeID = companyTypeID.split("%2c");
                }
            }
            var allCompanyType = getCompanyType();
            if (companyTypeID.length > 0 && companyTypeID[0] != "") {
                for (var i = 0; i < companyTypeID.length; i++) {
                    if (companyTypeID[i] != 255) {
                        var companyTypeItem = allCompanyType.findById(companyTypeID[i]);
                        if (companyTypeItem == null) continue;
                        this._selItems["comptype"].push({ id: companyTypeItem.id, name: companyTypeItem.name, parObj: null });
                        temp.push(companyTypeItem.name);
                    }
                }
            }
            jQuery("#txtAdvanceCompType").val(temp.join("+"));
            temp.length = 0;
        }

    },
    RenderSelectedHTML: function(selectedItem, type) {
        var location = '';
    },
    InitClass: function(temp, type) {
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

        }
        else {
            if (type == "cat") {
                $("#txtCat").removeClass("inp_txt inp_txtsel inp_wl");
                $("#txtCat").addClass("inp_txt inp_txtsel inp_wl inp_cue gray");
                $("#txtCat").val("请选择职业类别")
            }
            if (type == "loc") {
                $("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm");
                $("#txtLoc").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
                $("#txtLoc").val("请选择试题属性")
            }
            if (type == "ind") {
                $("#txtInd").removeClass("inp_txt inp_txtsel inp_wm");
                $("#txtInd").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
                $("#txtInd").val("请选择行业类别");
            }
        }
    },
    popup: function(type, ref, selectdtids, tagPrefix, checkType) {
        this.AarearenderSelItemEleId = selectdtids;
        this.AareatagPrefix = tagPrefix;
        this.AarearenderSelItemDiv = ref;
        var selLocIDs;
        if (this._box == null) this._box = jQuery("#popupSelector");
        if (this._subBox == null) this._subBox = document.getElementById("subItems");
        this._ref = ref;
        this._type = type.toString().toLowerCase();
        //初始化菜单数据
        if (this.AarearenderSelItemEleId != null)
            selLocIDs = document.getElementById(this.AarearenderSelItemEleId).value.split(',');
        var allLocs = getLoc();
        this._curItems.length = 0;
        //        if (selLocIDs != null && selLocIDs.length > 0) {
        //            for (var i = 0; i < selLocIDs.length; i++) {
        //                if (selLocIDs[i] != 255) {
        //                    var locItem = allLocs.findById(selLocIDs[i]);
        //                    if (locItem == null) continue;
        //                    this._curItems.push(locItem);
        //                    //temp.push(indItem.name);
        //                }
        //            }
        //        }
        this._curItems = this._selItems[this._type].clone();
        if (arguments.length == 3) {
            this._checkType = checkType;
        }
        //        this._style.position = "absolute";
        //        this._style.zIndex = "10001";
        //        this._style.top = 100 + webPB.getScrollPos().top + "px";
        //        this._style.left = webPB.getWAndHOfWindow().w / 2 - 350 + "px";
        var offset = jQuery("#" + ref.id).offset();
        this._style.left = offset.left;
        this._style.top = offset.top;
        this.render();
    },

    render: function(event) {
        var pos = { top: (this._style.top + this._style.offset.levelOne.Y), left: this._style.left + this._style.offset.levelOne.X };
        if (this._type != "cat" && this._type != "comptype") {
            //            if (document.location.href.toLowerCase().indexOf("searchjob.chinahr.com") > -1)
            //                pos.left -= (this._style.width[this._type] - 384);
            if (this._type == "ind")
                pos.left = jQuery(jQuery("#txtCat")[0]).offset().left;
            else if (this._type == "loc") {
                //部分页面调整工作地点位置
                if (this.InSalaryPage) {
                    pos.left = this._style.left;
                }
                else if (this.InAdvanceSearchPage) {
                    pos.left = this._style.left;
                }
                else {
                    pos.left = this._style.offset.levelOne[this._type].X;
                }
            }

        }
        var scrolTop;
        if (jQuery.browser.msie && jQuery.browser.version == '6.0') scrolTop = document.body.scrollTop;
        else scrolTop = document.documentElement.scrollTop;
        //alert(scrolTop);
        //当前窗口对照显示参照位置可用的高度，如果可用高度小于弹出层的高度，则进行调整
        var remainHeight = jQuery(window).height() - pos.top + scrolTop;
        if (remainHeight < this._style.height[this._type]) pos.top = pos.top - (this._style.height[this._type] - remainHeight);
        //如果当前窗口高度不足以显示弹出层高度，则顶部对齐即可
        if (this._style.height[this._type] > jQuery(window).height()) pos.top = scrolTop;
        var pslayer = jQuery("#pslayer");
        for (var i = 0; i < this._types.length; i++) {
            pslayer.removeClass(this._style.className.levelOne[this._types[i]]);
        }
        pslayer.addClass(this._style.className.levelOne[this._type]);
        if (this.InSalaryPage) {
            this._gtYourSelected["ind"] = '请选择你的行业类别：';
        }

        jQuery("#selectingHeader").html(this._gtYourSelected[this._type]);


        jQuery("#psHeader").html(this._gtPopupSelectorHeader[this._type]);

        //this._curItems = this._selItems[this._type];
        var html = [];
        var popupLayer;
        var parItems = this.allItems[this._type]();
        var start = 0;
        var end = parItems.length;
        if (this._type == "loc") {
            start = 0;
            end = 6; //地区集合中前35位是国内省,如果不包含全国则是34
            jQuery("#subHeader1").html("<span>一级学科：</span>");
            //jQuery("#subHeader2").html("<span>其它国家和地区：</span>").show();
            
            jQuery("#subHeader1").show();
            //jQuery("#subHeader2").show();
            //jQuery("#allItems2").html(html.join("")).show();
        }
        else {
            jQuery("#subHeader1").hide();
            jQuery("#subHeader2").hide();
            jQuery("#allItems2").hide();
        }

        html = [];
        var IsAllOption = false;
        for (var j = 0; j < this._curItems.length; j++) {
            if (this._curItems[j].id == "999999") {
                IsAllOption = true;
            }
        }
        //        if (document.getElementById(this.AarearenderSelItemEleId).plShowAllCountry.toLowerCase() == "false")
        //            end--;
        for (var i = start; i < end; i++) {
            var parItem = parItems[i];
            if (parItem.subItems && parItem.subItems.length > 1) {

                if (this._curItems.has({ id: parItem.id, name: parItem.name, parObj: null }) || this._curItems.GetFather({ id: parItem.id, name: parItem.name, parObj: null }) || this._curItems.GetFaFather({ id: parItem.id, name: parItem.name, parObj: null }) || this._curItems.GetFaFaFather({ id: parItem.id, name: parItem.name, parObj: null })) {
                    html.push("<li id=jQuery" + i + " name=" + parItem.id + " class=\"layicon\" onmouseover=\"PopupSelector.showSubItems(");
                }
                else {
                    html.push("<li id=jQuery" + i + " name=" + parItem.id + " onmouseover=\"PopupSelector.showSubItems(");
                }
                html.push(i);
                html.push("," + parItem.id + ", this, true)\" onmouseout=\"PopupSelector.hideSubItems(this)\">");
                html.push("<a href=\"javascript:void(0);\">");

                //html.push("<label >");

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
                }
                else
                    if (this._curItems.has({ id: parItem.id, name: parItem.name, parObj: null })) html.push(" checked");
                html.push(" onclick=\"PopupSelector.click(");
                html.push(i + ",this, null," + parItem.id + ")\" />");
                html.push(dbcToSbc(parItem.name));

                //html.push("</label>");
                html.push("</a></li>");
            }

            else {
                if (this._curItems.has({ id: parItem.id, name: parItem.name, parObj: null }))
                    html.push("<li id=jQuery" + i + " name=" + parItem.id + " class=\"layon\">");
                else
                    html.push("<li id=jQuery" + i + " name=" + parItem.id + " class=\"nonelay\">");
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
                }
                else
                    if (this._curItems.has({ id: parItem.id, name: parItem.name, parObj: null })) html.push(" checked");
                html.push(" onclick=\"PopupSelector.click(");
                html.push(i + ",this, null," + parItem.id + ")\" />");
                html.push(dbcToSbc(parItem.name));

                html.push("</label>");
                html.push("</a></li>");
            }

        }
        jQuery("#allItems1").html(html.join("").toString());
        html.length = 0;

        jQuery("#divSelecting").css("display", (this._curItems.length == 0) ? "none" : "block");
        var displayNoSelected = (this._curItems.length == 0) ? "block" : "none";
        if (this._type == "cat") {
            jQuery("#noSelectedCat").css("display", displayNoSelected);
            jQuery("#noSelectedLoc").hide();
        }
        else if (this._type == "loc") {
            jQuery("#noSelectedLoc").css("display", displayNoSelected);
            jQuery("#noSelectedCat").hide();
        }
        else {
            jQuery("#noSelectedCat").hide();
            jQuery("#noSelectedLoc").hide();
        }
        for (var i = 0; i < this._curItems.length; i++) {
            //            html.push("<li id=\"");
            //            html.push(this._curItems[i].id);
            //            if (this._curItems[i].parObj) {
            //                if (this._curItems[i].parObj.parObj != null) {
            //                    html.push("\"><a href=\"javascript:void(0);\" onclick=\"PopupSelector.removelv3(");
            //                    html.push(this._curItems[i].id);
            //                    html.push("," + this._curItems[i].parObj.id);
            //                    html.push("," + this._curItems[i].parObj.parObj.id);
            //                    for (var m = 0; m < parItems.length; m++) {
            //                        if (parItems[m].id == this._curItems[i].parObj.parObj.id) html.push("," + m + ")\">");
            //                    }
            //                    parItem = this._curItems[i].parObj.parObj;
            //                    nameString = parItem.name + "-" + this._curItems[i].parObj.name + "-" + this._curItems[i].name;
            //                }
            //                else {
            //                    html.push("\"><a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove(");
            //                    html.push(this._curItems[i].id);
            //                    html.push("," + this._curItems[i].parObj.id);
            //                    for (var m = 0; m < parItems.length; m++) {
            //                        if (parItems[m].id == this._curItems[i].parObj.id) html.push("," + m + ")\">");
            //                    }
            //                    nameString = this._curItems[i].parObj.name + "-" + this._curItems[i].name;
            //                }
            //            } else {
            //                html.push("\"><a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove(");
            //                html.push(this._curItems[i].id);
            //                html.push(")\">");
            //                nameString = this._curItems[i].name;
            html.push("<li id=\"");
            html.push(this._curItems[i].id);
            html.push("\"><a href=\"###\" onclick=\"PopupSelector.remove(");
            html.push(this._curItems[i].id);
            if (this._curItems[i].parObj != null) {
                if (this._curItems[i].parObj.parObj != null) {
                    html.push("," + this._curItems[i].parObj.parObj.id + ")\">");
                }
                else {
                    html.push("," + this._curItems[i].parObj.id + ")\">");
                }
            }
            else
                html.push(")\">");
            var nameString;

            if (this._curItems[i].parObj && this._type == "loc") {
                if (this._curItems[i].parObj.parObj.parObj != null) {
                    parItem = parItems[parseInt(this._curItems[i].parObj.parObj)];
                    if (parItem == "undefined" || parItem == null) parItem = this._curItems[i].parObj.parObj;
                    if (parItem.name == this._curItems[i].parObj.name)
                        nameString = this._curItems[i].parObj.name + "-" + this._curItems[i].name;
                    else
                        nameString = this._curItems[i].parObj.parObj.parObj.name + "-" + this._curItems[i].parObj.parObj.name + "-" + this._curItems[i].parObj.name + "-" + this._curItems[i].name;
                } else {
                    nameString = this._curItems[i].parObj.name + "-" + this._curItems[i].name;
                }
            }
            else
                nameString = this._curItems[i].name;
            //}

            html.push(dbcToSbc(nameString));
            html.push("</a></li>");
        }
        jQuery("#selecting").html(html.join(""));
        if (this._type == "ind" || this._type == "comptype") {
            jQuery("#divSelecting").show();
            jQuery("#selecting").hide();
        }
        else jQuery("#selecting").show();
        jQuery("#shield").width(this._style.width[this._type]).height(this._style.height[this._type]);
        jQuery(this._box).css(pos).show();
        jQuery("#mask").bgiframe();//ie6 select
        jQuery("#mask").height(jQuery(document).height()).show();
        //        var tempK = jQuery("html");
        //        jQuery("html").css("overflow", "hidden");

        //定位焦点
        jQuery("#pcbx30000").focus();

    },

    close: function() {
        jQuery("#mask").hide();
        //        jQuery("html").css("overflow", "auto");
        this._ref = null;
        jQuery(this._box).hide();
    },

    OK: function() {
        var html = [], tmpSelectedItem = popupLayer.selectedItems;
        var renderSelItemDiv = this.AarearenderSelItemDiv;
        var renderSelIds = this.AarearenderSelItemEleId;
        var renderfix = this.AareatagPrefix;
        var ids = [];
        var temp = [];
        var parthis = this;
        jQuery.each(this._curItems, function() {
            var nameString;
            if (this.parObj && parthis._type == "loc") {
                var parItemsVal = parthis.allItems[parthis._type]();
                if (this.parObj.parObj.parObj != null) {
                    //parItem = parItemsVal[parseInt(this.parObj.parObj)];
                    parItem = this.parObj.parObj.parObj;
                    if (parItem == "undefined" || parItem == null) parItem = this.parObj.parObj.parObj;
                    if (parItem.name == this.parObj.name)
                        nameString = this.parObj.name + "-" + this.name;
                    else
                        nameString = parItem.name + "-" + this.parObj.parObj.name + "-" + this.parObj.name + "-" + this.name;
                        ids.push(parItem.id + "-" + this.parObj.parObj.id + "-" + this.parObj.id + "-" + this.id);
                } else {
                    nameString = this.parObj.name + "-" + this.name;
                }
            }
            else
                nameString = this.name;
            temp.push(nameString);
            html.push('<div class="list_m" id="' + renderfix + '_divSelItem_' + this.id + '">');
            html.push('<div class="list_c">');
            html.push(nameString);
            html.push('</div>');
            html.push('<input type="button" value="删除" title="删除"');
            html.push('onclick="pLayer.remove(\'' + renderfix + '_divSelItem_' + this.id + '\',\'' + renderSelIds + '\',\'' + renderSelItemDiv + '\')" />');
            html.push('</div>');
            //ids.push(this.id);
        });
        $(renderSelItemDiv).innerHTML = html.join("");
        $("#renderSelIds").val(ids.join(','));
        //document.getElementById(renderSelItemDiv).innerHTML = html.join("");
        //document.getElementById(renderSelIds).value = ids.join(',');
        if (this.InSalaryPage) {
            if (this._curItems) {
                if (this._type == "loc" && this._curItems[0] != null) {
                    jQuery("#hidLoc").val(this._curItems[0].id);
                }
                if (this._type == "ind" && this._curItems[0] != null) {
                    jQuery("#hidInd").val(this._curItems[0].id);
                }
                if (this._type == "cat" && this._curItems[0] != null) {
                    jQuery("#hidCat").val(this._curItems[0].id);
                }
            }
        }
        jQuery(this._ref).val(dbcToSbc(temp.join("+")));
        this.InitClass(temp, this._type);
        this._ref = null;
        this._selItems[this._type] = this._curItems.clone();
        this._curItems.clear();
        jQuery("#selecting").html("");
        jQuery("#mask").hide();
        jQuery(this._box).hide();
    },

    empty: function() {
        jQuery("#selecting").html("");
        jQuery("#allItems1 input").each(function(i) { this.checked = false; this.disabled = false; });
        jQuery("#allItems2 input").each(function(i) { this.checked = false; });
        jQuery("#allItems1 li").each(function(i) { if (this.className == "layicon") this.className = ""; if (this.className == "layon") this.className = "nonelay"; });
        jQuery("#allItems2 li").each(function(i) { if (this.className == "layicon") this.className = ""; if (this.className == "layon") this.className = "nonelay"; });
        this._curItems.clear();
        PopupSelector.showtips();
        //this.showHideSelecting(this);
    },
    InSalaryPage: false,
    InAdvanceSearchPage: false,
    click: function(ref, cbx, parObj, parName, lev) {
        // jQuery("#debugInfo").html("ref: " + ref + " cbx: " + cbx + " parObj:" + parObj + " parName: " + parName + " lev:" + lev);
        //限制选择个数
        //if (cbx.checked && this._curItems.length == this._maxSize) {
        //   alert(this._gtMaxLimit);
        //    cbx.checked = false;
        //    return;
        //}
        var item = { id: cbx.value.split("@")[0], name: cbx.value.split("@")[1], parObj: parObj };
        var selecting = document.getElementById("selecting");
        var selItem = null;
       
        if (cbx.checked) {
            if (parName != "999999") {
                selItem = document.createElement("li");
                selItem.id = item.id;
                if (parObj == null) {
                    //移除2,3级开始
                    var parItem = this.allItems[this._type]()[ref];
                    var parthis = this;
                    jQuery(parthis._curItems).each(function() {
                        var tt = this.id;
                        itemSubstring = document.getElementById(tt);
                        if (itemSubstring != null) {
                            var tmp = false;
                            if (parItem.subItems && parItem.subItems.length > 0) {
                                for (var i = 0; i < parItem.subItems.length; i++) {
                                    if (parItem.subItems[i].id == tt) tmp = true;
                                    if (parItem.subItems[i].subItems && parItem.subItems[i].subItems.length > 0)
                                        jQuery(parItem.subItems[i].subItems).each(function() {
                                            if (this.id == tt) tmp = true;
                                        });
                                }
                            }
                            if (tmp == true) {
                                selecting.removeChild(itemSubstring);
                                parthis._curItems.remove(this.id);
                            }
                        }
                    });
                    //移除2,3级结束
                    selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove(" + item.id + ",null);\">" + dbcToSbc(item.name) + "</a>";
                }
                else {
                    var nameString;
                    if (lev == 3) {
                        var parItemsVal = this.allItems[this._type]();
                        //var indexval = parseInt(parObj.parObj);
                        //parItem = parItemsVal[indexval];
                        parItem = parObj.parObj.parObj;
                        nameString = parItem.name + "-" + parObj.parObj.name + "-" + parObj.name + "-" + item.name;
                        selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.removelv3(" + item.id + "," + parObj.id + "," + parItem.id + "," + parItem.index + ");\">" + dbcToSbc(nameString) + "</a>";
                    }
                    else {
                        //移除3级开始
                        var parItem = this.allItems[this._type]()[parObj.parObj].subItems[ref];
                        var parthis = this;
                        jQuery(parthis._curItems).each(function() {
                            var tt = this.id;
                            itemSubstring = document.getElementById(tt);
                            if (itemSubstring != null) {
                                var tmp = false;
                                if (parItem.subItems && parItem.subItems.length > 0)

                                    jQuery(parItem.subItems).each(function() {
                                        if (this.id == tt) tmp = true;
                                    });
                                if (tmp == true) {
                                    selecting.removeChild(itemSubstring);
                                    parthis._curItems.remove(this.id);
                                }
                            }
                        });
                        //移除3级结束
                        if (this._type == "loc") {
                            nameString = parObj.name + "-" + item.name;
                        }
                        else {
                            nameString = item.name;
                        }
                        selItem.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove(" + item.id + "," + parObj.id + "," + parObj.parObj + ");\">" + dbcToSbc(nameString) + "</a>";
                    }
                }
                selecting.appendChild(selItem);
                this._curItems.push(item);
            }
            else {
                var parthis = this;

                //移除先前选择过的地区
                if (parthis._curItems.length > 0) {

                    jQuery(parthis._curItems).each(function() {
                        var tt = this.id;
                        itemSubstring = document.getElementById(tt);
                        if (itemSubstring != null) {
                            var tmp = false;
                            jQuery("#allItems2 input").each(function() {
                                if (this.value.split("@")[0] == tt) tmp = true;
                            });
                            if (tmp != true) {
                                selecting.removeChild(itemSubstring);
                                parthis._curItems.remove(this.id);
                            }
                        }
                    });

                }
                PopupSelector.showtips();
                //添加所有地区
                jQuery("#allItems1 input").each(function() {
                    if (this.id != "pcbx999999") {
                        var itemSubstring = null;
                        var selItemSubstring = null;
                        itemSubstring = { id: this.value.split("@")[0], name: this.value.split("@")[1], parObj: null };
                        selItemSubstring = document.createElement("li");
                        selItemSubstring.id = itemSubstring.id;
                        selItemSubstring.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"PopupSelector.remove(" + itemSubstring.id + ",null);\">" + dbcToSbc(itemSubstring.name) + "</a>";
                        selecting.appendChild(selItemSubstring);
                        parthis._curItems.push(itemSubstring);
                    }
                });
            }
            this.showHideSelecting(this);
        }
        else {
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
            }
            else {
                selItem = document.getElementById(item.id);
                selecting.removeChild(selItem);
                this._curItems.remove(item.id);
            }
            PopupSelector.showtips();
        }
        //设置样式
        if (parObj == null) {//选择省一级，将下一级全部选中
            var parItems = this.allItems[this._type]();
            parItem = parItems[ref];
            if (cbx.checked) {
                if (parItem.subItems && parItem.subItems.length > 1)
                    document.getElementById('jQuery' + ref).className = "layicon";
                else
                    document.getElementById('jQuery' + ref).className = "layon";

            }
            else {
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
                    }
                    else {
                        if (parItemSub.subItems && parItemSub.subItems.length > 1)
                            document.getElementById('jQuery' + i).className = "";
                        else
                            document.getElementById('jQuery' + i).className = "nonelay";
                    }
                }
                );
            }
            else {
                try {
                    if (parItem.subItems && parItem.subItems.length > 1)
                        var itemType = this._type;
                    jQuery("#subItems input").each(function(i) {
                        this.checked = this.disabled = cbx.checked;
                        var ItemSubCollection = parItem.subItems[i];
                        var idString = "^" + i;
                        if (itemType != "loc")
                            idString = "^" + (i + 1);
                        var subItems = document.getElementById(idString);
                        if (subItems != null) {
                            if (cbx.checked) {
                                if (ItemSubCollection.subItems && ItemSubCollection.subItems.length > 1) {
                                    subItems.className = "layicon";
                                }
                                else {
                                    subItems.className = "layon";
                                }
                            }
                            else {
                                if (ItemSubCollection.subItems && ItemSubCollection.subItems.length > 1)
                                    subItems.className = "";
                                else
                                    subItems.className = "nonelay";
                            }
                        }
                    });
                }
                catch (err) {

                }
            }
        }
        else {
            var parItems = this.allItems[this._type]();
            var indexString;
            for (var i = 0; i < parItems.length; i++) {
                if (parItems[i].id == parObj.id) {
                    parItem = parItems[i];
                    indexString = i;
                    break;
                }
            }
            if (cbx.checked) {
               
                //                var id = parObj.id;
                //                jQuery("li[@name=" + id + "]").addClass("layicon");
                if (lev == 2)
                    document.getElementById('jQuery' + indexString).className = "layicon";
                else if (lev == 3) {
                    //document.getElementById('jQuery' + parObj.parObj).className = "layicon";
                    document.getElementById(parObj.tid).className = "layon";
                }
            }
            else {

                if (lev == 2) {
                    if (!(this._curItems.GetFather({ id: parObj.id, name: null, parObj: null })))
                        document.getElementById('jQuery' + indexString).className = "layshow";
                    if (parItem.subItems[ref].subItems && parItem.subItems[ref].subItems.length > 1)
                        document.getElementById('^' + ref).className = "";
                    else
                        document.getElementById('^' + ref).className = "nonelay";
                }
                else if (lev == 3) {
                    var flag = false;
                    var tmpdata = this.allItems[this._type]()[parObj.parObj.parObj.index].subItems;
                    for (var i = 0; i < tmpdata.length; i++) {
                        for (var m = 0; m < this._curItems.length; m++) {
                            if (this._curItems[m].id != null && this._curItems[m].id == tmpdata[i].id) flag = true;
                            if (this._curItems[m].parObj != null && this._curItems[m].parObj.id == tmpdata[i].id) flag = true;
                        }
                    }
                    if (flag == false) document.getElementById('jQuery' + parObj.parObj.parObj.index).className = "layshow";
                    if (!(this._curItems.GetFather({ id: parObj.id, name: null, parObj: null }))) {
                        document.getElementById('^' + ref).className = "layshow";
                    }
                    document.getElementById(parObj.tid).className = "nonelay";
                }
            }
            if (lev == "2") {
                jQuery("#thirdItems input").each(function(i) { this.checked = this.disabled = cbx.checked; });
                if (cbx.checked) {
                    jQuery("#thirdItems li").each(function(i) { this.className = "layon"; });
                }
                else {
                    jQuery("#thirdItems li").each(function(i) { this.className = "nonelay"; });
                }
            }
        }

    },
    showtips: function() {
        if (this._curItems.length == 0) {
            if (this._type != "ind")
                jQuery("#divSelecting").css("display", "none");
            if (this._type == "cat") {
                //jQuery("#allItems1 li").each(function(i) { if(this.className =="layicon")this.className="";if(this.className =="layon")this.className="nonelay"; });
                jQuery("#noSelectedLoc").hide();
                if (this.InSalaryPage) {
                    jQuery("#noSelectedCat p").html(jQuery("#noSelectedCat p").html().replace("您最多可以选择5个职位类别", "&nbsp;"));
                }
                jQuery("#noSelectedCat").show();
            }
            if (this._type == "loc") {
                //jQuery("#allItems1 li").each(function(i) { if(this.className =="layicon")this.className="";if(this.className =="layon")this.className="nonelay"; });
                jQuery("#noSelectedCat").hide();
                if (this.InSalaryPage) {
                    jQuery("#noSelectedLoc p").html(jQuery("#noSelectedLoc p").html().replace("您最多可以选择5个工作地点", ""));
                }
                jQuery("#noSelectedLoc").show();
            }

        }
    },

    remove: function(id, parObj, index) {
        document.getElementById("selecting").removeChild(document.getElementById(id));
        var pcbx = document.getElementById("pcbx" + id);
        if (pcbx) pcbx.checked = false;
        var array = this._curItems.clone();
        this._curItems.clear();
        for (var i = 0; i < array.length; i++) {
            if (array[i].id != id) this._curItems.push(array[i]);
        }

        if (parObj == null) {
            jQuery("li[@name=" + id + "]").removeClass("layicon");
            jQuery("li[@name=" + id + "]").removeClass("layon");
            for (var i = 32; i < this.allItems[this._type]().length; i++) {

                if (this.allItems[this._type]()[i].id == id && this.allItems[this._type]()[i].subItems.length <= 1) {
                    jQuery("li[@name=" + id + "]").addClass("nonelay");
                    break;
                }
            }
        }
        else {
            var flag = false;
            if (index != undefined) {
                var tmpdata = this.allItems[this._type]()[index].subItems;
                for (var i = 0; i < tmpdata.length; i++) {
                    for (var m = 0; m < this._curItems.length; m++) {
                        if (this._curItems[m].id != null && this._curItems[m].id == tmpdata[i].id) flag = true;
                        if (this._curItems[m].parObj != null && this._curItems[m].parObj.id == tmpdata[i].id) flag = true;
                    }
                }
            }
            if (flag == false)
                if (!(this._curItems.GetFather({ id: parObj, name: null, parObj: null })) && !(this._curItems.GetFaFather({ id: parObj, name: null, parObj: null })))
                jQuery("li[@name=" + parObj + "]").removeClass("layicon");
        }
        var temp = jQuery("#allItems1 li[@name=" + id + "]");
        if (temp.length > 0) {
            var pcbxAll = document.getElementById("pcbx999999");
            if (pcbxAll != null && pcbxAll.checked) {
                jQuery("#allItems1 input").each(function(i) { this.disabled = false; });
                pcbxAll.checked = false;
                jQuery("li[@name=999999]").attr("class", "nonelay");
            }
        }
        PopupSelector.showtips();
        //this.showHideSelecting(this);
    },
    removelv3: function(id, parObj, fname, index) {
        document.getElementById("selecting").removeChild(document.getElementById(id));
        var pcbx = document.getElementById("pcbx" + id);
        if (pcbx) pcbx.checked = false;
        var array = this._curItems.clone();
        this._curItems.clear();
        for (var i = 0; i < array.length; i++) {
            if (array[i].id != id) this._curItems.push(array[i]);
        }
        if (parObj == null) {
            jQuery("li[@name=" + id + "]").removeClass("layicon");
            jQuery("li[@name=" + id + "]").removeClass("layon");
        }
        else {
            var flag = false;
            var tmpdata = this.allItems[this._type]()[index].subItems;
            for (var i = 0; i < tmpdata.length; i++) {
                for (var m = 0; m < this._curItems.length; m++) {
                    if (this._curItems[m].id != null && this._curItems[m].id == tmpdata[i].id) flag = true;
                    if (this._curItems[m].parObj != null && this._curItems[m].parObj.id == tmpdata[i].id) flag = true;
                }
            }
            if (flag == false)
                jQuery("li[@name=" + fname + "]").removeClass("layicon");
        }
        PopupSelector.showtips();
        //this.showHideSelecting(this);
    },

    //private method
    showHideSelecting: function(selector) {
        jQuery("#noSelectedCat").hide();
        jQuery("#noSelectedLoc").hide();

        if (selector._curItems.length > 0) {
            jQuery("#divSelecting").show();
        }
        //        else {
        //            if (selector._type != "ind") jQuery("#divSelecting").hide();
        //            if (selector._type == "cat") jQuery("#noSelectedCat").show();
        //            else if (selector._type == "loc") jQuery("#noSelectedLoc").show();
        //        }
    },

    showSubItems: function(index, firstid, ref, isDelay, ev) {
        var subItems = jQuery("#subItems");
        if (this._hideTimer) clearTimeout(this._hideTimer);
        if (this._showTimer) clearTimeout(this._showTimer);
        if (index == this._lastPopupIndex && subItems.css("display") == "block") {
            jQuery(ref).addClass("layshow");
            return;
        }
        if (!isDelay) loadSubItems(index, ref, this);
        var self = this;
        this._showTimer = setTimeout(function() { loadSubItems(index, ref, self); }, this._delay);

        function loadSubItems(index, ref, self) {
            var parItem = self.allItems[self._type]()[index];
            var offset = jQuery(ref).offset();
            var pos = { top: offset.top + self._style.offset.levelTwo[self._type].Y, left: offset.left + self._style.offset.levelTwo[self._type].X };
            if (document.body.clientWidth < pos.left + 296) {
                pos.left = offset.left - 296;
            }
            var actualSubItemCount = parItem.subItems.length - 1;
            var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2 : (actualSubItemCount / 2 + 1);
            if (actualSubItemCount <= self._oneColumnLimit[self._type]) calItemCount = actualSubItemCount;
            var subBoxHeight = self._style.lineHeight * parseInt(calItemCount) + self._style.topBottomMargin;
            var winHeight = jQuery(window).height();
            var scrolTop;
            //if (jQuery.browser.msie && jQuery.browser.version == '6.0') scrolTop = document.body.scrollTop;
            if (jQuery.browser.msie || jQuery.browser.mozilla) scrolTop = document.body.scrollTop;
            else scrolTop = document.documentElement.scrollTop;
            var remainHeight = winHeight - pos.top + scrolTop;
            if (remainHeight < subBoxHeight) pos.top = pos.top - (subBoxHeight - remainHeight);
            //如果整个IE窗口的高度小于弹出层的高度，则弹出层以对齐顶部显示
            if (subBoxHeight > winHeight) pos.top = scrolTop;

            var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled " : "";
            var item = { id: parItem.id, name: parItem.name, parObj: null };
            var html = [];
            html.push("<ol >");
            var startIndex = 1; //因为职类的子项的第一项是父项用于职位分类搜索 所以应该把第一个子项过滤掉

            if (self._type == "loc") {
                startIndex = 0;
            }
            for (var i = startIndex; i < parItem.subItems.length; i++) {
                var subItem = parItem.subItems[i];
                subItem.parObj = item;
                if (ref.getElementsByTagName("input")[0].checked == true || self._curItems.has(subItem) || self._curItems.GetFather(subItem)) {
                    if (parItem.subItems[i].subItems && parItem.subItems[i].subItems.length > 0) {
                        html.push("<li id=^" + i + " name=" + parItem.id + "  class=\"layicon\"  onmouseover=\"PopupSelector.showThirdItems(");
                        html.push(index);
                        html.push(",")
                        html.push(i);
                        html.push(", this, true)\" onmouseout=\"PopupSelector.hideThirdItems(this)\">");
                        html.push("<a href=\"javascript:void(0);\">");
                        html.push("<input id=\"scbx");
                        html.push(subItem.id);
                        html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
                        html.push(subItem.id);
                        html.push("@");
                        html.push(subItem.name);
                        html.push("\"");
                        html.push(parentChecked);
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + index + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','2')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</a></li>");
                    } else {
                        html.push("<li id=^" + i + " name=" + parItem.id + " class=\"layon\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");
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
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + index + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','2')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</label></a></li>");
                    }
                } else {
                    if (parItem.subItems[i].subItems && parItem.subItems[i].subItems.length > 0) {
                        html.push("<li id=^" + i + " name=" + parItem.id + "  onmouseover=\"PopupSelector.showThirdItems(");
                        html.push(index);
                        html.push(",")
                        html.push(i);
                        html.push(", this, true)\" onmouseout=\"PopupSelector.hideThirdItems(this)\">");
                        html.push("<a href=\"javascript:void(0);\">");
                        html.push("<input id=\"scbx");
                        html.push(subItem.id);
                        html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
                        html.push(subItem.id);
                        html.push("@");
                        html.push(subItem.name);
                        html.push("\"");
                        html.push(parentChecked);
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + index + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','2')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</a></li>");
                    }
                    else {
                        html.push("<li id=^" + i + " name=" + parItem.id + " class=\"nonelay\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");
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
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + index + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','2')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</label></a></li>");
                    }
                }


                //                html.push("<a href=\"javascript:void(0);\"><label for=\"scbx");
                //                html.push(subItem.id);
                //                html.push("\">

            }
            html.push("</ol>");
            var subBox = jQuery("#subBox");
            for (var i = 0; i < self._types.length; i++) {
                subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
                subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
            }
            var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type] : self._style.className.levelTwo1[self._type];
            if (self._type == "loc") {
                if (parItem.id == "8000" || parItem.id == "15000") {
                    levelTwo = self._style.className.levelTwo1[self._type];
                }
            }
            self._lastPopupIndex = index;
            jQuery("#subItems").hover(function(e) { self.showSubItems(index, firstid, ref, true, e); }, function(e) { self.hideSubItems(ref); });
            subBox.html(html.join(""));
            subItems.addClass(levelTwo).css(pos).show();
        }
    },

    hideSubItems: function(ref) {
        jQuery(ref).removeClass("layshow");
        if (this._showTimer) clearTimeout(this._showTimer);
        if (this._hideTimer) clearTimeout(this._hideTimer);
        this._hideTimer = setTimeout(function() { jQuery("#subItems").hide(); }, 100);
    },
    clearThirdHidden: function() {
        if (this._showThirdTimer) clearTimeout(this._showThirdTimer);
        if (this._hideThirdTimer) clearTimeout(this._hideThirdTimer);
        jQuery("#thirdItems").hide();
    },
    clearFourthHidden: function() {
        if (this._showFourthTimer) clearTimeout(this._showFourthTimer);
        if (this._hideFourthTimer) clearTimeout(this._hideFourthTimer);
        jQuery("#fourthItems").hide();
    },
    showThirdItems: function(index, secondIndex, ref, isDelay, ev) {

        var subItems = jQuery("#thirdItems");
        //                if (this._hideTimer) clearTimeout(this._hideTimer);
        //                if (this._showTimer) clearTimeout(this._showTimer);

        if (this._showThirdTimer) clearTimeout(this._showThirdTimer);
        if (this._hideThirdTimer) clearTimeout(this._hideThirdTimer);

        //jQuery("#debugInfo").html("index: " + index + " secondIndex:" + secondIndex + " this._lastPopupIndex: " + this._lastPopupIndex);
        //        if (index == this._lastPopupIndex && subItems.css("display") == "block") {
        //            jQuery(ref).addClass("layshow");
        //            return;
        //        }
        
        if ((secondIndex + 50) == this._lastPopupIndex && subItems.css("display") == "block") {
            jQuery("#jQuery" + index).addClass("layshow"); jQuery(ref).addClass("layshow");
            return;
        }
        
        if (!isDelay) loadThirdItems(index, secondIndex, ref, this);
        var self = this;
        this._showThirdTimer = setTimeout(function() { loadThirdItems(index, secondIndex, ref, self); }, this._delay);

        function loadThirdItems(index, secondIndex, ref, self) {


            var parParItem = self.allItems[self._type]()[index];
            var parItem = self.allItems[self._type]()[index].subItems[secondIndex];
            
            var offset = jQuery(ref).offset();
            var pos = {
                top: offset.top + self._style.offset.levelTwo[self._type].Y,
                left: offset.left + self._style.offset.levelTwo[self._type].X
            };
            if (document.body.clientWidth < pos.left + 296) {
                pos.left = offset.left - 296;
            }
            var actualSubItemCount = parItem.subItems.length - 1;
            var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2 : (actualSubItemCount / 2 + 1);
            if (actualSubItemCount <= self._oneColumnLimit[self._type]) calItemCount = actualSubItemCount;
            var subBoxHeight = self._style.lineHeight * parseInt(calItemCount) + self._style.topBottomMargin;
            var winHeight = jQuery(window).height();
            var scrolTop;
            if (jQuery.browser.msie || jQuery.browser.mozilla) scrolTop = document.body.scrollTop;
            else scrolTop = document.documentElement.scrollTop;
            var remainHeight = winHeight - pos.top + scrolTop;
            if (remainHeight < subBoxHeight) pos.top = pos.top - (subBoxHeight - remainHeight);
            if (subBoxHeight > winHeight) pos.top = scrolTop;
            var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled " : "";
            var item = {
                id: parItem.id,
                name: parItem.name,
                parObj: {id:parParItem.id,name:parParItem.name}
            };
            var html = [];
            html.push("<ol >");
            for (var i = 0; i < parItem.subItems.length; i++) {
                var subItem = parItem.subItems[i];
                subItem.parObj = item;
                
                 if (ref.getElementsByTagName("input")[0].checked == true || self._curItems.has(subItem) || self._curItems.GetFather(subItem)) {
                    if (parItem.subItems[i].subItems && parItem.subItems[i].subItems.length > 0) {
                        html.push("<li id=^^" + i + " name=" + parItem.id + "  class=\"layicon\"  onmouseover=\"PopupSelector.showFourthItems(");
                        html.push(index);
                        html.push(",");
                        html.push(secondIndex);
                        html.push(",");
                        html.push(i);
                        html.push(", this, true)\" onmouseout=\"PopupSelector.hideFourthItems(this)\">");
                        html.push("<a href=\"javascript:void(0);\">");
                        html.push("<input id=\"scbx");
                        html.push(subItem.id);
                        html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
                        html.push(subItem.id);
                        html.push("@");
                        html.push(subItem.name);
                        html.push("\"");
                        html.push(parentChecked);
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + secondIndex + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','3')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</a></li>");
                    } else {
                        html.push("<li id=^^" + i + " name=" + parItem.id + " class=\"layon\" onmouseover='PopupSelector.clearFourthHidden()' onmouseover='PopupSelector.hideFourthItems(this)'>");
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
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + secondIndex + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','3')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</label></a></li>");
                    }
                } else {
                    if (parItem.subItems[i].subItems && parItem.subItems[i].subItems.length > 0) {
                        html.push("<li id=^^" + i + " name=" + parItem.id + " onmouseover=\"PopupSelector.showFourthItems(");
                        html.push(index);
                        html.push(",");
                        html.push(secondIndex);
                        html.push(",");
                        html.push(i);
                        html.push(", this, true)\" onmouseout=\"PopupSelector.hideFourthItems(this)\">");
                        html.push("<a href=\"javascript:void(0);\">");
                        html.push("<input id=\"scbx");
                        html.push(subItem.id);
                        html.push("\" type=\"" + PopupSelector._checkType + "\" value=\"");
                        html.push(subItem.id);
                        html.push("@");
                        html.push(subItem.name);
                        html.push("\"");
                        html.push(parentChecked);
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + secondIndex + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','3')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</a></li>");
                    }
                    else {
                        html.push("<li id=^^" + i + " name=" + parItem.id + " class=\"nonelay\" onmouseover='PopupSelector.clearFourthHidden()' onmouseover='PopupSelector.hideFourthItems(this)'>");
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
                        if (self._curItems.has(subItem)) html.push(" checked");
                        html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                        html.push(parItem.id);
                        html.push(", name: '");
                        html.push(parItem.name);
                        html.push("', parObj: " + secondIndex + " }");
                        //html.push("', parObj:null }");
                        //html.push(",'')\" />");
                        html.push(",'','3')\" />");
                        html.push(dbcToSbc(subItem.name));
                        html.push("</label></a></li>");
                    }
                }
            }
                
            html.push("</ol>");
            var subBox = jQuery("#thirdBox");
            for (var i = 0; i < self._types.length; i++) {
                //subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
                //subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
                subItems.removeClass(self._style.className.levelThree1[self._types[i]]);
                subItems.removeClass(self._style.className.levelThree2[self._types[i]]);
            }
            var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type] : self._style.className.levelTwo1[self._type];
            //var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelThree2[self._type] : self._style.className.levelThree1[self._type];
            if (self._type == "loc") {
                if (parItem.id == "8000" || parItem.id == "15000") {
                    //levelTwo = self._style.className.levelTwo1[self._type];
                    levelTwo = self._style.className.levelThree1[self._type];
                }
            }
            self._lastPopupIndex = secondIndex + 50;
            //jQuery("#thirdItems").hover(function(e) { self.showSubItems(index, ref, true, e); }, function(e) { self.hideSubItems(ref); });
            jQuery("#thirdItems").hover(
                function(e) {
                    //alert(self.thirdHoverIn());
                    // debugger;
                    if (self._hideTimer) clearTimeout(self._hideTimer);
                    if (self._hideThirdTimer) clearTimeout(self._hideThirdTimer);
                    //self.showThirdItems(index, secondIndex, ref, isDelay, ev);
                    self.showThirdItems(index, secondIndex, ref, true, e);
                },
                function(e) {
                    ; //if (self._hideTimer) clearTimeout(self._hideTimer);
                    self.hideThirdItems();
                }
            );

            subBox.html(html.join(""));
            subItems.css("display", "block");
            subItems.addClass(levelTwo).css(pos).show();
            //debugger;
        };
        //loadSubItems end
    },
    showFourthItems: function(index, secondIndex,thirdIndex, ref, isDelay, ev) {
        var subItems = jQuery("#fourthItems");

        if (this._showFourthTimer) clearTimeout(this._showFourthTimer);
        if (this._hideFourthTimer) clearTimeout(this._hideFourthTimer);

        
        
        if (!isDelay) loadFourthItems(index, secondIndex,thirdIndex, ref, this);
        var self = this;
        this._showFourthTimer = setTimeout(function() { loadFourthItems(index, secondIndex,thirdIndex, ref, self); }, this._delay);

        function loadFourthItems(index, secondIndex,thirdIndex, ref, self) {


            var parParParItem = self.allItems[self._type]()[index];
            var parParItem = self.allItems[self._type]()[index].subItems[secondIndex];
            var parItem = self.allItems[self._type]()[index].subItems[secondIndex].subItems[thirdIndex];
            var offset = jQuery(ref).offset();
            var pos = {
                top: offset.top + self._style.offset.levelTwo[self._type].Y,
                left: offset.left + self._style.offset.levelTwo[self._type].X
            };
            if (document.body.clientWidth < pos.left + 296) {
                pos.left = offset.left - 296;
            }
            var actualSubItemCount = parItem.subItems.length - 1;
            var calItemCount = (actualSubItemCount % 2 == 0) ? actualSubItemCount / 2 : (actualSubItemCount / 2 + 1);
            if (actualSubItemCount <= self._oneColumnLimit[self._type]) calItemCount = actualSubItemCount;
            var subBoxHeight = self._style.lineHeight * parseInt(calItemCount) + self._style.topBottomMargin;
            var winHeight = jQuery(window).height();
            var scrolTop;
            if (jQuery.browser.msie || jQuery.browser.mozilla) scrolTop = document.body.scrollTop;
            else scrolTop = document.documentElement.scrollTop;
            var remainHeight = winHeight - pos.top + scrolTop;
            if (remainHeight < subBoxHeight) pos.top = pos.top - (subBoxHeight - remainHeight);
            if (subBoxHeight > winHeight) pos.top = scrolTop;
            var parentChecked = (ref.getElementsByTagName("input")[0].checked == true) ? " checked disabled " : "";
            var item = {
                id: parItem.id,
                name: parItem.name,
                parObj: {id:parParItem.id,name:parParItem.name,parObj:{id:parParParItem.id,name:parParParItem.name}}
            };
            var html = [];
            html.push("<ol >");
            for (var i = 0; i < parItem.subItems.length; i++) {
                var subItem = parItem.subItems[i];
                subItem.parObj = item;
                //                if (ref.getElementsByTagName("input")[0].checked == true || self._curItems.has(subItem)) {
                //                    html.push("<li id=Q" + i + " class=\"nonelay\"  >");
                //                }
                //                else
                if (ref.getElementsByTagName("input")[0].checked == true || self._curItems.has(subItem)) {
                    html.push("<li id=Q" + i + " class=\"layon\"  >");
                }
                else {
                    html.push("<li id=Q" + i + " class=\"nonelay\"  >");
                }
                html.push("<a href=\"javascript:void(0);\"><label for=\"tcbx");
                html.push(subItem.id);
                html.push("\"><input id=\"tcbx");
                html.push(subItem.id);
                html.push("\" type=\"checkbox\" value=\"");
                html.push(subItem.id);
                html.push("@");
                html.push(subItem.name);
                html.push("\"");
                html.push(parentChecked);
                if (self._curItems.has(subItem)) html.push(" checked");
                html.push(" onclick=\"PopupSelector.click(" + i + ",this, { id: ");
                html.push(parItem.id);
                html.push(", name: '");
                html.push(parItem.name);
                html.push("', parObj: {id:" + item.parObj.id + ",name:'"+item.parObj.name+"',parObj:{id:"+item.parObj.parObj.id+",name:'"+item.parObj.parObj.name+"',index:"+index+"}},tid:'Q" + i + "'}");
                html.push(",'','3')\" />");
                html.push(addSpan(subItem.name));
                html.push("</label></a></li>");
            }
            html.push("</ol>");
            var subBox = jQuery("#fourthBox");
            for (var i = 0; i < self._types.length; i++) {
                //subItems.removeClass(self._style.className.levelTwo1[self._types[i]]);
                //subItems.removeClass(self._style.className.levelTwo2[self._types[i]]);
                subItems.removeClass(self._style.className.levelThree1[self._types[i]]);
                subItems.removeClass(self._style.className.levelThree2[self._types[i]]);
            }
            //var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelTwo2[self._type] : self._style.className.levelTwo1[self._type];
            var levelTwo = (actualSubItemCount > self._oneColumnLimit[self._type]) ? self._style.className.levelThree2[self._type] : self._style.className.levelThree1[self._type];
            if (self._type == "loc") {
                if (parItem.id == "8000" || parItem.id == "15000") {
                    //levelTwo = self._style.className.levelTwo1[self._type];
                    levelTwo = self._style.className.levelThree1[self._type];
                }
            }
            self._lastPopupIndex = secondIndex + 50;
            //jQuery("#thirdItems").hover(function(e) { self.showSubItems(index, ref, true, e); }, function(e) { self.hideSubItems(ref); });
            jQuery("#fourthItems").hover(
                function(e) {
                    //alert(self.thirdHoverIn());
                    // debugger;
                    if (self._hideTimer) clearTimeout(self._hideTimer);
                    if (self._hideThirdTimer) clearTimeout(self._hideThirdTimer);
                    if (self._hideFourthTimer) clearTimeout(self._hideFourthTimer);
                    //self.showThirdItems(index, secondIndex, ref, isDelay, ev);
                    self.showFourthItems(index, secondIndex,thirdIndex, ref, true, e);
                },
                function(e) {
                    ; //if (self._hideTimer) clearTimeout(self._hideTimer);
                    self.hideFourthItems();
                }
            );

            subBox.html(html.join(""));
            subItems.css("display", "block");
            subItems.addClass(levelTwo).css(pos).show();
            //debugger;
        };
        //loadSubItems end
    },
    hideThirdItems: function(ref) {
        jQuery(ref).removeClass("layshow");

        if (this._showTimer) clearTimeout(this._showTimer);
        if (this._hideTimer) clearTimeout(this._hideTimer);

        if (this._showThirdTimer) clearTimeout(this._showThirdTimer);
        if (this._hideThirdTimer) clearTimeout(this._hideThirdTimer);



        this._hideThirdTimer = setTimeout(function() {
            jQuery("#thirdItems").hide();
            jQuery("#subItems").hide();
        }, 100);
    },
    hideFourthItems: function(ref) {
        jQuery(ref).removeClass("layshow");
		if (this._showTimer) clearTimeout(this._showTimer);
        if (this._hideTimer) clearTimeout(this._hideTimer);
        if (this._showThirdTimer) clearTimeout(this._showThirdTimer);
        if (this._hideThirdTimer) clearTimeout(this._hideThirdTimer);
        if (this._showFourthTimer) clearTimeout(this._showFourthTimer);
        if (this._hideFourthTimer) clearTimeout(this._hideFourthTimer);

        this._hideFourthTimer = setTimeout(function() {
            jQuery("#fourthItems").hide();
            jQuery("#thirdItems").hide();
            jQuery("#subItems").hide();
        }, 100);
    },
    _showTimer: null,
    _hideTimer: null,
    _showThirdTimer: null,
    _hideThirdTimer: null,
    _showFourthTimer: null,
    _hideFourthTimer: null,
    _lastPopupIndex: null,
    _box: null,
    _subbox: null,
    _ref: null,
    _type: null,
    _types: ["cat", "ind", "loc", "comptype"],
    _maxSize: 5,
    _checkType: "hidden",

    //_curItems：在弹出层显示时，用户增加或减少的选项都在这个数组里，当用户清空时，也是清空此数组，
    //当点击确定时，将此数组中的选项复制到_selItems中对应的数组做持久保存，然后清空此数组
    //示例：[{id:xxx, name:xxxx, parObj:null/object ref, isParent:true/false}, .....]
    _curItems: [],
    _selItems: { cat: [], loc: [], ind: [], comptype: [] },

    _gtMaxLimit: "对不起,您的已选项已经达到了5项.请减少已选项,再继续选择",
    _gtYourSelected: { cat: "您选择的职位类别是：", ind: "您最多可以选择５个行业类别", loc: "您选择的试题属性是", comptype: "您选择的公司性质是：" },
    _gtPopupSelectorHeader: { cat: "职位类别", ind: "行业类别", loc: "请选择试题属性", comptype: "公司性质" },
    _oneColumnLimit: { cat: 12, loc: 11 }, //二级类别显示成一列的最大条目数，超过这个数就要显示成两列
    _delay: 500,
    _style: {
        className: {
            levelOne: { "cat": "lay_wl", "loc": "lay_wls", "ind": "lay_wll", "comptype": "lay_wms" },
            //二级类别一列显示的样式
            levelTwo1: { "cat": "lay_wm", "loc": "lay_ws lm", "ind": "", "comptype": "" },
            //二级类别两列显示的样式
            levelTwo2: { "cat": "lay_wl2", "loc": "lay_ws lm", "ind": "", "comptype": "" },
            //二级类别一列显示的样式
            levelThree1: { "cat": "lay_wm", "loc": "lay_ws", "ind": "", "comptype": "" },
            //3级类别两列显示的样式
            levelThree2: { "cat": "lay_wl2", "loc": "lay_ws", "ind": "", "comptype": "" }
        },
        left: 0,
        top: 0,
        width: { "cat": 594, "loc": 466, "ind": 746, "comptype": 360 },
        height: { "cat": 517, "loc": 419, "ind": 510, "comptype": 173 },
        lineHeight: 20,
        topBottomMargin: 17,
        offset: {
            levelOne: { X: 0, Y: 20, "cat": { X: 0, Y: 20 }, "loc": { X: 250, Y: 20} },
            levelTwo: { "cat": { X: 304, Y: 0 }, "loc": { X: 130, Y: 0} }
        }
    },
    allItems: {
        cat: function() { return getCat(); },
        ind: function() { return getInd(); },
        loc: function() { return getLoc(); },
        comptype: function() { return getCompanyType(); }
    }
};

jQuery(document).ready(function() {
    jQuery("#txtLoc").click(function() { PopupSelector.popup("loc", this); });
    jQuery("#txtLocDrop").click(function() { PopupSelector.popup("loc", jQuery("#txtLoc")[0]); });

    popupLayer = $(".sech_layb");
    jQuery("#lnkEmpty").click(function() { PopupSelector.empty(); });
    jQuery("#lnkCancel").click(function() { PopupSelector.cancel(); });
    jQuery("#lnkOK").click(function() { PopupSelector.OK(); });
    jQuery("#btnOk").click(function() { PopupSelector.OK(); });
    jQuery("#btnOkLoc").click(function() { PopupSelector.OK(); });
    jQuery("#imgClose").click(function() { PopupSelector.close(); });

});

//字典资源
//********************************************************************

function getCompanyType() {
    return [
        { id: "10", name: "外商独资·外企办事处" },
        { id: "20", name: "中外合营(合资·合作)" },
        { id: "21", name: "股份制企业" },
        { id: "50", name: "国内上市公司" },
        { id: "30", name: "私营·民营企业" },
        { id: "40", name: "国有企业" },
        { id: "60", name: "政府机关/非营利机构" },
        { id: "70", name: "事业单位" }
    ];
}

function getCat() {
    return [
  
    ];
}

function getLoc() {
    return [
{
    id: "17000",
    name: "临床医学",
    subItems: [
		{
		    id: "50",
		    name: "内科学",
		    subItems: [
			{
			    id: "5001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "5001001",
					    name: "支气管哮喘"
					},
					{
					    id: "5001002",
					    name: "肺炎"
					},
					{
					    id: "5001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "5002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "5002001",
					    name: "胃食管反流病"
					},
					{
					    id: "5002002",
					    name: "急性胃炎"
					},
					{
					    id: "5002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "5003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "5003001",
					    name: "心力衰竭"
					},
					{
					    id: "5003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "5003003",
					    name: "硬化性心脏病"
					},
					{
					    id: "5003004",
					    name: "躯体因素的行为综合征"
					}
				]
			}
		]
		},
		
		{
		    id: "51",
		    name: "外科",
		    subItems: [
			{
			    id: "5101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "5101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "5101002",
					    name: "慢性淋巴细胞性甲状腺炎"
					},
					{
					    id: "5101003",
					    name: "胃、十二指肠溃疡的外科治疗"
					}
				]
			},
			{
			    id: "5102",
			    name: "神经外科",
			    subItems: [
					{
					    id: "5102001",
					    name: "颅内压增高"
					},
					{
					    id: "5102002",
					    name: "颅脑损伤"
					},
					{
					    id: "5102003",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "5103",
			    name: "骨科",
			    subItems: [
					{
					    id: "5103001",
					    name: "骨折概论"
					},
					{
					    id: "5103002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "5103003",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "52",
		    name: "妇产科",
		    subItems: [
			{
			    id: "5201",
			    name: "妇科",
			    subItems: [
					{
					    id: "5201001",
					    name: "妇科病史及检查"
					},
					{
					    id: "5201002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "5201003",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "5202",
			    name: "产科",
			    subItems: [
					{
					    id: "5202001",
					    name: "妊娠生理"
					},
					{
					    id: "5202002",
					    name: "妊娠诊断"
					},
					{
					    id: "5202003",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "5203",
			    name: "计划生育",
			    subItems: [
					{
					    id: "5203001",
					    name: "药物避孕"
					},
					{
					    id: "5203002",
					    name: "工具避孕"
					},
					{
					    id: "5203003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
},
{
    id: "16000",
    name: "基础医学",
    subItems: [
		{
		    id: "60",
		    name: "内科学",
		    subItems: [
			{
			    id: "6001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "6001001",
					    name: "支气管哮喘"
					},
					{
					    id: "6001002",
					    name: "肺炎"
					},
					{
					    id: "6001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "6002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "6002001",
					    name: "胃食管反流病"
					},
					{
					    id: "6002002",
					    name: "急性胃炎"
					},
					{
					    id: "6002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "6003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "6003001",
					    name: "心力衰竭"
					},
					{
					    id: "6003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "6003003",
					    name: ""
					},
					{
					    id: "6003004",
					    name: "躯体"
					}
				]
			}
		]
		},
		
		{
		    id: "61",
		    name: "外科",
		    subItems: [
			{
			    id: "6101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "6101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "6101002",
					    name: "慢性淋巴细胞性甲状腺炎"
					},
					{
					    id: "6101003",
					    name: "胃、十二指肠溃疡的外科治疗"
					}
				]
			},
			{
			    id: "6102",
			    name: "神经外科",
			    subItems: [
					{
					    id: "6102001",
					    name: "颅内压增高"
					},
					{
					    id: "6102002",
					    name: "颅脑损伤"
					},
					{
					    id: "6102003",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "6103",
			    name: "骨科",
			    subItems: [
					{
					    id: "6103001",
					    name: "骨折概论"
					},
					{
					    id: "6103002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "6103002",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "51",
		    name: "妇产科",
		    subItems: [
			{
			    id: "6001",
			    name: "妇科",
			    subItems: [
					{
					    id: "6001001",
					    name: "妇科病史及检查"
					},
					{
					    id: "6001002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "6001002",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "6002",
			    name: "产科",
			    subItems: [
					{
					    id: "6002001",
					    name: "妊娠生理"
					},
					{
					    id: "6002002",
					    name: "妊娠诊断"
					},
					{
					    id: "6002002",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "6103",
			    name: "计划生育",
			    subItems: [
					{
					    id: "6103001",
					    name: "药物避孕"
					},
					{
					    id: "6103002",
					    name: "工具避孕"
					},
					{
					    id: "6103003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
},
{
    id: "15000",
    name: "预防医学与卫生学",
    subItems: [
		{
		    id: "70",
		    name: "内科学",
		    subItems: [
			{
			    id: "7001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "7001001",
					    name: "支气管哮喘"
					},
					{
					    id: "7001002",
					    name: "肺炎"
					},
					{
					    id: "7001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "7002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "7002001",
					    name: "胃食管反流病"
					},
					{
					    id: "7002002",
					    name: "急性胃炎"
					},
					{
					    id: "7002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "7003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "7003001",
					    name: "心力衰竭"
					},
					{
					    id: "7003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "7003003",
					    name: "硬化病"
					},
					{
					    id: "7003004",
					    name: "综合征"
					}
				]
			}
		]
		},
		
		{
		    id: "71",
		    name: "外科",
		    subItems: [
			{
			    id: "7101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "7101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "7101002",
					    name: "慢性淋巴细胞性甲状腺炎"
					},
					{
					    id: "7101002",
					    name: "胃、十二指肠溃疡"
					}
				]
			},
			{
			    id: "7002",
			    name: "神经外科",
			    subItems: [
					{
					    id: "7002001",
					    name: "颅内压增高"
					},
					{
					    id: "7002002",
					    name: "颅脑损伤"
					},
					{
					    id: "7002002",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "7003",
			    name: "骨科",
			    subItems: [
					{
					    id: "7003001",
					    name: "骨折概论"
					},
					{
					    id: "7003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "7003002",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "51",
		    name: "妇产科",
		    subItems: [
			{
			    id: "7101",
			    name: "妇科",
			    subItems: [
					{
					    id: "7101001",
					    name: "妇科病史及检查"
					},
					{
					    id: "7101002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "7101003",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "7102",
			    name: "产科",
			    subItems: [
					{
					    id: "7102001",
					    name: "妊娠生理"
					},
					{
					    id: "7102002",
					    name: "妊娠诊断"
					},
					{
					    id: "7102003",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "7103",
			    name: "计划生育",
			    subItems: [
					{
					    id: "7103001",
					    name: "药物避孕"
					},
					{
					    id: "7103002",
					    name: "工具避孕"
					},
					{
					    id: "7103003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
},
{
    id: "14000",
    name: "药学",
    subItems: [
		{
		    id: "80",
		    name: "内科学",
		    subItems: [
			{
			    id: "8001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "8001001",
					    name: "支气管哮喘"
					},
					{
					    id: "8001002",
					    name: "肺炎"
					},
					{
					    id: "8001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "8002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "8002001",
					    name: "胃食管反流病"
					},
					{
					    id: "8002002",
					    name: "急性胃炎"
					},
					{
					    id: "8002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "8003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "8003001",
					    name: "心力衰竭"
					},
					{
					    id: "8003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "8003003",
					    name: "硬化性心脏病"
					},
					{
					    id: "8003004",
					    name: "综合征"
					}
				]
			}
		]
		},
		
		{
		    id: "81",
		    name: "外科",
		    subItems: [
			{
			    id: "8101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "8101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "8101002",
					    name: "慢性淋巴细胞性"
					},
					{
					    id: "8101002",
					    name: "胃、十二指肠溃疡的外科治疗"
					}
				]
			},
			{
			    id: "8002",
			    name: "神经外科",
			    subItems: [
					{
					    id: "8002001",
					    name: "颅内压增高"
					},
					{
					    id: "8002002",
					    name: "颅脑损伤"
					},
					{
					    id: "8002002",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "8003",
			    name: "骨科",
			    subItems: [
					{
					    id: "8003001",
					    name: "骨折概论"
					},
					{
					    id: "8003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "8003002",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "51",
		    name: "妇产科",
		    subItems: [
			{
			    id: "8101",
			    name: "妇科",
			    subItems: [
					{
					    id: "8101001",
					    name: "妇科病史及检查"
					},
					{
					    id: "8101002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "8101003",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "8102",
			    name: "产科",
			    subItems: [
					{
					    id: "8102001",
					    name: "妊娠生理"
					},
					{
					    id: "8102002",
					    name: "妊娠诊断"
					},
					{
					    id: "8102003",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "8103",
			    name: "计划生育",
			    subItems: [
					{
					    id: "8103001",
					    name: "药物避孕"
					},
					{
					    id: "8103002",
					    name: "工具避孕"
					},
					{
					    id: "8103003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
},
{
    id: "13000",
    name: "中医学与中药学",
    subItems: [
		{
		    id: "90",
		    name: "内科学",
		    subItems: [
			{
			    id: "9001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "9001001",
					    name: "支气管哮喘"
					},
					{
					    id: "9001002",
					    name: "肺炎"
					},
					{
					    id: "9001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "9002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "9002001",
					    name: "胃食管反流病"
					},
					{
					    id: "9002002",
					    name: "急性胃炎"
					},
					{
					    id: "9002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "9003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "9003001",
					    name: "心力衰竭"
					},
					{
					    id: "9003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "9003003",
					    name: "硬化性心脏病"
					},
					{
					    id: "9003004",
					    name: "为综合征"
					}
				]
			}
		]
		},
		
		{
		    id: "91",
		    name: "外科",
		    subItems: [
			{
			    id: "9101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "9101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "9101002",
					    name: "慢性淋巴细胞性甲状腺炎"
					},
					{
					    id: "9101003",
					    name: "胃、十二指肠溃疡"
					}
				]
			},
			{
			    id: "9102",
			    name: "神经外科",
			    subItems: [
					{
					    id: "9102001",
					    name: "颅内压增高"
					},
					{
					    id: "9102002",
					    name: "颅脑损伤"
					},
					{
					    id: "9102003",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "9103",
			    name: "骨科",
			    subItems: [
					{
					    id: "9103001",
					    name: "骨折概论"
					},
					{
					    id: "9103002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "9103003",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "92",
		    name: "妇产科",
		    subItems: [
			{
			    id: "9201",
			    name: "妇科",
			    subItems: [
					{
					    id: "9201001",
					    name: "妇科病史及检查"
					},
					{
					    id: "9201002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "9201003",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "9202",
			    name: "产科",
			    subItems: [
					{
					    id: "9202001",
					    name: "妊娠生理"
					},
					{
					    id: "9202002",
					    name: "妊娠诊断"
					},
					{
					    id: "9202003",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "9203",
			    name: "计划生育",
			    subItems: [
					{
					    id: "9203001",
					    name: "药物避孕"
					},
					{
					    id: "9203002",
					    name: "工具避孕"
					},
					{
					    id: "9203003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
},
{
    id: "12000",
    name: "军事医学与特种医学",
    subItems: [
		{
		    id: "40",
		    name: "内科学",
		    subItems: [
			{
			    id: "4001",
			    name: "呼吸内科",
			    subItems: [
					{
					    id: "4001001",
					    name: "支气管哮喘"
					},
					{
					    id: "4001002",
					    name: "肺炎"
					},
					{
					    id: "4001003",
					    name: "肺结核"
					}
				]
			},
			{
			    id: "4002",
			    name: "消化内科",
			    subItems: [
					{
					    id: "4002001",
					    name: "胃食管反流病"
					},
					{
					    id: "4002002",
					    name: "急性胃炎"
					},
					{
					    id: "4002003",
					    name: "胰腺炎"
					}
				]
			},
			{
			    id: "4003",
			    name: "神经内科",
			    subItems: [
					{
					    id: "4003001",
					    name: "心力衰竭"
					},
					{
					    id: "4003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "4003003",
					    name: "硬化性心脏病"
					},
					{
					    id: "4003004",
					    name: "躯体因素的行为综合征"
					}
				]
			}
		]
		},
		
		{
		    id: "41",
		    name: "外科",
		    subItems: [
			{
			    id: "4101",
			    name: "普通外科",
			    subItems: [
					{
					    id: "4101001",
					    name: "甲状腺腺瘤"
					},
					{
					    id: "4101002",
					    name: "慢性淋巴细胞性甲状腺炎"
					},
					{
					    id: "4101002",
					    name: "胃、十二指肠溃疡的外科治疗"
					}
				]
			},
			{
			    id: "4002",
			    name: "神经外科",
			    subItems: [
					{
					    id: "4002001",
					    name: "颅内压增高"
					},
					{
					    id: "4002002",
					    name: "颅脑损伤"
					},
					{
					    id: "4002002",
					    name: "脊髓疾病"
					}
				]
			},
			{
			    id: "4003",
			    name: "骨科",
			    subItems: [
					{
					    id: "4003001",
					    name: "骨折概论"
					},
					{
					    id: "4003002",
					    name: "心脏瓣膜病"
					},
					{
					    id: "4003002",
					    name: "骨与关节化脓性感染"
					}
				]
			}
			]
		},
		
		{
		    id: "51",
		    name: "妇产科",
		    subItems: [
			{
			    id: "4101",
			    name: "妇科",
			    subItems: [
					{
					    id: "4101001",
					    name: "妇科病史及检查"
					},
					{
					    id: "4101002",
					    name: "女性生殖系统生理"
					},
					{
					    id: "4101003",
					    name: "女性生殖系统解剖"
					}
				]
			},
			{
			    id: "4102",
			    name: "产科",
			    subItems: [
					{
					    id: "4102001",
					    name: "妊娠生理"
					},
					{
					    id: "4102002",
					    name: "妊娠诊断"
					},
					{
					    id: "4102003",
					    name: "孕期监护与保健"
					}
				]
			},
			{
			    id: "4103",
			    name: "计划生育",
			    subItems: [
					{
					    id: "4103001",
					    name: "药物避孕"
					},
					{
					    id: "4103002",
					    name: "工具避孕"
					},
					{
					    id: "4103003",
					    name: "其他避孕"
					}
				]
			}
			]
		}
	]
}
    ];
}

function getInd() {
    return [
        
    ];
}