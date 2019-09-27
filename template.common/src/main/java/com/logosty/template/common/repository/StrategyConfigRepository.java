package com.logosty.template.common.repository;

import com.logosty.template.common.entity.StrategyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/23 11:53
 */
@Repository
public interface StrategyConfigRepository extends JpaRepository<StrategyConfig, Long> {

  @Query(value = "select max(version) from " + StrategyConfig.TABLE_NAME, nativeQuery = true)
  Integer getMaxVersion();
}
