package vv.huong.SpringBootKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vv.huong.SpringBootKafka.model.StatisticDTO;

import java.util.List;

@Repository
public interface StatisticRepo extends JpaRepository<StatisticDTO, Integer> {
    List<StatisticDTO> findAllByStatus(Boolean status);
}
