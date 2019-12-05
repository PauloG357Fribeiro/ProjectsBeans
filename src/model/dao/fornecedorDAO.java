package model.dao;

import Connection.ConnectionFactory;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.tableFornecedor;

/**
 *
 * @author aipsi01t
 */
public class fornecedorDAO {
    public void create(tableFornecedor f) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = (PreparedStatement) con.prepareStatement("INSERT INTO "
              + "cadastro_fornecedor(razaoSocial, nomeFantasia, endereco, cnpj, email, charMF) VALUES (?,?,?,?,?,?)");
        stmt.setString(1, f.getRazaoSocial());
        stmt.setString(2, f.getNomeFantasia());
        stmt.setString(3, f.getEndereco());
        stmt.setString(4, f.getCnpj());
        stmt.setString(5, f.getEmail());
        stmt.setString(6, f.getCharMF());
        try {   
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
    }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
    }
    public List<tableFornecedor> read() throws SQLException{
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         List<tableFornecedor> fornecedores = new ArrayList<>();
         try{
            stmt = (PreparedStatement) con.prepareStatement("select * from cadastro_fornecedor");
            rs = stmt.executeQuery();
            while(rs.next()){
                 tableFornecedor f = new tableFornecedor();
                 f.setId(rs.getInt("id"));
f.setRazaoSocial(rs.getString("razaoSocial"));
f.setNomeFantasia(rs.getString("nomeFantasia"));
f.setEndereco(rs.getString("endereco"));
f.setCnpj(rs.getString("cnpj"));
f.setEmail(rs.getString("email"));
f.setCharMF(rs.getString("charMF"));

        
}
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
}
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);}
return fornecedores;

}

    public void update (tableFornecedor f) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("UPDATE cadastro_fornecedor SET razaoSocial = ?, nomeFantasia = ?, endereco = ?, cnpj = ?, email = ?, charMF = ?"
                    + "where id = ?");
            stmt.setString(1, f.getRazaoSocial());
            stmt.setString(2, f.getNomeFantasia());
            stmt.setString(3, f.getEndereco());
            stmt.setString(4, f.getCnpj());
            stmt.setString(5, f.getEmail());
            stmt.setString(6, f.getCharMF());
            stmt.setInt(7, f.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
    
   public void delete (tableFornecedor f) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("DELETE FROM cadastro_fornecedor where id = ?");
            stmt.setInt(1, f.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
public List <tableFornecedor> readForDesc (String desc) throws SQLException{
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<tableFornecedor> fornecedores = new ArrayList<>();
    try{
       stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM cadastro_fornecedor where nomeFantasia LIKE ?");
       stmt.setString(1, "%" + desc + "%");
       rs = stmt.executeQuery();
       
       while(rs.next()){
           tableFornecedor fornecedor = new tableFornecedor();
           fornecedor.setId(rs.getInt("id"));
           fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
           fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
           fornecedor.setEndereco(rs.getString("endereco"));
           fornecedor.setCnpj(rs.getString("cnpj"));
           fornecedor.setEmail(rs.getString("email"));
           fornecedor.setCharMF(rs.getString("charMF"));
           fornecedores.add(fornecedor);
       }
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Erro na leitura" + ex);
        }
    finally{
        ConnectionFactory.closeConnection(null, stmt, rs);
    }
    return fornecedores;
}
}


