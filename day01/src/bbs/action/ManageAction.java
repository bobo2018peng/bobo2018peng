package bbs.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bbs.dao.BankuaiDao;

import bbs.dao.HuifuDao;

import bbs.dao.TieziDao;
import bbs.dao.UserDao;
import bbs.model.Bankuai;
import bbs.model.Huifu;
import bbs.model.Tiezi;
import bbs.model.User;
import bbs.util.Pager;
import bbs.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
//登入请求
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role= "+1 +" and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("manage", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("manage");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("manage");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');window.location.href='method!changepwd.action';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd.action';</script>");
		}
	}
	
	
	
	private BankuaiDao bankuaiDao;

	public BankuaiDao getBankuaiDao() {
		return bankuaiDao;
	}

	public void setBankuaiDao(BankuaiDao bankuaiDao) {
		this.bankuaiDao = bankuaiDao;
	}
	
	//板块列表
	public String bankuailist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bankuaiming = request.getParameter("bankuaiming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (bankuaiming != null && !"".equals(bankuaiming)) {

			sb.append("bankuaiming like '%" + bankuaiming + "%'");
			sb.append(" and ");
			request.setAttribute("bankuaiming", bankuaiming);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = bankuaiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", bankuaiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!bankuailist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!bankuailist.action");
		request.setAttribute("url2", "method!bankuai");
		request.setAttribute("title", "板块管理");
		this.setUrl("bankuai/bankuailist.jsp");
		return SUCCESS;

	}
//跳转到添加板块页面
	public String bankuaiadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!bankuaiadd2.action");
		request.setAttribute("title", "板块添加");
		this.setUrl("bankuai/bankuaiadd.jsp");
		return SUCCESS;
	}
	
	private File uploadfile;

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
//添加板块操作
	public void bankuaiadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bankuaiming = request.getParameter("bankuaiming");
		Bankuai bean = bankuaiDao.selectBean(" where bankuaiming='"+bankuaiming+"' and deletestatus=0 ");
		if(bean==null){
			String info = request.getParameter("info");
			bean = new Bankuai();
			bean.setBankuaiming(bankuaiming);
			bean.setCreatetime(new Date());
			bean.setInfo(info);
			if(uploadfile!=null){
				String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";
				String time = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).toString();
				String bankuaiimgPath = time+".jpg";
				File imageFile = new File(savaPath + bankuaiimgPath);
				Util.copyFile(uploadfile, imageFile);
				bean.setBankuaiimagePath(bankuaiimgPath);
			}
			
			bankuaiDao.insertBean(bean);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作成功');window.location.href='method!bankuailist.action';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作失败，该板块名已经存在');window.location.href='method!bankuailist.action';</script>");
		}
	}
//跳转到更新板块页面
	public String bankuaiupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!bankuaiupdate2.action?id="+bean.getId());
		request.setAttribute("title", "板块修改");
		this.setUrl("bankuai/bankuaiupdate.jsp");
		return SUCCESS;
	}
//更新板块操作
	public void bankuaiupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setInfo(info);
		
		bankuaiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bankuailist.action';</script>");
	}
//删除板块操作
	public void bankuaidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		bankuaiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bankuailist.action';</script>");
	}
	
	//跳转到查看板块页面
	public String bankuaiupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "板块查看");
		this.setUrl("bankuai/bankuaiupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	
	//跳转到指定板块管理员页面
	public String bankuaiupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		List<User> list2 = userDao.selectBeanList(0, 9999, " where role=3  ");
		List<User> list3 = new ArrayList<User>();
		for(User u:list2){
			int flag = 0;
			for(Bankuai b:list){
				if( b.getGuanli()!=null && u.getId()==b.getGuanli().getId()){
					flag = 1;
					break;
				}
			}
			if(flag==0){
				list3.add(u);
			}
		}
		
		
		
		request.setAttribute("userlist",list3 );
		
		request.setAttribute("url", "method!bankuaiupdate6.action?id="+bean.getId());
		request.setAttribute("title", "指定板块管理员");
		this.setUrl("bankuai/bankuaiupdate5.jsp");
		return SUCCESS;
	}
//指定板块管理员操作
	public void bankuaiupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String guanli = request.getParameter("guanli");
		Bankuai bean = bankuaiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setGuanli(userDao.selectBean(" where id= "+guanli));
		bankuaiDao.updateBean(bean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bankuailist.action';</script>");
	}
	
	
	
	//用户管理中心
	public String user() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.getWriter().print(
							"<script language=javascript>alert('请先登录');window.location.href='login.jsp';</script>");
			return null;
		}
		
		int flag =0 ;
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		for(Bankuai bean:list){
			if(bean.getGuanli()!=null && user.getId()==bean.getGuanli().getId()){
				flag =1 ;
				
			}
		}
		if(flag==1){
			session.setAttribute("bankuai", "1");
		}
		
		
		this.setUrl("user/index.jsp");
		return "redirect";
	}
	

	
	//跳转到修改密码页面
	public String changepwd3() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');window.location.href='method!changepwd3.action';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd3.action';</script>");
		}
	}
	
	
	//跳转到个人信息中心页面
	public String useradd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!useradd2.action");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		User bean = userDao.selectBean(" where id= "+user.getId());
		request.setAttribute("bean", bean);
		request.setAttribute("title", "个人信息中心");
		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}
	
	
	
//个人信息中心操作
	public void useradd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jianjie = request.getParameter("jianjie");
		String lianxifangshi = request.getParameter("lianxifangshi");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		User bean = userDao.selectBean(" where id= "+user.getId());
		bean.setJianjie(jianjie);
		bean.setLianxifangshi(lianxifangshi);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).toString();
			String userimgPath = time+".jpg";
			File imageFile = new File(savaPath + userimgPath);
			Util.copyFile(uploadfile, imageFile);
			bean.setTouxiang(userimgPath);
		}
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!useradd.action';</script>");
	}
	
	private TieziDao tieziDao;
	
	
	public TieziDao getTieziDao() {
		return tieziDao;
	}

	public void setTieziDao(TieziDao tieziDao) {
		this.tieziDao = tieziDao;
	}

	//帖子列表
	public String tiezilist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  deletestatus=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "帖子管理");
		this.setUrl("tiezi/tiezilist.jsp");
		return SUCCESS;

	}
	
	
	//删除帖子操作
	public void tiezidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		tieziDao.updateBean(bean);
		
		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setFatieshu(u.getFatieshu()-1);
		userDao.updateBean(u);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist.action';</script>");
	}
	
	//跳转到查看帖子页面
	public String tieziupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "帖子查看");
		this.setUrl("tiezi/tieziupdate3.jsp");
		return SUCCESS;
	}
	
	
	private HuifuDao huifuDao;
	
	
	public HuifuDao getHuifuDao() {
		return huifuDao;
	}

	public void setHuifuDao(HuifuDao huifuDao) {
		this.huifuDao = huifuDao;
	}

	//回复列表
	public String huifulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  deletestatus=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = huifuDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!huifulist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!huifulist.action");
		request.setAttribute("url2", "method!huifu");
		request.setAttribute("titletitle", "回复管理");
		this.setUrl("huifu/huifulist.jsp");
		return SUCCESS;

	}
	
	
	//删除回复操作
	public void huifudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		huifuDao.updateBean(bean);
		
		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setHuifushu(u.getHuifushu()-1);
		userDao.updateBean(u);
		Tiezi t = tieziDao.selectBean(" where id= "+bean.getTiezi().getId());
		t.setHuifushu(t.getHuifushu()-1);
		tieziDao.updateBean(t);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!huifulist.action';</script>");
	}
	
	//跳转到查看回复页面
	public String huifuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "回复查看");
		this.setUrl("huifu/huifuupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	
	
	//用户空间中心
	public String kongjian() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		
		this.setUrl("kongjian/index.jsp?id="+id);
		return "redirect";
	}
	
	//帖子列表
	public String tiezilist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String uid = request.getParameter("uid");
		
		User u = userDao.selectBean(" where id= "+uid);
		request.setAttribute("u", u);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}


		
		sb.append("  deletestatus=0  and user.id="+uid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist2.action?uid="+uid, "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist2.action?uid="+uid);
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "帖子查看");
		this.setUrl("tiezi/tiezilist2.jsp");
		return SUCCESS;

	}
	
	
	//回复查看
	public String huifulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String uid = request.getParameter("uid");

		User u = userDao.selectBean(" where id= "+uid);
		request.setAttribute("u", u);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

	
		
		sb.append("  deletestatus=0  and user.id="+uid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = huifuDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!huifulist2.action?uid="+uid, "共有" + total + "条记录"));
		request.setAttribute("url", "method!huifulist2.action?uid="+uid);
		request.setAttribute("url2", "method!huifu");
		request.setAttribute("titletitle", "回复查看");
		this.setUrl("huifu/huifulist2.jsp");
		return SUCCESS;

	}
	
	
	//帖子合法性列表
	public String tiezilist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		int bankuaiid = 0;
		for(Bankuai b:list){
			if(b.getGuanli()!=null&&b.getGuanli().getId()==user.getId()){
				bankuaiid = b.getId();
				break;
			}
		}
		
		

		
		
		sb.append("  deletestatus=0  and bankuai.id="+bankuaiid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist3.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist3.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "帖子合法性管理");
		this.setUrl("tiezi/tiezilist3.jsp");
		return SUCCESS;

	}
	
	
	//删除帖子合法性操作
	public void tiezidelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		tieziDao.updateBean(bean);
		
		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setFatieshu(u.getFatieshu()-1);
		userDao.updateBean(u);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist3.action';</script>");
	}
	
	
	//回复合法性列表
	public String huifulist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		int bankuaiid = 0;
		for(Bankuai b:list){
			if(b.getGuanli()!=null&&b.getGuanli().getId()==user.getId()){
				bankuaiid = b.getId();
				break;
			}
		}
		
		sb.append("  deletestatus=0  and tiezi.bankuai.id="+bankuaiid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = huifuDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!huifulist3.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!huifulist3.action");
		request.setAttribute("url2", "method!huifu");
		request.setAttribute("titletitle", "回复合法性管理");
		this.setUrl("huifu/huifulist3.jsp");
		return SUCCESS;

	}
	
	
	//删除回复合法性操作
	public void huifudelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		huifuDao.updateBean(bean);
		
		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setHuifushu(u.getHuifushu()-1);
		userDao.updateBean(u);
		Tiezi t = tieziDao.selectBean(" where id= "+bean.getTiezi().getId());
		t.setHuifushu(t.getHuifushu()-1);
		tieziDao.updateBean(t);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!huifulist3.action';</script>");
	}
	
	//精华帖子列表
	public String tiezilist4() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		int bankuaiid = 0;
		for(Bankuai b:list){
			if(b.getGuanli()!=null&&b.getGuanli().getId()==user.getId()){
				bankuaiid = b.getId();
				break;
			}
		}
		
		

		
		
		sb.append("  deletestatus=0  and bankuai.id="+bankuaiid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist4.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist4.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "精华帖子管理");
		this.setUrl("tiezi/tiezilist4.jsp");
		return SUCCESS;

	}
	
	
	//设置精华帖子操作
	public void tiezidelete4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setLeixing("精华");
		
		tieziDao.updateBean(bean);
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist4.action';</script>");
	}
	
	//取消精华帖子操作
	public void tiezidelete5() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if("精华".equals(bean.getLeixing())){
			bean.setLeixing("帖子");
		}
		
		tieziDao.updateBean(bean);
		

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist4.action';</script>");
	}
	
	
	//版主推荐列表
	public String tiezilist5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		int bankuaiid = 0;
		for(Bankuai b:list){
			if(b.getGuanli()!=null&&b.getGuanli().getId()==user.getId()){
				bankuaiid = b.getId();
				break;
			}
		}
		
		

		
		
		sb.append("  deletestatus=0  and bankuai.id="+bankuaiid+" order by tuijian desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll("order by tuijian desc", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist5.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist5.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "版主推荐管理");
		this.setUrl("tiezi/tiezilist5.jsp");
		return SUCCESS;

	}
	
	
	//设置版主推荐操作
	public void tiezidelete6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setTuijian("推荐");
		
		tieziDao.updateBean(bean);
		
	
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist5.action';</script>");
	}
	
	//取消版主推荐操作
	public void tiezidelete7() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("");
		tieziDao.updateBean(bean);
		

		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist5.action';</script>");
	}
	
	
	
	
	//注册用户列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}

		
		
		
		sb.append("  role!=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist.action");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "注册用户管理");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}

	

//锁定注册用户操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist.action';</script>");
	}
	
	//解锁注册用户操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(0);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist.action';</script>");
	}
	
	
	//活动管理列表
	public String tiezilist8() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		sb.append("  deletestatus=0 and user.id="+user.getId()+" and leixing='活动' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist8.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist8.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "活动管理管理");
		this.setUrl("tiezi/tiezilist8.jsp");
		return SUCCESS;

	}
//跳转到添加活动管理页面
	public String tieziadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!tieziadd2.action");
		request.setAttribute("title", "活动管理添加");
		this.setUrl("tiezi/tieziadd.jsp");
		return SUCCESS;
	}
	

	
//添加活动管理操作
	public void tieziadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		Tiezi bean = new Tiezi();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		int bankuaiid = 0;
		for(Bankuai b:list){
			if(b.getGuanli()!=null&&b.getGuanli().getId()==user.getId()){
				bankuaiid = b.getId();
				break;
			}
		}
		bean.setBankuai(bankuaiDao.selectBean(" where id= "+bankuaiid));
		bean.setContent(content);
		bean.setCreatetime(new Date());
		bean.setLeixing("活动");
		bean.setTitle(title);
		bean.setUser(user);
		tieziDao.insertBean(bean);
		
		User u = userDao.selectBean(" where id= "+user.getId());
		u.setFatieshu(u.getFatieshu()+1);
		userDao.updateBean(u);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist8.action';</script>");
		
	
	}

//删除活动管理操作
	public void tiezidelete8() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		tieziDao.updateBean(bean);
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist8.action';</script>");
	}
}
