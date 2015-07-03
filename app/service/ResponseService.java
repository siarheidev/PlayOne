package service;

import DAO.FieldDAO;
import DAO.ResponseDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Field;
import models.Response;
import models.Value;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResponseService {

    private static long count;

    static {
        count = ResponseDAO.countResponse();
    }

    private static List<WebSocket.Out<JsonNode>> connections = new ArrayList<WebSocket.Out<JsonNode>>();
    private static List<WebSocket.Out<String>> allConnections = new ArrayList<WebSocket.Out<String>>();

    public void addResponse(JsonNode jsonNode) {

        ObjectNode node = Json.newObject();
        List<Value> values = new ArrayList<>();
        FieldDAO fieldDAO = new FieldDAO();
        List<Field> fields = fieldDAO.getFields();
        Iterator<JsonNode> itr = jsonNode.elements();
        while (itr.hasNext()) {
            JsonNode json = itr.next();
            String label = json.findPath("name").textValue();
            String val = json.findPath("value").textValue();

            if (!val.equals("")) {

                node.put(label, val);

                Value newValue = new Value();
                newValue.setValue(val);
                System.out.println("JSON: " + json);
                for (Field field : fields) {
                    if (field.getLabel().equals(label)) {

                        newValue.setField(field);
                    }
                }
                values.add(newValue);
            }
        }

        Response response = new Response();
        response.setValues(values);

        JPA.em().persist(response);
        responseWS(node);

        count++;
        countWrite();
    }

    public List<List<String>> getResponsesJson() {  //добавить в парамер fields (дублируется в контроллере)

        FieldDAO fieldDAO = new FieldDAO();
        List<Field> fields = fieldDAO.getFields();
        ResponseDAO responseDAO = new ResponseDAO();
        List<Response> responses = responseDAO.getResponseList();

        List<List<String>> result = new ArrayList<>();

        for (Response response : responses) {
            List<String> responseList = new ArrayList<>();
            for (Field field : fields) {
                String fiedLabel = field.getLabel();
                String val = "N/A";
                for (Value value : response.getValues()) {
                    if (value.getField().getLabel().equals(field.getLabel())) {
                        val = value.getValue();
                        break;
                    }
                }
                responseList.add(val);
            }
            result.add(responseList);
        }
        System.out.println("allConnections " + allConnections.size()); //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
        return result;
    }


    public List<Response> getResponses() {
        ResponseDAO responseDAO = new ResponseDAO();
        return responseDAO.getResponseList();
    }

    public static void start(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {
        connections.add(out);

    }

    public static void responseWS(JsonNode json) {
        if (!connections.isEmpty()) {
            for (WebSocket.Out<JsonNode> out : connections) {
                out.write(json);
            }
        }
    }

    public static void addAllConnections(WebSocket.Out<String> out) {
        out.write("" + count);
        allConnections.add(out);
    }

    public static void countWrite() {
        if (!allConnections.isEmpty()) {
            for (WebSocket.Out<String> out : allConnections) {
                System.out.println("socket write");
                out.write("" + count);
            }
        }
    }
}