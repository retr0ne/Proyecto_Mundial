/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author PC
 */
public class ResultadosDAO {

    public ResultadosDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarResultados(Resultados resultado) {

        String sql = "INSERT INTO j_hernandez34.partidos (grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante) values("
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinente_local() + "', "
                + "'" + resultado.getContinente_visitante() + "', "
                + "'" + resultado.getGoles_local() + "', "
                + "'" + resultado.getGoles_visitante() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultados> getResultados() {

        String sql = "SELECT DISTINCT grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM j_hernandez34.partidos order by grupo";
        List<Resultados> resultado = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultados = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultado.add(resultados);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return resultado;
    }

    public String[][] getResultadosMatriz() {

        String[][] matrizResultados = null;
        List<Resultados> resultado = getResultados();

        if (resultado != null && resultado.size() > 0) {

            matrizResultados = new String[resultado.size()][7];

            int x = 0;
            for (Resultados resultados : resultado) {

                matrizResultados[x][0] = resultados.getGrupo();
                matrizResultados[x][1] = resultados.getLocal();
                matrizResultados[x][2] = resultados.getVisitante();
                matrizResultados[x][3] = resultados.getContinente_local();
                matrizResultados[x][4] = resultados.getContinente_visitante();
                matrizResultados[x][5] = resultados.getGoles_local();
                matrizResultados[x][6] = resultados.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }

    public List<Resultados> getResultadosBuscar(String busqueda) {

        String sql = "SELECT DISTINCT grupo, local, visitante, continente_local, continente_visitante, Goles_local, Goles_visitante FROM j_hernandez34.partidos WHERE LOWER(local) LIKE LOWER('%" + busqueda + "%') or LOWER(continente_local) LIKE LOWER('%" + busqueda + "%')";
        List<Resultados> resultados = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("Goles_local"), result.getString("Goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return resultados;
    }

    public String[][] getResultadoMatrizBuscar(String busqueda) {

        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultadosBuscar(busqueda);

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }

    public int NumeroDePartidos() {

        String sql = "SELECT COUNT(local) FROM j_hernandez34.partidos;";
        int partidos = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                partidos = result.getInt(1);
                System.out.println("Partidos: " + partidos);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return partidos;

    }

    public String PromedioDeGoles() {

        String sql = "SELECT AVG(goles_local + goles_visitante) AS promedio_goles FROM j_hernandez34.partidos p;";
        double promedio = 0;
        String resultado = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                promedio = result.getDouble(1);
                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                resultado = decimalFormat.format(promedio);
                System.out.println("Promedio: " + resultado);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return resultado;

    }

    public List<Resultados> PartidoConMasGoles() {

        String sql = "SELECT *\n"
                + "FROM j_hernandez34.partidos p\n"
                + "WHERE (goles_visitante + goles_local) = (\n"
                + "    SELECT MAX(goles_visitante + goles_local)\n"
                + "    FROM j_hernandez34.partidos\n"
                + ")\n"
                + "ORDER by visitante \n"
                + "LIMIT 1;";
        List<Resultados> resultado = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultados = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultado.add(resultados);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return resultado;
    }

    public List<Resultados> PartidoConMenosGoles() {

        String sql = "SELECT *\n"
                + "FROM j_hernandez34.partidos p\n"
                + "WHERE (goles_visitante + goles_local) = (\n"
                + "    SELECT MIN(goles_visitante + goles_local)\n"
                + "    FROM j_hernandez34.partidos\n"
                + ")\n"
                + "ORDER by visitante \n"
                + "LIMIT 1;";
        List<Resultados> resultado = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultados = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultado.add(resultados);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }

        return resultado;
    }

    public String[][] PartidosConMasGolesMatriz() {

        String[][] matrizResultados = null;
        List<Resultados> resultado = PartidoConMasGoles();

        if (resultado != null && resultado.size() > 0) {

            matrizResultados = new String[resultado.size()][7];

            int x = 0;
            for (Resultados resultados : resultado) {

                matrizResultados[x][0] = resultados.getGrupo();
                matrizResultados[x][1] = resultados.getLocal();
                matrizResultados[x][2] = resultados.getVisitante();
                matrizResultados[x][3] = resultados.getContinente_local();
                matrizResultados[x][4] = resultados.getContinente_visitante();
                matrizResultados[x][5] = resultados.getGoles_local();
                matrizResultados[x][6] = resultados.getGoles_visitante();

                x++;

            }
        }

        return matrizResultados;

    }

    public String[][] PartidosConMenosGolesMatriz() {

        String[][] matrizResultados = null;
        List<Resultados> resultado = PartidoConMenosGoles();

        if (resultado != null && resultado.size() > 0) {

            matrizResultados = new String[resultado.size()][7];

            int x = 0;
            for (Resultados resultados : resultado) {

                matrizResultados[x][0] = resultados.getGrupo();
                matrizResultados[x][1] = resultados.getLocal();
                matrizResultados[x][2] = resultados.getVisitante();
                matrizResultados[x][3] = resultados.getContinente_local();
                matrizResultados[x][4] = resultados.getContinente_visitante();
                matrizResultados[x][5] = resultados.getGoles_local();
                matrizResultados[x][6] = resultados.getGoles_visitante();

                x++;

            }
        }

        return matrizResultados;

    }

    public String getPartidosDondeGanaLocal() {
        String partidosConGanadorLocal = "";
        String sql = "SELECT COUNT(*) FROM j_hernandez34.partidos p WHERE goles_local  > goles_visitante;";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {
                while (result.next()) {
                    partidosConGanadorLocal = result.getString("count");
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }
        return partidosConGanadorLocal;
    }

    public String getPartidosDondeEmpata() {
        String partidosConGanadorLocal = "";
        String sql = "SELECT COUNT(*) FROM j_hernandez34.partidos p WHERE goles_local  = goles_visitante;";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {
                while (result.next()) {
                    partidosConGanadorLocal = result.getString("count");
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }
        return partidosConGanadorLocal;
    }

    public String getPartidosDondeGanaVisitante() {
        String partidosConGanadorLocal = "";
        String sql = "SELECT COUNT(*) FROM j_hernandez34.partidos p WHERE goles_local  < goles_visitante;";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {
                while (result.next()) {
                    partidosConGanadorLocal = result.getString("count");
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }
        return partidosConGanadorLocal;
    }

    public List<Resultados> EquipoConMasYMenosGoles(String sql) {
    
            List<Resultados> datos = new ArrayList<Resultados>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Resultados consulta = new Resultados(result.getString("equipo"), result.getString("totalgoles"));
                    datos.add(consulta);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Resultados");
        }
        
        return datos;
    }
    public String[][] MatrizEquipoConMasYMenosGoles(String sql) {

        String[][] matrizResultados = null;
        List<Resultados> resultados = EquipoConMasYMenosGoles(sql);

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][2];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.equipo();
                matrizResultados[x][1] = resultado.totalgoles();
                x++;
            }
        }

        return matrizResultados;
    }

}
