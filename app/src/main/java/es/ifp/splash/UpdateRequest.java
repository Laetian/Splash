package es.ifp.splash;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest {

    private static final String ruta = "https://travesiaelescorialnavacerrada.com/escolta/update.php";
    private Map<String, String> parametros;
    public UpdateRequest (String id, String matricula, String modelo, String color, String ubicacion, String intervencion, String comentarios, String escolta, Response.Listener<String> listener)
    {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("id", id+"");
        parametros.put("matricula", matricula+"");
        parametros.put("modelo", modelo+"");
        parametros.put("color", color+"");
        parametros.put("ubicacion", ubicacion+"");
        parametros.put("intervencion", intervencion+"");
        parametros.put("comentarios", comentarios+"");
        parametros.put("escolta", escolta+"");


    }

    @Override
    protected Map<String, String> getParams()
    {
        return parametros;
    }
}