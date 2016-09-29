package com.oneseven.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oneseven.models.Employee;
import com.oneseven.viewModels.Employees;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		Employees e = null;
		ModelAndView andView = null;
		try {
			e = new Employees();
			ArrayList<Employee> list = e.Instance().listEmployee();
			andView = new ModelAndView("home");
			andView.addObject("list", list);
		} catch (Exception ex) {
			andView = new ModelAndView("error");
			ex.printStackTrace();
		}

		return andView;
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String Agregar(){
		String page = "";
		try {
			page = "insert";
		} catch (Exception e) {
			page = "error";
			e.printStackTrace();
		}
		return page;
	}
	
}
