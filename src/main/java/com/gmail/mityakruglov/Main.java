package com.gmail.mityakruglov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        try {
            Scanner sc = new Scanner(System.in);
            MenuDAOImpl menu = new MenuDAOImpl();
            try{

            while (true) {


                System.out.println("1: add");
                System.out.println("2: delete");
                System.out.println("3: change");
                System.out.println("4: view all");
                System.out.println("5: view min max");
                System.out.println("6: view sale");
                System.out.print("-> ");

                String s = sc.nextLine();


                switch (s) {
                    case "1":
                        menu.add(sc);
                        break;
                    case "2":
                        menu.delete(sc);
                        break;
                    case "3":
                        menu.change(sc);
                        break;
                    case "4":
                        menu.viewAll();
                        break;
                    case "5":
                        menu.viewForParameter();
                        break;
                    case "6":
                        menu.viewForSale();
                        break;
                    default:
                        return;

                    }
                }

            } finally{
                sc.close();

            }
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
    }
}
