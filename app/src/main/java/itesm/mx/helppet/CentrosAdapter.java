package itesm.mx.helppet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leslie on 13/03/2016.
 */
public class CentrosAdapter  extends ArrayAdapter<Centro> {

    private final Context context;

    public CentrosAdapter(Context context, List<Centro> list){

        super(context, 0 , list);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Centro centro = getItem(position); //creo el objeto
        if(convertView == null) { //convertView renglon que voy a crear
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.asociacioneslayout, parent, false); //crear renglon
        }
        int id;
        //Imagen

        TextView nombre = (TextView)convertView.findViewById(R.id.nombre);
        nombre.setText(centro.getNombre());

        TextView temp = (TextView)convertView.findViewById(R.id.telefono);
        temp.setText("Telefono: "+centro.getTelefono());

        TextView cap = (TextView)convertView.findViewById(R.id.direccion);
        cap.setText("Direcciòn: "+centro.getDireccion());

        return convertView;
    }
}
