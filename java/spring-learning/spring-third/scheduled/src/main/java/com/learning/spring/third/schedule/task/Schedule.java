package com.learning.spring.third.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    /**
     * 每5秒钟调用一次执行
     * 这个周期是以上一次##完成时间##为基准，在上一个任务完成之后，5s后再次执行
     */
    @Scheduled(fixedDelay = 5000)
    public void task1() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * 每5秒钟调用一次执行
     * 这个周期是以上一个任务##开始时间##为基准，从上一任务开始执行后5s再次调用：
     */
    @Scheduled(fixedRate = 5000)
    public void task2() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * 这里是在每天的13点30分执行一次
     *
     * Cron表达式:
     * Seconds Minutes Hours DayofMonth Month DayofWeek
     * （1）*：表示匹配该域的任意值。假如在Minutes域使用*, 即表示每分钟都会触发事件。
     * （2）?：只能用在DayofMonth和DayofWeek两个域。它也匹配域的任意值，但实际不会。因为DayofMonth和DayofWeek会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法： 13 13 15 20 * ?, 其中最后一位只能用？，而不能使用*，如果使用*表示不管星期几都会触发，实际上并不是这样。
     * （3）/：表示起始时间开始触发，然后每隔固定时间触发一次。例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次.
     */
    @Scheduled(cron = "0 30 13 * * ?")
    public void task3() throws InterruptedException {
        Thread.sleep(1000);
    }
}