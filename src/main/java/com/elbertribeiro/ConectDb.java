package com.elbertribeiro;

import com.elbertribeiro.db.ConexaoDb;

import java.util.logging.Logger;

public class ConectDb {
    private static final Logger logger = Logger.getLogger(ConectDb.class.getName());

    public static void main(String[] args) {
        String tnsName = "nome_do_tns";
        logger.info(tnsName);
        new ConexaoDb().criaConexao(obterSelectDoUsuario(), tnsName);
    }

    private static String obterSelectDoUsuario() {
        return "SELECT * FROM sua_tabela";
    }
}