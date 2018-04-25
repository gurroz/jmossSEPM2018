package com.rmit.jmoss.views;

import java.util.List;

public interface IConsoleOutput {

    void login();
    void mainMenu();
    void cineplexList(List<String> cinemas);
    void movieFunctionSelect();
    void movieFunctionOperationSelect();
    void movieFunctionBook();
    void movieFunctionBookSuccesfull();
    void movieFunctionBookError();
    void movieFunctionUnbook();
    void movieFunctionUnbookSuccesfull();
    void movieFunctionUnbookError();
    void freeCinemaSearch();
    void freeMovieSearch();
    void exit();
    void logout();

}
