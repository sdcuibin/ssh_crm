package cn.cuibusi.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer cid;
	private String custName;
	//private String custLevel;
	private String custSource;
	private String custPhone;
	private String custMobile;
	
	private Dict dictCustLevel;
	public Dict getDictCustLevel() {
		return dictCustLevel;
	}
	public void setDictCustLevel(Dict dictCustLevel) {
		this.dictCustLevel = dictCustLevel;
	}
	
	//表示客户所有的联系人
	private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
	//一个客户里面所有的拜访记录
	private Set<Visit> setCusVisit = new HashSet<Visit>();
	

	public Set<Visit> getSetCusVisit() {
		return setCusVisit;
	}
	public void setSetCusVisit(Set<Visit> setCusVisit) {
		this.setCusVisit = setCusVisit;
	}
	public Set<LinkMan> getSetLinkMan() {
		return setLinkMan;
	}
	public void setSetLinkMan(Set<LinkMan> setLinkMan) {
		this.setLinkMan = setLinkMan;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	
}
