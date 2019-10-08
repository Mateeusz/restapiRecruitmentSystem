package pl.mateuszharazin.restapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.repository.OfferRepository;
import pl.mateuszharazin.restapi.service.OfferService;

import javax.validation.Valid;
import java.util.List;

//@AllArgsConstructor
@Controller
public class OffersController {

    @Autowired
    OfferService offerService;


    private final OfferRepository offerRepository;

    OffersController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }

    @RequestMapping(value = "/home/offers", method = RequestMethod.GET)
    public ModelAndView showOffers() {

        ModelAndView modelAndView = new ModelAndView();
//        List<Offer> offerList = offerService.listOffer();
        modelAndView.addObject("offerList", offerService.listOffer());
        modelAndView.setViewName("offers");
        return modelAndView;
    }

    @RequestMapping(value = "/home/getoffer", method = RequestMethod.GET)
    public ModelAndView getOffer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("offerinput");
        return modelAndView;
    }

    @RequestMapping(value = "/home/getoffer", method = RequestMethod.POST)
    public ModelAndView getOfferById(@ModelAttribute Offer offer) {

        System.out.println("~Mat: HELLO! --> "+offer.getId());
//        int id = offer.getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(offerRepository.findAllById(offer.getId()));
        modelAndView.setViewName("offerGet");

        modelAndView.addObject("successMessage", "Oferta o wskazanym ID nie istnieje!");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/offer/{id}", method = RequestMethod.GET)
    public ModelAndView getOfferById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offer", offerRepository.findById(id));
        modelAndView.setViewName("offerGet");
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
        else {
            offerService.newOffer(offer);
            modelAndView.addObject("successMessage", "Offer has been added successfully!");
            System.out.println("~Mat: Success! ---> " + offer.toString());
        }
        modelAndView.addObject("offer", new Offer());
        modelAndView.setViewName("offerForm"); // resources/template/offerForm.html

        return modelAndView;
    }

    @RequestMapping(value = "/home/deleteoffer", method = RequestMethod.GET)
    public ModelAndView deleteOffer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteofferinput");
        return modelAndView;
    }

    @RequestMapping(value = "/home/deleteoffer", method = RequestMethod.POST)
    public ModelAndView deleteOfferById(@ModelAttribute Offer offer) {

        ModelAndView modelAndView = new ModelAndView();
        offerService.deleteOffer(offer.getId());
        modelAndView.setViewName("offers");
//        modelAndView.addObject("successMessage", "Offer has been deleted successfully!");
        return modelAndView;
    }


    @RequestMapping(value = "/home/editoffer/{id}", method = RequestMethod.GET)
    public ModelAndView updateOffer(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Offer offer = offerRepository.findAllById(id);
        modelAndView.addObject("offer", offer);
        modelAndView.setViewName("editOffer");

        return modelAndView;
    }

    @RequestMapping(value = "/home/editoffer/{id}", method = RequestMethod.POST)
    public ModelAndView putOffer(@PathVariable(value = "id") int id, @Valid @ModelAttribute Offer offer) {

        System.out.println("Mat: "+offer.toString());
        ModelAndView modelAndView = new ModelAndView();
        Offer offer1 = offerRepository.findAllById(id);
        offer1.setTitle(offer.getTitle());
        offer1.setSalary(offer.getSalary());
        offer1.setDescription(offer.getDescription());
        offer1.setRequirements(offer.getRequirements());
        offer1.setVacantNumber(offer.getVacantNumber());

        modelAndView.addObject("offer", offer);
        offerService.newOffer(offer1);

        modelAndView.setViewName("offers");
        return modelAndView;
    }

//    FIXME
    @RequestMapping(value = "/home/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable(name = "id") int id) {
        offerService.deleteOffer(id);
        return "offers";
    }




    @RequestMapping(value = "/home/offer/delete/{id}", method = RequestMethod.DELETE)
    void deleteEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("offers");
        offerService.deleteOffer(id);
    }

}
