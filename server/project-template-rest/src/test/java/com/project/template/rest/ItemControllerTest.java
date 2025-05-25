package com.project.template.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.template.exception.ResourceNotFoundException;
import com.project.template.model.ItemRequestApiBean;
import com.project.template.model.ItemResponseApiBean;
import com.project.template.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest /** Loads a Spring context for testing and configure MockMvc without running a real server ... */
@AutoConfigureMockMvc
class ItemControllerContractTest {

    @Autowired
    private MockMvc mockMvc; /** Use to send fake HTTP requests to the controllers ... */

    @Autowired
    private ObjectMapper objectMapper; /** Use to parse JSON files into Java objects ... */

    /** Defines a mock version of ItemService,
    so that we don’t depend on real database or service logic during testing.
    It ensures isolation of the controller logic ... */
    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public ItemService itemService() {
            return mock(ItemService.class);
        }
    }

    @Autowired
    private ItemService itemService;

    @BeforeEach // Resets all mocks before each test to avoid side effects ...
    void setUp() {
        reset(itemService);
    }

    private final UUID TEST_ITEM_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    // Creating an item using valid and invalid Request Body ...

    @Test
    void createItem_validRequestBody_return201Created() throws Exception {

        String requestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/test-data/valid-request-body.json")));
        ItemResponseApiBean response = objectMapper.readValue(
                new File("src/test/resources/test-data/valid-response-body.json"),
                ItemResponseApiBean.class);

        when(itemService.createItem(any(ItemRequestApiBean.class))).thenReturn(response);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/item/" + TEST_ITEM_ID))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.quantity").exists())
                .andExpect(jsonPath("$.quantity").isNumber())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.price").isNumber());

        verify(itemService).createItem(any(ItemRequestApiBean.class));
    }

    @Test
    void createItem_invalidRequestBody_return400BadRequest() throws Exception {

        String requestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/test-data/invalid-request-body.json")));

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

    }

    // Get an item with valid and invalid response ...

    @Test
    void getItem_validId_return200Ok() throws Exception {

        ItemResponseApiBean response = objectMapper.readValue(
                new File("src/test/resources/test-data/valid-response-body.json"),
                ItemResponseApiBean.class);
        when(itemService.findItemById(TEST_ITEM_ID)).thenReturn(response);

        mockMvc.perform(get("/api/items/{itemId}", TEST_ITEM_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.quantity").exists())
                .andExpect(jsonPath("$.quantity").isNumber())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.price").isNumber());

        verify(itemService).findItemById(TEST_ITEM_ID);
    }

    @Test
    void getItem_invalidId_return404NotFound() throws Exception {

        UUID nonExistentId = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        doThrow(new ResourceNotFoundException("Item not found with id: " + nonExistentId))
                .when(itemService).findItemById(nonExistentId);

        mockMvc.perform(get("/api/items/{itemId}", nonExistentId))
                .andExpect(status().isNotFound());

        verify(itemService).findItemById(nonExistentId);
    }

    @Test
    void updateItem_validRequest_return200Ok() throws Exception {

        String requestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/test-data/valid-request-body.json")));
        ItemResponseApiBean response = objectMapper.readValue(
                new File("src/test/resources/test-data/valid-response-body.json"),
                ItemResponseApiBean.class);

        doNothing().when(itemService).updateItem(eq(TEST_ITEM_ID), any(ItemRequestApiBean.class));
        when(itemService.findItemById(TEST_ITEM_ID)).thenReturn(response);

        mockMvc.perform(put("/api/items/{itemId}", TEST_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.quantity").exists())
                .andExpect(jsonPath("$.quantity").isNumber())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.price").isNumber());

        verify(itemService).updateItem(eq(TEST_ITEM_ID), any(ItemRequestApiBean.class));
        verify(itemService).findItemById(TEST_ITEM_ID);
    }

    @Test
    void updateItem_invalidRequest_return400BadRequest() throws Exception {

        String requestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/test-data/invalid-request-body.json")));

        mockMvc.perform(put("/api/items/{itemId}", TEST_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateItem_itemNotFound_return404NotFound() throws Exception {

        UUID nonExistentId = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        String requestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/test-data/valid-request-body.json")));

        doThrow(new ResourceNotFoundException("Item not found with id: " + nonExistentId))
                .when(itemService).updateItem(eq(nonExistentId), any(ItemRequestApiBean.class));

        mockMvc.perform(put("/api/items/{itemId}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNotFound());

        verify(itemService).updateItem(eq(nonExistentId), any(ItemRequestApiBean.class));
    }

    @Test
    void deleteItem_return204NoContent() throws Exception {

        doNothing().when(itemService).deleteItem(TEST_ITEM_ID);

        mockMvc.perform(delete("/api/items/{itemId}", TEST_ITEM_ID))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
        verify(itemService).deleteItem(TEST_ITEM_ID);
    }

    @Test
    void deleteItem_whenItemNotFound_return404NotFound() throws Exception {

        UUID nonExistentId = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        doThrow(new ResourceNotFoundException("Item not found with id: " + nonExistentId))
                .when(itemService).deleteItem(nonExistentId);

        mockMvc.perform(delete("/api/items/{itemId}", nonExistentId))
                .andExpect(status().isNotFound());

        verify(itemService).deleteItem(nonExistentId);
    }

}