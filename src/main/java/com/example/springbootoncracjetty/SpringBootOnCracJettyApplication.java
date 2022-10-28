package com.example.springbootoncracjetty;

import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootOnCracJettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOnCracJettyApplication.class, args);
    }
}

@Component
class CracConfig implements Resource {
    private final ConfigurableApplicationContext ctx;

    CracConfig(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
        Core.getGlobalContext().register(this);
    }

    @Override public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
        System.out.println("BeforeCheckpoint");
        ctx.stop();
    }

    @Override public void afterRestore(Context<? extends Resource> context) throws Exception {
        System.out.println("AfterRestore");
        ctx.start();
    }
}

@RestController
class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "hello";
    }

}
