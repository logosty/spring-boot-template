package com.logosty.template.common.mapper;

import com.logosty.template.common.dto.StrategyConfigDTO;
import com.logosty.template.common.entity.SageConfig;
import com.logosty.template.common.entity.StrategyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 15:00
 */
@Mapper(componentModel = "spring")
public interface StrategyConfigMapper extends BaseMapper {

  @Mappings(value = {
      @Mapping(target = "creation", expression = "java(dateToLong(strategy.getCreation()))"),
      @Mapping(target = "id", source = "strategy.id"),
      @Mapping(target = "version", source = "strategy.version"),
      @Mapping(target = "modification", ignore = true),
  })
  StrategyConfigDTO toStrategyConfigDTO(StrategyConfig strategy, SageConfig sage);

}
