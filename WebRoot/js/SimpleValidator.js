String.prototype.replaceAll = function(search, replace){  
	var regex = new RegExp(search, "g");  
	return this.replace(regex, replace);  
}  

function forback()
{
	if(window.history.length==0)
	{
		window.close();
	}
	else
	{
		window.history.back();
	}
}
// 选中试题类型
function defaultsel(key){
	var o = $A(document.fm1.question_label_id.options).detect(function(obj){
		return obj.value == key;
	});
	if(o != null){
		o.selected=true;
	}
}

// 根据试题类型跳转到相应页
function showType(labelId){
	window.location.href= ctxJS + "/questionManage/swapQuestionLabel.do?questionLabel="+labelId;
}

// 高级编辑
function dialog(vurl,w,h,obj){
	var v = window.showModalDialog(ctxJS + "/page/" + vurl+'?tx='+obj,window,'dialogWidth:'+w+'px;dialogHeight:'+h+'px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
	if(v!=null){
		document.getElementById(obj).value=v;
	}
}


var ChildWin = Class.create();
ChildWin.prototype = {
	initialize: function(label) {
		this.label = label;
		this.dhtmlxWins = new dhtmlXWindows();
		this.win = this.dhtmlxWins.createWindow("w1", 20, 20, 600, 400);
		switch(label){
			case 6:this.win.attachURL(ctxJS+"/page/add_child_question.jsp"); break;
			case 3:this.win.attachURL(ctxJS+"/page/add_child_questions.jsp"); break;
		}
		this.win.hide();
	},
	show : function (){
		this.win.show();
		return true;
	},
	hide : function (){
		this.win.hide();
	},
	setText : function (str){
		this.win.setText(str);
	}
}

function deleteRow(evt,tableName,info){
	if(arguments.length<3){
		info = "确定要删除吗？";
	}
	if(confirm(info)){
		if(window.ActiveXObject){  
			deleteRowIE(tableName);  
		}else{  
			deleteRowFF(evt,tableName);  
		}
	}
}

function deleteRowIE(the_table){  
	var the_cell;    
	the_cell=get_Element(event.srcElement,"td");    
	var index=the_cell.parentElement.rowIndex;  
	the_table.deleteRow(index);  
}
function deleteRowFF(evt,the_table){  
	
	var the_cell;    
	the_cell=get_Element(evt.target,"td");  
	var index=the_cell.parentNode.rowIndex;   
	the_table.deleteRow(index);  
}




function getIndex(evt,tableName){  
	var index;
	if(window.ActiveXObject){  
		index = getIndexIE(tableName);  
	}else{  
		index = getIndexFF(evt,tableName);  
	}
	return index;
} 

function getIndexIE(the_table){  
	var the_cell;    
	the_cell=get_Element(event.srcElement,"td");    
	var index=the_cell.parentElement.rowIndex;  
	return index;
}

function getIndexFF(evt,the_table){  
	var the_cell;    
	the_cell=get_Element(evt.target,"td");  
	var index=the_cell.parentNode.rowIndex;   
	return index;  
}  

function get_Element(the_ele,the_tag){  
	the_tag = the_tag.toLowerCase();  
	if(the_ele.tagName.toLowerCase()==the_tag){  
		return the_ele;  
	}  
	while(the_ele=the_ele.offsetParent){  
		if(the_ele.tagName.toLowerCase()==the_tag){  
			return the_ele;  
		}  
	}  
	return(null);  
}

function delQuestion(temp,score){
	if(window.confirm("您确定要删除吗？")){
		var content=document.getElementById("contentDiv");
		var str="";
		strArray[temp][1]=1;
		for(var k=0;k<strArray.length;k++){
			if(strArray[k][1]==0){
				str+=strArray[k][0];
			}
		}
		sum_score=parseFloat(parseFloat(sum_score)-parseFloat(score));
		content.innerHTML=str;
	}
}


function checkkey(){
	var flag;
	var keylength = document.fm1.answer_key.length;
	for(var i=0;i<keylength;i++){
		if(document.fm1.answer_key[i].checked==true){
			flag=true;
			break;
		}
	}

	if(flag){
		return true;
	}else {
		return false;
	}
}



function addrowDX(id){
	var s = document.fm1.answer_key;
	if(id=='key_a' && s.length<6){
		var rt = document.getElementById(id);
		var rtz = rt.insertRow(-1);
		var rtz0=rtz.insertCell(0)
		var rtz1=rtz.insertCell(1)
		rtz0.style.cssText="width:6%";
		rtz0.innerHTML = '<font class="red_star">*</font>' +String.fromCharCode(rt.rows.length+66)+':<input type="radio" value="'+(rt.rows.length+2)+'" name="answer_key" id="answer_key"/>';
		rtz1.innerHTML = '<textarea name=\"answer_content\" id=\"answer_content'+(rt.rows.length+2)+'\" style=\"width: 92%\" rows=\"3\" class=\"editTextarea\"></textarea>  <a href=\"#\" onclick=\"dialog(\'adv_edit.jsp\',600,430,\'answer_content'+(rt.rows.length+2)+'\')\">高级编辑</a>';
	}
}
function addrowDX1(id){
	var s = document.fm1.answer_key;
	if(id=='key_a' && s.length<6){
		var rt = document.getElementById(id);
		var rtz = rt.insertRow(-1);
		var rtz0=rtz.insertCell(0)
		var rtz1=rtz.insertCell(1)
		rtz0.style.cssText="width:6%";
		rtz0.innerHTML = String.fromCharCode(rt.rows.length+64)+':<input type="radio" value="'+(rt.rows.length)+'" name="answer_key" id="answer_key"/>';
		rtz1.innerHTML = '<textarea name=\"answer_content\" id=\"answer_content'+(rt.rows.length)+'\" style=\"width: 92%\" rows=\"3\" class=\"editTextarea\"></textarea>  <a href=\"#\" onclick=\"dialog(\'adv_edit.jsp\',600,430,\'answer_content'+(rt.rows.length)+'\')\">高级编辑</a>';
	}
}

function addrowDuoX(id){
	var s = document.fm1.answer_key;
	if(id=='key_a' && s.length<6){
		var rt = document.getElementById(id);
		var rtz = rt.insertRow(-1);
		var rtz0=rtz.insertCell(0)
		var rtz1=rtz.insertCell(1)
		rtz0.style.cssText="width:6%";
		rtz0.innerHTML = '<font class="red_star">*</font>' +String.fromCharCode(rt.rows.length+66)+':<input type="checkbox" value="'+(rt.rows.length+2)+'" name="answer_key" id="answer_key"/>';
		rtz1.innerHTML = '<textarea name=\"answer_content\" id=\"answer_content'+(rt.rows.length+2)+'\" style=\"width: 92%\" rows=\"3\" class=\"editTextarea\"></textarea>  <a href=\"#\" onclick=\"dialog(\'adv_edit.jsp\',600,430,\'answer_content'+(rt.rows.length+2)+'\')\">高级编辑</a>';
	}
}

function addrowDuoX1(id){
	var s = document.fm1.answer_key;
	if(id=='key_a' && s.length<6){
		var rt = document.getElementById(id);
		var rtz = rt.insertRow(-1);
		var rtz0=rtz.insertCell(0)
		var rtz1=rtz.insertCell(1)
		rtz0.style.cssText="width:6%";	
		rtz0.innerHTML = String.fromCharCode(rt.rows.length+64)+':<input type="checkbox" value="'+(rt.rows.length)+'" name="answer_key" id="answer_key"/>';
		rtz1.innerHTML = '<textarea class=\"input1\" name=\"answer_content\" id=\"answer_content'+(rt.rows.length)+'\" style=\"width: 91.5%\" rows=\"3\" class=\"editTextarea\"></textarea>  <a href=\"#\" onclick=\"dialog(\'adv_edit.jsp\',600,430,\'answer_content'+(rt.rows.length)+'\')\">高级编辑</a>';
	}	
}

function deleteRowFI(the_table){
	var len = 0;
	if(deleteRowFI.arguments.length>1){
		len = arguments[1];
	}
	var a = document.getElementById(the_table).getElementsByTagName('tr');
	if(a.length>len){
		document.getElementById(the_table).deleteRow(a.length-1);
	}
}

function CheckAll(form) {
	for (var i=0;i<form.elements.length;i++) {
		var e = form.elements[i];
		if (e.name != 'chkall' && e.type=='checkbox')
			e.checked = form.chkall.checked;
	}
}


function LeftToRight(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	for(i=0;i<len_left;i++){
		if(lid.options[i].selected==true){
			var len_right = rid.options.length;
			rid.options[len_right]=new Option(lid.options[i].text,lid.options[i].value);
			lid.remove(lid.selectedIndex);
			break;
		}
	}
}

function RightToLeft(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_right = rid.options.length;
	for(i=0;i<len_right;i++){
		if(rid.options[i].selected==true){
			var len_left = lid.options.length;
			lid.options[len_left]=new Option(rid.options[i].text,rid.options[i].value);
			rid.remove(rid.selectedIndex);
			break;
		}
	}
}
function LeftAllRight(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	var len_right = rid.options.length;
	for(i=0;i<len_left;i++){
		rid.options[len_right++]=new Option(lid.options[i].text,lid.options[i].value);
	}
	lid.options.length=0;
}

function RightAllLeft(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	var len_right = rid.options.length;
	for(i=0;i<len_right;i++){
		lid.options[len_left++]=new Option(rid.options[i].text,rid.options[i].value);
	}
	rid.options.length=0;	
}

//
function addchildquestion(index){

	var child_content = win1.win._frame.contentWindow.document.getElementById('vchild_content').value;
	var child_answer = win1.win._frame.contentWindow.document.getElementById('vchild_answer').value;
	var child_analyse = win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value;
	
	child_content=child_content.replaceAll('\"','“');
	child_answer=child_answer.replaceAll('\"','“');
	child_analyse = child_analyse.replaceAll('\"','“');
	
	
	if(child_content.getBytes()>1800 || child_content.getBytes()==0){
		alert('试题内容不能为空且不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_content').focus();
		return;
	}
	
	if(child_analyse.getBytes()>1800){
		alert('试题分析不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_analyse').focus();
		return;
	}	

	if(child_answer.getBytes()>1800 || child_answer.getBytes()==0){
		alert('试题答案不能为空且不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_answer').focus();
		return;
	}
		
	var rt  = document.getElementById('child_questions');
	var rtz = rt.insertRow(index);
	var rtz0=rtz.insertCell(0);
	var rtz1=rtz.insertCell(1);
	rtz0.style.cssText="width:80%";
	rtz1.style.cssText="width:20%";
	if(child_content.getBytes()>50){
		rtz0.innerHTML=child_content.substring(0,50)+'...<input type=\"hidden\" value=\"'+child_content+'\" name=\"child_content\"  id=\"child_content\" />';
	}else{
		rtz0.innerHTML=child_content+'<input type=\"hidden\" value=\"'+child_content+'\" name=\"child_content\" id=\"child_content\" />';
	}
	var answer = '<input type=\"hidden\" value=\"'+child_answer+'\" name=\"child_answer\" />';
	var analyse = '<input type=\"hidden\" value=\"'+child_analyse+'\" name=\"child_analyse\" />';
	rtz1.innerHTML= answer + analyse + '<a href=\"#\" onclick=\"view_childquestion(event,child_questions);\">编辑</a> | <a href=\"#\" onclick=\"deleteRow(event,child_questions,\'确定要删除吗？\');\">删除</a>';
	win1.win._frame.contentWindow.document.getElementById('vchild_content').value="";
	win1.win._frame.contentWindow.document.getElementById('vchild_answer').value="";
	win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value="";
}

//
function switchfun(key){
	if(key==1){
		win1.win._frame.contentWindow.document.getElementById('vchild_content').value="";
		win1.win._frame.contentWindow.document.getElementById('vchild_answer').value="";
		win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value="";
	}else{
		edit_childquestion(key);
	}
	win1.win._frame.contentWindow.document.getElementById('vchild_save').onclick=function(){addchildquestion(-1)};
}


function viewInfo(index){
	if(win1.win._frame.contentWindow.document.getElementById('vchild_content') == null){
		window.setTimeout(function(){viewInfo(index);},0);
	}else{
		with(win1.win._frame.contentWindow.document){
			if(!document.fm1.child_content.length && index==2){
				getElementById('vchild_content').value= document.fm1.child_content.value;
				getElementById('vchild_answer').value=document.fm1.child_answer.value;
				getElementById('vchild_analyse').value=document.fm1.child_analyse.value;
				getElementById('vchild_save').onclick=function(){switchfun(index)};
			}else{
				getElementById('vchild_content').value = document.fm1.child_content[index-2].value;
				getElementById('vchild_answer').value = document.fm1.child_answer[index-2].value;
				getElementById('vchild_analyse').value=document.fm1.child_analyse[index-2].value;
				getElementById('vchild_save').onclick=function(){switchfun(index)};
			}
		}
	}
}

function view_childquestion(evt,tid){
	var flag = win1.show(1);
	if(flag){
		var index = getIndex(evt,tid);
		if(document.fm1.child_content!=null){
			viewInfo(index);
		}
	}
}

function edit_childquestion(index){
	var child_content = win1.win._frame.contentWindow.document.getElementById('vchild_content').value;
	var child_answer = win1.win._frame.contentWindow.document.getElementById('vchild_answer').value;
	var child_analyse = win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value;
	
	child_content=child_content.replaceAll('\"','“');
	child_answer=child_answer.replaceAll('\"','“');
	child_analyse = child_analyse.replaceAll('\"','“');
	
	if(child_content.getBytes()>1800 || child_content.getBytes()==0){
		alert('试题内容不能为空且不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_content').focus();
		return;
	}
	
	if(child_analyse.getBytes()>1800){
		alert('试题分析不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value.focus();
		return;
	}
	
	if(child_answer.getBytes()>1800 || child_answer.getBytes()==0){
		alert('试题答案不能为空且不能大于1800个字符或者900个汉字!');
		win1.win._frame.contentWindow.document.getElementById('vchild_answer').value.focus();
		return;
	}
			
	var answer = '<input type=\"hidden\" value=\"'+child_answer+'\" name=\"child_answer\" />';
	var analyse = '<input type=\"hidden\" value=\"'+child_analyse+'\" name=\"child_analyse\" />';
	
	var rt  = document.getElementById('child_questions');
	rt.deleteRow(index);
	var rtz = rt.insertRow(-1);
	
	var rtz0=rtz.insertCell(0);
	var rtz1=rtz.insertCell(1);
	
	rtz0.style.cssText="width:80%";
	rtz1.style.cssText="width:20%";
	if(child_content.getBytes()>50){
		rtz0.innerHTML=child_content.substring(0,50)+'...<input type=\"hidden\" value=\"'+child_content+'\" name=\"child_content\" id=\"child_content\" />';
	}else{
		rtz0.innerHTML=child_content+'<input type=\"hidden\" value=\"'+child_content+'\" name=\"child_content\" id=\"child_content\" />';
	}
	var answer = '<input type=\"hidden\" value=\"'+child_answer+'\" name=\"child_answer\" />';
	var analyse = '<input type=\"hidden\" value=\"'+child_analyse+'\" name=\"child_analyse\" />';
	rtz1.innerHTML=  answer + analyse + '<a href=\"#\" onclick=\"view_childquestion(event,child_questions);\">编辑</a> | <a href=\"#\" onclick=\"deleteRow(event,child_questions,\'确定要删除吗?\');\">删除</a>';
	win1.win._frame.contentWindow.document.getElementById('vchild_content').value="";
	win1.win._frame.contentWindow.document.getElementById('vchild_answer').value="";
	win1.win._frame.contentWindow.document.getElementById('vchild_analyse').value="";
	win1.hide();
}


//-------------------------------A3子试题部分START--------------------------------//
var child_index=0;
var input_num=0;
var status=0;
var strArray=new Array(new Array(),new Array());
var input_type =new Array("<input type='checkbox' name='#1' id='#1' disabled='disabled' value='#1'/>","<input type='radio' disabled='disabled' name='#1' id='#1' value='#2' />","<input name='sub_questions' id='sub_questions' type='hidden' value='#1' />");
function started(){
	var regu = "^([0-9]*)$";
	var re = new RegExp(regu); 
	if (win1.win._frame.contentWindow.document.getElementById("input_num").value.search(re) == -1) {
		alert("选项个数必须是整数"); 
		win1.win._frame.contentWindow.focus();
		return;
	}
	input_num=parseInt(win1.win._frame.contentWindow.document.getElementById("input_num").value);
	status=parseInt(win1.win._frame.contentWindow.document.getElementById("status").value);
	if(isNaN(input_num)){
		alert("选项个数必须是整数");
		win1.win._frame.contentWindow.focus();
		return null;
	}
	if(input_num<2){
		alert("选项个数必须大于或者等于2");
		win1.win._frame.contentWindow.focus();
		return;
	}
	if(input_num>6){
		alert("选项个数必须小于或者等于6");
		win1.win._frame.contentWindow.focus();
		return;
	}
	if(status==0){
		alert("请选择试题类型");
		win1.win._frame.contentWindow.focus();
		return null;
	}
	xuanxiang();
}
function xuanxiang(){
	var shiti=win1.win._frame.contentWindow.document.getElementById("shiti");
	shiti.style.display="block";
	var xuanxiang=win1.win._frame.contentWindow.document.getElementById("xuanxiang");
	var str="";
	if(status==1){
		for(var i=0;i<input_num;i++){
			str+= String.fromCharCode(i+65)+ ".<input type='text' name='input_name' size='20'/>&nbsp;&nbsp;<select name='input_answer'><option value='1'>正确</option><option value='0'>错误</option></select><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span><br/>"
		}
	}else{
		for(var i=0;i<input_num;i++){
			str+= String.fromCharCode(i+65)+".<input type='radio' name='input_answer' value='1' />&nbsp;&nbsp;<input type='text' name='input_name' size='20'/><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span><br/>"
		}
	}
	xuanxiang.innerHTML=str.substring(0,str.length-5)+"<div style='text-align: right; margin-top:-25px;'><input name='button' type='button' value='保存' onclick='winObj.add();' /></div>";
}

function add(){
	var str="";
	var str1="<span id='c_span" + child_index + "'>";
	str1 +="<table border='0' cellspacing='1' cellpadding='3' width='99%' class='gridtable'>";
	str1 +="<tr><td class='row_tip'>";
	var title=win1.win._frame.contentWindow.document.getElementsByName("title");
	var input_name=win1.win._frame.contentWindow.document.getElementsByName("input_name");
	var input_answer=win1.win._frame.contentWindow.document.getElementsByName("input_answer");
	var jiexi=win1.win._frame.contentWindow.document.getElementsByName("jiexi");
	if(status==1){
		
		var sel = input_answer.length;
		var stmp = 0;
		for(var j=0;j<sel;j++){
			if(input_answer[j].options[input_answer[j].options.selectedIndex].value == 1){
				stmp = 1;
				break;
			}
		}
		if(stmp==0){
			alert("必须要选择一个正确的");
			win1.win._frame.contentWindow.focus();
			return;
		}
		
		str=5+"|";
		str1 +="试题类型：</td><td>多选题(A3-2)</td></tr><tr><td class='row_tip'>";
	}
	if(Validate.isEmpty(title[0].value) || title[0].value.getBytes()>100){
		alert("子试题内容不能为空且不大于100个字符或50个汉字！");
		title[0].focus();
		return null;
	}
	if(!Validate.isEmpty(jiexi[0].value) && jiexi[0].value.getBytes()>100){
		alert("子试题分析不能为空且不大于100个字符或50个汉字！");
		jiexi[0].focus();
		return null;
	}

	for(var i=0;i<input_num;i++){
		if(Validate.isEmpty(input_name[i].value) || input_name[i].value.getBytes()>100){
			alert("子试题选项不能是空且不大于100个字符或50个汉字！");
			input_name[i].focus();
			return null;
		}
	}
	if(status==2){
		var isXuanZe=0;
		for(var m=0;m<input_num;m++){
			if(input_answer[m].checked==true)
				isXuanZe=1;
		}
		if(isXuanZe==0){
			alert("单选答案必须要选择一个正确的");
			win1.win._frame.contentWindow.focus();
			return;
		}
		
		str=4+"|";
		str1 +="试题类型：</td><td>单选题(A3-1)</td></tr><tr><td class='row_tip'>";
	}
	
	str=str+title[0].value+"|";
	str1=str1+"试题内容：</td><td><textarea name='_content"+ child_index + "' id='_content"+ child_index + "' disabled='disabled' style='width: 92%' rows='2'>"+title[0].value+"</textarea></td></tr><tr><td class='row_tip'>试题分析：</td><td><textarea style='width: 92%' id='_analyse"+ child_index + "' name='_analyse"+ child_index + "' disabled='disabled' rows='2' >"+jiexi[0].value+"</textarea></td></tr><tr><td class='row_tip'>候选项：</td><td>";
	for(var j=0;j<input_num;j++){
		if(status==1){
			str+=input_name[j].value+"/"+input_answer[j].options[input_answer[j].options.selectedIndex].value+"|";
			if(input_answer[j].options[input_answer[j].options.selectedIndex].value == 1){
				str1+="<input type='checkbox' disabled='disabled' checked='checked' name='_xx"+child_index+"' id=_xx"+child_index+"'>"+String.fromCharCode(j+65)+".<input type='text' name='_xxnr"+ child_index + "' id='_xxnr"+ child_index + "' disabled='disabled' value='"+input_name[j].value+"'/><br/>";
			}else{
				str1+="<input type='checkbox' disabled='disabled' name='_xx"+child_index+"' id=_xx"+child_index+"'>"+String.fromCharCode(j+65)+".<input type='text' name='_xxnr"+ child_index + "' id='_xxnr"+ child_index + "' disabled='disabled' value='"+input_name[j].value+"'/><br/>";
			}
		}else{
			var values=0;
			if(input_answer[j].checked==true){
				values=1;
			}
			str+=input_name[j].value+"/"+values+"|";
			if(values == 1){
				str1+="<input type='radio' checked='checked' disabled='disabled' name='_xx"+child_index+"' id=_xx"+child_index+"'>"+String.fromCharCode(j+65)+".<input type='text' name='_xxnr"+ child_index + "' id='_xxnr"+ child_index + "' disabled='disabled' value='"+input_name[j].value+"'/><br/>";
			}else{
				str1+="<input type='radio' disabled='disabled' name='_xx"+child_index+"' id=_xx"+child_index+"'>"+String.fromCharCode(j+65)+".<input type='text' name='_xxnr"+ child_index + "' id='_xxnr"+ child_index + "' disabled='disabled' value='"+input_name[j].value+"'/><br/>";
			}
		}
	}
	str=str+jiexi[0].value+"|";
	str1=str1+ "</td></tr><tr><td class='row_tip' colspan='2'>" + input_type[2].replace("#1",str)+"&nbsp;<input type='button' class='but2' value='删除子试题' onclick='delQuestion("+strArray.length+")'/><br/>";
	str1 +="</td></tr></table></span>";
	strArray[strArray.length]=[str1,0];
	closeLayer();
	child_index++;
}
function delQuestion(temp){
	if(window.confirm("您确定要删除吗？")){
		var content=document.getElementById("contentDiv");
		var str="";
		strArray[temp][1]=1;
		for(var k=0;k<strArray.length;k++){
			if(strArray[k][1]==0){
				str+=strArray[k][0];
			}
		}
		content.innerHTML=str;
	}
}
function updateQuestion(c_index){
	$A($("c_span" + c_index).getElementsByTagName("input")).each(function(obj){
		if(obj.type=='button'){
			if(obj.id=='save'){
				obj.style.display="";
			}else{
				obj.style.display="none";
			}
		}else{
			obj.disabled='';
		}
	});
	$A($("c_span" + c_index).getElementsByTagName("textarea")).each(function(obj){
		obj.disabled='';
	});
}

function closeLayer(){
	var content=document.getElementById("contentDiv");
	var str="";
	for(var k=0;k<strArray.length;k++){
		if(strArray[k][1]==0){
			str+=strArray[k][0]+"<hr width='100%'>";
		}
	}
	content.innerHTML=str;
	resetChildWin();
	win1.hide();
	return false;
}
function resetChildWin(){
	win1.win.attachURL(ctxJS+"/page/add_child_questions.jsp");
}
//-------------------------------A3子试题部分END--------------------------------//

function checkkey(){
	var flag;
	var keylength = document.fm1.answer_key.length;
	for(var i=0;i<keylength;i++){
		if(document.fm1.answer_key[i].checked==true){
			flag=true;
			break;
		}
	}

	if(flag){
		return true;
	}else {
		return false;
	}
}

function checkkeyAnswer(){
	var Answerlength = document.fm1.answer_content.length;
	for(var i=0;i<Answerlength;i++){
		if((document.fm1.answer_content[i].value.getBytes()==0) || (document.fm1.answer_content[i].value.getBytes()>1800)){
			flag=false;
			break;
		}else{
			flag=true;
		}
	}
	if(flag){
		return true;
	}else {
		return false;
	}
}