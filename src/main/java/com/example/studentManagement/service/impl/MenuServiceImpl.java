package com.example.studentManagement.service.impl;

import com.example.studentManagement.entity.Menu;
import com.example.studentManagement.entity.MenuItem;
import com.example.studentManagement.repository.MenuItemRepository;
import com.example.studentManagement.repository.MenuRepository;
import com.example.studentManagement.requestPayload.MenuRequestPayload;
import com.example.studentManagement.responsePayloads.MenuItemResponsePayload;
import com.example.studentManagement.responsePayloads.MenuResponsePayload;
import com.example.studentManagement.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public void createMenu(MenuRequestPayload menuRequestPayload) {

        try {
            MenuResponsePayload menu = new MenuResponsePayload(menuRepository.save(new Menu(menuRequestPayload)));
            menuRequestPayload.getMenuItems().forEach(menuItem -> {
                menuItem.setMenuId(menu.id);
                new MenuItemResponsePayload(menuItemRepository.save(new MenuItem(menuItem)));
            });
            log.error("create menu successful with menu id : {}", menu.id);
        } catch (Exception e) {
            log.error("exception occurs while creating menu");
            throw new RuntimeException();
        }

    }

    @Override
    public MenuResponsePayload getMenu(Long restaurantId) {

        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        menu.setMenuItems(menuItemRepository.findAllByMenuId(menu.id));
        log.error("get menu by restaurant id : {}",restaurantId);
        return new MenuResponsePayload(menu);

    }

    @Override
    public MenuItemResponsePayload updateMenuItemPrice(Long itemId, BigDecimal price) {

        try {
            Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);
            if (menuItem.isEmpty()) {
                log.error("menu item not found with id : {}",itemId);
                throw new RuntimeException("menu item not found with id : " + itemId);
            }
            menuItem.get().setPrice(price);
            menuItemRepository.save(menuItem.get());
            log.debug("save menu item with id : {}",itemId);
            return new MenuItemResponsePayload(menuItem.get());
        } catch (Exception e) {
            throw new RuntimeException("menu item not found with id : " + itemId);
        }
    }
}
