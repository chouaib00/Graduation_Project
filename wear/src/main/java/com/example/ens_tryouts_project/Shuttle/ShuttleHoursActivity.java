package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ens_tryouts_project.databinding.ActivityShuttleHoursBinding;

import java.util.ArrayList;
import java.util.List;

public class ShuttleHoursActivity extends AppCompatActivity {

    private ActivityShuttleHoursBinding binding;
    ShuttleAdapter myShuttleDaysAdapter;
    int flag = 0; //initially it is 0, meaning to_campus is set
    ShuttleClass theDestinationObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShuttleHoursBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        theDestinationObject = (ShuttleClass) intent.getSerializableExtra("theDestinationObject");
        final String theOnClickedDay = intent.getStringExtra("theClickedDay");


        String headerTextView = theDestinationObject.getRoute_name_eng() + " - Campus";
        binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
        converterFactoryFromToTo(theOnClickedDay, flag);

        binding.switchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    flag = 1;
                    String headerTextView = "Campus - " + theDestinationObject.getRoute_name_eng();
                    binding.destinationCampusTextView.setText(headerTextView);
                    converterFactoryFromToTo(theOnClickedDay, flag);
                }
                else if(flag == 1){
                    flag = 0;
                    String headerTextView = theDestinationObject.getRoute_name_eng() + " - Campus";
                    binding.destinationCampusTextView.setText(headerTextView);
                    converterFactoryFromToTo(theOnClickedDay, flag);
                }
            }
        });
    }

    public void converterFactoryFromToTo(String theOnClickedDay, int flag){
        if(flag == 0){ //To campus from destination (also default)
            if(theOnClickedDay != null && !theOnClickedDay.equals("")){ //it is a special occasion
                if(theOnClickedDay.equals("Monday") && theDestinationObject.getMonday().getTo_campus() != null){
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getMonday().getTo_campus());
                }
                else if(theOnClickedDay.equals("Friday") && theDestinationObject.getFriday().getTo_campus() != null){
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getFriday().getTo_campus());
                }
                else if(theOnClickedDay.equals("Weekdays") && theDestinationObject.getWeekdays().getTo_campus() != null){ //it is weekdays option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getWeekdays().getTo_campus());
                }
                else if(theOnClickedDay.equals("Saturday") && theDestinationObject.getSaturday().getTo_campus() != null){ //it is saturday option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSaturday().getTo_campus());
                }
                else if(theOnClickedDay.equals("Sunday") && theDestinationObject.getSunday().getTo_campus() != null){ //it is sunday option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSunday().getTo_campus());
                }
                else{
                    List<String> tmpList = new ArrayList<>();
                    tmpList.add("No shuttle buses found.");
                    myShuttleDaysAdapter = new ShuttleAdapter(tmpList);
                }
            }
        }
        else if(flag == 1){ //From campus to destination
            if(theOnClickedDay != null && !theOnClickedDay.equals("")){ //it is a special occasion
                if(theOnClickedDay.equals("Monday") && theDestinationObject.getMonday().getFrom_campus() != null){
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getMonday().getFrom_campus());
                }
                else if(theOnClickedDay.equals("Friday") && theDestinationObject.getFriday().getFrom_campus() != null){
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getFriday().getFrom_campus());
                }
                else if(theOnClickedDay.equals("Weekdays") && theDestinationObject.getWeekdays().getFrom_campus() != null){ //it is weekdays option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getWeekdays().getFrom_campus());
                }
                else if(theOnClickedDay.equals("Saturday") && theDestinationObject.getSaturday().getFrom_campus() != null){ //it is saturday option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSaturday().getFrom_campus());
                }
                else if(theOnClickedDay.equals("Sunday") && theDestinationObject.getSunday().getFrom_campus() != null){ //it is sunday option
                    myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSunday().getFrom_campus());
                }
                else{
                    List<String> tmpList = new ArrayList<>();
                    tmpList.add("No shuttle buses found.");
                    myShuttleDaysAdapter = new ShuttleAdapter(tmpList);
                }
            }
        }
        binding.shuttleDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.shuttleDetailsRecyclerView.setAdapter(myShuttleDaysAdapter);
    }
}
