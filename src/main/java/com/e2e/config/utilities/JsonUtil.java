package com.e2e.config.utilities;
import com.e2e.config.config.ConfigurationManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Configuration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.io.IOException;
import java.lang.reflect.Method;


public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper mapper = new ObjectMapper();
    static PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType("com.baeldung.jackson.inheritance")
            .allowIfSubType("java.util.ArrayList")
            .allowIfSubType("java.util.Arrays$ArrayList")
            .build();

    public JsonUtil(){
    }
    public static Object getObjectFromJsonString(String json){
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }
    public static Object readObjectFromJsonObject(Object doc,String jsonpath){
        return JsonPath.read(doc,jsonpath);
    }
    public static <T> T getObjectFromJsonString(String input, Class<T> typeKey){
        mapper.activateDefaultTyping(ptv);
        T obj = null;
        try {
            obj = mapper.readValue(input,typeKey);
        }catch (IOException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }
    public static <T> T getObjectFromJsonStringAndPath(String input, String jpath, Class<T> typekey){
        T obj = null;
        try {
            obj = JsonPath.parse(input).read(jpath,typekey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
    public static String convertPojoToJsonString(Object javaO){
        String output = "";
        mapper.activateDefaultTyping(ptv);
        try {
            output = mapper.writeValueAsString(javaO);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return output;
    }

    public static String loadJsonData(Method methodName){
        String OS = System.getProperty("os.name").toLowerCase();
        String separator = (OS.indexOf("win")>=0)? "\\":"/";
        String path = (OS.indexOf("win")>=0)? ConfigurationManager.configuration().path2testData()
                :
                ConfigurationManager.configuration().path2testData().replaceAll("\\\\","/");
        String currentWorkingDir = System.getProperty("user.dir");
        String fileName = currentWorkingDir + separator + path +methodName.getName() +".json";
        String jsonData = IOUtils.loadFileToString(fileName);
        return jsonData;
    }
}
