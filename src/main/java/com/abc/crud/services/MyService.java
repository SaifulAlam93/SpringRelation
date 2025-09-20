package com.abc.crud.services;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void sayHello() {
        System.out.println("Hello, Saiful!");
    }

    public void doWork() {
        System.out.println("Doing some work...");
    }
}
