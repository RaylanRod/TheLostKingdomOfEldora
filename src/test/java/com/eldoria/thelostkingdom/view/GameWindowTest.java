package com.eldoria.thelostkingdom.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

//PRESERVE TEST - SQUARETEST W/ MOCKITO PLUG-INS
class GameWindowTest {

    private GameWindow gameWindowUnderTest;

    @BeforeEach
    void setUp() {
        gameWindowUnderTest = new GameWindow();
    }

//    @Test
//    void testUpdateInventoryPanel() {
//        gameWindowUnderTest.updateInventoryPanel();
//    }

    @Test
    void testRemoveTitlePanel() {
        final GameWindow gameWindow = new GameWindow();
        GameWindow.removeTitlePanel(gameWindow);
    }

//    @Test
//    void testAddDialogueText() {
//        gameWindowUnderTest.addDialogueText("fileName");
//    }

    @Test
    void testGetInstance() {
        final GameWindow result = GameWindow.getInstance();
    }

    @Test
    void testGetMainContainer() {
        final Container result = gameWindowUnderTest.getMainContainer();
    }

    @Test
    void testProcessUserInput() {
        gameWindowUnderTest.processUserInput("input");
    }
}