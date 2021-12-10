package supermercado.app.economico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import supermercado.app.economico.R;
public class recuperarActivity extends AppCompatActivity {
    EditText txtDireccion;
    Button btnEnviar;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        btnEnviar=(Button) findViewById(R.id.btnEnviar);

        txtDireccion= (EditText)findViewById(R.id.txtDireccion);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=txtDireccion.getText().toString();
                String url="https://supereconomico.online/movil3/views/json-movil/correo.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(recuperarActivity.this,response.trim(),Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(recuperarActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("correo", s);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(recuperarActivity.this);
                requestQueue.add(stringRequest);

            }
        });
    }


}

/* Toast.makeText(recuperarActivity.this, "Hola", Toast.LENGTH_SHORT).show();
*  String mail= txtDireccion.getText().toString().trim();
        String subject= "Supermercado Economico -- Recuperar Contraseña";
        String message= "";
        String contraseña="";
        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
* */