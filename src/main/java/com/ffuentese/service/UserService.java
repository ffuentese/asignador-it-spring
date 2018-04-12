package com.ffuentese.service;

import com.ffuentese.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
