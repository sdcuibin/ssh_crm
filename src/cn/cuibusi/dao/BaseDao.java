package cn.cuibusi.dao;

import java.util.List;

/**
 * 泛型类
 * 定义类型是T,代表任意类型
 * @author cuibusi
 * @param <T>
 */
public interface BaseDao<T> {
	void add(T t);
	void update(T t);
	void delete(T t);
	T findOne(int id);
	List<T> findAll();
}
