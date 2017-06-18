/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import model.KontoInterface;
import model.RachunekInterface;

/**
 *
 * @author Dominik
 */
@Local
public interface RachunekFacadeLocal {

    void create(RachunekInterface rachunek);

    void edit(RachunekInterface rachunek);

//    void remove(RachunekInterface rachunek);

//    RachunekInterface find(Object id);

//    List<RachunekInterface> findRange(int[] range);

//    int count();

    public RachunekInterface findRachunekByNumber(String number);

    public Map ownCapital(KontoInterface k);
    
    public void createRachunek(KontoInterface k, RachunekInterface r);
    
    public List<int[]> transactionsByYear(RachunekInterface r, int year);

}
