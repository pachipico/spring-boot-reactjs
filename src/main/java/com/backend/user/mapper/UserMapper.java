package com.backend.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.user.domain.User;

@Mapper
public interface UserMapper {
	int register(User user);
	User login(String email, String password);
	User findByEmail(String email);
	int isEmailAvailable(String email);
	int isNickNameAvailable(String nickName);
	User findByName(String name);
	List<User> findByNickName(String nickName);
	List<User> findByLocation(String siName);
	List<User> findAll();
	User findLastSaved();
	int updateUserNickName(User user);
	int updateUserAddress(User user);
	int updateUserProfileImg(User user);
	int updateUserPassword(User user);
	int updateUserLastLoggedIn(String email);
	int deleteUser(String email);
}
