package pl.put.poznan.transformer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import pl.put.poznan.transformer.client.data.ServicesList;
import pl.put.poznan.transformer.client.data.ServicesList.ServicesListContent.ServiceDescription;
import pl.put.poznan.transformer.client.rest.RESTController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalContext {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final List<ServiceDescription> availableServices = new ArrayList<>();
    private static final List<Runnable> updateCallbacks = new ArrayList<>();
    private static URL selectedURL;

    public static URL getURL() {
        return selectedURL;
    }

    public static void setURL(@NotNull URL url) {
        selectedURL = url;
    }

    public static void updateServices() throws IOException {
        final String json = RESTController.getServices(selectedURL);
        final ServicesList services = OBJECT_MAPPER.readValue(json, ServicesList.class);
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
