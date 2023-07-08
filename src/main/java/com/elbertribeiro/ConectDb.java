package com.elbertribeiro;

import java.sql.*;

public class ConectDb {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
        String username = "seu_usuario";
        String password = "sua_senha";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            String selectQuery = obterSelectDoUsuario();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            exibirResultados(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String obterSelectDoUsuario() {
        return "SELECT * FROM sua_tabela";
    }

    private static void exibirResultados(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}