package com.artem.test;

import com.artem.validator.StateMachine;
import com.artem.validator.ValidationResult;
import org.junit.Assert;

public class Test {

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

    @org.junit.Test
    public void shouldPassWithWhitespace(){
        shouldPass("\" name \" : \" Вася \",");
    }

    @org.junit.Test
    public void shouldPassWithoutWhitespace(){
        shouldPass("\"name\":\"Вася\",");
    }

    @org.junit.Test
    public void shouldFailWithExtraLetter(){
        shouldFail("\"name\"f:\"Вася\",");
    }
}

