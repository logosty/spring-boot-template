package com.logosty.template.common.mapper;

import com.logosty.template.common.dto.TabConfigDTO;
import com.logosty.template.common.entity.TabConfig;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 14:38
 */

@Mapper(componentModel = "spring")
public interface TabConfigMapper extends BaseMapper {

  @Mappings(value = {
      @Mapping(target = "creation", expression = "java(dateToLong(source.getCreation()))"),
      @Mapping(target = "modification", ignore = true),
  })
  TabConfigDTO toTabConfigDTO(TabConfig source);

  List<TabConfigDTO> toTabConfigDTO(List<TabConfig> source);
}
