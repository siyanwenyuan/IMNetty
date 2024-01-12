package com.chen.im;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {
    /**
     * 连接信息编码
     */
    private String code;
    /**
     * 昵称
     */
    private  String nickname;


}
