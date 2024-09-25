package co.edu.udea.securecheck.adapter.driven.jpa.repository;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CustomQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomQuestionRepository extends JpaRepository<CustomQuestionEntity, Long> {
    List<CustomQuestionEntity> findAllByControlId(Long controlId);
}
