package cn.cuibusi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.User;
import cn.cuibusi.entity.Visit;
import cn.cuibusi.service.CustomerService;
import cn.cuibusi.service.UserService;
import cn.cuibusi.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {
	
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String toAddPage() {
		//查询所有客户
		List<Customer> listCustomer = customerService.findAll();
		List<User> listUser = userService.findAll();
		//查询所有用户
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		return "toAddPage";
	}
	
	//添加拜访
	public String addVisit(){
		visitService.addVisit(visit);
		return "addVisit";
	}
	
	//拜访列表方法
	public String list() {
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//到条件查询页面
	public String toSelectPage() {
		List<Customer> listcustomer = customerService.findAll();
		List<User> listuser = userService.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listuser", listuser);
		request.setAttribute("listcustomer", listcustomer);
		return "toSelectPage";
	}
	
	//条件查询
	public String moreCondition() {
		List<Visit> list = visitService.moreCondition(visit);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}

}
