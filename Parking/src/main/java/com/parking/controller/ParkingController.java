package com.parking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.parking.api.ParkingAPI;

@Controller
public class ParkingController {
	private ParkingAPI parkingAPI;
	
	public ParkingController(ParkingAPI parkingAPI) {
		this.parkingAPI = parkingAPI;
	}

	@RequestMapping("/")
	public ModelAndView main(ModelAndView view) {
		view.setViewName("park_main");
		try {
			JSONObject json = new JSONObject(parkingAPI.test());
			view.addObject("parkList", json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return view;
	}
}
