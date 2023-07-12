package com.chat.chatroom.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "rooms")
public class Rooms {

    @Id
    @GeneratedValue
    private Long roomId;
    @Column
    private String roomName;
    @Column
    private String roomStyle;
    @Column
    private RoomPrivacyEnum privacy;

    @ManyToMany(mappedBy = "userRooms", fetch = FetchType.LAZY)
    private Set<AppUser> users = new HashSet<>();

}
