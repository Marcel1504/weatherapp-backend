package me.marcelberger.weatherapp.assistant.entity;

import jakarta.persistence.*;
import lombok.*;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "as_chat_message")
public class ChatMessageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ChatRoleEnum role;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ChatTypeEnum type;

    @Column(name = "content")
    private String content;

    @Column(name = "openai_message")
    private String openAIMessage;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
