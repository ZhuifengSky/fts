package com.file.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.file.model.User;
import com.file.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public List<User> searchUsers(User queryBean) {
		List<User> users = new ArrayList<User>();
		if (queryBean.getUserName().equals("admin")) {
			for (int i = 0; i < 5; i++) {
				User user = new User();
				user.setUserName("用户"+i);
				user.setAge(i);
				users.add(user);
			}
		}else{
			User user = new User();
			user.setAge(999);
			user.setUserName("非法用户");
			users.add(user);
		}
		return users;
	}

}
