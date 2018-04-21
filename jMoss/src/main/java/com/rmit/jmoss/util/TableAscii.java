package com.rmit.jmoss;

import java.util.Arrays;

public class TableAscii {
    private final String TABLE_LINE = "+-----------------+";
    private final String TABLE_ROW = "%n";
    private final String TABLE_ROW_CELL = "| %-15s |";

    private String[] headers;
    private String[][] data;
    private int columns;

    public TableAscii() {}

    public TableAscii(String[] headers, String[][] data) {
        this.headers = headers;
        this.data = data;
        this.columns = headers.length;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public void printTable() {
        printHeader();
        printData();
    }

    private void printHeader() {
        printNewLine();

        for(String header : headers) {
            System.out.format(TABLE_ROW_CELL, header);
        }
    }

    private void printData() {
        printNewLine();

        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                System.out.format(TABLE_ROW_CELL, data[i][j]);
            }
            printNewLine();
        }
    }

    private void printNewLine() {
        System.out.format(TABLE_ROW);
        for(int i = 0; i < columns; i++) {
            System.out.format(TABLE_LINE);
        }
        System.out.format(TABLE_ROW);
    }

    @Override
    public String toString() {
        return "TableAscii{" +
                "headers=" + Arrays.toString(headers) +
                ", data=" + data +
                '}';
    }
}
