package com.yuewen.nrzx.switcher.ex;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * project : switch-server
 *
 * @author - liyuqi 2020-04-26 17:05</br>
 */
public class ErrorConst {

    @ErrorConstMsg("Success")
    public static final int ERROR_SUCCESS = 0;
    @ErrorConstMsg("SERVER_INTERNAL_ERROR")
    public static final int ERROR_INTERNAL_ERROR = 500;
    @ErrorConstMsg("PARAM_ERROR")
    public static final int ERROR_PARAM_ERROR = 1000;
    @ErrorConstMsg("SW_NOT_EXIST")
    public static final int ERROR_SW_NOT_EXIST = 2000;
    @ErrorConstMsg("SW_RULE_NOT_EXIST")
    public static final int ERROR_SW_RULE_NOT_EXIST = 2001;
    @ErrorConstMsg("SW_RULE_FORMAT_INVALID")
    public static final int ERROR_SW_RULE_FORMAT_INVALID = 2002;
    @ErrorConstMsg("SW_RULE_TYPE_INVALID")
    public static final int ERROR_SW_RULE_TYPE_INVALID = 2003;

    private static final Map<Integer, ErrorConstMsg> CODE_MSG_MAP = new HashMap<>();

    static {
        Field[] fields = ErrorConst.class.getDeclaredFields();
        for (Field field : fields) {
            ErrorConstMsg msg = field.getAnnotation(ErrorConstMsg.class);
            if (msg == null) {
                continue;
            }
            try {
                CODE_MSG_MAP.put(field.getInt(null), msg);
            } catch (IllegalAccessException e) {

            }
        }
    }

    public static String getErrorMsg(int code) {
        ErrorConstMsg msg = CODE_MSG_MAP.get(code);
        if (msg == null) {
            return "ErrorCode:" + code;
        }
        return msg.value();
    }
}
