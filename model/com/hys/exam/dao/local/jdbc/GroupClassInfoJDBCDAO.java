package com.hys.exam.dao.local.jdbc;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.GroupClassInfoDAO;
import com.hys.exam.model.CVT;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class GroupClassInfoJDBCDAO extends BaseDao implements GroupClassInfoDAO {

	private static final String QUERY_CLASS_SQL = "select * from group_class_info where class_id= ? ";
	
	//private static final String QUERY_TREE_SQL =  " select c.id,0 as pId,c.name from cv c where c.id = ? " + " union all " + " select u.id,1 as pId,u.name from cv_ref_unit r left join cv_unit u on r.unit_id = u.id where r.cv_id = ?" ;
	private static final String QUERY_TREE_SQL =  " select c.id,0 as pId,c.name,0 as sequenceNum,0 AS type from cv c where c.id = ? "
	                                            + " union all " 
			                                    + " select u.id,1 as pId,u.name,u.sequenceNum, u.TYPE from cv_ref_unit r left join cv_unit u on r.unit_id = u.id where r.cv_id = ?  order by sequenceNum asc " ; //YHQ，2017-05-26，按照单元顺序排序
	
	private static final String QUERY_SQL = " select * from group_class_info info where 1 = 1 order by info.state desc ";
	
	@Override
	public boolean addGroupClassInfo(GroupClassInfo group) {
		//YHQ，2017-05-17
		String classContentHtml = group.getClassContent() ;
		String mediaType = null , mediaId = null ;
		if (classContentHtml != null) {
			Document htmlDoc = Jsoup.parse(classContentHtml) ;
			if (htmlDoc != null) {
				Elements htmlAllImg = htmlDoc.getElementsByTag("img") ;				
				for (Element imgElt : htmlAllImg) {
					Attributes allProp = imgElt.attributes() ;
					String altAttr = allProp.get("alt") ;
					String urlAttr = allProp.get("_url") ;
					String onclickAttr = allProp.get("onclick") ; //play(this);
					
					if (!StringUtils.checkNull(urlAttr)) {
						if (!StringUtils.checkNull(altAttr)) {
							if (altAttr.equals("paper")) {//试卷
								mediaType = "paper" ;
								mediaId   = urlAttr ;
								break ;
							}
							if (altAttr.equals("bingli")) {//病例
								mediaType = "bingli" ;
								mediaId   = urlAttr ;
								break ;
							}
							if (altAttr.equals("talk")) {//讨论
								mediaType = "talk" ;
								mediaId   = urlAttr ;
								break ;
							}
						}
						if (!StringUtils.checkNull(onclickAttr) && onclickAttr.equals("play(this);")) {
							mediaType = "video" ;
							mediaId   = urlAttr ;
							break ;
						}						
					}					
				}
			}			
		}
				
		String sql = " insert into group_class_info(class_id,class_name,class_parent_id,class_parent_name,class_content,template_type,create_date) "
                    + " values (" + group.getClassId() + ",'" + group.getClassName() + "'," + group.getClassParentId() + ",'" + group.getClassParentName() +"','" 
				    + classContentHtml + "','" + group.getTemplateType() + "',sysdate())";
		
		if (mediaType != null && mediaId != null) {
			sql = " insert into group_class_info(class_id,class_name,class_parent_id,class_parent_name,class_content,template_type,create_date,media_type,media_id) "
                    + " values (" + group.getClassId() + ",'" + group.getClassName() + "'," + group.getClassParentId() + ",'" + group.getClassParentName() +"','" 
				    + classContentHtml + "','" + group.getTemplateType() + "',sysdate(),'" + mediaType + "','" + mediaId + "')";
		}
		
		int result = getSimpleJdbcTemplate().update(sql);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<GroupClassInfo> queryGroupClassContent(int classId){
		List <GroupClassInfo> list = new ArrayList<GroupClassInfo>();
		list = getJdbcTemplate().query(QUERY_CLASS_SQL, ParameterizedBeanPropertyRowMapper.newInstance(GroupClassInfo.class), classId);
		return list;
	} 
	
	@Override
	public void updateGroupClassInfo(GroupClassInfo group) throws Exception {
		//YHQ，2017-05-17
		String classContentHtml = group.getClassContent() ;
		String mediaType = null , mediaId = null ;
		if (classContentHtml != null) {
			Document htmlDoc = Jsoup.parse(classContentHtml) ;
			if (htmlDoc != null) {
				Elements htmlAllImg = htmlDoc.getElementsByTag("img") ;				
				for (Element imgElt : htmlAllImg) {
					Attributes allProp = imgElt.attributes() ;
					String altAttr = allProp.get("alt") ;
					String urlAttr = allProp.get("_url") ;
					String onclickAttr = allProp.get("onclick") ; //play(this);
					
					if (!StringUtils.checkNull(urlAttr)) {
						if (!StringUtils.checkNull(altAttr)) {
							if (altAttr.equals("paper")) {//试卷
								mediaType = "paper" ;
								mediaId   = urlAttr ;
								break ;
							}
							if (altAttr.equals("bingli")) {//病例
								mediaType = "bingli" ;
								mediaId   = urlAttr ;
								break ;
							}
							if (altAttr.equals("talk")) {//讨论
								mediaType = "talk" ;
								mediaId   = urlAttr ;
								break ;
							}
						}
						if (!StringUtils.checkNull(onclickAttr) && onclickAttr.equals("play(this);")) {
							mediaType = "video" ;
							mediaId   = urlAttr ;
							break ;
						}						
					}					
				}
			}			
		}
				
		String sql = "update group_class_info set class_content = ? , template_type = ?   where class_id = ?";		
		
		if (mediaType != null && mediaId != null) {
			sql = "update group_class_info set class_content = ? , template_type = ? , media_type = ? , media_id = ?  where class_id = ?";
			getJdbcTemplate().update(sql,classContentHtml,group.getTemplateType(),mediaType,mediaId,group.getClassId());
		} else {
			getJdbcTemplate().update(sql,classContentHtml,group.getTemplateType(),group.getClassId());
		}
	}

	@Override
	public List<CVT> queryCVTList(int classId) throws Exception {
		List <CVT> list = new ArrayList<CVT>();
		list = getJdbcTemplate().query(QUERY_TREE_SQL, ParameterizedBeanPropertyRowMapper.newInstance(CVT.class), classId,classId);
		return list;
	}
	
	@Override
	public List<GroupClassInfo> queryList() throws Exception {
		List <GroupClassInfo> list = new ArrayList<GroupClassInfo>();
		list = getJdbcTemplate().query(QUERY_SQL, ParameterizedBeanPropertyRowMapper.newInstance(GroupClassInfo.class));
		return list;
	}
        
        //YHQ 2017-03-04，更新课程单元的评分
	@Override
        public boolean updateUnitQuota(Long unitId, Double unitQuota) throws Exception {
            if (unitId != null && unitQuota != null) {
                String sql = "update cv_unit set quota=" + unitQuota + " where id=" + unitId ;
                int updateNum = getJdbcTemplate().update(sql) ;
                if (updateNum > 0) return true ;
            }
            return false ;
        }
        
}
