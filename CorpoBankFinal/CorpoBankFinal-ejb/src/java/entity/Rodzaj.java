/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "rodzaj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rodzaj.findAll", query = "SELECT r FROM Rodzaj r"),
    @NamedQuery(name = "Rodzaj.findByIdRodzaj", query = "SELECT r FROM Rodzaj r WHERE r.idRodzaj = :idRodzaj"),
    @NamedQuery(name = "Rodzaj.findByNazwa", query = "SELECT r FROM Rodzaj r WHERE r.nazwa = :nazwa"),
    @NamedQuery(name = "Rodzaj.findByProcent", query = "SELECT r FROM Rodzaj r WHERE r.procent = :procent")})
public class Rodzaj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRodzaj")
    private Integer idRodzaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procent")
    private int procent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRodzaj")
    private Collection<Lokata> lokataCollection;

    public Rodzaj() {
    }

    public Rodzaj(Integer idRodzaj) {
        this.idRodzaj = idRodzaj;
    }

    public Rodzaj(Integer idRodzaj, String nazwa, int procent) {
        this.idRodzaj = idRodzaj;
        this.nazwa = nazwa;
        this.procent = procent;
    }

    public Integer getIdRodzaj() {
        return idRodzaj;
    }

    public void setIdRodzaj(Integer idRodzaj) {
        this.idRodzaj = idRodzaj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    @XmlTransient
    public Collection<Lokata> getLokataCollection() {
        return lokataCollection;
    }

    public void setLokataCollection(Collection<Lokata> lokataCollection) {
        this.lokataCollection = lokataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRodzaj != null ? idRodzaj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rodzaj)) {
            return false;
        }
        Rodzaj other = (Rodzaj) object;
        if ((this.idRodzaj == null && other.idRodzaj != null) || (this.idRodzaj != null && !this.idRodzaj.equals(other.idRodzaj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rodzaj[ idRodzaj=" + idRodzaj + " ]";
    }
    
}
