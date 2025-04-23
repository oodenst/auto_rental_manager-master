package com.coder.rental.vo;

import lombok.Data;

@Data
public class MailVo {
    private String from;
    private String to;
    private String subject;
    private String content;
}
