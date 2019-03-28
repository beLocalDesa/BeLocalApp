package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belocal.R;

import java.util.List;

import model.MProduct;
import model.MStore;

public class ProductAdapter extends ArrayAdapter<MProduct> {

    private Context context;
    private List<MProduct> listProduct;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<MProduct> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listProduct = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_store, parent, false);

        TextView textProductId = (TextView) rowView.findViewById(R.id.textProductId);
        TextView textProductName = (TextView) rowView.findViewById(R.id.textProductName);

        textProductId.setText(String.format("Product ID: %d", listProduct.get(pos).getId()));
        textProductName.setText(String.format("Product Name: %s", listProduct.get(pos).getName()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, listProduct.get(pos).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }





}
