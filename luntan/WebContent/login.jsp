<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0025)http://taizhou.19lou.com/ -->
<HTML lang=zh-CN><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>彭文波艺术作品论坛</TITLE>

<!--[if lt IE 9]>
<SCRIPT>
        // html5shiv MIT @rem remysharp.com/html5-enabling-script
        // iepp v1.6.2 MIT @jon_neal iecss.com/print-protector
        /*@cc_on(function(m,c){var z="abbr|article|aside|audio|canvas|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video";function n(d){for(var a=-1;++a<o;)d.createElement(i[a])}function p(d,a){for(var e=-1,b=d.length,j,q=[];++e<b;){j=d[e];if((a=j.media||a)!="screen")q.push(p(j.imports,a),j.cssText)}return q.join("")}var g=c.createElement("div");g.innerHTML="<z>i</z>";if(g.childNodes.length!==1){var i=z.split("|"),o=i.length,s=RegExp("(^|\\s)("+z+")",
        "gi"),t=RegExp("<(/*)("+z+")","gi"),u=RegExp("(^|[^\\n]*?\\s)("+z+")([^\\n]*)({[\\n\\w\\W]*?})","gi"),r=c.createDocumentFragment(),k=c.documentElement;g=k.firstChild;var h=c.createElement("body"),l=c.createElement("style"),f;n(c);n(r);g.insertBefore(l,
        g.firstChild);l.media="print";m.attachEvent("onbeforeprint",function(){var d=-1,a=p(c.styleSheets,"all"),e=[],b;for(f=f||c.body;(b=u.exec(a))!=null;)e.push((b[1]+b[2]+b[3]).replace(s,"$1.iepp_$2")+b[4]);for(l.styleSheet.cssText=e.join("\n");++d<o;){a=c.getElementsByTagName(i[d]);e=a.length;for(b=-1;++b<e;)if(a[b].className.indexOf("iepp_")<0)a[b].className+=" iepp_"+i[d]}r.appendChild(f);k.appendChild(h);h.className=f.className;h.innerHTML=f.innerHTML.replace(t,"<$1font")});m.attachEvent("onafterprint",
        function(){h.innerHTML="";k.removeChild(h);k.appendChild(f);l.styleSheet.cssText=""})}})(this,document);@*/
</SCRIPT>
<![endif]-->
<LINK rel=stylesheet href="_files/index-min.css">


<META name=GENERATOR content="MSHTML 8.00.7601.18210">

<script language="javascript" type="text/javascript"> 
		
		
		var code ; //在全局 定义验证码
function createCode(){ 
code = "";
var codeLength = 4;//验证码的长度
var checkCode = document.getElementById("checkCode");
checkCode.value = "";

var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

for(var i=0;i<codeLength;i++) {
   var charIndex = Math.floor(Math.random()*32);
   code +=selectChar[charIndex];
}
if(code.length != codeLength){
   createCode();
}
checkCode.value = code;
}
		
		

		function checkregisterform(){
		
		var inputCode = document.getElementById("input1").value.toUpperCase();

if(inputCode.length <=0) {
   alert("请输入验证码！");
   return false;
}
else if(inputCode != code ){
   alert("验证码输入错误！");
   createCode();
   return false;
		
		
		
}
	 
	 if (document.getElementById('usernameid').value=="")
	{
		alert("用户名不能为空");
		return false;
	}
	

	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	
	
	
	return true;
	
}




	</script>

</HEAD>
<BODY onload="createCode()">
<DIV id=wrap>
<%@ include file="head.jsp" %>




<ARTICLE class="content layout">


  
  <SECTION class=box>
  
<DIV class="box-hd mb10 clearall">

<H3><SPAN>用户登录</SPAN></H3>


</DIV>

<DIV class="box-bd clearall">



<form action="${pageContext.request.contextPath }/user"  method="post" >
<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
  <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">用户名：</span></TH>
    <TH width="70%"><input type="text" name="username" size="45"  id="usernameid"/></TH>
   </THEAD>
   
    <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">密码：</span></TH>
    <TH width="70%" ><input type="password" name="password" size="45" id="passwordid"/></TH>
   </THEAD>
   
  
   
    <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">请输入验证码：</span></TH>
    <TH width="70%"><input type="text" id="input1" size="45"/></TH>
   </THEAD>
   
   <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">验证码：</span></TH>
    <TH width="70%"><input type="text" id="checkCode" class="code" style="width: 55px" size="20" readonly="readonly" onpaste="return false" oncontextmenu="return false" 

oncopy="javascript:alert('不可复制');return false;" oncut="return false"/> <a href="####" onclick="createCode()"/> <a href="####" onclick="createCode()">看不清楚</a></TH>
   </THEAD>
   
   <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">操作：</span></TH>
    <TH width="70%">
    <INPUT class=list-yd  value=提交 type="submit"  /> 

   	&nbsp;&nbsp;&nbsp;&nbsp;
   	<INPUT class=list-yd  value=返回 type="button" onclick="javascript:history.go(-1)"  /> 
   					
   </THEAD>
   
   
   
  </TABLE>
  </form>


  </DIV>
  </SECTION>
  </ARTICLE>
  
</DIV>








<SCRIPT src="_files/cdntest5.js"></SCRIPT>

</BODY></HTML>
