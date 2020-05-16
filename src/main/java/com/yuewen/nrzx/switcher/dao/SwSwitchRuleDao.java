package com.yuewen.nrzx.switcher.dao;

import com.yuewen.nrzx.switcher.model.SwSwitchRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * project : switch-server
 *
 * @author 2020-04-26 16:15</br>
 */
@Repository
public class SwSwitchRuleDao {
    private static final Logger logger = LoggerFactory.getLogger(SwSwitchRuleDao.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据switchId 找到第一个开关规则
     *
     * @param switchId 开关ID
     * @return 第一个开关规则
     */
    @Cacheable(value = "swCache", key = "#switchId", sync = true)
    public SwSwitchRule findOneRuleByAppId(Integer switchId) {
        logger.info("findOneRule|" + switchId);

        return jdbcTemplate.queryForObject("select id,app_id,switch_id,rule,sort from sw_switch_rules where status =1 and switch_id = ?" +
                        " order by sort asc  limit 1"
                , new Object[]{switchId}, new BeanPropertyRowMapper<>(SwSwitchRule.class));
    }

    /**
     * 根据switchId 找到开关规则列表
     *
     * @param switchId 开关ID
     * @return 第一个开关规则
     */
    @Cacheable(value = "swCache", key = "#switchId", sync = true)
    public List<SwSwitchRule> findRulesByAppId(Integer switchId) {
        logger.info("findRulesByAppId|" + switchId);
        return jdbcTemplate.query("select id,app_id,switch_id,rule,sort from sw_switch_rules where status =1 and switch_id = ?" +
                        " order by sort asc"
                , new Object[]{switchId}, new BeanPropertyRowMapper<>(SwSwitchRule.class));
    }
}
