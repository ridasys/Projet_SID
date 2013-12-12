import java.sql.*;
public class ConnexionBD {

	Connection con = null;
	Statement sta;
	ResultSet re;
	ResultSetMetaData metaBase;

	final private String driver = "com.mysql.jdbc.Driver";
	final private String url = "jdbc:mysql://localhost:3306/testrida";
	final private String usr = "root";
	final private String passwd = "";

	public ConnexionBD() {
		try {
			Class.forName(driver);
			 //System.out.println("driver ok !!!");
			con = DriverManager.getConnection(url, usr, passwd);
			// System.out.println("Overture de la connection");
			sta = con.createStatement();
		} catch (ClassNotFoundException erreurDriver) {
			System.err.println("Erreur de driver :" + erreurDriver);

		} catch (SQLException erreurBD) {
			System.err.println("Erreur de connexion :" + erreurBD);

		}
	}

	public void ajouter(String query) {
		try {
			sta.executeUpdate(query);
			// System.out.println("Auteurs ajoutés à la Base de Donnée");
		} catch (SQLException erreurSQL) {
			System.err.println("Erreur de requete SQL :" + erreurSQL);
		}
	}
	 public ResultSet rechercher(String query) {
	        try {
	            re = sta.executeQuery(query);
	            System.out.println("Element trouvé ");
	        } catch (SQLException ex) {
	            System.err.println(ex);
	        }
	        return re;
	    }
	 
	public void fermer() {
		try {
			if (re != null) {
				re.close();
			}

			if (sta != null) {
				sta.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Problème de fermeture de la Base de données");
		}
		// System.out.println("Base de donnée Fermée");
	}

}