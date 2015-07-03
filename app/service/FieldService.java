package service;

import DAO.FieldDAO;
import com.fasterxml.jackson.databind.JsonNode;
import models.Field;
import models.FieldType;
import models.Option;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FieldService {

    public void addField(JsonNode json){

        Field field = new Field();

        Iterator<JsonNode> nodeIterator = json.elements();
        while(nodeIterator.hasNext()){
            JsonNode node = nodeIterator.next();
            System.err.println(node.findPath("name").textValue() + " " + node.findPath("value").textValue());

            if(node.findPath("name").textValue().equals("label")){
                field.setLabel(node.findPath("value").textValue());
            }else if(node.findPath("name").textValue().equals("typeField")){
                field.setFieldType(FieldType.valueOf(node.findPath("value").textValue()));
            }else if(node.findPath("name").textValue().equals("required")) {
                field.setRequired(true);
            }else if(node.findPath("name").textValue().equals("active")) {
                field.setActive(true);
            }else if(node.findPath("name").textValue().equals("options")) {
                String [] options = node.findPath("value").textValue().split("\\r\\n");
                System.out.println(options.length);
                List<Option> optionList = new ArrayList<>();
                for(int i = 0; i < options.length; i++){
                    if(!options[i].isEmpty()){
                        Option newOption = new Option();
                        newOption.setName(options[i]);
                        optionList.add(newOption);
                    }
                }
                System.out.println(optionList.size());
                field.setOptions(optionList);
            }

        }

        if(field.getFieldType().name().equalsIgnoreCase("single line text") || field.getFieldType().name().equalsIgnoreCase("multi line text")
                || field.getFieldType().name().equalsIgnoreCase("date")){
            field.setOptions(null);
        }

        FieldDAO fieldDAO = new FieldDAO();
        fieldDAO.saveField(field);
    }

    public List<Field> getFieldList(){
        FieldDAO fieldDAO = new FieldDAO();
        return fieldDAO.getFields();
    }

    public static Field getFieldById(int id){
        return FieldDAO.getById(id);
    }

    public static void delById (int id){
        FieldDAO.delById(id);
    }

}
