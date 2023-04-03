package biogateway.app.internal;

import biogateway.app.internal.view.MainView;
import jdk.tools.jmod.Main;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class CyActivator extends AbstractCyActivator {
    private void addServiceToControlPanel(BundleContext context) {
        //
        MainView main = new MainView();
        //   Register it as a service:
        registerService(context, main, CytoPanelComponent.class, new Properties());
    }

    @Override
    public void start(BundleContext context) throws Exception {

        CyNetworkNaming cyNetworkNamingServiceRef = getService(context, CyNetworkNaming.class);

        CyNetworkFactory cyNetworkFactoryServiceRef = getService(context, CyNetworkFactory.class);
        CyNetworkManager cyNetworkManagerServiceRef = getService(context, CyNetworkManager.class);

        CyNetworkViewFactory cyNetworkViewFactoryServiceRef = getService(context, CyNetworkViewFactory.class);
        CyNetworkViewManager cyNetworkViewManagerServiceRef = getService(context, CyNetworkViewManager.class);

        BioTaskFactory bioTaskFactory = new BioTaskFactory(cyNetworkNamingServiceRef, cyNetworkFactoryServiceRef, cyNetworkManagerServiceRef, cyNetworkViewFactoryServiceRef, cyNetworkViewManagerServiceRef);

        Properties bioTaskFactoryProps = new Properties();
        bioTaskFactoryProps.setProperty("preferredMenu", "Apps.Samples");
        bioTaskFactoryProps.setProperty("title", "Create Bioinprogresswork View");
        registerService(context, bioTaskFactory, TaskFactory.class, bioTaskFactoryProps);

        // Starts the biogateway service and adds the tab to the control pannel
        addServiceToControlPanel(context);
    }
}
