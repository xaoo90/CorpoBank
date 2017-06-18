/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Konto;
import entity.Przelew;
import entity.Rachunek;
import entity.Uzytkownik;
import javax.ejb.Singleton;

/**
 *
 * @author Dominik
 */
@Singleton
public class EntityFactory {
        
    
    public UzytkownikInterface newUzytkownik(){
        Uzytkownik u = new Uzytkownik();
        return u;
    }
    
    public KontoInterface newKonto(){
        Konto k = new Konto();
        return k;
    }
    
    public RachunekInterface newRachunek(){
        Rachunek r = new Rachunek();
        return r;
    }
    
    public PrzelewInterface newPrzelew(){
        Przelew p = new Przelew();
        return p;
    }
    
}
