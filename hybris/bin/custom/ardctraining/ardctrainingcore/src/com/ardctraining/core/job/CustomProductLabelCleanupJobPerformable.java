package com.ardctraining.core.job;

import com.ardctraining.core.model.CustomProductLabelCleanupCronjobModel;
import com.ardctraining.core.product.service.CustomProductLabelService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

public class CustomProductLabelCleanupJobPerformable extends AbstractJobPerformable<CustomProductLabelCleanupCronjobModel> {

    private CustomProductLabelService customProductLabelService;

    @Override
    public PerformResult perform(final CustomProductLabelCleanupCronjobModel customProductLabelCleanupCronjobModel) {
        return null;
    }

    @Override
    public boolean isAbortable() {
        return Boolean.TRUE;
    }

    public CustomProductLabelService getCustomProductLabelService() {
        return customProductLabelService;
    }

    public void setCustomProductLabelService(CustomProductLabelService customProductLabelService) {
        this.customProductLabelService = customProductLabelService;
    }
}
