package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class IllnessListActivity extends AppCompatActivity {

    private TextView test, test1;
    private static final String ILLNESS_LIST_URL = "https://api.myjson.com/bins/11qn2q";
    private static final String DOCTOR_LIST_URL = "https://api.myjson.com/bins/1ahxdu";
    ArrayList<String> illnessList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> yearList = new ArrayList<>();
    ArrayList<String> degreeList = new ArrayList<>();
    ArrayList<String> departmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_list);

//        test = findViewById(R.id.testview);
//        test1 = findViewById(R.id.testview1);

        IllnessRequest illness_request = new IllnessRequest();
        illness_request.execute();

//        DoctorRequest doctor_request = new DoctorRequest();
//        doctor_request.execute();
    }


    public class IllnessRequest extends AsyncTask<URL, String, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL url;

            try {
                url = new URL(ILLNESS_LIST_URL);
            } catch (MalformedURLException exception) {
                Log.e("errorTag", "Error with creating URL", exception);
                return null;
            }

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e("errorTag", "Error in request");
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            test.setText(s);
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection;
            InputStream inputStream;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readInputStream(inputStream);

            return jsonResponse;
        }

        private String readInputStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }

            JSONObject parentObject, testObject;
            JSONArray AllergistObject, CardiologistObject, DermatologistObject, ENTObject, GastroenterologistObject, HepatologistObject, NephrologistObject, NeurologistObject, OrthodontistObject, OrthopedistObject, OncologistObject, PhysicianObject;

            try {
                parentObject = new JSONObject(output.toString());
                AllergistObject = parentObject.getJSONArray("Allergist");
                for (int i=0; i<AllergistObject.length(); i++){
                    testObject = AllergistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                CardiologistObject = parentObject.getJSONArray("Cardiologist");
                for (int i=0; i<CardiologistObject.length(); i++){
                    testObject = CardiologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                DermatologistObject = parentObject.getJSONArray("Dermatologist");
                for (int i=0; i<DermatologistObject.length(); i++){
                    testObject = DermatologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                ENTObject = parentObject.getJSONArray("ENT");
                for (int i=0; i<ENTObject.length(); i++){
                    testObject = ENTObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                GastroenterologistObject = parentObject.getJSONArray("Gastroenterologist");
                for (int i=0; i<GastroenterologistObject.length(); i++){
                    testObject = GastroenterologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                HepatologistObject = parentObject.getJSONArray("Hepatologist ");
                for (int i=0; i<HepatologistObject.length(); i++){
                    testObject = HepatologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                NephrologistObject = parentObject.getJSONArray("Nephrologist");
                for (int i=0; i<NephrologistObject.length(); i++){
                    testObject = NephrologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                NeurologistObject = parentObject.getJSONArray("Neurologist");
                for (int i=0; i<NeurologistObject.length(); i++){
                    testObject = NeurologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                OrthodontistObject = parentObject.getJSONArray("Orthodontist");
                for (int i=0; i<OrthodontistObject.length(); i++){
                    testObject = OrthodontistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                OrthopedistObject = parentObject.getJSONArray("Orthopedist");
                for (int i=0; i<OrthopedistObject.length(); i++){
                    testObject = OrthopedistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                OncologistObject = parentObject.getJSONArray("Oncologist");
                for (int i=0; i<OncologistObject.length(); i++){
                    testObject = OncologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };
                PhysicianObject = parentObject.getJSONArray("Physician");
                for (int i=0; i<PhysicianObject.length(); i++){
                    testObject = PhysicianObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                };

                Log.e("illness list",illnessList.toString());

            }catch (JSONException e){
                e.printStackTrace();
            }

            return illnessList.toString();
        }
    }


}

