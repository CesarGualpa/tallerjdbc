package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakdev.entidades.Vehiculo;

public class SelectVehiculo {

	private static final Logger log = LoggerFactory.getLogger(SelectVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				SELECT * FROM vehiculos
				""";

		try {

			con = Conexion.getConnection();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				String placa = rs.getString("placa");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				int anio = rs.getInt("anio");
				double precio = rs.getDouble("precio");
				String color = rs.getString("color");
				boolean disponible = rs.getBoolean("disponible");
				int kilometraje = rs.getInt("kilometraje");

				Vehiculo vehiculo = new Vehiculo(
						placa,
						marca,
						modelo,
						anio,
						precio,
						color,
						disponible,
						kilometraje
				);

				System.out.println(vehiculo);

				log.info("Vehiculo encontrado: " + placa);
			}

		} catch (Exception e) {

			log.error("Error al listar vehiculos: " + e.getMessage(), e);

		} finally {

			Conexion.cerrar(rs);
			Conexion.cerrar(ps);
			Conexion.cerrar(con);
		}
	}
}