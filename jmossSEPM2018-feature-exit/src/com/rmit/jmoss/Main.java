package com.rmit.jmoss;

import com.rmit.jmoss.services.MenuService;

public class Main {


    public static void main(String[] args) {
        MenuService menu = new MenuService();
        menu.start();
    }

}
