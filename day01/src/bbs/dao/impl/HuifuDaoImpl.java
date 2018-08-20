package bbs.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbs.dao.HuifuDao;
import bbs.model.Huifu;











public class HuifuDaoImpl extends HibernateDaoSupport implements  HuifuDao{


	public void deleteBean(Huifu bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Huifu bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Huifu selectBean(String where) {
		List<Huifu> list = this.getHibernateTemplate().find("from Huifu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Huifu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Huifu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Huifu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Huifu> list = session.createQuery("from Huifu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Huifu bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
