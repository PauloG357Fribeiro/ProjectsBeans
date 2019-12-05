package model.dao;

import Connection.ConnectionFactory;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.tableProduto;

/**
 *
 * @author aipsi01t
 */
public class produtoDAO {
    public void create(tableProduto m) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = (PreparedStatement) con.prepareStatement("INSERT INTO "
              + "cadastro_produto(nome, unidade, valor) VALUES (?,?,?)");
        stmt.setString(1, m.getNome());
        stmt.setString(2, m.getUnidade());
        stmt.setString(3, m.getValor());
        try {   
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
    }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
    }
    public List<tableProduto> read() throws SQLException{
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         List<tableProduto> produtos = new ArrayList<>();
         try{
            stmt = (PreparedStatement) con.prepareStatement("select * from cadastro_produto");
            rs = stmt.executeQuery();
            while(rs.next()){
                 tableProduto m = new tableProduto();
                 m.setId(rs.getInt("id"));
m.setNome(rs.getString("nome"));
m.setUnidade(rs.getString("unidade"));
m.setValor(rs.getString("valor"));
        
}
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
}
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);}
return produtos;

}

    public void update (tableProduto m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("UPDATE cadastro_produto SET nome = ?, unidade = ?, valor = ?"
                    + "where id = ?");
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getUnidade());
            stmt.setString(3, m.getValor());
            stmt.setInt(4, m.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
    
   public void delete (tableProduto m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt =  (PreparedStatement) con.prepareStatement("DELETE FROM cadastro_produto where id = ?");
            stmt.setInt(1, m.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso");    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        }
    finally{ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);}
}
public List <tableProduto> readForDesc (String desc) throws SQLException{
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<tableProduto> produtos = new ArrayList<>();
    try{
       stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM cadastro_produto where nome LIKE ?");
       stmt.setString(1, "%" + desc + "%");
       rs = stmt.executeQuery();
       
       while(rs.next()){
           tableProduto produto = new tableProduto();
           produto.setId(rs.getInt("id"));
           produto.setNome(rs.getString("nome"));
           produto.setUnidade(rs.getString("unidade"));
           produto.setValor(rs.getString("valor"));
           produtos.add(produto);
       }
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Erro na leitura" + ex);
        }
    finally{
        ConnectionFactory.closeConnection(null, stmt, rs);
    }
    return produtos;
}
}


