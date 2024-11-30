package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe responsável por realizar operações relacionadas ao login do usuário.
 * 
 * Esta classe inclui métodos para conectar ao banco de dados e verificar a 
 * autenticidade de um usuário com base no login e na senha fornecidos.
 */
public class User {

    /**
     * Método para conectar ao banco de dados.
     * 
     * @return Uma conexão com o banco de dados ou null se a conexão falhar.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Tentativa de carregar o driver do banco de dados e estabelecer conexão
            Class.forName("com.mysql.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Captura silenciosa de exceções (não recomendado em aplicações reais)
        }
        return conn;
    }

    /** Nome do usuário autenticado. */
    public String nome = "";

    /** Resultado da verificação do usuário (true se autenticado, false caso contrário). */
    public boolean result = false;

    /**
     * Método para verificar as credenciais do usuário no banco de dados.
     * 
     * Este método utiliza uma consulta SQL para verificar se o login e a senha fornecidos
     * correspondem a um registro no banco de dados.
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD(); // Conexão com o banco de dados
        // Montagem da instrução SQL
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // Verifica se o ResultSet contém resultados
            if (rs.next()) {
                result = true; // Login bem-sucedido
                nome = rs.getString("nome"); // Recupera o nome do usuário
            }
        } catch (Exception e) {
            // Exceção capturada silenciosamente (não recomendado em aplicações reais)
        }
        return result;
    }
}
