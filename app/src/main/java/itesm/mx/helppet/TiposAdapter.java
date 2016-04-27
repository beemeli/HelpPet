package itesm.mx.helppet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leslie on 03/04/2016.
 */
public class TiposAdapter extends ArrayAdapter<TipoMascota> {

    private final Context context;

    public TiposAdapter(Context context, List<TipoMascota> list){

        super(context, 0 , list);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoMascota tipoMascota = getItem(position); //creo el objeto
        if(convertView == null) { //convertView renglon que voy a crear
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tipos_layout, parent, false); //crear renglon
        }
        int id;
        System.out.println("--------------Imagen: "+tipoMascota.getImagen());
        if(tipoMascota.getImagen().equals("")){
            id = context.getResources().getIdentifier("itesm.mx.helppet:drawable/fiestero" , null, null);
        }else{
            id = context.getResources().getIdentifier("itesm.mx.helppet:drawable/" + tipoMascota.getImagen(), null, null);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.imagenPerro);
        img.setImageResource(id);

        TextView nombre = (TextView)convertView.findViewById(R.id.nombre_text);
        nombre.setText(tipoMascota.getNombre());

        TextView categoria = (TextView)convertView.findViewById(R.id.categoria_text);
        categoria.setText("Cate: "+tipoMascota.getClasificacion());

        return convertView;
    }
}