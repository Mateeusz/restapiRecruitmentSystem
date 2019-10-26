package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.TestType;
import pl.mateuszharazin.restapi.repository.TestTypeRepository;

import javax.validation.Valid;

@Controller
public class TestTypeController {

@Autowired
private TestTypeRepository testTypeRepository;

    @RequestMapping(value = "/admin/test/addType", method = RequestMethod.GET)
    public ModelAndView testType() {
        ModelAndView modelAndView = new ModelAndView();
        TestType testType = new TestType();
        modelAndView.addObject("testType", testType);
        modelAndView.setViewName("testTypeForm"); // resources/template/testTypeForm.html
        return modelAndView;
    }

@RequestMapping(value = "/admin/test/addType", method = RequestMethod.POST)
public ModelAndView addTestType(@Valid @ModelAttribute TestType testType, BindingResult bindingResult, ModelMap modelMap) {

    ModelAndView modelAndView = new ModelAndView();
    if(bindingResult.hasErrors()) {
        modelAndView.addObject("successMessage", "Please correct the errors in form!");
        modelMap.addAttribute("bindingResult", bindingResult);
    }
    else {
        testTypeRepository.save(testType);
        modelAndView.addObject("successMessage", "Test type has been added successfully!");
    }
    modelAndView.addObject("testType", new TestType());
    modelAndView.setViewName("testTypeForm"); // resources/template/testTypeForm.html

    return modelAndView;
}

@RequestMapping(value = "/admin/test/types", method = RequestMethod.GET)
public ModelAndView getTestType() {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("testTypeList", testTypeRepository.findAll());
    modelAndView.setViewName("testTypeList");
    return modelAndView;
}

    @RequestMapping(value = "/admin/test/editType/{id}", method = RequestMethod.GET)
    public ModelAndView updateTestType(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        TestType testType = testTypeRepository.findAllById(id);
        modelAndView.addObject("testType", testType);
        modelAndView.setViewName("testTypeEditForm");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/editType/{id}", method = RequestMethod.POST)
    public ModelAndView putTestType(@PathVariable(value = "id") int id, @Valid @ModelAttribute TestType testType) {

        ModelAndView modelAndView = new ModelAndView();
        TestType testType1 = testTypeRepository.findAllById(id);
        testType1.setTestTypeName(testType.getTestTypeName());
        testType1.setTestTypeDesc(testType.getTestTypeDesc());

        modelAndView.addObject("testType", testType);
        testTypeRepository.save(testType1);


        modelAndView.setViewName("testTypeList");
        return modelAndView;
    }

}
