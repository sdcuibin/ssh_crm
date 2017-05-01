package cn.cuibusi.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.omg.PortableServer.ThreadPolicyOperations;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class pClass;
	//构造方法
	public BaseDaoImpl() {
		//得到当前运行类的class
		Class clazz = this.getClass();
		//得到运行类的父类的参数化类型
		//使用type的子接口ParamterizedType
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		//得到实际类型参数
		Type[] types = ptype.getActualTypeArguments();
		Class tclass = (Class) types[0];
		this.pClass = tclass;
	}

	//添加
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//根据id查询
	@SuppressWarnings("all")
	public T findOne(int id) {
		//不能写T.class
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	//查询所有
	@SuppressWarnings("all")
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}
}
