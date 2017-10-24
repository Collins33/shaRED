package com.example.root.shared;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationDetailFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.donationLocation) TextView mPatientLocation;
    @Bind(R.id.donationCondition) TextView mPatientCondition;
    @Bind(R.id.donationBloodType) TextView mPatientBloodType;

    private Request request;
    //required newInstance()

     public static DonationDetailFragment newInstance(Request request) {
         DonationDetailFragment fragment = new DonationDetailFragment();
        Bundle args = new Bundle();
         args.putParcelable("request", Parcels.wrap(request));

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //unwrap the bundle
        request=Parcels.unwrap(getArguments().getParcelable("request"));

    }

    public DonationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_donation_detail, container, false);
        Button acceptButton=(Button) view.findViewById(R.id.acceptRequest);
        acceptButton.setOnClickListener(this);
        ButterKnife.bind(this,view);

        mPatientBloodType.setText(request.getBloodtype());
        mPatientCondition.setText(request.getState());
        mPatientLocation.setText(request.getHospital());
        return view;

    }
    @Override
    public void onClick(View view){
        Intent newIntent=new Intent(getContext(),ThankyouActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(newIntent);

    }

}
