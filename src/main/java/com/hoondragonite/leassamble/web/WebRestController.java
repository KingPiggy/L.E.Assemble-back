package com.hoondragonite.leassamble.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {

    @PostMapping("/api/frontTest")
    public ResponseEntity<String> frontTest(HttpServletRequest request) {
        return ResponseEntity.ok("안녕, 친구!");
    }
}
