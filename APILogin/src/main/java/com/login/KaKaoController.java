package com.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KaKaoController {
	private final String REST_API_KEY = "1fd9ab06ba65109ec82e613a3c3752e1";
	private final String REDIRECT_URI = "http://localhost:9999/kakao/callback";
	
	@RequestMapping("/kakao/login")
	public ModelAndView kakaoLoginView(ModelAndView view) {
		String apiURL = "https://kauth.kakao.com/oauth/authorize?response_type=code&"
				+ "client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI;
		
		view.addObject("apiURL", apiURL);
		view.setViewName("kakao_login");
		
		return view;
	}
}






