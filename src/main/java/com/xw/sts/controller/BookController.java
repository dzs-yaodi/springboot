package com.xw.sts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xw.sts.bean.Book;
import com.xw.sts.bean.BookCollection;
import com.xw.sts.dto.BookColletcDto;
import com.xw.sts.dto.Result;
import com.xw.sts.repos.BookCollectionRepos;
import com.xw.sts.repos.BookRepos;

@RestController
@RequestMapping("/book")
public class BookController {

	private String Local_Path = "D:\\test/";
	
	@Autowired
	private BookRepos bookRepos;
	
	@Autowired
	private BookCollectionRepos collectRepos;
	
	/**
	 * 上传
	 * @param name
	 * @param author
	 * @param mClass
	 * @param status
	 * @param role
	 * @param costart
	 * @param plot
	 * @param synopsis
	 * @param pushUser
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/pushBook")
	public Result pushBook(String name,String author,String mClass,String status,String role,String costart,
			String plot,String synopsis,String pushUser,MultipartFile file,HttpServletRequest request) {
		
		Book book = bookRepos.findByNameAndAuthor(name, author);
		if (book != null) {
			return Result.error("该书已添加");
		}
		
		//把封面图存到本地磁盘
		String filePathString= FileUtils.saveFile(file, Local_Path, name+".jpg",request);
		
		
		book = new Book();
		book.setAuthor(author);
		book.setCostart(costart);
		book.setImagepath(filePathString);
		book.setmClass(mClass);
		book.setName(name);
		book.setPlot(plot);
		book.setPushuser(Integer.parseInt(pushUser));
		book.setRole(role);
		book.setStatus(status);
		book.setSynopsis(synopsis);
		
		bookRepos.save(book);
		return Result.ok("上传成功");
	}
	
	/**
	 * 查找所有书籍
	 * @return
	 */
	@RequestMapping("/findAll")
	public Result findAllBook() {
		
		List<Book> bList = bookRepos.findAll();
		return Result.ok("success").put("data", bList);
	}
	
	/**
	 * 根据id查询书籍
	 * @param id
	 * @return
	 */
	public Result findById(int id) {
		Book book = bookRepos.findById(id);
		if (book == null) {
			return Result.error("暂无该书信息");
		}
		
		return Result.ok("success").put("book", book);
	}
	
	/**
	 * 根据书名查询书籍
	 * @param name
	 * @return
	 */
	public Result findByName(String name) {
		Book book = bookRepos.findByName(name);
		
		if (book == null) {
			return Result.error("暂无该书信息");
		}
		return Result.ok("success").put("book", book);
		
	}
	
	
	/**
	 * 添加收藏
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/collect/add")
	public Result addCollect(@RequestParam int userId,@RequestParam int bookId) {
		
		try {
			BookCollection collection = new BookCollection();
			collection.setUserId(userId);
			collection.setBookId(bookId);
			
			collectRepos.save(collection);
			Result result = Result.ok("收藏成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("不能重复收藏");
		}
		
		
	}
	
	/**
	 * 查询收藏
	 * @param userId
	 * @return
	 */
	@RequestMapping("/collect/getAll")
	public Result getAllCollection(@RequestParam int userId) {
		
		List<BookColletcDto> list = collectRepos.findCollection(userId);
		Result result = Result.ok("获取成功");
		result.put("data", list);
		return result;
	}
	
}
