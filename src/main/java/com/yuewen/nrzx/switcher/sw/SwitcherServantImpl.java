package com.yuewen.nrzx.switcher.sw;

import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import com.yuewen.nrzx.switcher.ex.ErrorConst;
import com.yuewen.nrzx.switcher.service.SwitcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * project : switch-server
 *
 * @author liyuqi
 */
@TarsServant("SwitchObj")
public class SwitcherServantImpl implements SwitcherServant {
    private static final Logger logger = LoggerFactory.getLogger(SwitcherServantImpl.class);

    @Autowired
    SwitcherService switcherService;

    @Override
    public int swOn(CommonInParam in, String swKey, Holder<Boolean> swStatus) {
        logger.info("swOn|" + in.platform + "|" + in.appId + "|" + in.areaId + "|" + swKey);
        swStatus.value = false;
        if (StringUtils.isEmpty(swKey)) {
            return ErrorConst.ERROR_PARAM_ERROR;
        }

        try {
            Holder<Integer> ret = new Holder<>();
            boolean status = switcherService.swOn(swKey, ret);
            if (ret.value == ErrorConst.ERROR_SUCCESS) {
                swStatus.value = status;
                return ErrorConst.ERROR_SUCCESS;
            }

            return ret.value;
        } catch (Throwable e) {
            logger.error("swOnException|" + swKey, e);
            return ErrorConst.ERROR_INTERNAL_ERROR;
        }
    }

    @Override
    public int swRegularOn(CommonInParam in, String swKey, String expression, Holder<Boolean> swStatus) {
        logger.info("swOn|" + in.platform + "|" + in.appId + "|" + in.areaId + "|" + swKey + "|expression=" + expression);
        swStatus.setValue(false);
        if (StringUtils.isEmpty(swKey) || StringUtils.isEmpty(expression)) {
            return ErrorConst.ERROR_PARAM_ERROR;
        }

        try {
            Holder<Integer> ret = new Holder<>();
            boolean status = switcherService.swOn(swKey, expression, ret);
            if (ret.value == ErrorConst.ERROR_SUCCESS) {
                swStatus.value = status;
                return ErrorConst.ERROR_SUCCESS;
            }

            return ret.value;
        } catch (Throwable e) {
            logger.error("swRegularOnException|" + swKey, e);
            return ErrorConst.ERROR_INTERNAL_ERROR;
        }
    }

    @Override
    public int swGrayOn(CommonInParam in, String swKey, String grayId, Holder<Boolean> swStatus) {
        logger.info("swOn|" + in.platform + "|" + in.appId + "|" + in.areaId + "|" + swKey + "|grayId=" + grayId);
        swStatus.setValue(false);
        if (StringUtils.isEmpty(swKey) || StringUtils.isEmpty(grayId)) {
            return ErrorConst.ERROR_PARAM_ERROR;
        }

        try {
            Holder<Integer> ret = new Holder<>();
            boolean status = switcherService.swOn(swKey, grayId, ret);
            if (ret.value == ErrorConst.ERROR_SUCCESS) {
                swStatus.value = status;
                return ErrorConst.ERROR_SUCCESS;
            }

            return ret.value;
        } catch (Throwable e) {
            logger.error("swGrayOnException|" + swKey, e);
            return ErrorConst.ERROR_INTERNAL_ERROR;
        }
    }
}
