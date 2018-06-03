package com.example.strahinja.contacts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_contact);
        vHolder.item.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "Test click " + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                TextView dialogName = (TextView) myDialog.findViewById(R.id.dialog_name);
                TextView dialogPhone = (TextView) myDialog.findViewById(R.id.dialog_phone);
                ImageView dialogImage = (ImageView) myDialog.findViewById(R.id.dialog_image);
                dialogName.setText(mData.get(vHolder.getAdapterPosition()).getFirstName() + " " + mData.get(vHolder.getAdapterPosition()).getLastName());
                dialogPhone.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
//        dialogImage.setImageDrawable(mData.get(vHolder.getAdapterPosition()).getImage());
                dialogImage.setImageResource(R.drawable.ic_person);

                myDialog.show();

                Button btn = (Button) myDialog.findViewById(R.id.dialog_edit);
                btn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(mContext, EditContactActivity.class);

                        Log.i("FNAME", mData.get(vHolder.getAdapterPosition()).getFirstName());
                        Log.i("LNAME", mData.get(vHolder.getAdapterPosition()).getLastName());
                        Log.i("PHONE", mData.get(vHolder.getAdapterPosition()).getPhone());
                        myIntent.putExtra("fname", String.valueOf(mData.get(vHolder.getAdapterPosition()).getFirstName()));
                        myIntent.putExtra("lname", String.valueOf(mData.get(vHolder.getAdapterPosition()).getLastName()));
                        myIntent.putExtra("phone", String.valueOf(mData.get(vHolder.getAdapterPosition()).getPhone()));
                        myIntent.putExtra("id", String.valueOf(mData.get(vHolder.getAdapterPosition()).getId()));
                        myDialog.dismiss();
                        mContext.startActivity(myIntent);
                    }
                });
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textViewName.setText(mData.get(position).getFirstName() + " " + mData.get(position).getLastName());
        holder.textViewPhone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(R.drawable.ic_person);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends ViewHolder {

        private LinearLayout item;
        private TextView textViewName;
        private TextView textViewPhone;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            item = (LinearLayout) itemView.findViewById(R.id.contact_item);
            textViewName = (TextView) itemView.findViewById(R.id.contact_name);
            textViewPhone = (TextView) itemView.findViewById(R.id.contact_phone);
            img = (ImageView) itemView.findViewById(R.id.contact_img);

        }
    }
}
