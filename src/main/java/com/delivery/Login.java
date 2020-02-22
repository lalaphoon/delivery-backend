package com.delivery;

import org.json.JSONObject;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        //TODO: read body and fetch the correct user_id
        JSONObject obj = new JSONObject();
        obj.put("user_id", "1111");
        RpcHelper.writeJsonObject(response, obj);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        //TODO: read body and fetch the correct user_id
        JSONObject obj = new JSONObject();
        obj.put("user_id", "1111");
        RpcHelper.writeJsonObject(response, obj);
    }
}
