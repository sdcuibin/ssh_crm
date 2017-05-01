package cn.cuibusi.dao;

import java.util.List;

import cn.cuibusi.entity.User;

public interface UserDao {

	User userLogin(User user);

	List<User> findAll();

}
