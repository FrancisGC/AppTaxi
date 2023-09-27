package pe.gob.sunat.apptaxi.model.dao.impl;

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
            String sql = "INSERT INTO VEHICULO (MODELO, COLOR, ANIO, NUM_PLACA,ID_USUARIO) VALUE (?, ?, ?, ?. ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehiculo.getModelo());
            pstmt.setString(2, vehiculo.getColor());
            pstmt.setInt(3, vehiculo.getAnio());
            pstmt.setString(4, vehiculo.getNumPlaca());
            pstmt.setLong(5, vehiculo.getIdUusario());
            
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
    public List<Vehiculo> findAll() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        ResultSet rs = null;

        try {

            String sql = "SELECT IDVEHICULO, MODELO, COLOR, ANIO, NUM_PLACA, ID_USUARIO FROM VEHICULO ";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                vehiculos.add(new Vehiculo(0L, rs.getString(2),
                        rs.getString(3), rs.getInt(4),rs.getString(5),rs.getLong(6)));
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

            String sql = "SELECT IDVEHICULO,  MODELO, COLOR, ANIO, NUM_PLACA, IDUSUARIO FROM VEHICULO WHERE IDVEHICULO= ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                vehiculo = new Vehiculo(0L, rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getLong(6));
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
            String sql = "UPDATE VECHICULO SET  MODELO = ?, COLOR = ?, ANIO = ?, NUM_PLACA = ?, IDUSUARIO = ? WHERE IDVEHICULO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehiculo.getModelo());
            pstmt.setString(2, vehiculo.getColor());
            pstmt.setInt(3, vehiculo.getAnio());
            pstmt.setString(4,vehiculo.getNumPlaca());
            pstmt.setLong(5, vehiculo.getIdUusario());

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
            String sql = "DELETE CLIENTES WHERE IDCLIENTE = ?";
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
