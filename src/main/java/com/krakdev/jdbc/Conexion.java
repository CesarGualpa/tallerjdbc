package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

	private static final Logger log = LoggerFactory.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/tallerjdbc";
	private static final String USER = "postgres";

	public static Connection getConnection() {

		String password = System.getenv("TALLERJDBC_DB_PASSWORD");

		try {

			Connection con = DriverManager.getConnection(URL, USER, password);

			log.info("Conexion realizada correctamente");

			return con;

		} catch (SQLException e) {

			log.error("Error de conexion: " + e.getMessage(), e);

			throw new RuntimeException("No se pudo conectar a PostgreSQL", e);
		}
	}

	public static void cerrar(AutoCloseable recurso) {

		if (recurso != null) {

			try {

				recurso.close();

			} catch (Exception e) {

				log.error("Error al cerrar recurso JDBC", e);
			}
		}
	}
}