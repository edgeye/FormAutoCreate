package com.cdc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdc.model.Item;
import com.cdc.service.Assemble;
import com.cdc.service.Dismantle;
import com.cdc.util.BaseUtil;

public class Creator extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));
		
		if(path.equals("first")) {
			response.sendRedirect("first.jsp");
		}else if(path.equals("original")) {
			String base = request.getParameter("base");
			
			String headTitle = request.getParameter("headTitle");
			String headLine = request.getParameter("headLine");
			
			List<Item> items = Dismantle.convert(base);
			System.out.println("======== size of items: " + items.size() + " ==========");
			
			request.setAttribute("headTitle", headTitle);
			request.setAttribute("headLine", headLine.toLowerCase());
			request.setAttribute("items", items);
			
			request.getRequestDispatcher("second.jsp").forward(request, response);
		}else if(path.equals("dismantle")) {
			String[] names = request.getParameterValues("name");
			String[] types = request.getParameterValues("type");
			String[] needs = request.getParameterValues("need");
			String[] areas = request.getParameterValues("area");
			
			String headTitle = request.getParameter("headTitle");
			String headLine = request.getParameter("headLine");
			
			List<Item> items = Dismantle.dispatch(names, types, needs, areas);
			System.out.println("======== size of items: " + items.size() + " ==========");
			
			request.setAttribute("headTitle", headTitle);
			request.setAttribute("headLine", headLine.toLowerCase());
			request.setAttribute("items", items);
			
			request.getRequestDispatcher("second.jsp").forward(request, response);
		}else if(path.equals("assemble")) {
			String[] titles = request.getParameterValues("title");
			String[] names = request.getParameterValues("names");
			String[] types = request.getParameterValues("types");
			String[] lengths = request.getParameterValues("lengths");
			String[] reqs = request.getParameterValues("req");
			String[] props = request.getParameterValues("prop");
			String[] views = request.getParameterValues("view");
			
			String headTitle = request.getParameter("headTitle");
			String headLine = request.getParameter("headLine");
			BaseUtil.setTitle(headLine.trim());
			BaseUtil.setHead(headTitle.trim());
			
			Assemble.create(titles, names, types, lengths, reqs, props, views);
			
			request.setAttribute("headTitle", headTitle);
			request.setAttribute("headLine", headLine);
			
			request.getRequestDispatcher("third.jsp").forward(request, response);
		}
	}

}
