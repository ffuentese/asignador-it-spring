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

import com.ffuentese.model.Device;
import com.ffuentese.model.Type;
import com.ffuentese.repository.DeviceRepository;
import com.ffuentese.repository.TypeRepository;

@Controller
public class TypeController {
	@Autowired
	private TypeRepository typeRepository;
	@Autowired 
	private DeviceRepository deviceRepository;
	
	@GetMapping("/types")
	public ModelAndView typeShow(ModelAndView mav, Model model) {
		model.addAttribute("type", new Type());
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);
		return mav;
	}
	
	@PostMapping("/types")
	public ModelAndView typeSubmit(Model model, @Valid @ModelAttribute("type") Type type, BindingResult bindingResult) {
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);		
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("types");
			model.addAttribute("type", type);
			return mav;
		}
		typeRepository.save(type);
		ModelAndView mav = new ModelAndView("redirect:/types");		
		model.addAttribute("type", new Type());

		return mav;
	}
	
	@GetMapping("/types/{id}")
	public ModelAndView typeDevices(Model model, ModelAndView mav, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findDevicesByDepartmentId(id);
		Optional<Type> type = typeRepository.findById(id);
		model.addAttribute("ldev", ldev);
		model.addAttribute("type", type);
		return mav;
	}
	
	@DeleteMapping("/types")
	public  ModelAndView typeDelete(Model model, @ModelAttribute Type type) {
		typeRepository.delete(type);
		ModelAndView mav = new ModelAndView("redirect:/types");
		model.addAttribute("type", new Type());
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);
		return mav;
	}
	
}
