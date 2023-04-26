package de.p3lina.application;

import java.util.*;

public class JSON {
    private Object value;

    public JSON(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public static JSON parse(String jsonString) {
        return new JSON(parseObject(jsonString));
    }

    private static Object parseObject(String jsonString) {
        jsonString = jsonString.trim();
        if (jsonString.startsWith("{")) {
            return parseJSONObject(jsonString);
        } else if (jsonString.startsWith("[")) {
            return parseJSONArray(jsonString);
        } else {
            throw new IllegalArgumentException("Invalid JSON string");
        }
    }

    private static Map<String, Object> parseJSONObject(String jsonString) {
        Map<String, Object> jsonObject = new LinkedHashMap<>();
        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
        if (jsonString.isEmpty()) {
            return jsonObject;
        }

        String[] keyValuePairs = jsonString.split(",");
        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split(":", 2);
            String key = keyValue[0].trim().substring(1, keyValue[0].length() - 1);
            Object value = parseObject(keyValue[1]);
            jsonObject.put(key, value);
        }
        return jsonObject;
    }

    private static List<Object> parseJSONArray(String jsonString) {
        List<Object> jsonArray = new ArrayList<>();
        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
        if (jsonString.isEmpty()) {
            return jsonArray;
        }

        String[] values = jsonString.split(",");
        for (String value : values) {
            jsonArray.add(parseObject(value));
        }
        return jsonArray;
    }

    public String toJSONString() {
        return generateJSONString(value);
    }

    private String generateJSONString(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + ((String) value).replace("\"", "\\\"") + "\"";
        } else if (value instanceof Map) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            Map<String, Object> jsonObject = (Map<String, Object>) value;
            for (String key : jsonObject.keySet()) {
                sb.append("\"").append(key).append("\":");
                sb.append(generateJSONString(jsonObject.get(key))).append(",");
            }
            if (!jsonObject.isEmpty()) {
                sb.setLength(sb.length() - 1);
            }
            sb.append("}");
            return sb.toString();
        } else if (value instanceof List) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            List<Object> jsonArray = (List<Object>) value;
            for (Object obj : jsonArray) {
                sb.append(generateJSONString(obj)).append(",");
            }
            if (!jsonArray.isEmpty()) {
                sb.setLength(sb.length() - 1);
            }
            sb.append("]");
            return sb.toString();
        } else {
            return value.toString();
        }
    }

    public JSON get(String key) {
        if (value instanceof Map) {
            return new JSON(((Map<String, Object>) value).get(key));
        } else {
            throw new IllegalStateException("Cannot get value from a non-object JSON");
        }
    }

    public JSON get(int index) {
        if (value instanceof List) {
            return new JSON(((List<Object>) value).get(index));
        } else {
            throw new IllegalStateException("Cannot get value from a non-array JSON");
        }
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isObject() {
        return value instanceof Map;
    }

    public boolean isArray() {
        return value instanceof List;
    }

    public int size() {
        if (value instanceof Map) {
            return ((Map<String, Object>) value).size();
        } else if (value instanceof List) {
            return ((List<Object>) value).size();
        } else {
            throw new IllegalStateException("Cannot get size of a non-object/non-array JSON");
        }
    }

    public Set<String> keys() {
        if (value instanceof Map) {
            return ((Map<String, Object>) value).keySet();
        } else {
            throw new IllegalStateException("Cannot get keys of a non-object JSON");
        }
    }

    public boolean containsKey(String key) {
        if (value instanceof Map) {
            return ((Map<String, Object>) value).containsKey(key);
        } else {
            throw new IllegalStateException("Cannot check key existence in a non-object JSON");
        }
    }

    public boolean containsValue(Object val) {
        if (value instanceof Map) {
            return ((Map<String, Object>) value).containsValue(val);
        } else if (value instanceof List) {
            return ((List<Object>) value).contains(val);
        } else {
            throw new IllegalStateException("Cannot check value existence in a non-object/non-array JSON");
        }
    }

    public JSON put(String key, Object val) {
        if (value instanceof Map) {
            ((Map<String, Object>) value).put(key, val);
        } else {
            throw new IllegalStateException("Cannot put value in a non-object JSON");
        }
        return this;
    }

    public JSON put(int index, Object val) {
        if (value instanceof List) {
            ((List<Object>) value).add(index, val);
        } else {
            throw new IllegalStateException("Cannot put value in a non-array JSON");
        }
        return this;
    }

    @Override
    public String toString() {
        return toJSONString();
    }
}