package com.krakdev.jdbc;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProbarConexion {

	private static final Logger log = LoggerFactory.getLogger(ProbarConexion.class);

	public static void main(String[] args) {

		Connection con = null;

		try {

			con = Conexion.getConnection();

			log.info("Prueba de conexion exitosa");

		} catch (Exception e) {

			log.error("Error durante la prueba de conexion", e);

		} finally {

			Conexion.cerrar(con);

			log.info("Prueba de conexion finalizada");
		}
	}
}