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
import com.ffuentese.model.Device;
import com.ffuentese.repository.BranchRepository;
import com.ffuentese.repository.DeviceRepository;

@Controller
public class BranchController {
	@Autowired
	private BranchRepository branchRepository;
	@Autowired 
	private DeviceRepository deviceRepository;
	
	@GetMapping("/branches")
	public ModelAndView branchShow(ModelAndView mav, Model model) {
		model.addAttribute("branchform", new Branch());
		model.addAttribute("branchtable", new Branch());
		Iterable<Branch> lloc = branchRepository.findAll();
		model.addAttribute("lloc", lloc);
		return mav;
	}
	
	@PostMapping("/branches")
	public String branchSubmit(Model model, @Valid @ModelAttribute("branchform") Branch branchform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
	//		ModelAndView mav = new ModelAndView("branches");
			model.addAttribute("branchform", branchform);
			model.addAttribute("branchtable", new Branch());
			Iterable<Branch> lloc = branchRepository.findAll();
			model.addAttribute("lloc", lloc);		
	//		return mav;
			return "branches";
		}
		branchRepository.save(branchform);
	//	ModelAndView mav = new ModelAndView("redirect:/branches");		
		model.addAttribute("branchform", new Branch());
		model.addAttribute("branchtable", new Branch());
		Iterable<Branch> lloc = branchRepository.findAll();
		model.addAttribute("lloc", lloc);		
	//	return mav;
		return "branches";
	}
	
	@GetMapping("/branches/{id}")
	public ModelAndView branchDevices(Model model, @PathVariable long id) {
		Iterable<Device> ldev = deviceRepository.findDevicesByBranchId(id);
		Optional<Branch> branch = branchRepository.findById(id);
		model.addAttribute("ldev", ldev);
		model.addAttribute("branch", branch);
		ModelAndView mav = new ModelAndView("branch_device_list");
		return mav;
	}
	
	@DeleteMapping("/branches")
	public  ModelAndView branchDelete(Model model, @ModelAttribute Branch branch) {
		branchRepository.delete(branch);
		ModelAndView mav = new ModelAndView("redirect:/branches");
		model.addAttribute("branch", new Branch());
		Iterable<Branch> lloc = branchRepository.findAll();
		model.addAttribute("lloc", lloc);
		return mav;
	}
	
}
