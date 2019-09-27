package com.logosty.template.common.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/23 11:54
 */
@Data
@Entity
@Table(name = SageConfig.TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class SageConfig {

  public static final String TABLE_NAME = "sageConfig";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column(columnDefinition = "DATETIME", updatable = false)
  private Date creation;

  @UpdateTimestamp
  @Column(columnDefinition = "DATETIME")
  private Date modification;

  private String classFile;

  private String className;

  private String dictFile;

  private String endpointName;

  private int version;

  private int status;
}
