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
public interface UzytkownikInterface {

//    boolean equals(Object object);

    String getHaslo();

//    Integer getIdUzytkownik();

    String getImie();

//    @XmlTransient
//    Collection<Konto> getKontoCollection();

    String getLogin();

    Date getLogowanieNieUdane();

    Date getLogowanieUdane();

    String getMail();

    String getNazwisko();

//    @XmlTransient
//    Collection<Rachunek> getRachunekCollection();

    String getUprawnienia();

//    int hashCode();

//    void setHaslo(String haslo);

//    void setIdUzytkownik(Integer idUzytkownik);

    void setImie(String imie);

//    void setKontoCollection(Collection<KontoInterface> kontoCollection);

    void setLogin(String login);

//    void setLogowanieNieUdane(Date logowanieNieUdane);

//    void setLogowanieUdane(Date logowanieUdane);

    void setMail(String mail);

    void setNazwisko(String nazwisko);

//    void setRachunekCollection(Collection<Rachunek> rachunekCollection);

//    void setUprawnienia(String uprawnienia);

//    String toString();
    
}
