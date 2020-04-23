package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("14", "List1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("16", "List2", false);
        List<TrelloListDto> trelloLists = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Board1", trelloLists);
        List<TrelloBoardDto> trelloBoardListDto = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardListDto);

        //Then
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals(2, trelloBoardList.get(0).getLists().size());
        Assert.assertEquals("14", trelloBoardList.get(0).getLists().get(0).getId());
        Assert.assertEquals("List2", trelloBoardList.get(0).getLists().get(1).getName());
        Assert.assertFalse(trelloBoardList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToBoardDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("10", "name1", true);
        TrelloList trelloList2 = new TrelloList("11", "name2", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board1", trelloLists);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardDto(trelloBoardList);

        //Then
        Assert.assertEquals(1, trelloBoardDtoList.size());
        Assert.assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        Assert.assertEquals("10", trelloBoardDtoList.get(0).getLists().get(0).getId());
        Assert.assertEquals("name2", trelloBoardDtoList.get(0).getLists().get(1).getName());
        Assert.assertTrue(trelloBoardDtoList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("10", "name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("11", "name2", false);
        List<TrelloListDto> trelloListDto = Arrays.asList(trelloListDto1, trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);

        //Then
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("10", trelloLists.get(0).getId());
        Assert.assertEquals("name2", trelloLists.get(1).getName());
        Assert.assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "name1", true);
        TrelloList trelloList2 = new TrelloList("2", "name2", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(2, trelloListDto.size());
        Assert.assertEquals("1", trelloListDto.get(0).getId());
        Assert.assertEquals("name2", trelloListDto.get(1).getName());
        Assert.assertTrue(trelloListDto.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "top", "listId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("name", trelloCardDto.getName());
        Assert.assertEquals("description", trelloCardDto.getDescription());
        Assert.assertEquals("top", trelloCardDto.getPos());
        Assert.assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "mid", "listId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("name", trelloCardDto.getName());
        Assert.assertEquals("description", trelloCardDto.getDescription());
        Assert.assertEquals("mid", trelloCardDto.getPos());
        Assert.assertEquals("listId", trelloCardDto.getListId());
    }
}
