package com.projekthibernate.POJO;

public class Row {
    int nr;
    int cols;
    

    public Row(int nr, int cols) {
        this.nr = nr;
        this.cols = cols;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}

