package com.yuewen.nrzx.switcher.sw;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import com.qq.tars.client.ServantProxyConfig;
import com.qq.tars.common.support.Holder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * project : switch-server
 *
 * @author liyuqi 2020-04-26 11:15</br>
 */
public class SwitcherServantImplTest {
    private static final Logger logger = LoggerFactory.getLogger(SwitcherServantImplTest.class);

    @Test
    public void swOn() {
        CommunicatorConfig config = new CommunicatorConfig();
        config.setCharsetName("utf-8");

        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(config);

        ServantProxyConfig servantProxyConfig = new ServantProxyConfig("Unite.SwitcherServer.SwitchObj@tcp -h 127.0.0.1 -p 18601 -t 3000");
        SwitcherServant prx = communicator.stringToProxy(SwitcherServant.class, servantProxyConfig);

        Holder<Boolean> ret = new Holder<>();
        int code = prx.swOn(new CommonInParam(), "key_2", ret);
        logger.info("swOnRet=|" + code+"|"+ret.value);

        communicator.shutdown();
    }

    @Test
    public void swRegularOn() {
    }

    @Test
    public void swGrayOn() {
        CommunicatorConfig config = new CommunicatorConfig();
        config.setCharsetName("utf-8");

        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(config);

        ServantProxyConfig servantProxyConfig = new ServantProxyConfig("Unite.SwitcherServer.SwitchObj@tcp -h 127.0.0.1 -p 18601 -t 3000");
        SwitcherServant prx = communicator.stringToProxy(SwitcherServant.class, servantProxyConfig);

        Holder<Boolean> ret = new Holder<>();
        int code = prx.swOn(new CommonInParam(), "key_2", ret);
        logger.info("swOnRet=|" + code+"|"+ret.value);

        communicator.shutdown();
    }
}