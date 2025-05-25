package com.project.template.service;

import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;

import java.util.UUID;

/**
 * @author Pravesh Shashiraj Boodhoo
 */
public interface ItemService {

    ItemResponseApiBean createItem(ItemRequestApiBean createItemRequest);

    void updateItem(UUID itemId, ItemRequestApiBean itemUpdateRequest);

    void deleteItem(UUID itemId);

    ItemResponseApiBean findItemById(UUID itemId);

}
