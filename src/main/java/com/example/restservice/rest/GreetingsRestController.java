package com.example.restservice.rest;


import com.example.restservice.model.Greetings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingsRestController {

    private static final String template_admins = "Hello admin, %s!";
    private static final String template_users = "Hello user, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/admins/greeting")
    @PreAuthorize("hasRole('ADMIN')")
    public Greetings greetingsAdmin(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greetings(counter.incrementAndGet(), String.format(template_admins, name));
    }

    @GetMapping("/users/greeting")
    @PreAuthorize("hasRole('USER')")
    public Greetings greetingsUser(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greetings(counter.incrementAndGet(), String.format(template_users, name));
    }

    @PostMapping("/achievements/users")
    public Greetings achievementModify(@RequestBody String name) {
        return new Greetings(counter.incrementAndGet(), String.format(template_users, name));
    }
}