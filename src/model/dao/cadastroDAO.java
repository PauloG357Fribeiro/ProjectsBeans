package model.dao;

import Connection.ConnectionFactory;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.tableCadastro;

/**
 *
 * @author aipsi01t
 */
public class cadastroDAO {
    public void create(tableCadastro c) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = (PreparedStatement) con.prepareStatement("INSERT INTO "
              + "cadastro_usuario(nome, endereco, telefone) VALUES (?,?,?)");
        stmt.setString(1, c.getNome());
        stmt.setString(2, c.getEndereco());
        stmt.setString(3, c.getTelefone());
        try {   
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
    }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
    }
    public List<tableCadastro> read() throws SQLException{
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         List<tableCadastro> cadastros = new ArrayList<>();
         try{
            stmt = (PreparedStatement) con.prepareStatement("select * from cadastro_usuario");
            rs = stmt.executeQuery();
            while(rs.next()){
                 tableCadastro c= new tableCadastro();
                 c.setId(rs.getInt("id"));
c.setNome(rs.getString("nome"));
c.setEndereco(rs.getString("endereco"));
c.setTelefone(rs.getString("telefone"));
        
}
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
}
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);}
return cadastros;

}

    public void update (tableCadastro c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("UPDATE cadastro_usuario SET nome = ?, telefone = ?, endereco = ?"
                    + "where id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setInt(4, c.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
    
   public void delete (tableCadastro c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("DELETE FROM cadastro_usuario where id = ?");
            stmt.setInt(1, c.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
public List <tableCadastro> readForDesc (String desc) throws SQLException{
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<tableCadastro> cadastros = new ArrayList<>();
    try{
       stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM cadastro_usuario where nome LIKE ?");
       stmt.setString(1, "%" + desc + "%");
       rs = stmt.executeQuery();
       
       while(rs.next()){
           tableCadastro cadastro = new tableCadastro();
           cadastro.setId(rs.getInt("id"));
           cadastro.setNome(rs.getString("nome"));
           cadastro.setEndereco(rs.getString("endereco"));
           cadastro.setTelefone(rs.getString("telefone"));
           cadastros.add(cadastro);
       }
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Erro na leitura" + ex);
        }
    finally{
        ConnectionFactory.closeConnection(null, stmt, rs);
    }
    return cadastros;
}
}


