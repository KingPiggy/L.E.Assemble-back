package com.hoondragonite.leassamble.web;

import com.hoondragonite.leassamble.web.dto.HelloResponseDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

    @PostMapping("/api/frontTest")
    public ResponseEntity<String> frontTest(HttpServletRequest request){
        return ResponseEntity.ok("안녕, 친구!");
    }
}
