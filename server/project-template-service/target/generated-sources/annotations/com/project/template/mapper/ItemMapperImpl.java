package com.project.template.mapper;

import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;
import com.project.template.persistence.entity.ItemEntity;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-25T16:31:03+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (OpenLogic)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemEntity mapToItemEntity(ItemRequestApiBean createItemRequest) {
        if ( createItemRequest == null ) {
            return null;
        }

        ItemEntity.ItemEntityBuilder itemEntity = ItemEntity.builder();

        itemEntity.name( createItemRequest.getName() );
        itemEntity.quantity( createItemRequest.getQuantity() );
        if ( createItemRequest.getPrice() != null ) {
            itemEntity.price( BigDecimal.valueOf( createItemRequest.getPrice() ) );
        }

        return itemEntity.build();
    }

    @Override
    public ItemRequestApiBean mapToItemCreateOrUpdateRequest(ItemEntity item) {
        if ( item == null ) {
            return null;
        }

        ItemRequestApiBean itemRequestApiBean = new ItemRequestApiBean();

        itemRequestApiBean.setName( item.getName() );
        itemRequestApiBean.setQuantity( item.getQuantity() );
        if ( item.getPrice() != null ) {
            itemRequestApiBean.setPrice( item.getPrice().floatValue() );
        }

        return itemRequestApiBean;
    }

    @Override
    public ItemResponseApiBean mapToItemResponse(ItemEntity item) {
        if ( item == null ) {
            return null;
        }

        ItemResponseApiBean itemResponseApiBean = new ItemResponseApiBean();

        itemResponseApiBean.setId( item.getId() );
        itemResponseApiBean.setName( item.getName() );
        itemResponseApiBean.setQuantity( item.getQuantity() );
        if ( item.getPrice() != null ) {
            itemResponseApiBean.setPrice( item.getPrice().floatValue() );
        }

        return itemResponseApiBean;
    }

    @Override
    public void mapToUpdateItemEntity(ItemEntity user, ItemRequestApiBean itemUpdateRequest) {
        if ( itemUpdateRequest == null ) {
            return;
        }

        user.setName( itemUpdateRequest.getName() );
        user.setQuantity( itemUpdateRequest.getQuantity() );
        if ( itemUpdateRequest.getPrice() != null ) {
            user.setPrice( BigDecimal.valueOf( itemUpdateRequest.getPrice() ) );
        }
        else {
            user.setPrice( null );
        }
    }
}
