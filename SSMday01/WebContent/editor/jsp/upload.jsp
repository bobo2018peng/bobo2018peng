<%@ page contentType="text/html; charset=utf-8"
	import="java.io.*,com.editor.*,java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	request.setCharacterEncoding("UTF-8"); 
	String sAction = request.getParameter("action");
	if (sAction == null || sAction.length()==0) {
		return;
	}
	sAction = sAction.trim();
	String filePath = "", webUrl = "";
	String contentPath = "";
	if("".equals(request.getContextPath())){
		contentPath = "/";
	}else if ("/".equals(request.getContextPath())){
		contentPath = "/";
	}else {
		contentPath = request.getContextPath() + "/";
	}
	String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
	UpLoad.InitUpload(request);
	if (sAction.equalsIgnoreCase("remote")) { //远程获取
		String sContent = "";
		String SaveFileName = "";
		String [] s = request.getParameterValues("eWebEditor_UploadText");
		for(String st:s)
		sContent = sContent + st;
		if (sContent == null || sContent == "") {
			sContent = "";
		} else{
			sContent = new String(sContent.getBytes("utf-8"),"utf-8");
		}
		if (UpLoad.sAllowExt != "") {
			List URLList = new ArrayList();
			Urls urls = new Urls();
			String[] exts = UpLoad.sAllowExt.split("\\|");
				for (String value : exts){
				String regExt = "";
				for(String b:value.split("|")){
					  if(b!=null)
						  continue;
					  //System.out.println(b);
					  regExt=regExt+"[" +b.toUpperCase() +"|" + b.toLowerCase() + "]";
				  }
				URLList.addAll(urls.getString(sContent,"((http|https|ftp|rtsp|mms):(//|\\\\){1}(([A-Za-z0-9_-])+[.]){1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(\\S*/)((\\S)+[.]{1}(" + regExt + ")))"));
				}
				HttpGet http = new HttpGet();
				filePath = pageContext.getServletContext().getRealPath(request.getServletPath());
				filePath = new java.io.File(filePath).getParentFile().getParentFile().getParent() + "\\";
				filePath = filePath	+ UpLoad.sUploadDir + UpLoad.sType + "\\" + folderName + "\\";
				filePath = filePath.replace('/','\\');
				UpLoad.mkFolder(filePath);
				webUrl = contentPath + UpLoad.sUploadDir + UpLoad.sType + "/" +  folderName + "/" + SaveFileName;
				sContent = UpLoad.replaceAll(sContent, http.downLoadByList(URLList, filePath, webUrl));
		}
		sContent = UpLoad.inHTML(sContent);
	
		out.println("<HTML><HEAD><TITLE>远程上传</TITLE><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body>");
		out.print("<input type=hidden id=UploadText value=\"");
		out.print(sContent);
		out.println("\">");
		out.println("</body></html>");
		out.println("<script language=javascript>");
		out.print("parent.setHTML(UploadText.value); parent.remoteUploadOK();");
		out.println("</script>");
	} else if (sAction.equalsIgnoreCase("save")) {
		//显示上传菜单
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>文件上传</TITLE>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		out.println("<style type=\"text/css\">");
		out.println("body, a, table, div, span, td, th, input, select{font:9pt;font-family: \"宋体\", Verdana, Arial, Helvetica, sans-serif;}");
		out.println("body {padding:0px;margin:0px}");
		out.println("</style>");
		out.println("<script language=\"JavaScript\" src=\"dialog/dialog.js\">");
		out.println("</script>");
		out.println("</head>");
		out.println("<body bgcolor=menu>");
		out.print("<form action=\"?action=save&type=");
		out.print(UpLoad.sType);
		out.print("&style=");
		out.print(UpLoad.sStyleName);
		out.println("\" method=post name=myform enctype=\"multipart/form-data\">");
		out.println("<input type=file name=uploadfile size=1 style=\"width:100%\" onchange=\"originalfile.value=this.value\">");
		out.println("<input type=hidden name=originalfile value=\"\">");
		out.println("</form>");
		out.println("<script language=javascript>");
		out.print("var sAllowExt = \"");
		out.print(UpLoad.sAllowExt);
		out.println("\";");
		out.println("// 检测上传表单");
		out.println("function CheckUploadForm() {");
		out.println("if (!IsExt(document.myform.uploadfile.value,sAllowExt)){");
		out.println("parent.UploadError(\"提示：\\n\\n请选择一个有效的文件，\\n支持的格式有（\"+sAllowExt+\"）！\");");
		out.println("return false;");
		out.println("}");
		out.println("return true");
		out.println("}");
		out.println("// 提交事件加入检测表单");
		out.println("var oForm = document.myform;");
		out.println("oForm.attachEvent(\"onsubmit\", CheckUploadForm) ;");
		out.println("if (! oForm.submitUpload) oForm.submitUpload = new Array() ;");
		out.println("oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;");
		out.println("if (! oForm.originalSubmit) {");
		out.println("oForm.originalSubmit = oForm.submit ;");
		out.println("oForm.submit = function() {");
		out.println("if (this.submitUpload) {");
		out.println("for (var i = 0 ; i < this.submitUpload.length ; i++) {");
		out.println("this.submitUpload[i]() ;");
		out.println("			}");
		out.println("		}");
		out.println("		this.originalSubmit() ;");
		out.println("	}");
		out.println("}");
		out.println("// 上传表单已装入完成");
		out.println("try {");
		out.println("	parent.UploadLoaded();");
		out.println("}");
		out.println("catch(e){");
		out.println("}");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		SmartUpload up = new SmartUpload();
		up.initialize(pageContext);
		up.setMaxFileSize(UpLoad.nAllowSize * 1024);
		String setExt = UpLoad.sAllowExt.replace('|', ',');
		up.setAllowedFilesList(setExt.toLowerCase());
		try{
			up.upload();
		}catch(Exception e){
			out.println("<script language=javascript>");
			out.print("alert(\"" + e.getMessage() + "\")");
			out.print("</script>");
			return ;
		}
		String SaveFileName = "",sOriginalFileName = "",sPathFileName = "";
		for (int i = 0; i < up.getFiles().getCount(); i++) {
			// Retreive the current file
			com.editor.File myFile = up.getFiles().getFile(i);
			if (!myFile.isMissing()) {
				SaveFileName = UpLoad.GetRndFileName(myFile.getFileExt());
				sOriginalFileName = myFile.getFileName();
				filePath = pageContext.getServletContext().getRealPath(request.getServletPath());
				filePath = new java.io.File(filePath).getParentFile().getParentFile().getParent() + "\\";
				filePath = filePath	+ UpLoad.sUploadDir + UpLoad.sType + "\\" + folderName + "\\";
				filePath = filePath.replace('/','\\');
				UpLoad.mkFolder(filePath);
				filePath = filePath + SaveFileName;
				myFile.saveAs(filePath);
				webUrl = contentPath + UpLoad.sUploadDir + UpLoad.sType + "/" +  folderName + "/" + SaveFileName;
			}
		}
		out.println("<script language=javascript>");
		out.print("parent.UploadSaved('");
		out.print(webUrl);
		out.print("');var obj=parent.dialogArguments.dialogArguments;if (!obj) obj=parent.dialogArguments;");
		out.println("history.back()</script>");
	} else if(sAction.equalsIgnoreCase("local")){
		String SaveFileName = "";
		String sOriginalFileName ="";
		SmartUpload up = new SmartUpload();
		up.initialize(pageContext);
		up.setMaxFileSize(UpLoad.nAllowSize * 1024);
		String setExt = UpLoad.sAllowExt.replace('|', ',');
		up.setAllowedFilesList(setExt.toLowerCase());
		try{
			up.upload();
		}catch(Exception e){
			return ;
		}
		for (int i = 0; i < up.getFiles().getCount(); i++) {
			// Retreive the current file
			com.editor.File myFile = up.getFiles().getFile(i);
			if (!myFile.isMissing()) {
				SaveFileName = UpLoad.GetRndFileName(myFile.getFileExt());
				sOriginalFileName = myFile.getFileName();
				filePath = pageContext.getServletContext().getRealPath(request.getServletPath());
				filePath = new java.io.File(filePath).getParentFile().getParentFile().getParent() + "\\";
				filePath = filePath	+ UpLoad.sUploadDir + UpLoad.sType + "\\" + folderName + "\\";
				filePath = filePath.replace('/','\\');
				UpLoad.mkFolder(filePath);
				filePath = filePath + SaveFileName;
				myFile.saveAs(filePath);
				webUrl = contentPath + UpLoad.sUploadDir + UpLoad.sType + "/" +  folderName + "/" + SaveFileName;
			}
		}
		out.print(webUrl);
	}else{
		//显示上传表单
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>文件上传</TITLE>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		out.println("<style type=\"text/css\">");
		out.println("body, a, table, div, span, td, th, input, select{font:9pt;font-family: \"宋体\", Verdana, Arial, Helvetica, sans-serif;}");
		out.println("body {padding:0px;margin:0px}");
		out.println("</style>");
		out.println("<script language=\"JavaScript\" src=\"dialog/dialog.js\">");
		out.println("</script>");
		out.println("</head>");
		out.println("<body bgcolor=menu>");
		out.print("<form action=\"?action=save&type=");
		out.print(UpLoad.sType);
		out.print("&style=");
		out.print(UpLoad.sStyleName);
		out.println("\" method=post name=myform enctype=\"multipart/form-data\">");
		out.println("<input type=file name=uploadfile size=1 style=\"width:100%\" onchange=\"originalfile.value=this.value\">");
		out.println("<input type=hidden name=originalfile value=\"\">");
		out.println("</form>");
		out.println("<script language=javascript>");
		out.print("var sAllowExt = \"");
		out.print(UpLoad.sAllowExt);
		out.println("\";");
		out.println("// 检测上传表单");
		out.println("function CheckUploadForm() {");
		out.println("	if (!IsExt(document.myform.uploadfile.value,sAllowExt)){");
		out.println("		parent.UploadError(\"提示：\\n\\n请选择一个有效的文件，\\n支持的格式有（\"+sAllowExt+\"）！\");");
		out.println("		return false;");
		out.println("	}");
		out.println("	return true");
		out.println("}");
		out.println("");
		out.println("var oForm = document.myform ;");
		out.println("oForm.attachEvent(\"onsubmit\", CheckUploadForm) ;");
		out.println("if (! oForm.submitUpload) oForm.submitUpload = new Array() ;");
		out.println("oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;");
		out.println("if (! oForm.originalSubmit) {");
		out.println("	oForm.originalSubmit = oForm.submit ;");
		out.println("	oForm.submit = function() {");
		out.println("		if (this.submitUpload) {");
		out.println("			for (var i = 0 ; i < this.submitUpload.length ; i++) {");
		out.println("				this.submitUpload[i]() ;");
		out.println("			}");
		out.println("		}");
		out.println("		this.originalSubmit() ;");
		out.println("	}");
		out.println("}");
		out.println("");
		out.println("try {");
		out.println("	parent.UploadLoaded();");
		out.println("}");
		out.println("catch(e){");
		out.println("}");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
	}
%>

