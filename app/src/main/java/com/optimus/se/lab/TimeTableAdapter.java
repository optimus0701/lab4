package com.optimus.se.lab;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeTableAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<TimeTable> timeTables;
    private int type = -1;

    public TimeTableAdapter(Context context, int layout, ArrayList<TimeTable> timeTables, int type) {
        this.context = context;
        this.layout = layout;
        this.timeTables = timeTables;
        this.type = type;
    }

    @Override
    public int getCount() {
        return timeTables.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView id;
        ImageView img_save, img_remove;
        GridView gv_items;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.id = view.findViewById(R.id.time_table_id);
            holder.img_save = view.findViewById(R.id.img_save);
            holder.img_remove = view.findViewById(R.id.img_remove);
            holder.gv_items = view.findViewById(R.id.time_table);

            if (type == 2) {
                holder.img_save.setVisibility(View.VISIBLE);
                holder.img_remove.setVisibility(View.VISIBLE);
            }
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TimeTable timeTable = timeTables.get(i);
        holder.id.setText(timeTable.getId());

        ArrayList<String> items = timeTable.getItems();


        ViewGroup.LayoutParams params = holder.gv_items.getLayoutParams();
        params.height = 10 * items.size();
        holder.gv_items.setLayoutParams(params);

        ItemAdapter adapter = new ItemAdapter(context, R.layout.item, items);
        holder.gv_items.setAdapter(adapter);
        holder.gv_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (type == 2) {
                    showDialogEdit(i, context, items);
                } else {
                    Toast.makeText(context, "you do not have permission to change this field", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        holder.img_remove.setOnClickListener(view1 -> {
            timeTables.remove(i);
            notifyDataSetChanged();
        });
        
        holder.img_save.setOnClickListener(view12 -> {
            Toast.makeText(context, "Save!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void showDialogEdit(int position, Context context, ArrayList<String> items) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);

        EditText editText = dialog.findViewById(R.id.dialog_edit_edt);
        Button btnSave = dialog.findViewById(R.id.dialog_edit_btn_save);
        editText.setText(items.get(position));
        btnSave.setOnClickListener(view -> {
            items.set(position, editText.getText().toString().trim());
            notifyDataSetChanged();
            dialog.dismiss();
        });

        dialog.show();
    }
}
