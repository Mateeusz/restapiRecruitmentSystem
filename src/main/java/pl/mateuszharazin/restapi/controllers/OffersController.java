package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.model.User;
import pl.mateuszharazin.restapi.repository.OfferRepository;
import pl.mateuszharazin.restapi.service.OfferService;

import javax.validation.Valid;

@Controller
public class OffersController {

    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/home/offers", method = RequestMethod.GET)
    public ModelAndView showOffers() {

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("offer", offerRepository.findById(1));
//        modelAndView.addObject("offer", offerRepository.findAll().get(1));
        modelAndView.addObject("offer", offerService.listOffer().get(1));
        modelAndView.setViewName("offers");

        return modelAndView;
    }

    @RequestMapping(value = "admin/offers/add", method = RequestMethod.GET)
    public ModelAndView offer() {
        ModelAndView modelAndView = new ModelAndView();
        Offer offer = new Offer();
        modelAndView.addObject("offer", offer);
        modelAndView.setViewName("offerForm"); // resources/template/offerForm.html
        return modelAndView;
    }

    @RequestMapping(value = "admin/offers/add", method = RequestMethod.POST)
    public ModelAndView addOffer(@Valid Offer offer, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
//        else if(userService.isUserAlreadyPresent(user)){
//            modelAndView.addObject("successMessage", "user already exists!");
//        }
        // we will save the user if, no binding errors
        else {
            //save
            offerService.saveOffer(offer);
            modelAndView.addObject("successMessage", "Offer has been added successfully!");
            System.out.println("~Mat: Success! ---> " + offer.toString());
        }
        modelAndView.addObject("offer", new Offer());
        modelAndView.setViewName("offerForm"); // resources/template/offerForm.html

        return modelAndView;
    }

}
