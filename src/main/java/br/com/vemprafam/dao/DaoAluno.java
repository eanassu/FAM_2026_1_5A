package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vemprafam.pojo.Aluno;

public class DaoAluno {
	private Connection conn;
	private String url = "jdbc:hsqldb:hsql://localhost/";
	private String user = "SA";
	private String password = "";

	public DaoAluno() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(Aluno a) {
		String sql = "INSERT INTO ALUNOS VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getRa());
			pstmt.setString(2, a.getNome());
			pstmt.setDate(3, new java.sql.Date(a.getDataNascimento().getTime()));
			pstmt.setDouble(4, a.getRenda());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(Aluno a) {
		String sql = "UPDATE ALUNOS SET NOME=?,DATANASCIMENTO=?,RENDA=? WHERE RA=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getNome());
			pstmt.setDate(2, new java.sql.Date(a.getDataNascimento().getTime()));
			pstmt.setDouble(3, a.getRenda());
			pstmt.setInt(4, a.getRa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(Aluno a) {
		String sql = "DELETE FROM ALUNOS WHERE RA=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getRa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Aluno buscarPeloRa(int ra) {
		String sql = "SELECT RA,NOME,DATANASCIMENTO,RRENDA FROM ALUNOS WHERE RA=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ra);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				return new Aluno(ra,nome,dataNascimento,renda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Aluno> getLista() {
		List<Aluno> result = new ArrayList<Aluno>();
		String sql = "SELECT RA,NOME,DATANASCIMENTO,RENDA FROM ALUNOS";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				result.add(new Aluno(ra,nome,dataNascimento,renda));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
