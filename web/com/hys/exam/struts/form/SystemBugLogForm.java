package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.exam.model.system.SystemBugLog;
import com.hys.framework.web.form.BaseForm;

@SuppressWarnings("serial")
public class SystemBugLogForm extends BaseForm {


		//日志/bug管理
		private SystemBugLog systemBugLog = new SystemBugLog();
		
		private FormFile file1;
		
		private FormFile file2;
		
		private FormFile file3;
		
		public FormFile getFile1() {
			return file1;
		}

		public void setFile1(FormFile file1) {
			this.file1 = file1;
		}

		public FormFile getFile2() {
			return file2;
		}

		public void setFile2(FormFile file2) {
			this.file2 = file2;
		}

		public FormFile getFile3() {
			return file3;
		}

		public void setFile3(FormFile file3) {
			this.file3 = file3;
		}
		
		public SystemBugLog getSystemBugLog() {
			return systemBugLog;
		}

		public void setSystemBugLog(SystemBugLog systemBugLog) {
			this.systemBugLog = systemBugLog;
		}
}
