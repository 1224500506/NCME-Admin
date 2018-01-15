KindEditor.plugin('specialchar', function(K){
    var self = this, name = 'specialchar',
        // allowPreviewSpecialChars 暂未启用预览功能
        allowPreview = self.allowPreviewSpecialChars === undefined ? true : self.allowPreviewSpecialChars,
        currentPageNum = 1;
    var KE_SPECIAL_CHARACTER = Array(
        '!','&quot;','#','$','%','&amp;','&#39;','(',')','*','+','-','.','/','0','1','2',
        '3','4','5','6','7','8','9',':',';','&lt;','=','&gt;','?','@','A','B','C',
        'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
        'U','V','W','X','Y','Z','[',']','^','_','`','a','b','c','d','e','f',
        'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w',
        'x','y','z','{','|','}','~','&euro;','&lsquo;',';&rsquo;','&ldquo;','&rdquo;','&ndash;','&mdash;','&iexcl;','&cent;','&pound;',
        '&curren;','&yen;','&brvbar;','&sect;','&uml;','&copy;','&ordf;','&laquo;','&not;','&reg;','&macr;','&deg;','&sup2;','&sup3;','&acute;','&micro;','&para;',
        '&middot;','&cedil;','&sup1;','&ordm;','&raquo;','&frac14;','&frac12;','&frac34;','&iquest;','&Agrave;','&Aacute;','&Acirc;','&Atilde;','&Auml;','&Aring;','&AElig;','&Ccedil;',
        '&Egrave;','&Eacute;','&Ecirc;',';&Euml;','&Igrave;','&Iacute;','&Icirc;','&Iuml;','&ETH;','&Ntilde;','&Ograve;','&Oacute;','&Ocirc;','&Otilde;','&Ouml;','&times;','&Oslash;',
        '&Ugrave;','&Uacute;','&Ucirc;','&Uuml;','&Yacute;','&THORN;','&szlig;','&agrave;','&aacute;','&acirc;','&atilde;','&auml;','&aring;','&aelig;','&ccedil;','&egrave;','&eacute;',
        '&ecirc;','&euml;','&igrave;','&iacute;','&icirc;','&iuml;','&eth;','&ntilde;','&ograve;','&oacute;','&ocirc;','&otilde;','&ouml;','&divide;','&oslash;','&ugrave;','&uacute;',
        '&ucirc;','&uuml;','&yacute;','&thorn;','&yuml;','&OElig;','&oelig;','Ŵ','Ŷ','ŵ','ŷ','&sbquo;','‛','&bdquo;','&hellip;','&trade;','►',
        '&bull;','&rarr;','&rArr;','&hArr;','&diams;','&asymp;'
    );
    //点击图标时执行
    self.clickToolbar(name, function(){
        var rows = 5, cols = 10, total = KE_SPECIAL_CHARACTER.length, startNum = 0,
            cells = rows * cols, pages = Math.ceil(total / cells),
            colsHalf = Math.floor(cols / 2),
            wrapperDiv = K('<div class="ke-plugin-specialchars"></div>'),
            elements = [],
            menu = self.createMenu({
                name : name,
                beforeRemove : function() {
                    removeEvent();
                }
            });
        menu.div.append(wrapperDiv);
        var previewDiv, previewChar;
        if(allowPreview) {
            //未实现预览
        }

        function bindCellEvent(cell, j, num) {
            if (previewDiv) {
                //未实现预览
            } else {
                cell.mouseover(function() {
                    K(this).addClass('ke-over');
                });
            }
            cell.mouseout(function() {
                K(this).removeClass('ke-over');
            });
            cell.click(function(e) {
                self.insertHtml(KE_SPECIAL_CHARACTER[num]).hideMenu().focus();
                e.stop();
            });
        }

        function createEmoticonsTable(pageNum, parentDiv) {
            var table = document.createElement('table');
            parentDiv.append(table);
            if (previewDiv) {
                //未实现预览
            }
            table.className = 'ke-table';
            table.cellPadding = 0;
            table.cellSpacing = 0;
            table.border = 0;
            var num = (pageNum - 1) * cells + startNum;
            for (var i = 0; i < rows; i++) {
                var row = table.insertRow(i);
                for (var j = 0; j < cols; j++) {
                    var cell = K(row.insertCell(j));
                    cell.addClass('ke-cell');
                    bindCellEvent(cell, j, num);
                    var span = K('<span class="ke-char"></span>')
                        .html(KE_SPECIAL_CHARACTER[num]);
                    cell.append(span);
                    elements.push(cell);
                    num++;
                }
            }
            return table;
        }

        var table = createEmoticonsTable(currentPageNum, wrapperDiv);

        function removeEvent() {
            K.each(elements, function() {
                this.unbind();
            });
        }
        var pageDiv;
        function bindPageEvent(el, pageNum) {
            el.click(function(e) {
                removeEvent();
                table.parentNode.removeChild(table);
                pageDiv.remove();
                table = createEmoticonsTable(pageNum, wrapperDiv);
                createPageTable(pageNum);
                currentPageNum = pageNum;
                e.stop();
            });
        }
        function createPageTable(currentPageNum) {
            pageDiv = K('<div class="ke-page"></div>');
            wrapperDiv.append(pageDiv);
            for (var pageNum = 1; pageNum <= pages; pageNum++) {
                if (currentPageNum !== pageNum) {
                    var a = K('<a href="javascript:;">[' + pageNum + ']</a>');
                    bindPageEvent(a, pageNum);
                    pageDiv.append(a);
                    elements.push(a);
                } else {
                    pageDiv.append(K('@[' + pageNum + ']'));
                }
                pageDiv.append(K('@&nbsp;'));
            }
        }
        createPageTable(currentPageNum);
        /*var charTable = '<table id="KE_SPECIAL_TABLE">';
        for(var i in KE_SPECIAL_CHARACTER) {
            if(i == 0) {
                charTable += "<tr>";
                charTable += "<td><a><span>"+KE_SPECIAL_CHARACTER[i]+"</span></a></td>";
            } else if (i!=0 && i%10 == 0) {
                charTable += "</tr><tr>";
                charTable += "<td><a><span>"+KE_SPECIAL_CHARACTER[i]+"</span></a></td>";
            } else if (i!=0 && i%10 !=0 && i == KE_SPECIAL_CHARACTER.length-1) {
                charTable += "<td><a><span>"+KE_SPECIAL_CHARACTER[i]+"</span></a></td>";
                charTable += "</tr>";
            } else if (i!=0 && i%10 == 0 && i== KE_SPECIAL_CHARACTER.length-1) {
                charTable += "<tr>";
                charTable += "<td><a><span>"+KE_SPECIAL_CHARACTER[i]+"</span></a></td>";
                charTable += "</tr>";
            } else {
                charTable += "<td><a><span>"+KE_SPECIAL_CHARACTER[i]+"</span></a></td>";
            }
        }
        charTable +="</table>";
        wrapperDiv.append(charTable);

        function addEvent(obj,type,fun){
            if(obj.addEventListener){
                obj.addEventListener(type,fun);
                return true;
            }else if(obj.attachEvent){
                return obj.attachEvent("on"+type,fun);
            }else{
                return false;
            };
        };

        function createEmoticonsTable() {
            var table = document.getElementById('KE_SPECIAL_TABLE');
            var rows = table.rows.length;
            for(var q=0;q<rows;q++) {
                var cells = table.rows[q].cells;
                for(var m=0;m<cells.length; m++) {
                    *//*(function(x) {
                        cells[x].onclick=function(){
                            var htm = cells[x].getElementsByTagName("span")[0].innerHTML;
                            self.insertHtml(htm).hideMenu().focus();
                        }
                        cells[x].onmouseover=function() {
                            cells[x].style.backgroundColor = '#ffffff';
                        }
                        cells[x].onmouseout=function() {
                            cells[x].style.backgroundColor = '#F1F1F1';
                        }
                    })(m);*//*
                }
            }
            return table;
        }

        var table = createEmoticonsTable();

        function removeEvent() {
            K.each(elements, function() {
                this.unbind();
            });
        }*/
        //self.insertHtml('您好');
    });
});