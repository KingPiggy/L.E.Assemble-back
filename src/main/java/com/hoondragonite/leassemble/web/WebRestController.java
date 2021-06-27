package com.hoondragonite.leassemble.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
    @GetMapping("/api/port-test")
    public ResponseEntity<String> portTest(HttpServletRequest request) {
        return ResponseEntity.ok("8080 리턴값 받아라!");
    }

    @GetMapping("/api/port-test1")
    public ResponseEntity<String> portTest1(HttpServletRequest request) {
        return ResponseEntity.ok("8081 리턴값 받아라!");
    }
    @GetMapping("/api/port-test2")
    public ResponseEntity<String> portTest2(HttpServletRequest request) {
        return ResponseEntity.ok("8082 리턴값 받아라!");
    }
}
