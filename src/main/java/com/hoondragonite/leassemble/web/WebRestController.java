package com.hoondragonite.leassemble.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {

    @PostMapping("/api/port-test1")
    public ResponseEntity<String> portTest1(HttpServletRequest request) {
        return ResponseEntity.ok("8081 테스트");
    }
    @PostMapping("/api/port-test2")
    public ResponseEntity<String> portTest2(HttpServletRequest request) {
        return ResponseEntity.ok("8082 테스트");
    }
}
