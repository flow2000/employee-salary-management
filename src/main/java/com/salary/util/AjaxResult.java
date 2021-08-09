package com.salary.util;

import java.util.HashMap;
import java.util.List;

/**
 * date: 2021/08/2
 * description: 返回消息类
 * require:
 * author: 庞海
 * version: 1.0
 *
 */

public class AjaxResult extends HashMap<String, Object> {

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**
     * 根据消息是否为空来返回成功或者失败消息
     * @param list 返回内容
     *
     * @return 消息
     */
    public static AjaxResult returnMessage(List<?> list){
        if(list==null||list.size()==0){
            return AjaxResult.error("内容为空", Type.WARN,null);
        }
        return AjaxResult.success("操作成功", Type.SUCCESS,list);
    }

    /**
     * 根据消息是否为空来返回成功或者失败消息
     * @param object 返回内容
     *
     * @return 消息
     */
    public static AjaxResult returnMessage(Object object){
        if(object==null){
            return AjaxResult.error("内容为空", Type.WARN,null);
        }
        return AjaxResult.success("操作成功", Type.SUCCESS,object);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, Type.SUCCESS,null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     *
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(msg, Type.SUCCESS, data);
    }


    /**
     * 返回成功消息
     *
     * @param msg    返回提示
     * @param object 返回数据
     * @param type   返回类型
     *
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Type type, Object object )
    {
        return new AjaxResult(msg, type, object);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     *
     * @return 操作结果
     */
    public static AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     *
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, Type.ERROR, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     *
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(msg, Type.ERROR, data);
    }


    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param type 数据类型
     * @param data 数据对象
     *
     * @return 错误消息
     */
    public static AjaxResult error(String msg, Type type, Object data)
    {
        return new AjaxResult(msg, type, data);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     *
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param msg 返回内容
     * @param type 数据类型
     * @param data 数据对象
     */
    public AjaxResult(String msg, Type type, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

}
