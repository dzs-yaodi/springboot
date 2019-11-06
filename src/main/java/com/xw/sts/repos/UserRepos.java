package com.xw.sts.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xw.sts.bean.User;

@Repository
public interface UserRepos extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username,String password);
}
