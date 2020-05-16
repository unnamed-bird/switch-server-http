package com.yuewen.nrzx.switcher.dao;

import com.yuewen.nrzx.switcher.model.SwSwitch;
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
 * @author 2020-04-26 16:29</br>
 */
@Repository
public class SwSwitchDao {
    private static final Logger logger = LoggerFactory.getLogger(SwSwitchRuleDao.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Cacheable(value = "swCache", key = "#nameKey", sync = true)
    public SwSwitch findSwSwitch(String nameKey) {
        logger.info("findSwitch|" + nameKey);
        List<SwSwitch> swSwitches = jdbcTemplate.query("select id,name,name_key,type from sw_switch where name_key = ?"
                , new Object[]{nameKey}, new BeanPropertyRowMapper<>(SwSwitch.class));

        if (swSwitches.isEmpty()) {
            return null;
        }
        return swSwitches.get(0);
    }
}
