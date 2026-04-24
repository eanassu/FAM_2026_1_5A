package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vemprafam.pojo.Funcionario;

@Repository
public class DaoFuncionario {

	private Connection conn;

	public DaoFuncionario() {
		super();
	}
	@Autowired
	public DaoFuncionario(DataSource dataSource) {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Funcionario f) {
		String sql = "INSERT INTO FUNCIONARIOS VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getRe());
			pstmt.setString(2, f.getNome());
			pstmt.setDate(3, new java.sql.Date(f.getDataAdmissao().getTime()));
			pstmt.setDouble(4, f.getSalario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Funcionario f) {
		String sql = "UPDATE FUNCIONARIOS SET NOME=?,DATAADMISSAO=?,SALARIO=? WHERE RE=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getNome());
			pstmt.setDate(2, new java.sql.Date(f.getDataAdmissao().getTime()));
			pstmt.setDouble(3, f.getSalario());
			pstmt.setInt(4, f.getRe());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Funcionario f) {
		String sql = "DELETE FROM FUNCIONARIOS WHERE RE=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getRe());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Funcionario buscarPeloRe(int re) {
		String sql = "SELECT RE,NOME,DATAADMISSAO,SALARIO FROM FUNCIONARIOS WHERE RE=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String nome = rs.getString(2);
				Date dataAdmissao = rs.getDate(3);
				Double salario = rs.getDouble(4);
				return new Funcionario(re,nome,dataAdmissao,salario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Funcionario> getLista() {
		List<Funcionario> result = new ArrayList<Funcionario>();
		String sql = "SELECT RE,NOME,DATAADMISSAO,SALARIO FROM FUNCIONARIOS";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int re = rs.getInt(1);
				String nome = rs.getString(2);
				Date dataAdmissao = rs.getDate(3);
				Double salario = rs.getDouble(4);
				result.add(new Funcionario(re,nome,dataAdmissao,salario));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}