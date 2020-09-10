package app.hellotask.easynote;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.isFavorite() == newItem.isFavorite();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewDay.setText(getDayFromDate(currentNote.getDate()));
        holder.textViewMonth.setText(getMonthFromDate(currentNote.getDate()));
        switch (position % 3) {
            case 0:
                holder.colorfulBar.setBackgroundColor(Color.parseColor("#F57824"));
                break;
            case 1:
                holder.colorfulBar.setBackgroundColor(Color.parseColor("#6F4FF1"));
                break;
            case 2:
                holder.colorfulBar.setBackgroundColor(Color.parseColor("#F52439"));
                break;
            default:
                break;
        }
        //holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDay;
        private TextView textViewMonth;
        private View colorfulBar;
        //private TextView textViewPriority;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewDay = itemView.findViewById(R.id.tvDay);
            textViewMonth = itemView.findViewById(R.id.tvMonth);
            colorfulBar = itemView.findViewById(R.id.colorfulBar);
            //textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private String getDayFromDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String dateString = formatter.format(new Date(date));
        return dateString;
    }

    private String getMonthFromDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM");
        String monthString = formatter.format(new Date(date)).substring(0, 3).toUpperCase();
        return monthString;
    }
}
