/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.apptaxi.controller.enums;

/**
 *
 * @author Aldo Malaver
 */
public enum EstadoEnum {

    ACTIVO(1),
    INACTIVO(0);

    private int valor;

    EstadoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static String getStringValueFromInt(int i) {
        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.getValor() == i) {
                return estado.toString();
            }
        }

        throw new IllegalArgumentException("el numero dado no se encontro.");
    }

}
