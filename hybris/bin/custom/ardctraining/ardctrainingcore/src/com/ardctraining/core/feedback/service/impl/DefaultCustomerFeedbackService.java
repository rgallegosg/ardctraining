package com.ardctraining.core.feedback.service.impl;

import com.ardctraining.core.feedback.dao.CustomerFeedbackDao;
import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.model.CustomerFeedbackEmailProcessModel;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DefaultCustomerFeedbackService implements CustomerFeedbackService {

    private CustomerFeedbackDao customerFeedbackDao;
    private ModelService modelService;
    private TimeService timeService;
    private UserService userService;
    private BusinessProcessService businessProcessService;
    private BaseSiteService baseSiteService;
    private static final String FIELD_SEPARATOR = "|";


    @Override
    public List<CustomerFeedbackModel> findByCustomer(CustomerModel customer) {
        ServicesUtil.validateParameterNotNull(customer, "customer cannot be null");

        return getCustomerFeedbackDao().findByCustomer(customer);
    }

    @Override
    public void save(CustomerFeedbackModel feedbackModel) {
        CustomerFeedbackModel feedbackSave = getCustomerFeedbackDao().save(feedbackModel);
        Set<String> feedbackSet = new HashSet<>();

        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss.S");
        final Date now = getTimeService().getCurrentTime();

        final CustomerFeedbackEmailProcessModel process = getBusinessProcessService().createProcess(
                new StringBuilder().append("customerFeedbackEmailProcess").append("-").append(dateFormat.format(now)).toString(),
                "customerFeedbackEmailProcess");

        process.setLanguage(getBaseSiteService().getBaseSiteForUID("electronics").getDefaultLanguage());
        process.setSite(getBaseSiteService().getBaseSiteForUID("electronics"));
        feedbackSet.add(convertCustomerFeedback(feedbackSave));
        process.setCustomerFeedbacks(feedbackSet);
        modelService.save(process);
        getBusinessProcessService().startProcess(process);

    }

    String convertCustomerFeedback (CustomerFeedbackModel customerFeedbackModel){
        return new StringBuilder()
                .append(Objects.isNull(customerFeedbackModel.getCustomer()) ? StringUtils.EMPTY : customerFeedbackModel.getCustomer().getUid())
                .append(FIELD_SEPARATOR)
                .append(customerFeedbackModel.getSubject())
                .append(FIELD_SEPARATOR)
                .append(customerFeedbackModel.getMessage())
                .append(FIELD_SEPARATOR)
                .append(customerFeedbackModel.getSubmittedDate())
                .append(FIELD_SEPARATOR)
                .append(customerFeedbackModel.getStatus())
                .toString();
    }


    public CustomerFeedbackDao getCustomerFeedbackDao() {
        return customerFeedbackDao;
    }

    public void setCustomerFeedbackDao(CustomerFeedbackDao customerFeedbackDao) {
        this.customerFeedbackDao = customerFeedbackDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }
}
