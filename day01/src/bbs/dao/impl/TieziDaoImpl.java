package bbs.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbs.dao.TieziDao;
import bbs.model.Tiezi;











public class TieziDaoImpl extends HibernateDaoSupport implements  TieziDao{


	public void deleteBean(Tiezi bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Tiezi bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Tiezi selectBean(String where) {
		List<Tiezi> list = this.getHibernateTemplate().find("from Tiezi " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Tiezi "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Tiezi> selectBeanList(final int start,final int limit,final String where) {
		return (List<Tiezi>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Tiezi> list = session.createQuery("from Tiezi "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Tiezi bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
