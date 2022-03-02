package kr.co.bttf.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.co.bttf.action.Action;
import kr.co.bttf.action.ActionForward;
import kr.co.bttf.DAO.CssDAO;
import kr.co.bttf.DAO.MemberDAO;
import kr.co.bttf.DTO.UserDTO;

public class CssBoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		CssDAO bdao = new CssDAO();
		MemberDAO mdao = new MemberDAO();
		UserDTO udto = (UserDTO)session.getAttribute("session_id");
		System.out.println(udto);
		String user_id = udto.getUser_id();
		String recommedStatus = "uncheck";
		
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		// 댓글 페이징
		int totalCnt = bdao.getCssReplyCnt(post_id);
		String temp = request.getParameter("page");
		
		int page = 0;
		page = temp == null ? 1 : Integer.parseInt(temp);
		int pageSize = 5;
		int endRow = page * 5;
		int startRow = endRow -4;
		int startPage = (page - 1) / pageSize * pageSize + 1;
		int endPage = startPage + pageSize - 1;
		int totalPage = (totalCnt - 1) / pageSize + 1;

		endPage = endPage > totalPage ? totalPage : endPage;

		request.setAttribute("totalPage", totalPage);
		request.setAttribute("nowPage", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCnt", totalCnt);

		// 조회수 update와
		bdao.updateCssReadCount(post_id);
		// 댓글조회를 먼저 해야
		request.setAttribute("replylist", bdao.getCssReplyList(startRow, endRow, post_id));
		// 상세내역을 모두 불러온다. 순서가 중요
		request.setAttribute("board", bdao.getCssDetail(post_id));
		
		System.out.println("useridid" + user_id);
		if(mdao.checkRecommend(user_id)) {
			recommedStatus = "check";
		}
		request.setAttribute("recommedStatus", recommedStatus);

		System.out.println(request.getAttribute("recommedStatus"));
		forward.setRedirect(false);
		forward.setPath(request.getContextPath() + "/app/pages/cssview.jsp");
		

		return forward;
	}

}
