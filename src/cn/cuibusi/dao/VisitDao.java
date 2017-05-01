package cn.cuibusi.dao;

import java.util.List;

import cn.cuibusi.entity.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

	List<Visit> moreCondition(Visit visit);

}
