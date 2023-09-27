package pe.gob.sunat.apptaxi.model.dao.impl;

import pe.gob.sunat.apptaxi.controller.enums.EstadoSolicitudEnum;
import pe.gob.sunat.apptaxi.model.dao.ISolicitudDao;
import pe.gob.sunat.apptaxi.model.entities.Solicitud;
import pe.gob.sunat.apptaxi.model.entities.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDaoImpl implements ISolicitudDao {
    @Override
    public Integer save(Solicitud solicitud) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;
        try {
            String sql = "INSERT INTO SOLICITUD(ORIGEN, DESTINO, PRECIO, ESTADO, ID_USUARIO, FEC_REGISTRO) VALUE (?, ?, ?, " + EstadoSolicitudEnum.REGISTRADO.getValor() + ",? , SYSDATE())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, solicitud.getOrigen());
            pstmt.setString(2, solicitud.getDestino());
            pstmt.setDouble(3, solicitud.getPrecio());
            pstmt.setLong(4, solicitud.getIdUsuario());

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
    public List<Solicitud> findAllByUser(Long idUser) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        List<Solicitud> solicitudes = new ArrayList<>();
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, ORIGEN, DESTINO, PRECIO, ESTADO, ID_USUARIO, FEC_REGISTRO FROM SOLICITUD WHERE ID_USUARIO = ? ORDER BY ESTADO DESC, FEC_REGISTRO DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, idUser);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                solicitudes.add(new Solicitud(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getDouble(4), rs.getInt(5),
                        rs.getLong(6), rs.getDate(7)));
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

        return solicitudes;
    }

    @Override
    public Solicitud findById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        Solicitud solicitud = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, ORIGEN, DESTINO, PRECIO, ESTADO, ID_USUARIO, FEC_REGISTRO FROM USUARIOS WHERE ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                solicitud = new Solicitud(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getDouble(4), rs.getInt(5),
                        rs.getLong(6), rs.getDate(7));
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

        return solicitud;
    }

    @Override
    public Integer update(Solicitud solicitud) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;

        try {
            String sql = "UPDATE SOLICITUD SET ORIGEN = ?, DESTINO = ?, PRECIO = ?, ESTADO = ? WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, solicitud.getOrigen());
            pstmt.setString(2, solicitud.getDestino());
            pstmt.setDouble(3, solicitud.getPrecio());
            pstmt.setInt(4, solicitud.getEstado());
            pstmt.setLong(5, solicitud.getId());

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
    public Integer delete(Long id) {
        return null;
    }
}
