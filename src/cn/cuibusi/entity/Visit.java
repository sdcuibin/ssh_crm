package cn.cuibusi.entity;

public class Visit {
	private Integer vid;
	private String vaddress;
	private String vconcent;
	private User user;
	private Customer customer;
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getVconcent() {
		return vconcent;
	}
	public void setVconcent(String vconcent) {
		this.vconcent = vconcent;
	}
	
}
