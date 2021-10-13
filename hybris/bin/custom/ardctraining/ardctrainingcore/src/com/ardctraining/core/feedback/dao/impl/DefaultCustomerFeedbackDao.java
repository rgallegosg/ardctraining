package com.ardctraining.core.feedback.dao.impl;

import com.ardctraining.core.feedback.dao.CustomerFeedbackDao;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DefaultCustomerFeedbackDao implements CustomerFeedbackDao {

    private static Logger LOG = Logger.getLogger(DefaultCustomerFeedbackDao.class);
    private FlexibleSearchService flexibleSearchService;
    private TimeService timeService;
    private UserService userService;
    private ModelService modelService;

    private static final String FIND_BY_CUSTOMER_STATUS_READ_FEEDBACK =
            "SELECT {" + ItemModel.PK + "} " +
                    "FROM   {" + CustomerFeedbackModel._TYPECODE + "} " +
                    "WHERE  {" + CustomerFeedbackModel.CUSTOMER + "} = ?customer AND " +
                    "       {" + CustomerFeedbackModel.READ + "} = FALSE " ;

    @Override
    public List<CustomerFeedbackModel> findByCustomer(CustomerModel customer) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BY_CUSTOMER_STATUS_READ_FEEDBACK);
        query.addQueryParameter("customer", customer);

        final SearchResult<CustomerFeedbackModel> result = getFlexibleSearchService().search(query);

        if(Objects.nonNull(result) && CollectionUtils.isNotEmpty(result.getResult())){
            return result.getResult();
        }

        LOG.warn("unable to find result for customo product labels");

        return Collections.emptyList();
    }

    @Override
    public CustomerFeedbackModel save(CustomerFeedbackModel customerFeedbackModel) {

        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss.S");
        final Date now = getTimeService().getCurrentTime();
        customerFeedbackModel.setRead(Boolean.FALSE);
        customerFeedbackModel.setSubmittedDate(now);
        customerFeedbackModel.setCustomer((CustomerModel) getUserService().getCurrentUser());
        getModelService().save(customerFeedbackModel);
        return customerFeedbackModel;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
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
}
