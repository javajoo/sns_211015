package com.sns.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private PermissionInterceptor interceptor;
	
	
	/*
	 * 웹의 이미지 주소와 실제 파일 경로를 매핑해주는 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		// http://localhost/images/kimje205_165434132/sun.png
		.addResourceHandler("/images/**") 	// ** 모든 주소
		//.addResourceLocations("file:///C:\\김은주java\\6_spring_project\\sns\\workspace\\images/"); 
		// 아래처럼 해놓으면 FileManagerService클래스만 바꿔주면 된다.
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); 
		//학원 경로
		//D:\김은주\6_spring-project\sns\workspace\images/
		//집 경로
		//C:\김은주java\6_spring_project\sns\workspace\images/
		//file:/// 앞에 /// 3개로 해줘야 한다
		// 경로끝에/ 붙었는지랑 @Configuration 제대로 되었는지 확인하기

	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry
		.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**","/error","/user/sign_out");
	}
}























	