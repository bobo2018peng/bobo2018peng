package bbs.dao;

import java.util.List;

import bbs.model.Tiezi;





public interface TieziDao  {
	
	
	
	public void insertBean(Tiezi bean);
	
	public void deleteBean(Tiezi bean);
	
	public void updateBean(Tiezi bean);

	public Tiezi selectBean(String where);
	
	public List<Tiezi> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
