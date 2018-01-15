package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.MessageManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.Message;
import com.hys.exam.model.MessageSendType;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

public class MessageManageJDBCDAO extends BaseDao  implements MessageManageDAO {

	@Override
	public Message getMessageById(Long id) {
		Message message = new Message();
		String sql = "select * from message where id = "+id;
		message=getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Message.class));
		return message;
	}

	@Override
	public List<Message> getMessageList(Message message) {
		List<Object> values = new ArrayList<Object>();
		List<Message> list=new ArrayList<>();
		
		if(message.getId() != null){
			StringBuilder sql = new StringBuilder();  //SELECT a.* FROM advert a LEFT JOIN advert_site a_s on a.ID = a_s.AID where
			sql.append( "select m.* from message m LEFT JOIN message_site ms on m.id = ms.mid where"); 
			sql.append("  m.id = ?");
			values.add(message.getId());
			String sql_sites = "SELECT ms.sid as ID from message_site ms where ms.mid =" + message.getId();
			List<SystemSite> siteList = getJdbcTemplate().query(sql_sites,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Message.class),values.toArray());
			list.get(0).setSiteList(siteList);
			
		}
		else
			{
			StringBuilder query= new StringBuilder( "select m.* from message m LEFT JOIN message_site ms on m.id = ms.mid where  1=1 "); 
			//消息类型   
			if(message.getMessageType()!=null && message.getMessageType() !=0 ){
				query.append(" AND  m.messageType = '"+message.getMessageType()+"'");  
			}
			//消息内容
			if(message.getContent()!=null && !message.getContent().equals("") ){
				query.append(" AND  m.content like ?");  
				values.add("%"+message.getContent()+"%");
			}
			
			//类型 0:未发布  1:已发布
			 if(message.getSendType()!=null && !message.getSendType().equals("") ){
				 query.append(" AND m.state='"+message.getSendType()+"'");
//				values.add(advert.getState());
			}
			 
			//发布开始时间
			if(message.getStart_date()!=null && !message.getStart_date().equals("")){
				query.append(" AND sendTime>'"+message.getStart_date()+"'");
			}
			//发布结束时间
			if(message.getEnd_date()!=null && !message.getEnd_date().equals("")){
				query.append(" AND sendTime<'"+message.getEnd_date()+"'");
			}
				query.append("");
			//排序-------->
			
			query.append(" order by m.sendTime desc ");
			list =  getJdbcTemplate().query(query.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Message.class),values.toArray());	
			for (Message a:list) {
				String sql_site = "select t.* from SYSTEM_SITE t, message_site t2 where t.ID=t2.SID and t2.MID=" + a.getId();
				List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
				if(siteList.size() > 0)
					a.setSiteList(siteList);
			}		
		}
		return list;
	}

	@Override
	public boolean addMessage(Message message) {
		
		message.setId(this.getSequence("MESSAGE"));
		//站点添加
				String site_sql;
				if (message.getSiteList()!=null)
					for(int i=0;i<message.getSiteList().size();i++){
						 site_sql="insert into message_site (MID,SID) values(?,?)";
						 getJdbcTemplate().update(site_sql, message.getId(),message.getSiteList().get(i).getId());
					}
		//邮件表添加
		String sql=""
				+ "INSERT into MESSAGE (messageType,title,content,receiver,creater,sendState) VALUES"
				+ "(:messageType,:title,:content,:receiver,:creater,:sendState)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(message));
		
		
		//添加发送方式
		String send_sql="";
		String type="";
		if (message.getSendcheck()!=null) {
			for (int j = 0; j < message.getSendcheck().length; j++) {
				type=message.getSendcheck()[j];
				message.setSendType(type);
				send_sql="INSERT into message_send_type (sendtype,mid) VALUES (?,?)";
				getJdbcTemplate().update(send_sql, message.getSendType(),message.getId());
				}
		}
		
		return true;
	}

	@Override
	public boolean updateState(Long id, int state) {
		if (id!=null&& id!=0) {
			String sql = "update message set sendState ="+state+" where ID=" + id;
			getJdbcTemplate().execute(sql);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteMessageById(Long id) {
		String sqltype = "DELETE from message_send_type where mid =" + id;
		getJdbcTemplate().execute(sqltype);
		
		/*String sqluser = "delete from message_user where mid=" + id;
		getJdbcTemplate().execute(sqluser);
		*/
		String sql = "delete from message where ID=" + id;
		getJdbcTemplate().execute(sql);
		
		return true;
	}

	@Override
	public void getMessagePageList(PageList pl, Message message) {

		List<Object> values = new ArrayList<Object>();  
		List<Message> list=new ArrayList<>();
		StringBuilder query= new StringBuilder( " select  * FROM message where 1=1 "); 
		//消息类型   
		if(message.getMessageType()!=null && message.getMessageType() !=0 ){
			query.append(" AND  messageType = '"+message.getMessageType()+"'");  
		}
		//消息内容
		if(message.getContent()!=null && !message.getContent().equals("") ){
			query.append(" AND  content like ?");  
			values.add("%"+message.getContent()+"%");
		}
		
		//类型 1:未发送  1:已发送
		 if(message.getSendState()!=null && !message.getSendState().equals("") ){
			 query.append(" AND sendState='"+message.getSendState()+"'");
//			values.add(advert.getState());
		}
		 
		//发布开始时间
		if(message.getStart_date()!=null && !message.getStart_date().equals("")){
			query.append(" AND sendTime>'"+message.getStart_date()+"'");
		}
		//发布结束时间
		if(message.getEnd_date()!=null && !message.getEnd_date().equals("")){
			query.append(" AND sendTime<'"+message.getEnd_date()+"'");
		}
			query.append("");
		//排序-------->
		
		query.append(" order by sendTime desc ");
			
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(query.toString()), values.toArray());
		pl.setFullListSize(fullListSize);
		
		list = getJdbcTemplate().query(PageUtil.getPageSql(query.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(Message.class),values.toArray());
		pl.setList(list);
	}

	@Override
	public List<SystemSite> getSiteListByMessageId(long id) {
		String sql_site = "select * from message_site m,system_site s where m.mid=s.id and m.mid=" + id;
		List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return siteList;
	}

	@Override
	public int getMessagetByName(String title) {
		String sql = "select count(*) from message where title = "+"'"+title+"'";
		int count = 1;
		try {
			count = getJdbcTemplate().queryForInt(sql);
		} catch (DataAccessException e) {
		}
		return count;
	}

	@Override
	public boolean updateMessage(Message message) {
		String sql = "update message set messageType=?, title=?, content=?, receiver=?,creater=?,sendState=? where id=?";
		getSimpleJdbcTemplate().update(sql, message.getMessageType(), message.getTitle(), message.getContent(), message.getReceiver(),message.getCreater(),message.getSendState(),message.getId());
		sql = "delete from message_site where MID=" + message.getId();
		getJdbcTemplate().execute(sql);
		sql = "delete from message_send_type where MID="+message.getId();
		getJdbcTemplate().execute(sql);
	
		if (message.getSiteList()!=null)
			for(SystemSite systemSite: message.getSiteList()){
				sql = "insert into message_site (MID, SID) values (" + message.getId()+ "," + systemSite.getId()+")"; 
				getJdbcTemplate().update(sql);
			}
		//添加发送方式
		String send_sql="";
		String type="";
		if (message.getSendcheck()!=null) {
			for (int j = 0; j < message.getSendcheck().length; j++) {
				type=message.getSendcheck()[j]+"";
				message.setSendType(type);
				send_sql="INSERT into message_send_type (sendtype,mid) VALUES (?,?)";
				getJdbcTemplate().update(send_sql, message.getSendType(),message.getId());
				}
		}
		
		return true;
	}
	//发送邮件，保存在收件箱
	@Override
	public boolean sendMessage(SystemMessageModel messageModel) {
		
		String sql=""
				+ "insert into SYSTEM_MESSAGE (SYSTEM_USER_ID,MESSAGE_CONTENT,IS_READ) values"
				+ "(:system_user_id, :message_content,:is_read)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(messageModel));
		
		return true;
		
		
	}

	@Override
	public List<SystemUser> getUserByType(Integer userType) {
		String sql_site = "SELECT * FROM system_user WHERE USER_TYPE =" + userType;
		List<SystemUser> list = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		return list;
	}

	@Override
	public SystemSite getMessagetBySite(String tempContextUrl) {
		SystemSite site = new SystemSite(); 
		String sql = "SELECT * from system_site where like "+"%"+tempContextUrl+"%";
		site=getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return site;
	}

	@Override
	public boolean insertSendType(MessageSendType sendType) {
		String sql="INSERT into message_send_type (sendtype,mid) VALUES ("+sendType.getSendtype()+","+sendType.getMessage().getId()+")";
		int i = getJdbcTemplate().update(sql);
		if (i>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<MessageSendType> getSendCheck(Long id) {
		String sql = "select * from message_send_type where MID=" + id;
		List<MessageSendType> list = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MessageSendType.class));
		return list;
	}

}
