package cn.cuibusi.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuibusi.dao.VisitDao;
import cn.cuibusi.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	public void addVisit(Visit visit) {
		visitDao.add(visit);
	}
	public List<Visit> findAll() {
		return visitDao.findAll();
	}
	public List<Visit> moreCondition(Visit visit) {
		return visitDao.moreCondition(visit);
	}
	
}
