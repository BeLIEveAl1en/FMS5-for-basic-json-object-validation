package com.artem.validator;

public class StateMachine {
    private char[] str = new char[0];
    private int position = 0;
    private final State state = new State(0);

    public ValidationResult validate(String inputStr){
        str = inputStr.toCharArray();
        ValidationResult result;
        for (position = 0; position < str.length; position++){
            if (!validationSymbol())
                return ValidationResult.unexpectedSymbol(str[position], position);
        }
        if (state.getState() == 5){
            result = ValidationResult.valid();
        }
        else {
            result = ValidationResult.unexpectedEOF();
        }
        return result;
    }

    //  21/700+485
    public boolean validationSymbol() {
        char symbol = str[position];
        switch (state.getState()){
            case 0 :
                if(symbol == '"'){
                    state.setState(1);
                }
                else {
                    return false;
                }
                return true;

            case 1 :
                if (symbol == '"'){
                    state.setState(2);
                }
                else if(Character.isDigit(symbol) || Character.isAlphabetic(symbol) || Character.isWhitespace(symbol)){
                    state.setState(1);
                }
                else {
                    return false;
                }
                return true;

            case 2 :
                if (symbol == ':'){
                    state.setState(3);
                }
                else if(!Character.isWhitespace(symbol)){
                    return false;
                }
                return true;

            case 3 :
                if (symbol == '"'){
                    state.setState(4);
                }
                else if(!Character.isWhitespace(symbol)){
                    return false;
                }
                return true;

            case 4 :
                if (symbol == '"'){
                    state.setState(5);
                }
                else if(Character.isDigit(symbol) || Character.isAlphabetic(symbol) || Character.isWhitespace(symbol)){
                    state.setState(4);
                }
                else {
                    return false;
                }
                return true;

            case 5 :
                 if (symbol == ','){
                    return true;
                 }
                 else if (Character.isDigit(symbol) || Character.isAlphabetic(symbol)){
                        return false;
                 }
                 return true;
        }
        return false;
    }
}
