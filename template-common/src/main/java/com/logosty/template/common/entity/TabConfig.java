package com.logosty.template.common.entity;

import com.logosty.template.common.model.App;
import com.logosty.template.common.util.StringListToStringConverter;
import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author logosty(ganyingle)
 * @date 2019-08-09 16:19 description: 客户端 tab 配置的表
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TabConfig.TABLE_NAME)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TabConfig implements Serializable {

  public static final String TABLE_NAME = "tabConfig";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "VARCHAR(128) DEFAULT ''", nullable = false)
  private String name;

  @Column(columnDefinition = "VARCHAR(128) DEFAULT ''", nullable = false)
  private String localName;

  @Column(columnDefinition = "VARCHAR(128) DEFAULT ''", nullable = false)
  private String category;

  @Convert(converter = StringListToStringConverter.class)
  @Column(columnDefinition = "TEXT")
  private List<String> uniCategories;

  @Column(columnDefinition = "VARCHAR(128) DEFAULT ''", nullable = false)
  private String region;

  @Enumerated(value = EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(25) ", nullable = false)
  private App app;

  @Enumerated(value = EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(128) DEFAULT 'OFFLINE'", nullable = false)
  private Status status;

  @Column(columnDefinition = "INT DEFAULT 0")
  private int position;

  @Column(columnDefinition = "INT DEFAULT 0")
  private int version;

  @CreationTimestamp
  @Column(columnDefinition = "DATETIME")
  private Date creation;

  @UpdateTimestamp
  @Column(columnDefinition = "DATETIME")
  private Date modification;

  @Column(columnDefinition = "VARCHAR(128) DEFAULT ''")
  private String creator;

  public enum Status {
    ONLINE,
    OFFLINE,
    TESTING,
    HIDDEN
  }
}
