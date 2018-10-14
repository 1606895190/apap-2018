package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
		
	}
	
	@RequestMapping("/challenge/{name}")
	public String challengePath(@PathVariable String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
		
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value = "a", required = false, defaultValue = "0") String a, 
			@RequestParam(value = "b", required = false, defaultValue = "0") String b, Model model) {
		model.addAttribute("a",a);
		model.addAttribute("b",b);
		
		try {
			int jmlM = Integer.parseInt(a);
			int jmlHm = Integer.parseInt(b);
			String hm = "hm";
			
			for (int i = jmlM; i>1; i--) {
				hm += "m";
			}
			
			String hmAwal = hm;
			
			for (int i = jmlHm; i>1; i--) {			
				hm += " " + hmAwal;
			}
			
			model.addAttribute("hm",hm);
			
		} catch (NumberFormatException e) {
			model.addAttribute("hm","hm salah ngasi angka");
		}
		
		return "generator";
		
	}

}
