/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Dominik
 */
public interface PrzelewInterface {

//    boolean equals(Object object);

    String getAdres();

    Date getDataRealizacji();

    Date getDataWystawienia();

//    Integer getIdPrzelew();

    int getKwota();

    String getNazwa();

    String getNrRachunku();

    String getStatus();

    String getTytul();
    
//    int hashCode();

    void setAdres(String adres);

    void setDataRealizacji(Date dataRealizacji);

    void setDataWystawienia(Date dataWystawienia);

    void setIdPrzelew(Integer idPrzelew);

    void setKwota(int kwota);

    void setNazwa(String nazwa);

    void setNrRachunku(String nrRachunku);

    void setStatus(String status);

    void setTytul(String tytul);

//    String toString();

    public void setRachunek(RachunekInterface r);
    
    public RachunekInterface getRachunek();
    
}
