package com.antibas.utils;

import com.antibas.util.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class JsonTest {

    @Test
    void parse_whenKeyIsEmpty_shouldThrowIllegalArgumentException(){
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> new Json(": 5")
        );
        log.error(thr.toString(), thr);
    }

    @Test
    void parse_whenSplitIsNot2_shouldThrowIllegalArgumentException(){
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> new Json("a: 5: 6")
        );
        log.error("", thr);
    }

    @Test
    void parse_whenInputIsValid_shouldNotThrowIllegalArgumentException(){
        assertDoesNotThrow(
                () -> new Json("""
                a: "string"
                number: 567
                number2: 23.5
                bool: true
                bd: null
                arr: [32, 45.5, false]
                js: {a: "string"; number: 567; bool: true}
                """)
        );
    }
}
