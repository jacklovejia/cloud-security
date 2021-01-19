package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('p11')")
    public String getOrder(@PathVariable Long id) {
        return "订单" + id;
    }
}
