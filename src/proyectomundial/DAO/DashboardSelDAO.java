package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author usuario
 */
public class DashboardSelDAO {

    public static double[] num_continentes = new double[7];
    public static String[] nacionalidades = new String[5];
    public static int[] ranking = new int[5];

    public DashboardSelDAO() {
        BasedeDatos.conectar();
    }

    public void getPaisesporContinente() {

        String[] continentes = {"África", "América del Sur", "América Central", "América del Norte", "Asia", "Europa", "Oceanía"};
        try {
            for (int x = 0; x < num_continentes.length; x++) {
                String sql = "SELECT COUNT (continente) AS cuenta FROM d_vargas8.equipo e where continente = '" + continentes[x] + "';";
                ResultSet contar = BasedeDatos.ejecutarSQL(sql);
                if (contar.next()) {
                    num_continentes[x] = contar.getByte("cuenta");
                }
                //System.out.println(num_continentes[x]);
            }

        } catch (Exception ex) {
            System.out.println("Fallo en conexion dash");
            Logger.getLogger(DashboardSelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public byte getselecciones() {
        byte num_selecciones = 0;
        try {

            String sql = "SELECT COUNT (equipo) AS cuenta FROM d_vargas8.equipo e";
            ResultSet contar = BasedeDatos.ejecutarSQL(sql);
            if (contar.next()) {
                num_selecciones = contar.getByte("cuenta");
            }
            System.out.println(num_selecciones);
        } catch (Exception ex) {
            System.out.println("Fallo en conexion dash");
            Logger.getLogger(DashboardSelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num_selecciones;
    }

    public byte getnacionalidades() {
        byte num_nacionalidades = 0;
        try {

            String sql = "SELECT COUNT (DISTINCT nacionalidad) AS cuenta FROM d_vargas8.equipo e";
            ResultSet contar = BasedeDatos.ejecutarSQL(sql);
            if (contar.next()) {
                num_nacionalidades = contar.getByte("cuenta");
            }
            System.out.println(num_nacionalidades);
        } catch (Exception ex) {
            System.out.println("Fallo en conexion dash");
            Logger.getLogger(DashboardSelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num_nacionalidades;
    }

    public void getnacionalidad_ranking() {
        try {
            String sql = "select nacionalidad as nac, COUNT(nacionalidad) AS cuenta FROM d_vargas8.equipo e GROUP BY nacionalidad order by cuenta desc LIMIT 5;";
            ResultSet contar = BasedeDatos.ejecutarSQL(sql);
            for (int x = 0; x < 5; x++) {
                if (contar.next()) {
                    ranking[x] = contar.getInt("cuenta");
                    nacionalidades[x] = contar.getString("nac");
                }
                System.out.println(ranking[x]);
            }
        } catch (Exception ex) {
            System.out.println("Fallo en conexion dash");
            Logger.getLogger(DashboardSelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
