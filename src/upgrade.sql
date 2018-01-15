-------20150416----
---考试设置增加站点-----
1，exam_examination:   alter table exam_examination add(site_id NUMBER(18))



---2014.03.03----

---系统必须初始化一条记录在system_card里。
insert into system_card (ID, CARD_NUMBER, CARD_PASSWORD, CARD_TYPE, IMPORT_DATE, USEFUL_DATE, ISNOT_BIND, FACE_VALUE, CREATE_USER_ID, CREATE_DATE, STATUS, LAST_UPDATE_USER_ID, LAST_UPDATE_DATE, BALANCE, ORDER_ID)
values (12281, '14010000004', '431576', 21, to_date('25-02-2014 14:14:25', 'dd-mm-yyyy hh24:mi:ss'), null, 0, null, null, to_date('25-02-2014 14:14:25', 'dd-mm-yyyy hh24:mi:ss'), 1, null, to_date('01-03-2014 14:06:02', 'dd-mm-yyyy hh24:mi:ss'), 0.00, null);

---更新学习卡的状态
comment on column SYSTEM_CARD.STATUS is '1  有效; 0 待生效;  -1 禁用 ;  ';

--崇文和玄武区,状态标为-1
update system_org o set o.status = -1 where o.id = 2 or o.id = 36

--更改北京市安监局的org_id为北京
update system_admin_organ o set o.org_id = 1 where o.organ_id = 2

--课程授权表中增加 学时数 creditHours,修改course_type长度,以及联合主键等等
-- Create table
create table SYSTEM_COURSE_CREDIT
(
  CREDIT_YEAR   VARCHAR2(4) not null,
  COURSE_ID     NUMBER(18) not null,
  ORG_ID        NUMBER(18) not null,
  CREDIT_TYPE   VARCHAR2(4) not null,
  INDUSTRY_ID   NUMBER(18),
  COURSE_TYPE   NUMBER(18) not null,
  CREDIT_NUM    FLOAT,
  COURSE_SERIAL VARCHAR2(128),
  REQUEST       VARCHAR2(1000),
  RE_STUDY_FLAG INTEGER default 1,
  CREDIT_DATE   DATE default SYSDATE ,
  START_DATE    DATE default SYSDATE not null,
  END_DATE		DATE default SYSDATE not null,
  CREDIT_HOURS  FLOAT default 0
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYSTEM_COURSE_CREDIT.CREDIT_YEAR
  is '如：2010';
comment on column SYSTEM_COURSE_CREDIT.COURSE_ID
  is '课程的唯一标志';
comment on column SYSTEM_COURSE_CREDIT.ORG_ID
  is '用户机构id';
comment on column SYSTEM_COURSE_CREDIT.CREDIT_TYPE
  is '表示课程在一个地区，所受于的课程级别如下：
G1国家一类
P1省级一类
对应表：
SYSTEM_CREDIT_TYPE';
comment on column SYSTEM_COURSE_CREDIT.COURSE_TYPE
  is '课程类型ID';
comment on column SYSTEM_COURSE_CREDIT.CREDIT_NUM
  is '课程在一个地区中受于的学分数如：5';
comment on column SYSTEM_COURSE_CREDIT.COURSE_SERIAL
  is '课题被批准时发布的编号
如：2005-15-01-024';
comment on column SYSTEM_COURSE_CREDIT.RE_STUDY_FLAG
  is '课件是否可以多次学习标记
0：不同年份不能重复学习本课程
1：不同年份可以重复学习本课程';
comment on column SYSTEM_COURSE_CREDIT.CREDIT_DATE
  is '添加授权的日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYSTEM_COURSE_CREDIT
  add constraint PK_SYSTEM_COURSE_CREDIT2 primary key (CREDIT_YEAR, COURSE_ID, ORG_ID, COURSE_TYPE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SYSTEM_COURSE_CREDIT
  add constraint FK_SCT_C_REFERENCE_SCC_C foreign key (CREDIT_TYPE)
  references SYSTEM_CREDIT_TYPE (CREDIT_TYPE);

--------------学习卡制卡记录------------------------
create table SYSTEM_CARD_CREATE_RECORD  (
   ID                   NUMBER(18)                      not null,
   CARD_START_NUMBER    VARCHAR2(50)                    not null,
   CARD_END_NUMBER      VARCHAR2(50)                    not null,
   CARD_SUM             NUMBER(18)                      not null,
   CARD_USERD_SUM       NUMBER(18),
   CREATE_DATE          DATE,
   DESCRIPTION          VARCHAR2(100),
   constraint PK_SYSTEM_CARD_CREATE_RECORD primary key (ID)
);

-------------------- 能力课程----需手动更改-----
alter table system_ability_course modify (IS_OBLIGATORY number(1) default 0);

--------------------学习记录表----增加年度
alter table log_study add(year VARCHAR2(4))


--------------------专家表-----------------
create table SYSTEM_EXPERTS  (
   ID                   NUMBER(18)                      not null,
   NAME                 VARCHAR2(50)                    not null,
   SPELLING_NAME        VARCHAR2(50)                    not null,
   PHOTO_PATH           VARCHAR2(50)                    not null,
   INTRODUCTION         VARCHAR2(1000),
   SEQ                  NUMBER(3),
   DESCRIPTION          VARCHAR2(100),
   CREATE_DATE          date,
   constraint PK_SYSTEM_EXPERTS primary key (ID)
);
-----------------------------seq
create sequence SYSTEM_EXPERTS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
-----------------------------

----------------------2014.03.29------------
修改课程表里的STUDY_COURSE_TYPE 去除 非空限制
alter table STUDY_COURSE modify(STUDY_COURSE_TYPE number(2))

-------------------系统bug日志表
create table SYSTEM_BUG_LOG  (
   ID                   NUMBER(18)                      not null,
   TITLE                VARCHAR2(50)                    not null,
   CONTENT              VARCHAR2(1000)                  not null,
   BUG_LEVEL            NUMBER(1),
   BUG_STATUS           NUMBER(1)                       not null,
   STATUS               NUMBER(1),
   CREATOR              VARCHAR2(10),
   FINISHER             VARCHAR2(10),
   CREATE_DATE          DATE,
   REPLY       VARCHAR2(1000),
   UPDATE_DATE          DATE,
   constraint PK_SYSTEM_BUG_LOG primary key (ID)
);
------seq
 
 create sequence SYSTEM_BUG_LOG_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

------------------课程设置(时间)
create table STUDY_COURSE_SETTING  (
   ID                   NUMBER(18)                      not null,
   TIME                 FLOAT                           not null,
   STATUS               NUMBER(1),
   CREATOR              VARCHAR2(10),
   CREATE_DATE          DATE,
   UPDATE_DATE          DATE,
   constraint PK_STUDY_COURSE_SETTING primary key (ID)
);

----------------后台行业 增加排序 
alter table system_industry add(seq NUMBER(10))

---------------
alter table SYSTEM_BUG_LOG add(FILE_PATH VARCHAR2(50))
alter table SYSTEM_BUG_LOG add(FILE_PATH_two VARCHAR2(50))
alter table SYSTEM_BUG_LOG add(FILE_PATH_three VARCHAR2(50))

----------------卡类型站点关联表
drop table SYSTEM_CARD_TYPE_SITE cascade constraints;
create table SYSTEM_CARD_TYPE_SITE  (
   CARDTYPE_ID          NUMBER(18)                      not null,
   SITE_ID              NUMBER(18)                      not null,
   constraint PK_SYSTEM_CARD_TYPE_SITE primary key (SITE_ID, CARDTYPE_ID)
);


----------------站点地区关联表
drop table SYSTEM_SITE_ORGAN cascade constraints;
create table SYSTEM_SITE_ORGAN  (
   SITE_ID              NUMBER(18)                      not null,
   ORGAN_ID             NUMBER(18)                      not null,
   constraint PK_SYSTEM_SITE_ORGAN primary key (ORGAN_ID, SITE_ID)
);


---把system_admin_organ 里的卫生局改为安监局
在系统后台，课程目录里再根目录下新建“大兴安监局远程课程”,然后课程授权选择此项。


----20141117
1, 在system_admin_organ 里增加 status 字段
   alter table system_admin_organ add(status NUMBER(1)); 并把北京和山东之外的都设置为status为-1
----------------后台课程授权 增加站点ID 
alter table system_course_credit add(SITE_ID NUMBER(18)) 

----------------20141104 更新----------
1, 	system_user 去掉 account_name
	system_user_config 去掉 site_id,
		ALTER TABLE system_user_config DROP COLUMN site_id;
		ALTER TABLE system_user DROP COLUMN account_name;
		
	system_account 里增加 user_id, site_id, -------目前user_id 和 account_id 相同,属巧合,因为一个用户一个账户对应的
		alter table system_account add(user_id NUMBER(18));
		alter table system_account add(site_id NUMBER(18));
		alter table system_account add(account_status NUMBER(1));
		update system_account set site_id = 24
		update system_account set account_status = 1
    
