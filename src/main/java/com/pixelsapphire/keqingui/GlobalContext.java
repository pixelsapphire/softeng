package com.pixelsapphire.keqingui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pixelsapphire.keqingui.rest.RESTController;
import com.pixelsapphire.keqingui.rest.ServicesList;
import com.pixelsapphire.keqingui.rest.ServicesList.ServicesListContent.ServiceDescription;
import com.pixelsapphire.keqingui.data.S31n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

public class GlobalContext {

    private static final List<ServiceDescription> availableServices = new ArrayList<>();
    private static final List<Runnable> updateCallbacks = new ArrayList<>();
    private static URL selectedURL;

    public static void setURL(@NotNull URL url) {
        selectedURL = url;
    }

    public static URL getURL() {
        return selectedURL;
    }

    public static void updateServices() throws IOException {
        final String json = RESTController.getServices(selectedURL);
        final ServicesList services = S31n.OBJECT_MAPPER.readValue(json, ServicesList.class);
        services.getMessage().getContent().stream()
                .filter(ServiceDescription::isNotRequired)
                .forEach(availableServices::add);
        updateCallbacks.forEach(Runnable::run);
    }

    public static void addUpdateListener(@NotNull Runnable listener) {
        updateCallbacks.add(listener);
    }

    public static @NotNull @UnmodifiableView List<ServiceDescription> getAvailableServices() {
        return Collections.unmodifiableList(availableServices);
    }
}
