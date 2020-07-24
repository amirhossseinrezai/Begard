package com.example.begard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.dialog.MaterialDialogs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddingDataByUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddingDataByUserFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddingDataByUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddingDataByUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddingDataByUserFragment newInstance(String param1, String param2) {
        AddingDataByUserFragment fragment = new AddingDataByUserFragment();
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
    ImageView imgAdderListener;
    EditText edtPlaceName,edtAbout,edtAddress;
    Button btnregister;
    DatabaseManager dbm;

    ArrayList imgAdder = new ArrayList() ;
    ImageView picImage;
    private static final int IMAGE_PICK_CODE = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adding_data_by_user,container,false);
        imgAdder.add(R.drawable.nature);imgAdder.add(R.drawable.banner);imgAdder.add(R.drawable.camera);
        picImage = view.findViewById(R.id.imgAdderListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.rclImageAdder);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MyRecyclerView rclView =new MyRecyclerView(getActivity(),imgAdder);
        recyclerView.setAdapter(rclView);
        rclView.setOnItemClickListener(new MyRecyclerView.onItemClickListener(){
            @Override
            public void onDeleteClick(int position) {
                imgAdder.remove(position);
                rclView.notifyItemRemoved(position);
            }
        });
        picImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              pickImageFromGallery();
              rclView.notifyDataSetChanged();
            }
        });
        dbm = new DatabaseManager(getActivity());
        edtAbout = view.findViewById(R.id.edtAbout);
        //edtAddress = view.findViewById(R.id.)
        edtPlaceName =  view.findViewById(R.id.edtPlaceName);
        btnregister =  view.findViewById(R.id.btnregister);
        //imgAdderListener = view.findViewById(R.id.)
        return view;
    }

    private void pickImageFromGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select"),IMAGE_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == getActivity().RESULT_OK && requestCode == IMAGE_PICK_CODE){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                //picImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        final String PlaceName = edtPlaceName.getText().toString();
        final String About = edtAbout.getText().toString();
        //final String Address = edtAddress.getText().toString();
        final String Placename = edtPlaceName.getText().toString();
        Data place = new Data (PlaceName,About);
        place.Placename = PlaceName;
        place.About = About;
        //place.Address = Address;

    }

    private static class MyRecyclerView extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {

        private ArrayList arrayList;
        Context context;
        private onItemClickListener mlistener;
        public interface onItemClickListener{
            void onDeleteClick(int position);
        }
        public void setOnItemClickListener(onItemClickListener listener) {
            mlistener = listener;
        }
        public MyRecyclerView(Context context,ArrayList arrayList) {
            this.arrayList = arrayList;
            this.context =context;
        }

        @NonNull
        @Override
        public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.piture_adder,parent,false);
            return new ViewHolder(view,mlistener);
        }

        @Override
        public void onBindViewHolder(@NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).onBind(position);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public  class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
            ImageView imageView;

            public ViewHolder(@NonNull View itemView,final onItemClickListener listener) {
                super(itemView);
                imageView=itemView.findViewById(R.id.imgAdderSample);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            }


            public void onBind(int position){
                imageView.setImageResource((Integer) arrayList.get(position));
            }

        }
    }

}