package com.msa.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Aa {
    @GetMapping("/")
    public String aa() {
        return "hihi";
    }
}
