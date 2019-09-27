package com.logosty.template.common.entity;

import com.logosty.template.common.model.App;
import com.logosty.template.common.util.StringListToStringConverter;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * @date 2019/9/23 11:40
 */
@Data
@Entity
@Table(name = StrategyConfig.TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class StrategyConfig {

  public static final String TABLE_NAME = "config";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column(columnDefinition = "DATETIME", updatable = false)
  private Date creation;

  @UpdateTimestamp
  @Column(columnDefinition = "DATETIME")
  private Date modification;

  @Column(name = "[condition]") //系统保留字
  private String condition;

  private String source;

  private String filter;

  private String booster;

  private int postRanker;

  private int sageId;

  private int version;

  private int priority;

  private int highConversionSageId;

  //Not used temporarily
  @Enumerated(value = EnumType.STRING)
  private App app;

  @Convert(converter = StringListToStringConverter.class)
  private List<String> regions;

}
