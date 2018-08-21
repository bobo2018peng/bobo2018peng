package com.itheima.crm.pojo;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 板块
 */
@Entity
@Table(name="t_bankuai")
public class Bankuai {
	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	private String bankuaiming;//版块名称
	
	private String info;//简单介绍该板块

	@ManyToOne
	@JoinColumn(name="guanliid")
	private User guanli;//版块管理者
	
	private Date createtime;
	
	private String bankuaiimagePath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getBankuaiming() {
		return bankuaiming;
	}

	public void setBankuaiming(String bankuaiming) {
		this.bankuaiming = bankuaiming;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public User getGuanli() {
		return guanli;
	}

	public void setGuanli(User guanli) {
		this.guanli = guanli;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getBankuaiimagePath() {
		return bankuaiimagePath;
	}

	public void setBankuaiimagePath(String bankuaiimagePath) {
		this.bankuaiimagePath = bankuaiimagePath;
	}	
}
