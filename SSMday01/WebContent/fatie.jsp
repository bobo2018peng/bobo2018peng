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
		
		
		
		
		

		function checkform(){
		
	
	 
	 if (document.getElementById('titleid').value=="")
	{
		alert("标题不能为空");
		return false;
	}
	
	
	return true;
	
}




	</script>

</HEAD>
<BODY >
<DIV id=wrap>
<%@ include file="head.jsp" %>




<ARTICLE class="content layout">


  
  <SECTION class=box>
  
<DIV class="box-hd mb10 clearall">

<H3><SPAN>发帖</SPAN></H3>


</DIV>

<DIV class="box-bd clearall">



<form action="indexmethod!fatie2.action?bid=${bid }&leixing=${leixing }" onsubmit="return checkform()" method="post" >
  <table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
  <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">标题：</span></TH>
    <TH width="70%">
    <input type="text" name="title" size="45"  id="titleid"  /></TH>
   </THEAD>
   
    <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">内容：</span></TH>
    <TH width="70%" >
    <textarea id="content1" name="content1" style="display: none;"></textarea>
    <iframe ID="eWebEditor" src="editor/ewebeditor.htm?id=content1" frameborder="0" scrolling="no" width="500" height="300"></iframe>	
    
    </TH>
   </THEAD>
   
   
   
   <THEAD>
  <TR >
    <TH width="30%" align="right"><span style="font-size: 25px;font-weight: bold">操作：</span></TH>
    <TH width="70%">
     <INPUT class=list-yd  value=提&nbsp;&nbsp;&nbsp;&nbsp;交 type="submit"  /> 

   	&nbsp;&nbsp;&nbsp;&nbsp;
   	<INPUT class=list-yd  value=返&nbsp;&nbsp;&nbsp;&nbsp;回 type="button" onclick="javascript:history.go(-1)"  /> 
    </TH>
   </THEAD>
  </TABLE>
  
  </form>


  </DIV>
  </SECTION>
  </ARTICLE>
  
</DIV>








<SCRIPT src="_files/cdntest5.js"></SCRIPT>

</BODY></HTML>
