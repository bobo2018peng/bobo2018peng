<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

</HEAD>
<BODY>
<DIV id=wrap>
<%@ include file="head.jsp" %>




<ARTICLE class="content layout">


  
  <SECTION class=box>
  
<DIV class="box-hd mb10 clearall">

<H3><SPAN>论坛地图</SPAN></H3>



</DIV>
<DIV class="box-bd clearall">

<UL class=club-list>
  
  <c:forEach items="${list}"  var="bean">
  <LI class="club01 club">
  <DIV class=board>
  <H2><A  href="indexmethod!bankuailist.action?id=${bean.id }" >${bean.bankuaiming }</A></H2>
  <DIV class=board-logo>
  <P><A href="indexmethod!bankuailist.action?id=${bean.id }">
  <IMG src="<%=basePath %>uploadfile/${bean.bankuaiimagePath }"
  width=64 height=64 border="0"></A></P></DIV>
  <DIV class="board-info color6">${bean.info }</DIV></DIV>
  </LI>
  </c:forEach>
  
  
  
  
  
  
  </UL>
  </DIV>
  </SECTION>
  </ARTICLE>
  
</DIV>



<DIV class=go-top><A class=back-to-top href="#"><SPAN 
class=arrow></SPAN>
<P>回顶部</P></A>
</DIV>
<a href="manage/login.jsp">
管理后台
</a>

<SCRIPT src="_files/cdntest5.js"></SCRIPT>

</BODY></HTML>
