/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import model.KontoInterface;
import model.RachunekInterface;
import model.UzytkownikInterface;

/**
 *
 * @author Dominik
 */
@Local
public interface KontoFacadeLocal {

//    void create(KontoInterface konto);

//    void edit(KontoInterface konto);

//    void remove(KontoInterface konto);

//    KontoInterface find(Object id);

    //List<KontoInterface> findAll();

//    List<KontoInterface> findRange(int[] range);

//    int count();

    public List<KontoInterface> findUserAccounts(UzytkownikInterface user);
    
    public List<RachunekInterface> getRachunki(KontoInterface account);

    public List<UzytkownikInterface> getUsers(KontoInterface konto);
}
