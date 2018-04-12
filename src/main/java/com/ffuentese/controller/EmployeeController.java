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

import com.ffuentese.model.Branch;
import com.ffuentese.model.Department;
import com.ffuentese.model.Device;
import com.ffuentese.model.Employee;
import com.ffuentese.repository.BranchRepository;
import com.ffuentese.repository.DepartmentRepository;
import com.ffuentese.repository.DeviceRepository;
import com.ffuentese.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private EmployeeRepository employeeRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private BranchRepository branchRepository;



	@GetMapping("/employees")
	public ModelAndView employeeForm(ModelAndView mav, Model model) {
		model.addAttribute("employee", new Employee());
		Iterable<Employee> listg = employeeRepository.findAll();
		model.addAttribute("listg", listg);
		Iterable<Department> listd = departmentRepository.findAll();
		model.addAttribute("listd", listd);
		Iterable<Branch> lloc = branchRepository.findAll();
		model.addAttribute("lloc", lloc);
		return mav;
	}

	@PostMapping("/employees")
	public   ModelAndView employeeSubmit(Model model, @Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		Iterable<Employee> listg = employeeRepository.findAll();
		model.addAttribute("listg", listg);
		Iterable<Department> listd = departmentRepository.findAll();
		model.addAttribute("listd", listd);
		Iterable<Branch> lloc = branchRepository.findAll();
		model.addAttribute("lloc", lloc);
		if(bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			ModelAndView mav = new ModelAndView("employees");
			return mav;
		}
		employeeRepository.save(employee);
		ModelAndView mav = new ModelAndView("redirect:/employees");
		return mav;
	}
	@DeleteMapping("/employees")
	public  ModelAndView employeeDelete(@ModelAttribute Employee employee) {

		employeeRepository.delete(employee);
		ModelAndView mav = new ModelAndView("redirect:/employees");
		return mav;
	}
	
	@GetMapping("/employees/{id}")
	public ModelAndView employeeDevices(Model model, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findAllByEmployeeId(id);
		model.addAttribute("ldev", ldev);
		Optional<Employee> emp = employeeRepository.findById(id);
		model.addAttribute("emp", emp);
		ModelAndView mav = new ModelAndView("emp_device_list");
		return mav;
	}
	
	
//	@GetMapping("/employees")
//	public ModelAndView getAllEmployees(ModelAndView mav,Model model) {
//		Iterable<Employee> listg = employeeRepository.findAll();
//		model.addAttribute("listg",listg);
//		return mav;
//	}

}