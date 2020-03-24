package com.wyjax.websocketlab.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest {
    private String user;
    private String message;
}