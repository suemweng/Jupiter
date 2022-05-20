package com.laioffer.jupiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.response.Game;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/Hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello Sue!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

//        String gamename = request.getParameter("gamename");
//        response.getWriter().print("Game is: " + gamename);

        response.setContentType("application/json");

//        JSONObject game = new JSONObject();
//        game.put("name", "World of Warcraft");
//        game.put("developer", "Blizzard Entertainment");
//        game.put("release_time", "Feb 11, 2005");
//        game.put("website", "https://www.worldofwarcraft.com");
//        game.put("price", 49.99);
//
//        // Write game information to response body
//        response.getWriter().print(game);


        ObjectMapper mapper = new ObjectMapper();
        Game.Builder builder = new Game.Builder();
        builder.name("World of Warcraft");
        //builder.setDeveloper("Blizzard Entertainment");
        //builder.setReleaseTime("Feb 11, 2005");
        builder.boxArtUrl("https://www.worldofwarcraft.com");
        //builder.setPrice(49.99);


        Game game = builder.build();
        response.getWriter().print(mapper.writeValueAsString(game));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read game information from request body
//        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
//        String name = jsonRequest.getString("name");
//        String developer = jsonRequest.getString("developer");
//        String releaseTime = jsonRequest.getString("release_time");
//        String website = jsonRequest.getString("website");
//        float price = jsonRequest.getFloat("price");
//
//        // Print game information to IDE console
//        System.out.println("Name is: " + name);
//        System.out.println("Developer is: " + developer);
//        System.out.println("Release time is: " + releaseTime);
//        System.out.println("Website is: " + website);
//        System.out.println("Price is: " + price);

        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(IOUtils.toString(request.getReader()), Game.class);
//        Game.Builder builder = objectMapper.readValue(IOUtils.toString(request.getReader()), Game.Builder.class);
//        Game game = builder.build();
        System.out.println("Name is: " + game.getName());
        //System.out.println("Developer is: " + game.getDeveloper());
        //System.out.println("Release time is: " + game.getReleaseTime());
        System.out.println("Website is: " + game.getBoxArtUrl());
        //System.out.println("Price is: " + game.getPrice());


        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}