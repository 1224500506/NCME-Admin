var dhtmlXWindows = Class.create();
var modalWindow = Class.create();

dhtmlXWindows.prototype = {
	initialize: function(){
	
	},
	setImagePath: function(){
	
	},
	createWindow: function(id,t1,t2,w,h){
		return new modalWindow(id,t1,t2,w,h);
	}
}
modalWindow.prototype = {
	initialize: function(id,t1,t2,w,h){
		this.id = id;
		this.width = w;
		this.height = h;
		this.url = "";
		this.obj = null;
		this._frame = { 
			contentWindow: null
		};
	},
	keepInViewport: function(){
	
	},
	setText: function(text){
	
	},
	attachURL: function(url){
		this.url = url;
	},
	button: function(id){
		var b = document.createElement("input");
		b.id = id;
		b.type = "button";
		document.body.appendChild(b);
		b.style.display="none";
		Object.extend(b,{hide: function(){
			this.style.display="none";
		},
		attachEvent: function(){}});
		return b;
	},
	maximize: function(){
		this.width = 1000;
		this.height = 550;
	},
	show: function(){
		this.obj = window.open (this.url,this.id,'height=' + this.height + ',width=' + this.width + ',top=90,left=120,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no') 
		this._frame.contentWindow = this.obj;
		this.obj.focus();
		this.hide=function(){
			this.obj.close();
		}
	},
	hide: function(){

	}
}