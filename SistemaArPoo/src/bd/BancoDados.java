package bd;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/sistema_poo_ar", "postgres", "123");
            }
            return connection;
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,
                    "Não foi possivel encontrar o drive de acesso " +
                            "ao banco de dados.");
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar o banco de dados.");
            return null;
        }
    }

    public static List<String[]> executaQuery (String sql){
        try {
            List<String[]> dados = new ArrayList<>();
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int numeroColunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] linha = new String[numeroColunas];
                for (int i = 1; i <= numeroColunas; i++){
                    linha[i - 1] = rs.getString(i);
                }
                dados.add(linha);
            }
            return dados;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,
                    "Não foi possivel consultar o banco de dados.");
            return new ArrayList<>();
        }
    }
}
