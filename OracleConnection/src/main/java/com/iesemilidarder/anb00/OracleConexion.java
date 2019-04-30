package com.iesemilidarder.anb00;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class OracleConexion {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","friends");
        if (c != null) {
            System.out.println("Conectado");
            String[] nombres = { "MANUEL", "JACINTO", "TOMAS", "AMBROSIO", "PEDRO", "TADEO"};
            final int inicial = 20;
            for (int i = 0; i < nombres.length; i++) {
                PreparedStatement ps = c.prepareStatement("InSERT INTO PERSONA(IDPERSONA, NOMBRE) VALUES(?, ?)");
                ps.setInt(1, i + inicial);
                ps.setString(2, nombres[i]);
                ps.execute();
                System.out.println(ps);
            }
            PreparedStatement commit = c.prepareStatement("COMMIT");
            commit.execute();
            c.close();
        }

    }
}