package com.xw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: UploadFilePathConfig
 * @Description:TODO() 访问绝对路径下的静态资源
 * @date: 2018年12月4日 下午3:03:26
 */
@Configuration
public class UploadFilePathConfig extends WebMvcConfigurerAdapter {
	@Value("${file.staticAccessPath}")
	private String staticAccessPath;
	@Value("${file.uploadFolder}")
	private String uploadFolder;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
	}

}
