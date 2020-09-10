package app.hellotask.easynote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvWelcome = root.findViewById(R.id.tvWelcomeText);
        String welcomeText = "Welcome John Doe!";
        SpannableStringBuilder builder = new SpannableStringBuilder(welcomeText);
        builder.setSpan(new TextAppearanceSpan(getActivity(), R.style.WelcomeText), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextAppearanceSpan(getActivity(), R.style.NameText), 8, welcomeText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvWelcome.setText(builder);

        CalendarView calendarView = root.findViewById(R.id.calendarView);
        calendarView.setCalendarListener(new CalendarView.CalendarListener() {
            @Override
            public void onDayClick(Date date) {

            }

            @Override
            public void onDayLongClick(Date date) {

            }
        });

        return root;
    }
}