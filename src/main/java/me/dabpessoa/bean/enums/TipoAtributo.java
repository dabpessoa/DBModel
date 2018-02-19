package me.dabpessoa.bean.enums;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
public enum TipoAtributo {

    VARCHAR("varchar"),
    INT("int"),
    DOUBLE("double"),
    BIT("bit"),
    SERIAL("serial"),
    NUMERIC("numeric"),
    INTEGER("integer"),
    CHARACTER_VARYING("character varying");

    private String descricao;

    private TipoAtributo(String descricao) {
        this.descricao = descricao;
    }

    public static String[] asStringArray() {
        TipoAtributo[] types = TipoAtributo.values();
        String[] stringValues = new String[types.length];
        for (int i = 0 ; i < stringValues.length ; i++) {
            stringValues[i] = types[i].getDescricao();
        }
        return stringValues;
    }

    public static TipoAtributo findType(String descricao) {
        if (descricao == null) return null;
        TipoAtributo[] types = TipoAtributo.values();
        for (TipoAtributo type : types) {
            if (type.getDescricao() != null && type.getDescricao().equals(descricao)) {
                return type;
            }
        } return null;
    }

    public String getDescricao() {
        return descricao;
    }
}
