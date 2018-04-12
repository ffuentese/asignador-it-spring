package com.ffuentese.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ffuentese.model.Department;
import com.ffuentese.model.Device;
import com.ffuentese.repository.DepartmentRepository;
import com.ffuentese.repository.DeviceRepository;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired 
	private DeviceRepository deviceRepository;
	
	@GetMapping("/departments")
	public ModelAndView deptShow(ModelAndView mav, Model model) {
		model.addAttribute("dept", new Department());
		Iterable<Department> ldep = departmentRepository.findAll();
		model.addAttribute("ldep", ldep);
		return mav;
	}
	
	@PostMapping("/departments")
	public ModelAndView deptSubmit(Model model, @Valid @ModelAttribute("dept") Department dept, BindingResult bindingResult) {
		Iterable<Department> ldep = departmentRepository.findAll();
		model.addAttribute("ldep", ldep);		

		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("departments");
			model.addAttribute("dept", dept);
			return mav;
		} else {
		ModelAndView mav = new ModelAndView("redirect:/departments");
		departmentRepository.save(dept);
		model.addAttribute("dept", new Department());
		return mav;
		}
	}
	
	@GetMapping("/departments/{id}")
	public ModelAndView deptDevices(Model model, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findDevicesByDepartmentId(id);
		Optional<Department> dept = departmentRepository.findById(id);
		model.addAttribute("ldev", ldev);
		model.addAttribute("dept", dept);
		ModelAndView mav = new ModelAndView("dept_device_list");
		return mav;
	}
	
	@DeleteMapping("/departments")
	public  ModelAndView employeeDelete(ModelAndView mav, Model model, @ModelAttribute Department dept) {
		departmentRepository.delete(dept);
		model.addAttribute("dept", new Department());
		Iterable<Department> ldep = departmentRepository.findAll();
		model.addAttribute("ldep", ldep);
		
		return mav;
	}
	
}
