package com.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
	
	private FileMapper mapper;
	
	public MainController(FileMapper mapper) {
		this.mapper = mapper;
	}

	@RequestMapping("/fileUpload.do")
	public String fileUpload(@RequestParam(value = "file") MultipartFile[] fileload, HttpServletRequest request) {
		List<FileDTO> list = null;
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
				//파일 정보를 DB에 저장
				mapper.insertFile(file.getAbsolutePath());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//전체 파일 정보 읽어오는 작업
		list = mapper.selectAllFile();
		request.setAttribute("list", list);
		return "result";
	}
	
	@RequestMapping("/fileAjaxUpload.do")
	public ResponseEntity<String> fileAjaxUpload(@RequestParam(value = "file") MultipartFile[] fileload) {
		ArrayList<String> list = new ArrayList<String>();
		// 파일 업로드할 경로 설정
		String root = "c:\\fileupload\\";
		// 현재 날짜 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		// 저장한 파일경로
		String fileResult = "";
		for (int i = 0; i < fileload.length; i++) {
			System.out.println(fileload[i].getOriginalFilename());
			if (fileload[i].getSize() == 0)
				continue;
			// 서버에 파일을 저장할때 파일명을 날짜시간으로 변경
			// DB에 저장할 때는 원본파일명과 변경된 파일명 모두 저장
			// 원본 파일명 뽑음
			String originFileName = fileload[i].getOriginalFilename();
			// 저장할 파일명
			String fileName = date + "_" + i + originFileName.substring(originFileName.lastIndexOf('.'));
			System.out.println("저장할 파일명 : " + fileName);

			try {
				// 실제 파일이 업로드 되는 부분
				File file = new File(root + fileName);
				fileload[i].transferTo(file);
				//DB에 저장 후, 파일 다운로드 시키는 경로를 완성해서 list에 추가
				list.add(file.getAbsolutePath());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@RequestMapping("/ajax")
	public String main() {
		return "ajax_upload.html";
	}
	
	@RequestMapping("/filedown")
	public void fileDown(int fno, HttpServletResponse response) {
		FileDTO dto = mapper.selectFile(fno);
		File file = new File(dto.getFpath());
		
		response.setHeader("Content-Disposition", "attachement;fileName="+file.getName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		try(BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis = new FileInputStream(file);){
			
			byte[] buffer = new byte[1024*1024];
			
			while(true) {
				int count = fis.read(buffer);
				if(count == -1) break;
				bos.write(buffer,0,count);
				bos.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}











