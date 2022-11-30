package vv.huong.SpringBootKafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vv.huong.SpringBootKafka.model.AccountDTO;
import vv.huong.SpringBootKafka.model.MessageDTO;
import vv.huong.SpringBootKafka.model.StatisticDTO;
import vv.huong.SpringBootKafka.repository.AccountRepo;
import vv.huong.SpringBootKafka.repository.MessageRepo;
import vv.huong.SpringBootKafka.repository.StatisticRepo;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final MessageRepo messageRepo;
    private final AccountRepo accountRepo;
    private final StatisticRepo statisticRepo;

    public AccountController(MessageRepo messageRepo, AccountRepo accountRepo, StatisticRepo statisticRepo) {
        this.messageRepo = messageRepo;
        this.accountRepo = accountRepo;
        this.statisticRepo = statisticRepo;
    }

    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO account) {
        StatisticDTO stat = new StatisticDTO("Account " + account.getEmail() + " is created", new Date());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(account.getEmail());
        messageDTO.setToName(account.getName());
        messageDTO.setSubject("Welcome to kafka");
        messageDTO.setContent("Spring boot application with kafka");

        accountRepo.save(account);
        messageRepo.save(messageDTO);
        statisticRepo.save(stat);

        return account;
    }
}
