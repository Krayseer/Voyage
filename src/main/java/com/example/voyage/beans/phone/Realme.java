package com.example.voyage.beans.phone;

import com.example.voyage.beans.processor.Processor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Data
public class Realme extends Phone{
    @Autowired
    @Qualifier(value = "snapdragon")
    private Processor processor;
}
