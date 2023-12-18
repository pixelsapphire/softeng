package pl.put.poznan.transformer.client.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.put.poznan.transformer.client.rest.RESTController;

import java.util.List;

/**
 * This class represents a list of services. It is used by Jackson Databind to deserialize the response from the server.
 *
 * @see RESTController#getServices
 */
@SuppressWarnings("unused")
public class ServicesList {

    private String error;
    private ServicesListContent message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ServicesListContent getMessage() {
        return message;
    }

    public void setMessage(ServicesListContent message) {
        this.message = message;
    }

    public static class ServicesListContent {

        private String header;
        private List<ServiceDescription> content;

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public List<ServiceDescription> getContent() {
            return content;
        }

        public void setContent(List<ServiceDescription> content) {
            this.content = content;
        }

        public static class ServiceDescription {

            private String name, type, description;
            private boolean required;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isRequired() {
                return required;
            }

            public void setRequired(boolean required) {
                this.required = required;
            }

            @JsonIgnore
            public boolean isNotRequired() {
                return !required;
            }
        }
    }
}
