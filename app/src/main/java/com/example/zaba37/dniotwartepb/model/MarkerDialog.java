package com.example.zaba37.dniotwartepb.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zaba37.dniotwartepb.activity.CompassActivity;
import com.example.zaba37.dniotwartepb.activity.ShowEventsActivity;

/**
 * Created by zaba3 on 02.03.2016.
 */
public class MarkerDialog extends RelativeLayout implements View.OnClickListener{

    private TextView title;
    private TextView subtitle;

    private static int getDIP( Context context, int pixels ) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, context.getResources().getDisplayMetrics());
    }

    public MarkerDialog( Context context ) {

        super( context );

        // children
        LinearLayout bubble = new LinearLayout( context );
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor( 0xFF313231 );
        drawable.setCornerRadius(getDIP(context, 4));
        bubble.setBackgroundDrawable(drawable);
        bubble.setId(1);
        int padding = getDIP( context, 17 );
        bubble.setPadding( padding, padding, padding, padding );
        LayoutParams bubbleLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        addView( bubble, bubbleLayout );

        Nub nub = new Nub( context );
        LayoutParams nubLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        nubLayout.addRule( RelativeLayout.BELOW, bubble.getId() );
        nubLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(nub, nubLayout);

        LinearLayout labels = new LinearLayout( context );
        labels.setOrientation(LinearLayout.VERTICAL);
        LayoutParams labelsLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        bubble.addView( labels, labelsLayout );

        int maxWidth = getDIP( context, 230 );
        title = new TextView( context );
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        title.setMaxWidth(maxWidth);
        LinearLayout.LayoutParams titleLayout = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        labels.addView( title, titleLayout );

        subtitle = new TextView( context );
        subtitle.setTextColor(0xFF88afca);
        subtitle.setTypeface(Typeface.SANS_SERIF);
        subtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        subtitle.setMaxWidth(maxWidth);
        LinearLayout.LayoutParams subtitleLayout = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        labels.addView(subtitle, subtitleLayout);

        RelativeLayout iconColumn = new RelativeLayout( context );
        LayoutParams iconColumnLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT );
        bubble.addView(iconColumn, iconColumnLayout);

        ImageView icon = new ImageView( context );
        icon.setId( 2 );
        //icon.setImageResource( R.drawable.callout_info_icon );
        icon.setScaleType(ImageView.ScaleType.MATRIX);
        LayoutParams iconLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        iconLayout.addRule( RelativeLayout.CENTER_VERTICAL );
        iconLayout.leftMargin = getDIP( context, 10 );
        iconColumn.addView(icon, iconLayout);

        Button button = new Button(context);
        button.setId(3);
        button.setText("Prowadź");
        LinearLayout.LayoutParams buttonLayout = new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT );
        labels.addView( button, buttonLayout );
        button.setOnClickListener(this);

        Button button2 = new Button(context);
        button2.setId( 4 );
        button2.setText("Wydarzenia");
        LinearLayout.LayoutParams buttonLayout2 = new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT );
        labels.addView( button2, buttonLayout2 );
        button2.setOnClickListener(this);
    }

    public void setTitle( CharSequence text ) {
        title.setText( text );
    }

    public void setSubtitle( CharSequence text ) {
        subtitle.setText( text );
    }

    public void transitionIn() {

        ScaleAnimation scaleAnimation = new ScaleAnimation( 0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f );
        scaleAnimation.setInterpolator( new OvershootInterpolator( 1.2f ) );
        scaleAnimation.setDuration( 250 );

        AlphaAnimation alphaAnimation = new AlphaAnimation( 0, 1f );
        alphaAnimation.setDuration( 200 );

        AnimationSet animationSet = new AnimationSet( false );

        animationSet.addAnimation( scaleAnimation );
        animationSet.addAnimation( alphaAnimation );

        startAnimation( animationSet );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 3:
                getContext().startActivity(new Intent(getContext(), CompassActivity.class));
                break;
            case 4:
                getContext().startActivity(new Intent(getContext(), ShowEventsActivity.class));
                break;
        }
    }

    private static class Nub extends View {

        private Paint paint = new Paint();
        private Path path = new Path();

        public Nub( Context context ) {

            super( context );

            paint.setStyle( Paint.Style.FILL );
            paint.setColor( 0xFF313231 );
            paint.setAntiAlias( true );

            path.lineTo( getDIP( context, 20 ), 0 );
            path.lineTo( getDIP( context, 10 ), getDIP( context, 15 ) );
            path.close();

        }

        @Override
        protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
            setMeasuredDimension( getDIP( getContext(), 20 ), getDIP( getContext(), 15 ) );
        }

        @Override
        public void onDraw( Canvas canvas ) {
            canvas.drawPath( path, paint );
            super.onDraw( canvas );
        }
    }

}