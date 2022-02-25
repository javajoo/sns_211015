package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  // 스프링 bean
public class FileManagerService {
	// CDN 서버 (이미지, CSS, JS): 다른 서버에 분리
	// 한번 설정하면 바꿀 수 없다(상수로 설정)
	// 워크스페이스안에다가 새로운 폴더 만들어서 저장해준다.(깃까지 들어가면 용량이 커서 복잡)
	// 경로 마지막에 / 넣어준다!!!!
	public final static String FILE_UPLOAD_PATH = "C:\\김은주java\\6_spring_project\\sns\\workspace\\images/";
	//D:\\김은주\\6_spring-project\\sns\\workspace\\images/
	//C:\김은주java\6_spring_project\sns\workspace\images/
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 경로 예: kimje205_165434132/sun.png (아이디 + 시간 + 이미지)
		// 파일명이 겹치지 않게 하기 위해 현재시간을 경로에 붙여준다.
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 디렉토리 만들기
		File directory = new File(filePath);
		if (directory.mkdir() == false) { // makedirectory의 약자
			return null; // 디렉토리 생성 시 실패하면 null 리턴
		}
	
		// 파일 업로드: byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes(); // 예외처리 해줘야 한다
			Path path = Paths.get(filePath + file.getOriginalFilename()); // getOriginalFilename()는 INPUT에서 올린 파일명(한글 X)
			Files.write(path, bytes);
			
			// 이미지 url을 리턴 (WebMvcConfig에서 매핑한 이미지 path)
			// 예)http://localhost/images/kimje205_165434132/sun.png
			return "/images/" + directoryName + file.getOriginalFilename(); // 성공
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null; // 실패
		
	}
	
}
