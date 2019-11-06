package com.xw.sts.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xw.sts.bean.BookCollection;
import com.xw.sts.dto.BookColletcDto;

@Repository
public interface BookCollectionRepos extends JpaRepository<BookCollection, Long>{


	@Query(value = "select new com.xw.sts.dto.BookColletcDto(b.id,b.userId,a.id AS bookId,a.name,a.imagepath) from Book a,BookCollection b where a.id=b.bookId and b.userId= ?1")
	List<BookColletcDto> findCollection(int userId);
}
