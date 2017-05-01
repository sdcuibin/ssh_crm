package cn.cuibusi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.cuibusi.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//添加联系人方法
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}
	
	//查询所有
	@SuppressWarnings("all")
	public List<LinkMan> list() {
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
		return list;
	}
	
	//查询单个
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}
	
	//修改联系人
	public void updateLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}
		
	//删除联系人
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
	}

	//条件查询 离线查询方式
	@SuppressWarnings("all")
	public List<LinkMan> findmoreCondition(LinkMan linkMan) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())){
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0){
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	//条件查询联系人
//	public List<LinkMan> findmoreCondition(LinkMan linkMan) {
//		String hql = "from LinkMan where 1=1 ";
//		List<Object> p = new ArrayList<Object>();
//		//判断条件值是否为空
//		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
//			hql += " and lkmName=?";
//			p.add(linkMan.getLkmName());
//		}
//		//判断是否选择客户
//		if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
//			//判断客户里面cid值
//			hql += " and customer.cid=?";
//			p.add(linkMan.getCustomer().getCid());
//		}
//		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
//	}

}
