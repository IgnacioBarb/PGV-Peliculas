package dam.pgl.peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguracionActivity extends AppCompatActivity {

    private EditText api;
    private EditText EPpelis;
    private EditText EPcreditos;
    private Button guardar;
    private SharedPreferences preferencias;

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


        // Preferencias.
        preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);

        guardar.setOnClickListener(e->{
            // Guardar preferencias y salir.
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("api_key", api.getText().toString());
            editor.commit();

            Toast t = Toast.makeText(getApplicationContext(), "Configuración guardada correctamente.", Toast.LENGTH_SHORT);
            t.show();

            finish();
        });
    }
}