<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@ include file="/commons/taglibs.jsp"%>
    <title>乡医详细信息</title>
  </head>
  
  <body>
    <div class="q4all">
    <center><input type="button" name="goback" value=" 返 回 " onclick="history.go(-1)"/></center>
	<table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center" >
		<tr><td align="center">详细信息</td></tr>
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
        <tr>
            <td width="20%" height="27" align="left">姓名：</td>
            <td width="30%" height="27" align="left">${doctorDetail.doctorName}</td>
            <td width="20%" height="27" align="left">性别：</td>
            <td width="30%" height="27" align="left"><c:if test="${doctorDetail.sex==0}">男</c:if><c:if test="${doctorDetail.sex==1}">女</c:if></td>
          </tr>
          <tr>
            <td width="141" height="27" align="left">出生年月：</td>
            <td height="27" align="left">${doctorDetail.birthday}</td>
            <td width="68" height="27" align="left">民族：</td>
            <td height="27" align="left">${doctorDetail.nation}</td>
          </tr>
          <tr>
            <td width="141" height="27" align="left">文化程度：</td>
            <td width="218" height="27" align="left">${doctorDetail.education}</td>
            <td width="68" height="27" align="left">家庭电话：</td>
            <td width="253" height="27" align="left">${doctorDetail.homeTel}</td>
          </tr>
          <tr>
            <td width="141" height="27" align="left">家庭住址：</td>
            <td height="27" colspan="5" align="left">${doctorDetail.homeAddress}</td>
            </tr>
          <tr>
            <td width="141" height="27" align="left">身份证号码：</td>
            <td width="218" height="27" align="left">${doctorDetail.idCardNumber}</td>
            <td width="68" height="27" align="left">联系电话：</td>
            <td width="253" height="27" align="left">${doctorDetail.orgTel}</td>
          </tr>
          <tr>
            <td width="141" height="27" align="left">申请执业地的村医疗<br/>卫生机构名称：</td>
            <td width="218" height="27" align="left">${doctorDetail.applyOrgName}</td>
            <td width="68" height="27" align="left">邮政编码：</td>
            <td width="253" height="27" align="left">${doctorDetail.orgPostcode}</td>
          </tr>
          <tr>
            <td width="20%" height="27" align="left">村医疗卫生机构地址：</td>
            <td width="30%" height="27" align="left">${doctorDetail.orgAddress}</td>
            <td width="20%" height="27" align="left">开始从事乡村医生工作时间：</td>
            <td width="30%" height="27" align="left"><c:if test="${doctorDetail.beginWork!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.beginWork}"/></c:if></td>
            </tr>
        </table></td>
      </tr>
    </table><br/>
<table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td width="45%"><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left" nowrap>是否取得县级以上卫生行政<br>部门颁发的乡村医生证书：</td>
            <td width="50%" colspan="2" align="left"><c:if test="${doctorDetail.isnotGainCertificate==1}">是</c:if><c:if test="${doctorDetail.isnotGainCertificate==0}">否</c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">证书名称：</td>
            <td width="50%" height="27" align="left">${doctorDetail.certificateName}</td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">发证机关：</td>
            <td width="50%" height="27" align="left">${doctorDetail.giveCertificateOrg}</td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">获取证书时间：</td>
            <td width="50%" height="27" align="left"><c:if test="${doctorDetail.gainCertificateDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.gainCertificateDate}"/></c:if></td>
            </tr>
        </table></td>
      </tr>
    </table>
    <br/>
<table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td width="45%"><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">是否取得国民教育序列<br>中等以上医学专业学历：</td>
            <td width="50%" colspan="2" align="left"><c:if test="${doctorDetail.isnotGainEducation==1}">是</c:if><c:if test="${doctorDetail.isnotGainEducation==0}">否</c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">毕业学校：</td>
            <td width="50%" height="27" align="left">${doctorDetail.schoolName}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">取得学历时间：</td>
            <td width="50%" height="27" align="left"><c:if test="${doctorDetail.gainEducationDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.gainEducationDate}"/></c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">专业学历水平：</td>
            <td width="50%" height="27" align="left">${doctorDetail.educationGrade}</td>
            </tr>
        </table></td>
      </tr>
    </table><br/>
          <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">期间是否离开过乡村医生岗位：</td>
            <td width="50%" height="27" colspan="2" align="left"><c:if test="${doctorDetail.isnotLeft==1}">是</c:if><c:if test="${doctorDetail.isnotLeft==0}">否</c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">离开乡村医生工作岗位时间的起始时间：</td>
            <td width="50%" height="27" align="left"><c:if test="${doctorDetail.leftBeginDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.leftBeginDate}"/></c:if> 至 <c:if test="${doctorDetail.leftEndDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.leftEndDate}"/></c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">或累计：</td>
            <td width="50%" align="left">${doctorDetail.leftTotalYear}年</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">离开乡村医生岗位期间所从事的职业：</td>
            <td width="50%" align="left">${doctorDetail.leftOverWork}</td>
            </tr>
        </table></td>
      </tr>
    </table><br/>
    <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">2003年8月5日前是否已取得省级卫生<br>行政部门认定的乡村医生培训合格证书：</td>
            <td width="50%" colspan="2" align="left"><c:if test="${doctorDetail.isnot_Gain_Certificate_200385==1}">是</c:if><c:if test="${doctorDetail.isnot_Gain_Certificate_200385==0}">否</c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">证书名称：</td>
            <td width="50%" height="27" align="left">${doctorDetail.certificate_Name_200385}</td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">发证机关：</td>
            <td width="50%" align="left">${doctorDetail.give_Certificate_Org_200385}</td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">获取证书时间：</td>
            <td width="50%" align="left"><c:if test="${doctorDetail.gain_Certificate_Date_200385!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.gain_Certificate_Date_200385}"/></c:if></td>
            </tr>
        </table></td>
      </tr>
      
    </table><br/>
    <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">是否参加省卫生厅组织的<br>乡村医生执业注册资格培训：</td>
            <td width="50%" colspan="2" align="left"><c:if test="${doctorDetail.isnotJoinTraining==1}">是</c:if><c:if test="${doctorDetail.isnotJoinTraining==0}">否</c:if></td>
            </tr>
          <tr>
            <td width="40%" height="27" align="left">培训地点：</td>
            <td width="50%" height="27" align="left">${doctorDetail.trainingAddress}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">是否取得省卫生厅验印的《江苏省乡村医生职业注册资格培训合格证》</td>
            <td width="50%" colspan="2" align="left"><c:if test="${doctorDetail.isnotGainProvinceCertificat==1}">是</c:if><c:if test="${doctorDetail.isnotGainProvinceCertificat==0}">否</c:if></td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">其它需要说明的问题：</td>
            <td width="50%" height="27" align="left">${doctorDetail.otherQuetion}</td>
            </tr>
        </table></td>
      </tr>
    </table><br/>
    <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">村医疗卫生机构意见：</td>
            <td width="50%" height="27" align="left">${doctorDetail.orgOpinion}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">执业登记号：</td>
            <td width="50%" height="27" align="left">${doctorDetail.orgPracticeNumber}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">负责人：</td>
            <td width="50%" height="27" align="left">${doctorDetail.orgMainPerson}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">日期：</td>
            <td width="50%" height="27" align="left">${doctorDetail.orgDate}</td>
            </tr>
        </table></td>
      </tr>
    </table>
    
    <br/>
    <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">拟聘用单位意见：</td>
            <td width="50%" height="27" align="left">${doctorDetail.companyOpinion}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">负责人：</td>
            <td width="50%" height="27" align="left">${doctorDetail.companyMainPerson}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">日期：</td>
            <td width="50%" height="27" align="left">${doctorDetail.companyDate}</td>
            </tr>
        </table></td>
      </tr>
    </table>
    <br/>
    <table width="90%" border="1" cellspacing="0" cellpadding="1" bordercolor="#f5e3ac" bgcolor="#fdfdf2" align="center">
      <tr align="center">
        <td><table width="99%" border="0" cellspacing="1" cellpadding="1" style="font-size: 12px;">
          <tr>
            <td width="40%" height="27" align="left">卫生行政部门审批意见：</td>
            <td width="50%" height="27" align="left">经审查，按照《江苏省乡村医生注册管理办法》第 ${doctorDetail.theSizeOrder} 条规定，同意 ${doctorDetail.agreePerson} 乡村医生执业注册。</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">负责人：</td>
            <td width="50%" height="27" align="left">${doctorDetail.agreeMainPerson}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">日期：</td>
            <td width="50%" height="27" align="left">${doctorDetail.agreeDate}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">执业证书编码：</td>
            <td width="50%" height="27" align="left">${doctorDetail.thePracticeCertificateNumbe}</td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">执业证书有效期自</td>
            <td width="50%" height="27" align="left"><c:if test="${doctorDetail.certificateBeginDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.certificateBeginDate}"/></c:if> 至 <c:if test="${doctorDetail.certificateEndDate!='0003-12-31'}"><fmt:formatDate pattern= "yyyy-MM" value="${doctorDetail.certificateEndDate}"/></c:if></td>
            </tr>
            <tr>
            <td width="40%" height="27" align="left">备注：</td>
            <td width="50%" height="27" align="left">${doctorDetail.remark}</td>
            </tr>
        </table></td>
      </tr>
    </table>
    <center><input type="button" name="goback" value=" 返 回 " onclick="history.go(-1)"/></center>
    </div>
  </body>
</html>
