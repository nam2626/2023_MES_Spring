package com.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KaKaoController {
	private final String REST_API_KEY = "1fd9ab06ba65109ec82e613a3c3752e1";
	private final String REDIRECT_URI = "http://localhost:9999/kakao/callback";

	@RequestMapping("/kakao/login")
	public ModelAndView kakaoLoginView(ModelAndView view) {
		String apiURL = "https://kauth.kakao.com/oauth/authorize?response_type=code&" + "client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI;

		view.addObject("apiURL", apiURL);
		view.setViewName("kakao_login");

		return view;
	}

	@RequestMapping("/kakao/callback")
	public ModelAndView kakaoCallBack(HttpSession session, ModelAndView view, String code)
			throws UnsupportedEncodingException, JSONException {

		String apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code" 
				+ "&client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI
				+ "&code=" + code;

		String res = requestKaKaoServer(apiURL, null);

		if (res != null && !res.equals("")) {
			JSONObject json = new JSONObject(res);
			session.setAttribute("user", res);
			session.setAttribute("accessToken", json.getString("access_token"));
			session.setAttribute("refreshToken", json.getString("refresh_token"));
		} else {
			view.addObject("res", "로그인 실패");
		}
		view.setViewName("kakao_login_result");

		return view;
	}

	private String requestKaKaoServer(String apiURL, String header) {
		StringBuilder res = new StringBuilder();
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
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









