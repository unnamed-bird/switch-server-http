package com.yuewen.nrzx.switcher.service;

import com.qq.tars.common.support.Holder;
import com.yuewen.nrzx.switcher.dao.SwSwitchDao;
import com.yuewen.nrzx.switcher.dao.SwSwitchRuleDao;
import com.yuewen.nrzx.switcher.ex.ErrorConst;
import com.yuewen.nrzx.switcher.model.SwSwitch;
import com.yuewen.nrzx.switcher.model.SwSwitchRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;


/**
 * project : switch-server
 *
 * @author liyuqi
 */
@Service
public class SwitcherService {
    private static final Logger logger = LoggerFactory.getLogger(SwitcherService.class);

    @Autowired
    SwSwitchDao swSwitchDao;
    @Autowired
    SwSwitchRuleDao swSwitchRuleDao;

    @Autowired
    SwitchPatternService switchPatternService;

    /**
     * 判断普通类型的开关是否打开
     *
     * @param swKey key 开关key
     * @return 判断开关是否存在
     */
    public boolean swOn(String swKey, Holder<Integer> ret) {
        logger.info("swOn|{}", swKey);

        SwSwitch swSwitch = swSwitchDao.findSwSwitch(swKey);
        if (swSwitch == null) {
            logger.warn("swSwitchNotExist|" + swKey);
            ret.value = ErrorConst.ERROR_SW_NOT_EXIST;
            return false;
        }

        if (swSwitch.getType() == null || swSwitch.getType() != 1) {
            logger.warn("swSwitchTypeError|" + swKey);
            ret.value = ErrorConst.ERROR_SW_RULE_TYPE_INVALID;
            return false;
        }

        SwSwitchRule swSwitchRule = swSwitchRuleDao.findOneRuleByAppId(swSwitch.getId());
        if (swSwitchRule == null) {
            logger.warn("swRuleNotExist|" + swKey);
            ret.value = ErrorConst.ERROR_SW_RULE_NOT_EXIST;
            return false;
        }

        String rule = swSwitchRule.getRule();
        if (StringUtils.isEmpty(rule)) {
            logger.warn("swRuleInvalid|" + swKey + "|{}", rule);
            ret.value = ErrorConst.ERROR_SW_RULE_FORMAT_INVALID;
            return false;
        }
        ret.value = ErrorConst.ERROR_SUCCESS;
        return "true".equalsIgnoreCase(rule);
    }

    /**
     * 判断复杂类型开关是否存在
     *
     * @param swKey key
     * @param param 待匹配值
     * @param ret   返回结果
     * @return 是否命中
     */
    public boolean swOn(String swKey, String param, Holder<Integer> ret) {
        logger.info("swOn|{}|{}", swKey, param);

        SwSwitch swSwitch = swSwitchDao.findSwSwitch(swKey);
        if (swSwitch == null) {
            logger.warn("swSwitchNotExist|" + swKey);
            ret.value = ErrorConst.ERROR_SW_NOT_EXIST;
            return false;
        }

        if (swSwitch.getType() == null) {
            logger.warn("swSwitchTypeNull|" + swKey);
            ret.value = ErrorConst.ERROR_SW_RULE_TYPE_INVALID;
            return false;
        }

        //type 1, 2
        List<SwSwitchRule> swRules = swSwitchRuleDao.findRulesByAppId(swSwitch.getId());
        if (swRules.isEmpty()) {
            logger.warn("swRuleInvalidEmpty|" + swKey);
            ret.value = ErrorConst.ERROR_SW_RULE_FORMAT_INVALID;
            return false;
        }

        if (swSwitch.getType() == SwSwitch.SW_TYPE_REG) {
            for (SwSwitchRule sw : swRules) {
                if (StringUtils.isEmpty(sw.getRule())) {
                    continue;
                }

                Pattern pattern = switchPatternService.expression(sw.getId() + "." + sw.getAppId(), sw.getRule());
                if (pattern.matcher(param).find()) {
                    ret.value = ErrorConst.ERROR_SUCCESS;
                    return true;
                }
            }
            //miss
            ret.value = ErrorConst.ERROR_SUCCESS;
            return false;
        } else if (swSwitch.getType() == SwSwitch.SW_TYPE_GRAY) {
            for (SwSwitchRule sw : swRules) {
                if (StringUtils.isEmpty(sw.getRule())) {
                    continue;
                }

                if (sw.getRule().equals(param)) {
                    ret.value = ErrorConst.ERROR_SUCCESS;
                    return true;
                }
            }
            //miss
            ret.value = ErrorConst.ERROR_SUCCESS;
            return false;
        }
        //type miss
        ret.value = ErrorConst.ERROR_SW_RULE_TYPE_INVALID;
        return false;
    }

}
