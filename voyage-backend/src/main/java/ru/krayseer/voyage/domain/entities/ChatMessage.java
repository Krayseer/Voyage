package ru.krayseer.voyage.domain.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChatMessage {

    String content;

    String sender;

}
