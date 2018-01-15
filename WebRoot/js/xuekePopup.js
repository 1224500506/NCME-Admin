function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
		$('.tit_biaoti').text(_title);
	$('.xs_selectlvl').text(_selectLvl);
	$('.xs_checklvl').text(_checkLvl);
	$('.xs_currentid').text(_initId);

	$('.xs_er').each(function(){ $(this).eq(0).removeClass('xs_er').addClass('xs_san');});
	$('.xs_er').each(function(){ $(this).remove();});
	$('.xs_san').each(function(){ $(this).eq(0).removeClass('xs_san').addClass('xs_er');});
	$('.xs_er i').each(function(){ $(this).show();});
	$('.xs_er em').each(function(){ $(this).hide();});
	
	kuangcode = _kuangcode;
	kuang = _kuang;
	ajaxurl = _ajaxurl;
	
	if(_title == "学科"){
		$('.xs_biaoti .xs_er .attr_xueke01').each(function () {
			$(this).text('一级学科');
		});
		initsubmenu="一级学科";
	}
	$('.xs_kuangcode').text($(_kuangcode).val());
	//$('.xs_kuang').text($(_kuang).text());
	
	var selstr = $(_kuang).text();
	selarray = selstr.split(",");
	var newnarray = new Array();
	$(selarray).each(function(key, val){
		if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
	});
	$('.xs_kuang').html(newnarray.toString());		

	$('.delem').click(function(){
		delem($(this));
	});

	var url;
	
		url = ajaxurl + "?type="+ _initType +"&id="+ _initId + "&mode=getListByType";
		
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList(result);
		   };
	    }
	});		
}

function initPropWndProp(){
	
	$('.attr_xueke04').off('click');
	$('.attr_xueke04').click(function(){
		var curid = eval($('.xs_currentid').text());
		var selid = $(this).prop('id');
		var selname = $(this).text();
		if (selid < 1) return false;
		var a = $(this).find('i').length;
		if (!a) return false;
		
		$('.xs_currentid').text(selid);
		var ms = $('.xs_biaoti .attr_xueke01').length-1;
		$('.xs_er i').hide();
		$('.xs_er em').show();
	//	$('.xs_er').eq(ms).find('i').show();
	//	$('.xs_er').eq(ms).find('em').hide();
		
		var str = '<div class="fl xs_er"><p class="fl attr_xueke01" id=' + curid + '>' + selname + '</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>';
		$('.xs_er').eq(ms).after(str);
		if(curid == 0)	$('.xs_er').eq(0).remove();
		var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});
	});
	
	$('.attr_xueke01').off('click');
	$('.attr_xueke01').click(function(){
		var curid = eval($('.xs_currentid').text());
		var selid = $(this).prop('id');
		var selname = $(this).text();
		if (selid == '') return false;
		
		$('.xs_currentid').text(selid);
		var inx = $(this).parent().index();
		if (inx<0)return;
		$('.xs_er').each(function(key, val){
			if (key >= inx)
				$(val).remove();
			if (key == inx-1){
				$(val).find('i').show();
				$(val).find('em').hide();
			}
				
		});

		if (selid == 0) $('.xs_biaoti').html('<div class="fl xs_er"><p class="fl attr_xueke01">'+initsubmenu+'</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>');

		var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});
	});
	
	$('.xs_ul input[type="checkbox"]').off('click');
	$('.xs_ul input[type="checkbox"]').click(function(){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
		
		if ($(this).prop('checked')){
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var newarray = new Array();
			$(selarray).each(function(key, val){
				if (val != "") newarray.push(val);
			});
			newarray.push(id);
			$('.xs_kuangcode').text(newarray.toString());

			selstr = $('.xs_kuang').html();
			selarray = selstr.split(",");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (val != "") newnarray.push(val);
			});
			newnarray.push(propname);
			$('.xs_kuang').html(newnarray.toString());
		}
		else{
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			var newarray = new Array();
			$(selarray).each(function(key, val){
				if (key != idx) newarray.push(val);
			});
			$('.xs_kuangcode').text(newarray.toString());

			selstr = $('.xs_kuang').html();
			selarray = selstr.split(",");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (key != idx) newnarray.push(val);
			});
			$('.xs_kuang').html(newnarray.toString());
		}
		
		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
	
	});
	
	//selected item mark checked.
	$('.xs_ul input[type="checkbox"]').each(function(key, val){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var selstr = $('.xs_kuangcode').text();
		var selarray = selstr.split(",");
		var idx = selarray.indexOf(id);
		
		if (idx>=0) $(this).prop("checked", true);
	});
}	

function updatePropList(result){
	
	var str = "";
	var check = eval($('.xs_checklvl').text());
	var select = eval($('.xs_selectlvl').text());
	$(result.list).each(function(key, value){
		str += "<li><div class=''>";
		if (check <= value.type)
			str += '<input class="fl" style="margin-top:5px;" type="checkbox">';

		str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
		if (select > value.type){
			str += '<i class="fl ml10 kti_bjt2"></i>';
		}
		str += "</div><div class='clear'></div></li>";
	});
	
	$('.xs_ul').html(str);
		initPropWndProp();
	}
	
	
	
function delem(obj){
		var i = $(obj).index();
		//delete text
	var selstr = $(obj).parent().html();
	var selarray = selstr.split(",");
	var newnarray = new Array();
	$(selarray).each(function(key, val){
		if (key != i) newnarray.push(val);
	});
	$('.xs_kuang').html(newnarray.toString());
	
	//delete code
	var deletecode = "";
	selstr = $('.xs_kuangcode').text();
	selarray = selstr.split(",");
	newarray = new Array();
	$(selarray).each(function(key, val){
		if (key != i) newarray.push(val);
		else
			deletecode = val;
	});
	$('.xs_kuangcode').text(newarray.toString());

	//delete check
	var checklist = $('.attr_xueke04');
	$(checklist).each(function(key, val){
		if(deletecode == $(val).prop('id'))
			$(val).siblings().eq(0).prop("checked", "");
	});

	$('.delem').off('click');
	$('.delem').click(function(){
			delem($(this));
		});
		
}


function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
	$(kuang).text($('.xs_kuang').text());
		
}