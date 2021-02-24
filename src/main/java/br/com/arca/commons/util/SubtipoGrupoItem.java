package br.com.arca.commons.util;

public enum SubtipoGrupoItem {
    ANJO, LINK_FILTRO_APRESENTACAO;

    public static SubtipoGrupoItem safeValueOf(String name) {
        return name != null ? SubtipoGrupoItem.valueOf(name) : null;
    }
}
