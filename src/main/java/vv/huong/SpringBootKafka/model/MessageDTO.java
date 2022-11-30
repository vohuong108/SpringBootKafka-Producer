package vv.huong.SpringBootKafka.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_email")
    private String to;
    private String toName;
    private String subject;
    private String content;
    private Boolean status = Boolean.FALSE;
}
