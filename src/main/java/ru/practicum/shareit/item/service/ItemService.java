package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDetailedDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Collection;

public interface ItemService {
    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto update(Long userId, Long itemId, ItemDto itemDto);

    ItemDetailedDto getByUserIdAndItemId(Long userId, Long itemId);

    Collection<ItemDetailedDto> getItemsUser(Long userId);

    Collection<ItemDto> getItemsOnRequest(String text);

    CommentDto addComment(Long userId, Long itemId, CommentDto commentDto);
}
