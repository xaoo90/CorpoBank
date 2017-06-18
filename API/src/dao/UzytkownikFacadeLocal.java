package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.ejb.Local;
import model.KontoInterface;
import model.UzytkownikInterface;

/**
 *
 * @author Dominik
 */
@Local
public interface UzytkownikFacadeLocal {

//    void create(UzytkownikInterface uzytkownik);

//    void edit(UzytkownikInterface uzytkownik);

//    void remove(UzytkownikInterface uzytkownik);

//    UzytkownikInterface find(Object id);
    
//    UzytkownikInterface findByCredentials(String login, String password);

//    List<UzytkownikInterface> findAll();

//    List<UzytkownikInterface> findRange(int[] range);

//    int count();

    public UzytkownikInterface login(String uname, String password);
    
    public String rejestracjaUzytkownika(UzytkownikInterface uzytkownik, KontoInterface konto);

}
