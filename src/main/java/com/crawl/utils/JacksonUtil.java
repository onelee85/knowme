package com.crawl.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/23 13:23
 */
public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj){
        StringWriter sw = new StringWriter();
        try{
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, obj);
            gen.close();
        }catch (IOException e){
            logger.error("bean2Json:{}",e);
        }

        return sw.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        try{
            return mapper.readValue(jsonStr, objClass);
        }catch (IOException e){
            logger.error("json2Bean:{}",e);
        }
        return  null;
    }

    public static Map<Object, Object> json2Map(String jsonStr){
        Map<Object, Object> maps = json2Bean(jsonStr, Map.class);
        return maps;
    }

    public static String map2Json(Map<Object, Object> map){
        return bean2Json(map);
    }

    public static void main(String[] args) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        map.put("name", "1");
        map.put("book", "2");
        System.out.println(map2Json(map));
        System.out.println(json2Map("{\"book\":\"2\",\"name\":\"1\"}"));
    }

}
