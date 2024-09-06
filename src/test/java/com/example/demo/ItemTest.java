package com.example.demo;

import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    public void itemSave() throws Exception{
        //given
        Item item = new Item();
        item.setName("아이폰");

        //when
        itemService.saveItem(item);

        //then
        assertEquals(item, itemService.findOne(item.getId()));
    }
}
