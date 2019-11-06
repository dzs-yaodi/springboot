package com.xw.sts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @ClassName: UploadFileUtil
 * @Description:TODO()
 * @date: 2018年11月23日 下午1:44:23
 */
public class FileUtils {

	public static List<String> uploadFile(HttpServletRequest request,String path) {
		List<String> urls = new ArrayList<String>();
		// Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/pic/";
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = multipartRequest.getFile(iterator.next());
				String picName = UUID.randomUUID().toString() + ".png";
				String url = saveFile(file, path,picName,request);
				urls.add(url);
				System.out.println(url);

			}
		}
		return urls;
	}
	
	/**   
	 * @Title: saveFile   
	 * @Description: TODO 保存文件到磁盘并返回路径
	 * @param: @param file
	 * @param: @param path
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String saveFile(MultipartFile file,String path,String picName,HttpServletRequest request){

		File fileFolder = new File(path);
		if (!fileFolder.exists()) {// 如果文件夹不存在
			fileFolder.mkdir();// 创建文件夹
		}
		path = path + picName;
		System.out.println("====保存图片的路径===" + path);
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//这里可以修改为只返回文件名称，下载的时候直接传文件路径名称即可，显示的时候自动添加域名即可
		String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/file/";//存储路径     
		return returnUrl + picName;
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> uploadOrdinartyFile(HttpServletRequest request,String path) {
		List<String> urls = new ArrayList<String>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = multipartRequest.getFile(iterator.next());
				System.out.println(file.getOriginalFilename());
				String picName = file.getOriginalFilename();
				String url = saveFile(file, path,picName,request);
				urls.add(url);
				System.out.println(url);

			}
		}
		return urls;
	}
}
