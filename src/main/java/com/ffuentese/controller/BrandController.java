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

import com.ffuentese.model.Brand;
import com.ffuentese.model.Department;
import com.ffuentese.model.Device;
import com.ffuentese.repository.BrandRepository;
import com.ffuentese.repository.DeviceRepository;

@Controller
public class BrandController {
	@Autowired
	private BrandRepository brandRepository;
	@Autowired 
	private DeviceRepository deviceRepository;
	
	@GetMapping("/brands")
	public ModelAndView brandShow(ModelAndView mav, Model model) {
		model.addAttribute("brand", new Department());
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);
		return mav;
	}
	
	@PostMapping("/brands")
	public ModelAndView brandSubmit(Model model, @Valid @ModelAttribute Brand brand, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("brands");
			return mav;
		}
		brandRepository.save(brand);
		ModelAndView mav = new ModelAndView("redirect:/brands");
		model.addAttribute("dept", new Brand());
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);		
		return mav;
	}
	
	@GetMapping("/brands/{id}")
	public ModelAndView brandDevices(Model model, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findAllByBrandId(id);
		Optional<Brand> brand = brandRepository.findById(id);
		model.addAttribute("ldev", ldev);
		model.addAttribute("brand", brand);
		ModelAndView mav = new ModelAndView("brand_device_list");
		return mav;
	}
	
	@DeleteMapping("/brands")
	public  ModelAndView brandDelete(Model model, @ModelAttribute Brand brand) {
		brandRepository.delete(brand);
		ModelAndView mav = new ModelAndView("redirect:/brands");
		model.addAttribute("brand", new Brand());
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);
		return mav;
	}
	
}
