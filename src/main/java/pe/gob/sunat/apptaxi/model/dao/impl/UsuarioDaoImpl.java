package pe.gob.sunat.apptaxi.model.dao.impl;

import pe.gob.sunat.apptaxi.controller.enums.EstadoEnum;
import pe.gob.sunat.apptaxi.model.dao.IUsuarioDao;
import pe.gob.sunat.apptaxi.model.entities.Usuario;
import pe.gob.sunat.apptaxi.model.entities.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements IUsuarioDao {
    @Override
    public Integer save(Usuario usuario) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;
        try {
            String sql = "INSERT INTO USUARIOS(NUMERO, PASSWORD, PERFIL, APELLIDOS, NOMBRES, ESTADO, FECHA_REGISTRO) VALUE (?, ?, ?, ?, ?, " + EstadoEnum.ACTIVO.getValor() + ", SYSDATE())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNumero());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getPerfil());
            pstmt.setString(4, usuario.getApellidos());
            pstmt.setString(5, usuario.getNombres());

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
    public List<Usuario> findAll() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, NUMERO, PASSWORD, PERFIL, APELLIDOS, NOMBRES, ESTADO, FECHA_REGISTRO FROM USUARIOS WHERE ESTADO = 1";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getDate(8)));
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

        return usuarios;
    }

    @Override
    public Usuario findById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        Usuario usuario = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT ID, NUMERO, PASSWORD, PERFIL, APELLIDOS, NOMBRES, ESTADO, FECHA_REGISTRO FROM USUARIOS WHERE ID = ? AND ESTADO = 1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                usuario = new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getDate(8));
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

        return usuario;
    }

    @Override
    public Integer update(Usuario usuario) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;

        try {
            String sql = "UPDATE USUARIOS SET NUMERO = ?, PASSWORD = ?, PERFIL = ?, APELLIDOS = ?, NOMBRES = ? WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNumero());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getPerfil());
            pstmt.setString(4, usuario.getApellidos());
            pstmt.setString(5, usuario.getNombres());
            pstmt.setLong(6, usuario.getId());

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
            String sql = "UPDATE USUARIOS SET ESTADO = 0 WHERE ID = ?";
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

    @Override
    public String findPasswordByNumber(String numero) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        String response = " ";
        ResultSet rs = null;

        try {

            String sql = "SELECT PASSWORD FROM USUARIOS WHERE NUMERO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numero);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                response = rs.getString(1);
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

        return response;
    }

    @Override
    public Integer findNumber(String numero) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;
        ResultSet rs = null;

        try {

            String sql = "SELECT EXISTS(SELECT NUMERO FROM USUARIOS WHERE NUMERO = ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numero);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                response = rs.getInt(1);
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

        return response;
    }
}
