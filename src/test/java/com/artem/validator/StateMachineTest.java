package com.artem.validator;

import org.junit.Assert;
import org.junit.Test;

public class StateMachineTest {

    public void shouldPass(String str){
        StateMachine stateMachine = new StateMachine();

        ValidationResult result = stateMachine.validate(str);

        Assert.assertTrue(result.getComment(), result.isValid());
    }

    public void shouldFail(String str){
        StateMachine stateMachine = new StateMachine();

        ValidationResult result = stateMachine.validate(str);

        Assert.assertFalse(result.getComment(), result.isValid());
    }

    @Test
    public void shouldPassWithWhitespace(){
        shouldPass("{\" name \" : \" Вася \"}");
    }

    @Test
    public void shouldPassWithInteger(){
        shouldPass("{\" name \" : 55.5, \"old\" : 34}");
    }

    @Test
    public void shouldPassWithStringTrue(){
        shouldPass("{\"name\":true}");
    }

    @Test
    public void shouldPassWithStringNull(){
        shouldPass("{\"name\":null}");
    }

    @Test
    public void shouldPassWithStringFalse(){
        shouldPass("{\"name\":false}");
    }

    @Test
    public void shouldPassWithStringBrackets(){
        shouldPass("{\"name\":[thfdth],\"name\":{drgdrg}}");
    }

    @Test
    public void shouldPassWithTwoObj(){
        shouldPass("{\"name\":true," +
                       "\"lastName\":\"fff\"" +
                        "}");
    }

    @Test
    public void shouldFailWithExtraLetter(){
        shouldFail("{\"name\"f:\"Вася\"}");
    }

    @Test
    public void shouldFailWithTwoColon(){
        shouldFail("{\"name\"::\"Вася\"}");
    }

    @Test
    public void shouldFailWithExtraQuotes(){
        shouldFail("{\"\"name\"\":\"Вася\"}");
    }
}

