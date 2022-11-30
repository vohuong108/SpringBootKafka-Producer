package vv.huong.SpringBootKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vv.huong.SpringBootKafka.model.MessageDTO;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<MessageDTO, Integer> {
    List<MessageDTO> findAllByStatus(Boolean status);
}
