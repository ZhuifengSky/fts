package com.file.service;

import java.util.List;

import com.file.model.User;

public interface IUserService {

	public List<User> searchUsers(User queryBean);
}
