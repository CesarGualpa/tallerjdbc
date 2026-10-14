package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteVehiculo {

	private static final Logger log = LoggerFactory.getLogger(DeleteVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				DELETE FROM vehiculos
				WHERE placa = ?
				""";

		try {

			con = Conexion.getConnection();

			ps = con.prepareStatement(sql);

			ps.setString(1, "DEF5678");

			int filas = ps.executeUpdate();

			log.info("Vehiculo eliminado");
			log.info("Filas eliminadas: " + filas);

		} catch (Exception e) {

			log.error("Error al eliminar vehiculo: " + e.getMessage(), e);

		} finally {

			if (ps != null) {

				try {

					ps.close();

				} catch (SQLException e) {

					log.error("Error al cerrar PreparedStatement", e);
				}
			}

			if (con != null) {

				try {

					con.close();

				} catch (SQLException e) {

					log.error("Error al cerrar conexion", e);
				}
			}
		}
	}
}