package tarefa0605;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	
	public void salvar(Aluno a) throws SQLException {
		
		try {
			con = db.getConexao();
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
			db.closeConexao();
		}
	}
	
	public List<Aluno> listar() throws SQLException {
		    List<Aluno> listaAlunos = new ArrayList<>();
		    
		    try {
		        con = db.getConexao();
		        sql = "SELECT * FROM aluno";
		        pst = con.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();
		        
		        while(rs.next()) {
		            Aluno a = new Aluno();
		            a.setId(rs.getInt("id"));
		            a.setNome(rs.getString("nome"));
		            a.setSexo(rs.getString("sexo"));
		            a.setDt_nasc(rs.getDate("dt_nasc").toLocalDate());
		            listaAlunos.add(a);
		        }
		    } catch (Exception e) {
		        System.out.println(e);
		    } finally {
		        pst.close();
		        db.closeConexao();
		    }
		    
		    return listaAlunos;
		}		
	
	public void apagar(int id) throws SQLException {
	    try {
	        con = db.getConexao();
	        sql = "DELETE FROM aluno WHERE id=?";
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, id);
	        pst.executeUpdate();
	        System.out.println("\nAluno excluído com sucesso!");
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        pst.close();
	        db.closeConexao();
	    }
	}
	
	public void alterar(Aluno a) throws SQLException {
	    try {
	        con = db.getConexao();
	        sql = "UPDATE aluno SET nome=?, sexo=?, dt_nasc=? WHERE id=?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, a.getNome());
	        pst.setString(2, a.getSexo());
	        Date dataSql = Date.valueOf(a.getDt_nasc());
	        pst.setDate(3, dataSql);
	        pst.setInt(4, a.getId());
	        pst.executeUpdate();
	        System.out.println("\nDados do aluno atualizados com sucesso!");
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        pst.close();
	        db.closeConexao();
	    }
	}
}
