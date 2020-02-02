package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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


    private AutoCompleteTextView illnessAutoCompleteTextView;
//    private static final String ILLNESS_LIST_URL = "https://api.myjson.com/bins/11qn2q";
private static final String ILLNESS_LIST_URL = "https://api.myjson.com/bins/ae15y";
    ArrayList<String> illnessList = new ArrayList<>();
    ArrayList<String> departmentList = new ArrayList<>();
    String sicknessfetch, department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_list);
        illnessAutoCompleteTextView = findViewById(R.id.illness_auto_complete_textview);

        IllnessRequest illness_request = new IllnessRequest();
        illness_request.execute();

        ArrayAdapter<String> adapterIllness = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, illnessList);
        illnessAutoCompleteTextView.setAdapter(adapterIllness);
        illnessAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sicknessfetch = illnessAutoCompleteTextView.getText().toString();
                if(sicknessfetch.equals("Allergy") || sicknessfetch.equals("Asthma")){
                    department = "Allergist";
                } else if (sicknessfetch.equals("Heartburn") || sicknessfetch.equals("Artery Blockage")||sicknessfetch.equals("Heart Attack")||sicknessfetch.equals("high blood pressure")){
                    department = "Cardiologist";
                }
                else if(sicknessfetch.equals("Acne")||sicknessfetch.equals("Skin problem")){
                    department="Dermatologist";
                }
                else if (sicknessfetch.equals("Tuberculosis") || sicknessfetch.equals("Ear-Pain")||sicknessfetch.equals("Swine Flu")){
                    department = "ENT";
                }
                else if (sicknessfetch.equals("Appendicitis") || sicknessfetch.equals("Jaundice")||sicknessfetch.equals("Ulcers")){
                    department = "Gastroenterologist";
                }
                else if (sicknessfetch.equals("Hepatitis-B")){
                    department = "Hepatologist";
                }
                else if (sicknessfetch.equals("Kidney Stone")){
                    department = "Nephrologist";
                }
                else if (sicknessfetch.equals("Diabetes") || sicknessfetch.equals("Migrane")||sicknessfetch.equals("Headache")||sicknessfetch.equals("")){
                    department = "Neurologist";
                }
                else if (sicknessfetch.equals("Teeth Pain") || sicknessfetch.equals("Wisdom Tooth")){
                    department = "Orthodontist";
                }
                else if (sicknessfetch.equals("Chikungunya") || sicknessfetch.equals("Fracture") || sicknessfetch.equals("Ligament")){
                    department = "Orthopedist";
                }
                else if (sicknessfetch.equals("Cancer")){
                    department = "Oncologist";
                }
                else if (sicknessfetch.equals("Cholera") || sicknessfetch.equals("Fever") || sicknessfetch.equals("Dengue") || sicknessfetch.equals("Cold") || sicknessfetch.equals("Cough") || sicknessfetch.equals("Malaria") || sicknessfetch.equals("Obesity") || sicknessfetch.equals("Rabies") || sicknessfetch.equals("Body Weakness") ){
                    department = "Physician";
                }

                Intent goToDoctorListPage = new Intent(IllnessListActivity.this, ListOfDoctorsActivity.class);
                goToDoctorListPage.putExtra("department",department);
                startActivity(goToDoctorListPage);
            }
        });
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
            initRecyclerView();
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
                    departmentList.add("Allergist");
                };
                CardiologistObject = parentObject.getJSONArray("Cardiologist");
                for (int i=0; i<CardiologistObject.length(); i++){
                    testObject = CardiologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Cardiologist");
                };
                DermatologistObject = parentObject.getJSONArray("Dermatologist");
                for (int i=0; i<DermatologistObject.length(); i++){
                    testObject = DermatologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Dermatologist");
                };
                ENTObject = parentObject.getJSONArray("ENT");
                for (int i=0; i<ENTObject.length(); i++){
                    testObject = ENTObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("ENT");
                };
                GastroenterologistObject = parentObject.getJSONArray("Gastroenterologist");
                for (int i=0; i<GastroenterologistObject.length(); i++){
                    testObject = GastroenterologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Gastroenterologist");
                };
                HepatologistObject = parentObject.getJSONArray("Hepatologist ");
                for (int i=0; i<HepatologistObject.length(); i++){
                    testObject = HepatologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Hepatologist");
                };
                NephrologistObject = parentObject.getJSONArray("Nephrologist");
                for (int i=0; i<NephrologistObject.length(); i++){
                    testObject = NephrologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Nephrologist");
                };
                NeurologistObject = parentObject.getJSONArray("Neurologist");
                for (int i=0; i<NeurologistObject.length(); i++){
                    testObject = NeurologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Neurologist");
                };
                OrthodontistObject = parentObject.getJSONArray("Orthodontist");
                for (int i=0; i<OrthodontistObject.length(); i++){
                    testObject = OrthodontistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Orthodontist");
                };
                OrthopedistObject = parentObject.getJSONArray("Orthopedist");
                for (int i=0; i<OrthopedistObject.length(); i++){
                    testObject = OrthopedistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Orthopedist");
                };
                OncologistObject = parentObject.getJSONArray("Oncologist");
                for (int i=0; i<OncologistObject.length(); i++){
                    testObject = OncologistObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Oncologist");
                };
                PhysicianObject = parentObject.getJSONArray("Physician");
                for (int i=0; i<PhysicianObject.length(); i++){
                    testObject = PhysicianObject.getJSONObject(i);
                    illnessList.add(testObject.getString("sick"));
                    departmentList.add("Physician");
                };

                Log.e("illness list",illnessList.toString());

            }catch (JSONException e){
                e.printStackTrace();
            }

            return illnessList.toString();
        }
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.illness_recycler_view);
        IllnessListAdapter adapter = new IllnessListAdapter(this, illnessList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}