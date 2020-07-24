package com.example.begard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    EditText edtName, edtEmail, edtID, edtNumber;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    DatabaseManager dbm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        dbm = new DatabaseManager(getActivity());
        edtID = view.findViewById(R.id.edtID);
        edtName =  view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtNumber =  view.findViewById(R.id.edtEmail);
        btnInsert =  view.findViewById(R.id.btnInsert);
        btnView = view.findViewById(R.id.btnView);
        btnUpdate =  view.findViewById(R.id.btnUpdate);
        btnDelete =  view.findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        final String UserID = edtID.getText().toString();
        final String fullname = edtName.getText().toString();
        final String email = edtEmail.getText().toString();
        final String number = edtNumber.getText().toString();
        final String password = edtNumber.getText().toString();

        if (TextUtils.isEmpty(UserID) || TextUtils.isEmpty(fullname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(number)
                || TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(), "تمامی فیلدها باید تکمیل شود", Toast.LENGTH_LONG).show();

        } else {

            User user = new User(UserID,fullname,email,number,password);
            user.UserID = UserID;
            user.fullName = fullname;
            user.email = email;
            user.number = number;
            user.password = password;
            dbm.insertUser(user);
            Toast.makeText(getActivity(), "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_LONG).show();
    }
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vUserID = edtID.getText().toString();
                User vuser = dbm.getUser(vUserID);
                edtName.setText(vuser.fullName);
                edtEmail.setText(vuser.email);
                edtNumber.setText(vuser.number);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uID = edtID.getText().toString();
                String uName = edtName.getText().toString();
                String unumber = edtNumber.getText().toString();
                String uemail = edtEmail.getText().toString();
                String upassword = edtNumber.getText().toString();

                User uUser = new User(UserID,fullname,email,number,password);
                uUser.UserID = uID;
                uUser.fullName = uName;
                uUser.email = uemail;
                uUser.number = unumber;
                uUser.password = upassword;
                dbm.updateUser(uUser);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delID = edtID.getText().toString();
                User duser = new User(UserID,fullname,email,number,password);
                duser.UserID = delID;
                boolean del = dbm.deletePerson(duser);

                if (del)
                    Toast.makeText(getActivity(), "اطلاعات حذف شد", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(), "در حذف اطلاعات اشکالی وجود دارد", Toast.LENGTH_LONG).show();


            }
        });
    }

}