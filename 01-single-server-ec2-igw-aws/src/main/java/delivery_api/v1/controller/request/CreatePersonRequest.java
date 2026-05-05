package delivery_api.v1.controller.request;

public record CreatePersonRequest(
        String name,
        Integer age,
        String document,
        String zipCode,
        String street,
        Integer number,
        String complement,
        String neighborhood,
        String city,
        String state
) {
}