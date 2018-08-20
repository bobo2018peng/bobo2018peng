<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 头部 -->
<HEADER class="head layout">
<DIV class=head-hd align="center">

<span style="font-size: 40px;font-weight: bold;color: #339900;">
彭文波艺术作品论坛


</span>


</DIV>


<DIV class=head-bd>
<NAV class=menu>
<a href=".">
<STRONG>首页</STRONG> 
</a>
<A href="register.jsp" rel=nofollow>用户注册</A> 
<A href="method!user.action" >管理中心</A> 

<c:if test="${user==null}">
<A href="login.jsp">用户登录</A> 
</c:if>
<c:if test="${user!=null}">
<A href="indexmethod!loginout.action">用户退出</A> 
<span style="font-size: 20px;">
欢迎${user.truename }使用本论坛
</span>
</c:if>

</NAV>




</DIV>
</HEADER>