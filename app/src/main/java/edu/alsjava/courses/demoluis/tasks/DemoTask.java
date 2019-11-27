package edu.alsjava.courses.demoluis.tasks;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.alsjava.courses.demoluis.model.Example;
import edu.alsjava.courses.demoluis.model.RetrieveDemo;
import edu.alsjava.courses.demoluis.model.network.request.DemoRequest;
import edu.alsjava.courses.demoluis.model.network.response.DemoResponse;

/**
 * Created by aluis on 11/26/19.
 */
public class DemoTask extends AsyncTask<Void, Void, DemoResponse> {

    private DemoRequest demoRequest;
    private RetrieveDemo retrieveDemo;

    public DemoTask(@NonNull DemoRequest demoRequest, @NonNull RetrieveDemo retrieveDemo) {
        this.demoRequest = demoRequest;
        this.retrieveDemo = retrieveDemo;
    }

    @Override
    protected void onPostExecute(DemoResponse demoResponse) {
        retrieveDemo.history(demoRequest.getOffset(), demoResponse.getTotal(), demoResponse.getExamples());
    }

    @Override
    protected DemoResponse doInBackground(Void... voids) {
        // Esto se hace asincronincronico en un hilo, como no tenemos server lo simmulamos creando una lista a mano
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setTotal(100);
        List<Example> examples = new ArrayList<>();
        for (int i = 0; i < demoRequest.getQuantity(); i++) {
            if ((i & 1) != 0) {
                examples.add(new Example("Uva", "https://www.herbazest.com/imgs/d/3/d/354166/uva.png"));
            } else {
                examples.add(new Example("Naranja", "https://naranjasamparo.net/81-large_default/naranjas-zumo-extra-.jpg"));
            }
        }
        demoResponse.setExamples(examples);
        return demoResponse;
    }
}
