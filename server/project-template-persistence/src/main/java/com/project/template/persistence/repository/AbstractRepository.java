package com.project.template.persistence.repository;

import com.project.template.persistence.entity.AuditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * @author Pravesh Shashiraj Boodhoo
 */
@NoRepositoryBean
public interface AbstractRepository<T extends AuditModel> extends JpaRepository<T, UUID>, QuerydslPredicateExecutor<T> {

}