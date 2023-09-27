package pe.gob.sunat.apptaxi.model.dao.impl;

import pe.gob.sunat.apptaxi.model.entities.Usuario;
import pe.gob.sunat.apptaxi.model.entities.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.sunat.apptaxi.model.dao.IVehiculoDao;
import pe.gob.sunat.apptaxi.model.entities.Vehiculo;

public class VehiculoDaoImpl implements IVehiculoDao {
    @Override
    public Integer save(Vehiculo vehiculo) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;
        try {
            String sql = "INSERT INTO VEHICULO (MARCA, MODELO, COLOR, ANIO, NUM_PLACA,ID_USUARIO) VALUE (?, ?, ?, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehiculo.getMarca());
            pstmt.setString(2, vehiculo.getModelo());
            pstmt.setString(3, vehiculo.getColor());
            pstmt.setInt(4, vehiculo.getAnio());
            pstmt.setString(5, vehiculo.getNumPlaca());
            pstmt.setLong(6, Usuario.getInstance().getId());

            response = pstmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<Vehiculo> findAllById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, MARCA, MODELO, COLOR, ANIO, NUM_PLACA, ID_USUARIO FROM VEHICULO WHERE ID_USUARIO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                vehiculos.add(new Vehiculo(rs.getLong(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getString(6),  rs.getLong(7)));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return vehiculos;
    }

    @Override
    public Vehiculo findById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        Vehiculo vehiculo = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, MARCA, MODELO, COLOR, ANIO, NUM_PLACA, ID_USUARIO FROM VEHICULO WHERE ID= ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                vehiculo = new Vehiculo(rs.getLong(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getString(6),  rs.getLong(7));
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return vehiculo;
    }

    @Override
    public Integer update(Vehiculo vehiculo) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;

        try {
            String sql = "UPDATE VECHICULO SET MARCA = ?, MODELO = ?, COLOR = ?, ANIO = ?, NUM_PLACA = ? WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehiculo.getMarca());
            pstmt.setString(2, vehiculo.getModelo());
            pstmt.setString(3, vehiculo.getColor());
            pstmt.setInt(4, vehiculo.getAnio());
            pstmt.setString(5, vehiculo.getNumPlaca());
            pstmt.setLong(5, vehiculo.getIdVehiculo());

            response = pstmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return response;
    }

    @Override
    public Integer deleteById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;

        try {
            String sql = "DELETE VECHICULO WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            response = pstmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return response;
    }

}
