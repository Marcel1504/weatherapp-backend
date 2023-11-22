package me.marcelberger.weatherapp.assistant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "as_chat")
public class ChatEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private List<ChatMessageEntity> messages = new ArrayList<>();
}
