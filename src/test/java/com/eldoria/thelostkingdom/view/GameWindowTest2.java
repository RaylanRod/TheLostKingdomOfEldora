//package com.eldoria.thelostkingdom.view;
//
//import com.eldoria.thelostkingdom.Main;
//import com.eldoria.thelostkingdom.gamelogic.GameMethods;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import javax.swing.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GameWindowTest2 {
//    private GameWindow game;
//    private JTextArea textArea;
//    private JTextField inputField;
//
//    @BeforeEach
//    public void setup() {
//        game = new GameWindow(); // or however you instantiate this
//        textArea = new JTextArea();
//        inputField = new JTextField();
////        game.setTextArea(textArea); // Assuming you have a setter for textArea
//        game.setInputField(inputField); // Assuming you have a setter for inputField
//    }
//
//    @Test
//    public void testProcessInput_SaveCommand() {
//        game.processUserInput("save");
//        assertTrue(textArea.getText().contains("Enter a file name: > "));
//    }
//
//    @Test
//    public void testProcessInput_UnknownSingleWordCommand() {
//        game.processUserInput("dance");
//        assertTrue(textArea.getText().contains("Unknown command:"));
//    }
//
//    @Test
//    public void testProcessInput_RiddleCorrectAnswer() {
//        Main.isExpectingRiddleAnswer = true;
//        Main.currentRiddleAnswer = "sword";
//        game.processUserInput("sword");
//        assertTrue(textArea.getText().contains("correct"));
//    }
//
//    @Test
//    public void testProcessInput_RiddleWrongAnswer() {
//        Main.isExpectingRiddleAnswer = true;
//        Main.currentRiddleAnswer = "sword";
//        game.processUserInput("shield");
//        assertTrue(textArea.getText().contains("wrong answer"));
//    }
//}
//
