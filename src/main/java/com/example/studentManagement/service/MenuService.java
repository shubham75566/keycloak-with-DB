package com.example.studentManagement.service;

import com.example.studentManagement.requestPayload.MenuRequestPayload;
import com.example.studentManagement.responsePayloads.MenuItemResponsePayload;
import com.example.studentManagement.responsePayloads.MenuResponsePayload;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

public interface MenuService {
    public void createMenu(MenuRequestPayload menuRequestPayload);

    public MenuResponsePayload getMenu(Long restaurantId);

    public MenuItemResponsePayload updateMenuItemPrice(Long itemId, BigDecimal price);

}
