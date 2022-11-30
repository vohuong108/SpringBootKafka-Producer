package vv.huong.SpringBootKafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic notification() {
        return new NewTopic("notification", 2, (short) 3);
    }

    @Bean
    NewTopic statistic() {
        return new NewTopic("statistic", 1, (short) 3);
    }
}
