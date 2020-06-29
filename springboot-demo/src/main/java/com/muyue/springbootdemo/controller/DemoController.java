package com.muyue.springbootdemo.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    public static class Event{

        private final String event = "hello";

        @Subscribe
        public void listen(String event) {
            System.out.println(event + ": this is a event demo!");
        }

        @Subscribe
        public void listen(Integer a) {
            System.out.println(a + ": this is a event demo!");
        }

    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Event());
        eventBus.post("demo");
        eventBus.post(1);
    }



}
