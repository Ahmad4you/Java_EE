package com.example.cdione;

import jakarta.enterprise.context.Dependent;

@Dependent
public class Calculator {

    public int add(int a, int b) { return a + b;}
}
