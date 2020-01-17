package com.cxytiandi.sjdbc.service;

import java.util.List;


import com.cxytiandi.sjdbc.po.User;
import com.cxytiandi.sjdbc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> list() {
		// 强制路由主库
		//HintManager.getInstance().setMasterRouteOnly();
		return userRepository.list();
	}

	public Long add(User user) {
		return userRepository.addUser(user);
	}

}
