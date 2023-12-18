package com.pixelsapphire.keqingui.gui;

import com.pixelsapphire.keqingui.GlobalContext;
import com.pixelsapphire.keqingui.rest.ServicesList.ServicesListContent.ServiceDescription;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComboBox;

public class ServicesDropdownList extends JComboBox<String> {

    public ServicesDropdownList() {
        final var services = GlobalContext.getAvailableServices();
        if (!services.isEmpty()) services.stream().map(ServiceDescription::getName).forEach(this::addItem);
        else {
            addItem("Failed to load services list!");
            setEnabled(false);
        }
    }

    public @Nullable ServiceDescription getSelectedService() {
        final var services = GlobalContext.getAvailableServices();
        return services.isEmpty() ? null : services.get(getSelectedIndex());
    }
}
