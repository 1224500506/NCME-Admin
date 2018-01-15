

/**
* 简单验证组件 util.js
* @author: guojin 
* @date: 2010-9-8
* @version: 1.0
* @copyright(C) all rights reserved
*/
/**
*trim()属性方法
*/
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function () {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function () {
	return this.replace(/(\s*$)/g, "");
};

/**
 *过滤html页面 
 */
function filterChart(str){
	  if(str != '' && !/^[^\<>~!`@#$%&*(),.?{};'"+/|]*$/.test(str)){  
          //包含<>返回false  
          alert("只支持汉字、英文字母和数字，请输入合法的名称");
          return false;
        } else{
        	return true;
        } 
}
//清空查询表单中所有的输入条件 
/*
调用例子:queryFm为form表单ID 
	<button type="button" onclick="clearForm('queryForm');return false;" class="mr1em">清空</button>
*/
function clearForm(form)
	{
    var formObj = document.getElementsByName(form)[0];
    
    if(formObj == undefined)
    {
        return;
    }
    for(var i=0; i<formObj.elements.length; i++)
    {
        if(formObj.elements[i].type == "text")
        {
            formObj.elements[i].value = "";
        }
        else if(formObj.elements[i].type == "password")
        {
            formObj.elements[i].value = "";
        }
        else if(formObj.elements[i].type == "radio")
        {
            formObj.elements[i].checked = false;
        }
        else if(formObj.elements[i].type == "checkbox")
        {
            formObj.elements[i].checked = false;
        }
        else if(formObj.elements[i].type == "select-one")
        {
            formObj.elements[i].options[0].selected = true;
        }
        else if(formObj.elements[i].type == "select-multiple")
        {    
            for(var j = 0; j < formObj.elements[i].options.length; j++)
            {
                formObj.elements[i].options[j].selected = false;
            }
        }
        else if(formObj.elements[i].type == "file")
        {
            //formObj.elements[i].select();
            //document.selection.clear();             
            // for IE, Opera, Safari, Chrome
            var file = formObj.elements[i];
             if (file.outerHTML) {
                 file.outerHTML = file.outerHTML;
             } else {
                 file.value = "";  // FF(包括3.5)
            }
        }
        else if(formObj.elements[i].type == "textarea")
        {
            formObj.elements[i].value = "";
        }
	}
	}

/**
* 去掉表单中所有文本框值的空格
* param: fName formName
*/
function FormTextTrim(fName) {
//input text
var inputs = document.forms[fName].getElementsByTagName("input");
for(var i=0;i<inputs.length;i++)
{
   var inType = inputs[i].type;
   if(inType == "text"){//去掉所有text类型左右两端的空格
      inputs[i].value = inputs[i].value.replace(/\'/gi, '');
      inputs[i].value = inputs[i].value.trim();
   }
}

//textarea
var textareas = document.forms[fName].getElementsByTagName("textarea");
for(var i=0;i<textareas.length;i++)
{
      textareas[i].value = textareas[i].value.replace(/\'/gi, '');
      textareas[i].value = textareas[i].value.trim();
}
}

/*  
 * 日期格式化
 * eg:format="YYYY-MM-dd hh:mm:ss";  
 */  
Date.prototype.format = function(format){   
var o = {   
 "M+" :  this.getMonth()+1,  //month   
 "d+" :  this.getDate(),     //day   
 "h+" :  this.getHours(),    //hour   
     "m+" :  this.getMinutes(),  //minute   
      "s+" :  this.getSeconds(), //second   
      "q+" :  Math.floor((this.getMonth()+3)/3),  //quarter   
      "S"  :  this.getMilliseconds() //millisecond   
   }   
     
   if(/(y+)/.test(format)) {   
    format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
   }   
    
   for(var k in o) {   
    if(new RegExp("("+ k +")").test(format)) {   
      format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));   
    }   
   }   
 return format;   
}  

/**
* 全选/反选 
* 实例:<input type="checkbox" name="sel_all" id="orgId" value="checkbox" onclick="select_all(this)" />全选'><input type="checkbox" name="orgId" value="checkbox" value="11"/
*/
function select_all(obj) {
	var chkName = obj.id;
	var names = document.getElementsByName(chkName);
	var len = names.length;
	obj.checked=!obj.checked;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++) {
			if (names[i].checked) {
				names[i].checked = false;
			} else {
				names[i].checked = true;
			}
		}
	}
}

//检测是否有选择项 
function hadChk(chkName) {
	var names = document.getElementsByName(chkName);
	for (var i = 0, len = names.length; i < len; i++) {
		if (names[i].checked) {
			return true;
		}
	}
	return false;
}
/*拼字符串,eg:"1,2,"
* 其中:chkName为复选框name,arrStrName为页面赋值name
*
*/
function spellArrStr(chkName, arrStrName) {
	var arrStrValue = "";
	var names = document.getElementsByName(chkName);
	for (var i = 0, len = names.length; i < len; i++) {
		if (names[i].checked) {
			arrStrValue += names[i].value + ",";
		}
	}
	if (document.getElementById(arrStrName)) {
		document.getElementById(arrStrName).value = arrStrValue.substring(0,arrStrValue.length-1);
	}
}

//回车转入传入ID元素焦点
function getfocus(tmpstr) {
	if (event.keyCode == 13) {
		var tmpEle = document.getElementById(tmpstr);
		if (tmpEle == null) {
			tmpEle = eval(tmpstr);
		}
		tmpEle.focus();
		event.returnValue = false;
	}
}
//判断年份是否是闰年
function isLeapYear(year) {
	if (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0))) {
		return true;
	}
	return false;
}

//校验输入条件至少有一项_输入框及列表
/*
调用例子:queryFm为form表单ID,input为tagName
	if (!chkTagName('queryFm','input')){
		alert('由于数据量大,为提高查询效率,请至少输入一项查询条件!');
		return;
	}

*/
function chkTagName(divName, tagName) {
	var tab = document.getElementById(divName).getElementsByTagName(tagName);  //input,select,textarea等
	for (var j = 0, len = tab.length; j < len; j++) {
		  	//输入框
		if ("input" == tagName && tab[j].type == "text" && "" != tab[j].value.trim()) {
			return true;
		}
		    //列表
		if ("input" != tagName && "" != tab[j].value.trim()) {
			return true;
		}
	}
	return false;
}

/*
*
*string:原始字符串
*substr:子字符串
*isIgnoreCase:忽略大小写
*/
function contains(string,substr,isIgnoreCase)
{
    if(isIgnoreCase)
    {
     string=string.toLowerCase();
     substr=substr.toLowerCase();
    }
     var startChar=substr.substring(0,1);
     var strLen=substr.length;
         for(var j=0;j<string.length-strLen+1;j++)
         {
             if(string.charAt(j)==startChar)//如果匹配起始字符,开始查找
             {
                   if(string.substring(j,j+strLen)==substr)//如果从j开始的字符与str匹配，那ok
                   {
                         return true;
                   }   
             }
         }
         return false;
}

//getObjById
function $(id){
	return document.getElementById(id);
}   
//getObjsByName
function $Name(name){
	return document.getElementsByName(name);
}
/** 功能：常用下拉列表
  *　
  *　调用例子：
  	<div id="nation">民族：</div>   
	<div id="shengxiao">生肖：</div>   
	<div id="degree">学位：</div>   
	<div id="self">自定义：</div>
    $("nation").appendChild(createSelect({type : "nation", selected : "汉族",name: "nation"}));
    $("shengxiao").appendChild(createSelect({type : "shengxiao", selected : "虎", id : "shengxiao"}));   
	$("degree").appendChild(createSelect({type : "degree", selected : "本科", name : "degree"}));   
	$("self").appendChild(createSelect({array : ["你","我","他"], selected : "他"}));
  *
*/ 
function createSelect(c){   
    var _inner = {   
        "nation" : ['汉族','蒙古族','彝族','侗族','哈萨克族',    
            '畲族','纳西族','仫佬族','仡佬族','怒族','保安族',    
            '鄂伦春族','回族','壮族','瑶族','傣族','高山族',    
            '景颇族','羌族','锡伯族','乌孜别克族','裕固族','赫哲族',    
            '藏族','布依族','白族','黎族','拉祜族','柯尔克孜族','布朗族',    
            '阿昌族','俄罗斯族','京族','门巴族','维吾尔族','朝鲜族',    
            '土家族','傈僳族','水族','土族','撒拉族','普米族','鄂温克族',    
            '塔塔尔族','珞巴族','苗族','满族','哈尼族','佤族','东乡族',    
            '达斡尔族','毛南族','塔吉克族','德昂族','独龙族','基诺族'],   
        "shengxiao" : ['鼠','牛','虎','兔','蛇','蛇','马','羊','猴','鸡','狗','猪'],   
        "degree" : ['中专','大专','本科','硕士','博士']   
    }   
    var _array = c["array"] || _inner[c["type"]];   
    var _select = document.createElement("select");  
    _select.options[0] = new Option('-请选择-', ''); 
    for(var i=1,len=_array.length; i < len+1; i++){   
    	var j=i-1;//
        _select.options[i] = new Option(_array[j], _array[j]);   
        _array[j] == c["selected"] && (_select.options[i].selected = true);   //选中项
    }   
    c["id"] && (_select.id = c["id"]);   
    c["name"] && (_select.name = c["name"]);   
    c["onchange"] && (_select.onchange = c["onchange"]);   
    return _select;   
}   

//判断是否为数字
function IsNum(s)
{
    if (s!=null && s!="")
    {
        return !isNaN(s);
    }
    return false;
}

//email验证
function isEmail(emailStr){
	var emailPat=/^(.+)@(.+)$/;
	var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
	var validChars="\[^\\s" + specialChars + "\]";
	var quotedUser="(\"[^\"]*\")";
	var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
	var atom=validChars + '+';
	var word="(" + atom + "|" + quotedUser + ")";
	var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
	var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
	var matchArray=emailStr.match(emailPat);
	if (matchArray == null) {
	   return false;
	}
	var user=matchArray[1];
	var domain=matchArray[2];
	if (user.match(userPat) == null) {
	   return false;
	}
	var IPArray = domain.match(ipDomainPat);
	if (IPArray != null) {
	   for (var i = 1; i <= 4; i++) {
		  if (IPArray[i] > 255) {
			 return false;
		  }
	   }
	   return true;
	}
	var domainArray=domain.match(domainPat);
	if (domainArray == null) {
	   return false;
	}
	var atomPat=new RegExp(atom,"g");
	var domArr=domain.match(atomPat);
	var len=domArr.length;
	if ((domArr[domArr.length-1].length < 2) ||
	   (domArr[domArr.length-1].length > 3)) {
	   return false;
	}
	if (len < 2) {
	   return false;
	}
	return true;
}

var ADKPasswordStrength =
{
   Level : ["高","中","弱"],
   LevelValue : [30,20,0],//强度值
   Factor : [1,2,5],//字符加数,分别为字母，数字，其它
   KindFactor : [0,0,10,20],//密码含几种组成的加数
   Regex : [/[a-zA-Z]/g,/\d/g,/[^a-zA-Z0-9]/g] //字符正则数字正则其它正则
}
ADKPasswordStrength.StrengthValue=function(pwd)
{
   var strengthValue = 0;
   var ComposedKind = 0;
   for(var i = 0 ; i < this.Regex.length;i++)
   {
       var chars = pwd.match(this.Regex[i]);
        if(chars != null)
        {
                strengthValue += chars.length * this.Factor[i];
                ComposedKind ++;
         }
     }
    strengthValue += this.KindFactor[ComposedKind];
    return strengthValue;
} 
    
ADKPasswordStrength.StrengthLevel = function(pwd)
{
   var value = this.StrengthValue(pwd);
    for(var i = 0 ; i < this.LevelValue.length ; i ++)
    {
        if(value >= this.LevelValue[i] )
        return this.Level[i];
    }
}

ADKPasswordStrength.Check=function(iobj,sobj)
{
    var inputobj,showobj;
    
    inputobj=typeof(iobj)=="string"?document.getElementById(iobj):iobj;
    showobj=typeof(sobj)=="string"?document.getElementById(sobj):sobj;//提示内容

    if(!showobj || !inputobj)
        return;

    var lv=ADKPasswordStrength.StrengthLevel(inputobj.value);
    if(lv=="高")
    {
        showobj.innerHTML="高";
        showobj.className="ADKPasswordStrength_height"
    }
    else if(lv=="中")
    {
        showobj.innerHTML="中";
        showobj.className="ADKPasswordStrength_middle"
    }
    else if(lv=="弱")
    {
        showobj.innerHTML="弱";
        showobj.className="ADKPasswordStrength_low"
    }
}
/**
  *统计页面某列合计值
  *
  */
function sumByVarName(name){
    var arr = $Name(name);
	if (!arr || arr.length<1) return 0;
	
	var total = 0;
	for (var i=0,len=arr.length;i<len;i++){
		total += parseFloat(arr[i].value);
	}
	return total; 
} 


 /* 隐藏查询条件*/
 function toggleSearchUI(divflag){
  if (divflag.checked){
     searchUI.style.display = "none";
   }else{
     searchUI.style.display = "block";
   }
 }

/*TOP页面总菜单*/

function forwa(lefturl,righturl,vid){
  parent.mainFrame.location=righturl;
  parent.leftFrame.location=lefturl;
  changeBg(vid);
}



function changeBg(vid){
	initBackground();
	/*document.getElementById(vid).background="../images/menubg_01.jpg";*/
}



function initBackground(){
  var xx=19;
  for(var i=1;i<xx;i++){
  	 try{
  	 	var obj = document.getElementById(i).background=".";
  	 }catch(E){}
  }
}



function ChagetoolBg(td,pic){
	if (!pic) pic="../images/tools_bg.jpg";
	if (td.style.backgroundImage!=""){
		td.style.backgroundImage ="";
	}
	else
	{ 
		td.style.backgroundImage ="url("+pic+")";
	}
}

/*TOP页面二级单*/

function forSec(target,targeturl,vid){
	target.location=targeturl;
  changeSecBg(vid);
}
function changeSecBg(vid){
	initSecBackground();
	//document.getElementById(vid).background="../images/menubg_01.jpg";
}
function swtichSecbg(td,pic){
	if (!pic) pic="../images/menubg01.gif";
	if (td.style.backgroundImage!=""){
		td.style.backgroundImage ="";
	}
	else
	{ 
		td.style.backgroundImage ="url("+pic+")";
	}
}
function initSecBackground(){
  var mm=110;
  var xx=120;
  for(var i=mm;i<xx;i++){
  	 try{
  	 	var obj = document.getElementById(i).background="../images/banner_admin.gif";
  	 }catch(E){}
  }
}








/*********控制主菜单每次显示个数及左右拉动************/
  	var initTds=5;//最初显示多少列
    var starttd=0;//开始隐藏的列 位置数
    var endtd=initTds;//结束隐藏的列 位置数
 	/*
 	根据table的id，拖动的长度，动作(左，右),来显示table的内容
 	*/
  function movetd(tabid,len,act) {
    var tbl  = document.getElementById(tabid);
    var rows = tbl.getElementsByTagName('tr');
    for (var row=0; row<rows.length;row++) {
      var cels = rows[row].getElementsByTagName('td');
      if(act=='left'){
				if((starttd-len)<=0){
					starttd=0;
				}else{
					starttd=starttd-len;
				}
			}else{
				if(!((starttd+len)>=cels.length)){
					starttd=endtd;
				}
			}
			endtd=starttd+len;
			
			
			if(act=='left'){
				endtd=starttd+initTds
			}else{
				if(endtd>cels.length){
					endtd=cels.length;
				}
				starttd=endtd-initTds;
				
			}
			if(starttd<0){
				starttd=0;
				endtd=initTds;
			}
			
      for(var cel=0;cel<cels.length;cel++){
      	if(cel>=starttd&&cel<endtd){
      		cels[cel].style.display='';
      	}else{
      		cels[cel].style.display='none';
      	}
      }
    }
  }
  
  function changeTabCels(tabid,len) {
  	endtd=len;
  	initTds=len;//重新初始化现实列数
    var tbl  = document.getElementById(tabid);
    if (!tbl) return;
    var rows = tbl.getElementsByTagName('tr');
    for (var row=0; row<rows.length;row++) {
      var cels = rows[row].getElementsByTagName('td');
      for(var cel=0;cel<cels.length;cel++){
      	if(cel<initTds){
      		cels[cel].style.display='';
      	}else{
      		cels[cel].style.display='none';
      	}
      }
    }
  }  
/*
document.getElementById("columnLeft").style.height=document.getElementById("columnRight").scrollHeight+"px";
*/
 /*只显示页面内容，去除菜单*/
function initFrameset(){
		parent.bottomFrameset.cols = "200,*";
		return false;
}

 /*只显示页面内容，去除菜单*/
function onlyMain(){
		parent.bottomFrameset.cols = "0,*";
		return false;
}

 function viewClient(){
  location.href=" ../ClientManagement/EnterpriseMainUpdate.html";
 }

  function viewProject(){
    alert('进入项目信息')
	return;
 }

 function uploadProjectFile(){
  location.href=" ../DocumentManagement/addDocument.html";
 }

 function viewProjectFile(){
  location.href=" ../DocumentManagement/DocumentTypeManagement.html";

 }
 function viewProjectLog(){
   alert("进入项目日志");
 }

 function draftContract(){
   alert("进入合同模板界面");
 }

 function uploadFinanceReport(){
   alert("进入财务报表");
 }

 function viewTaskResource(){
  alert("查看人员项目分配情况");
 }

 function enterProjectBill(){
	 location.href="../AccountManagement/AddIncome.html";
 }

 function viewCounterG(){
	 location.href="../beforeGuaranty/counterGuaranty.html";
 }

 function registerAsset(){
	 alert("登记资产评估");
 }
function creditEvaluation(){
  location.href=" ../riskManage/creditAppraisal/addCreditAppraisal.html";
}

function viewPreStep(){
	alert("查看上一步骤工作记录");
}

function enactReport(type){
	if (type==1){
		alert("开具《担保受理通知书》");
	}else
	if (type==2){
		alert("开具《担保不予受理通知书》");
	} else
    if (type==3){
		alert("开具《担保意向书》");
	}else
    if (type==4){
		alert("开具《担保通知书》");
	}
	
}

function toggleMeetUI(){
   var meetAStyle = document.getElementById('meetA');
   var meetBStyle = document.getElementById('meetB');
   var meetCStyle = document.getElementById('meetC'); 
   if (meetAStyle.checked) {
     document.getElementById('meetDate').style.display = "none";
	 document.getElementById('meetMember').style.display = "none";
	 document.getElementById('meetDirector').style.display = "none";
	 document.getElementById('meetAddr').style.display = "none";
   } else
    if (meetBStyle.checked) {
     document.getElementById('meetDate').style.display = "block";
	 document.getElementById('meetMember').style.display = "block";
	 document.getElementById('meetDirector').style.display = "block";
	 document.getElementById('meetAddr').style.display = "none";
   } else
    if (meetCStyle.checked) {
     document.getElementById('meetDate').style.display = "block";
	 document.getElementById('meetMember').style.display = "block";
	 document.getElementById('meetDirector').style.display = "block";
	 document.getElementById('meetAddr').style.display = "block";
   } 
 }
 
 
 function delete_run(RUN_ID)
{
  msg="确认要删除该条记录么？";
  if(alert(msg))
  {
    URL="delete.php?RUN_ID_STR="+RUN_ID;
    del_request(URL,RUN_ID);
  }
}

 function delete_run01(RUN_ID)
{
  msg="你无权限操作此功能！？";
  if(alert(msg))
  {
    URL="delete.php?RUN_ID_STR="+RUN_ID;
    del_request(URL,RUN_ID);
  }
}
/****  *****************/
/****  ****弹出操作类信息页面**********/
function list_query()
{
	window.open("list_queryInfo.html","newswindow","width=450 ,height=400,top=150,left=200" );
	}
function particular_query()
{
	window.open("list_statInfo.html","newswindow","width=630 ,height=230,top=150,left=200" );
	}
	
function shipmentInfo_query()
{
	window.open("list_shipmentInfo.html","newswindow","width=630 ,height=230,top=150,left=200" );
	}
function shipmentInfo01_query()
{
	window.open("shipmentInfo01_query.html","newswindow","width=630 ,height=230,top=150,left=200" );
	}
function repertoryInfo_query()
{
	window.open("list_repertoryInfo.html","newswindow","width=630 ,height=200,top=150,left=200" );
	}
function Info_01()
{
	window.open("list_Info_01.html","newswindow","width=630 ,height=200,top=150,left=200" );
	}
function Info_02 ()
	{
		window.open("list_Info_02.html","newswindow","width=630 ,height=200,top=150,left=200" );
		}
function bas()
{
	window.open("bas.html","newswindow","width=630 ,height=250,top=150,left=200" );
	}
	
function FixWidth(selectObj)
{
	//IE9下不用设置
	if(navigator.appVersion.indexOf("MSIE 9.0")>-1){
		return;
	}
    var newSelectObj = document.createElement("select");
    newSelectObj = selectObj.cloneNode(true);
    newSelectObj.selectedIndex = selectObj.selectedIndex;
    newSelectObj.onmouseover = null;
    
    var e = selectObj;
    var absTop = e.offsetTop;
    var absLeft = e.offsetLeft;
    while(e = e.offsetParent)
    {
        absTop += e.offsetTop;
        absLeft += e.offsetLeft;
    }
    with (newSelectObj.style)
    {
        position = "absolute";
        top = absTop + "px";
        left = absLeft + "px";
        width = "auto";
    }
    
    var rollback = function(){ RollbackWidth(selectObj, newSelectObj); };
    if(window.addEventListener)
    {
        newSelectObj.addEventListener("blur", rollback, false);
        newSelectObj.addEventListener("change", rollback, false);
    }
    else
    {
        newSelectObj.attachEvent("onblur", rollback);
        newSelectObj.attachEvent("onchange", rollback);
    }
    
    selectObj.style.visibility = "hidden";
    document.body.appendChild(newSelectObj);
    newSelectObj.focus();
}

function RollbackWidth(selectObj, newSelectObj)
{
    selectObj.selectedIndex = newSelectObj.selectedIndex;
    selectObj.style.visibility = "visible";
    document.body.removeChild(newSelectObj);
}
//select下拉框内容过长,进行换行操作
function opts(selectObj) {
    var optDivs = document.createElement("div");
    var objTable = document.createElement("table");
    var objTbody = document.createElement("tbody");
    optDivs.style.zIndex = "100";
    objTable.style.zIndex = "100";
    objTable.width = selectObj.style.width;

    //确定div的位置刚好在selectObj下
    var e = selectObj;
    var absTop = e.offsetTop;
    var absLeft = e.offsetLeft;
    var absWidth = e.offsetWidth;
    var absHeight = e.offsetHeight;

    while (e = e.offsetParent) {
        absTop += e.offsetTop;
        absLeft += e.offsetLeft;
    }
    with(objTable.style) {
        position = "absolute";
        top = (absTop + absHeight) + "px";
        left = absLeft + "px";
        border = "1px solid black";
        tableLayout = "fixed"; //定长,不想要就注释掉
        wordBreak = "break-all"; //多行,不想要就注释掉
    }

    var options = selectObj.options;
    var val = selectObj.value;
    if (options.length > 0) {
        for (var i = 0; i < options.length; i++) {
            var newOptDiv = document.createElement("td");
            var objRow = document.createElement("tr");
            newOptDiv.name = options[i].value;
            newOptDiv.innerText = options[i].innerText;
            newOptDiv.title = options[i].title;
            //newOptDiv.onclick=function() {choose(selectObj,val,optDivs)};
            newOptDiv.onmouseout = function() {
                this.className = 'mouseOut';
                val = selectObj.value
            };
            newOptDiv.onmouseover = function() {
                this.className = 'mouseOver';
                val = this.name;
            };
            newOptDiv.className = "juzhongStyle";
            //newOptDiv.style.width=selectObj.style.width;
            newOptDiv.style.width = "500px";

            objRow.appendChild(newOptDiv);
            objTbody.appendChild(objRow);
        }
    }
    objTbody.appendChild(objRow);
    objTable.appendChild(objTbody);
    optDivs.appendChild(objTable);
    document.body.appendChild(optDivs);
    //用ifram覆盖下面的select
    var IfrRef = document.createElement("iframe");
    IfrRef.style.position = "absolute";
    //IfrRef.style.width = objTable.offsetWidth;
    IfrRef.style.width = "506";
    IfrRef.style.height = objTable.offsetHeight;
    IfrRef.style.top = objTable.style.top;
    IfrRef.style.left = objTable.style.left;
    IfrRef.style.zIndex = optDivs.style.zIndex - 1;
    IfrRef.style.display = "block";
    document.body.appendChild(IfrRef);

    objTable.focus();
    objTable.onblur = function() {
        choose(selectObj, val, optDivs, IfrRef)
    };
}
function choose(objselect, val, delobj, delobj2) {
    objselect.value = val;
    document.body.removeChild(delobj);
    document.body.removeChild(delobj2);
}

//验证时间控件,只允许选择和复制正确的日期格式.
function upperCase(id){
	var dateValue = document.getElementById(id).value;
	var reg=/^(\d{4})([-])(\d{2})([-])(\d{2})/;
	if(!reg.test(dateValue)){
        $.messager.alert('录入提示','日期格式不正确!正确格式为:2012-01-01,请重新选择日期。');
        document.getElementById(id).value = ""; 
    }
}
