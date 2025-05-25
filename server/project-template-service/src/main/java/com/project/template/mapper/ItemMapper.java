package com.project.template.mapper;

import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;
import com.project.template.persistence.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Pravesh Shashiraj Boodhoo
 */
@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    ItemEntity mapToItemEntity(ItemRequestApiBean createItemRequest);

    ItemRequestApiBean mapToItemCreateOrUpdateRequest(ItemEntity item);

    ItemResponseApiBean mapToItemResponse(ItemEntity item);

    void mapToUpdateItemEntity(@MappingTarget ItemEntity user, ItemRequestApiBean itemUpdateRequest);

}