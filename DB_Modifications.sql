/*********************************************************************************************************************
# ------2017-07-04以前--------------------------------------------------------------------------------------------------
CREATE TABLE cv_set_refereebook (
	cv_set_id INT(18) NOT NULL COMMENT '项目的id(cv_set表的id)',
	book_name VARCHAR(500) NOT NULL COMMENT '书籍名称',
	book_url VARCHAR(500) NOT NULL COMMENT '书籍的网址',
	INDEX cv_set_rb_cvsetid (cv_set_id)
)
COMMENT='项目的书籍推荐'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE cv_set_knowledgebase (
	cv_set_id INT(18) NOT NULL COMMENT '项目id(cv_set表的id)',
	knowledgebase_name VARCHAR(500) NOT NULL COMMENT '指南共识名称',
	knowledgebase_url VARCHAR(500) NOT NULL COMMENT '指南共识网址',
	INDEX cv_set_kb_cvsetid (cv_set_id)
)
COMMENT='项目中的指南共识'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE cv_set_referencelately (
	cv_set_id INT(18) NOT NULL COMMENT '项目id(cv_set表的id)',
	reference_name VARCHAR(500) NOT NULL COMMENT '文献名称',
	reference_url VARCHAR(500) NOT NULL COMMENT '文献网址',
	INDEX cv_set_rl_cvsetid (cv_set_id)
)
COMMENT='项目中最新文献'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

ALTER TABLE group_class_info
	ADD COLUMN media_type VARCHAR(50) NULL DEFAULT NULL COMMENT '媒体类型（null为无类型,paper为试卷,talk为讨论点,bingli为病例,video为视频）' AFTER `comp_index`,
	ADD COLUMN media_id VARCHAR(500) NULL DEFAULT NULL COMMENT '媒体的id就是富文本里的_url值' AFTER `media_type`;

ALTER TABLE cv_unit
	ADD COLUMN sequenceNum INT NULL DEFAULT NULL COMMENT '在课程中出现的顺序，越大越靠后' AFTER `quota`;

# 修复课程单元中在课程中顺序的旧数据：
update cv_unit set sequenceNum = id  where sequenceNum is NULL order by id asc ;

ALTER TABLE cv_schedule
	ADD COLUMN sequenceNum INT NULL DEFAULT NULL COMMENT '课程在项目中的顺序，越大越靠后' AFTER `END_DATE`;

# 修复课程在项目中顺序的旧数据：
update cv_schedule set sequenceNum = cv_id where sequenceNum is NULL and SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID in (select id from cv_set))  order by cv_id asc ;

ALTER TABLE cv
	ADD COLUMN cv_type INT NOT NULL DEFAULT 0 COMMENT '0点播，1面授，2直播（默认的老课程都是点播）' AFTER `CREATOR`;

ALTER TABLE cv_set
	ADD COLUMN cv_set_type INT NOT NULL DEFAULT 0 COMMENT '0继教项目，1乡医培训（默认为0，老的项目都是0）' AFTER `RELATION_QUIZ`;

CREATE TABLE cv_set_schedule_faceteach (
	cv_set_id INT NOT NULL COMMENT '来自cv_set表的id',
	class_name VARCHAR(100) NOT NULL COMMENT '培训班次名称',
	people_number INT NULL COMMENT '面授人数',
	train_place VARCHAR(200) NULL COMMENT '培训地点',
	train_starttime DATETIME NULL COMMENT '培训开始时间',
	train_endtime DATETIME NULL COMMENT '培训结束时间',
	contact_way VARCHAR(200) NULL COMMENT '联系方式',
	sequenceNum INT(11) NULL DEFAULT NULL COMMENT '顺序，越大越靠后'
)
COMMENT='项目面授班次安排'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

# --------2017-07-04---------------------------------------------------------------------------------------------------
ALTER TABLE content_edition_ref
ADD COLUMN sort  int(11) NULL DEFAULT 0 AFTER CHECK_STATE;
update content_edition_ref t set t.sort = t.ordernum where t.sort =0;

ALTER TABLE system_site DROP FOREIGN KEY system_site_ibfk_1;
ALTER TABLE system_site_course_type DROP FOREIGN KEY system_site_course_type_ibfk_1;

ALTER TABLE log_exam_result ADD COLUMN REALITYSCORE DOUBLE(8,2) NULL DEFAULT NULL AFTER SCORE;

# --------2017-07-04 12:00:00---taoliang
ALTER TABLE `group_class_info`
MODIFY COLUMN `class_content`  varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程内容' AFTER `class_parent_name`;

# --------2017-07-05 14:50:00---taoliang
ALTER TABLE `cv_set`
MODIFY COLUMN `SCORE`  double(20,2) NULL DEFAULT NULL AFTER `LEVEL`;

# -------2017-07-09-----陈来滨(合并7月8号的，已更新到213和ncme)
update exam_question_label set IS_CHILD = 0 where id = 4 ;
update exam_question_label set IS_CHILD = 1 where id = 5 ;
update exam_question_label set name = 'A3A4主题干' where id = 3 ;

# -------2017-07-09-----杨红强(前台学习课程单元中的考试用，已更新到213和ncme)
ALTER TABLE log_exam_result
	DROP PRIMARY KEY,
	ADD PRIMARY KEY (LOG_EXAM_ID, QUESTION_ID);
ALTER TABLE log_exam_result
	CHANGE COLUMN SELECT_RESULT SELECT_RESULT VARCHAR(1000) NULL DEFAULT NULL AFTER REALITYSCORE;

# -------2017-07-09-----郑州王豪骞(已更新到213和ncme)
ALTER TABLE cv_diploma
	ADD COLUMN ITEM_SCORE VARCHAR(10) NULL DEFAULT NULL COMMENT '学分' AFTER END_TIME;

# -------2017-07-10-----杨红强(部分试题这两个字段值错误导致前台答题报错，已更新到213和ncme)
update exam_question set CREATE_DATE = CURRENT_TIMESTAMP where date_format(CREATE_DATE, '%Y%m%d') = '00000000' ;
update exam_question set ONLINE_DATE = CURRENT_TIMESTAMP where date_format(ONLINE_DATE, '%Y%m%d') = '00000000' ;

# -------2017-07-12-----杨红强(增加了无学分的项目级别，)
update cv_set set level = 6 where score in ( 0.00 , 0 , 0.0 ) ;

# -------2017-07-13-----郑州王豪骞(修改CV表的结构，支持多课程标签)
ALTER TABLE `cv` MODIFY COLUMN `BRAND`  varchar(20) NULL DEFAULT NULL AFTER `SERIAL_NUMBER`;

# --------2017-07-04--------杨先腾(新增视频实际学习时间统计表)
CREATE TABLE `log_study_cv_unit_video_census` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SYSTEM_USER_ID` int(18) NOT NULL COMMENT '用户ID',
  `CV_SET_ID` int(18) NOT NULL COMMENT '项目ID',
  `CV_ID` int(18) NOT NULL COMMENT '课程ID',
  `CV_UNIT_ID` int(18) NOT NULL COMMENT '单元ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `videoEchoLength` int(18) DEFAULT NULL COMMENT '视频回放时间',
  `videoStartLength` int(18) DEFAULT NULL COMMENT '视频播放开始时间',
  `videoEndLength` int(18) DEFAULT NULL COMMENT '视频播放结束时间',
  `videoLength` int(18) DEFAULT NULL COMMENT '视频播放总时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# -------2017-07-14-----taoliang添加直播学习考核情况表
DROP TABLE IF EXISTS `cv_live_study_ref`;
CREATE TABLE `cv_live_study_ref` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `class_no` varchar(100) DEFAULT NULL COMMENT '直播房间ID',
  `start_time` bigint(32) DEFAULT 0 COMMENT '直播实际开始时间',
  `end_time` bigint(32) DEFAULT 0 COMMENT '直播实际结束时间',
  `total_live_length` int(18) DEFAULT 0 COMMENT '直播各场次累计时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cv_live_study`;
CREATE TABLE `cv_live_study` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) DEFAULT NULL COMMENT '学生 ID (当没有给展示互动uid时，为展示互动随机生成)',
  `class_no` varchar(100) DEFAULT NULL,
  `nickname` varchar(200) DEFAULT NULL COMMENT '参课昵称',
  `mobile` varchar(50) DEFAULT NULL,
  `joinTime` bigint(32) DEFAULT NULL COMMENT '加入时间',
  `leaveTime` bigint(32) DEFAULT NULL COMMENT '离开时间',
  `ip` varchar(100) DEFAULT NULL,
  `device` int(11) DEFAULT NULL COMMENT '设备类型 0  PC 客户端  1  PC Web 端  2  PC Web 端(http流)  3  IPAD Web 端  4  IPHONE Web端  5  APAD Web 端  6  APHONE Web 端 7  IPAD APP 端 8  IPHONE APP 端 9  APAD APP 端 10 APHONE APP 端 11 MAC 客户端 12  电话端 16  PLAYER SDK  IOS 端 17  PLAYER SDK  安卓端 ',
  `useful_length` int(18) DEFAULT 0 COMMENT '学员本场直播中参与的有效时间长',
  `company` varchar(250) DEFAULT NULL,
  `area` varchar(500) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# -------2017-07-19-----杨红强增加考试成绩长度
ALTER TABLE log_exam CHANGE COLUMN RESULT RESULT DOUBLE(8,1) NULL DEFAULT NULL AFTER RIGHT_RATE;

# -------2017-07-21-----王豪骞(修改exam_examination表的结构，CREATE_TIME的格式从yyyy-mm-dd 改成 yyyy-mm-dd hh:mm:ss )
ALTER TABLE `exam_examination`
MODIFY COLUMN `CREATE_TIME`  datetime NULL DEFAULT NULL AFTER `PAPER_SCORING`;

# -------2017-07-25-----杨红强删除垃圾站点数据
ALTER TABLE system_site AUTO_INCREMENT=100;
delete from system_site_course_type_exam where SITE_ID in (4,5,6,7) ;
delete from system_resource_site where SITE_ID in (4,5,6,7) ;
delete from system_org_unit where SITE_ID in (4,5,6,7) ;
delete from system_site  where id in (4,5,6,7) ;

# -------2017-07-27-----yxt直播回放课件观看记录表
CREATE TABLE `cv_live_courseware_study` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `uid` varchar(30) DEFAULT NULL COMMENT '学生ID',
  `coursewareId` varchar(30) DEFAULT NULL COMMENT '课件ID',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `joinTime` bigint(32) DEFAULT NULL COMMENT '加入时间',
  `leaveTime` bigint(32) DEFAULT NULL COMMENT '离开时间',
  `duration` varchar(30) DEFAULT NULL COMMENT '学员观看时长',
  `ip` varchar(30) DEFAULT NULL,
  `device` int(11) DEFAULT NULL COMMENT '终端类型：0-PC;1-Mac;2-Linux;4-Ipad;8-Iphone;16-AndriodPad;32-AndriodPhone;132-IPad(PlayerSDK);136-IPhone(PlayerSDK);144-AndriodPad(PlayerSDK);256-AndriodPhone(PlayerSDK);237-移动设备;',
  `recordDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# -------2017-07-28-----taoliang
ALTER TABLE `cv_unit`
ADD COLUMN `unit_make_type`  int(4) NULL DEFAULT 0 COMMENT '0.创建直播课程时所生成单元 1.直播回放单元 2.直播课程设置成点播单元' AFTER `sequenceNum`;

ALTER TABLE `cv_live`
ADD COLUMN `course_make_type`  int NULL DEFAULT 0 COMMENT '0.直播 1.回放 2.点播' AFTER `modify_date`;

# -------Table structure for `cv_live_courseware`----------------------------
DROP TABLE IF EXISTS `cv_live_courseware`;
CREATE TABLE `cv_live_courseware` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `courseware_no` varchar(100) DEFAULT NULL COMMENT '课件 ID',
  `courseware_num` varchar(50) DEFAULT NULL COMMENT '编号',
  `courseware_url` varchar(500) DEFAULT NULL COMMENT '课件的URL',
  `courseware_token` varchar(50) DEFAULT NULL,
  `subject` varchar(500) DEFAULT NULL COMMENT '课件名字',
  `class_no` varchar(100) DEFAULT NULL COMMENT '所属课堂SDK ID',
  `courseware_create_time` bigint(32) DEFAULT NULL COMMENT '课件创建时间',
  `duration` bigint(32) DEFAULT '0' COMMENT '课件总时长',
  `screenshot` varchar(500) DEFAULT NULL COMMENT '文档的截屏 ',
  `recordId` bigint(32) DEFAULT NULL COMMENT '资源 ID ',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `cv_id` int(18) DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# -------Table structure for `cv_live_ref_unit`----------------------------
DROP TABLE IF EXISTS `cv_live_ref_unit`;
CREATE TABLE `cv_live_ref_unit` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `cv_id` int(18) DEFAULT NULL,
  `unit_id` int(18) DEFAULT NULL,
  `courseware_no` varchar(100) DEFAULT NULL COMMENT '课件SDK ID',
  `class_no` varchar(100) DEFAULT NULL COMMENT '直播间SDK ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# -------2017-08-03-----王豪骞(后台页面增加生成首页的菜单，使用前要针对角色授权)
INSERT INTO system_menu (`NAME`, `SYSTEM_TYPE`, `MENU_TYPE`, `URL`, `state`) VALUES
('生成首页', '后台管理系统', '系统维护', '/systemManage/getAccounts.do?method=executeHtml', '1');

# -------2017-08-08-----taoliang添加课程学习记录表
DROP TABLE IF EXISTS `log_study_cv`;
CREATE TABLE `log_study_cv` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `SYSTEM_USER_ID` int(18) DEFAULT NULL COMMENT '用户ID',
  `CV_SET_ID` int(18) DEFAULT NULL COMMENT '项目ID',
  `CV_ID` int(18) DEFAULT NULL COMMENT '课程ID',
  `STATE` int(2) DEFAULT 1 COMMENT '学习状态：1.未完成 2.已完成',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `cv_live_study`
CHANGE COLUMN `uid` `user_id`  bigint(32) NULL DEFAULT NULL COMMENT '学生 ID (当没有给展示互动user_id时，为展示互动随机生成)' AFTER `id`;

ALTER TABLE `cv_live_courseware_study`
CHANGE COLUMN `uid` `user_id`  bigint(32) NULL DEFAULT NULL COMMENT '学生ID' AFTER `id`;

ALTER TABLE case_course_disease	CHANGE COLUMN COMPLAINT COMPLAINT VARCHAR(500) NULL DEFAULT NULL AFTER DISEASETYPE;

# 下面地址为初始化log_study_cv和log_study_cv_set表，需要在上线的时候执行一下
# 页面地址： http://{IP}/qiantai_page/initDemo.jsp
# 比如： http://101.200.85.213:8090/qiantai2/qiantai_page/initDemo.jsp
#
# 初始化视频统计表：
# select count(*) from log_study_cv_unit_video_census where videoEchoLength < 0  ;
# select count(*) from log_study_cv_unit_video_census where videoEndLength > videoLength  ;
# select count(*) from log_study_cv_unit_video_census where videoStartLength > videoLength  ;
#
# update log_study_cv_unit_video_census set videoEchoLength = 0  where videoEchoLength < 0  ;
# update log_study_cv_unit_video_census set videoEndLength = videoLength  where   videoEndLength > videoLength ;
# update log_study_cv_unit_video_census set videoStartLength = 0  where   videoStartLength > videoLength ;
#
# 病例管理 -> 创建病例 时，病例时间分三个字段保存
# alter table case_course_disease add column DISEASEYEAR varchar(10) null;
# alter table case_course_disease add column DISEASEMONTH varchar(10) null;
# alter table case_course_disease add column DISEASEDAY varchar(10) null;

# -------2017-08-15-----李亚杰(后台增加登录限制，角色授权此功能于对应人员)-----------------------------------
INSERT INTO system_menu (`NAME`, `SYSTEM_TYPE`, `MENU_TYPE`, `URL`, `state`) VALUES
('登录限制', '后台管理系统', '系统维护', '/system/loginConfig.do', '1');

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `system_login_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` int(11) DEFAULT NULL COMMENT '限制登录时间间隔',
  `login_num` int(11) DEFAULT NULL COMMENT '给定时间内限制登录次数',
  `lock_time` int(11) DEFAULT NULL COMMENT '错误超频后锁定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

# ---------Records of system_login_limit------------------------------
INSERT INTO `system_login_limit` VALUES ('1', '1', '5', '1');

ALTER TABLE `system_user`
ADD COLUMN `login_error_num`  int NULL DEFAULT 0 AFTER `other_hospital_name`,
ADD COLUMN `login_error_first_time`  bigint NULL DEFAULT 0 AFTER `login_error_num`,
ADD COLUMN `login_error_last_time`  bigint NULL DEFAULT 0 AFTER `login_error_first_time`;

# ---登录限制表结构修改----
update system_user set login_error_first_time=0,login_error_last_time=0,login_error_num=0;
# -------2017-08-15-----李亚杰(后台增加登录限制，角色授权此功能于对应人员)-----------------------------------

# -------2017-08-15-----taoliang添加直播状态排序表
DROP TABLE IF EXISTS `cv_live_type_sort`;
CREATE TABLE `cv_live_type_sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(4) DEFAULT NULL COMMENT '直播状态  1.正在直播  2.即将开课  3.已经结束  4.观看回放 5.未完成转码',
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cv_live_type_sort
-- ----------------------------
INSERT INTO `cv_live_type_sort` VALUES ('1', '1', '正在直播');
INSERT INTO `cv_live_type_sort` VALUES ('2', '2', '即将开课');
INSERT INTO `cv_live_type_sort` VALUES ('3', '4', '观看回放');
INSERT INTO `cv_live_type_sort` VALUES ('4', '3', '已经结束');
INSERT INTO `cv_live_type_sort` VALUES ('5', '5', '未完成转码');


# -------2017-08-15-----王豪骞(增加关键步骤日志记录功能)-----------------------------------
CREATE TABLE `system_operate_log` (
`operate_id`  int(11) NOT NULL AUTO_INCREMENT ,
`visit_ip`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`request_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`operate_login_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`operate_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`operate_context`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`operate_platform`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`module_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL ,
`operate_time`  datetime NOT NULL ,
`create_time`  datetime NOT NULL ,
PRIMARY KEY (`operate_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=274
ROW_FORMAT=DYNAMIC
;
-- 增加对应菜单  （需要授权）
INSERT INTO `system_menu` ( `NAME`, `SYSTEM_TYPE`, `MENU_TYPE`, `URL`, `state`) VALUES ( '审查日志', '后台管理系统', '系统维护', '/systemManage/getMenu.do?method=systemlog', '1');

# -------2017-08-15-----王豪骞(增加关键步骤日志记录功能)-----------------------------------

# -------2017-08-22-----薛红(修改表字段PROP、ANALYSIS长度由200 修改为500 说明：病例管理-> 页面 "讨论点"、"解析" 输入栏位限制500个字，数据库字段设置为  200个长度)-----------
ALTER TABLE case_course_discuss	CHANGE COLUMN PROP PROP VARCHAR(500) NULL DEFAULT NULL AFTER DISEASEID;
ALTER TABLE case_course_discuss	CHANGE COLUMN ANALYSIS ANALYSIS VARCHAR(500) NULL DEFAULT NULL AFTER PROP;

# -------2017-09-06-----王豪骞(修改system_operate_log表的结构，记录登录用户的user_id)
ALTER TABLE `system_operate_log` ADD COLUMN `user_id`  int(18) NULL AFTER `request_url`;

**********************************************************************************************************************/

/************************************** 以下SQL在下次上线时需要在线上数据库执行**********************************************/
# -------2017-09-06-----薛红(修改表字段DURATION 长度由300 修改为500 说明：素材管理->素材管理->新增素材,素材名称过长时报错)-----------
ALTER TABLE material_info CHANGE COLUMN SAVEPATH SAVEPATH VARCHAR(500) NULL DEFAULT NULL AFTER DURATION;

# ---- 修改历史操作数据。
# 使用
UPDATE system_operate_log l,system_user u set l.user_id=u.ID WHERE l.operate_login_name=u.REAL_NAME and  l.user_id is NULL;
UPDATE system_operate_log l,system_account a set l.user_id=a.USER_ID where l.operate_login_name=a.ACCOUNT_NAME and l.user_id is NULL;
# -- 删除不可恢复的错误数据
DELETE  from system_operate_log  where operate_login_name='';
DELETE  from system_operate_log  where operate_login_name is NULL;

# -------2017-09-14-----taoliang
ALTER TABLE `cv_live_study_ref`
ADD COLUMN `audience_num`  int(18) NULL DEFAULT 0 COMMENT '直播实时观看人数' AFTER `total_live_length`;
# -------2017-09-18-----杨先腾(卡类型管理-卡类型价格)
alter table system_card_type modify column PRICE DOUBLE(8,2);

# -------2017-09-27-----王豪骞(修正system_operate_log 中文字描述：登陆改成登录)
UPDATE system_operate_log set module_name='登录',operate_context=REPLACE(operate_context, '登陆','登录') where module_name LIKE '%登陆%';
# -------2017-10-19-----王豪骞(修正system_operate_log 中绑定学习卡查询错误)
UPDATE system_operate_log set operate_type='qt_bindCard' where operate_platform='qiantai' and operate_type='bind';
# --  修改生成首页
UPDATE system_menu set URL='/systemManage/executeHtml.do?method=executeHtml' WHERE ID=62 and  `NAME`='生成首页';

# -------2017.10.26--------分布式数据下修改以下表结构
ALTER TABLE `system_card_user`
add column `id` int(18) not null auto_increment AFTER `BIND_DATE`, add primary key(id);

ALTER TABLE `qm_user_image_prop`
add column `ID` int(18) not null auto_increment FIRST, add primary key(ID);

ALTER TABLE `system_account_role` drop foreign key `system_account_role_ibfk_1` ;


# ------2017.11.16--------乡医表添加数据
INSERT INTO `xiangyi_province` VALUES ('7', '新疆', '7', '111000031');


# -------2017.11.20--------杨先腾	专家表新加性别、学历、专项能力项目师资等字段
ALTER TABLE `expert_info`
ADD COLUMN `specialability` varchar(30) NULL DEFAULT NULL COMMENT '专项能力项目师资' AFTER `CODE`;
ALTER TABLE `expert_info`
ADD COLUMN `GRADE` char(1) NULL DEFAULT 0 COMMENT '学历' AFTER `CODE`;
ALTER TABLE `expert_info`
ADD COLUMN `SEX` char(1) NULL DEFAULT 0 COMMENT '性别' AFTER `CODE`;

# -------2017.11.21--------杨先腾	专家所属委员会、学组	——	新老数据合并
UPDATE expert_info e 
SET e.GROUPID = (SELECT egv.GROUPID FROM expert_group_val egv LEFT JOIN expert_group eg ON egv.GROUPID = eg.ID 
WHERE eg.PARENT = 0 AND egv.EXPERTID = e.ID GROUP BY egv.EXPERTID), 
e.SUBGROUPID = (SELECT egv.GROUPID FROM expert_group_val egv LEFT JOIN expert_group eg ON egv.GROUPID = eg.ID 
WHERE eg.PARENT != 0 AND egv.EXPERTID = e.ID GROUP BY egv.EXPERTID) WHERE e.ID IN (SELECT DISTINCT ev.EXPERTID FROM expert_group_val ev);

# -------2017.11.21--------杨先腾	专家所属委员会、学组	——	清除冲突关联关系
UPDATE expert_info
SET GROUPID = NULL
WHERE
	ID IN (
		SELECT
			a.id
		FROM
			(
				SELECT
					e.id
				FROM
					expert_info e
				LEFT JOIN expert_office_val eo ON e.ID = eo.EXPERTID
				WHERE
					e.GROUPID IS NOT NULL
				AND e.SUBGROUPID IS NULL
				AND eo.EXPERTID IS NULL
			) a
	);

# -------2017.11.23--------杨先腾	专家信息	——	删除上个版本功能的问题数据
DELETE
FROM
	expert_office_val
WHERE
	expertid IN (
		SELECT
			a.expertid
		FROM
			(
				SELECT
					eo.EXPERTID
				FROM
					expert_office_val eo
				LEFT JOIN expert_info e ON e.ID = eo.EXPERTID
				WHERE
					e.GROUPID IS NOT NULL
				AND e.SUBGROUPID IS NULL
				AND eo.OFFICEID > 3
			) a
	)
	AND OFFICEID IN (4, 5);

# -------2017.11.22--------杨先腾	专家信息	——	修复上个版本功能的问题数据
UPDATE expert_info
SET OFFICE = 5
WHERE
	ID IN (
		SELECT
			a.ID
		FROM
			(
				SELECT
					e.ID
				FROM
					expert_info e
				LEFT JOIN expert_office_val eo ON e.ID = eo.EXPERTID
				WHERE
					e.SUBGROUPID IS NOT NULL
				AND eo.OFFICEID IS NULL
				AND (e.OFFICE = 0 OR e.OFFICE IS NULL)
			) a
	);

# -------2017.11.23--------杨先腾	专家信息	——	从专家表把专家职务中间表没有的职务关系导入
INSERT INTO expert_office_val SELECT
	a.OFFICE,
	a.ID
FROM
	(
		SELECT
			e.OFFICE,
			e.ID
		FROM
			expert_info e
		LEFT JOIN expert_office_val eo ON e.ID = eo.EXPERTID
		WHERE
			e.SUBGROUPID IS NOT NULL
		AND eo.OFFICEID IS NULL
	) a;
	
# -------2017.11.23--------杨先腾	专家信息	——	给在学组中没有委员职务的专家添加委员职务
INSERT INTO expert_office_val SELECT
	5,
	a.EXPERTID
FROM
	(
		SELECT
			eo.EXPERTID
		FROM
			expert_office_val eo LEFT JOIN expert_info e ON eo.EXPERTID = e.ID
		WHERE
			e.SUBGROUPID IS NOT NULL
		AND eo.EXPERTID NOT IN (
			SELECT
				eoo.EXPERTID
			FROM
				expert_office_val eoo
			WHERE
				eoo.OFFICEID = 5
		)
	) a;

# -------2017.11.23--------杨先腾	专家信息	——	重置错误在职状态和离职时间
UPDATE expert_info SET STATE = 0, BREAKDATE = NULL WHERE ID IN (SELECT a.id FROM (SELECT e.ID FROM expert_info e WHERE e.GROUPID IS NULL) a);

# -------2017.11.21--------taoliang修改菜单栏医院信息
UPDATE system_menu SET NAME = '医院',URL = '/propManage/hospitalList.do' WHERE id = 9;

# -------2017.11.24--------taoliang来源添加作者和网址
ALTER TABLE `exam_source_val`
ADD COLUMN `author`  varchar(200) NULL AFTER `STATE`,
ADD COLUMN `website`  varchar(1000) NULL AFTER `author`;

# -------2017.11.27--------taoliang素材添加分类目录及创建分类目录表
ALTER TABLE `material_info`
ADD COLUMN `material_catalog`  varchar(200) NULL COMMENT '分类目录' AFTER `CREATORID`;
ALTER TABLE `material_info`
ADD COLUMN `categoryIds`  varchar(200) NULL COMMENT '分类目录标识' AFTER `material_catalog`;


DROP TABLE IF EXISTS `material_category_menu`;
CREATE TABLE `material_category_menu` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `code` varchar(200) DEFAULT NULL,
  `pcode` varchar(200) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

# --------2017.11.28----luobw  ---站点/广告---中间表
DROP TABLE IF EXISTS `advert_site`;
CREATE TABLE `advert_site` (
  `AID` int(18) DEFAULT NULL,
  `SID` int(18) DEFAULT NULL,
  KEY `AID` (`AID`),
  KEY `SID` (`SID`),
  CONSTRAINT `advert_site_ibfk_1` FOREIGN KEY (`AID`) REFERENCES `advert` (`ID`),
  CONSTRAINT `advert_site_ibfk_2` FOREIGN KEY (`SID`) REFERENCES `system_site` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#--------2017-11-14----------薛红---------------用户表----------------------
ALTER TABLE system_user
	ADD COLUMN grassroot int(1) NULL DEFAULT NULL COMMENT '是否来自基层 1:是  0:否' AFTER `last_update_date`;


# -------2017.12.01--------杨先腾	授权信息相关表
CREATE TABLE `cv_set_authorization` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '授权名称',
  `CV_SET_ID` int(18) NOT NULL COMMENT '项目ID',
  `CV_SET_SERIAL_NUMBER` varchar(255) DEFAULT NULL COMMENT '项目授权编号',
  `CV_SET_LEVEL` int(2) DEFAULT NULL COMMENT '项目授权级别',
  `CV_SET_SCORE` DECIMAL(20,2) DEFAULT NULL COMMENT '项目授权学分',
  `CV_SET_COST_TYPE` int(2) DEFAULT '0' COMMENT '0-免费,1-学习卡,2-收费',
  `CV_SET_COST` DECIMAL(20,0) DEFAULT NULL COMMENT 'CV_SET_COST_TYPE 设置为收费时使用该字段',
  `CV_SET_SIGN` varchar(255) DEFAULT NULL COMMENT '项目授权标签',
  `CV_SET_START_DATE` date DEFAULT NULL COMMENT '项目授权开始时间',
  `CV_SET_END_DATE` date DEFAULT NULL COMMENT '项目授权结束时间',
  `CV_SET_REGISTRATION_STOP` int(18) DEFAULT NULL COMMENT '项目报名授权截止时间',
  `CV_SET_BREAK_DAYS` int(20) DEFAULT NULL COMMENT '项目课程授权学习间隔时间',  
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='项目授权信息表';

CREATE TABLE `cv_set_authorization_cv_schedule` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `AUTHORIZATION_ID` int(18) NOT NULL COMMENT '授权信息ID - cv_set_authorization',
  `CV_SCHEDULE_ID` int(18) NOT NULL COMMENT '原课程有效时间表SCHEDULE_ID - cv_schedule', 
  `START_DATE` date DEFAULT NULL COMMENT '课程授权开始时间',
  `END_DATE` date DEFAULT NULL COMMENT '课程授权结束时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='中间表（项目课程授权有效时间）';

CREATE TABLE `cv_set_authorization_region` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `AUTHORIZATION_ID` int(18) NOT NULL COMMENT '授权信息ID - cv_set_authorization',
  `PROP_VAL_ID` int(18) NOT NULL COMMENT '属性ID(省份:TYPE=20) - exam_prop_val', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='中间表（项目授权区域）';

CREATE TABLE `cv_set_authorization_system_site` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `AUTHORIZATION_ID` int(18) NOT NULL COMMENT '授权信息ID - cv_set_authorization',
  `SYSTEM_SITE_ID` int(18) NOT NULL COMMENT '站点ID - system_site', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='中间表（项目授权站点）';

# -------2017.12.01--------杨先腾	授权信息相关老数据导入
INSERT INTO cv_set_authorization (
	CV_SET_ID,
	CV_SET_SERIAL_NUMBER,
	CV_SET_LEVEL,
	CV_SET_SCORE,
	CV_SET_COST_TYPE,
	CV_SET_COST,
	CV_SET_SIGN,
	CV_SET_START_DATE,
	CV_SET_END_DATE,
	CV_SET_BREAK_DAYS
) SELECT
	cs.ID,
	cs.SERIAL_NUMBER,
	cs.`LEVEL`,
	cs.SCORE,
	cs.cost_type,
	cs.COST,
	cs.SIGN,
	cs.START_DATE,
	cs.END_DATE,
	cs.BREAK_DAYS
FROM
	cv_set cs
WHERE
	cs.`STATUS` IN (5, 6);

INSERT INTO cv_set_authorization_cv_schedule (
	AUTHORIZATION_ID,
	CV_SCHEDULE_ID,
	START_DATE,
	END_DATE
) SELECT
	csa.ID,
	cs.SCHEDULE_ID,
	cs.START_DATE,
	cs.END_DATE
FROM
	cv_set_authorization csa
LEFT JOIN cv_set_schedule css ON css.CV_SET_ID = csa.CV_SET_ID
LEFT JOIN cv_schedule cs ON cs.SCHEDULE_ID = css.CV_SCHEDULE_ID;

INSERT INTO cv_set_authorization_region (
	AUTHORIZATION_ID,
	PROP_VAL_ID
) SELECT
	csa.ID,
	cr.REGION_ID
FROM
	cv_set_authorization csa
LEFT JOIN cv_region cr ON cr.CV_SET_ID = csa.CV_SET_ID WHERE cr.REGION_ID IS NOT NULL;

INSERT INTO cv_set_authorization_system_site (
	AUTHORIZATION_ID,
	SYSTEM_SITE_ID
) SELECT
	csa.ID,
	csss.SYSTEM_SITE_ID
FROM
	cv_set_authorization csa
LEFT JOIN cv_set_system_site csss ON csss.CV_SET_ID = csa.CV_SET_ID WHERE csss.SYSTEM_SITE_ID IS NOT NULL;

# -------2017.12.08--------杨先腾	修复cv_set_authorization_region表错误数据
UPDATE cv_set_authorization_region SET PROP_VAL_ID = 700010939 WHERE PROP_VAL_ID = 700037650;
UPDATE cv_set_authorization_region SET PROP_VAL_ID = 700010961 WHERE PROP_VAL_ID = 700037672;
UPDATE cv_set_authorization_region SET PROP_VAL_ID = 700010962 WHERE PROP_VAL_ID = 700037673;

# -------2017.12.01--------王豪骞 根据新需求调整面授项目排期表
ALTER TABLE `cv_set_schedule_faceteach`
MODIFY COLUMN `train_starttime`  datetime NULL DEFAULT NULL COMMENT '培训开始时间' AFTER `people_number`,
MODIFY COLUMN `train_endtime`  datetime NULL DEFAULT NULL COMMENT '培训结束时间' AFTER `train_starttime`,
ADD COLUMN `lession_starttime`  varchar(20) NULL DEFAULT NULL COMMENT '上课开始时间' AFTER `train_endtime`,
ADD COLUMN `lession_endtime`  varchar(20) NULL DEFAULT NULL COMMENT '上课结束时间' AFTER `lession_starttime`,
ADD COLUMN `route_way`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式' AFTER `contact_way`;


-- 修改课程相关
ALTER TABLE `cv`
ADD COLUMN `is_add_out_chain`  int(11) NOT NULL default 0 COMMENT '0 否 1 是 是否添加外链' AFTER `cv_type` ,
ADD COLUMN `cv_url`  varchar(100) NULL COMMENT '外链URL' AFTER `is_add_out_chain`,
ADD COLUMN `cv_address`  varchar(200) NULL COMMENT '面授课程地址' AFTER `cv_url`,
ADD COLUMN `is_need_apply`  int(11) NOT NULL default 0 COMMENT '是否需要报名' AFTER `cv_address`,
ADD COLUMN `apply_num`  varchar(100) NULL COMMENT '报名人数' AFTER `is_need_apply`;

# --2017-12-12-----以后是新加表

# ---反馈表------罗博文
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATER` varchar(25) DEFAULT NULL COMMENT '用户名、操作人',
  `TELPHONE` varchar(25) DEFAULT NULL COMMENT '手机号',
  `CONTENT` varchar(50) DEFAULT NULL COMMENT '反馈内容',
  `SYSTEM` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `BACKTIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间 ',
  `STATE` int(18) DEFAULT NULL COMMENT '回复的状态   1未回复   2已回复',
  `IMAGE` varchar(100) DEFAULT NULL COMMENT '截图',
  `SYSTEMVERSION` varchar(50) DEFAULT NULL COMMENT '操作系统版本',
  `SITE` varchar(50) DEFAULT NULL COMMENT '用户请求中获取站点',
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
# ----反馈表/站点--------罗博文
DROP TABLE IF EXISTS `feedback_site`;
CREATE TABLE `feedback_site` (
  `FID` int(11) DEFAULT NULL,
  `SID` int(11) DEFAULT NULL,
  KEY `FID` (`FID`),
  KEY `SID` (`SID`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# ------回复表----罗博文
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REPLY_CONTENT` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `REPLYER` varchar(255) DEFAULT NULL COMMENT '回复人',
  `REPLY_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `REPLY_TYPE` int(11) DEFAULT NULL COMMENT '默认系统消息   1:系统消息 2:其他',
  `FID` int(11) DEFAULT NULL COMMENT '外键:评论的外键:一对多',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
# ---消息表------罗博文
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `messageType` int(20) DEFAULT NULL COMMENT '消息类型',
  `title` varchar(20) NOT NULL COMMENT '邮件标题',
  `content` varchar(2000) NOT NULL COMMENT '消息内容',
  `receiver` varchar(20) DEFAULT NULL COMMENT '接收人',
  `sendTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '操作人',
  `sendState` int(18) DEFAULT NULL COMMENT '发送的状态  1:未发送   2:已发送',
  `reciverId` int(18) DEFAULT NULL COMMENT '接受用户的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
# ------消息/站点--------罗博文
DROP TABLE IF EXISTS `message_site`;
CREATE TABLE `message_site` (
  `MID` int(11) DEFAULT NULL,
  `SID` int(11) DEFAULT NULL,
  KEY `MID` (`MID`),
  KEY `SID` (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# -------罗博文----message/发送方式--一对多
DROP TABLE IF EXISTS `message_send_type`;
CREATE TABLE `message_send_type` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `SENDTYPE` int(20) DEFAULT NULL COMMENT '发送的方法   1:平台消息  2:短息',
  `MID` int(18) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MID` (`MID`),
  CONSTRAINT `message_send_type_ibfk_1` FOREIGN KEY (`MID`) REFERENCES `message` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

# -------王印涛---
CREATE TABLE `system_user_face_entry` (
  `user_id` int(18) DEFAULT NULL COMMENT '用户id',
  `fid` int(18) DEFAULT NULL COMMENT '面授id',
  `entry_time` date DEFAULT NULL COMMENT '报名时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# -------------页面管理---罗博文-----新加名师，专委会---
INSERT INTO `content_edition` VALUES ('9', '名师', '/', '9');
INSERT INTO `content_edition` VALUES ('10', '专委会', '/', '10');
# ------------对content_edition_ref做了修改-----------------
ALTER TABLE `content_edition_ref`
ADD COLUMN `EXPERT_ID`  int(18) DEFAULT NULL,
ADD COLUMN `expert_info_id`  int(18) DEFAULT NULL;

-- 书籍推荐
ALTER TABLE `cv_set_refereebook` ADD COLUMN source_id  int NULL AFTER cv_set_id;
ALTER TABLE `cv_set_refereebook` ADD CONSTRAINT `FK_souces_referee` FOREIGN KEY (`source_id`) REFERENCES `exam_source_val` (`ID`);
ALTER TABLE `cv_set_refereebook`
MODIFY COLUMN `book_name`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '书籍名称' AFTER `source_id`,
MODIFY COLUMN `book_url`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '书籍的网址' AFTER `book_name`;

-- 最新文献
ALTER TABLE `cv_set_referencelately` ADD COLUMN `source_id`  int NULL AFTER `cv_set_id`;
ALTER TABLE `cv_set_referencelately` ADD CONSTRAINT `FK_souces_reference` FOREIGN KEY (`source_id`) REFERENCES `exam_source_val` (`ID`);
ALTER TABLE `cv_set_referencelately` MODIFY COLUMN `reference_name`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文献名称' AFTER `source_id`,
MODIFY COLUMN `reference_url`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文献网址' AFTER `reference_name`;
-- 指南共识
ALTER TABLE `cv_set_knowledgebase`
ADD COLUMN `source_id`  int NULL AFTER `cv_set_id`;
ALTER TABLE `cv_set_knowledgebase` ADD CONSTRAINT `FK_souces_knowledge` FOREIGN KEY (`source_id`) REFERENCES `exam_source_val` (`ID`);

ALTER TABLE `cv_set_knowledgebase`
MODIFY COLUMN `knowledgebase_name`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '指南共识名称' AFTER `source_id`,
MODIFY COLUMN `knowledgebase_url`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '指南共识网址' AFTER `knowledgebase_name`;

#--------2017-12-22----------薛红---------------用户表 ----------------------
ALTER TABLE system_user
	ADD COLUMN reason varchar(300) NULL DEFAULT NULL COMMENT '停用原因' AFTER `grassroot`;
ALTER TABLE system_user
	ADD COLUMN reasondate varchar(20) NULL DEFAULT NULL COMMENT '停用时间' AFTER `reason`;

#-----------------------2017--意见反馈表----增加了图片字段-------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATER` varchar(100) DEFAULT NULL COMMENT '用户名、操作人',
  `TELPHONE` varchar(25) DEFAULT NULL COMMENT '手机号',
  `CONTENT` varchar(20) DEFAULT NULL COMMENT '反馈内容',
  `SYSTEM` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `BACKTIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间 ',
  `STATE` int(18) DEFAULT NULL COMMENT '回复的状态   1未回复   2已回复',
  `IMAGE` varchar(1000) DEFAULT NULL COMMENT '截图',
  `SYSTEMVERSION` varchar(50) DEFAULT NULL COMMENT '操作系统版本',
  `SITE` varchar(50) DEFAULT NULL COMMENT '用户请求中获取站点',
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

	
#----------2017-12-26---cv_set_authorization ---taoliang
ALTER TABLE `cv_set_authorization` ADD COLUMN `modify_date`  timestamp NULL DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP AFTER `CV_SET_BREAK_DAYS`;
----------2017-12-27--cv_unit_ref_source ---whq
-- ----------------------------
-- --  扩展阅读关联来源中间表
-- ----------------------------
DROP TABLE IF EXISTS `cv_unit_ref_source`;
CREATE TABLE `cv_unit_ref_source` (
  `UNIT_REF_ID` int(18) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `UNIT_ID` int(18) DEFAULT NULL COMMENT '单元ID',
  `SOURCE_ID` int(18) DEFAULT NULL COMMENT '来源ID',
  PRIMARY KEY (`UNIT_REF_ID`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 修改单元属性
ALTER TABLE `cv_unit`
ADD COLUMN `key_words`  varchar(200) NULL AFTER `unit_make_type` COMMENT '扩展阅读关键字' #-----------2017.12.27------zxw----直播报名----关系表
DROP TABLE IF EXISTS `cv_live_signup`;
CREATE TABLE `cv_live_signup` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `userid` int(32) DEFAULT NULL,
  `cvsetid` int(32) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
--罗博文---对广告表做了修改-------------------
DROP TABLE IF EXISTS `advert`;
CREATE TABLE `advert` (
  `ID` int(18) NOT NULL AUTO_INCREMENT COMMENT '首页广告表主键',
  `NAME` varchar(50) NOT NULL,
  `URL` varchar(500) NOT NULL COMMENT '广告图片地址',
  `TARGET_URL` varchar(50) DEFAULT NULL COMMENT '点击跳转地址',
  `REMARK` varchar(2000) DEFAULT NULL COMMENT '备注',
  `TYPE` int(1) DEFAULT NULL COMMENT '类型 1:PC端 2:app端',
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `STATE` int(1) DEFAULT NULL COMMENT '类型 1:未发布 2:已发布',
  `CREATOR` varchar(10) DEFAULT NULL COMMENT '创建人',
  `ORDERNUM` int(18) DEFAULT '999999999',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

# ----------罗博文---菜单栏增加--banner管理------消息管理-------反馈意见管理---
INSERT INTO system_menu (
	NAME,
	SYSTEM_TYPE,
	MENU_TYPE,
	URL,
	STATE
)
VALUES
	('Banne管理','后台管理系统','内容管理','/contentManage/bannerManage.do?method=list',1);
INSERT INTO system_menu (
	NAME,
	SYSTEM_TYPE,
	MENU_TYPE,
	URL,
	STATE
)
VALUES
	('消息管理','后台管理系统','内容管理','/contentManage/messageManage.do?method=list',1);
	
INSERT INTO system_menu (
	NAME,
	SYSTEM_TYPE,
	MENU_TYPE,
	URL,
	STATE
)
VALUES
	('反馈意见管理','后台管理系统','内容管理','/contentManage/feedbackManage.do?method=list',1);

#--------2018-01-02----------薛红---------------微信简版专项项目表 ----------------------
CREATE TABLE `cv_set_temp` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `FORMA` int(2) DEFAULT NULL COMMENT '授课形式 1:远程   3:面授',
  `CODE` varchar(255) DEFAULT NULL COMMENT '项目编号',
  `TEACHER` varchar(100) DEFAULT NULL COMMENT '培训负责人',
  `FILE_PATH` varchar(1024) DEFAULT NULL COMMENT '项目封面',
  `institutions` varchar(255) DEFAULT NULL COMMENT '基地/机构',
  `address` varchar(255) DEFAULT NULL COMMENT '培训地点',
  `START_DATE` datetime DEFAULT NULL COMMENT '培训开始时间',
  `END_DATE` datetime DEFAULT NULL COMMENT '培训结束时间',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=273 DEFAULT CHARSET=utf8;

#--------2018-01-02----------薛红---------------微信简版专项报名表 ----------------------
CREATE TABLE `sign_up_temp` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '报名表主键ID',
  `cv_set_id` int(16) DEFAULT NULL COMMENT '项目ID',
  `faceteach_id` int(16) DEFAULT NULL COMMENT '期数表主键ID',
  `user_id` int(16) DEFAULT NULL COMMENT '用户ID(即报名者ID)',
  `create_date` datetime DEFAULT NULL COMMENT '报名时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


#--------2018-01-04----------taoliang---------------菜单表创建新菜单----------------------
INSERT INTO system_menu(
	`NAME`,
	SYSTEM_TYPE,
	MENU_TYPE,
	URL,
	STATE
)
VALUES
	(
		'项目发布',
		'后台管理系统',
		'项目管理',
		'/CVSet/CVDistribute.do?method=releaseList',
		1
	);
