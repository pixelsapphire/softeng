package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.Nullable;
import pl.put.poznan.transformer.client.GlobalContext;
import pl.put.poznan.transformer.client.data.ServicesList.ServicesListContent.ServiceDescription;

import javax.swing.JComboBox;

/**
 * The dropdown list of available services.
 */
public class ServicesDropdownList extends JComboBox<String> {

    /**
     * Creates the dropdown list of available services.
     */
    public ServicesDropdownList() {
        final var services = GlobalContext.getAvailableServices();
        if (!services.isEmpty()) services.stream().map(ServiceDescription::getName).forEach(this::addItem);
        else {
            addItem("Failed to load services list!");
            setEnabled(false);
        }
    }

    /**
     * Returns the currently selected service description.
     *
     * @return the selected service
     */
    public @Nullable ServiceDescription getSelectedService() {
        final var services = GlobalContext.getAvailableServices();
        return services.isEmpty() ? null : services.get(getSelectedIndex());
    }
}
