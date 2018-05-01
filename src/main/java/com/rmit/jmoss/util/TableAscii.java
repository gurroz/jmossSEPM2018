package com.rmit.jmoss.util;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TableAscii {
    private final String TABLE_LINE = "-";
    private final String TABLE_LINE_SEPARATOR = "+";
    private final String TABLE_ROW = "%n";

    private String[] headers;
    private List<List<String>> data;
    private List<Integer> columns;

    public TableAscii() {}

    public TableAscii(String[] headers, List<List<String>> dataRows) {
        this.headers = headers;
        this.columns = new ArrayList<Integer>();
        this.data = new ArrayList<List<String>>();
        for(int i = 0; i < headers.length; i++) {
            columns.add(headers[i].length());
        }

        this.data = dataRows;
        for(int i = 0; i < dataRows.size(); i++) {
            for(int j = 0; j < dataRows.size(); j++) {
                int actualValue = columns.get(j);
                if(actualValue < dataRows.get(i).get(j).length()) {
                    columns.set(j, dataRows.get(i).get(j).length());
                }
            }
        }
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public void printTable() {
        printHeader();
        printData();
    }

    private void printHeader() {
        printNewLine();

        for(int i = 0; i < headers.length; i++) {
            int diference = columns.get(i) - headers[i].length();
            System.out.print("| " + headers[i] );
            for(int j = 0; j <= diference; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
    }

    private void printData() {
        printNewLine();

        for(List<String> rows : data) {
            for(int i = 0; i < rows.size(); i++) {
                System.out.print("| " + rows.get(i) + " |");
            }
            printNewLine();
        }
    }

    private void printNewLine() {
        System.out.format(TABLE_ROW);
        for(Integer maxSize : columns) {
            String finalLine = TABLE_LINE_SEPARATOR;
            for(int i = 0; i < maxSize + 2; i++) {
                finalLine += TABLE_LINE;
            }

            finalLine += TABLE_LINE_SEPARATOR;
            System.out.format(finalLine);
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
