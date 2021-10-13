package com.ardctraining.core.handlers;

import com.ardctraining.core.enums.CustomFeedbackEnum;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.joda.time.DateTime;

import javax.annotation.Resource;

public class CustomerFeedBackStatusHandler implements DynamicAttributeHandler<CustomFeedbackEnum, CustomerFeedbackModel> {

    private ConfigurationService configurationService;



    @Override
    public CustomFeedbackEnum get(CustomerFeedbackModel model) {
        int days = getConfigurationService().getConfiguration().getInt("feedback.status.duedate.calculation.days.threshold");
        DateTime feedbacksubmitDate = new DateTime(model.getSubmittedDate());
        DateTime limitReadDate = feedbacksubmitDate.plusDays(days);

        if (model.getRead()){
            if(limitReadDate.isAfterNow()){
                return CustomFeedbackEnum.READ;
            }else {
                return CustomFeedbackEnum.READ_PASTDUE;
            }
        }else {
            if(limitReadDate.isBeforeNow()){
                return CustomFeedbackEnum.PASTDUE;
            }
            return CustomFeedbackEnum.NOT_READ;
        }
    }

    @Override
    public void set(CustomerFeedbackModel model, CustomFeedbackEnum customFeedbackEnum) {
        throw new UnsupportedOperationException("Write is not a valid operation for this dynamic attribute");
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
