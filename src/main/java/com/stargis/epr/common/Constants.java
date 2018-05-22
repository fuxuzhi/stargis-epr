package com.stargis.epr.common;

/**
 * 常量类
 */
public class Constants {

    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求
    public static final int RESULT_CODE_BAD_REQUEST = 412;  // bad request
    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 没有对应结果

    public static final String ARTICLE_CACHE_KEY = "ssm-cluster:article:";//文章key
    public static final String PICTURE_CACHE_KEY = "ssm-cluster:picture:";//图片key

    public static final String UPLOAD_PATH = "E:\\epr\\uploadpic\\";//文件存储地址
    public static final String REGISTRY_UPLOAD_PATH = "file:E:\\epr\\uploadpic\\";//文件拦截设置

}
