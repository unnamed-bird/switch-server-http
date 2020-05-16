package com.yuewen.nrzx.switcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * project : switch-server
 *
 * @author liyuqi 2020-04-26 17:45</br>
 */
@Service
public class SwitchPatternService {
    private static final Logger logger = LoggerFactory.getLogger(SwitchPatternService.class);

    @Cacheable(value = "swRuleCache", key = "#ruleId", sync = true)
    public Pattern expression(String ruleId, String rule) {
        logger.info("cacheExpression|" + ruleId + "|" + rule);
        return Pattern.compile(rule);
    }
}
