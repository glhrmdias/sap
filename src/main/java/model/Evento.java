package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Evento {

    int codEvento;
    String evento;
    int codigo;

    public Evento() {
        this.codEvento = codEvento;
        this.evento = evento;
        this.codigo = codigo;
    }



    @Override
    public String toString() {
        return codigo + "| "+ evento;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getEvento() {
        return evento;
    }

    public Evento setEvento(String evento) {
        this.evento = evento;
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
