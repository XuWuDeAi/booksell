package unit;

import javax.servlet.http.HttpSession;

public class MainUnit {

    public static void print(String it) {
        System.out.println(it);
    }

    public static String getAttribute(HttpSession session, String key)
    {
        if (session.getAttribute(key)==null)
            return "";
        return  session.getAttribute(key).toString();
    }
}
