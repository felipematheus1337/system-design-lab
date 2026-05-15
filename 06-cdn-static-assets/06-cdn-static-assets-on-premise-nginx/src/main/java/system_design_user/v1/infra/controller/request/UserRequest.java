package system_design_user.v1.infra.controller.request;

import java.util.List;

public record UserRequest(String firstName, String lastName,
                          AddressRequest addressRequest, List<String> phoneNumbers) {
}
