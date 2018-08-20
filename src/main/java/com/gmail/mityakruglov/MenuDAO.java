package com.gmail.mityakruglov;

import java.util.Scanner;

public interface MenuDAO {
    void add(Scanner sc);
    void delete(Scanner sc);
    void change(Scanner sc);
    void viewAll();
    void viewForSale();
    void viewForParameter();
}
