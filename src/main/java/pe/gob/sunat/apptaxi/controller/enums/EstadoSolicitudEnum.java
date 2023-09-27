package pe.gob.sunat.apptaxi.controller.enums;

public enum EstadoSolicitudEnum {
    REGISTRADO(1),
    APROBADO(0),
    CANCELADO(3),
    FINALIZADO(2);

    private int valor;

    EstadoSolicitudEnum(int valor) {
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
