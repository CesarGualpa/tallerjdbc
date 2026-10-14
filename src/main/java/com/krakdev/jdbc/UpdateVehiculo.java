package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateVehiculo {

	private static final Logger log = LoggerFactory.getLogger(UpdateVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				UPDATE vehiculos
				SET marca = ?, modelo = ?, anio = ?, precio = ?, color = ?, disponible = ?
				WHERE placa = ?
				""";

		try {

			con = Conexion.getConnection();

			ps = con.prepareStatement(sql);

			ps.setString(1, "Toyota");
			ps.setString(2, "Corolla XEI");
			ps.setInt(3, 2021);
			ps.setDouble(4, 16500.00);
			ps.setString(5, "Negro");
			ps.setBoolean(6, true);
			ps.setString(7, "ABC1234");

			int filas = ps.executeUpdate();

			log.info("Vehiculo actualizado");
			log.info("Filas actualizadas: " + filas);

		} catch (Exception e) {

			log.error("Error al actualizar vehiculo: " + e.getMessage(), e);

		} finally {

			Conexion.cerrar(ps);
			Conexion.cerrar(con);
		}
	}
}