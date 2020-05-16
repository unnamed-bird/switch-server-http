package com.yuewen.nrzx.switcher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qq.tars.common.support.Holder;
import com.yuewen.nrzx.switcher.ex.ErrorConst;
import com.yuewen.nrzx.switcher.sw.CommonInParam;
import com.yuewen.nrzx.switcher.sw.SwitcherServant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * project : switch-server
 */
@RestController
@RequestMapping("/")
public class SwitcherController {
    private static final Logger logger = LoggerFactory.getLogger(SwitcherController.class);
    private static final CommonInParam COMMON_IN_PARAM = new CommonInParam();
    @Autowired
    SwitcherServant swPrx;

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/sw/{swKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestReutrn swOn(@PathVariable("swKey") String swKey) {
        logger.info("swAdapterOn|{}", swKey);
        if (StringUtils.isEmpty(swKey)) {
            return new RestReutrn(ErrorConst.ERROR_PARAM_ERROR, ErrorConst.getErrorMsg(ErrorConst.ERROR_PARAM_ERROR));
        }
        Holder<Boolean> ret = new Holder<>();
        int code = swPrx.swOn(COMMON_IN_PARAM, swKey, ret);

        if (code != ErrorConst.ERROR_SUCCESS) {
            return new RestReutrn(code, ErrorConst.getErrorMsg(code));
        }

        return new RestReutrn(ret.getValue());
    }

    @RequestMapping(value = "/swRegularOn/{swKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestReutrn swRegularOn(@PathVariable("swKey") String swKey, @RequestParam("expression") String expression) {
        logger.info("swRegularAdapterOn|{}|{}", swKey, expression);

        if (StringUtils.isEmpty(expression) || StringUtils.isEmpty(swKey)) {
            return new RestReutrn(ErrorConst.ERROR_PARAM_ERROR, ErrorConst.getErrorMsg(ErrorConst.ERROR_PARAM_ERROR));
        }

        Holder<Boolean> ret = new Holder<>();
        int code = swPrx.swRegularOn(COMMON_IN_PARAM, swKey, expression, ret);

        if (code != ErrorConst.ERROR_SUCCESS) {
            return new RestReutrn(code, ErrorConst.getErrorMsg(code));
        }

        return new RestReutrn(ret.getValue());
    }

    @RequestMapping(value = "/swGrayOn/{swKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestReutrn swGrayOn(@PathVariable("swKey") String swKey, @RequestParam("grayId") String grayId) {
        logger.info("swGrayAdapterOn|{}|{}", swKey, grayId);

        if (StringUtils.isEmpty(grayId) || StringUtils.isEmpty(swKey)) {
            return new RestReutrn(ErrorConst.ERROR_PARAM_ERROR, ErrorConst.getErrorMsg(ErrorConst.ERROR_PARAM_ERROR));
        }

        Holder<Boolean> ret = new Holder<>();
        int code = swPrx.swGrayOn(COMMON_IN_PARAM, swKey, grayId, ret);

        if (code != ErrorConst.ERROR_SUCCESS) {
            return new RestReutrn(code, ErrorConst.getErrorMsg(code));
        }

        return new RestReutrn(ret.getValue());
    }
}
