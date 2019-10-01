package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.repository.OfferRepository;

@Controller
public class OffersController {

    @Autowired
    OfferRepository offerRepository;

    @Value("${spring.queries.offers-query}")

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public ModelAndView showOffers() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offer", offerRepository.findAll().get(1));
        modelAndView.setViewName("offers");

        return modelAndView;
    }

}
