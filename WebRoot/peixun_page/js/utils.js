//返回字符串长度，包含中英文
String.prototype.allLength = function(){return this.replace(/[^\x00-\xff]/g,"01").length;}

//YHQ 2017-03-13
function HashTable() {
    var size = 0;
    var entry = new Object();
    this.add = function (key, value) {
        if (!this.containsKey(key)) {
            size++;
        }
        entry[key] = value;
    }
    this.getValue = function (key) {
        return this.containsKey(key) ? entry[key] : null;
    }
    this.remove = function (key) {
        if (this.containsKey(key) && (delete entry[key])) {
            size--;
        }
    }
    this.containsKey = function (key) {
        return (key in entry);
    }
    this.containsValue = function (value) {
        for (var prop in entry) {
            if (entry[prop] == value) {
                return true;
            }
        }
        return false;
    }
    this.getValues = function () {
        var values = new Array();
        for (var prop in entry) {
            values.push(entry[prop]);
        }
        return values;
    }
    this.getKeys = function () {
        var keys = new Array();
        for (var prop in entry) {
            keys.push(prop);
        }
        return keys;
    }
    this.getSize = function () {
        return size;
    }
    this.clear = function () {
        size = 0;
        entry = new Object();
    }
}

//分页js
//chenlb add
function gotoPage_displaytag(pageFlag) {

    var T = /^[1-9]\d*$/;
    var pageNum = document.getElementById("gotoPageNumber_displaytag").value;
    if (!T.test(pageNum)) {
    	alert("请输入数字！");
    	document.getElementById("gotoPageNumber_displaytag").value="";
        return;
    }
    
    var pageCount = document.getElementById("pageCount").value;
    
    if(pageCount != ''){
    	pageCount=pageCount.replace(/,/, "");
	    if(parseInt(pageNum)>parseInt(pageCount)){
	    	alert("您输入的页数不能大于最大页数！");
	    	document.getElementById("pageCount").value = "";
	    	return;
	    }
    }
    // 定义url的缓存变量
    var tempUrl;
    //用于截取
    var tempUrl2;
    if (pageFlag == 0) {
        // paging.banner.full
        // paging.banner.first
        url = document.getElementById("lastPageUrl_displaytag").value;
        tempUrl = document.getElementById("lastPageUrl_displaytag").value;
        tempUrl2 = document.getElementById("lastPageUrl_displaytag").value;
    } else {
        // pageFlag == 1
        // paging.banner.last
        url = document.getElementById("prevPageUrl_displaytag").value;
        tempUrl = document.getElementById("prevPageUrl_displaytag").value;
        tempUrl2 = document.getElementById("prevPageUrl_displaytag").value;
    }
    //需要匹配的变量（displayTag默认的分页变量）的正则表达式
    var pageReg = /d-[\d]+-p/;
    var replacedContent = pageReg.exec(tempUrl);
    //如果匹配到变量，那么就是displayTag分页默认的分页参数，那么给该参数赋值
    if(replacedContent){
    	url = url.substring(0,url.indexOf(replacedContent[0]));
    	//处理有查询条件的分页情况。
    	arr1=tempUrl2.slice(tempUrl2.indexOf(replacedContent[0])+1).split('&');
    	for (var i = 1; i < arr1.length; i++) {
    		url += "&" + arr1[i];
    	}
    	url += "&";
    	url += replacedContent[0];
    	url += "=";
    	url += pageNum;
    	url += "&";
    }
    
    if(url.indexOf("page=")==-1){
    	url = url + "page=1";
    }
    var url1 = url.substring(0,url.indexOf("page=")+5);
    var params = "";
    if(url.indexOf("&")>-1){
    	params = url.substring(url.indexOf("&"));
    }
    window.location = url1+pageNum+params;
}
