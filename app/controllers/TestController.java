package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.FieldType;
import models.Option;
import org.hibernate.Session;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TestController extends Controller {

    static int i;

    public static Result setI(int newInt){
        i += newInt;
        return ok("dd");
    }

    public static Result getI(){
        return ok("" + i);
    }


//    public static Result getAjaxJs (){
//        return ok(views.js.ajaxFieldTest.render());
//    }
//
//    @Transactional
//    public static Result addFieldPost(){
//        JsonNode json = request().body().asJson();
//        System.out.println(json);
//        addField(json);
//        return ok(views.html.addField.render());
//    }
//
//    public static Result testForm(){
//        FieldTest field = new FieldTest();
//
//        List<Option> opt = new ArrayList<>();
//        Option o1 = new Option();
//        Option o2 = new Option();
//        o1.setName("o1");
//        o2.setName("o2");
//        opt.add(o1);
//        opt.add(o2);
//        field.setOptions(opt);
//
//        field.setFieldType(FieldType.DATE);
//        field.setLabel("My label");
//
//        return ok(views.html.test.render(field));
//    }
//
//
//
//    public static void addField(JsonNode json){
//
//        FieldTest field = new FieldTest();
//
//        Iterator<JsonNode> nodeIterator = json.elements();
//        while(nodeIterator.hasNext()){
//            JsonNode node = nodeIterator.next();
//            System.err.println(node.findPath("name").textValue() + " " + node.findPath("value").textValue());
//
//            if(node.findPath("name").textValue().equals("label")){
//                field.setLabel(node.findPath("value").textValue());
//            }else if(node.findPath("name").textValue().equals("typeField")){
//                field.setFieldType(FieldType.valueOf(node.findPath("value").textValue()));
//            }else if(node.findPath("name").textValue().equals("required")) {
//                field.setRequired(true);
//            }else if(node.findPath("name").textValue().equals("active")) {
//                field.setActive(true);
//            }else if(node.findPath("name").textValue().equals("options")) {
//                String [] options = node.findPath("value").textValue().split("\\r\\n");
//                System.out.println(options.length);
//                List<Option> optionList = new ArrayList<>();
//                for(int i = 0; i < options.length; i++){
//                    if(!options[i].isEmpty()){
//                        Option newOption = new Option();
//                        newOption.setName(options[i]);
//                        optionList.add(newOption);
//                    }
//                }
//                System.out.println(optionList.size());
//                field.setOptions(optionList);
//            }
//
//        }
//
//        if(field.getFieldType().name().equalsIgnoreCase("single line text") || field.getFieldType().name().equalsIgnoreCase("multi line text")
//                || field.getFieldType().name().equalsIgnoreCase("date")){
//            field.setOptions(null);
//        }
//
//        saveField(field);
//    }
//
//    public static void saveField(FieldTest field) {
//        List<FieldTest> fields = getFields();
//        boolean flag = true;
//        for(int i = 0; i < fields.size(); i++){
//            FieldTest existingField = fields.get(i);
//            if(existingField.getLabel().equalsIgnoreCase(field.getLabel())){
//                Session session = (Session) JPA.em().getDelegate();
//                List<Option> options = existingField.getOptions();
//                for(Option option: options){
//                    session.delete(option);
//                }
//                existingField.setActive(field.getActive());
//                existingField.setRequired(field.getRequired());
//                existingField.setFieldType(field.getFieldType());
//                existingField.setOptions(field.getOptions());
//                session.saveOrUpdate(existingField);
//                flag = false;
//            }
//        }
//        if(flag) {
//            JPA.em().persist(field);
//        }
//    }
//
//    public static List<FieldTest> getFields(){
//        Session s = (Session)JPA.em().getDelegate();
//        List<FieldTest> fields = s.createCriteria(FieldTest.class).list();
//        return fields;
//    }
//
//    @Transactional
//    public static Result byId (){
//        FieldTest field = JPA.em().find(FieldTest.class, 1190);
//        System.out.println(field.getFieldType());
//
//        return ok();
//    }

}
