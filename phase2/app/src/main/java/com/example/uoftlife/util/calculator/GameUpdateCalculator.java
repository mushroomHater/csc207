package com.example.uoftlife.util.calculator;

import android.app.Activity;
import android.content.Intent;

import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.transpage.AssignmentPageActivity;
import com.example.uoftlife.util.GameMessenger;
import com.example.uoftlife.util.TransitionPageBuilder;

public class GameUpdateCalculator {
    private Activity context;
    private TransitionPageBuilder builder;
    private final int FRAME_MAX = 5;
    private final double ASSIGNMENT_FREQUENCY = 0.01;
    private final int MAX_DUETIME = 200;
    private final int MAX_TAKES = 100;
    private final int MAX_WORTH = 20;
    private int frequencySpliter = FRAME_MAX;
    private GameMessenger messenger = GameMessenger.getMessenger();

    public GameUpdateCalculator(Activity context) {
        this.context = context;
        builder = new TransitionPageBuilder(context).setShowingTime(9);
    }

    public void update() {
        checkEnding();
        regularReduction();
        statusCheck();
        sendAssignment();
        checkDue();
    }

    private void checkDue() {
        if (DataFacade.getValue("time") < DataFacade.getValue("due")) {
            messenger.toastMessage("Unfortunately, assignment due date has past and you lost" + DataFacade.getValue("worth") + "marks...");
            DataFacade.addToValue("mark", DataFacade.getValue("worth"));
            DataFacade.setValue("due", 0);
        }
    }


    private void sendAssignment() {
        if (Math.random() < ASSIGNMENT_FREQUENCY) {
            if (DataFacade.getValue("due") == 0) {
                int due = DataFacade.getValue("time") - ((int) (MAX_DUETIME * Math.random()));
                int worth = ((int) (MAX_WORTH * Math.random()));
                int takesTime = ((int) (MAX_TAKES * Math.random()));
                DataFacade.setValue("due", due);
                DataFacade.setValue("worth", worth);
                DataFacade.setValue("takes", takesTime);
                messenger.initialize();
                messenger.setTitle(String.format(context.getString(R.string.assignment), "") + context.getString(R.string.assign))
                        .setText(String.format(context.getString(R.string.worth), worth) +
                                String.format(context.getString(R.string.due), due))
                        .setIntent(new Intent(context, AssignmentPageActivity.class)).sendNewMessage();
            }
        }
    }

    private void regularReduction() {
        if (DataFacade.getValue("repletion") <= 20 || DataFacade.getValue("vitality") <= 20) {
            DataFacade.addToValue("health", -1);
        }
        DataFacade.addToValue("time", -1);
        DataFacade.addToValue("vitality", -2);
        DataFacade.addToValue("repletion", -2);
        frequencySpliter--;
        if (frequencySpliter == 0) {
            frequencySpliter = FRAME_MAX;
            DataFacade.addToValue("practice", -1);
        }
    }


    private void statusCheck() {
        if (DataFacade.getValue("mood") <= 20) {
            setStatus(context.getString(R.string.status_bad_mood));
        } else if (DataFacade.getValue("mood") >= 90) {
            setStatus(context.getString(R.string.status_good_mood));
        }
        if (DataFacade.getValue("vitality") <= 20) {
            setStatus(context.getString(R.string.status_exhausted));
        }
        if (DataFacade.getValue("repletion") <= 20) {
            setStatus(context.getString(R.string.status_starving));
        }
        if (DataFacade.getValue("health") <= 40) {
            setStatus(context.getString(R.string.status_sick));
        }
        if(DataFacade.getValue("due")!=0){
            setStatus(context.getString(R.string.status_has_due));
        }
    }

    private void setStatus(String statusText) {
        DataFacade.setTempData("status", statusText);
    }

    private void checkEnding() {
        if (DataFacade.getValue("time") <= 0) {
            double mark = DataFacade.getValue("mark");
            int diff = (100 - DataFacade.getValue("practice")) + (100 - DataFacade.getValue("understanding"));
            if (DataFacade.getValue("vitality") < 50) {
                diff += 20;
            }
            if (diff < 0) {
                diff = 0;
            }else if(diff>100){
                diff = 100;
            }
            mark -= diff * 0.4;
            builder.setDescription(DataFacade
                    .getTempData("name") +
                    " have to take final exam! According to the body condition and mastering of knowledge, get final exam "
                    + (100 - diff) + ". And Overall mark is " + mark + ".");

            if (mark >= 60) {
                builder.setTitle("The semester is over...Congratulations!");
            } else {
                builder.setTitle("The semester is over...You failed.");
            }
            builder.start();
            context.finish();
        }
        if (DataFacade.getValue("health") <= 0) {
            builder.setTitle("You died").setDescription("Because starving or staying up too long... please try again.").start();
            context.finish();
        }
        if (DataFacade.getValue("mood") <= 0) {
            builder.setTitle("You suicide.").setDescription("Your feel so frustrated... please try again.").start();
            context.finish();
        }
    }
}
