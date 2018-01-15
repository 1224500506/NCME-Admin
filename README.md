# 学习平台后台

### 配置文件说明
1. 数据库连接：
修改WEB-INF\classes下的applicationContext_jndi.xml文件：
		<property name="url" value="jdbc:mysql://127.0.0.1:3306/ncme_test?useUnicode=true&amp;characterEncoding=utf-8" />
		<property name="username" value="root" />
		<property name="password" value="123456" />

2.环境变量配置
修改WEB-INF\classes下的ncme.properties文件（默认为ncme生产环境，开发、测试时请注释掉生产环境并打开开发或测试部分）
		#私有云请求处理超时时间，1M=1000
		connectionRequestTimeout = 2048000

		#私有云IP和端口：ncme生产环境
		hostAndPort=10.1.27.99:8080


		#前端、后台和资源管理访问的URL：ncme生产环境
		adminURL=http://www.ncme.org.cn/resource
		peixunURL=http://www.ncme.org.cn/admin
		qiantaiURL=http://www.ncme.org.cn

		#私有云IP和端口：213测试环境及本机测试环境用
		#hostAndPort=111.206.238.131:8080

		#前端、后台和资源管理访问的URL：213测试环境
		#adminURL=http://101.200.85.213:8090/admin
		#peixunURL=http://101.200.85.213:8090/peixun
		#qiantaiURL=http://101.200.85.213:8090/qiantai


		#前端、后台和资源管理访问的URL：本机测试环境
		#adminURL=http://localhost:8080/admin
		#peixunURL=http://localhost:8080/peixun
		#qiantaiURL=http://localhost:8080/qiantai

