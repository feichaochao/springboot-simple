package com.heimao.wuye.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heimao.wuye.entity.UserEntity;
import com.heimao.wuye.result.ErrorInfoInterface;
import com.heimao.wuye.result.GlobalErrorInfoException;
import com.heimao.wuye.result.ResultBody;
import com.heimao.wuye.result.exception.UserErrorInfoEnum;
import com.heimao.wuye.service.UserService;


/**
 * 
 * @author wuyeheimao
 * @time   2017年9月27日 下午1:08:29
 */
@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger( UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUsers", method=RequestMethod.GET)
	public ResultBody<List<UserEntity>> getUsers() {
		List<UserEntity> users=userService.getAll();
		return new ResultBody<>(users);
	}
	
    @RequestMapping(value="/getUser", method=RequestMethod.GET)
    public ResultBody<UserEntity> getUser(Long id) {
    	if(id == null){
    		throw new GlobalErrorInfoException(UserErrorInfoEnum.PARAMS_NO_COMPLETE);
    	}
    	UserEntity user=userService.find(id);
        return new ResultBody<>(user);
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResultBody<Long> save(UserEntity user) {
    	logger.info("add");
    	userService.insert(user);
    	return new ResultBody<>(user.getId());
    }
    
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ResultBody<Long> update(UserEntity user) {
    	userService.update(user);
    	return new ResultBody<>(user.getId());
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResultBody<Long> delete(@PathVariable("id") Long id) {
    	userService.delete(id);
    	return new ResultBody<>(id);
    }
    
    
}