package vv.huong.SpringBootKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vv.huong.SpringBootKafka.model.AccountDTO;
import vv.huong.SpringBootKafka.model.MessageDTO;
import vv.huong.SpringBootKafka.model.StatisticDTO;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO account) {
        StatisticDTO stat = new StatisticDTO("Account " + account.getEmail() + " is created", new Date());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(account.getName());
        messageDTO.setToName(account.getName());
        messageDTO.setSubject("Welcome to kafka");
        messageDTO.setContent("Spring boot application with kafka");

        kafkaTemplate.send("notification", messageDTO);
        kafkaTemplate.send("statistic", stat);

        return account;
    }
}
