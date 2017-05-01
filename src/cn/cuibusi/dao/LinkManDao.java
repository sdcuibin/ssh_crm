package cn.cuibusi.dao;

import java.util.List;

import cn.cuibusi.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list();

	LinkMan findOne(int linkid);

	void updateLinkMan(LinkMan linkMan);

	void delete(LinkMan linkMan);

	List<LinkMan> findmoreCondition(LinkMan linkMan);
	
}
