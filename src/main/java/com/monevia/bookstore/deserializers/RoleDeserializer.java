package com.monevia.bookstore.deserializers;

import com.monevia.bookstore.user_service.Role;

public class RoleDeserializer extends EnumDeserializer<Role> {
    public RoleDeserializer() {
        super(Role.class);
    }
}
