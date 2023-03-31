package edu.ucsf.rbvi.internal.myapp;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;

import java.util.Properties;

/**
 * {@code CyActivator} is a class that is a starting point for OSGi bundles.
 * 
 * A quick overview of OSGi: The common currency of OSGi is the <i>service</i>.
 * A service is merely a Java interface, along with objects that implement the
 * interface. OSGi establishes a system of <i>bundles</i>. Most bundles import
 * services. Some bundles export services. Some do both. When a bundle exports a
 * service, it provides an implementation to the service's interface. Bundles
 * import a service by asking OSGi for an implementation. The implementation is
 * provided by some other bundle.
 * 
 * When OSGi starts your bundle, it will invoke {@CyActivator}'s
 * {@code start} method. So, the {@code start} method is where
 * you put in all your code that sets up your app. This is where you import and
 * export services.
 * 
 * Your bundle's context{@code Bundle-Activator} manifest entry has a fully-qualified
 * path to this ccontextlass. It's not necessary to inherit from
 * {@code AbstractCyActivator}. However, we provide this class as a convenience
 * to make it easier to work with OSGi.
 *
 * Note: AbstractCyActivator already provides its own {@code stop} method, which
 * {@code unget}s any services we fetch using getService().
 */
public class CyActivator extends AbstractCyActivator {
	/**
	 * This is the {@code start} method, which sets up your app. The
	 * {@code BundleContext} object allows you to communicate with the OSGi
	 * environment. You use {@code BundleContext} to import services or ask OSGi
	 * about the status of some service.
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		CyNetworkNaming cyNetworkNamingServiceRef = getService(context,CyNetworkNaming.class);

		CyNetworkFactory cyNetworkFactoryServiceRef = getService(context,CyNetworkFactory.class);
		CyNetworkManager cyNetworkManagerServiceRef = getService(context,CyNetworkManager.class);

		CyNetworkViewFactory cyNetworkViewFactoryServiceRef  = getService(context,CyNetworkViewFactory.class);
		CyNetworkViewManager cyNetworkViewManagerServiceRef = getService(context,CyNetworkViewManager.class);

		BioTaskFactory bioTaskFactory = new BioTaskFactory(cyNetworkNamingServiceRef, cyNetworkFactoryServiceRef,cyNetworkManagerServiceRef, cyNetworkViewFactoryServiceRef,cyNetworkViewManagerServiceRef);

		Properties bioTaskFactoryProps = new Properties();
		bioTaskFactoryProps.setProperty("preferredMenu","Apps.Samples");
		bioTaskFactoryProps.setProperty("title","Create Network View");
		registerService(context,bioTaskFactory, TaskFactory.class, bioTaskFactoryProps);
	}
}
