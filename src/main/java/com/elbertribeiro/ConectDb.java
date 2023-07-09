package com.elbertribeiro;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.logging.Logger;

public class ConectDb {
    private static final Logger logger = Logger.getLogger(ConectDb.class.getName());

    public static void main(String[] args) {
        String tnsName = "nome_do_tns";
        logger.info(tnsName);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setURL("jdbc:oracle:thin:@" + tnsName);

            String username = "seu_usuario";
            String password = "sua_senha";

            Connection connection = dataSource.getConnection(username, password);
            logger.info("Conexão estabelecida com sucesso!");

            String selectQuery = obterSelectDoUsuario();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            exibirResultados(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            logger.info(metaData.getColumnName(i) + "\t");
        }

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                logger.info(resultSet.getString(i) + "\t");
            }
        }
    }
}