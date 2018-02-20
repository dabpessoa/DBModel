package me.dabpessoa.bean;

public class TabelaModelo {

    private Double largura;
    private Double altura;
    private Double posicaoX;
    private Double posicaoY;

    public TabelaModelo() {
    }

    public TabelaModelo(Double largura, Double altura, Double posicaoX, Double posicaoY) {
        this.largura = largura;
        this.altura = altura;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Double posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Double getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Double posicaoY) {
        this.posicaoY = posicaoY;
    }

}
