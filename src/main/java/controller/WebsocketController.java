/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.facade.StreamFacade;
import sun.util.calendar.BaseCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author steve
 */
@ServerEndpoint("/queueSock")
public class WebsocketController {

    @Inject
    private StreamFacade facade;

    private static final Logger logger = Logger.getLogger(WebsocketController.class.getName());

    private static List<Session> list = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        logger.log(Level.INFO, "Opening Session {0}", session.getId());
        list.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        list.remove(session);
        logger.log(Level.INFO, "Closing Session {0}", session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        logger.log(Level.INFO,"onError",t);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson = new Gson();
        logger.log(Level.INFO,"got message");
        try {
            for (Session s: list) {
                logger.log(Level.INFO,"Update Session {0}", s.getId());
                updateSession(s);
            }
            logger.log(Level.INFO, "Received Message on Session {0}", session.getId());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "{0}", ex);
        }
    }

    private void updateSession(Session session) {
        logger.log(Level.INFO, "Updating {0}]", session.getId());
        try {
            Gson gson = new Gson();
            String queueJson = gson.toJson(facade.GetQueue());
            logger.log(Level.INFO, "Sending {0}", queueJson);
            session.getBasicRemote().sendText(queueJson);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
