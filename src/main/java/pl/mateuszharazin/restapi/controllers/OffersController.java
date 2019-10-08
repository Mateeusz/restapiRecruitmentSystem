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

//    @RequestMapping(value = "/home/deleteoffer", method = RequestMethod.GET)
//    public ModelAndView editOffer() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editOffer");
//        return modelAndView;
//    }

    @RequestMapping(value = "/home/editoffer/{id}", method = RequestMethod.GET)
    public ModelAndView updateOffer(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Offer offer = offerRepository.findAllById(id);
        modelAndView.addObject("offer", offer);
        modelAndView.setViewName("editOffer");

        return modelAndView;
    }

//    @RequestMapping(value = "/home/editoffer/{id}", method = RequestMethod.POST)
//    public ModelAndView putOffer(@RequestBody Offer newOffer, @PathVariable(name = "id") int id) {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName("editOffer");
////        modelAndView.addObject(offerRepository.findById(id)
////                .map(offer -> {
////                    if(newOffer.getTitle()!=null && !newOffer.getTitle().isEmpty()) {
////                        offer.setTitle(newOffer.getTitle());
////                    }
////                    if(newOffer.getSalary()!=0 && newOffer.getSalary()>0) {
////                        offer.setSalary(newOffer.getSalary());
////                    }
////                    if(newOffer.getDescription()!=null && newOffer.getDescription().isEmpty()) {
////                        offer.setDescription(newOffer.getDescription());
////                    }
////                    if(newOffer.getRequirements()!=null && newOffer.getRequirements().isEmpty()) {
////                        offer.setRequirements(newOffer.getRequirements());
////                    }
////                    if(newOffer.getVacantNumber()!=0 && newOffer.getVacantNumber()>0) {
////                        offer.setVacantNumber(newOffer.getVacantNumber());
////                    }
////                    offerRepository.save(offer);
////                })
////                .orElseGet(() -> {
////                    newOffer.setId(id);
////                    offerRepository.save(newOffer);
////                }));
//        ModelAndView modelAndView = new ModelAndView();
//        Offer offer = offerRepository.findAllById(id);
//        offer.setTitle(newOffer.getTitle());
//        offer.setSalary(newOffer.getSalary());
//        offer.setDescription(newOffer.getDescription());
//        offer.setRequirements(newOffer.getRequirements());
//        offer.setVacantNumber(newOffer.getVacantNumber());
//
//        modelAndView.addObject("offerDetail", offer);
//        offerService.newOffer(offer);
//        modelAndView.setViewName("editOffer");
//
//        System.out.println("~Mat: offer:" + offer().toString());
//        System.out.println("~Mat: Newoffer:" + newOffer.toString());
//        return modelAndView;
//    }
//    @RequestMapping(value = "/api/Users/{id}", method = RequestMethod.PUT)
//    public Offer putOffer(@PathVariable int id, @RequestBody Offer newOffer) {
//        return offerRepository.findById(id)
//
//    }
    @RequestMapping(value = "/home/editoffer/{id}", method = RequestMethod.POST)
    public ModelAndView putOffer(@PathVariable(value = "id") int id, @Valid @ModelAttribute Offer offer) {
//        return offerRepository.findById(id)
//                .map(offerDetails -> {
//
//                })
//                .map(user -> {
//                    if(newUser.getName()!=null&&!newUser.getName().isEmpty())
//                    {
//                        user.setName(newUser.getName());
//                    }
//                    if(newUser.getAge()!=null&&newUser.getAge()>0)
//                    {
//                        user.setAge(newUser.getAge());
//                    }
//                    return repo.save(user);
//                })
//                .orElseGet(() -> {
//                    newUser.setUser_id(id);
//                    return repo.save(newUser);
//                });
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
    @RequestMapping("/home/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        offerService.deleteOffer(id);
        return "offers";
    }

//    Podmianę obiektów zrób!!!
//    @RequestMapping(value = "/home/edit/{id}", method = RequestMethod.PUT)
//    public ModelAndView saveProduct(@Valid@ModelAttribute("offer") Offer offer, @PathVariable int id) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("~Mat: ID: " + id + "  Offer: " + offer.toString());
//        offer.setId(id);
//        offerService.newOffer(offer);
//        modelAndView.addObject(offer);
//        modelAndView.setViewName("editOffer");
//
//        return modelAndView;
//    }



    @RequestMapping(value = "/home/offer/delete/{id}", method = RequestMethod.DELETE)
    void deleteEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("offers");
        offerService.deleteOffer(id);
    }

//    @PutMapping("/employees/{id}")
//    Offer updateOffer(@RequestBody Offer newOffer, @PathVariable int id) {
//
//        return offerRepository.findAllById(id).map
//
//
////                map(employee -> {
////            employee.setFirstName(newEmployee.getFirstName());
////            employee.setLastName(newEmployee.getLastName());
////            employee.setEmail(newEmployee.getEmail());
////            return repository.save(employee);employee
//        }).orElseGet(() -> {
//            newEmployee.setId(id);
//            return repository.save(newEmployee);
//        });
//    }

//    @PutMapping("/students/{id}")
//    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
//        Optional<Student> studentOptional = studentRepository.findById(id);
//        if (!studentOptional.isPresent())
//            return ResponseEntity.notFound().build();
//        student.setId(id);
//        studentRepository.save(student);
//        return ResponseEntity.noContent().build();
//    }
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
