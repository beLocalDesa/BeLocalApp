package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belocal.MainActivity;
import com.example.belocal.R;
import com.example.belocal.StoreActivity;

import java.util.List;

import model.MStore;

public class StoreAdapter extends ArrayAdapter<MStore> {

    private Context context;
    private List<MStore> listStore;

    public StoreAdapter(@NonNull Context context, int resource, @NonNull List<MStore> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listStore = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_store, parent, false);

        TextView textStoreId = (TextView) rowView.findViewById(R.id.textStoreId);
        TextView textStoreName = (TextView) rowView.findViewById(R.id.textStoreName);

        textStoreId.setText(String.format("Store ID: %d", listStore.get(pos).getId()));
        textStoreName.setText(String.format("Store Name: %s", listStore.get(pos).getName()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, listStore.get(pos).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }



}
