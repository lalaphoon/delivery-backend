package com.delivery;

import org.json.JSONException;
import org.json.JSONObject;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/hello")
public class Hello extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();

        if (request.getParameter("username") != null) {
            String username = request.getParameter("username");
            try {
                obj.put("username", username);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        RpcHelper.writeJsonObject(response, obj);

    }
}
