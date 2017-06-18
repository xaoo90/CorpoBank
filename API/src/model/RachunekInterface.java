/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik
 */
public interface RachunekInterface {

//    boolean equals(Object object);

    Date getDataZalozenia();

    Integer getIdRachunek();

    String getNazwa();

    String getNumer();

    int getSaldo();

    String getWaluta();

//    int hashCode();

//    void setDataZalozenia(Date dataZalozenia);

//    void setIdRachunek(Integer idRachunek);

//    void setNazwa(String nazwa);

//    void setNumer(String numer);

    void setSaldo(int saldo);

//    void setWaluta(String waluta);

//    String toString();
    
//    public void setIdKonto(KontoInterface idKonto);
            
    public KontoInterface getIdKonto();
    
//    public Collection<PrzelewInterface> getPrzelewCollection();

    
}
