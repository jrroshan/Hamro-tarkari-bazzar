/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Administrator
 */
@WebListener
public class DBHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hamro_Tarkari_BazzarPU");
        sce.getServletContext().setAttribute("HamroTarikarBazzaremf",emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf =  (EntityManagerFactory) sce.getServletContext().getAttribute("HamroTarikarBazzaremf");
        emf.close();       
    }
    
}