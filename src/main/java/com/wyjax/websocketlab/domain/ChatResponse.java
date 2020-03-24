package com.wyjax.websocketlab.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {
    private String user;
    private String message;
    private String datetime;
}
