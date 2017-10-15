package helper;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Helper {

    public String naytaMuuttujaArvoparit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paluuStr="Request-headerit:\n";
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                paluuStr += paramName + " = " + paramValues[i] + "\n";
            }
        }
        return paluuStr;
    }
}
