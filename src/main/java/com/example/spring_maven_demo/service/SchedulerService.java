package com.example.spring_maven_demo.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
//@EnableAsync
public class SchedulerService {

    ScheduledExecutorService Scheduled_Executor_Service = Executors.newScheduledThreadPool(30);

    // Метод для просмотра списка задач
    public void printAllTasks() {
        System.out.println("Запланированные задачи:");
    }

    private long delay1 = 1000L; // Начальная задержка в миллисекундах
    private long delay2 = 1000L; // Начальная задержка в миллисекундах
    private static int count1 = 0;
    private static int count2 = 0;

    @Scheduled(fixedRate = 510)
    //@Async
    public void runTask1() {
        try {
            System.out.println("Running scheduler 1 fixedRate. Count=" + count1 + " Delay=" + delay1);

            // Выполняем необходимые операции...

            Thread.sleep(delay1); // Ждем текущую задержку перед следующим запуском
            delay1 *= 2; // Удваиваем задержку для следующего запуска
            System.out.println("Exit scheduler 1 fixedRate. Count=" + count1 + " Next Delay=" + delay1);
            count1++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 490)
    //@Async
    public void runTask2() {
        try {
            System.out.println("Running scheduler 2 fixedDelay. Count=" + count2 + " Delay=" + delay2);

            // Выполняем необходимые операции...

            Thread.sleep(delay2); // Ждем текущую задержку перед следующим запуском
            delay2 *= 2; // Удваиваем задержку для следующего запуска
            System.out.println("Exit scheduler 2 fixedDelay. Count=" + count2 + " Next Delay=" + delay2);
            count2++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
