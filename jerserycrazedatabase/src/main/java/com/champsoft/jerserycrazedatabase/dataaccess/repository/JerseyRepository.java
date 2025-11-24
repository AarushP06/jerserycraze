package com.champsoft.jerserycrazedatabase.dataaccess.repository;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JerseyRepository extends JpaRepository<Jersey, Long> {
    Page<Jersey> findByNameContainingIgnoreCaseOrClubContainingIgnoreCase(String a, String b, Pageable pageable);
}