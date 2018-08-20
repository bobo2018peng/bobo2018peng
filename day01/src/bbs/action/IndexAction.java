package bbs.action;

import java.io.IOException;
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
import bbs.util.Pager2;
import bbs.util.Util;

import com.opensymphony.xwork2.ActionSupport;



public class IndexAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

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
	
	

	//首页入口
	public String index() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		List<Bankuai> list = bankuaiDao.selectBeanList(0, 9999, " where deletestatus=0 ");
		request.setAttribute("list", list);
		
		return "success";
	}
	
	//用户注册操作
	public void register2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setTruename(request.getParameter("truename"));
		user.setLianxifangshi(request.getParameter("lianxifangshi"));
		user.setCreatetime(new Date());
		user.setRole(3);
		user.setTouxiang("touxiang.jpg");
		user.setCreatetime(new Date());
		
		User u = userDao.selectBean(" where username='"+user.getUsername()+"'");
		
		if(u==null){
			userDao.insertBean(user);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('注册成功，您的用户名是"+user.getUsername()+",请妥善保管');window.location.href='login.jsp';</script>");
		}else{
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('注册失败！该用户名已经存在！');window.location.href='register.jsp';</script>");
		}
		
	}
	
	
	//用户登录操作
	public void login2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = userDao.selectBean(" where role!=1 and  username='"+username+"' and password='"+password+"' and deletestatus=0");
		if(u==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误！');window.location.href='login.jsp';</script>");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('登录成功！');window.location.href='./';</script>");
		}
	}
	
	
	//用户退出操作
	public void loginout() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("bankuai");
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('退出成功');window.location.href='./';</script>");
	}
	
	
	private BankuaiDao bankuaiDao;


	public BankuaiDao getBankuaiDao() {
		return bankuaiDao;
	}


	public void setBankuaiDao(BankuaiDao bankuaiDao) {
		this.bankuaiDao = bankuaiDao;
	}
	
	
	private TieziDao tieziDao;


	public TieziDao getTieziDao() {
		return tieziDao;
	}


	public void setTieziDao(TieziDao tieziDao) {
		this.tieziDao = tieziDao;
	}
	
	
	
	//板块列表
	public String bankuailist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Bankuai bean = bankuaiDao.selectBean(" where id= "+request.getParameter("id"));
		
		int tiezicount = tieziDao.selectBeanCount(" where deletestatus=0 and bankuai.id= "+bean.getId());
		request.setAttribute("tiezicount", tiezicount);//该板块总帖子数
		
		int huifucount = huifuDao.selectBeanCount(" where deletestatus=0  and tiezi.bankuai.id= "+bean.getId());
		request.setAttribute("huifucount", huifucount);//该板块总回复数
		
		//版主推荐
		
		
		request.setAttribute("tjlist", tieziDao.selectBeanList(0, 10, " where tuijian='推荐'  and  deletestatus=0 and   bankuai.id= "+bean.getId()));
		
		request.setAttribute("bankuai", bean);
		
		String leixing = request.getParameter("leixing");
		String title = request.getParameter("title");
		String paixu = request.getParameter("paixu");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (leixing != null && !"".equals(leixing)) {
			if("1".equals(leixing)){
				leixing = "帖子";
			}else if("2".equals(leixing)){
				leixing = "精华";
			}else if("3".equals(leixing)){
				leixing = "求助";
			}else if("4".equals(leixing)){
				leixing = "活动";
			}
			

			sb.append("leixing like '%" + leixing + "%'");
			sb.append(" and ");
			request.setAttribute("leixing", leixing);
		}
		
		if (title != null && !"".equals(title)) {
			
			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}
		
		
		sb.append("  bankuai.id="+bean.getId()+"  and  deletestatus=0  ");
		
		if("1".equals(paixu)){
			sb.append(" order by id desc ");
		}else if("2".equals(paixu)){
			sb.append(" order by huifutime desc ");
		}else if("3".equals(paixu)){
			sb.append(" order by huifushu desc ");
		}else {
			sb.append(" order by id desc ");
		}
		
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		int total = 0;
		if("1".equals(paixu)){
			total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		}else if("2".equals(paixu)){
			total = tieziDao.selectBeanCount(where.replaceAll(" order by huifutime desc", ""));
		}else if("3".equals(paixu)){
			total = tieziDao.selectBeanCount(where.replaceAll(" order by huifushu desc", ""));
		}else{
			total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		}
		request.setAttribute("pagerinfo", Pager2.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!bankuailist.action?id="+bean.getId(), ""));
		this.setUrl("bankuailist.jsp");
		return SUCCESS;

	}
	
	
	
	//跳转到发帖页面
	public String fatie() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('请先登录！');window.location.href='login.jsp';</script>");
			return null;
		}
		request.setAttribute("bid", request.getParameter("bid"));
		request.setAttribute("leixing", request.getParameter("leixing"));
		this.setUrl("fatie.jsp");
		return SUCCESS;
	}
	
	//发帖操作
	public void fatie2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('请先登录！');window.location.href='login.jsp';</script>");
			return ;
		}
		String title = request.getParameter("title");
		String content1 = request.getParameter("content1");
		String bid = request.getParameter("bid");
		Bankuai bankuai = bankuaiDao.selectBean(" where id= "+bid);
		String leixing = request.getParameter("leixing");
		Tiezi bean  = new Tiezi();
		bean.setBankuai(bankuai);
		bean.setContent(content1);
		bean.setCreatetime(new Date());
		if("1".equals(leixing)){
			bean.setLeixing("帖子");
		}else if("2".equals(leixing)){
			bean.setLeixing("求助");
		}
		bean.setTitle(title);
		bean.setUser(user);
		tieziDao.insertBean(bean);
		
		User u = userDao.selectBean(" where id= "+user.getId() );
		
		u.setFatieshu(u.getFatieshu()+1);

		userDao.updateBean(u);
		
		
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('发布成功');" +
						"window.location.href='indexmethod!bankuailist.action?id="+bean.getBankuai().getId()+"';</script>");
	}
	
	
	private HuifuDao huifuDao;


	public HuifuDao getHuifuDao() {
		return huifuDao;
	}


	public void setHuifuDao(HuifuDao huifuDao) {
		this.huifuDao = huifuDao;
	}
	
	
	//跳转到查看帖子页面
	public String tiezi() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String tid = request.getParameter("tid");
		Tiezi bean = tieziDao.selectBean(" where id= "+tid);
		
		List<Huifu> list = huifuDao.selectBeanList(0, 9999, " where tiezi.id="+bean.getId()+"  and  deletestatus=0 order by id ");
		for(int i=0;i<list.size();i++){
			Huifu hf = list.get(i);
			if(i==0){
				hf.setWeizhi("沙发");
			}
			else if(i==1){
				hf.setWeizhi("板凳");
			}
			else if(i==2){
				hf.setWeizhi("地板");
			}
			else{
				hf.setWeizhi((i-2)+"#");
			}
			huifuDao.updateBean(hf);
		}
		
		
		request.setAttribute("tiezi", bean);
		
		
		bean.setDianjishu(bean.getDianjishu()+1);
		tieziDao.updateBean(bean);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		
		
		
		sb.append("  tiezi.id="+bean.getId()+"  and  deletestatus=0  ");
		
		sb.append(" order by id  ");
		
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		
		request.setAttribute("huifulist", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		int total = huifuDao.selectBeanCount(where.replaceAll("order by id", ""));
		
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!tiezi.action?tid="+bean.getId(), ""));
		
		this.setUrl("content.jsp");
		return SUCCESS;
	}
	
	
	//回复操作
	public void huifu2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = request.getParameter("tid");
		if(user==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('请先登录！');window.location.href='login.jsp';</script>");
			return ;
		}

		String content1 = request.getParameter("content1");
		
		if(content1==null||"".equals(content1)){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('回复内容不能为空');" +
							"window.location.href='indexmethod!tiezi.action?tid="+tid+"';</script>");
			return ;
		}
		

		Tiezi tiezi = tieziDao.selectBean(" where id= "+tid);
		Huifu bean = new Huifu();
		bean.setContent(content1);
		bean.setCreatetime(new Date());
		bean.setTiezi(tiezi);
		
		bean.setUser(user);
		huifuDao.insertBean(bean);
		
		tiezi.setHuifutime(new Date());
		tiezi.setHuifushu(tiezi.getHuifushu()+1);
		tieziDao.updateBean(tiezi);
		User u = userDao.selectBean(" where id= "+user.getId());
		
		u.setHuifushu(u.getHuifushu()+1);
		userDao.updateBean(u);
		
		
		
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('发布成功');" +
						"window.location.href='indexmethod!tiezi.action?tid="+tid+"';</script>");
	}
	
	
	
	
	
	
	
	
	
	

}
