package chinna.sandyz.com.materialdesignfab;

import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
               dialog();

            }
        });
    }

    private void dialog() {
        final View myView = View.inflate(this, R.layout.alert, null);

        final Dialog aleDialog = new Dialog(this);
        aleDialog.setContentView(myView);
        aleDialog.setCancelable(false);
        aleDialog.setCanceledOnTouchOutside(false);
      aleDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        aleDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    aleDialog.show();
                } else
                    effect(myView, true, null);
            }

        });
        myView.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    aleDialog.dismiss();
                } else
                    effect(myView, false, aleDialog);
            }
        });
        aleDialog.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void effect(View rootView, boolean reveal, final Dialog dialog) {

        final View view = rootView.findViewById(R.id.alertTitle);
        int w = view.getWidth();
        int h = view.getHeight();
        float maxRadius = (float) Math.sqrt(w * w / 4 + h * h / 4);

        if (reveal) {
            android.animation.Animator animator = ViewAnimationUtils.createCircularReveal(view, w / 2, h / 2, 0, maxRadius);
            view.setVisibility(View.VISIBLE);
            animator.start();

        } else {
            android.animation.Animator animat = ViewAnimationUtils.createCircularReveal(view, w / 2, h / 2, maxRadius, 0);
            animat.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);
                }
            });
            animat.start();
        }
    }

}
