package es.ifp.splash;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String ruta = "https://splashplacas.000webhostapp.com/androidstudio/SplashPlacas/login.php";
    private Map<String, String> parametros;
    public LoginRequest (String usuario, String clave, Response.Listener<String> listener)
    {
        super(Request.Method.POST, ruta, listener, null);
        parametros= new HashMap<>();
        parametros.put( "usuario", usuario+"");
        parametros.put( "clave", clave+"");

    }

    @Override
    protected Map<String, String> getParams()  {
        return parametros;
    }
}
