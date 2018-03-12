/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alis.store.domain.enums;


public enum EType {

    /**
     * This user has ready-only permission.
     */
    User(1),

    /**
     *This user has r/w permission.
     */
    Admin(2);

    private final int value;

    EType(int value){
        this.value = value;
    }
}
