package com.metar.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan("com.metar.task")
@Configuration
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulingConfiguration {
}
