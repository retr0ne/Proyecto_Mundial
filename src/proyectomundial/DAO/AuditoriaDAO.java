
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author LAB205BPC02
 */
public class AuditoriaDAO {
    public boolean update(String parametro) {
        String sql = "UPDATE d_vargas8.auditoria SET contador = contador + 1 where pagina = '" + parametro + "'";
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        return registro;
    }

    public List<Auditoria> getAuditoria() {

        String sql = "SELECT pagina, contador FROM d_vargas8.auditoria";
        List<Auditoria> aux_auditoria = new ArrayList<Auditoria>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Auditoria auditoria = new Auditoria(result.getString("pagina"), result.getString("contador"));
                    aux_auditoria.add(auditoria);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Auditoria");
        }

        return aux_auditoria;
    }

    public String[][] getMatrizAuditoria() {

        String[][] matrizAuditoria = null;
        List<Auditoria> auditoria = getAuditoria();

        if (auditoria != null && auditoria.size() > 0) {
            matrizAuditoria = new String[auditoria.size()][4];
            int i = 0;
            for (Auditoria aux_auditoria : auditoria) {
                matrizAuditoria[i][0] = aux_auditoria.getPagina();
                matrizAuditoria[i][1] = aux_auditoria.getContador();
                i++;
            }
        }

        return matrizAuditoria;
    }

    public class Auditoria {

        String pagina;
        String contador;

        public Auditoria() {
        }

        public Auditoria(String menu, String contador) {
            this.pagina = menu;
            this.contador = contador;
        }

        public String getPagina() {
            return pagina;
        }

        public void setPagina(String menu) {
            this.pagina = menu;
        }

        public String getContador() {
            return contador;
        }

        public void setContador(String contador) {
            this.contador = contador;
        }

    }
}

