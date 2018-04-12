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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ffuentese.model.Brand;
import com.ffuentese.model.Device;
import com.ffuentese.model.Type;
import com.ffuentese.repository.BrandRepository;
import com.ffuentese.repository.DeviceRepository;
import com.ffuentese.repository.ModelRepository;
import com.ffuentese.repository.TypeRepository;

@Controller
public class ModelController {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired 
	private DeviceRepository deviceRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired TypeRepository typeRepository;
	
	@GetMapping("/models")
	public ModelAndView modelShow(ModelAndView mav, Model model) {
		model.addAttribute("model", new com.ffuentese.model.Model());
		Iterable<com.ffuentese.model.Model> lmod = modelRepository.findAll();
		model.addAttribute("lmod", lmod);
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);
		return mav;
	}
	
	@PostMapping("/models")
	public ModelAndView modelSubmit(Model model, @Valid @ModelAttribute com.ffuentese.model.Model mdl, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("models");
			return mav;
		}
		modelRepository.save(mdl);
		ModelAndView mav = new ModelAndView("redirect:/models");
		model.addAttribute("model", new com.ffuentese.model.Model());
		Iterable<com.ffuentese.model.Model> lmod = modelRepository.findAll();
		model.addAttribute("lmod", lmod);	
		Iterable<Brand> lbra = brandRepository.findAll();
		model.addAttribute("lbra", lbra);
		Iterable<Type> ltype = typeRepository.findAll();
		model.addAttribute("ltype", ltype);
		return mav;
	}
	
	@GetMapping("/models/{id}")
	public ModelAndView deptDevices(Model model, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findAllByModelId(id);
		Optional<com.ffuentese.model.Model> mdl = modelRepository.findById(id);
		model.addAttribute("ldev", ldev);
		model.addAttribute("model", mdl);
		ModelAndView mav = new ModelAndView("model_device_list");
		return mav;
	}
	
	@PostMapping("/ajax/modQtyModels")
	public @ResponseBody String modDevices(Model model, @RequestParam(value = "modelId", required = true) Long modelId,  @RequestParam(value = "qty", required = true) int qty) {
		Optional<com.ffuentese.model.Model> mdl = modelRepository.findById(modelId);
		if(mdl.isPresent()) {
			mdl.get().setQty(qty);
			modelRepository.save(mdl.get());
			return "{\"success\": true}";
		}
		return "{\"success\": false}";
	}
	
	@DeleteMapping("/models")
	public  ModelAndView employeeDelete(Model model, @ModelAttribute com.ffuentese.model.Model mdl) {
		modelRepository.delete(mdl);
		ModelAndView mav = new ModelAndView("redirect:/models");
		model.addAttribute("model", new com.ffuentese.model.Model());
		Iterable<com.ffuentese.model.Model> lmod  = modelRepository.findAll();
		model.addAttribute("lmod", lmod);
		return mav;
	}
	
}
