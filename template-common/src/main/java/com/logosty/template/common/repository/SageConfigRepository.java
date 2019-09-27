package com.logosty.template.common.repository;

import com.logosty.template.common.entity.SageConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/23 11:57
 */
@Repository
public interface SageConfigRepository extends JpaRepository<SageConfig, Long> {

  @Query(value = "select max(version) from " + SageConfig.TABLE_NAME, nativeQuery = true)
  Integer getMaxVersion();
}
