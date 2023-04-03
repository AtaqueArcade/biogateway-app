package biogateway.app.internal.view;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel implements CytoPanelComponent {
    static final String appname = "BIO-APP";
    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public CytoPanelName getCytoPanelName() {
        return CytoPanelName.WEST;
    }

    @Override
    public String getTitle() {
        return appname;
    }

    @Override
    public Icon getIcon() {
        return null;
    }
}
