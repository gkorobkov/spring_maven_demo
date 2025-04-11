package com.example.spring_maven_demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class SchedulerService {
    private long delay = 1000L; // Начальная задержка в миллисекундах
    private static int count = 0;

    @Scheduled(fixedRate = 500)
    public void runTask() {
        try {
            System.out.println("Run scheduler. Count=" + count + " Delay=" + delay);
            // Выполняем необходимые операции...
            count++;
            //if (count>50) return;
            Thread.sleep(delay); // Ждем текущую задержку перед следующим запуском
            delay *= 2; // Удваиваем задержку для следующего запуска
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
