package com.decagon.interview.stageone.ui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.decagon.interview.stageone.api.ApiService.getUsernameWithHighestCommentCount;
import static com.decagon.interview.stageone.api.ApiService.getUsernames;
import static com.decagon.interview.stageone.api.ApiService.getUsernamesSortedByRecordDate;

@WebServlet(name = "ApiProcessor", urlPatterns = "/processor")

public class ApiProcessor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        try {

            String selectedOption = request.getParameter("cmd");
            String pageId = request.getParameter("pid");

            if(selectedOption.equals("1"))
                writer.append(""+getUsernames(Integer.valueOf(pageId)));
            if(selectedOption.equals("2"))
                writer.append(""+getUsernameWithHighestCommentCount(Integer.valueOf(pageId)));
            if(selectedOption.equals("3"))
                writer.append(""+getUsernamesSortedByRecordDate(Integer.valueOf(pageId)));

            writer.flush();
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
