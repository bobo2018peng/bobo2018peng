package bbs.dao;

import java.util.List;

import bbs.model.Huifu;





public interface HuifuDao  {
	
	
	
	public void insertBean(Huifu bean);
	
	public void deleteBean(Huifu bean);
	
	public void updateBean(Huifu bean);

	public Huifu selectBean(String where);
	
	public List<Huifu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
