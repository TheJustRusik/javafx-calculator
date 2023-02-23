package com.example.calcproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HelloController {

    @FXML
    private Label lineLabel;
    @FXML
    private Button b0;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button bDot;
    @FXML
    private Button bErase;
    @FXML
    private Button bClear;
    @FXML
    private Button bEqual;
    @FXML
    private Button bMinus;
    @FXML
    private Button bPlus;
    @FXML
    private Button bDivide;
    @FXML
    private Button bMtply;
    @FXML
    private Button bPower;
    @FXML
    private Button bSqrt;

    static boolean isFirstNum;
    static boolean haveDot;
    static String firstNum;
    static String secondNum;
    static char operator;

    private Map<Button, Buttons> buttonMap = new HashMap<>();
    @FXML
    protected void fnNum(String c){
        if(isFirstNum)
            firstNum += c;
        else
            secondNum += c;
        lineLabel.setText(lineLabel.getText() + c);
    }
    @FXML
    protected void calc(){
        isFirstNum = true;
        haveDot = true;
        if(Objects.equals(firstNum, ""))
            firstNum = "0";
        try{
            switch (operator){
                case '+' -> firstNum = String.valueOf(Double.valueOf(firstNum) + Double.valueOf(secondNum));
                case '-' -> firstNum = String.valueOf(Double.valueOf(firstNum) - Double.valueOf(secondNum));
                case '*' -> firstNum = String.valueOf(Double.valueOf(firstNum) * Double.valueOf(secondNum));
                case '/' -> firstNum = String.valueOf(Double.valueOf(firstNum) / Double.valueOf(secondNum));
                case '^' -> firstNum = String.valueOf(Math.pow(Double.valueOf(firstNum), Double.valueOf(secondNum)));
            }

            lineLabel.setText(firstNum);
        }catch (Exception e){
            System.err.println(e.getMessage());
            firstNum = "";
            haveDot = false;
            lineLabel.setText("");
        }
        secondNum = "";
        operator = ' ';
    }
    @FXML
    protected void fnOper(char c){
        if(operator != ' '){
            calc();
        }
        operator = c;
        isFirstNum = false;
        haveDot = false;
        lineLabel.setText(lineLabel.getText() + c);
    }
    public void initialize(){
        isFirstNum = true;
        haveDot = false;
        operator = ' ';
        firstNum = "";
        secondNum = "";

        buttonMap.put(b0, Buttons.B0);
        buttonMap.put(b1, Buttons.B1);
        buttonMap.put(b2, Buttons.B2);
        buttonMap.put(b3, Buttons.B3);
        buttonMap.put(b4, Buttons.B4);
        buttonMap.put(b5, Buttons.B5);
        buttonMap.put(b6, Buttons.B6);
        buttonMap.put(b7, Buttons.B7);
        buttonMap.put(b8, Buttons.B8);
        buttonMap.put(b9, Buttons.B9);
        buttonMap.put(bPlus, Buttons.Bplus);
        buttonMap.put(bMinus, Buttons.Bminus);
        buttonMap.put(bMtply, Buttons.Bmtply);
        buttonMap.put(bDivide, Buttons.Bdivide);
        buttonMap.put(bPower, Buttons.Bpower);
    }
    @FXML
    protected void onNumButtonClick(ActionEvent event) {
        Buttons buttonId = buttonMap.get(event.getSource());
        switch (buttonId) {
            case B0 -> fnNum("0");
            case B1 -> fnNum("1");
            case B2 -> fnNum("2");
            case B3 -> fnNum("3");
            case B4 -> fnNum("4");
            case B5 -> fnNum("5");
            case B6 -> fnNum("6");
            case B7 -> fnNum("7");
            case B8 -> fnNum("8");
            case B9 -> fnNum("9");
        }
    }
    @FXML
    protected void onOperatorClick(ActionEvent event){
        Buttons buttonId = buttonMap.get(event.getSource());
        switch (buttonId){
            case Bplus -> fnOper('+');
            case Bminus -> fnOper('-');
            case Bmtply -> fnOper('*');
            case Bdivide -> fnOper('/');
            case Bpower -> fnOper('^');
        }
    }
    @FXML
    protected void onEraseClick(){
        if(isFirstNum){
            if(firstNum.length() > 0) {
                if (firstNum.charAt(firstNum.length() - 1) == '.')
                    haveDot = false;
                firstNum = firstNum.substring(0, firstNum.length() - 1);
                lineLabel.setText(firstNum);
            }
        }else{
            if(secondNum.length() > 0) {
                if (secondNum.charAt(secondNum.length() - 1) == '.')
                    haveDot = false;
                secondNum = secondNum.substring(0, secondNum.length() - 1);
                lineLabel.setText(firstNum + operator + secondNum);
            }
        }
    }
    @FXML
    protected void onClearClick(){
        lineLabel.setText("");
        isFirstNum = true;
        haveDot = false;
        operator = ' ';
        firstNum = "";
        secondNum = "";
    }
    @FXML
    protected void onEqual(){
        calc();
    }
    @FXML
    protected void onDotClick(){
        if(!haveDot) {
            lineLabel.setText(lineLabel.getText() + ".");
            haveDot = true;
            if (isFirstNum)
                firstNum += ".";
            else
                secondNum += ".";
        }
    }
    @FXML
    protected void onSqrtClick(){
        if(isFirstNum){
            firstNum = String.valueOf(Math.pow(Double.parseDouble(firstNum), 0.5f));
            lineLabel.setText(firstNum);
        }else{
            secondNum = String.valueOf(Math.pow(Double.parseDouble(secondNum), 0.5f));
            lineLabel.setText(firstNum + operator + secondNum);
        }

    }
}