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

public class MainWindow extends JFrame {

    private final JTextArea topTextBox = new JTextArea(), bottomTextBox = new JTextArea();
    private ServicesDropdownList serviceDropdown;

    public MainWindow() {

        super("KeqinGUI");
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

        final var yeetButton = new JButton("Perform transformation!");

        yeetButton.addActionListener(e -> {
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
        panel.add(yeetButton);
    }

    private @NotNull TransformRequest buildTransformRequest(@NotNull ServiceDescription service) {
        return new TransformRequest(topTextBox.getText()).with(service.getName(), true);
    }
}
