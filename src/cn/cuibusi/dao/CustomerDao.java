package cn.cuibusi.dao;

import java.util.List;

import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.Dict;

public interface CustomerDao extends BaseDao<Customer> {

//	void add(Customer customer);

//	List<Customer> findAll();

//	Customer findOne(int cid);

//	void delete(Customer c);

//	void update(Customer customer);

	int findcount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);

	List<Customer> findMoreCondition(Customer customer);

	List<Dict> findAllDictLevel();

	List<Customer> findCountSource();

	List findCountLevel();

}
