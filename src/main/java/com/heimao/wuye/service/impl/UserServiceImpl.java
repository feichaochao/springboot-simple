package com.heimao.wuye.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.heimao.wuye.entity.UserEntity;
import com.heimao.wuye.mapper.UserMapper;
import com.heimao.wuye.service.UserService;

/**
 * 
 * @author wuyeheimao
 * @time   2017年9月27日 下午1:09:39
 */
@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserEntity> getAll() {
		
		return userMapper.getAll();
	}

	@Override
	public UserEntity find(Long id) {
		return userMapper.find(id);
	}

	@Override
	public void insert(UserEntity user) {
		userMapper.insert(user);
		
	}

	@Override
	public void update(UserEntity user) {
		userMapper.update(user);
		
	}

	@Override
	public void delete(Long id) {
		userMapper.delete(id);
		
	}

}
