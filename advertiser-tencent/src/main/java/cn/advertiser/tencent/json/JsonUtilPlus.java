package cn.advertiser.tencent.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yujiumin
 * @date 2022/11/20
 */
public class JsonUtilPlus {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将对象转换为json字符串，如果转换失败会抛出异常
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJsonString(Object object) throws JsonProcessingException {
        if (object instanceof String) {
            return (String) object;
        } else if (object instanceof Number) {
            return object.toString();
        } else {
            return OBJECT_MAPPER.writeValueAsString(object);
        }
    }

    /**
     * 将对象转换为json字符串，转换失败时可选择是否抛出异常
     *
     * @param object
     * @param throwEx
     * @return
     */
    public static String toJsonString(Object object, boolean throwEx) {
        if (object instanceof String) {
            return (String) object;
        } else if (object instanceof Number) {
            return object.toString();
        } else {
            try {
                return OBJECT_MAPPER.writeValueAsString(object);
            } catch (JsonProcessingException ex) {
                if (throwEx) {
                    throw new RuntimeException(ex);
                }
                return null;
            }
        }
    }

    /**
     * 将json字符串转换为对象，转换失败时可选择是否抛出异常
     *
     * @param content
     * @param clazz
     * @param throwEx
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String content, Class<T> clazz, boolean throwEx) {
        try {
            if (clazz.isAssignableFrom(String.class)) {
                return (T) clazz;
            } else if (clazz.isAssignableFrom(Number.class)) {
                return (T) clazz;
            } else {
                return OBJECT_MAPPER.readValue(content, clazz);
            }
        } catch (JsonProcessingException ex) {
            if (throwEx) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }

    /**
     * 将json字符串转换为对象（带泛型），转换失败时可选择是否抛出异常
     *
     * @param content
     * @param typeReference
     * @param throwEx
     * @param <T>
     * @return
     */
    public static <T> T toBean(String content, TypeReference<T> typeReference, boolean throwEx) {
        try {
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(content, typeReference);
        } catch (JsonProcessingException ex) {
            if (throwEx) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }

}
