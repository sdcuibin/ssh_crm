package cn.cuibusi.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.cuibusi.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

//	private HibernateTemplate hibernateTemplate;
//
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	@SuppressWarnings("unchecked")
	public User userLogin(User user) {
		//调用方法得到hibernateTemplate对象
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		//登录的查询操作
		List<User> list = (List<User>) hibernateTemplate.
		find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		if(list!=null && list.size()!=0){
		User u = list.get(0);
		return u;
		}
			return null;
	}

	//查询所有用户
	@SuppressWarnings("all")
	public List<User> findAll() {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User");
		return list;
	}
}
