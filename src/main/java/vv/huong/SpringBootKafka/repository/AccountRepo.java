package vv.huong.SpringBootKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vv.huong.SpringBootKafka.model.AccountDTO;

@Repository
public interface AccountRepo extends JpaRepository<AccountDTO, Integer> {
}
