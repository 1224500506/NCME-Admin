package com.hys.exam.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;

import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;
import com.hys.exam.service.local.ExamImportQuestManage;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ImportQuestAtt {

	private static String url = "jdbc:oracle:thin:@192.168.191.11:1521:ncme";
	private static String user = "new_exam3";
	private static String password = "new_exam3";
	
	public static void main(String args[]){
		
		ExamImportQuestManage client = (ExamImportQuestManage) ApplicationContextUtils
		.getApplicationContext().getBean("localExamImportQuestManage");
		read("d:\\2010_11_24.xls",client);
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
	
	private static void read(String fileName,ExamImportQuestManage client){
		   InputStream inStream = null;
	        Workbook workBook = null;
	        try
	        {
	            inStream = new FileInputStream(fileName);   //输入流
	            workBook = Workbook.getWorkbook(inStream);  //工作薄
//	            Sheet inSheet = workBook.getSheet("基础医学");   //sheet页
	            Sheet inSheet = workBook.getSheet(3);
	            
				List<ExamQuestAtt> c1 = new ArrayList<ExamQuestAtt>();
				List<ExamQuestAtt> c2 = new ArrayList<ExamQuestAtt>();
				List<ExamQuestAtt> c3 = new ArrayList<ExamQuestAtt>();
				List<ExamQuestAtt> c4 = new ArrayList<ExamQuestAtt>();
				List<ExamQuestAtt> c5 = new ArrayList<ExamQuestAtt>();
//				List<ExamQuestAtt> c6 = new ArrayList<ExamQuestAtt>();

				// 填充数据
				c1 = getExcelInfoList(inSheet,0);
				c2 = getExcelInfoList(inSheet,1);
				c3 = getExcelInfoList(inSheet,2);
				c4 = getExcelInfoList(inSheet,3);
				c5 = getExcelInfoList(inSheet,4);
//				c6 = getExcelInfoList(inSheet,5);
				

				
				// 读取关系ID
				setListByCid(c1, c2);
				setListByCid(c2, c3);
				setListByCid(c3, c4);
				setListByCid(c4, c5);
//				setListByCid(c5, c6);

				
				List<ExamQuestAtt> cx = new ArrayList<ExamQuestAtt>();
				
				cx.addAll(c1);
				cx.addAll(c2);
				cx.addAll(c3);
				cx.addAll(c4);
				cx.addAll(c5);
//				cx.addAll(c6);
				client.addQuestProp(cx);

				List<ExamQuestRelation> relationList = new ArrayList<ExamQuestRelation>();
				
				setRef(c1,relationList);
				setRef(c2,relationList);
				setRef(c3,relationList);
				setRef(c4,relationList);
//				setRef(c5,relationList);
				client.addSysPropVal(cx, relationList);
				
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public static void setRef(List<ExamQuestAtt> c,List<ExamQuestRelation> relationList){
		for(ExamQuestAtt att: c){
			relationList.addAll(att.getRelation());
		}
	}
	
	
	public static List<ExamQuestAtt> getExcelInfoList(Sheet sheet,int count) {
		Cell cell = null;
		List<ExamQuestAtt> list = new ArrayList<ExamQuestAtt>();

		for (int i = 1; i < sheet.getRows(); i++) {
			ExamQuestAtt att = new ExamQuestAtt();
			List<ExamQuestRelation> relationList = new ArrayList<ExamQuestRelation>();
			Long id = getId("exam_prop_val_seq");
			Long sysPropId =  getId("sub_sys_prop_val_seq");
			att.setId(id);
			att.setSysPropValId(sysPropId);
			att.setType((count+1));
			// 横纵坐标
			att.setX_y( i + "_" + count);
			
			Range range = findRange(sheet,i,count);
			if(range != null){
			    // 名称
				att.setName(replaceBlank(range.getTopLeft().getContents()));
				if(count<5){
					for (int j = range.getTopLeft().getRow(); j <= range.getBottomRight().getRow(); j++) {
						ExamQuestRelation relation = new ExamQuestRelation();
						relation.setId(sysPropId);
				    	// 横纵坐标
						relation.setX_y( j + "_" + (count+1));
				    	
				    	relationList.add(relation);
					}
					// 关系List
					att.setRelation(relationList);
				}
				i = range.getBottomRight().getRow();
			}else{
				cell = sheet.getCell(count,i);
				// 名称
				att.setName(replaceBlank(cell.getContents()));
				int t = 0;
				do {
					if ((i + 1 + t) == sheet.getRows()) {
						t++;
						break;
					}
					// 得到当前单元格下方单元格
					cell = sheet.getCell(count, i + 1 + t);
					t++;
				} while (cell.getContents().trim().equals(""));
				if (count < 5) {
					for (int j = i; j < i + t; j++) {
						ExamQuestRelation relation = new ExamQuestRelation();
						relation.setId(sysPropId);
						// 横纵坐标
						relation.setX_y(j + "_" + (count + 1));

						relationList.add(relation);
					}
					if (t != 0) {
						i = i + t - 1;
					}
					// 关系List
					att.setRelation(relationList);
				}
			}
			if(!att.getName().trim().equals("")){
				list.add(att);
			}
		}
		return list;
	}
	
	private static Range findRange(Sheet sheet,int row,int count){
		Range[] ranges = sheet.getMergedCells();
		for(Range space:ranges){ 
	    	if(space.getTopLeft().getColumn() == count && space.getTopLeft().getRow() == row){
	    		return space;
	    	}
		}
		return null;
	}
	
	private static void setListByCid(List<ExamQuestAtt> pList,List<ExamQuestAtt> cList) {
		if(pList != null && pList.size() > 0 ){
			for (int i = 0; i < pList.size(); i++) {
				ExamQuestAtt t_Attribute= pList.get(i);
				// 得到关系对象List
				List<ExamQuestRelation> old_attList  = t_Attribute.getRelation();
				// 新的关系对象List
				List<ExamQuestRelation> new_attList = new ArrayList<ExamQuestRelation>(); 
				if(old_attList != null && old_attList.size() > 0 ){
					for (int j = 0; j < old_attList.size(); j++) {
						ExamQuestRelation t_AttributeRelation = old_attList.get(j);
						Long c_id = getIdFindList(cList,t_AttributeRelation.getX_y());
						if(c_id != null){
							t_AttributeRelation.setC_Id(c_id);
							new_attList.add(t_AttributeRelation);
						}
					}
				}
				t_Attribute.setRelation(new_attList);
			}
		}
	}	
	
	private static Long getIdFindList(List<ExamQuestAtt> list, String x_y){
		if(list!= null && list.size() >0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getX_y().trim().equals(x_y.trim())){
					return list.get(i).getSysPropValId();
				}
			}
		}
		return null;
	}
	
	 public static String replaceBlank(String _str)
	 {
		  String str = _str.trim();
		  Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		  Matcher m = p.matcher(str);
		  str = m.replaceAll(""); 
		  str = str.replaceAll("　", "");
		  return str;
	 }	
}
