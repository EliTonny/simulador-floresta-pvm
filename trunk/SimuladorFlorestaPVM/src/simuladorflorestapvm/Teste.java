/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorflorestapvm;

import java.io.Serializable;

public class Teste implements Serializable {
    private String lol;
    private static Teste instancia;
    
    public String getLol() {
        return lol;
    }

    public void setLol(String lol) {
        this.lol = lol;
    }
    
    private Teste()
    {
        
    }
    
    public static Teste getinstancia() {
        if (instancia == null) {
            instancia = new Teste();
        }
        return instancia;
    }
}
