/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.HolaMundoEjbRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author macos
 */
public class TestHolaMundoEjb {
    
    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente\n");
        
        try {
            Context jdni = new InitialContext();
            HolaMundoEjbRemote holaMundoEJB = (HolaMundoEjbRemote)jdni.lookup("java:global/HolaMundoEJB/HolaMundoEjbImpl!beans.HolaMundoEjbRemote");
            int resultado = holaMundoEJB.sumar(1, 5);
            System.out.println("El resultado de la suma 1 + 5 = " + resultado);
        } catch (NamingException e) {
            e.printStackTrace(System.out);
        }
    }
}
