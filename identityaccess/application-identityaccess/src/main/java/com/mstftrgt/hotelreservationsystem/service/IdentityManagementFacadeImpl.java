package com.mstftrgt.hotelreservationsystem.service;

import com.mstftrgt.hotelreservationsystem.IdentityManagementFacade;
import com.mstftrgt.hotelreservationsystem.UserContract;
import com.mstftrgt.hotelreservationsystem.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IdentityManagementFacadeImpl implements IdentityManagementFacade {
    @Override
    public UserContract getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User)authentication.getPrincipal();
        return new UserContract(principal.getId());
    }
}
