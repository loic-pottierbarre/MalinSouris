package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lpott on 07/12/2017.
 */

public class ControleSouris extends Activity {
    TextView positionX = null;
    TextView positionY = null;
    Float x = null;
    Float y = null;
    RelativeLayout parentControleSouris = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controle_souris);

        positionX = (TextView)findViewById(R.id.positionX);
        positionY = (TextView)findViewById(R.id.positionY);
        positionX.setText("X = ");
        positionY.setText("Y = ");
        parentControleSouris = (RelativeLayout)findViewById(R.id.parentControleSouris);

        parentControleSouris.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                positionX.setText("X = " + x.toString());

                y = motionEvent.getY();
                positionY.setText("Y = " + y.toString());

                return true;
            }
        });
    }
}
