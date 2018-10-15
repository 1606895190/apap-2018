package com.apap.tutorial4.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

/**
 * DealerController
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String view(long dealerId, Model model) {
		try {
			String alamat = dealerService.getDealerDetailById(dealerId).get().getAlamat(),
					noTelp = dealerService.getDealerDetailById(dealerId).get().getNoTelp();
			Long id = dealerService.getDealerDetailById(dealerId).get().getId();
			List carList = dealerService.getDealerDetailById(dealerId).get().getListCar();
			//Sort harga descending
			Collections.sort(carList, new Comparator<CarModel>() {
				  @Override
				  public int compare(CarModel u1, CarModel u2) {
				    return u2.getPrice().compareTo(u1.getPrice());
				  }
				});
			model.addAttribute("carList", carList);
			model.addAttribute("dealerId", id.toString());
			model.addAttribute("alamat", alamat);
			model.addAttribute("noTelp", noTelp);
			return "view-dealer";
		}catch (NoSuchElementException e) {
			model.addAttribute("errorMsg", "Maaf, id tidak ditemukan.");
			return "error";
		}

	}

}
