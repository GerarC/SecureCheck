package co.edu.udea.securecheck.adapter.driven.jpa.repository;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity, Long> {
    boolean existsById(Long id);
}
