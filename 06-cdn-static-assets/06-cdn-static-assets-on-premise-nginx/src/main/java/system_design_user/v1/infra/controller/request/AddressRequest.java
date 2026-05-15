package system_design_user.v1.infra.controller.request;

public record AddressRequest(String streetAddress, String city,
        String state,
        Integer postalCode) {
}
