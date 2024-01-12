package com.chen.im;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CommandType {


    /**
     * 建立连接
     */
    CONNECTION(10001),
    ERROR(-1);
    private Integer code;

    /**
     * 写一个匹配方法
     */

    public static CommandType match(Integer code) {
        for (CommandType value : CommandType.values()) {
            if (value.getCode().equals(code)) {
                return value;

            }

        }
        return ERROR;

    }
}
