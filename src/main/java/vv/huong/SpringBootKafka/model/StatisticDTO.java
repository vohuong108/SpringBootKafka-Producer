package vv.huong.SpringBootKafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class StatisticDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private String createdDate;
    private Boolean status = Boolean.FALSE;

    public StatisticDTO(String message, Date createdDate) {
        this.message = message;
        this.createdDate = createdDate.toString();
    }
}
