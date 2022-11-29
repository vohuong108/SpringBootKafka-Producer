package vv.huong.SpringBootKafka.model;

import lombok.Data;

import java.util.Date;

@Data
public class StatisticDTO {
    private String message;
    private String createdDate;

    public StatisticDTO(String message, Date createdDate) {
        this.message = message;
        this.createdDate = createdDate.toString();
    }
}
