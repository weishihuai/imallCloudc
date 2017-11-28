package com.imall.iportal.core.processor;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import org.springframework.stereotype.Component;

/**
 * Created by HWJ on 2017/7/6
 */
@Component
public class OrderIndexProcessor extends BaseIndexProcesser {
    @Override
    public IndexTypeCodeEnum getObjectTypeCode() {
        return IndexTypeCodeEnum.ORDER;
    }
}
