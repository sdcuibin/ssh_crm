package cn.cuibusi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.spi.id.TableBasedDeleteHandlerImpl;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	//添加客户功能
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}

	//列表功能
//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}

	//根据id查询
//	public Customer findOne(int cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);
//
//	}

	//删除方法
//	public void delete(Customer c) {
//		this.getHibernateTemplate().delete(c);
//	}

	//修改功能
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}

	//查询记录数
	public int findcount() {
		@SuppressWarnings("all")
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//从list中把值得到
		if(list!=null && list.size()!=0){
			Object obj = list.get(0);
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//分页查询操作
	@SuppressWarnings("all")
	public List<Customer> findPage(int begin, int pageSize) {
		//1.使用hibernate底层代码实现
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		List<Customer> list = query.list();
		
		//2.使用离线对象和hibernate模版实现
		//创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//调用hibernateTemplete进行操作
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//条件查询方法
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		//第一种
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//     	Session session = sessionFactory.getCurrentSession();
//     	Query query = session.createQuery("from Customer where custName like ?");
//     	query.setParameter(0,"%"+customer.getCustName()+"%");
//     	List<Customer> list = query.list();
		//第二种
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().
//		find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		
		//第三种 离线
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	//条件查询
	@SuppressWarnings("all")
	public List<Customer> findMoreCondition(Customer customer) {
		//拼接hql语句
		String hql = "from Customer where 1=1";
		List<Object> p = new ArrayList<Object>();
		//判断条件值是否为空
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())){
			hql += " and custName=?";
			p.add(customer.getCustName());
		}
//		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel())){
//			hql += " and custLevel=?";
//			p.add(customer.getCustLevel());
//		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())){
			hql += " and custSource=?";
			p.add(customer.getCustSource());
		}
		return (List<Customer>) this.getHibernateTemplate().find(hql,p.toArray());
	}

	//查询所有级别
	@SuppressWarnings("all")
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//根据客户来源统计
	public List<Customer> findCountSource() {
		//写复杂语句，调用底层sql
		//SQLQuery对象
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource");
		//把返回数据转换成map集合
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}

	//根据客户级别统计
	public List findCountLevel() {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM t_customer GROUP BY custLevel) c , t_dict d WHERE c.custLevel=d.did");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}

}
