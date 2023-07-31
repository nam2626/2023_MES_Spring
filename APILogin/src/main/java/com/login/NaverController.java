package com.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NaverController {
	private final String CLIENT_ID = "KHkektMREM4uRRubtXSl";
	private final String CLIENT_SECRET_ID = "trD4iQ7zjS";
	
	@RequestMapping("/")
	public ModelAndView main(HttpSession session, ModelAndView view) throws UnsupportedEncodingException {
		String clientId = CLIENT_ID;
	    String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    view.addObject("apiURL", apiURL);
	    session.setAttribute("state", state);
	    view.setViewName("naver_login");
		return view;
	}
	
}










