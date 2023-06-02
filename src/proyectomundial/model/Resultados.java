package proyectomundial.model;

/**
 *
 * @author PC
 */
public class Resultados {
    
    String grupo;
    String Local;
    String visitante;
    String continente_local;
    String continente_visitante;
    String goles_local;
    String goles_visitante;
    String equipo;
    String totalgoles;
    

    public Resultados() {
    }

    public Resultados(String grupo, String Local, String visitante, String continente_local, String continente_visitante, String goles_local, String goles_visitante) {
        this.grupo = grupo;
        this.Local = Local;
        this.visitante = visitante;
        this.continente_local = continente_local;
        this.continente_visitante = continente_visitante;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
    }
    
    public Resultados(String eqipo, String totalgoles){
        this.equipo = eqipo;
        this.totalgoles = totalgoles;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getLocal() {
        return Local;
    }

    public String getVisitante() {
        return visitante;
    }


    public String getContinente_local() {
        return continente_local;
    }
    
    public String getContinente_visitante(){
        return continente_visitante;
    }
    
    public String getGoles_local(){
        return goles_local;
    }
    
    public String getGoles_visitante(){
        return goles_visitante;
    }
    
    public String equipo(){
        return equipo;
    }
    
    public String totalgoles(){
        return totalgoles;
    }

    
}
