package de.p3lina.domain;


public interface Handle<ReturnType, Parameter, AnotherReturnType, AnotherParameter> {


    ReturnType process(Parameter something);
    AnotherReturnType isMatchSetWon(AnotherParameter something);
}
