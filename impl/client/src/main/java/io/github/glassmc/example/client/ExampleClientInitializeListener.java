package io.github.glassmc.example.client;

import com.github.glassmc.loader.GlassLoader;
import com.github.glassmc.loader.Listener;

public class ExampleClientInitializeListener implements Listener {

    @Override
    public void run() {
        //System.out.println("Am I currently on MAC (retrieved from minecraft)? " + GlassLoader.getInstance().getInterface(IExampleInterface.class).isMAC());
        //System.out.println("This is only a feature on versions past 0.4.0!");
    }

}
