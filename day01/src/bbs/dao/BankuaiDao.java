package bbs.dao;

import java.util.List;

import bbs.model.Bankuai;





public interface BankuaiDao  {
	
	
	
	public void insertBean(Bankuai bean);
	
	public void deleteBean(Bankuai bean);
	
	public void updateBean(Bankuai bean);

	public Bankuai selectBean(String where);
	
	public List<Bankuai> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
