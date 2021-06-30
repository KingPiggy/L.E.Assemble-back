package com.hoondragonite.leassemble.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
    @GetMapping("/api/test/get-value-of-react")
    public ResponseEntity<String> getValueOfReact(HttpServletRequest request) {
        System.out.println("test");
        //String test = (String)request.getAttribute("test");
        String test = "hi";
        return ResponseEntity.ok("받은 값 :" + test);
    }
}
