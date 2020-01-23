package com.example.backed.service.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class IdGenerator {

    private static long idCounter = 0;

    public static long nextId() {
        return ++idCounter;
    }
}
