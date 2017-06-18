/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import model.UzytkownikInterface;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "uzytkownik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findByIdUzytkownik", query = "SELECT u FROM Uzytkownik u WHERE u.idUzytkownik = :idUzytkownik"),
    @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login"),
    @NamedQuery(name = "Uzytkownik.findByHaslo", query = "SELECT u FROM Uzytkownik u WHERE u.haslo = :haslo"),
    @NamedQuery(name = "Uzytkownik.findByMail", query = "SELECT u FROM Uzytkownik u WHERE u.mail = :mail"),
    @NamedQuery(name = "Uzytkownik.findByImie", query = "SELECT u FROM Uzytkownik u WHERE u.imie = :imie"),
    @NamedQuery(name = "Uzytkownik.findByNazwisko", query = "SELECT u FROM Uzytkownik u WHERE u.nazwisko = :nazwisko"),
    @NamedQuery(name = "Uzytkownik.findByUprawnienia", query = "SELECT u FROM Uzytkownik u WHERE u.uprawnienia = :uprawnienia"),
    @NamedQuery(name = "Uzytkownik.findByLogowanieUdane", query = "SELECT u FROM Uzytkownik u WHERE u.logowanieUdane = :logowanieUdane"),
    @NamedQuery(name = "Uzytkownik.findByLogowanieNieUdane", query = "SELECT u FROM Uzytkownik u WHERE u.logowanieNieUdane = :logowanieNieUdane")})
public class Uzytkownik implements Serializable, UzytkownikInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUzytkownik")
    private Integer idUzytkownik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "haslo")
    private String haslo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "uprawnienia")
    private String uprawnienia;
    @Column(name = "logowanieUdane")
    @Temporal(TemporalType.DATE)
    private Date logowanieUdane;
    @Column(name = "logowanieNieUdane")
    @Temporal(TemporalType.DATE)
    private Date logowanieNieUdane;
    @JoinTable(name = "uzytkownikrachunku", joinColumns = {
        @JoinColumn(name = "idUzytkownik", referencedColumnName = "idUzytkownik")}, inverseJoinColumns = {
        @JoinColumn(name = "idRachunek", referencedColumnName = "idRachunek")})
    @ManyToMany
    private Collection<Rachunek> rachunekCollection;
    @JoinTable(name = "uzytkownikkonta", joinColumns = {
        @JoinColumn(name = "idUzytkownik", referencedColumnName = "idUzytkownik")}, inverseJoinColumns = {
        @JoinColumn(name = "idKonto", referencedColumnName = "idKonto")})
    @ManyToMany
    private Collection<Konto> kontoCollection;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public Uzytkownik(Integer idUzytkownik, String login, String haslo, String mail, String imie, String nazwisko, String uprawnienia) {
        this.idUzytkownik = idUzytkownik;
        this.login = login;
        this.haslo = haslo;
        this.mail = mail;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.uprawnienia = uprawnienia;
    }

//    @Override
    public Integer getIdUzytkownik() {
        return idUzytkownik;
    }

//    @Override
    public void setIdUzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getHaslo() {
        return haslo;
    }

//    @Override
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String getImie() {
        return imie;
    }

    @Override
    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String getUprawnienia() {
        return uprawnienia;
    }

//    @Override
    public void setUprawnienia(String uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    @Override
    public Date getLogowanieUdane() {
        return logowanieUdane;
    }

//    @Override
    public void setLogowanieUdane(Date logowanieUdane) {
        this.logowanieUdane = logowanieUdane;
    }

    @Override
    public Date getLogowanieNieUdane() {
        return logowanieNieUdane;
    }

//    @Override
    public void setLogowanieNieUdane(Date logowanieNieUdane) {
        this.logowanieNieUdane = logowanieNieUdane;
    }

//    @XmlTransient
//    @Override
//    public Collection<Rachunek> getRachunekCollection() {
//        return rachunekCollection;
//    }

//    @Override
//    public void setRachunekCollection(Collection<Rachunek> rachunekCollection) {
//        this.rachunekCollection = rachunekCollection;
//    }

    @XmlTransient
    public Collection<Konto> getKontoCollection() {
        return kontoCollection;
    }

    public void setKontoCollection(Collection<Konto> kontoCollection) {
        this.kontoCollection = kontoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownik != null ? idUzytkownik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        return !((this.idUzytkownik == null && other.idUzytkownik != null) || (this.idUzytkownik != null && !this.idUzytkownik.equals(other.idUzytkownik)));
    }

    @Override
    public String toString() {
        return "entity.Uzytkownik[ idUzytkownik=" + idUzytkownik + " ]";
    }
    
}
