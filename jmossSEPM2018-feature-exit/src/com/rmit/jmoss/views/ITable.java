package com.rmit.jmoss.views;

import java.util.List;

public interface ITable {

    void printTable();
    void setHeaders(String[] headers);
    void addRow(List<String> row);
}
