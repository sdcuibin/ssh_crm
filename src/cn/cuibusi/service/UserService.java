package cn.cuibusi.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuibusi.dao.UserDao;
import cn.cuibusi.entity.User;

@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(User user) {
		
		return userDao.userLogin(user);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	
}
