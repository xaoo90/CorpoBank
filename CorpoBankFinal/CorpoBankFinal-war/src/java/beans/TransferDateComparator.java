/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Comparator;
import model.PrzelewInterface;

/**
 *
 * @author Dominik
 */
 class TransferDateComparator implements Comparator<PrzelewInterface> {

    @Override
    public int compare(PrzelewInterface o1, PrzelewInterface o2) {
        return (o2.getDataRealizacji().compareTo(o1.getDataRealizacji()));           
    }
 }
