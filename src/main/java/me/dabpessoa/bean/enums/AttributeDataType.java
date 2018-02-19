package me.dabpessoa.bean.enums;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
public enum AttributeDataType {

    VARCHAR("varchar"),
    INT("int"),
    DOUBLE("double"),
    BIT("bit"),
    SERIAL("serial"),
    NUMERIC("numeric"),
    INTEGER("integer"),
    CHARACTER_VARYING("character varying");

    private String descricao;

    private AttributeDataType(String descricao) {
        this.descricao = descricao;
    }

    public static String[] asStringArray() {
        AttributeDataType[] types = AttributeDataType.values();
        String[] stringValues = new String[types.length];
        for (int i = 0 ; i < stringValues.length ; i++) {
            stringValues[i] = types[i].getDescricao();
        }
        return stringValues;
    }

    public static AttributeDataType findType(String descricao) {
        if (descricao == null) return null;
        AttributeDataType[] types = AttributeDataType.values();
        for (AttributeDataType type : types) {
            if (type.getDescricao() != null && type.getDescricao().equals(descricao)) {
                return type;
            }
        } return null;
    }

    public String getDescricao() {
        return descricao;
    }
}
