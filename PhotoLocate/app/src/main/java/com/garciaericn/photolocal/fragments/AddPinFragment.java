package com.garciaericn.photolocal.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.garciaericn.photolocal.R;
import com.garciaericn.photolocal.data.Pin;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class AddPinFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "com.garciaericn.photolocal.fragments.AddPinFragment.TAG";
    private static final int REQUEST_TAKE_PICTURE = 0X23412;
    private LatLng mLatLng;
    private EditText mTitleET;
    private EditText mDescriptionET;
    private ImageView mImageView;
    private Uri mImageUri;
    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public AddPinFragment() {

    }

    public static AddPinFragment getInstance(LatLng latLng) {
        AddPinFragment fragment = new AddPinFragment();
        if (latLng != null) {
            Bundle b = new Bundle();
            b.putParcelable(Pin.LAT_LNG, latLng);
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Ensure activity implements OnFragmentInteractionListener
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mContext = getActivity();

        Bundle b = getArguments();
        if (b != null && b.containsKey(Pin.LAT_LNG)) {
            mLatLng = b.getParcelable(Pin.LAT_LNG);
        } else {
            mLatLng = mListener.getCurrentLocation();
        }
        mListener.setHomeAsUp();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_pin, container, false);

        TextView latTV = (TextView) view.findViewById(R.id.lat_holder);
        TextView lngTV = (TextView) view.findViewById(R.id.lng_holder);

        if (mLatLng != null) {
            latTV.setText(String.valueOf(mLatLng.latitude));
            lngTV.setText(String.valueOf(mLatLng.longitude));
        } else {
            latTV.setText("Updating...");
            lngTV.setText("Updating...");
        }

        mImageView = (ImageView) view.findViewById(R.id.new_pin_image);

        ImageButton addImageBtn = (ImageButton) view.findViewById(R.id.add_image_button);
        addImageBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        mTitleET = (EditText) view.findViewById(R.id.new_pin_title);
        mDescriptionET = (EditText) view.findViewById(R.id.new_pin_description);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent cancelIntent = new Intent();
                break;
            case R.id.action_save:
                savePin();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void savePin() {

        Pin newPin = new Pin(mLatLng, mTitleET.getText().toString(), mDescriptionET.getText().toString());
        newPin.setImageUriString(mImageUri.toString());
        mListener.savePin(newPin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_image_button:
                // TODO: Set up camera input
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImageUri = getImageUri();
                if(mImageUri != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                }
                startActivityForResult(intent, REQUEST_TAKE_PICTURE);


                break;
            default:
                Toast.makeText(getActivity(), "Add image button pressed", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private Uri getImageUri() {
        String imageName = new SimpleDateFormat("MMddyyyy_HHmmss")
                .format(new Date(System.currentTimeMillis()));
        File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // Creating our own folder in the default directory.
        File appDir = new File(imageDir, "Photo Local");
        appDir.mkdirs();
        File image = new File(appDir, imageName + ".jpg");
        try {
            image.createNewFile();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(image);
    }

    private void addImageToGallery(Uri imageUri) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        getActivity().sendBroadcast(scanIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getActivity();
        if (requestCode == REQUEST_TAKE_PICTURE && resultCode != Activity.RESULT_CANCELED) {
            if(mImageUri != null) {
                mImageView.setImageBitmap(BitmapFactory.decodeFile(mImageUri.getPath()));
                addImageToGallery(mImageUri);
            } else {
                mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            }
        }
    }

    /**
     * AddPinFragment
     * Interface
     * */
    public interface OnFragmentInteractionListener {
        public void setHomeAsUp();
        public void savePin(Pin pin);
        public LatLng getCurrentLocation();
    }
}
