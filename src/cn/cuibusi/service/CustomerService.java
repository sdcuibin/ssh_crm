package cn.cuibusi.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuibusi.dao.CustomerDao;
import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.Dict;
import cn.cuibusi.entity.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void add(Customer customer) {
		customerDao.add(customer);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public Customer findOne(int cid) {
		return customerDao.findOne(cid);
	}

	public void delete(Customer c) {
		customerDao.delete(c);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}
	
	//封装分页的数据到PageBean
	public PageBean listpage(Integer currentPage) {
		PageBean pageBean = new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount = customerDao.findcount();
		pageBean.setTotalCount(totalCount);
		//每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//总页数 总记录数除以每页显示的记录数
		//判断是否能够整除
		int totalPage=0;
		if(totalCount%pageSize==0){
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		//开始位置
		int begin = (currentPage-1)*pageSize;
		pageBean.setBegin(begin);
		
		//每页纪录的list集合
		List<Customer> list = customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}

	public List<Dict> findAllDictLevel() {
		return customerDao.findAllDictLevel();
	}

	public List findCountSource() {
		return customerDao.findCountSource();
	}

	public List findCountLevel() {
		
		return customerDao.findCountLevel();
	}

	
	
}
