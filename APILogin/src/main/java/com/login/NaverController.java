package com.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

	@RequestMapping("/naver/callback")
	public ModelAndView naverCallBack(HttpSession session, ModelAndView view, String code, String state)
			throws UnsupportedEncodingException, JSONException {
		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback", "UTF-8");

		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + CLIENT_ID
				+ "&client_secret=" + CLIENT_SECRET_ID + "&redirect_uri=" + redirectURI + "&code=" + code + "&state="
				+ state;

		String res = requestNaverServer(apiURL, null);

		if (res != null && !res.equals("")) {
			JSONObject json = new JSONObject(res);
			session.setAttribute("user", res);
			session.setAttribute("accessTokken", json.getString("access_token"));
			session.setAttribute("refreshTokken", json.getString("refresh_token"));
		} else {
			view.addObject("res", "로그인 실패");
		}
		view.setViewName("naver_login_result");

		return view;
	}

	@RequestMapping("/naver/profile")
	public ModelAndView getProfile(ModelAndView view, HttpSession session) {
		String token = (String) session.getAttribute("accessTokken"); // 네이버 로그인 접근 토큰;
		String header = "Bearer " + token; // Bearer 다음에 공백 추가

		String apiURL = "https://openapi.naver.com/v1/nid/me";

		String result = requestNaverServer(apiURL, header);
		
		view.addObject("userInfo",result);
		view.setViewName("naver_login_result");
		return view;
	}
	
	@RequestMapping("/naver/refresh")
	public ModelAndView refreshTokken(HttpSession session, ModelAndView view) throws JSONException {
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&"
				+ "client_id=" + CLIENT_ID
				+ "&client_secret=" + CLIENT_SECRET_ID
				+ "&refresh_token=" + session.getAttribute("refreshTokken");
		String result = requestNaverServer(apiURL, null);
		if (result != null && !result.equals("")) {
			JSONObject json = new JSONObject(result);
			session.setAttribute("user", result);
			session.setAttribute("accessTokken", json.getString("access_token"));
			session.setAttribute("refreshTokken", json.getString("refresh_token"));
		} else {
			view.addObject("res", "로그인 실패");
		}
		view.setViewName("naver_login_result");
		return view;
	}
	@RequestMapping("/naver/delete")
	public ModelAndView deleteTokken(HttpSession session, ModelAndView view) throws JSONException {
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&"
				+ "client_id=" + CLIENT_ID
				+ "&client_secret=" + CLIENT_SECRET_ID
				+ "&access_token=" + session.getAttribute("accessTokken")
				+ "&service_provider=NAVER";
		String result = requestNaverServer(apiURL, null);
		System.out.println(result);	
		session.invalidate();
		view.setViewName("redirect:/");
		return view;
	}
	
	@RequestMapping("/naver/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	public String requestNaverServer(String apiURL, String header) {
		StringBuilder res = new StringBuilder();
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			if (header != null && !header.equals("")) {
				con.setRequestProperty("Authorization", header);
			}

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
			}
		} catch (Exception e) {
			// Exception 로깅
		}
		return res.toString();
	}

}
