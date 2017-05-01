package cn.cuibusi.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.cuibusi.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//添加客户拜访
	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}
	
	//查询所有拜访
	@SuppressWarnings("all")
	public List<Visit> findAll() {
		List<Visit> list = (List<Visit>) this.getHibernateTemplate().find("from Visit");
		return list;
	}

	//
	@SuppressWarnings("all")
	public List<Visit> moreCondition(Visit visit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		if(visit.getUser().getUid()!=null && visit.getUser().getUid()>0){
			criteria.add(Restrictions.eq("user.uid", visit.getUser().getUid()));
		}
		if (visit.getCustomer().getCid()!=null && visit.getCustomer().getCid()>0) {
			criteria.add(Restrictions.eq("customer.cid", visit.getCustomer().getCid()));
		}
		
		return (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
