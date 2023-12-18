package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.client.GlobalContext;
import pl.put.poznan.transformer.client.data.ServicesList.ServicesListContent.ServiceDescription;
import pl.put.poznan.transformer.client.data.TransformRequest;
import pl.put.poznan.transformer.client.data.TransformResponse;
import pl.put.poznan.transformer.client.rest.RESTController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Objects;

/**
 * Main window of the application.
 */
public class MainWindow extends JFrame {

    /**
     * Stores the text to be transformed.
     */
    private final JTextArea topTextBox = new JTextArea();
    /**
     * Stores the transformed text.
     */
    private final JTextArea bottomTextBox = new JTextArea();
    /**
     * The dropdown list of available services.
     */
    private ServicesDropdownList serviceDropdown;

    /**
     * Creates the main window.
     */
    public MainWindow() {

        super("Text Transformer Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new GridLayout(1, 1));
        addComponents();
        pack();
        final var size = new Dimension(800, 400);
        setMinimumSize(size);
        setSize(size);
        setLocationRelativeTo(null);
    }

    /**
     * Adds all components to the window.
     */
    private void addComponents() {

        final var mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        add(mainPanel);

        topTextBox.setEditable(true);
        mainPanel.add(topTextBox);

        final var controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(1, 2));
        addControls(controlsPanel);
        mainPanel.add(controlsPanel);

        bottomTextBox.setEditable(false);
        mainPanel.add(bottomTextBox);
    }

    /**
     * Adds the controls to the center panel.
     *
     * @param panel the panel to add the controls to, should be the center panel
     */
    private void addControls(@NotNull JPanel panel) {

        final var dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new GridLayout(2, 1));
        panel.add(dropdownPanel);

        serviceDropdown = new ServicesDropdownList();
        dropdownPanel.add(serviceDropdown);

        final var serviceDescription = new WrapLabel("Yeet");
        dropdownPanel.add(serviceDescription);

        serviceDropdown.addActionListener(e -> {
            final var selectedService = Objects.requireNonNull(serviceDropdown.getSelectedService());
            serviceDescription.setText(selectedService.getDescription());
        });

        panel.add(transformationButton());
    }

    /**
     * Creates the "perform transformation" button.
     *
     * @return the initialized button
     */
    @NotNull
    private JButton transformationButton() {
        final var performTransformation = new JButton("Perform transformation!");
        performTransformation.addActionListener(e -> {
            final var service = serviceDropdown.getSelectedService();
            if (service != null) {
                try {
                    final var response = RESTController.transform(GlobalContext.getURL(), buildTransformRequest(service));
                    final TransformResponse text = GlobalContext.OBJECT_MAPPER.readValue(response, TransformResponse.class);
                    bottomTextBox.setText(text.getMessage());
                } catch (final Exception ex) {
                    Messages.error(ex.getMessage());
                }
            }
        });
        return performTransformation;
    }

    /**
     * Builds a {@link TransformRequest} with the specified service and the text from the top text box.
     *
     * @param service the service to be used for transformation
     * @return the built {@link TransformRequest} object
     */
    private @NotNull TransformRequest buildTransformRequest(@NotNull ServiceDescription service) {
        return new TransformRequest(topTextBox.getText()).with(service.getName(), true);
    }
}
