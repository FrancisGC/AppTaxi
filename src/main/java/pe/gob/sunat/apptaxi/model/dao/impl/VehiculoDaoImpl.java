package pe.gob.sunat.apptaxi.model.dao.impl;

import pe.gob.sunat.apptaxi.model.entities.Cliente;
import pe.gob.sunat.apptaxi.model.entities.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.gob.sunat.apptaxi.model.dao.IClienteDao;

public class ClienteDaoImpl implements IClienteDao {
    @Override
    public Integer save(Cliente cliente) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;
        try {
            String sql = "INSERT INTO CLIENTES (NOMBRES, APELLIDOS, EMAIL, TELEFONO) VALUE (?, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            
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
    public List<Cliente> findAll() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs = null;

        try {

            String sql = "SELECT IDCLIENTE, APELLIDOS, NOMBRES, EMAIL, TELEFONO FROM CLIENTES ";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(0L, rs.getString(2),
                        rs.getString(3), rs.getString(4),rs.getString(5) ));
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

        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        Cliente cliente = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT IDCLIENTE,  APELLIDOS, NOMBRES, EMAIL, TELEFONO FROM CLIENTES WHERE IDCLIENTE = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(0L, rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
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

        return cliente;
    }

    @Override
    public Integer update(Cliente cliente) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        int response = 0;

        try {
            String sql = "UPDATE CLIENTES SET  APELLIDOS = ?, NOMBRES = ?, EMAIL = ?, TELEFONO= ? WHERE IDCLIENTE = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getApellido());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4,cliente.getTelefono());
            pstmt.setLong(5, cliente.getId());

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
