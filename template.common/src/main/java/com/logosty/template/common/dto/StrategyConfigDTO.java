package com.logosty.template.common.dto;

import com.logosty.template.common.model.App;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 14:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyConfigDTO {

  private Long id;

  private Long creation;

  private Long modification;

  private String condition;

  private String source;

  private String filter;

  private String booster;

  private int postRanker;

  private int version;

  private int priority;

  private int highConversionSageId;

  //Not used temporarily
  private App app;

  private List<String> regions;

  //sage
  private int sageId;

  private String classFile;

  private String className;

  private String dictFile;

  private String endpointName;

  private int status;
}
