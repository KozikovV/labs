package com.nixsolutions.api;

import java.util.List;

import com.nixsolutions.domain.User;

public interface UserApi {

	List<User> findAll();

	void create(User user);

	void update(User user);

	User findByLogin(String login);

	User findByEmail(String login);

	void remove(Long id);

}
