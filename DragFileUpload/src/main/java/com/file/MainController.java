package com.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
	@RequestMapping("/fileUpload.do")
	public String fileUpload(@RequestParam(value = "file") MultipartFile[] fileload, HttpServletRequest request) {
		// 파일 업로드할 경로 설정
		File root = new File("c:\\fileupload");
		if (!root.exists())
			root.mkdirs();
		
		// 현재 날짜 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		
		//저장한 파일 경로
		String fileResult = "";
		
		for(int i=0;i<fileload.length;i++) {
			if(fileload[i].getSize() == 0) continue;
			
			//서버에 파일을 저장할 때는 파일명 날짜시간으로 변경
			//DB에 저장할 떄는 원본파일명과 변경된 파일명 모두 저장
			//원본파일명
			String originFileName = fileload[i].getOriginalFilename();
			//저장할 파일명
			String fileName = date + "_" 
						+ i + originFileName.substring(originFileName.lastIndexOf('.'));
			System.out.println("저장할 파일명 : " + fileName);
			
			try {
				//실제 파일이 업로드 되는 부분
				File file = new File(root, fileName);
				fileload[i].transferTo(file);
				fileResult += file.getAbsolutePath() + "<br>";
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		request.setAttribute("file_path", fileResult);
		return "result";
	}
}











