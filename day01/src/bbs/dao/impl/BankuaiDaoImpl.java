package bbs.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbs.dao.BankuaiDao;
import bbs.model.Bankuai;











public class BankuaiDaoImpl extends HibernateDaoSupport implements  BankuaiDao{


	public void deleteBean(Bankuai bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Bankuai bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Bankuai selectBean(String where) {
		List<Bankuai> list = this.getHibernateTemplate().find("from Bankuai " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Bankuai "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Bankuai> selectBeanList(final int start,final int limit,final String where) {
		return (List<Bankuai>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Bankuai> list = session.createQuery("from Bankuai "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Bankuai bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
