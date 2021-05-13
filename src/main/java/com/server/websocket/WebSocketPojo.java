package com.server.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WebSocketPojo implements Serializable {

    private static final long serialVersionUID = -5093460762146620022L;

    private String userId;
}
