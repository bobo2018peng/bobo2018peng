<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="left">
	<div id="lhead">管理菜单</div>
	<div style="margin-top:  -50px;">
	<ul>
	<c:if test="${bankuai!=null}">
	<li ><a href="#">版主管理中心</a></li>
	<li ><a href="method!tiezilist3.action">帖子合法性管理</a></li>
	<li ><a href="method!huifulist3.action">回复合法性管理</a></li>
	<li ><a href="method!tiezilist4.action">精华帖管理</a></li>
	<li ><a href="method!tiezilist5.action">版主推荐管理</a></li>
	<li ><a href="method!tiezilist8.action">板块活动管理</a></li>
	<br/><br/><br/>

	</c:if>
	<li ><a href="#">个人管理中心</a></li>
	<li ><a href="method!useradd.action">个人信息管理</a></li>
	<li ><a href="method!tiezilist.action">我的帖子管理</a></li>
	<li ><a href="method!huifulist.action">我的回复管理</a></li>
	
	</ul>
	</div>
</div>