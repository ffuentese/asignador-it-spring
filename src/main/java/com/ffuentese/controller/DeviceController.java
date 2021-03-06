package com.ffuentese.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ffuentese.model.Brand;
import com.ffuentese.model.Device;
import com.ffuentese.model.Employee;
import com.ffuentese.model.Type;
import com.ffuentese.repository.BrandRepository;
import com.ffuentese.repository.DeviceRepository;
import com.ffuentese.repository.EmployeeRepository;
import com.ffuentese.repository.ModelRepository;
import com.ffuentese.repository.TypeRepository;

@Controller
public class DeviceController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private DeviceRepository deviceRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private TypeRepository typeRepository;
	

	@GetMapping("/devices")
	public ModelAndView deviceForm(ModelAndView mav, Model model) {
		model.addAttribute("device", new Device());
		Iterable<Device> listg = deviceRepository.findAll();
		model.addAttribute("listg", listg);
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);
		Iterable<Employee> lemp = employeeRepository.findAll();
		model.addAttribute("lemp", lemp);
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);
		Iterable<com.ffuentese.model.Model> lmod = new ArrayList<com.ffuentese.model.Model>();
		model.addAttribute("lmod", lmod);
		return mav;
	}
	
	@GetMapping("/ajax/dropdown_model") 
	public @ResponseBody Iterable<com.ffuentese.model.Model> showDropdownModels(Model model, @RequestParam(value = "typeId", required = true) Long typeId,  @RequestParam(value = "brandId", required = true) Long brandId) {
		Iterable<com.ffuentese.model.Model> lmod = modelRepository.findAllByBrandId(typeId, brandId);
		model.addAttribute("lmod", lmod);
		return lmod;
		
	}

	@GetMapping("/ajax/dropdown_brand") 
	public @ResponseBody Iterable<Brand> showDropdownBrands(Model model, @RequestParam(value = "typeId", required = true) Long typeId) {
		Iterable<Brand> lbra = brandRepository.findAllByTypeId(typeId);
		model.addAttribute("lbra", lbra);
		return lbra;
		
	}
	
	@PostMapping("/devices")
	public ModelAndView deviceSubmit(@Valid @ModelAttribute Device device, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("redirect:/devices");
		if(bindingResult.hasErrors()) {
			return mav;
		}
		if (device.getEmployee() == null) {
			return mav;
		} else {
			if(device.getModel().getQty() > 0) {
			deviceRepository.save(device);
			com.ffuentese.model.Model modeld = device.getModel();
			modeld.setQty(modeld.getQty() - 1);
			modelRepository.save(modeld);
			}
			return mav;
		}
	}

	@PostMapping("/modify")
	public ModelAndView deviceModifyOrDelete(@Valid @ModelAttribute Device device, Model model, HttpServletRequest request, BindingResult bindingResult) {

		String borrar = request.getParameter("borrar");
		String modificar = request.getParameter("modificar");
		
		if (borrar != null ) { 
		deviceRepository.delete(device);
		com.ffuentese.model.Model modeld = device.getModel();
		modeld.setQty(modeld.getQty() + 1);
		modelRepository.save(modeld);
		ModelAndView mav = new ModelAndView("redirect:/devices");
		return mav;
		} else if (modificar != null) {
			// model.addAttribute("device", device);
			Iterable<Employee> lemp = employeeRepository.findAll();
			model.addAttribute("lemp", lemp);
			Iterable<Brand> lbra = brandRepository.findAll();
			model.addAttribute("lbra", lbra);
			System.out.println("Brand num: " + device.getBrand().getId());
			Iterable<com.ffuentese.model.Model> lmod = modelRepository.findAllByBrandId(device.getType().getId(), device.getBrand().getId());
			model.addAttribute("lmod", lmod);
			ModelAndView mav = new ModelAndView("modify");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("redirect:/devices");
			return mav;
		}
	}
	
//	@GetMapping("/modify")
//	public ModelAndView showModify(ModelAndView mav, Model model, @ModelAttribute Device device) {
//		Iterable<Employee> lemp = employeeRepository.findAll();
//		model.addAttribute("lemp", lemp);
////		model.addAttribute("device", device);
//		return mav;
//	
//	}

	@PutMapping("/devices")
	public ModelAndView updateDelete(@Valid @ModelAttribute Device device, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("devices");
			return mav;
		}
		deviceRepository.save(device);
		ModelAndView mav = new ModelAndView("redirect:/devices");
		return mav;
	}

}
