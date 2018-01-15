package com.hys.exam.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-16
 * 
 * 描述：
 * 
 * 说明:
 */
public class OlemImpProp {

	private static String url = "jdbc:oracle:thin:@192.168.191.11:1521:ncme";
	private static String user = "new_exam2";
	private static String password = "new_exam2";
	
	
	public static void main(String[] args) {
		InputStream inStream = null;
		Workbook workBook = null;
		try{
			String fileName = "d:\\aaa.xls";
            inStream = new FileInputStream(fileName);   //输入流
            workBook = Workbook.getWorkbook(inStream);  //工作薄
            Sheet inSheet = workBook.getSheet(0);
            read(inSheet,4,1222L);
		}catch(Exception e){
		}
	}

	
	public static Long getId(String seq){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long id=null;
		String sql ="select "+seq+".nextval from dual";
		try {
			conn = Test1.getConnection(url,user,password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				id=rs.getLong(1);
			}
		} catch (SQLException e) {
		}
		return id;
	}	
	
	private static void read(Sheet inSheet,int type,Long parent_id){        
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
            
            for(ExamOlemProp Ab : allList){
            	System.out.println(Ab.getId()+"\t"+Ab.getParent_id()+"\t"+Ab.getOlem_prop_name()+"\t"+Ab.getOlem_prop_type()+"\t"+Ab.getRelation()+"\t"+Ab.getSys_prop());
            }
            
            for(ExamOlemPropRefBaseProp p:olemAndBaseList){
            	System.out.println(p.getOlem_prop_id()+"-----------------------"+p.getBase_prop_id());
            }
            
            
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
	private static List<ExamOlemProp> getExcelInfoList(Sheet sheet,int col,int type,Long parent_id){
		Cell cell = null;
		List<ExamOlemProp> olemPropList = new ArrayList<ExamOlemProp>();
		
		for(int i=1;i<sheet.getRows();i++){
			ExamOlemProp olemProp = new ExamOlemProp();
			List<ExamOlemProp> nextOlemPropList = new ArrayList<ExamOlemProp>();

			//基础理论
			if(type==2){ 
				if(col==0){
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(5);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1){
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(6);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
				} else if(col==2) {
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(7);
					olemProp.setRelation(0);
					olemProp.setSys_prop(1);					
				}
			}
			//基本知识
			if(type==3){ 
				if(col==0){
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(8);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1){
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(9);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);					
				} else if(col==2) {
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(10);
					olemProp.setRelation(0);
					olemProp.setSys_prop(1);					
				}
			}
			//基本技能
			if(type==4){ 
				if(col==0){
					olemProp.setId(getId("olem_question_prop_seq"));
					olemProp.setX_y(i + "_" + col);
					olemProp.setOlem_prop_type(11);
					olemProp.setRelation(1);
					olemProp.setSys_prop(0);
					olemProp.setParent_id(parent_id);
				} else if(col==1) {
					olemProp.setId(getId("olem_question_prop_seq"));
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
	
	
	
	private static Range findRange(Sheet sheet,int row,int col){
		Range[] ranges = sheet.getMergedCells();
		for(Range space:ranges){ 
	    	if(space.getTopLeft().getColumn() == col && space.getTopLeft().getRow() == row){
	    		return space;
	    	}
		}
		return null;
	}
	
	private static List<ExamOlemPropRefBaseProp> setOlemRefProp(Sheet sheet,int col){
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
		
	private static List<ExamOlemProp> serRef(List<ExamOlemProp> ul,List<ExamOlemProp> nl,List<ExamOlemProp> all,int type){
		
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
	
	private static void serRefBase(List<ExamOlemProp> ul,List<ExamOlemPropRefBaseProp> basePropList){
		
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
