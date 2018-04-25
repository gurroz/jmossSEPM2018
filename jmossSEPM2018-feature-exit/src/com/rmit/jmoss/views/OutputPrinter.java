package com.rmit.jmoss.views;

public class OutputPrinter implements IOutputPrinter {

    @Override
    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
