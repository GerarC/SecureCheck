package co.edu.udea.securecheck.adapter.driven.jpa.repository;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findAllByAuditId(Long id);

    @Query("SELECT a FROM AnswerEntity a WHERE a.id IN :ids")
    List<AnswerEntity> findAllByIds(@Param("ids") List<Long> ids);
}
