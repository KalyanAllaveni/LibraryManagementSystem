package com.example.Library.DTO;

import com.example.Library.Entities.Admin;
import com.example.Library.Entities.User;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AdminDTO {
    String name;

    public Admin convertToAdmin(User user){
        Admin admin=new Admin();
        admin.setEmail(user.getUsername());
        admin.setUser(user);
        admin.setName(this.name);
        return admin;
    }
}
