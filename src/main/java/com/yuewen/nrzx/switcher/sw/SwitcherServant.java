// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package com.yuewen.nrzx.switcher.sw;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface SwitcherServant {
	/**
	 * 普通开关类型 , 判断开关是否打开
	 * @param in 统一入参
	 * @param string swKey  开关唯一KEY
	 * @param bool swStatus 开关结果
	 * @return RPC 调用结果 , 0:success , 其它失败,详见错误返回码wiki
	 */
	 int swOn(CommonInParam in, String swKey, @TarsHolder Holder<java.lang.Boolean> swStatus);
	/**
	 * 正则开关类型 , 判断开关是否打开
	 * @param in 统一入参
	 * @param string swKey  开关唯一KEY
	 * @param string expression 待校验结果
	 * @param bool swStatus 开关结果
	 * @return RPC 调用结果 , 0:success , 其它失败,详见错误返回码wiki
	 */
	 int swRegularOn(CommonInParam in, String swKey, String expression, @TarsHolder Holder<java.lang.Boolean> swStatus);
	/**
	 * 灰度开关类型  , 判断开关是否打开
	 * @param in 统一入参
	 * @param string swKey  开关唯一KEY
	 * @param string grayId 待校验结果
	 * @param bool swStatus 开关结果
	 * @return RPC 调用结果 , 0:success , 其它失败,详见错误返回码wiki
	 */
	 int swGrayOn(CommonInParam in, String swKey, String grayId, @TarsHolder Holder<java.lang.Boolean> swStatus);
}
