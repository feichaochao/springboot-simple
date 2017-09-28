package com.heimao.wuye.service;

import java.util.List;

import com.heimao.wuye.entity.UserEntity;

/**
 * 
 * @author wuyeheimao
 * @time   2017年9月27日 下午1:09:32
 */
public interface UserService {

    List<UserEntity> getAll();
	
	UserEntity find(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);
}
