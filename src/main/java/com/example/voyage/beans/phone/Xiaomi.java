package com.example.voyage.beans.phone;

import com.example.voyage.beans.processor.Processor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Data
public class Xiaomi extends Phone{
    private final Processor processor;

    public Xiaomi(@Qualifier(value = "dimensity") Processor processor){
        this.processor = processor;
    }
}
