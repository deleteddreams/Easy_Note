package app.hellotask.easynote;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setSelectedItemId(R.id.invisible);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.app_bar_calender:
                        changeToCalender();
                        return true;
                    case R.id.app_bar_notes:
                        changeToNotes();
                        return true;
                    case R.id.app_bar_fav:
                        changeToFavorite();
                        return true;
                    case R.id.app_bar_settings:
                        changeToSettings();
                        return true;
                }
                return false;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.getMenu().getItem(2).setChecked(true);
                changeToHome();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.frame_container, homeFragment);
        fragmentTransaction.commit();
    }


    public void changeToAddNote() {
        AddEditNoteFragment addEditNoteFragment = new AddEditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mode", ADD_NOTE_REQUEST);
        addEditNoteFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container, addEditNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeToEditNote(int id, String title, String desc, boolean favorite, long date) {
        AddEditNoteFragment addEditNoteFragment = new AddEditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mode", EDIT_NOTE_REQUEST);
        bundle.putInt(AddEditNoteFragment.EXTRA_ID, id);
        bundle.putString(AddEditNoteFragment.EXTRA_TITLE, title);
        bundle.putString(AddEditNoteFragment.EXTRA_DESCRIPTION, desc);
        bundle.putBoolean(AddEditNoteFragment.EXTRA_FAVORITE, favorite);
        bundle.putLong(AddEditNoteFragment.EXTRA_DATE, date);
        addEditNoteFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container, addEditNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeToHome() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, homeFragment)
                .commit();
    }

    public void changeToCalender() {
        CalenderFragment calenderFragment = new CalenderFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, calenderFragment)
                .commit();
    }

    public void changeToNotes() {
        NotesFragment notesFragment = new NotesFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, notesFragment)
                .commit();
    }

    public void changeToFavorite() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, favoriteFragment)
                .commit();
    }

    public void changeToSettings() {
        SettingsFragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, settingsFragment)
                .commit();
    }
}