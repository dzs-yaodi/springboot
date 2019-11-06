package com.xw.sts.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xw.sts.bean.Book;


@Repository
public interface BookRepos extends JpaRepository<Book, Long>{

	Book findByNameAndAuthor(String name,String author);
	
	Book findById(int id);
	
	Book findByName(String name);
	
}
