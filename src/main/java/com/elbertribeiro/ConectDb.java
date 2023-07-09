package com.elbertribeiro;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;

public class ConectDb {
    private static final Logger logger = LoggerFactory.getLogger(ConectDb.class);

    public static void main(String[] args) {
        String tnsName = "nome_do_tns";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setURL("jdbc:oracle:thin:@" + tnsName);

            String username = "seu_usuario";
            String password = "sua_senha";

            Connection connection = dataSource.getConnection(username, password);
            logger.debug("Conexão estabelecida com sucesso!");

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
            logger.debug(metaData.getColumnName(i) + "\t");
        }

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                logger.debug(resultSet.getString(i) + "\t");
            }
        }
    }
}