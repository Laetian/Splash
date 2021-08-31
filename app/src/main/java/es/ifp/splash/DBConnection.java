package es.ifp.splash;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class DBConnection extends StringRequest {

    private static final String ruta = "https://splashplacas.000webhostapp.com/androidstudio/SplashPlacas/registro.php";
    private Map<String, String> parametros;
    public DBConnection (String usuario, String clave, String level, Response.Listener<String> listener)
    {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("usuario", usuario+"");
        parametros.put("clave", clave+"");
        parametros.put("level", level+"");


    }

    @Override
    protected Map<String, String> getParams()
    {
        return parametros;
    }
}
