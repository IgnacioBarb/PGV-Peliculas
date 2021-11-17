package dam.pgl.peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ConfiguracionActivity extends AppCompatActivity {

    private EditText api;
    private EditText EPpelis;
    private EditText EPcreditos;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        api = findViewById(R.id.editTextAPI);
        EPpelis = findViewById(R.id.editTextEndpointPelis);
        EPcreditos = findViewById(R.id.editTextEndpointCreditos);
        guardar = findViewById(R.id.btnGuardar);

        Bundle p= getIntent().getExtras();
        api.setText(p.getString("api"));
        EPpelis.setText(p.getString("epp"));
    }
}