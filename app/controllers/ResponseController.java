package controllers;

import DAO.FieldDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Field;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import service.ResponseService;

import java.util.List;


public class ResponseController extends Controller {

    @Transactional
    public static Result newResponsePage(){
        FieldDAO fieldDAO = new FieldDAO();
        List<Field> fields = fieldDAO.getFields();
        return ok(views.html.addResponse.render(fields));
    }

    public static Result getAjaxJs (){
        return ok(views.js.ajaxResponse.render());
    }

//    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public static Result addResponse(){
        JsonNode json = request().body().asJson();
        ResponseService responseService = new ResponseService();
        responseService.addResponse(json);
        return ok();
    }

    @Transactional
    public static Result listResponses(){
        ResponseService responseService = new ResponseService();
        List arrayNode = responseService.getResponsesJson();
        FieldDAO fieldDAO = new FieldDAO();
        List<Field> fields = fieldDAO.getFields();
        return ok(views.html.listResponses.render(fields, arrayNode));
    }

    public static Result buildTableWS(){
        return ok(views.js.buildTableWS.render());
    }

    public static WebSocket<JsonNode> wsResponses(){
        return new WebSocket<JsonNode>(){
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
                ResponseService.start(in, out);
            }
        };
    }


    public static WebSocket<String> newConnections(){
        return new WebSocket<String>(){
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out){
                ResponseService.addAllConnections(out);
            }
        };
    }

    public static Result counterWS(){
        return ok(views.js.counterResponses.render());
    }


}
