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

/**
 * Stores the global context of the application.
 */
public class GlobalContext {

    /**
     * The object mapper used to deserialize JSON.
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     * Stores the list of available services.
     */
    private static final List<ServiceDescription> availableServices = new ArrayList<>();
    /**
     * Stores the list of callbacks to be called when the list of available services is updated.
     */
    private static final List<Runnable> updateCallbacks = new ArrayList<>();
    /**
     * Stores the URL of the Text Transformer server.
     */
    private static URL selectedURL;

    /**
     * Returns the URL of the Text Transformer server, which was selected by the user.
     *
     * @return the selected URL
     */
    public static URL getURL() {
        return selectedURL;
    }

    /**
     * Sets the URL of the Text Transformer server that was selected by the user.
     *
     * @param url the selected URL
     */
    public static void setURL(@NotNull URL url) {
        selectedURL = url;
    }

    /**
     * Updates the list of available services.
     *
     * @throws IOException if an I/O error occurs while retrieving the list of services
     */
    public static void updateServices() throws IOException {
        final String json = RESTController.getServices(selectedURL);
        final ServicesList services = OBJECT_MAPPER.readValue(json, ServicesList.class);
        services.getMessage().getContent().stream()
                .filter(ServiceDescription::isNotRequired)
                .forEach(availableServices::add);
        updateCallbacks.forEach(Runnable::run);
    }

    /**
     * Adds a listener to be called when the list of available services is updated.
     *
     * @param listener the listener to be called
     */
    public static void addUpdateListener(@NotNull Runnable listener) {
        updateCallbacks.add(listener);
    }

    /**
     * Returns the stored list of available services. Does not call the server to update the list.
     *
     * @return the list of available services
     */
    public static @NotNull @UnmodifiableView List<ServiceDescription> getAvailableServices() {
        return Collections.unmodifiableList(availableServices);
    }
}
