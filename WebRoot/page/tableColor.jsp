<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style media="all" type="text/css">
	tr.odd {  
		line-height:25px;  
	}  
	tr.even {  
		line-height:25px;  
	}  
	.trmo{  
        background-color:#E0E3EC !important;  
    } 
</style>
<script type="text/javascript">
	function m_over(tr){  
	    tr.className=tr.className + " " + "trmo";  
	}  
	function m_out(tr){  
	    var cn = tr.className.replace(/\s+trmo/,'');  
	    tr.className = cn;  
	}
</script>
