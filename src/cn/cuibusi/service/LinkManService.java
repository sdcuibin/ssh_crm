package cn.cuibusi.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuibusi.dao.LinkManDao;
import cn.cuibusi.entity.LinkMan;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void addLinkMan(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	public List<LinkMan> listLinkMan() {
		return linkManDao.list();
	}

	public LinkMan findOne(int linkid) {
		
		return linkManDao.findOne(linkid);
	}

	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.updateLinkMan(linkMan);
	}

	public void delete(LinkMan linkMan) {
		// TODO Auto-generated method stub
		linkManDao.delete(linkMan);
	}
	
	public List<LinkMan> findmoreCondition(LinkMan linkMan) {
		
		return linkManDao.findmoreCondition(linkMan);
	}
	
}
