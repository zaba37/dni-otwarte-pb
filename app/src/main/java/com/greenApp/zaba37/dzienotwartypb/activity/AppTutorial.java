package com.greenApp.zaba37.dzienotwartypb.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.greenApp.zaba37.dzienotwartypb.R;
import com.greenApp.zaba37.dzienotwartypb.fragment.TutorialFragment;

/**
 * Created by Marcin on 07.03.2016.
 */
public class AppTutorial extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        setFadeAnimation();
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(TutorialFragment.newInstance("Witaj na Dniu Otwartym Politechniki Białostockiej!",
                "Ten przewodnik pokaże Ci jak korzystać z naszej aplikacji",
                R.drawable.pan_murek,
                Color.parseColor("#86A23C")));

        addSlide(TutorialFragment.newInstance("Interaktywna mapa kampusu",
                "Oznaczyliśmy dla Ciebie wszystkie wydarzenia, wystarczy kliknąć w marker",
                R.drawable.tut_bg,
                Color.parseColor("#86A23C")));

        addSlide(TutorialFragment.newInstance("Wydarzenia i nawigacja",
                "Po kliknięciu markera, możesz przejrzeć wszystkie wydarzenia odbywające się w tym miejsciu, lub wybrać opcję Prowadź, która będzie nawigowała Cię pod same drzwi wydziału",
                R.drawable.tut_3,
                Color.parseColor("#86A23C")));
        // OPTIONAL METHODS
        // Override bar/separator color.

        addSlide(TutorialFragment.newInstance("Więcej atrakcji",
                "Kiedy już odwiedzisz wszystkie miejsca, zajrzyj do panelu bocznego, aby dowiedzieć się więcej o Politechnice i nadchodzących wydarzeniach",
                R.drawable.tut_4,
                Color.parseColor("#86A23C")));

        addSlide(TutorialFragment.newInstance("Powodzenia!",
                "Wiesz już wszystko, możemy zaczynać!",
                R.drawable.splash_murek,
                Color.parseColor("#86A23C")));

        setSeparatorColor(Color.WHITE);

        setDoneText("Zaczynamy!");
        setSkipText("Pomiń");
        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        finish();
    }


    @Override
    public void onDonePressed() {
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.

    }
}
