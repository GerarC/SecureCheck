package co.edu.udea.securecheck.adapter.driven.jpa.repository;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AuditEntity;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
    List<AuditEntity> findAllByCompanyId(String companyId, Sort sort);
    Optional<AuditEntity> findFirstByCompanyIdAndState(String companyId, AuditState state);
}
