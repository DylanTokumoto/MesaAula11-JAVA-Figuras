import java.sql.*;

public class App {

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS figuras; CREATE TABLE figuras (id INT PRIMARY KEY, tipo VARCHAR(64), cor VARCHAR(32))";

    private static final String sqlInsert = "INSERT INTO figuras (id, tipo, cor) VALUES (1, 'Circulo', 'Vermelho')";
    private static final String sqlInsert1 = "INSERT INTO figuras (id, tipo, cor) VALUES (2, 'Circulo', 'Amarelo')";
    private static final String sqlInsert2= "INSERT INTO figuras (id, tipo, cor) VALUES (3, 'Quadrado', 'Azul')";
    private static final String sqlInsert3 = "INSERT INTO figuras (id, tipo, cor) VALUES (4, 'Quadrado', 'Branco')";
    private static final String sqlInsert4 = "INSERT INTO figuras (id, tipo, cor) VALUES (5, 'Quadrado', 'Preto')";

    public static void main(String[] args) throws SQLException {

        Connection myConnection = null;

        try {
            myConnection = getConection();

            Statement prep = getConection().createStatement();
            prep.execute(sqlCreateTable);
            prep.execute(sqlInsert);
            prep.execute(sqlInsert1);
            prep.execute(sqlInsert2);
            prep.execute(sqlInsert3);
            prep.execute(sqlInsert4);

            System.out.println("=========listando todos os dados inseridos==========");
            showAll(myConnection);
            System.out.println("=========listando todos os CIRCULOS VERMELHOS==========");
            showAllRed(myConnection);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            myConnection.close();
            System.out.println("Conexao encerrada");
        }


    }

    public static Connection getConection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/aula11-Mesa", "sa", "");
    }

    public static void showAllRed(Connection myConnection) throws SQLException {
        String sqlQuery = "SELECT * FROM figuras WHERE tipo='Circulo' AND cor='Vermelho'";
        Statement prep = myConnection.createStatement();
        ResultSet rs = prep.executeQuery(sqlQuery);

        while (rs.next()){
            System.out.println("ID: " + rs.getInt(1) + " - - " + " tipo: " + rs.getString(2) + " - - " + " cor: " + rs.getString(3));
        }
    }


    public static void showAll(Connection myConnection) throws SQLException {
        String sqlQuery = "SELECT * FROM figuras";
        Statement prepQuery = myConnection.createStatement();
        ResultSet rs = prepQuery.executeQuery(sqlQuery);

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + " - - " + " tipo: " + rs.getString(2) + " - - " + " cor: " + rs.getString(3));
        }
    }


}
