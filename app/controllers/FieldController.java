package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Field;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.FieldService;


public class FieldController extends Controller {

    @Transactional
    public static Result addFieldPost(){
        JsonNode json = request().body().asJson();
        FieldService fieldService = new FieldService();
        fieldService.addField(json);
        return ok("ok");
    }

    @Transactional
    public static Result getForm(int id){
        System.out.println("ID = " + id);
        Field field;
        if(id == 0){
            field = new Field();
        }else{
            field = FieldService.getFieldById(id);
        }
        return ok(views.html.addField.render(field));
    }

    public static Result getAjaxJs (){
        return ok(views.js.ajaxField.render());
    }

    @Transactional
    public static Result getFieldList (){
        FieldService fieldService = new FieldService();
        return ok(views.html.fieldList.render(fieldService.getFieldList()));
    }

    public static Result delFieldJs(){
        return ok(views.js.ajaxFieldDel.render());
    }

    @Transactional
    public static Result delById(){
        JsonNode json = request().body().asJson();
        int id = json.asInt();
        FieldService.delById(id);
        System.out.println(id);
        return ok("ok");
    }

}