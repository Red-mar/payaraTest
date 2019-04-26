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

    Set<Session> set;

    private Thread queueThread;

    @OnOpen
    public void onOpen(Session session) {
        logger.log(Level.INFO, "Opening Session {0}", session.getId());
        set.add(session);
        queueThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);

                        Gson gson = new Gson();
                        String queueJson = gson.toJson(facade.GetQueue());

                        session.getBasicRemote().sendText(queueJson);
                    } catch( Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        queueThread.start();
    }

    @OnClose
    public void onClose(Session session) {
        set.remove(session);
        logger.log(Level.INFO, "Closing Session {0}", session.getId());
        queueThread.stop();
    }

    @OnError
    public void onError(Throwable t) {
        logger.log(Level.INFO,"onError",t);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson = new Gson();
        JsonObject jobj = gson.fromJson(message, JsonObject.class);
        try {
            if (jobj.has("op") && jobj.get("op").getAsString().equals(""))
            logger.log(Level.INFO, "Received Message on Session {0}", session.getId());
            //session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
