//package com.example.mycalculator;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//public class btnAdapter extends ArrayAdapter<btn> {
//    private Context context;
//    private TextView cal_text;
//
//    public btnAdapter(@NonNull Context context, ArrayList<btn> btnArrayList, TextView cal_text) {
//        super(context, 0, btnArrayList);
//        this.context = context;
//        this.cal_text = cal_text;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listitemView = convertView;
//        if (listitemView == null) {
//            // Layout Inflater inflates each item to be displayed in GridView.
//            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.number_btn, parent, false);
//        }
//        final btn currentBtn = getItem(position);
//        Button num_btn = listitemView.findViewById(R.id.btn);
//        num_btn.setText(currentBtn.getNumber());
//        num_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button click here
//                updateTextView(currentBtn.getNumber());
//            }
//        });
//
//
//        return listitemView;
//    }
//    private void updateTextView(String newText) {
//        String currentText = cal_text.getText().toString();
//        cal_text.setText(currentText + newText);
//
//    }
//}
