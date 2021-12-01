package dam.pgl.peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Actores extends AppCompatActivity {
    private ArrayList<Actor> listaActores = new ArrayList<Actor>();
    private ListView listView;
    private String id, titulo;

    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listActores);

        Bundle p = getIntent().getExtras();
        this.id = p.getString("id");
        this.titulo = p.getString("titulo");

        setTitle("Actores: "+titulo);
        new ObtenerActoresAsync().execute("http://api.themoviedb.org/3/movie/" + id + "/casts?api_key=88065b90732f187d12b27e745e91bdd9");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    class ObtenerActoresAsync extends AsyncTask<String, Integer, String> {
        ProgressDialog progreso;

        protected void onPreExecute () {
            super.onPreExecute();

            // Mostrar progress bar.
            progreso = new ProgressDialog(Actores.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setMessage("Obteniendo ...");
            progreso.setCancelable(false);
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();
        }

        protected String doInBackground(String... params) {
            StringBuilder result = new StringBuilder();

            try {
                URL urlObj = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = reader.readLine()) != null) result.append(line);

                Log.d("test", "respuesta: " + result.toString());

            } catch (Exception e) {
                Log.d("test", "error2: " + e.toString());
            }

            return result.toString();
        }

        protected void onProgressUpdate(Integer...a){
            super.onProgressUpdate(a);
        }

        protected void onPostExecute(String result) {
            JSONObject resp = null;
            JSONArray actores = null;

            try {
                resp = new JSONObject(result);
                actores = resp.getJSONArray("cast");

                for (int i = 0; i<actores.length();i++) {
                    JSONObject actor = actores.getJSONObject(i);

                    listaActores.add(new Actor(
                            actor.getInt("id"),
                            actor.getString("name"),
                            actor.getString("character"),
                            actor.getString("profile_path") ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            progreso.dismiss();

            AdaptadorActores adaptador = new AdaptadorActores(getApplicationContext(), listaActores);
            listView.setAdapter(adaptador);
        }
    }

    class AdaptadorActores extends BaseAdapter {
        Context context;
        ArrayList<Actor> arrayList;

        public AdaptadorActores(Context context, ArrayList<Actor> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        public int getCount() {
            return arrayList.size();
        }

        public Actor getItem(int position) {
            return arrayList.get(position);
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.lista_actores, parent, false);
            }

            // Nombre
            TextView fecha = (TextView) convertView.findViewById(R.id.tvNombre);
            fecha.setText(arrayList.get(position).getNombre());

            // Personaje
            TextView name = (TextView) convertView.findViewById(R.id.tvPersonajes);
            name.setText(arrayList.get(position).getCharacter());

            // Imagen.
            ImageView imagen = (ImageView) convertView.findViewById(R.id.imgActor);
            Picasso.get().load(MOVIE_BASE_URL + arrayList.get(position).getProfile_path()).into(imagen);

            return convertView;
        }

    }
}