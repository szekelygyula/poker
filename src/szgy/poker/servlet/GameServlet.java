package szgy.poker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONObject;



import org.json.simple.JSONObject;

import szgy.poker.action.GameAction;
import szgy.poker.game.Game;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Feldolgozza a klienstõl kapott kéréseket
	 * @param request A kérés objektuma
	 * @param response A válasz objektuma
	 */
	@SuppressWarnings("unchecked")
	protected void process(HttpServletRequest request, HttpServletResponse response) {
		String action = "";
		GameAction gameAction;
		JSONObject responseObject = new JSONObject();
		try {
			action = request.getParameter("action");
			gameAction = new GameAction((Game) request.getSession().getAttribute("GAME"));
			if("newGame".equals(action)) {
				responseObject = gameAction.newGame((String) request.getParameter("playerName"));
			} else if("newDeal".equals(action)) {
				responseObject = gameAction.newDeal();
			} else if("evaluate".equals(action)) {
				responseObject = gameAction.evaluate();
			} else {
				responseObject.put("status", "warning");
				responseObject.put("msg", "Ismeretlen mûvelet!");
			}
			request.getSession().setAttribute("GAME", gameAction.getGame());
		} catch (Exception e) {
			responseObject.put("status", "error");
			responseObject.put("msg", "A mûvelet során váratlan hiba történt.");
			e.printStackTrace();
		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println(responseObject.toJSONString());
			} catch (Exception t) {
				t.printStackTrace();
			}
		}
	}

}
