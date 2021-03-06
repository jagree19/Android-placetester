package edu.asu.bsse.jagree19.plactester;

/*
 * AddNewActivity.java
 * Assign 7
 *
 * Copyright 2018 Justin Greene,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: To add a new location to the database as per assignment 7 SER423 2018 Spring B
 *
 * @author Justin Greene   mailto:jagree19@asu.edu
 * @version March 2018
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText descriptionText;
    private EditText categoryText;
    private EditText addressTitleText;
    private EditText addressStreetText;
    private EditText elevationText;
    private EditText latitudeText;
    private EditText longitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        nameText = (EditText)findViewById(R.id.nameET);
        descriptionText = (EditText)findViewById(R.id.descET);
        categoryText = (EditText)findViewById(R.id.catET);
        addressTitleText = (EditText)findViewById(R.id.addTET);
        addressStreetText = (EditText)findViewById(R.id.addSET);
        elevationText = (EditText)findViewById(R.id.elevET);
        latitudeText = (EditText)findViewById(R.id.latET);
        longitudeText = (EditText)findViewById(R.id.longET);
    }
    public void addNewLocation(View v){
        try{
            LocationDB db = new LocationDB(this);
            SQLiteDatabase locDB = db.openDB();
            ContentValues cv = new ContentValues();
            cv.put("name",this.nameText.getText().toString());
            cv.put("description",this.descriptionText.getText().toString());
            cv.put("category",this.categoryText.getText().toString());
            cv.put("address_title",this.addressTitleText.getText().toString());
            cv.put("address_street",this.addressStreetText.getText().toString());
            Double elevation = Double.parseDouble(this.elevationText.getText().toString());
            cv.put("elevation",elevation);
            Double latitude = Double.parseDouble(this.latitudeText.getText().toString());
            cv.put("latitude",latitude);
            Double longitude = Double.parseDouble(this.longitudeText.getText().toString());
            cv.put("longitude",longitude);
            locDB.insert("locations",null,cv);
            locDB.close();
            db.close();
            AddNewActivity.this.finish();
        } catch (Exception e) {
            android.util.Log.w(this.getClass().getSimpleName(),"error adding new location" + e.getMessage());
        }

    }
}
