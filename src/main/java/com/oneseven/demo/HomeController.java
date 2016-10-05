package com.oneseven.demo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oneseven.models.Employee;
import com.oneseven.models.Position;
import com.oneseven.viewModels.Employees;
import com.oneseven.viewModels.Positions;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView view = null;
		try {
			ArrayList<Employee> list = Employees.Instance().listEmployee();
			view = new ModelAndView("home");
			view.addObject("list", list);
		} catch (Exception ex) {
			view = new ModelAndView("404");
			ex.printStackTrace();
		}

		return view;
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public ModelAndView Agregar() {
		ModelAndView view = null;
		try {
			ArrayList<Position> list = Positions.Instance().listPosition();
			view = new ModelAndView("insert");
			view.addObject("list", list);
		} catch (Exception e) {
			view = new ModelAndView("404");
			e.printStackTrace();
		}
		return view;
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public ModelAndView Agregar(@ModelAttribute("SpringWeb") Employee e, HttpServletRequest request) {
		ModelAndView view = null;
		try {
			int post = Integer.parseInt(request.getParameter("selectPosition"));
			Position p = new Position();
			p.setId(post);
			e.setCargo(p);
			Boolean var = Employees.Instance().InsertEmployee(e);
			if (var) {
				ArrayList<Employee> list = Employees.Instance().listEmployee();
				view = new ModelAndView("home");
				view.addObject("list", list);
			} else {
				view = new ModelAndView("error");
			}
		} catch (Exception ex) {
			view = new ModelAndView("404");
			ex.printStackTrace();
		}
		return view;
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public ModelAndView Eliminar(HttpServletRequest request) {
		ModelAndView view = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Boolean var = Employees.Instance().deleteEmployee(id);
			if (var) {
				ArrayList<Employee> list = Employees.Instance().listEmployee();
				view = new ModelAndView("home");
				view.addObject("list",list);
			} else {
				view = new ModelAndView("error");
			}
		} catch (Exception ex) {
			view = new ModelAndView("404");
			ex.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView Editar(HttpServletRequest request) {
		ModelAndView view = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee e = Employees.Instance().SearchEmployee(id);
			Position p = Positions.Instance().backPosition(e.getCargo().getId());
			view = new ModelAndView("edit");
			view.addObject("model", e);
			view.addObject("cargo", p);
		} catch (Exception ex) {
			view = new ModelAndView("404");
			ex.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/editar" ,method = RequestMethod.POST)
	public ModelAndView Editar(@ModelAttribute("SpringWeb") Employee e, HttpServletRequest request){
		ModelAndView view = null;
		try {
			int id = Integer.parseInt(request.getParameter("selectPosition"));
			Position p = new Position();
			p.setId(id);
			e.setCargo(p);
			Boolean var = Employees.Instance().EditEmployee(e);
			if (var) {
				ArrayList<Employee> list = Employees.Instance().listEmployee();
				view = new ModelAndView("home");
				view.addObject("list",list);
			} else {
				view = new ModelAndView("error");
			}
		} catch (Exception ex) {
			view = new ModelAndView("404");
			ex.printStackTrace();
		}
		return view;
	}

}
