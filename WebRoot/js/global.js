
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
var Validate = {isEmpty:function (str) {
	return trim(str) == "";
}, isChineseW:function (str) {
	return /^[\u4e00-\u9fa5\w]+([\u4e00-\u9fa5\w\s])*$/.test(str);
}, isChinese:function (str) {
	return /^[\u4e00-\u9fa5]+([\u4e00-\u9fa5\s])*$/.test(str);
}, isW:function (str) {
	return /^\w+$/.test(str);
}, isNum:function (str) {
	return /^\d+$/.test(str);
}, isNumGtZero:function (str) {
	return /^[1-9]{1}[0-9]*$/.test(str);
}};

function m_over(tr) {
	tr.className = tr.className + " " + "trmo";
}
function m_out(tr) {
	var cn = tr.className.replace(/\s+trmo/, "");
	tr.className = cn;
}

