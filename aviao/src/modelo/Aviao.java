/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.time.LocalDate;
/**
 *
 * @author Administrador
 */
public class Aviao {
    private Integer codigo;
    private String modelo;
    private Integer capacidade;
    private Double volume;
    private LocalDate data_construcao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public LocalDate getData_construcao() {
        return data_construcao;
    }

    public void setData_construcao(LocalDate data_construcao) {
        this.data_construcao = data_construcao;
    }

    @Override
    public String toString() {
        return "Aviao{" + "modelo=" + modelo + '}';
    }
}
