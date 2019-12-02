package com.example.uoftlife.transpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.gamemap.MapActivity;


@SuppressLint("Registered")
public class AssignmentPageActivity extends TransitionPageActivity {
    private int restTime = DataFacade.getValue("time") - DataFacade.getValue("due");
    private boolean isEnough = restTime >= DataFacade.getValue("takes");
    private int markPercentage = calculateMark();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateValues();
    }

    private void updateValues(){
        int timePass;
        if (isEnough) {
            timePass = DataFacade.getValue("takes");
            updateValue("mark", -DataFacade.getValue("worth") * (1 - (markPercentage / 100)));
        } else {
            timePass = restTime;
            updateValue("mark", -DataFacade.getValue("worth"));
        }
        updateValue("time", -timePass);
        updateValue("vitality", -timePass * 2);
        updateValue("repletion", -timePass * 2);
        DataFacade.setValue("due", 0);
    }

    @Override
    protected Intent nextActivity() {
        return new Intent(this, MapActivity.class);
    }

    @Override
    protected String setTitleText() {
        return isEnough ? "Completed!" : "Failed...";
    }

    private int calculateMark() {
        int diff = (100 - DataFacade.getValue("practice")) + (100 - DataFacade.getValue("understanding"));
        if (diff < 0) {
            diff = 0;
        }else if(diff>100){
            diff = 100;
        }
        return 100 - diff;
    }

    @Override
    protected String setDescriptionText() {
        ((TextView)findViewById(R.id.description_text)).setTextSize(16);
        return isEnough ? "Finished in time. " +
                "Correspond to performance on this period's study, result is " + markPercentage + "% marks." :
                "Although you spend time on it, the due date is still coming earlier than you think... You lost all the marks that this assignment is worth.";
    }

    @Override
    protected int setShowLength() {
        return 10;
    }

}
