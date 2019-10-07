package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OffersController {

    @Autowired
    OfferService offerService;

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
        modelAndView.addObject(offerService.getOffer(offer.getId()));
        modelAndView.setViewName("offerGet");

        modelAndView.addObject("successMessage", "Oferta o wskazanym ID nie istnieje!");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/offer/{id}", method = RequestMethod.GET)
    public ModelAndView getOfferById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offer", offerService.getOffer(id));
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
        modelAndView.setViewName("deleteofferinput");
        modelAndView.addObject("successMessage", "Offer has been deleted successfully!");
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("editOffer");
        Offer offer = offerService.getOffer(id);
        modelAndView.addObject("offer", offer);

        return modelAndView;
    }

//    FIXME
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        offerService.deleteOffer(id);
        return "redirect:/";
    }

//    Podmianę obiektów zrób!!!
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Offer offer) {
        offerService.newOffer(offer);

        return "redirect:/";
    }
//    @RequestMapping(value = "admin/offers/delete", method = RequestMethod.GET)
//    public ModelAndView deleteOffer() {
//        ModelAndView modelAndView = new ModelAndView();
//        Offer offer = new Offer();
//        modelAndView.addObject("offer", offer);
//        modelAndView.setViewName("deleteForm"); // resources/template/offerForm.html
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/offers/delete", method = RequestMethod.GET)
//    public ModelAndView deleteOffer(@RequestParam int id) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        offerService.deleteOffer(id);
//        modelAndView.setViewName("adminOffer"); // resources/template/offerForm.html
//        return modelAndView;
//    }
//    @RequestMapping(value = "admin/offers/delete", method = RequestMethod.POST)
//    public ModelAndView deleteOffer() {
//        ModelAndView modelAndView = new ModelAndView();
//        Offer offer = new Offer();
//        modelAndView.addObject("offer", offer);
//        modelAndView.setViewName("deleteForm"); // resources/template/offerForm.html
//        return modelAndView;
//    }
//
//
//
//
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
//        System.out.println("Delete Customer with ID = " + id + "...");
//
//        offerService.deleteOffer(id);
//
//        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
//    }
}
