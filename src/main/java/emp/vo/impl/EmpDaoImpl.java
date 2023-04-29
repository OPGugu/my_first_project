package emp.vo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import emp.dao.EmpDao;

//import com.mysql.cj.protocol.Resultset;
//import com.mysql.cj.xdevapi.PreparableStatement;

import emp.vo.Emp;

public class EmpDaoImpl implements EmpDao {
	
	@Override
	public int insert(Emp emp) {
		String sql = "insert into EMP values(?,?,?,?,?,?,?,?)";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE", "root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setTimestamp(5, emp.getHiredate());
			pstmt.setDouble(6, emp.getSal());
			pstmt.setDouble(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	@Override
	public int deleteByEmpno(Integer empno) {
		String sql = "delete from EMP where EMPNO = ?";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE", "root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, empno);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	@Override
	public int updateByEmpno(Emp emp) {
		String sql = "update EMP "
				+ "set "
				+ "     ENAME = ?,"
				+ "     JOB = ?,"
				+ "     MGR = ?,"
				+ "     HIREDATE = ?,"
				+ "     SAL = ?,"
				+ "     COMM = ?,"
				+ "     DEPTNO = ? "
				+ "where EMPNO = ?";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE", "root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getMgr());
			pstmt.setTimestamp(4, emp.getHiredate());
			pstmt.setDouble(5, emp.getSal());
			pstmt.setDouble(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpno());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	@Override
	public Emp selectByEmpno(Integer empno) {
		String sql = "delete from EMP where EMPNO = ?";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE", "root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, empno);
			try (ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Emp emp = new Emp();
					emp.setEmpno(rs.getInt("EMPNO"));
					emp.setEname(rs.getString("ENAME"));
					emp.setJob(rs.getString("JOB"));
					emp.setMgr(rs.getInt("MGR"));
					emp.setHiredate(rs.getTimestamp("HIREDATE"));
					emp.setSal(rs.getDouble("SAL"));
					emp.setComm(rs.getDouble("COMM"));
					emp.setDeptno(rs.getInt("DEPTNO"));
					return emp;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<Emp> selectAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String sql = "select * from EMP";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE", "root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		){
			List<Emp> list = new ArrayList<Emp>();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setMgr(rs.getInt("MGR"));
				emp.setHiredate(rs.getTimestamp("HIREDATE"));
				emp.setSal(rs.getDouble("SAL"));
				emp.setComm(rs.getDouble("COMM"));
				emp.setDeptno(rs.getInt("DEPTNO"));
				list.add(emp);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		EmpDao dao = new EmpDaoImpl();
		for(Emp emp : dao.selectAll()) {
			System.out.println(emp.getEname());
		}
	}
	
	
	
	
	
}
