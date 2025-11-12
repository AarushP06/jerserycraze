package com.champsoft.jerserycrazedatabase.dataaccess.repository;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JerseyRepository extends JpaRepository<Jersey, Long> {
    List<Jersey> findByCustomerId(Long customerId);
}
