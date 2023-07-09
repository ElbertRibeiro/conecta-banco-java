package com.elbertribeiro.db;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.logging.Logger;

public class ConexaoDb {
    private static final Logger logger = Logger.getLogger(ConexaoDb.class.getName());

    private void exibirResultados(ResultSet resultSet) throws SQLException {
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

    public void criaConexao(String select, String tnsName) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setURL("jdbc:oracle:thin:@" + tnsName);

            String username = "seu_usuario";
            String password = "sua_senha";

            Connection connection = dataSource.getConnection(username, password);
            logger.info("Conexão estabelecida com sucesso!");

            String selectQuery = select;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            this.exibirResultados(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
