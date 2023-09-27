package pe.gob.sunat.apptaxi.controller.enums;

public enum PerfilEnum {
    ADMIN(1),
    CONDUCTOR(2),
    USUARIO(3);

    private Integer valor;
    PerfilEnum(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public static String getStringValueFromInt(int i) {
        for (PerfilEnum estado : PerfilEnum.values()) {
            if (estado.getValor() == i) {
                return estado.toString();
            }
        }

        throw new IllegalArgumentException("El número dado no se encontró.");
    }
}
