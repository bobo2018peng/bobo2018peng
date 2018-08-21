<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0069)http://taizhou.19lou.com/forum-827-thread-164231376836350284-1-1.html -->
<HTML><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>彭文波艺术作品论坛</TITLE>
<META charset=gb2312>


<LINK rel=stylesheet href="_files3/view-min.css">
<LINK rel=stylesheet href="_files3/viewqqb.css">
<LINK rel=stylesheet href="_files3/view_baipin.css">


<META name=GENERATOR content="MSHTML 8.00.7601.18210">

</HEAD>
<BODY>





<DIV style="POSITION: relative" id=view-wrap class="layout&#13;&#10;has-bigshow" >



<DIV class=view-crumbs>
<DIV id=nav class="layout link0" itemprop="breadcrumb">当前位置：
<A href=".">首页</A> <I>&gt;&nbsp;</I>
<A  href="indexmethod!bankuailist.action?id=${tiezi.bankuai.id }">${tiezi.bankuai.bankuaiming }</A> <I>&gt;&nbsp;</I><EM>查看帖子</EM> <I>&nbsp;</I>
</DIV></DIV>



<DIV id=view-hd>
<H1 class="fl f14 link1">
<A ><SPAN >${tiezi.title }</SPAN> </A></H1>
<UL class="fr clearall color9 view-hd-num">
  <LI><I id=J_threadViewNum class=color2 
  itemprop="http://rdfs.org/sioc/ns#num_views">${tiezi.dianjishu }</I>阅读 </LI>
  <LI><I id=J_threadReplyNum class=color2 
  itemprop="http://rdfs.org/sioc/ns#num_replies">${tiezi.huifushu }</I>回复 </LI>
  
  
  </UL>
  
  </DIV>

<FORM id=postForm method=get name=postForm target=_blank>

<DIV id=view-bd>

<DIV id=164481376836350599 class="clearall floor first"  data-floor="f-3">

<DIV id=pid164481376836350599 class=cont>

<DIV class=cont-bd>

<DIV class="link0 view-cont" >
<TABLE class=view-data border=0 cellSpacing=0 cellPadding=0>
  <TBODY>
  <TR>
    <TD>
      <DIV class=thread-cont>
      <DIV class=thread-cont>
      <DIV align=left>
    ${tiezi.content }  
      
      </DIV>
      
    
    </DIV>
      </DIV></TD></TR></TBODY></TABLE>
      
      
     </DIV>



</DIV>
</DIV>

<DIV class=side >
<DIV id=user_online_uid_36108552 class=author>
<DIV id=uname_164481376836350599 class=uname>
<A class=fb  href="method!kongjian.action?id=${tiezi.user.id }">
<SPAN >${tiezi.user.truename }</SPAN></A></DIV>
</DIV>

<DIV class=uavatar>
<A href="method!kongjian.action?id=${tiezi.user.id }" rel=nofollow>
<IMG class=J_lazyImgs  src="<%=basePath %>uploadfile/${tiezi.user.touxiang }"  width=120 > </A>
<P class=say></P></DIV>
<DL class="color9 uinfo clearall">
  <DT>发帖： </DT>
  <DD class=color6>${tiezi.user.fatieshu } </DD>
  <DT>回复： </DT>
  <DD class=usocial><SPAN class="color6 fl" itemprop="coins">${tiezi.user.huifushu }</SPAN> 
  </DD>
  <DT style="WIDTH: 60px">注册时间： </DT>
  <DD style="WIDTH: 94px" class=color6 itemprop="registeredTime">${fn:substring(tiezi.user.createtime,0, 10)}
</DD>
</DL>

<P>

</P>

<P class=umedal></P></DIV>
</DIV>



<!-- 回帖 -->

<c:forEach items="${huifulist}" var="bean2" varStatus="v">
<DIV id=141601376877321141 class="clearall floor" >
<DIV  class=cont>
<DIV class="cont-hd clearall">
<P class="fl link1">发表于
<SPAN >${fn:substring(bean2.createtime,0, 19)}</SPAN> 
</P>

<div align="right">

<SPAN style="color: #FF6600;font-weight: bold;font-size: 20px;">${bean2.weizhi }</SPAN>

</div>
</DIV>
<DIV class=cont-bd>

<DIV class="link0 view-cont" itemprop="http://rdfs.org/sioc/ns#content">
<TABLE class=view-data border=0 cellSpacing=0 cellPadding=0>
  <TBODY>
  <TR>
    <TD>
      <H2></H2></TD></TR>
  <TR>
    <TD>
      <DIV class=thread-cont>
     ${bean2.content }
      </DIV>
      </TD></TR></TBODY></TABLE>
      
 </DIV>


<DIV class=view-ft>
<DIV class=user-sign></DIV>

</DIV>
</DIV>
</DIV>

<DIV class=side >
<DIV id=user_online_uid_31806651 class=author>
<DIV id=uname_141601376877321141 class=uname>
<A style="COLOR: #7200ff" class=fb  href="method!kongjian.action?id=${bean2.user.id }"><SPAN >${bean2.user.truename } </SPAN></A></DIV>
</DIV>

<DIV class=uavatar>
<A href="method!kongjian.action?id=${bean2.user.id }" rel=nofollow>
<IMG class=J_lazyImgs src="<%=basePath %>uploadfile/${bean2.user.touxiang }" width=120 > 
</A>
<P class=say></P>
</DIV>

<DL class="color9 uinfo clearall">
  <DT>发帖： </DT>
  <DD class=color6>${bean2.user.fatieshu } </DD>
  <DT>回复： </DT>
  <DD class=usocial><SPAN class="color6 fl" itemprop="coins">${bean2.user.huifushu } </SPAN> </DD>
  <DT style="WIDTH: 60px">注册时间： </DT>
  <DD style="WIDTH: 94px" class=color6 itemprop="registeredTime">${fn:substring(bean2.user.createtime,0, 10)} 
</DD></DL>
<P>
</P>
<P class=umedal></P>
</DIV>

</DIV>

</c:forEach>

</FORM>


<DIV class="mb10 clearall view-ctrl">
${pagerinfo }
<A class=return href="indexmethod!bankuailist.action?id=${tiezi.bankuai.id }" ttname="bbs_detail_returnlist">返回列表</A> </DIV>



<DIV id=quickreply class="clearall floor view-reply">



<DIV class="clearall reply-bd">
<DIV class=cont>
<form action="indexmethod!huifu2.action?tid=${tiezi.id }"  method="post" >
<span style="font-size: 30px;font-weight: bold;">快速回复：</span>
<br/>
<textarea id="content1" name="content1" style="display: none;"></textarea>
    <iframe ID="eWebEditor" src="editor/ewebeditor.htm?id=content1" frameborder="0" scrolling="no" width="600" height="300"></iframe>	
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <INPUT class=list-yd  value=提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交 type="submit" size="30" /> 
 </form>
</DIV>

</DIV>
</DIV>

</DIV>


<DIV class=go-top><A class=back-to-top href="#"><SPAN 
class=arrow></SPAN>
<P>回顶部</P></A>
</DIV>





</BODY></HTML>


