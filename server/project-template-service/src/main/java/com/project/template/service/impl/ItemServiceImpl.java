package com.project.template.service.impl;

import com.project.template.exception.ResourceNotFoundException;
import com.project.template.mapper.ItemMapper;
import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;
import com.project.template.persistence.entity.ItemEntity;
import com.project.template.persistence.repository.ItemRepository;
import com.project.template.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Pravesh Shashiraj Boodhoo
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private static final String ITEM_ID_NOT_FOUND = "ItemId: %s not found";

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    @Override
    public ItemResponseApiBean createItem(ItemRequestApiBean createItemRequest) {
        ItemEntity item = itemMapper.mapToItemEntity(createItemRequest);
        item = itemRepository.save(item);
        return itemMapper.mapToItemResponse(item);

    }

    @Transactional
    @Override
    public void updateItem(UUID itemId, ItemRequestApiBean itemUpdateRequest) {
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ITEM_ID_NOT_FOUND, itemId)));
        itemMapper.mapToUpdateItemEntity(item, itemUpdateRequest);
        itemRepository.save(item);
    }

    @Transactional
    @Override
    public void deleteItem(UUID itemId) {
        itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ITEM_ID_NOT_FOUND, itemId)));
        itemRepository.deleteById(itemId);
    }

    @Override
    public ItemResponseApiBean findItemById(UUID itemId) {
        return itemRepository.findById(itemId)
                .map(itemMapper::mapToItemResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ITEM_ID_NOT_FOUND, itemId)));
    }
}