package com.hys.exam.struts.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-10
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemImpPropAction extends BaseAction {
	
	private ExamOlemPropFacade localExamOlemPropFacade;
	
	

	public ExamOlemPropFacade getLocalExamOlemPropFacade() {
		return localExamOlemPropFacade;
	}

	public void setLocalExamOlemPropFacade(
			ExamOlemPropFacade localExamOlemPropFacade) {
		this.localExamOlemPropFacade = localExamOlemPropFacade;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm uploadForm = (DynaActionForm) form;
		
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		
		FormFile file = (FormFile) uploadForm.get("uploadfile");
		InputStream is = file.getInputStream();
		Workbook rwb = Workbook.getWorkbook(is);
		Sheet inSheet = rwb.getSheet(0);
		
		if((!type.equals("0")) && (!id.equals("0"))){
			read(inSheet,Integer.valueOf(type),Long.valueOf(id));
		}
		
		return "SUCCESS";
	}
	
	private  void read(Sheet inSheet,int type,Long parent_id){        
        try{

            List<ExamOlemProp> allList = new ArrayList<ExamOlemProp>();
            
            List<ExamOlemProp> c1 = new ArrayList<ExamOlemProp>();
            List<ExamOlemProp> c2 = new ArrayList<ExamOlemProp>();
            List<ExamOlemProp> c3 = new ArrayList<ExamOlemProp>();
            List<ExamOlemPropRefBaseProp> olemAndBaseList = new ArrayList<ExamOlemPropRefBaseProp>();
            if(type<4){
            	c1 = getExcelInfoList(inSheet,0,type,parent_id);
            	c2 = getExcelInfoList(inSheet,1,type,parent_id);
            	c3 = getExcelInfoList(inSheet,2,type,parent_id);
            	olemAndBaseList = setOlemRefProp(inSheet,3);
            	serRef(c1,c2,allList,1);
            	serRef(c2,c3,allList,0);
            	serRefBase(c3,olemAndBaseList);
            } else{
            	c1 = getExcelInfoList(inSheet,0,type,parent_id);
            	c2 = getExcelInfoList(inSheet,1,type,parent_id);
            	olemAndBaseList = setOlemRefProp(inSheet,2);
            	serRef(c1,c2,allList,1);
            	serRefBase(c2,olemAndBaseList);
            }
            
//            for(ExamOlemProp Ab : allList){
//            	System.out.println(Ab.getId()+"\t"+Ab.getParent_id()+"\t"+Ab.getOlem_prop_name()+"\t"+Ab.getOlem_prop_type()+"\t"+Ab.getRelation()+"\t"+Ab.getSys_prop());
//            }
//            
//            for(ExamOlemPropRefBaseProp p:olemAndBaseList){
//            	System.out.println(p.getOlem_prop_id()+"-----------------------"+p.getBase_prop_id());
//            }
            
            localExamOlemPropFacade.addImportExamOlemProp(allList, olemAndBaseList);
            
        } catch(Exception e) {
        }
	}
	

	/**
	 * 
	 * @param sheet
	 * @param col 列号
	 * @param type	上级类型
	 * @param parent_id 上级属性ID
	 * @return
	 */
	private List<ExamOlemProp> getExcelInfoList(Sheet sheet,int col,int type,Long parent_id){
		Cell cell = null;
		List<ExamOlemProp> olemPropList = new ArrayList<ExamOlemProp>();
		
		for(int i=1;i<sheet.getRows();i++){
			ExamOlemProp olemProp = new ExamOlemProp();
			List<ExamOlemProp> nextOlemPropList = new ArrayList<ExamOlemProp>();

			//基础理论
			if(type==2){ 
				if(col==0){
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(5);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1){
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(6);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
				} else if(col==2) {
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(7);
					olemProp.setRelation(0);
					olemProp.setSys_prop(1);					
				}
			}
			//基本知识
			if(type==3){ 
				if(col==0){
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(8);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1){
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(9);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);					
				} else if(col==2) {
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(10);
					olemProp.setRelation(0);
					olemProp.setSys_prop(1);					
				}
			}
			//基本技能
			if(type==4){ 
				if(col==0){
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(11);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1) {
					olemProp.setId(localExamOlemPropFacade.getId());
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(12);
					olemProp.setRelation(0);
					olemProp.setSys_prop(1);
				}
			}
			
			Range range = findRange(sheet,i,col);
			
			if(range!=null){
				
				olemProp.setOlem_prop_name(range.getTopLeft().getContents());
				
				if(col<4){
					for (int j = range.getTopLeft().getRow(); j <= range.getBottomRight().getRow(); j++) {
						ExamOlemProp nextOlemProp = new ExamOlemProp();
						nextOlemProp.setX_y(j + "_" + (col+1));
						nextOlemPropList.add(nextOlemProp);
					}
					// 关系List
					olemProp.setNextOlemPropList(nextOlemPropList);
				}				
				
				i = range.getBottomRight().getRow();
			} else {
				cell = sheet.getCell(col,i);
				olemProp.setOlem_prop_name(cell.getContents());

				int t = 0;
				do {
					if ((i + 1 + t) == sheet.getRows()) {
						t++;
						break;
					}
					// 得到当前单元格下方单元格
					cell = sheet.getCell(col, i + 1 + t);
					t++;
				} while (cell.getContents().trim().equals(""));
				if (col < 5) {
					for (int j = i; j < i + t; j++) {
						ExamOlemProp nextOlemProp = new ExamOlemProp();
						nextOlemProp.setX_y(j + "_" + (col+1));
						nextOlemPropList.add(nextOlemProp);
					}
					if (t != 0) {
						i = i + t - 1;
					}
					// 关系List
					olemProp.setNextOlemPropList(nextOlemPropList);
				}
			}
			if(!olemProp.getOlem_prop_name().trim().equals("")){
				olemPropList.add(olemProp);
			}
		}
		
		return olemPropList;
	}
	
	
	
	private Range findRange(Sheet sheet,int row,int col){
		Range[] ranges = sheet.getMergedCells();
		for(Range space:ranges){ 
	    	if(space.getTopLeft().getColumn() == col && space.getTopLeft().getRow() == row){
	    		return space;
	    	}
		}
		return null;
	}
	
	private List<ExamOlemPropRefBaseProp> setOlemRefProp(Sheet sheet,int col){
		List<ExamOlemPropRefBaseProp> propList = new ArrayList<ExamOlemPropRefBaseProp>();
		Cell cell = null;
		
		for(int i=1;i<sheet.getRows();i++){
			ExamOlemPropRefBaseProp olemBaseProp = new ExamOlemPropRefBaseProp();

			olemBaseProp.setX_y(i + "_" + col);
			
			Range range = findRange(sheet,i,col);
			
			if(range!=null){
				olemBaseProp.setBase_prop_id(Long.valueOf(range.getTopLeft().getContents()));
				i = range.getBottomRight().getRow();
			} else {
				cell = sheet.getCell(col,i);
				olemBaseProp.setBase_prop_id(Long.valueOf(cell.getContents()));
			}
			if(olemBaseProp.getBase_prop_id()!=0){
				propList.add(olemBaseProp);
			}
		}		
		return propList;
	}
		
	private List<ExamOlemProp> serRef(List<ExamOlemProp> ul,List<ExamOlemProp> nl,List<ExamOlemProp> all,int type){
		
        for(ExamOlemProp a : ul){
        	if(type==1){	      
	        	all.add(a);
        	}
        	for(ExamOlemProp b :a.getNextOlemPropList()){
        		for(ExamOlemProp c: nl){
        			if(b.getX_y().equals(c.getX_y())){
        				c.setParent_id(a.getId());
        				all.add(c);
        			}
        		}
        	}
        }		
		
		return all;
	}
	
	private void serRefBase(List<ExamOlemProp> ul,List<ExamOlemPropRefBaseProp> basePropList){
		
    	for(ExamOlemProp a :ul){
    		for(ExamOlemProp b :a.getNextOlemPropList()){
    			for(ExamOlemPropRefBaseProp c: basePropList){
    				if(b.getX_y().equals(c.getX_y())){
    					c.setOlem_prop_id(a.getId());
    				}
    			}
    		}
    	}
	}

}
