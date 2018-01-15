/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2016-1-12
 */

package com.hys.exam.displaytag;

import org.displaytag.decorator.TableDecorator;

public class TableOverOutWrapper extends TableDecorator{
	  
    @Override  
    public String addRowId() {  
        return "i_d\" onmouseover=\"if (typeof(window.m_over)=='function') window.m_over(this);\" onmouseout=\"if (typeof(window.m_out)=='function') window.m_out(this);\" title=\"";  
    }  
	 
}


