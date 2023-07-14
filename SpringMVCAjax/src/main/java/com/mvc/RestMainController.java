package com.mvc;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMainController {
	@RequestMapping("/main2")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/callData")
	public ResponseEntity<String> callData(String data){
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.OK;
		
		if (data.equals("true")) {
			map.put("result", "Exception이 발생하지 않았습니다");
		}else {
			map.put("result", "Exception이 발생 했습니다.");
			map.put("errorCode", 777);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity(map,status);
	}
}











