package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakdev.entidades.Vehiculo;

public class InsertVehiculo {

	private static final Logger log = LoggerFactory.getLogger(InsertVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				INSERT INTO vehiculos
				(placa, marca, modelo, anio, precio, color, disponible)
				VALUES (?, ?, ?, ?, ?, ?, ?)
				""";

		try {

			con = Conexion.getConnection();

			ps = con.prepareStatement(sql);

			Vehiculo vehiculo = new Vehiculo(
					"ABC1234",
					"Toyota",
					"Corolla",
					2020,
					15500.75,
					"Blanco",
					true
			);

			ps.setString(1, vehiculo.getPlaca());
			ps.setString(2, vehiculo.getMarca());
			ps.setString(3, vehiculo.getModelo());
			ps.setInt(4, vehiculo.getAnio());
			ps.setDouble(5, vehiculo.getPrecio());
			ps.setString(6, vehiculo.getColor());
			ps.setBoolean(7, vehiculo.isDisponible());

			int filas = ps.executeUpdate();

			log.info("Vehiculo insertado: " + vehiculo.getPlaca());
			log.info("Filas insertadas: " + filas);

        } catch (Exception e) {

            log.error("Error al insertar vehiculo: " + e.getMessage(), e);

        } finally {

            Conexion.cerrar(ps);
            Conexion.cerrar(con);

        }
    }
}