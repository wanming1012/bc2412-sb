package com.bootcamp.sb.demo_sb_customer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig {
  @Scheduled(fixedDelay = 3000)
  public void sayHello() {
    System.out.println("Hello");
  }
}
