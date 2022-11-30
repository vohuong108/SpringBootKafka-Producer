package vv.huong.SpringBootKafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vv.huong.SpringBootKafka.model.MessageDTO;
import vv.huong.SpringBootKafka.model.StatisticDTO;
import vv.huong.SpringBootKafka.repository.MessageRepo;
import vv.huong.SpringBootKafka.repository.StatisticRepo;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PollingService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MessageRepo messageRepo;
    private final StatisticRepo statisticRepo;

    @Scheduled(fixedDelay = 1000)
    public void producer() {
        List<MessageDTO> messages = messageRepo.findAllByStatus(false);

        for (MessageDTO message : messages) {
            kafkaTemplate.send("notification", message).addCallback(new KafkaSendCallback<String, Object>() {
                @Override
                public void onFailure(KafkaProducerException e) {
                    e.printStackTrace();
                }

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    message.setStatus(true);
                    messageRepo.save(message);
                }
            });
        }

        List<StatisticDTO> statistics = statisticRepo.findAllByStatus(false);

        for (StatisticDTO statistic : statistics) {
            kafkaTemplate.send("statistic", statistic).addCallback(new KafkaSendCallback<String, Object>() {
                @Override
                public void onFailure(KafkaProducerException e) {
                    e.printStackTrace();
                }

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    statistic.setStatus(true);
                    statisticRepo.save(statistic);
                    log.info("SEND SUCCESS");
                }
            });
        }
    }

    @Scheduled(fixedDelay = 2000)
    public void delete() {
        log.info("START DELETING ...");
        List<MessageDTO> messages = messageRepo.findAllByStatus(true);
        messageRepo.deleteAll(messages);

        List<StatisticDTO> statistics = statisticRepo.findAllByStatus(true);
        statisticRepo.deleteAll(statistics);
    }
}
