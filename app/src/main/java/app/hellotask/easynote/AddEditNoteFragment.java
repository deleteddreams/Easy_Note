package app.hellotask.easynote;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final String EXTRA_ID = "app.hellotask.easynote.EXTRA_ID";
    public static final String EXTRA_TITLE = "app.hellotask.easynote.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "app.hellotask.easynote.EXTRA_DESCRIPTION";
    public static final String EXTRA_FAVORITE = "app.hellotask.easynote.EXTRA_FAVORITE";
    public static final String EXTRA_DATE = "app.hellotask.easynote.EXTRA_DATE";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private CheckBox cbFavorite;

    private NoteViewModel noteViewModel;

    public AddEditNoteFragment() {
    }

    public static AddEditNoteFragment newInstance(String param1, String param2) {
        AddEditNoteFragment fragment = new AddEditNoteFragment();
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

        View root = inflater.inflate(R.layout.fragment_add_edit_note, container, false);
        editTextTitle = root.findViewById(R.id.edit_text_title);
        editTextDescription = root.findViewById(R.id.edit_text_description);
        cbFavorite = root.findViewById(R.id.cbFavorite);


        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getInt("mode") == MainActivity.EDIT_NOTE_REQUEST) {
                editTextTitle.setText(bundle.getString(EXTRA_TITLE));
                editTextDescription.setText(bundle.getString(EXTRA_DESCRIPTION));
                cbFavorite.setChecked(bundle.getBoolean(EXTRA_FAVORITE));
            }
        } else {
        }

        noteViewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);

        ImageButton buttonSave = root.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        ImageButton buttonCancel = root.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return root;
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        boolean favorite = cbFavorite.isChecked();
        long date = System.currentTimeMillis();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note(title, description, favorite, date);
        if (getArguments().getInt("mode") == MainActivity.ADD_NOTE_REQUEST) {
            noteViewModel.insert(note);
            Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            note.setId(getArguments().getInt(EXTRA_ID));
            noteViewModel.update(note);
            Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }
}