package unit;

import java.util.HashMap;
import java.util.Map;

public class Redis {

    //声明一个map,用来作为缓存模型
    private static Map<String, Object> map = new HashMap<String, Object>();

    public static Object getValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static void setValue(String key, String context) {
        map.put(key, context);
    }

}
