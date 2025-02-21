package com.ra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @GetMapping
    public ResponseEntity<?> home(){
        return new ResponseEntity<>("Xin chao den voi API", HttpStatus.OK);
    }
    @GetMapping("/cart")
    public ResponseEntity<?> cart(){
        return new ResponseEntity<>("Trang giot hang",HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return new ResponseEntity<>("Trang Quản trị",HttpStatus.OK);
    }
}
