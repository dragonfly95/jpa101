package com.example.jpa101.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardController {

    @RequestMapping("")
    private ResponseEntity<Map<String, String>> hello() {

        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}