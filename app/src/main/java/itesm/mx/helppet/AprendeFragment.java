package itesm.mx.helppet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class AprendeFragment extends Fragment {

    private String user;
    Animation animTranslate;
    ImageButton antes;
    ImageButton viviendo;


    public AprendeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        user = getActivity().getIntent().getStringExtra("email");
        animTranslate= AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate);

        antes = (ImageButton)getView().findViewById(R.id.button_antes);
        viviendo = (ImageButton)getView().findViewById(R.id.button_viviendo);

        antes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(getActivity(), AprendeArticulosActivity.class);
                it.putExtra("user", user);
                it.putExtra("opcion", "antes");
                startActivity(it);
            }
        });
        viviendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(getActivity(), AprendeArticulosActivity.class);
                it.putExtra("user", user);
                it.putExtra("opcion", "viviendo");
                startActivity(it);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aprende, container, false);
    }

}
