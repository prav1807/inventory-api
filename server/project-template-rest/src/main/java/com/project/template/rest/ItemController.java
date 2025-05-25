package com.project.template.rest;


import com.project.template.api.ItemApi;
import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;
import com.project.template.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

/**
 * @author Pravesh Shashiraj Boodhoo
 */
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<ItemResponseApiBean> createItem(@Valid ItemRequestApiBean itemRequestApiBean) {
        ItemResponseApiBean itemResponseApiBean = itemService.createItem(itemRequestApiBean);
       URI location = URI.create("/api/v1/item/" + itemResponseApiBean.getId());
        return ResponseEntity.created(location).body(itemResponseApiBean);
    }

    @Override
    public ResponseEntity<Void> deleteItem(UUID itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ItemResponseApiBean> getItemById(UUID itemId) {
        ItemResponseApiBean itemResponseApiBean = itemService.findItemById(itemId);
        return ResponseEntity.ok(itemResponseApiBean);
    }

    @Override
    public ResponseEntity<ItemResponseApiBean> updateItem(UUID itemId, @Valid ItemRequestApiBean itemRequestApiBean) {
        itemService.updateItem(itemId, itemRequestApiBean);
        ItemResponseApiBean itemResponseApiBean = itemService.findItemById(itemId);
        return ResponseEntity.ok(itemResponseApiBean);
    }
}