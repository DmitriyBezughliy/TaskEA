package com.gmail.mityakruglov;

import javax.persistence.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MenuDAOImpl implements MenuDAO {

    static EntityManagerFactory emf;
    static EntityManager em;

    public MenuDAOImpl() {
        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();
    }



    @Override
    public void add(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);
        System.out.print("Enter weight: ");
        String sWeight = sc.nextLine();
        double weight = Double.parseDouble(sWeight);
        System.out.print("Enter sale: ");
        String sSale = sc.nextLine();
        boolean sale = Boolean.parseBoolean(sSale);


        em.getTransaction().begin();
        try {
            SimpleMenu c = new SimpleMenu(name, price, weight, sale);
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Scanner sc) {
        System.out.print("Enter id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        SimpleMenu c = em.find(SimpleMenu.class, id);
        if (c == null) {
            System.out.println("not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void change(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);
        System.out.print("Enter weight: ");
        String sWeight = sc.nextLine();
        double weight = Double.parseDouble(sWeight);
        System.out.print("Enter sale: ");
        String sSale = sc.nextLine();
        boolean sale = Boolean.parseBoolean(sSale);

        SimpleMenu c = null;
        try {
            Query query = em.createQuery("SELECT c FROM SimpleMenu c WHERE c.name = :name", SimpleMenu.class);
            query.setParameter("name", name);
            c = (SimpleMenu) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            c.setPrice(price);
            c.setWeight(weight);
            c.setSale(sale);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void viewAll() {
        Query query = em.createQuery("SELECT c FROM SimpleMenu c", SimpleMenu.class);
        List<SimpleMenu> list = (List<SimpleMenu>) query.getResultList();
        for (SimpleMenu c : list) {
            System.out.println(c);
        }
    }

    @Override
    public void viewForSale() {
        Query query = em.createQuery("SELECT c FROM SimpleMenu c WHERE c.sale=true", SimpleMenu.class);
        List<SimpleMenu> list = (List<SimpleMenu>) query.getResultList();
        for (SimpleMenu c : list) {
            System.out.println(c);
        }
    }

    @Override
    public void viewForParameter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Min");
        double min = sc.nextDouble();
        System.out.println("Enter Max");
        double max = sc.nextDouble();

        Query query = em.createQuery("SELECT c FROM SimpleMenu c WHERE c.price BETWEEN :min AND :max", SimpleMenu.class);

        query.setParameter("min", min);
        query.setParameter("max", max);
        List<SimpleMenu> list = (List<SimpleMenu>) query.getResultList();
        for (SimpleMenu c : list) {
            System.out.println(c);
        }
    }


}
