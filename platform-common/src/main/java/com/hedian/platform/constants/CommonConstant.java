package com.hedian.platform.constants;

import org.springframework.beans.factory.annotation.Value;

/**

 * @Description:    常用常量

 * @Author:         noahw

 * @CreateDate:     2019-01-15 14:16

 * @Version:        1.0

 */
public class CommonConstant {

    //默认编码
    public static String DEFAULT_CHARSET;

    @Value("${self.default.charset}")
    public void setFtpIp(String charset) {
        DEFAULT_CHARSET = charset;
    }
}
