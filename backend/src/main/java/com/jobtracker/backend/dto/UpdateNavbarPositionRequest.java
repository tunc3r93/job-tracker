package com.jobtracker.backend.dto;

import com.jobtracker.backend.model.NavbarPosition;
import javax.validation.constraints.NotNull;

public class UpdateNavbarPositionRequest {
    @NotNull(message = "Position is required")
    private NavbarPosition position;

    public NavbarPosition getPosition() { return position; }
    public void setPosition(NavbarPosition position) { this.position = position; }
}
