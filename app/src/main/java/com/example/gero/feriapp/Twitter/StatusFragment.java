package com.example.gero.feriapp.Twitter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gero.feriapp.R;
import com.squareup.picasso.Picasso;

import twitter4j.Status;
import twitter4j.User;

/**
 * Created by autentia on 17/12/13.
 */
public class StatusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Obtenemos el Bundle con la informacion del Tweet a mostrar
        Bundle arguments = getArguments();
        Status status = (Status) arguments.getSerializable(TimelineActivity.SELECTED_STATUS);

        // Obtenemos el objeto de usuario asociado al Tweet
        User user = status.getUser();

        // Hacemos Inflate del archivo xml de vista
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        TextView statusTextView = (TextView) view.findViewById(R.id.statusTextView);
        TextView favoritosTextView = (TextView) view.findViewById(R.id.favoritosTextView);
        TextView retweetsTextView = (TextView) view.findViewById(R.id.retweetsTextView);
        TextView userTextView = (TextView) view.findViewById(R.id.userTextView);
        ImageView avatarImageView = (ImageView) view.findViewById(R.id.avatarImageView);

        // Actualizamos los TextView con la informacion del objeto
        statusTextView.setText(status.getText());
        favoritosTextView.setText(status.getFavoriteCount() + " Favoritos ");
        retweetsTextView.setText(status.getRetweetCount() + " Retweets ");
        userTextView.setText(user.getName());

        // Usamos la liberia Picasso para descargar la imagen del avatar de la URL y insertarla en el ImageView
        Picasso.with(getActivity().getApplicationContext()).load(user.getMiniProfileImageURL()).into(avatarImageView);

        return view;
    }
}
