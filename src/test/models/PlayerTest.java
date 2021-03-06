package models;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private Player player;
    Player p1 = mock(Player.class);
    ArrayList<Card> mockHandList;
    ArrayList<Card> mockHandList2;
    ArrayList<Card> mockTableList;
    GameEngine gameEngine;

    @Mock
    Card cardMock;

    @Mock
    CreatureCard creatureCardMock;

    @BeforeEach
    void setup(){

        player = new Player();
        mockHandList = new ArrayList<Card>(Arrays.asList(new Card(1,1,2,"c1", "basic", ""),
                new Card(1,2,2,"c2","basic", ""),
                new Card(1,2,1,"c3","basic", ""),
                new Card(1,3,2,"c4", "basic", "")));
        mockHandList2 = new ArrayList<Card>();
        mockTableList = new ArrayList<Card>();
        mockTableList.add(mockHandList.get(1));
        gameEngine = new GameEngine();
    }

    @Test
    void testIncreaseHealth() {
        int expected=20;
        int actual=player.getHealth();
        assertEquals(expected,actual);
    }

    @Test
    void playCard() {
        int playCardNr = 1;

        when(p1.getPlayerHand()).thenReturn(mockHandList).thenReturn(mockHandList).thenReturn(mockHandList2);
        when(p1.getTableCards()).thenReturn(mockTableList);

        Card playCard = (Card) p1.getPlayerHand().get(playCardNr);

        p1.playCard(mockHandList.get(mockHandList.indexOf(playCard)),1);

        assertEquals(4, p1.getPlayerHand().size());
        assertEquals(1, p1.getTableCards().size());
        assertFalse(p1.getPlayerHand().contains(playCard));
        assertTrue(p1.getTableCards().contains(playCard));
    }

    @Test
    void pickupCard() {

        player.getCurrentDeck().add(new Card(1,2,2,"c","basic", ""));

        int cdSize = player.getCurrentDeck().size();
        int phSize = player.getPlayerHand().size();

        player.pickupCard();

        assertEquals(cdSize - 1, player.getCurrentDeck().size());
        assertEquals(phSize + 1, player.getPlayerHand().size());
    }

    @Test
    void sendToGraveyard() {

        player.getTableCards().add(cardMock);
        int size = player.getTableCards().size();

        assertTrue(player.getTableCards().contains(cardMock));

        player.sendToGraveyard(cardMock);

        assertEquals(size - 1, player.getTableCards().size());
        assertFalse(player.getTableCards().contains(cardMock));
    }

    @DisplayName("testing remove player health ")
    @Test
    void testRemovePlayerHealth() {
        int healthToremove = 2;
        int expected = player.getHealth() - healthToremove;
        player.removeHp(2);
        assertEquals(expected, player.getHealth());

    }

    @Test
    void decreaseEnergyOnPlayCard(){
        int energy = 3;
        when(creatureCardMock.getCardEnergy()).thenReturn(2);
        player.setPlayerEnergy(energy);
        player.playCard(creatureCardMock,2);
        assertEquals(energy - creatureCardMock.getCardEnergy(), player.getPlayerEnergy());
    }

    @Test
    void notEnoughEnergyToPlayCard(){
        int playerEnergy = 3;
        int cardEnergy = 5;
        int handSize = player.getPlayerHand().size();
        when(creatureCardMock.getCardEnergy()).thenReturn(cardEnergy);
        player.setPlayerEnergy(playerEnergy);
        player.playCard(creatureCardMock, 2);
        assertEquals(playerEnergy, player.getPlayerEnergy());
        assertEquals(handSize, player.getPlayerHand().size());
    }

}