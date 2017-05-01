package cn.cuibusi.action;

import java.awt.geom.FlatteningPathIterator;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cuibusi.entity.Customer;
import cn.cuibusi.entity.LinkMan;
import cn.cuibusi.service.CustomerService;
import cn.cuibusi.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//注入客户端service对象
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 需要上传文件(流)
	 * 上传的文件名称
	 * @return
	 */
	private File upload;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String addLinkMan() throws IOException {
		if(upload != null){
			//在服务器文件夹里面创建文件
			File serverFile = new File("F:\\sshimg"+"/"+uploadFileName);
			//把文件上传复制到服务器里面
			FileUtils.copyFile(upload, serverFile);
		}
		/**
		 * cid不能直接封装
		 */
		linkManService.addLinkMan(linkMan);
		return "addLinkMan";
	}

	//到新增联系人页面
	public String toAddPage() {
		//查询出所有的客户,把所有客户传递到页面中进行显示
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}

	//联系人列表方法
	public String list() {
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//到修改联系人页面
	public String showLinkMan() {
		int linkid = linkMan.getLinkid();
		LinkMan link = linkManService.findOne(linkid);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Customer> listCustomer = customerService.findAll();
		request.setAttribute("listCustomer",listCustomer);
		request.setAttribute("linkman",link);
		return "showLinkMan";
	}
	
	//修改联系人方法
	public String updateLinkMan() {
		linkManService.updateLinkMan(linkMan);
		return "updateLinkMan";
	}
	
	//删除联系人方法
	public String delete() {
		
		linkManService.delete(linkMan);
		return "delete";
	}
	
	//到联系人查找页面
	public String toSelectPage() {
		
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}
	
	//多条件查询
	public String moreCondition() {
		List<LinkMan> list = linkManService.findmoreCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
}
