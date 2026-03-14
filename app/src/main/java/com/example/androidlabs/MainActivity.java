package com.example.androidlabs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Character> characters = new ArrayList<>();
    CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new CharacterAdapter(this, characters);
        listView.setAdapter(adapter);

        new GetCharactersTask().execute();

        listView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            Character selected = characters.get(position);

            Bundle bundle = new Bundle();
            bundle.putString("name", selected.getName());
            bundle.putString("height", selected.getHeight());
            bundle.putString("mass", selected.getMass());

            FrameLayout detailsFrame = findViewById(R.id.detailsFrame);

            if (detailsFrame == null) {
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                DetailsFragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detailsFrame, fragment)
                        .commit();
            }
        });
    }

    private class GetCharactersTask extends AsyncTask<Void, Void, ArrayList<Character>> {

        @Override
        protected ArrayList<Character> doInBackground(Void... voids) {
            ArrayList<Character> result = new ArrayList<>();

            try {
                URL url = new URL("https://swapi.dev/api/people/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                JSONObject root = new JSONObject(response.toString());
                JSONArray people = root.getJSONArray("results");

                for (int i = 0; i < people.length(); i++) {
                    JSONObject oneCharacter = people.getJSONObject(i);

                    String name = oneCharacter.getString("name");
                    String height = oneCharacter.getString("height");
                    String mass = oneCharacter.getString("mass");

                    result.add(new Character(name, height, mass));
                }

                reader.close();
                inputStream.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Character> result) {
            characters.clear();
            characters.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }
}
