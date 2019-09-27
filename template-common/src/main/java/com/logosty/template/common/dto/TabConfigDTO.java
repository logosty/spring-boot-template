package com.logosty.template.common.dto;

import com.logosty.template.common.entity.TabConfig.Status;
import com.logosty.template.common.model.App;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 14:36
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabConfigDTO {

  private Long id;

  private String name;

  private String localName;

  private String category;

  private List<String> uniCategories;

  private String region;

  private App app;

  private Status status;

  private int position;

  private int version;

  private Long creation;

  private Long modification;

  private String creator;
}
