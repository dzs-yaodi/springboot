package com.xw.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @ClassName:  UploadFileConfig   
 * @Description:TODO()  访问绝对路径下的静态资源
 * @date:   2018年12月4日 下午3:02:34   
 */
@Configuration
public class UploadFileConfig {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        //文件最大
        
//        factory.setMaxRequestSize(");
        // 设置总上传数据总大小
//        factory.setMaxFileSize("1000MB");
        return factory.createMultipartConfig();
    }

}
