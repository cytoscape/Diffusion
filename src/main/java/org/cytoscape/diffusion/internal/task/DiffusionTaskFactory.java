package org.cytoscape.diffusion.internal.task;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.diffusion.internal.ViewWriterFactoryManager;
import org.cytoscape.diffusion.internal.ui.OutputPanel;
import org.cytoscape.diffusion.internal.util.DiffusionNetworkManager;
import org.cytoscape.io.write.CyNetworkViewWriterFactory;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class DiffusionTaskFactory extends AbstractTaskFactory {
	
	private final ViewWriterFactoryManager factoryManager;
	private final DiffusionNetworkManager networkManager;
	private final OutputPanel outputPanel;
	private final CySwingApplication swingApplication;
	private final CyApplicationManager appManager;
	
	public DiffusionTaskFactory(
			DiffusionNetworkManager networkManager, OutputPanel outputPanel,
			final ViewWriterFactoryManager factoryManager, final CySwingApplication swingApplication,
			final CyApplicationManager appManager) {
		this.networkManager = networkManager;
		this.outputPanel = outputPanel;
		this.factoryManager = factoryManager;
		this.swingApplication = swingApplication;
		this.appManager = appManager;
	}


	@Override
	public TaskIterator createTaskIterator() {
		final CyNetworkViewWriterFactory writerFactory = this.factoryManager.getCxFactory();

		if (writerFactory == null) {
			throw new IllegalStateException(
					"CXWriterFactory is not available.  " + "Please make sure you have proper dependencies");
		}

		return new TaskIterator(new DiffuseSelectedTask(networkManager, writerFactory, outputPanel, swingApplication, appManager));
	}

}
