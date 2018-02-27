package me.dabpessoa.bean;

import java.awt.*;

public class TabelaEstilo {

    public Color corDeFundo;
    public Color corDeTitulo;
    public Color corFonteTitulo;
    public Color corFontePropriedades;
    public Color corDaBorda;
    public Color corDaBordaSelecionada;

    public Integer espessuraBorda;
    public Integer espessuraBordaSelecionada;
    public Integer tamanhoFonteTitulo;
    public Integer tamanhoFontePropriedades;

    public TabelaEstilo() {}

    public void zerarEstilos() {
        corDeFundo = null;
        corDeTitulo = null;
        corFonteTitulo = null;
        corFontePropriedades = null;
        corDaBorda = null;
        corDaBordaSelecionada = null;

        espessuraBorda = null;
        espessuraBordaSelecionada = null;
        tamanhoFonteTitulo = null;
        tamanhoFontePropriedades = null;
    }

    public void carregarEstilosPadroes() {
        corDeFundo = ValoresPadroes.corDeFundo;
        corDeTitulo = ValoresPadroes.corDeTitulo;
        corFonteTitulo = ValoresPadroes.corFonteTitulo;
        corFontePropriedades= ValoresPadroes.corFontePropriedades;
        corDaBorda = ValoresPadroes.corDaBorda;
        corDaBordaSelecionada = ValoresPadroes.corDaBordaSelecionada;

        espessuraBorda = ValoresPadroes.espessuraBorda;
        espessuraBordaSelecionada = ValoresPadroes.espessuraBordaSelecionada;
        tamanhoFonteTitulo = ValoresPadroes.tamanhoFonteTitulo;
        tamanhoFontePropriedades = ValoresPadroes.tamanhoFontePropriedades;
    }

    public Color getCorDeFundo() {
        return corDeFundo;
    }

    public void setCorDeFundo(Color corDeFundo) {
        this.corDeFundo = corDeFundo;
    }

    public Color getCorDeTitulo() {
        return corDeTitulo;
    }

    public void setCorDeTitulo(Color corDeTitulo) {
        this.corDeTitulo = corDeTitulo;
    }

    public Color getCorFonteTitulo() {
        return corFonteTitulo;
    }

    public void setCorFonteTitulo(Color corFonteTitulo) {
        this.corFonteTitulo = corFonteTitulo;
    }

    public Color getCorFontePropriedades() {
        return corFontePropriedades;
    }

    public void setCorFontePropriedades(Color corFontePropriedades) {
        this.corFontePropriedades = corFontePropriedades;
    }

    public Color getCorDaBorda() {
        return corDaBorda;
    }

    public void setCorDaBorda(Color corDaBorda) {
        this.corDaBorda = corDaBorda;
    }

    public Color getCorDaBordaSelecionada() {
        return corDaBordaSelecionada;
    }

    public void setCorDaBordaSelecionada(Color corDaBordaSelecionada) {
        this.corDaBordaSelecionada = corDaBordaSelecionada;
    }

    public Integer getEspessuraBorda() {
        return espessuraBorda;
    }

    public void setEspessuraBorda(Integer espessuraBorda) {
        this.espessuraBorda = espessuraBorda;
    }

    public Integer getEspessuraBordaSelecionada() {
        return espessuraBordaSelecionada;
    }

    public void setEspessuraBordaSelecionada(Integer espessuraBordaSelecionada) {
        this.espessuraBordaSelecionada = espessuraBordaSelecionada;
    }

    public Integer getTamanhoFonteTitulo() {
        return tamanhoFonteTitulo;
    }

    public void setTamanhoFonteTitulo(Integer tamanhoFonteTitulo) {
        this.tamanhoFonteTitulo = tamanhoFonteTitulo;
    }

    public Integer getTamanhoFontePropriedades() {
        return tamanhoFontePropriedades;
    }

    public void setTamanhoFontePropriedades(Integer tamanhoFontePropriedades) {
        this.tamanhoFontePropriedades = tamanhoFontePropriedades;
    }

    public static class ValoresPadroes {

        public static Color corDeFundo;
        public static Color corDeTitulo;
        public static Color corFonteTitulo;
        public static Color corFontePropriedades;
        public static Color corDaBorda;
        public static Color corDaBordaSelecionada;

        public static Integer espessuraBorda;
        public static Integer espessuraBordaSelecionada;
        public static Integer tamanhoFonteTitulo;
        public static Integer tamanhoFontePropriedades;

        static {
            corDeFundo = Color.WHITE;
            corDeTitulo = null;
            corFonteTitulo = null;
            corFontePropriedades = null;
            corDaBorda = Color.GRAY;
            corDaBordaSelecionada = null;

            espessuraBorda = 1;
            espessuraBordaSelecionada = null;
            tamanhoFonteTitulo = null;
            tamanhoFontePropriedades = null;
        }

    }

}
