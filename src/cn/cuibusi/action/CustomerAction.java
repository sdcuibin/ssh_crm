package cn.cuibusi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.Dict;
import cn.cuibusi.entity.PageBean;
import cn.cuibusi.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	private Customer customer = new Customer();
	public Customer getCustomer() {
		return customer;
	}
	@Override
	public Customer getModel() {
		return customer;
	}
	
	/**
	 * 注入service
	 */
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//条件查询方法
	public String listcondition() {
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())){
			List<Customer> list = customerService.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list", list);
		}else{
			list();
		}
		return "listcondition";
	}
	public String toAddPage() {
		//查寻所有级别
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	
	public String add() {
		customerService.add(customer);
		return "add";
	}
	
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//分页的方法
	public String listpage() {
		//调用service方法完成封装
		PageBean pageBean = customerService.listpage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//列表的方法
	public String list(){
		List<Customer> list = customerService.findAll();
		List<Dict> listDict = customerService.findAllDictLevel();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("listDict", listDict);
		return "list";
	}
	
	public String delete() {
		//使用模型驱动获取提交的cid的值
		int cid = customer.getCid();
		//首先根据id查询，调用方法删除
		Customer c= customerService.findOne(cid);
		if(c!=null){
			customerService.delete(c);
		}
		return "delete";
	}
	
	public String showCustomer() {
		int cid = customer.getCid();
		Customer c= customerService.findOne(cid);
		List<Dict> listDict = customerService.findAllDictLevel();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("customer", c);
		request.setAttribute("listDict", listDict);
		return "showCustomer";
	}
	
	//修改的方法
	public String update() {
		customerService.update(customer);
		return "update";
	}
	
	//跳转条件查询页面
	public String toSelectCustomerPage() {
		return "toSelectCustomerPage";
	}
	
	//多条件查询方法
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	//根据客户来源进行统计
	public String countSource() {
		List list = customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	//客户级别统计
	public String countLevel() {
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
}
