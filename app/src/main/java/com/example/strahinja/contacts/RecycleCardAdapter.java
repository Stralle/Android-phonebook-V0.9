package com.example.strahinja.contacts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleCardAdapter extends RecyclerView.Adapter<RecycleCardAdapter.ViewHolder> {

    private static ArrayList<Contact> mData;
    private Context context;
    Dialog myDialog;

    public RecycleCardAdapter(ArrayList<Contact> mData, Context context){
        this.mData = new ArrayList<>();
        for(Contact c: mData){
            if(c.getFavorite() == 1){
                this.mData.add(c);
            }
        }
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    public static ArrayList<Contact> getmData() {
        return mData;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int pos = position;
        //Glide.with(context).asBitmap().load(mData.get(position).getImgUrl()).into(holder.getImage());
        holder.getItemName().setText(mData.get(position).getFirstName() + " " + mData.get(position).getLastName());
        holder.getItemContact().setText(mData.get(position).getPhone());
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_contact);
        holder.getLayout().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "Test click " + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                TextView dialogName = (TextView) myDialog.findViewById(R.id.dialog_name);
                TextView dialogPhone = (TextView) myDialog.findViewById(R.id.dialog_phone);
                ImageView dialogImage = (ImageView) myDialog.findViewById(R.id.dialog_image);
                dialogName.setText(mData.get(holder.getAdapterPosition()).getFirstName() + " " + mData.get(holder.getAdapterPosition()).getLastName());
                dialogPhone.setText(mData.get(holder.getAdapterPosition()).getPhone());
//        dialogImage.setImageDrawable(mData.get(vHolder.getAdapterPosition()).getImage());
                dialogImage.setImageResource(R.drawable.ic_person);

                myDialog.show();

                Button btn = (Button) myDialog.findViewById(R.id.dialog_edit);
                btn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(context, EditContactActivity.class);

                        Log.i("FNAME", mData.get(holder.getAdapterPosition()).getFirstName());
                        Log.i("LNAME", mData.get(holder.getAdapterPosition()).getLastName());
                        Log.i("PHONE", mData.get(holder.getAdapterPosition()).getPhone());
                        myIntent.putExtra("fname", String.valueOf(mData.get(holder.getAdapterPosition()).getFirstName()));
                        myIntent.putExtra("lname", String.valueOf(mData.get(holder.getAdapterPosition()).getLastName()));
                        myIntent.putExtra("phone", String.valueOf(mData.get(holder.getAdapterPosition()).getPhone()));
                        myIntent.putExtra("id", String.valueOf(mData.get(holder.getAdapterPosition()).getId()));
                        myDialog.dismiss();
                        context.startActivity(myIntent);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView itemName;
        private TextView itemContact;
        private TextView itemEmail;
        private RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cardImage);
            itemName = itemView.findViewById(R.id.cardName);
            itemContact = itemView.findViewById(R.id.cardContact);
            layout = itemView.findViewById(R.id.cardRelativeLayout);
        }


        public ImageView getImage() {
            return image;
        }

        public RelativeLayout getLayout() {
            return layout;
        }

        public TextView getItemContact() {
            return itemContact;
        }

        public TextView getItemEmail() {
            return itemEmail;
        }

        public TextView getItemName() {
            return itemName;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public void setItemContact(TextView itemContact) {
            this.itemContact = itemContact;
        }

        public void setItemEmail(TextView itemEmail) {
            this.itemEmail = itemEmail;
        }

        public void setItemName(TextView itemName) {
            this.itemName = itemName;
        }

        public void setLayout(RelativeLayout layout) {
            this.layout = layout;
        }
    }
}
