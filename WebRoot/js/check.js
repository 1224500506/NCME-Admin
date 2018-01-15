// JavaScript Document

$(document).ready(function() {
    $("#check").click(function(){
		if($(this).parents("table").children("tbody").find("input:checked").length<$(this).parents("table").children("tbody").find("input:checkbox").length){
			$(this).parents("table").find("input:checkbox").attr("checked",true);
		}else{
			$(this).parents("table").find("input:checkbox").attr("checked",false);
		}
	});
});