package com.ardctraining.storefront.controllers.pages;

import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import com.ardctraining.storefront.controllers.ControllerConstants;
import com.ardctraining.storefront.form.CustomerFeedbackForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping (value = "/feedback")
public class FeedbackController extends AbstractPageController {

    @Resource(name="customerFeedbackFacade")
    private CustomerFeedbackFacade customerFeedbackFacade;

    @Resource(name="customerFeedbackService")
    private CustomerFeedbackService customerFeedbackService;

    @GetMapping
    public String getView (Model model) throws CMSItemNotFoundException{
        ContentPageModel contentPageModel = getContentPageForLabelOrId("feedback");
        storeCmsPageInModel(model, contentPageModel);
        setUpMetaDataForContentPage(model, contentPageModel);
        model.addAttribute("feedbacks", customerFeedbackFacade.findByCustomer());
        model.addAttribute("feedbackForm", emptyForm());
        return ControllerConstants.Views.Pages.Feedback.FeedbackPage;
    }
    private CustomerFeedbackForm emptyForm(){
        CustomerFeedbackForm customerFeedbackForm = new CustomerFeedbackForm();
        customerFeedbackForm.setMessage(StringUtils.EMPTY);
        customerFeedbackForm.setSubject(StringUtils.EMPTY);
        return customerFeedbackForm;
    }

    @PostMapping("/save")
    public String save (CustomerFeedbackForm customerFeedbackForm){
        customerFeedbackFacade.save(customerFeedbackForm.getSubject(), customerFeedbackForm.getMessage());
        return "redirect:/feedback";
    }

}
