package com.logosty.template.common.repository;

import static org.springframework.data.jpa.domain.Specification.where;

import com.logosty.template.common.entity.TabConfig;
import com.logosty.template.common.entity.TabConfig.Status;
import com.logosty.template.common.model.App;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * @author logosty(ganyingle)
 * @date 2019-08-12 10:33
 */
@Repository
public interface TabConfigRepository extends CrudRepository<TabConfig, Long>,
    JpaSpecificationExecutor<TabConfig> {

  static Specification<TabConfig> regionIn(List<String> regions) {
    return (root, query, cb) -> CollectionUtils.isEmpty(regions) ? cb.and()
        : cb.and(root.get("region").in(regions));
  }

  static Specification<TabConfig> status(Status status) {
    return (root, query, cb) -> status == null ? cb.and() : cb.equal(root.get("status"), status);
  }

  static Specification<TabConfig> app(App app) {
    return (root, query, cb) -> app == null ? cb.and() : cb.equal(root.get("app"), app);
  }

  @Query(value = "select max(version) from " + TabConfig.TABLE_NAME, nativeQuery = true)
  Integer getMaxVersion();

  default List<TabConfig> findTabConfigs(List<String> regions, Status status,
      App app) {

    return this
        .findAll(where(regionIn(regions)).and(status(status).and(app(app))),
            Sort.by("position"));
  }
}
