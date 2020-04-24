package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {

        //Given
        String trelloApiEndpoint = "https://api.trello.com/1";
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(trelloApiEndpoint);

        //When
        String expectedApiEndpoint = trelloConfig.getTrelloApiEndpoint();

        //Then
        Assert.assertEquals(trelloApiEndpoint, expectedApiEndpoint);
    }

    @Test
    public void testGetTrelloAppKey() {
        //Given
        String trelloAppKey = "15ee15ee5073e5e310e9d40ea4a65f43";
        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);

        //When
        String expectedTrelloAppKey = trelloConfig.getTrelloAppKey();

        //Then
        Assert.assertEquals(trelloAppKey, expectedTrelloAppKey);
    }

    @Test
    public void testGetTrelloToken() {
        //Given
        String trelloToken = "c716d8ebbc5545d366358fd117fbc18399e26b3ed546f62c7be16fbc2e0a648b";
        when(trelloConfig.getTrelloToken()).thenReturn(trelloToken);

        //When
        String expectedTrelloToken = trelloConfig.getTrelloToken();

        //Then
        Assert.assertEquals(trelloToken, expectedTrelloToken);
    }

    @Test
    public void testGetTrelloUsername() {
        //Given
        String trelloUsername = "marcinchudy3";
        when(trelloConfig.getTrelloUsername()).thenReturn(trelloUsername);

        //When
        String expectedTrelloUsername = trelloConfig.getTrelloUsername();

        //Then
        Assert.assertEquals(trelloUsername, expectedTrelloUsername);
    }
}

