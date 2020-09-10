package app.hellotask.easynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvSplash = findViewById(R.id.tvSplash);
        String splashText = "Easy Note";
        SpannableStringBuilder builder = new SpannableStringBuilder(splashText);
        builder.setSpan(new TextAppearanceSpan(this, R.style.SplashTextRed), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextAppearanceSpan(this, R.style.SplashTextBlack), 1, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextAppearanceSpan(this, R.style.SplashTextRed), 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextAppearanceSpan(this, R.style.SplashTextBlack), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSplash.setText(builder);

        CardView cvGetStarted = findViewById(R.id.cvGetStarted);
        cvGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}