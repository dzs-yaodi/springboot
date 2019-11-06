package com.xw.sts.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PostController {
	

	/**
	 * 保存自己搭建的tomcat本地服务器
	 * @param file
	 * @param request
	 */
	@PostMapping("post")
	public void postImage(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request) {
		if (file != null) {
			
			File targeFile = null;
			String url ="";//返回路径
			int code = 1;
			System.out.println(file);

	        String fileName=file.getOriginalFilename();//获取文件名加后缀
	        if (fileName != null && fileName.length() > 0) {
	        	String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/image/";//存储路径            
//	        	String path= "D:\\tools\\anzhuangbao\\eclipse\\apache-tomcat-9.0.27-windows-x64\\image";
	        	String path = "D:\\test";
	        	String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀            
	        	fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名 
	        	
	        	//先判断文件是否存在            
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");            
	        	String fileAdd = sdf.format(new Date());            
	        	//获取文件夹路径            
	        	File file1 =new File(path+"/"+fileAdd);             
	        	//如果文件夹不存在则创建                
	        	if(!file1 .exists()  && !file1 .isDirectory()){                       
	        		file1 .mkdir();              
	        	}            
	        	//将图片存入文件夹            
	        	targeFile = new File(file1, fileName);            
	        	try {            	
	        		//将上传的文件写到服务器上指定的文件。               
	        		file.transferTo(targeFile);           
//	        		String[] urlpStrings = returnUrl.split("8080"); 
//	        		url=urlpStrings[0] + "9527/downloads/"+fileAdd+"/"+fileName;      
	        		url = returnUrl + fileAdd + "/"+fileName;
	        		code=0;      
	        		System.out.println("图片上传成功===url=" +  url);
	        		} catch (Exception e) {                
	        			e.printStackTrace();       
	        			System.out.println("系统异常，图片上传失败");
	        		}
	        	
				}
	        
	        
			}
		
	}
	
	/**
	 * 
	 * @param file
	 * @param request
	 */
	@PostMapping("/postPic")
	public void postPic(MultipartFile file,HttpServletRequest request){
		
		String picName = new Date().getTime() + "_"+file.getOriginalFilename();
		String path = "D:\\test/";
		String filePathString= FileUtils.saveFile(file, path, picName,request);
		System.out.println("==路径=="+filePathString);
	}
	
	/**
	 * 保存springboot内置tomcat(推荐)  支持保存 单个/多文件（图片，音频，视频）/ 组合
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/pushMp4")
	public String postMP4(MultipartFile[] file,HttpServletRequest request) {
		
		String filePathString = null;
		for (int i = 0; i < file.length; i++) {
			String fileName = file[i].getOriginalFilename();
			filePathString = FileUtils.saveFile(file[i], "D:\\test/", fileName, request);
		}
	
		System.out.println("====mp4上传路径===" + filePathString);
		
		return filePathString;
	}
	
	/**
	 * 删除文件
	 * @param path
	 */
	@RequestMapping("/delet")
	public void deletPic(String path) {
		
		String[] nameString = path.split("/");
		if (nameString.length> 1) {
			
			String sPath = "D:\\test/" + nameString[nameString.length -1];
			boolean result = FileUtils.deleteFile(sPath);
			
			if (result) {
				System.out.println("操作成功");
			}else {
				System.out.println("操作失败");
			}
		}
		
	}
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getFile")
	public Object getFile(String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		if (fileName != null) {
			
			String realPathString = "D:\\test/" + fileName;
			
			File file = new File(realPathString);
			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
		 		
		 		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		 		headers.setContentDispositionFormData("attachment", fileName);

		        byte[] downLoadFromUrl = getByteByFile(file);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byteArrayOutputStream.write(downLoadFromUrl);
				return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
			}
		}
		
		return null;
	}

	private byte[] getByteByFile(File file) {
		 try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			
			fis.close();
			byte[] data = bos.toByteArray();
			bos.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
