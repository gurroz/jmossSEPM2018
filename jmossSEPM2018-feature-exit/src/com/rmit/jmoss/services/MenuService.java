package com.rmit.jmoss.services;

import com.rmit.jmoss.enums.MenuDayofWeekEnum;
import com.rmit.jmoss.enums.MenuStatesEnum;
import com.rmit.jmoss.models.Cineplex;
import com.rmit.jmoss.models.MovieFunction;
import com.rmit.jmoss.views.ConsoleOutput;
import com.rmit.jmoss.views.IConsoleOutput;
import com.rmit.jmoss.views.ITable;
import com.rmit.jmoss.views.TableAscii;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class MenuService {

    public static final int MAX_AMOUNT_SHOWS = 5;
    private final  String[] MOVIE_DETAIL= {
            "ID",
            "Name",
            "Description",
            "Available seats"
    };

    private IConsoleOutput consoleOutput;
    private IAuthService authService;
    private ICinemaService cinemaService;
    private Stack<MenuStatesEnum> history;
    private Scanner scanner;

    public MenuService() {
        scanner = new Scanner(System.in);
        consoleOutput = new ConsoleOutput();
        authService = new AuthService();
        cinemaService = new CinemaService();
        history = new Stack<MenuStatesEnum>();

    }

    private void insertHistory(MenuStatesEnum status) {
        history.push(status);
    }

    private void goBack(Cineplex cineplex, MovieFunction movie) {
        MenuStatesEnum response = MenuStatesEnum.LOGIN;
        if(!history.empty()) {
            response = history.pop();
        }

        showMenuOnStatus(response, cineplex, movie);
    }

    private void continueNextMenu(MenuStatesEnum actualStatus, MenuStatesEnum nextStatus, Cineplex cineplex, MovieFunction movie) {
        insertHistory(actualStatus);
        showMenuOnStatus(nextStatus, cineplex, movie);
    }

    private void showMenuOnStatus(MenuStatesEnum status, Cineplex cineplex, MovieFunction movie) {
        switch (status) {
            case LOGIN:
                login();
                break;
            case MAIN_MENU:
                history.empty();
                showMainMenu();
                break;
            case FREE_SEARCH:
//                freeSearch();
                break;
            case SELECT_CINEMA:
                showCineplexList();
                break;
            case SELECT_MOVIE:
                selectMovieFunction(cineplex);
                break;
            case MOVIE_OPERATION:
                showMovieFunctionDetail(cineplex, movie);
                break;
            case MOVIE_BOOK:
                bookMovieFunction(cineplex, movie);
                break;
            case MOVIE_UNBOOK:
                unbookMovieFunction(cineplex, movie);
                break;
            case LOGOUT:
            	logout();
            	continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.LOGIN, null, null);
            case EXIT:
                exit();
                break;
             default:
                login();
                break;
        }

    }

    public void start() {
        login();
    }

    private void login() {
        consoleOutput.login();
        String username = scanner.next();
        if(authService.login(username)) {
            continueNextMenu(MenuStatesEnum.LOGIN, MenuStatesEnum.MAIN_MENU, null, null);
        } else {
            continueNextMenu(MenuStatesEnum.LOGIN, MenuStatesEnum.LOGIN, null, null);
        }
    }

    private void showMainMenu() {
        consoleOutput.mainMenu();
        try {
            int option = Integer.parseInt(scanner.next());

            switch (option) {
                case 0:
                    continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.EXIT, null, null);
                    break;
                case 1:
                    continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.SELECT_CINEMA, null, null);
                    break;
                case 2:
                    continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.FREE_SEARCH, null, null);
                    break;
                case 3:
                    continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.FREE_SEARCH, null, null);
                    break;
                case 4:
                	continueNextMenu(MenuStatesEnum.MAIN_MENU, MenuStatesEnum.LOGOUT, null, null);
                    break;
                default:
                    continueNextMenu(MenuStatesEnum.MAIN_MENU, null, null, null);
                    break;

            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(null, null);
        }
    }


    private void freeCinemaSearch() {
        consoleOutput.freeCinemaSearch();
        try {
            String option = scanner.next();
            switch (option) {
                default:
                    List<Cineplex> cinemas = cinemaService.searchCinemasByName(option);
//                    continueNextMenu(MenuStatesEnum.SELECT_CINEMA, MenuStatesEnum.SELECT_MOVIE, cinema, null);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(null, null);
        }
    }

    private void showCineplexList() {
        consoleOutput.cineplexList(cinemaService.getCinemasNames());
        try {
            String option = scanner.next();
            switch (option) {
                case "0":
                    goBack(null, null);
                    break;
                default:
                    Cineplex cinema = cinemaService.getCinemasById(option);
                    continueNextMenu(MenuStatesEnum.SELECT_CINEMA, MenuStatesEnum.SELECT_MOVIE, cinema, null);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(null, null);
        }
    }

    private void selectMovieFunction(Cineplex cinema) {
        ITable table = new TableAscii(MenuDayofWeekEnum.getNames());
        for(int i = 0 ; i < MenuService.MAX_AMOUNT_SHOWS; i++) {
            table.addRow(cinemaService.getMovieFunctionNames(i, cinema));
        }
        table.printTable();

        consoleOutput.movieFunctionSelect();

        try {
            String option = scanner.next();
            switch (option) {
                case "0":
                    goBack(cinema, null);
                    break;
                default:
                    MovieFunction movieFunction = cinema.getMovieFunctionById(option);
                    continueNextMenu(MenuStatesEnum.SELECT_MOVIE, MenuStatesEnum.MOVIE_OPERATION, cinema, movieFunction);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(cinema, null);
        }
    }

    private void showMovieFunctionDetail(Cineplex cinema, MovieFunction movieFunction) {
        ITable table = new TableAscii(MOVIE_DETAIL);
        table.addRow(movieFunction.getMovieDetail());
        table.printTable();

        consoleOutput.movieFunctionOperationSelect();

        try {
            String option = scanner.next();
            switch (option) {
                case "0":
                    goBack(cinema, movieFunction);
                    break;
                case "1":
                    continueNextMenu(MenuStatesEnum.SELECT_MOVIE, MenuStatesEnum.MOVIE_BOOK, cinema, movieFunction);
                    break;
                case "2":
                    continueNextMenu(MenuStatesEnum.SELECT_MOVIE, MenuStatesEnum.MOVIE_UNBOOK, cinema, movieFunction);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(cinema, movieFunction);
        }
    }

    private void bookMovieFunction(Cineplex cinema, MovieFunction movieFunction) {
        consoleOutput.movieFunctionBook();

        try {
            String option = scanner.next();
            switch (option) {
                case "0":
                    goBack(cinema, movieFunction);
                    break;
                default:
                    boolean resultBooking = true;
                    String [] seats = option.split(",");
                    for(String seat : seats) {
                        if(!movieFunction.bookSeat(seat.trim())) {
                            resultBooking = false;
                            break;
                        }
                    }

                    if(resultBooking) {
                        consoleOutput.movieFunctionBookSuccesfull();
                        continueNextMenu(MenuStatesEnum.MOVIE_BOOK, MenuStatesEnum.MAIN_MENU, cinema, movieFunction);
                    } else {
                        consoleOutput.movieFunctionBookError();
                        continueNextMenu(MenuStatesEnum.MOVIE_OPERATION, MenuStatesEnum.MOVIE_BOOK, cinema, movieFunction);
                    }
                    break;

            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(cinema, movieFunction);
        }
    }

    private void unbookMovieFunction(Cineplex cinema, MovieFunction movieFunction) {
        consoleOutput.movieFunctionUnbook();

        try {
            String option = scanner.next();
            switch (option) {
                case "0":
                    goBack(cinema, movieFunction);
                    break;
                default:
                    boolean resultBooking = true;
                    String [] seats = option.split(",");
                    for(String seat : seats) {
                        if(!movieFunction.removeBooking(seat.trim())) {
                            resultBooking = false;
                            break;
                        }
                    }

                    if(resultBooking) {
                        consoleOutput.movieFunctionUnbookSuccesfull();
                        continueNextMenu(MenuStatesEnum.MOVIE_UNBOOK, MenuStatesEnum.MAIN_MENU, cinema, movieFunction);
                    } else {
                        consoleOutput.movieFunctionUnbookError();
                        continueNextMenu(MenuStatesEnum.MOVIE_OPERATION, MenuStatesEnum.MOVIE_UNBOOK, cinema, movieFunction);
                    }
                    break;

            }
        } catch (Exception e) {
            System.out.println(e);
            goBack(cinema, movieFunction);
        }
    }

    private void exit() {
        consoleOutput.exit();
    }

    private void logout() {
        consoleOutput.logout();
    }

}
