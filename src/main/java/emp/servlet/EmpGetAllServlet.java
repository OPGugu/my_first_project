package emp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import emp.dao.EmpDao;
import emp.vo.Emp;
import emp.vo.impl.EmpDaoImpl;

@WebServlet("/emp/getAll")
public class EmpGetAllServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override //dog -> enter
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpDao dao = new EmpDaoImpl();
		List<Emp> list = dao.selectAll(); //�����
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		resp.getWriter().write(jsonStr); //�n�^���ҥH�Oresp�A��json�O�¤�r�A�ҥH��writer
	}
	

}
